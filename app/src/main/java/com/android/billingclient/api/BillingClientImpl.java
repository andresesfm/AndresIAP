package com.android.billingclient.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.annotation.AnyThread;
import androidx.annotation.Nullable;
import com.android.billingclient.api.BillingResult.Builder;
import com.android.billingclient.api.Purchase.PurchasesResult;
import com.google.android.gms.internal.play_billing.zza;
import com.google.android.gms.internal.play_billing.zzd;
import com.google.android.gms.internal.play_billing.zzp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

class BillingClientImpl extends BillingClient {
    private volatile int zza;
    private final String zzb;
    private final Handler zzc;
    private volatile zzh zzd;
    private Context zze;
    private Context zzf;
    private volatile zzd zzg;
    private volatile zzaf zzh;
    private boolean zzi;
    private boolean zzj;
    private int zzk;
    private boolean zzl;
    private boolean zzm;
    private boolean zzn;
    private boolean zzo;
    private boolean zzp;
    private boolean zzq;
    private boolean zzr;
    private boolean zzs;
    private boolean zzt;
    private ExecutorService zzu;

    public final int getConnectionState() {
        return this.zza;
    }

    private int launchBillingFlowCpp(Activity activity, BillingFlowParams params) {
        return this.launchBillingFlow(activity, params).getResponseCode();
    }

    public final BillingResult isFeatureSupported(String var1) {
        if (!this.isReady()) {
            return zzak.zzq;
        } else {
            byte var2;
            label95: {
                switch(var1.hashCode()) {
                    case -422092961:
                        if (var1.equals("subscriptionsUpdate")) {
                            var2 = 1;
                            break label95;
                        }
                        break;
                    case 96321:
                        if (var1.equals("aaa")) {
                            var2 = 6;
                            break label95;
                        }
                        break;
                    case 97314:
                        if (var1.equals("bbb")) {
                            var2 = 5;
                            break label95;
                        }
                        break;
                    case 98307:
                        if (var1.equals("ccc")) {
                            var2 = 8;
                            break label95;
                        }
                        break;
                    case 99300:
                        if (var1.equals("ddd")) {
                            var2 = 7;
                            break label95;
                        }
                        break;
                    case 100293:
                        if (var1.equals("eee")) {
                            var2 = 9;
                            break label95;
                        }
                        break;
                    case 207616302:
                        if (var1.equals("priceChangeConfirmation")) {
                            var2 = 4;
                            break label95;
                        }
                        break;
                    case 292218239:
                        if (var1.equals("inAppItemsOnVr")) {
                            var2 = 2;
                            break label95;
                        }
                        break;
                    case 1219490065:
                        if (var1.equals("subscriptionsOnVr")) {
                            var2 = 3;
                            break label95;
                        }
                        break;
                    case 1987365622:
                        if (var1.equals("subscriptions")) {
                            var2 = 0;
                            break label95;
                        }
                }

                var2 = -1;
            }

            BillingResult var3;
            switch(var2) {
                case 0:
                    if (this.zzi) {
                        var3 = zzak.zzp;
                    } else {
                        var3 = zzak.zzi;
                    }

                    return var3;
                case 1:
                    if (this.zzj) {
                        var3 = zzak.zzp;
                    } else {
                        var3 = zzak.zzi;
                    }

                    return var3;
                case 2:
                    return this.zzG("inapp");
                case 3:
                    return this.zzG("subs");
                case 4:
                    if (this.zzm) {
                        var3 = zzak.zzp;
                    } else {
                        var3 = zzak.zzi;
                    }

                    return var3;
                case 5:
                    if (this.zzp) {
                        var3 = zzak.zzp;
                    } else {
                        var3 = zzak.zzi;
                    }

                    return var3;
                case 6:
                    if (this.zzr) {
                        var3 = zzak.zzp;
                    } else {
                        var3 = zzak.zzi;
                    }

                    return var3;
                case 7:
                    if (this.zzq) {
                        var3 = zzak.zzp;
                    } else {
                        var3 = zzak.zzi;
                    }

                    return var3;
                case 8:
                case 9:
                    if (this.zzs) {
                        var3 = zzak.zzp;
                    } else {
                        var3 = zzak.zzi;
                    }

                    return var3;
                default:
                    String var4 = String.valueOf(var1);
                    var1 = "Unsupported feature: ";
                    if (var4.length() != 0) {
                        var4 = var1.concat(var4);
                    } else {
                        var4 = new String(var1);
                    }

                    com.google.android.gms.internal.play_billing.zza.zzk("BillingClient", var4);
                    return zzak.zzv;
            }
        }
    }

