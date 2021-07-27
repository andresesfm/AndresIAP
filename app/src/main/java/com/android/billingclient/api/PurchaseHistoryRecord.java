package com.android.billingclient.api;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PurchaseHistoryRecord {
    private final String zza;
    private final String zzb;
    private final JSONObject zzc;

    public int getQuantity() {
        return this.zzc.optInt("quantity", 1);
    }

    public int hashCode() {
        return this.zza.hashCode();
    }

    public long getPurchaseTime() {
        return this.zzc.optLong("purchaseTime");
    }

    @NonNull
    public String getDeveloperPayload() {
        return this.zzc.optString("developerPayload");
    }

    @NonNull
    public String getOriginalJson() {
        return this.zza;
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
        String var1 = "PurchaseHistoryRecord. Json: ";
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

    public PurchaseHistoryRecord(@NonNull String jsonPurchaseInfo, @NonNull String signature) throws JSONException {
        this.zza = jsonPurchaseInfo;
        this.zzb = signature;
        JSONObject jsonPurchaseInfo1 = new JSONObject(this.zza);
        this.zzc = jsonPurchaseInfo1;
    }

    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof PurchaseHistoryRecord)) {
            return false;
        } else {
            PurchaseHistoryRecord o1 = (PurchaseHistoryRecord)o;
            return TextUtils.equals(this.zza, o1.getOriginalJson()) && TextUtils.equals(this.zzb, o1.getSignature());
        }
    }
}
