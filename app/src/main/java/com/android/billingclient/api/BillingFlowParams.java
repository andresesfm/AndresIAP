package com.android.billingclient.api;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

public class BillingFlowParams {
    @NonNull
    public static final String EXTRA_PARAM_KEY_ACCOUNT_ID = "accountId";
    @NonNull
    public static final String EXTRA_PARAM_KEY_REPLACE_SKUS_PRORATION_MODE = "prorationMode";
    @NonNull
    public static final String EXTRA_PARAM_KEY_VR = "vr";
    @NonNull
    public static final String EXTRA_PARAM_KEY_OLD_SKUS = "skusToReplace";
    @NonNull
    public static final String EXTRA_PARAM_KEY_OLD_SKU_PURCHASE_TOKEN = "oldSkuPurchaseToken";
    private boolean zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private int zze = 0;
    private ArrayList<SkuDetails> zzf;
    private boolean zzg;

    @NonNull
    public static BillingFlowParams.Builder newBuilder() {
        BillingFlowParams.Builder var0 = new BillingFlowParams.Builder((zzai)null);
        return var0;
    }

    private BillingFlowParams() {
    }

    public boolean getVrPurchaseFlow() {
        return this.zzg;
    }

    public final int zzb() {
        return this.zze;
    }

    @Nullable
    public final String zzf() {
        return this.zzb;
    }

    @Nullable
    public final String zzg() {
        return this.zzd;
    }

    @Nullable
    public final String zzh() {
        return this.zzc;
    }

    @NonNull
    public final ArrayList<SkuDetails> zzj() {
        ArrayList var1 = new ArrayList();
        var1.addAll(this.zzf);
        return var1;
    }

    final boolean zzm() {
        return this.zzg || this.zzb != null || this.zzd != null || this.zze != 0 || this.zza;
    }

    @zzc
    public static class SubscriptionUpdateParams {
        private String zza;
        private int zzb = 0;

        @zzc
        int getReplaceSkusProrationMode() {
            return this.zzb;
        }

        @zzc
        @NonNull
        public static BillingFlowParams.SubscriptionUpdateParams.Builder newBuilder() {
            BillingFlowParams.SubscriptionUpdateParams.Builder var0 = new BillingFlowParams.SubscriptionUpdateParams.Builder((zzai)null);
            return var0;
        }

        @zzc
        String getOldSkuPurchaseToken() {
            return this.zza;
        }

        private SubscriptionUpdateParams() {
        }

        @zzc
        public static class Builder {
            private String zza;
            private int zzb = 0;

            @zzc
            @NonNull
            public BillingFlowParams.SubscriptionUpdateParams.Builder setOldSkuPurchaseToken(@NonNull String purchaseToken) {
                this.zza = purchaseToken;
                return this;
            }

            @zzc
            @NonNull
            public BillingFlowParams.SubscriptionUpdateParams.Builder setReplaceSkusProrationMode(int replaceSkusProrationMode) {
                this.zzb = replaceSkusProrationMode;
                return this;
            }

            @zzc
            @NonNull
            public BillingFlowParams.SubscriptionUpdateParams build() {
                if (TextUtils.isEmpty(this.zza) && TextUtils.isEmpty((CharSequence)null)) {
                    IllegalArgumentException var2 = new IllegalArgumentException("Old SKU purchase token/id must be provided.");
                    throw var2;
                } else {
                    BillingFlowParams.SubscriptionUpdateParams var1 = new BillingFlowParams.SubscriptionUpdateParams((zzai)null);
                    var1.zza = this.zza;
                    var1.zzb = this.zzb;
                    return var1;
                }
            }

            private Builder() {
            }
        }
    }

    public static class Builder {
        private String zza;
        private String zzb;
        private String zzc;
        private int zzd = 0;
        private ArrayList<SkuDetails> zze;
        private boolean zzf;