    public final BillingResult launchBillingFlow(Activity var1, BillingFlowParams var2) {
        BillingResult var31;
        if (!this.isReady()) {
            var31 = zzak.zzq;
            this.zzE(var31);
            return var31;
        } else {
            ArrayList var10 = var2.zzj();
            SkuDetails var11 = (SkuDetails)var10.get(0);
            String var12 = var11.getType();
            if (var12.equals("subs") && !this.zzi) {
                com.google.android.gms.internal.play_billing.zza.zzk("BillingClient", "Current client doesn't support subscriptions.");
                var31 = zzak.zzs;
                this.zzE(var31);
                return var31;
            } else if (var2.zzm() && !this.zzl) {
                com.google.android.gms.internal.play_billing.zza.zzk("BillingClient", "Current client doesn't support extra params for buy intent.");
                var31 = zzak.zzh;
                this.zzE(var31);
                return var31;
            } else if (var10.size() > 1 && !this.zzs) {
                com.google.android.gms.internal.play_billing.zza.zzk("BillingClient", "Current client doesn't support multi-item purchases.");
                var31 = zzak.zzu;
                this.zzE(var31);
                return var31;
            } else {
                String var3 = "";

                int var4;
                String var5;
                for(var4 = 0; var4 < var10.size(); ++var4) {
                    var3 = String.valueOf(var3);
                    var5 = String.valueOf(var10.get(var4));
                    String var6 = String.valueOf(var3);
                    int var44 = var6.length();
                    String var7 = String.valueOf(var5);
                    int var48 = var7.length();
                    StringBuilder var8 = new StringBuilder(var44 + var48);
                    var8.append(var3);
                    var8.append(var5);
                    var3 = var8.toString();
                    if (var4 < var10.size() + -1) {
                        var3 = String.valueOf(var3).concat(", ");
                    }
                }

                String var40 = String.valueOf(var3);
                var4 = var40.length();
                var5 = String.valueOf(var12);
                int var41 = var5.length();
                StringBuilder var45 = new StringBuilder(var4 + 41 + var41);
                var45.append("Constructing buy intent for ");
                var45.append(var3);
                var45.append(", item type: ");
                var45.append(var12);
                com.google.android.gms.internal.play_billing.zza.zzj("BillingClient", var45.toString());
                Future var34;
                if (this.zzl) {
                    Bundle var13 = com.google.android.gms.internal.play_billing.zza.zze(var2, this.zzn, this.zzt, this.zzb);
                    ArrayList var14 = new ArrayList();
                    ArrayList var15 = new ArrayList();
                    ArrayList var16 = new ArrayList();
                    ArrayList var17 = new ArrayList();
                    ArrayList var18 = new ArrayList();
                    int var19 = var10.size();
                    var4 = 0;
                    boolean var42 = false;
                    boolean var46 = false;
                    boolean var49 = false;

                    boolean var51;
                    boolean var55;
                    for(var51 = false; var4 < var19; var42 = var55) {
                        SkuDetails var20 = (SkuDetails)var10.get(var4);
                        if (!var20.zze().isEmpty()) {
                            var14.add(var20.zze());
                        }

                        String var9 = var20.getOriginalJson();

                        try {
                            JSONObject var21 = new JSONObject(var9);
                            var9 = var21.optString("offer_id_token");
                        } catch (JSONException var24) {
                            var9 = "";
                        }

                        String var57 = var20.zzb();
                        int var22 = var20.zza();
                        String var56 = var20.zzd();
                        var15.add(var9);
                        var55 = var42 | TextUtils.isEmpty(var9) ^ true;
                        var16.add(var57);
                        var46 |= TextUtils.isEmpty(var57) ^ true;
                        var17.add(var22);
                        if (var22 != 0) {
                            var42 = true;
                        } else {
                            var42 = false;
                        }

                        var49 |= var42;
                        var51 |= TextUtils.isEmpty(var56) ^ true;
                        var18.add(var56);
                        ++var4;
                    }

                    if (!var14.isEmpty()) {
                        var13.putStringArrayList("skuDetailsTokens", var14);
                    }

                    if (var42) {
                        if (!this.zzq) {
                            var31 = zzak.zzi;
                            this.zzE(var31);
                            return var31;
                        }

                        var13.putStringArrayList("SKU_OFFER_ID_TOKEN_LIST", var15);
                    }

                    if (var46) {
                        var13.putStringArrayList("SKU_OFFER_ID_LIST", var16);
                    }

                    if (var49) {
                        var13.putIntegerArrayList("SKU_OFFER_TYPE_LIST", var17);
                    }

                    if (var51) {
                        var13.putStringArrayList("SKU_SERIALIZED_DOCID_LIST", var18);
                    }

                    boolean var43;
                    if (!TextUtils.isEmpty(var11.zzc())) {
                        var13.putString("skuPackageName", var11.zzc());
                        var43 = true;
                    } else {
                        var43 = false;
                    }

                    if (!TextUtils.isEmpty((CharSequence)null)) {
                        var13.putString("accountName", (String)null);
                    }

                    if (var10.size() > 1) {
                        ArrayList var50 = new ArrayList(var10.size() + -1);
                        ArrayList var52 = new ArrayList(var10.size() + -1);

                        for(var41 = 1; var41 < var10.size(); ++var41) {
                            var50.add(((SkuDetails)var10.get(var41)).getSku());
                            var52.add(((SkuDetails)var10.get(var41)).getType());
                        }

                        var13.putStringArrayList("additionalSkus", var50);
                        var13.putStringArrayList("additionalSkuTypes", var52);
                    }

                    if (!TextUtils.isEmpty(var1.getIntent().getStringExtra("PROXY_PACKAGE"))) {
                        var5 = var1.getIntent().getStringExtra("PROXY_PACKAGE");
                        var13.putString("proxyPackage", var5);

                        try {
                            var13.putString("proxyPackageVersion", this.zzf.getPackageManager().getPackageInfo(var5, 0).versionName);
                        } catch (NameNotFoundException var23) {
                            var13.putString("proxyPackageVersion", "package not found");
                        }
                    }

                    byte var47;
                    if (this.zzr && var43) {
                        var47 = 15;
                    } else if (this.zzn) {
                        var47 = 9;
                    } else if (var2.getVrPurchaseFlow()) {
                        var47 = 7;
                    } else {
                        var47 = 6;
                    }

                    zzx var53 = new zzx(this, var47, var11, var12, var2, var13);
                    var34 = this.zzH(var53, 5000L, (Runnable)null, this.zzc);
                } else {
                    zzm var35 = new zzm(this, var11, var12);
                    var34 = this.zzH(var35, 5000L, (Runnable)null, this.zzc);
                }

                String var36;
                int var37;
                StringBuilder var39;
                label134: {
                    label133: {
                        boolean var10001;
                        Bundle var38;
                        try {
                            var38 = (Bundle)var34.get(5000L, TimeUnit.MILLISECONDS);
                            var4 = com.google.android.gms.internal.play_billing.zza.zza(var38, "BillingClient");
                            var5 = com.google.android.gms.internal.play_billing.zza.zzh(var38, "BillingClient");
                        } catch (CancellationException | TimeoutException var29) {
                            var10001 = false;
                            break label134;
                        } catch (Exception var30) {
                            var10001 = false;
                            break label133;
                        }

                        if (var4 != 0) {
                            try {
                                StringBuilder var32 = new StringBuilder(52);
                                var32.append("Unable to buy item, Error response code: ");
                                var32.append(var4);
                                com.google.android.gms.internal.play_billing.zza.zzk("BillingClient", var32.toString());
                                Builder var33 = BillingResult.newBuilder();
                                var33.setResponseCode(var4);
                                var33.setDebugMessage(var5);
                                var31 = var33.build();
                                this.zzE(var31);
                                return var31;
                            } catch (CancellationException | TimeoutException var25) {
                                var10001 = false;
                                break label134;
                            } catch (Exception var26) {
                                var10001 = false;
                            }
                        } else {
                            label129: {
                                try {
                                    Intent var54 = new Intent(var1, ProxyBillingActivity.class);
                                    var54.putExtra("BUY_INTENT", (PendingIntent)var38.getParcelable("BUY_INTENT"));
                                    var1.startActivity(var54);
                                } catch (CancellationException | TimeoutException var27) {
                                    var10001 = false;
                                    break label134;
                                } catch (Exception var28) {
                                    var10001 = false;
                                    break label129;
                                }

                                return zzak.zzp;
                            }
                        }
                    }

                    var36 = String.valueOf(var3);
                    var37 = var36.length();
                    var39 = new StringBuilder(var37 + 69);
                    var39.append("Exception while launching billing flow: ; for sku: ");
                    var39.append(var3);
                    var39.append("; try to reconnect");
                    com.google.android.gms.internal.play_billing.zza.zzk("BillingClient", var39.toString());
                    var31 = zzak.zzq;
                    this.zzE(var31);
                    return var31;
                }

                var36 = String.valueOf(var3);
                var37 = var36.length();
                var39 = new StringBuilder(var37 + 68);
                var39.append("Time out while launching billing flow: ; for sku: ");
                var39.append(var3);
                var39.append("; try to reconnect");
                com.google.android.gms.internal.play_billing.zza.zzk("BillingClient", var39.toString());
                var31 = zzak.zzr;
                this.zzE(var31);
                return var31;
            }
        }
    }

