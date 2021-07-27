package com.android.billingclient.api;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Purchase {
    private final String zza;
    private final String zzb;
    private final JSONObject zzc;

    public int getPurchaseState() {
        switch(this.zzc.optInt("purchaseState", 1)) {
            case 4:
                return 2;
            default:
                return 1;
        }
    }

    public int getQuantity() {
        return this.zzc.optInt("quantity", 1);
    }

    public int hashCode() {
        return this.zza.hashCode();
    }

    public long getPurchaseTime() {
        return this.zzc.optLong("purchaseTime");
    }

    @Nullable
    public AccountIdentifiers getAccountIdentifiers() {
        String var1 = this.zzc.optString("obfuscatedAccountId");
        String var3 = this.zzc.optString("obfuscatedProfileId");
        if (var1 == null && var3 == null) {
            return null;
        } else {
            AccountIdentifiers var2 = new AccountIdentifiers(var1, var3);
            return var2;
        }
    }

    @NonNull
    public String getDeveloperPayload() {
        return this.zzc.optString("developerPayload");
    }

    @NonNull
    public String getOrderId() {
        return this.zzc.optString("orderId");
    }

    @NonNull
    public String getOriginalJson() {
        return this.zza;
    }

    @NonNull
    public String getPackageName() {
        return this.zzc.optString("packageName");
    }

    @NonNull
    public String getPurchaseToken() {
        JSONObject var1 = this.zzc;
        return var1.optString("token", var1.optString("purchaseToken"));
    }

    @NonNull
    public String getSignature() {
        return this.zzb;
    }

    @NonNull
    public String toString() {
        String var2 = String.valueOf(this.zza);
        String var1 = "Purchase. Json: ";
        if (var2.length() != 0) {
            var2 = var1.concat(var2);
        } else {
            var2 = new String(var1);
        }

        return var2;
    }

    @NonNull
    public ArrayList<String> getSkus() {
        ArrayList var1 = new ArrayList();
        if (this.zzc.has("productIds")) {
            JSONArray var2 = this.zzc.optJSONArray("productIds");
            if (var2 != null) {
                for(int var3 = 0; var3 < var2.length(); ++var3) {
                    var1.add(var2.optString(var3));
                }
            }
        } else if (this.zzc.has("productId")) {
            var1.add(this.zzc.optString("productId"));
        }

        return var1;
    }

    public Purchase(@NonNull String jsonPurchaseInfo, @NonNull String signature) throws JSONException {
        this.zza = jsonPurchaseInfo;
        this.zzb = signature;
        JSONObject jsonPurchaseInfo1 = new JSONObject(this.zza);
        this.zzc = jsonPurchaseInfo1;
    }

    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Purchase)) {
            return false;
        } else {
            Purchase o1 = (Purchase)o;
            return TextUtils.equals(this.zza, o1.getOriginalJson()) && TextUtils.equals(this.zzb, o1.getSignature());
        }
    }

    public boolean isAcknowledged() {
        return this.zzc.optBoolean("acknowledged", true);
    }

    public boolean isAutoRenewing() {
        return this.zzc.optBoolean("autoRenewing");
    }

    public static class PurchasesResult {
        @Nullable
        private final List<Purchase> zza;
        private final BillingResult zzb;

        public int getResponseCode() {
            return this.getBillingResult().getResponseCode();
        }

        @NonNull
        public BillingResult getBillingResult() {
            return this.zzb;
        }

        @Nullable
        public List<Purchase> getPurchasesList() {
            return this.zza;
        }

        public PurchasesResult(@NonNull BillingResult mBillingResult, @Nullable List<Purchase> purchasesList) {
            this.zza = purchasesList;
            this.zzb = mBillingResult;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PurchaseState {
        int UNSPECIFIED_STATE = 0;
        int PURCHASED = 1;
        int PENDING = 2;
    }
}