        @NonNull
        public BillingFlowParams.Builder setObfuscatedAccountId(@NonNull String obfuscatedAccountid) {
            this.zza = obfuscatedAccountid;
            return this;
        }

        @NonNull
        public BillingFlowParams.Builder setObfuscatedProfileId(@NonNull String obfuscatedProfileId) {
            this.zzc = obfuscatedProfileId;
            return this;
        }

        @NonNull
        public BillingFlowParams.Builder setSkuDetails(@NonNull SkuDetails skuDetails) {
            ArrayList var2 = new ArrayList();
            var2.add(skuDetails);
            this.zze = var2;
            return this;
        }

        @zzc
        @NonNull
        public BillingFlowParams.Builder setSubscriptionUpdateParams(@NonNull BillingFlowParams.SubscriptionUpdateParams subscriptionUpdateParams) {
            this.zzb = subscriptionUpdateParams.getOldSkuPurchaseToken();
            this.zzd = subscriptionUpdateParams.getReplaceSkusProrationMode();
            return this;
        }

        @NonNull
        public BillingFlowParams.Builder setVrPurchaseFlow(boolean isVrPurchaseFlow) {
            this.zzf = isVrPurchaseFlow;
            return this;
        }

        @NonNull
        public BillingFlowParams build() {
            ArrayList var1 = this.zze;
            IllegalArgumentException var7;
            if (var1 != null && !var1.isEmpty()) {
                ArrayList var2 = this.zze;
                int var3 = var2.size();

                int var4;
                int var8;
                for(var8 = 0; var8 < var3; var8 = var4) {
                    var4 = var8 + 1;
                    if ((SkuDetails)var2.get(var8) == null) {
                        var7 = new IllegalArgumentException("SKU cannot be null.");
                        throw var7;
                    }
                }

                if (this.zze.size() > 1) {
                    SkuDetails var9 = (SkuDetails)this.zze.get(0);
                    String var11 = var9.getType();
                    ArrayList var13 = this.zze;
                    int var5 = var13.size();

                    SkuDetails var6;
                    for(var8 = 0; var8 < var5; ++var8) {
                        var6 = (SkuDetails)var13.get(var8);
                        if (!var11.equals("play_pass_subs") && !var6.getType().equals("play_pass_subs") && !var11.equals(var6.getType())) {
                            var7 = new IllegalArgumentException("SKUs should have the same type.");
                            throw var7;
                        }
                    }

                    String var10 = var9.zzc();
                    var13 = this.zze;
                    var5 = var13.size();

                    for(var8 = 0; var8 < var5; ++var8) {
                        var6 = (SkuDetails)var13.get(var8);
                        if (!var11.equals("play_pass_subs") && !var6.getType().equals("play_pass_subs") && !var10.equals(var6.zzc())) {
                            var7 = new IllegalArgumentException("All SKUs must have the same package name.");
                            throw var7;
                        }
                    }
                }

                BillingFlowParams var12 = new BillingFlowParams((zzai)null);
                var12.zza = ((SkuDetails)this.zze.get(0)).zzc().isEmpty() ^ true;
                var12.zzb = this.zza;
                var12.zzd = this.zzc;
                var12.zzc = this.zzb;
                var12.zze = this.zzd;
                var12.zzf = this.zze;
                var12.zzg = this.zzf;
                return var12;
            } else {
                var7 = new IllegalArgumentException("SkuDetails must be provided.");
                throw var7;
            }
        }

        private Builder() {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ProrationMode {
        int UNKNOWN_SUBSCRIPTION_UPGRADE_DOWNGRADE_POLICY = 0;
        int IMMEDIATE_WITH_TIME_PRORATION = 1;
        int IMMEDIATE_AND_CHARGE_PRORATED_PRICE = 2;
        int IMMEDIATE_WITHOUT_PRORATION = 3;
        int DEFERRED = 4;
        @zzb
        int IMMEDIATE_AND_CHARGE_FULL_PRICE = 5;
    }
}