    public final PurchasesResult queryPurchases(String var1) {
        PurchasesResult var6;
        if (!this.isReady()) {
            var6 = new PurchasesResult(zzak.zzq, (List)null);
            return var6;
        } else if (TextUtils.isEmpty(var1)) {
            com.google.android.gms.internal.play_billing.zza.zzk("BillingClient", "Please provide a valid SKU type.");
            var6 = new PurchasesResult(zzak.zzg, (List)null);
            return var6;
        } else {
            zzz var2 = new zzz(this, var1);
            Future var5 = this.zzH(var2, 5000L, (Runnable)null, this.zzc);

            try {
                var6 = (PurchasesResult)var5.get(5000L, TimeUnit.MILLISECONDS);
                return var6;
            } catch (CancellationException | TimeoutException var3) {
                var6 = new PurchasesResult(zzak.zzr, (List)null);
                return var6;
            } catch (Exception var4) {
                var6 = new PurchasesResult(zzak.zzl, (List)null);
                return var6;
            }
        }
    }

    private BillingClientImpl(String versionOverride) {
        this.zza = 0;
        Handler var2 = new Handler(Looper.getMainLooper());
        this.zzc = var2;
        this.zzk = 0;
        this.zzb = versionOverride;
    }

    private BillingClientImpl(Activity activity, boolean enablePendingPurchases, String versionOverride) {
        Context activity1 = activity.getApplicationContext();
        zzah var4 = new zzah();
        this(activity1, enablePendingPurchases, var4, versionOverride, (String)null);
    }

    @AnyThread
    BillingClientImpl(@Nullable String var1, boolean var2, Context var3, PurchasesUpdatedListener var4) {
        try {
            var1 = (String)Class.forName("com.android.billingclient.ktx.BuildConfig").getField("VERSION_NAME").get((Object)null);
        } catch (Exception var5) {
            var1 = "4.0.0";
        }

        this(var3, var2, var4, var1, (String)null);
    }

    @AnyThread
    private BillingClientImpl(Context var1, boolean var2, PurchasesUpdatedListener var3, String var4, String var5) {
        super();
        this.zza = 0;
        Handler var6 = new Handler(Looper.getMainLooper());
        this.zzc = var6;
        this.zzk = 0;
        this.zzb = var4;
        this.initialize(var1, var3, var2);
    }

    public final void acknowledgePurchase(AcknowledgePurchaseParams var1, AcknowledgePurchaseResponseListener var2) {
        if (!this.isReady()) {
            var2.onAcknowledgePurchaseResponse(zzak.zzq);
        } else if (TextUtils.isEmpty(var1.getPurchaseToken())) {
            com.google.android.gms.internal.play_billing.zza.zzk("BillingClient", "Please provide a valid purchase token.");
            var2.onAcknowledgePurchaseResponse(zzak.zzk);
        } else if (!this.zzn) {
            var2.onAcknowledgePurchaseResponse(zzak.zzb);
        } else {
            zzk var3 = new zzk(this, var1, var2);
            zzj var4 = new zzj(var2);
            if (this.zzH(var3, 30000L, var4, this.zzD()) == null) {
                var2.onAcknowledgePurchaseResponse(this.zzF());
            }
        }
    }

    public final void consumeAsync(ConsumeParams var1, ConsumeResponseListener var2) {
        if (!this.isReady()) {
            var2.onConsumeResponse(zzak.zzq, var1.getPurchaseToken());
        } else {
            zzl var3 = new zzl(this, var1, var2);
            zzr var4 = new zzr(var2, var1);
            if (this.zzH(var3, 30000L, var4, this.zzD()) == null) {
                var2.onConsumeResponse(this.zzF(), var1.getPurchaseToken());
            }
        }
    }

    public final void endConnection() {
        // $FF: Couldn't be decompiled
    }

    private void initialize(Context context, PurchasesUpdatedListener listener, boolean enablePendingPurchases) {
        this.zzf = context.getApplicationContext();
        zzh var4 = new zzh(this.zzf, listener);
        this.zzd = var4;
        this.zze = context;
        this.zzt = enablePendingPurchases;
    }

    private void launchPriceChangeConfirmationFlow(Activity activity, PriceChangeFlowParams priceChangeFlowParams, long futureHandle) {
        zzah var5 = new zzah(futureHandle);
        this.launchPriceChangeConfirmationFlow(activity, priceChangeFlowParams, var5);
    }

    public final void launchPriceChangeConfirmationFlow(Activity var1, PriceChangeFlowParams var2, PriceChangeConfirmationListener var3) {
        if (!this.isReady()) {
            this.zzI(zzak.zzq, var3);
        } else if (var2 != null && var2.getSkuDetails() != null) {
            String var16 = var2.getSkuDetails().getSku();
            if (var16 == null) {
                com.google.android.gms.internal.play_billing.zza.zzk("BillingClient", "Please fix the input params. priceChangeFlowParams must contain valid sku.");
                this.zzI(zzak.zzn, var3);
            } else if (!this.zzm) {
                com.google.android.gms.internal.play_billing.zza.zzk("BillingClient", "Current client doesn't support price change confirmation flow.");
                this.zzI(zzak.zzi, var3);
            } else {
                Bundle var4 = new Bundle();
                var4.putString("playBillingLibraryVersion", this.zzb);
                var4.putBoolean("subs_price_change", true);
                zzo var5 = new zzo(this, var16, var4);
                Future var17 = this.zzH(var5, 5000L, (Runnable)null, this.zzc);

                int var15;
                StringBuilder var18;
                label44: {
                    label43: {
                        boolean var10001;
                        int var19;
                        BillingResult var21;
                        try {
                            var4 = (Bundle)var17.get(5000L, TimeUnit.MILLISECONDS);
                            var19 = com.google.android.gms.internal.play_billing.zza.zza(var4, "BillingClient");
                            String var6 = com.google.android.gms.internal.play_billing.zza.zzh(var4, "BillingClient");
                            Builder var7 = BillingResult.newBuilder();
                            var7.setResponseCode(var19);
                            var7.setDebugMessage(var6);
                            var21 = var7.build();
                        } catch (CancellationException | TimeoutException var12) {
                            var10001 = false;
                            break label44;
                        } catch (Exception var13) {
                            var10001 = false;
                            break label43;
                        }

                        if (var19 != 0) {
                            try {
                                StringBuilder var14 = new StringBuilder(68);
                                var14.append("Unable to launch price change flow, error response code: ");
                                var14.append(var19);
                                com.google.android.gms.internal.play_billing.zza.zzk("BillingClient", var14.toString());
                                this.zzI(var21, var3);
                                return;
                            } catch (CancellationException | TimeoutException var8) {
                                var10001 = false;
                                break label44;
                            } catch (Exception var9) {
                                var10001 = false;
                            }
                        } else {
                            try {
                                zzy var20 = new zzy(this, this.zzc, var3);
                                Intent var22 = new Intent(var1, ProxyBillingActivity.class);
                                var22.putExtra("SUBS_MANAGEMENT_INTENT", (PendingIntent)var4.getParcelable("SUBS_MANAGEMENT_INTENT"));
                                var22.putExtra("result_receiver", var20);
                                var1.startActivity(var22);
                                return;
                            } catch (CancellationException | TimeoutException var10) {
                                var10001 = false;
                                break label44;
                            } catch (Exception var11) {
                                var10001 = false;
                            }
                        }
                    }

                    var15 = var16.length();
                    var18 = new StringBuilder(var15 + 78);
                    var18.append("Exception caught while launching Price Change Flow for sku: ");
                    var18.append(var16);
                    var18.append("; try to reconnect");
                    com.google.android.gms.internal.play_billing.zza.zzk("BillingClient", var18.toString());
                    this.zzI(zzak.zzq, var3);
                    return;
                }

                var15 = var16.length();
                var18 = new StringBuilder(var15 + 70);
                var18.append("Time out while launching Price Change Flow for sku: ");
                var18.append(var16);
                var18.append("; try to reconnect");
                com.google.android.gms.internal.play_billing.zza.zzk("BillingClient", var18.toString());
                this.zzI(zzak.zzr, var3);
            }
        } else {
            com.google.android.gms.internal.play_billing.zza.zzk("BillingClient", "Please fix the input params. priceChangeFlowParams must contain valid sku.");
            this.zzI(zzak.zzn, var3);
        }
    }

    public final void queryPurchaseHistoryAsync(String var1, PurchaseHistoryResponseListener var2) {
        if (!this.isReady()) {
            var2.onPurchaseHistoryResponse(zzak.zzq, (List)null);
        } else {
            zzab var3 = new zzab(this, var1, var2);
            zzt var4 = new zzt(var2);
            if (this.zzH(var3, 30000L, var4, this.zzD()) == null) {
                var2.onPurchaseHistoryResponse(this.zzF(), (List)null);
            }
        }
    }

    @zze
    public void queryPurchasesAsync(String skuType, PurchasesResponseListener listener) {
        if (!this.isReady()) {
            listener.onQueryPurchasesResponse(zzak.zzq, com.google.android.gms.internal.play_billing.zzp.zzg());
        } else if (TextUtils.isEmpty(skuType)) {
            com.google.android.gms.internal.play_billing.zza.zzk("BillingClient", "Please provide a valid SKU type.");
            listener.onQueryPurchasesResponse(zzak.zzg, com.google.android.gms.internal.play_billing.zzp.zzg());
        } else {
            zzaa var3 = new zzaa(this, skuType, listener);
            zzu skuType1 = new zzu(listener);
            if (this.zzH(var3, 30000L, skuType1, this.zzD()) == null) {
                listener.onQueryPurchasesResponse(this.zzF(), com.google.android.gms.internal.play_billing.zzp.zzg());
            }
        }
    }

    public final void querySkuDetailsAsync(SkuDetailsParams var1, SkuDetailsResponseListener var2) {
        if (!this.isReady()) {
            var2.onSkuDetailsResponse(zzak.zzq, (List)null);
        } else {
            String var3 = var1.getSkuType();
            List var7 = var1.getSkusList();
            if (TextUtils.isEmpty(var3)) {
                com.google.android.gms.internal.play_billing.zza.zzk("BillingClient", "Please fix the input params. SKU type can't be empty.");
                var2.onSkuDetailsResponse(zzak.zzg, (List)null);
            } else if (var7 == null) {
                com.google.android.gms.internal.play_billing.zza.zzk("BillingClient", "Please fix the input params. The list of SKUs can't be empty - set SKU list or SkuWithOffer list.");
                var2.onSkuDetailsResponse(zzak.zzf, (List)null);
            } else {
                ArrayList var4 = new ArrayList();
                Iterator var8 = var7.iterator();

                while(var8.hasNext()) {
                    Object var5 = var8.next();
                    String var11 = (String)var5;
                    zzap var6 = new zzap((zzao)null);
                    var6.zza(var11);
                    var4.add(var6.zzb());
                }

                com.android.billingclient.api.zzp var9 = new com.android.billingclient.api.zzp(this, var3, var4, (String)null, var2);
                zzv var10 = new zzv(var2);
                if (this.zzH(var9, 30000L, var10, this.zzD()) == null) {
                    var2.onSkuDetailsResponse(this.zzF(), (List)null);
                }
            }
        }
    }

    private void startConnection(long futureHandle) {
        zzah var3 = new zzah(futureHandle);
        if (this.isReady()) {
            com.google.android.gms.internal.play_billing.zza.zzj("BillingClient", "Service connection is valid. No need to re-initialize.");
            var3.onBillingSetupFinished(zzak.zzp);
        } else if (this.zza == 1) {
            com.google.android.gms.internal.play_billing.zza.zzk("BillingClient", "Client is already in the process of connecting to billing service.");
            var3.onBillingSetupFinished(zzak.zzd);
        } else if (this.zza == 3) {
            com.google.android.gms.internal.play_billing.zza.zzk("BillingClient", "Client was already closed and can't be reused. Please create another instance.");
            var3.onBillingSetupFinished(zzak.zzq);
        } else {
            this.zza = 1;
            this.zzd.zzd();
            com.google.android.gms.internal.play_billing.zza.zzj("BillingClient", "Starting in-app billing setup.");
            zzaf var4 = new zzaf(this, var3, (zzy)null);
            this.zzh = var4;
            Intent var8 = new Intent("com.android.vending.billing.InAppBillingService.BIND");
            var8.setPackage("com.android.vending");
            List var5 = this.zzf.getPackageManager().queryIntentServices(var8, 0);
            if (var5 != null && !var5.isEmpty()) {
                ResolveInfo var9 = (ResolveInfo)var5.get(0);
                if (var9.serviceInfo != null) {
                    String var6 = var9.serviceInfo.packageName;
                    String var10 = var9.serviceInfo.name;
                    if ("com.android.vending".equals(var6) && var10 != null) {
                        ComponentName var7 = new ComponentName(var6, var10);
                        Intent var11 = new Intent(var8);
                        var11.setComponent(var7);
                        var11.putExtra("playBillingLibraryVersion", this.zzb);
                        if (this.zzf.bindService(var11, this.zzh, 1)) {
                            com.google.android.gms.internal.play_billing.zza.zzj("BillingClient", "Service was bonded successfully.");
                            return;
                        }

                        com.google.android.gms.internal.play_billing.zza.zzk("BillingClient", "Connection to Billing service is blocked.");
                    } else {
                        com.google.android.gms.internal.play_billing.zza.zzk("BillingClient", "The device doesn't have valid Play Store.");
                    }
                }
            }

            this.zza = 0;
            com.google.android.gms.internal.play_billing.zza.zzj("BillingClient", "Billing service unavailable on device.");
            var3.onBillingSetupFinished(zzak.zzc);
        }
    }

    public final void startConnection(BillingClientStateListener var1) {
        if (this.isReady()) {
            com.google.android.gms.internal.play_billing.zza.zzj("BillingClient", "Service connection is valid. No need to re-initialize.");
            var1.onBillingSetupFinished(zzak.zzp);
        } else if (this.zza == 1) {
            com.google.android.gms.internal.play_billing.zza.zzk("BillingClient", "Client is already in the process of connecting to billing service.");
            var1.onBillingSetupFinished(zzak.zzd);
        } else if (this.zza == 3) {
            com.google.android.gms.internal.play_billing.zza.zzk("BillingClient", "Client was already closed and can't be reused. Please create another instance.");
            var1.onBillingSetupFinished(zzak.zzq);
        } else {
            this.zza = 1;
            this.zzd.zzd();
            com.google.android.gms.internal.play_billing.zza.zzj("BillingClient", "Starting in-app billing setup.");
            zzaf var2 = new zzaf(this, var1, (zzy)null);
            this.zzh = var2;
            Intent var6 = new Intent("com.android.vending.billing.InAppBillingService.BIND");
            var6.setPackage("com.android.vending");
            List var3 = this.zzf.getPackageManager().queryIntentServices(var6, 0);
            if (var3 != null && !var3.isEmpty()) {
                ResolveInfo var7 = (ResolveInfo)var3.get(0);
                if (var7.serviceInfo != null) {
                    String var4 = var7.serviceInfo.packageName;
                    String var8 = var7.serviceInfo.name;
                    if ("com.android.vending".equals(var4) && var8 != null) {
                        ComponentName var5 = new ComponentName(var4, var8);
                        Intent var9 = new Intent(var6);
                        var9.setComponent(var5);
                        var9.putExtra("playBillingLibraryVersion", this.zzb);
                        if (this.zzf.bindService(var9, this.zzh, 1)) {
                            com.google.android.gms.internal.play_billing.zza.zzj("BillingClient", "Service was bonded successfully.");
                            return;
                        }

                        com.google.android.gms.internal.play_billing.zza.zzk("BillingClient", "Connection to Billing service is blocked.");
                    } else {
                        com.google.android.gms.internal.play_billing.zza.zzk("BillingClient", "The device doesn't have valid Play Store.");
                    }
                }
            }

            this.zza = 0;
            com.google.android.gms.internal.play_billing.zza.zzj("BillingClient", "Billing service unavailable on device.");
            var1.onBillingSetupFinished(zzak.zzc);
        }
    }

    public final boolean isReady() {
        return this.zza == 2 && this.zzg != null && this.zzh != null;
    }

    private final Handler zzD() {
        Handler var1;
        if (Looper.myLooper() == null) {
            var1 = this.zzc;
        } else {
            var1 = new Handler(Looper.myLooper());
        }

        return var1;
    }

    private final BillingResult zzE(BillingResult var1) {
        if (Thread.interrupted()) {
            return var1;
        } else {
            Handler var2 = this.zzc;
            zzq var3 = new zzq(this, var1);
            var2.post(var3);
            return var1;
        }
    }

    private final BillingResult zzF() {
        BillingResult var1;
        if (this.zza != 0 && this.zza != 3) {
            var1 = zzak.zzl;
        } else {
            var1 = zzak.zzq;
        }

        return var1;
    }

    private final BillingResult zzG(String var1) {
        zzn var2 = new zzn(this, var1);
        Future var7 = this.zzH(var2, 5000L, (Runnable)null, this.zzD());

        label33: {
            boolean var10001;
            int var8;
            try {
                var8 = (Integer)var7.get(5000L, TimeUnit.MILLISECONDS);
            } catch (Exception var6) {
                var10001 = false;
                break label33;
            }

            BillingResult var9;
            if (var8 == 0) {
                try {
                    var9 = zzak.zzp;
                } catch (Exception var5) {
                    var10001 = false;
                    break label33;
                }
            } else {
                try {
                    var9 = zzak.zzi;
                } catch (Exception var4) {
                    var10001 = false;
                    break label33;
                }
            }

            try {
                return var9;
            } catch (Exception var3) {
                var10001 = false;
            }
        }

        com.google.android.gms.internal.play_billing.zza.zzk("BillingClient", "Exception while checking if billing is supported; try to reconnect");
        return zzak.zzq;
    }

    @Nullable
    private final <T> Future<T> zzH(Callable<T> var1, long var2, @Nullable Runnable var4, Handler var5) {
        long var6 = (long)((double)var2 * 0.95D);
        if (this.zzu == null) {
            int var8 = com.google.android.gms.internal.play_billing.zza.zza;
            zzac var9 = new zzac(this);
            this.zzu = Executors.newFixedThreadPool(var8, var9);
        }

        Future var12;
        try {
            var12 = this.zzu.submit(var1);
        } catch (Exception var10) {
            String var11 = String.valueOf(var10);
            String var13 = String.valueOf(var11);
            int var14 = var13.length();
            StringBuilder var16 = new StringBuilder(var14 + 28);
            var16.append("Async task throws exception ");
            var16.append(var11);
            com.google.android.gms.internal.play_billing.zza.zzk("BillingClient", var16.toString());
            return null;
        }

        zzw var15 = new zzw(var12, var4);
        var5.postDelayed(var15, var6);
        return var12;
    }

    private final void zzI(BillingResult var1, PriceChangeConfirmationListener var2) {
        if (!Thread.interrupted()) {
            Handler var4 = this.zzc;
            zzs var3 = new zzs(var2, var1);
            var4.post(var3);
        }
    }
}
