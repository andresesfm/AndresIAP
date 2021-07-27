package com.android.billingclient.api;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class SkuDetails {
    private final String zza;
    private final JSONObject zzb;

    public int getIntroductoryPriceCycles() {
        return this.zzb.optInt("introductoryPriceCycles");
    }

    public int hashCode() {
        return this.zza.hashCode();
    }

    public long getIntroductoryPriceAmountMicros() {
        return this.zzb.optLong("introductoryPriceAmountMicros");
    }

    public long getOriginalPriceAmountMicros() {
        return this.zzb.has("original_price_micros") ? this.zzb.optLong("original_price_micros") : this.getPriceAmountMicros();
    }

    public long getPriceAmountMicros() {
        return this.zzb.optLong("price_amount_micros");
    }

    @NonNull
    public String getDescription() {
        return this.zzb.optString("description");
    }

    @NonNull
    public String getFreeTrialPeriod() {
        return this.zzb.optString("freeTrialPeriod");
    }

    @NonNull
    public String getIconUrl() {
        return this.zzb.optString("iconUrl");
    }

    @NonNull
    public String getIntroductoryPrice() {
        return this.zzb.optString("introductoryPrice");
    }

    @NonNull
    public String getIntroductoryPricePeriod() {
        return this.zzb.optString("introductoryPricePeriod");
    }

    @NonNull
    public String getOriginalJson() {
        return this.zza;
    }

    @NonNull
    public String getOriginalPrice() {
        return this.zzb.has("original_price") ? this.zzb.optString("original_price") : this.getPrice();
    }

    @NonNull
    public String getPrice() {
        return this.zzb.optString("price");
    }

    @NonNull
    public String getPriceCurrencyCode() {
        return this.zzb.optString("price_currency_code");
    }

    @NonNull
    public String getSku() {
        return this.zzb.optString("productId");
    }

    @NonNull
    public String getSubscriptionPeriod() {
        return this.zzb.optString("subscriptionPeriod");
    }

    @NonNull
    public String getTitle() {
        return this.zzb.optString("title");
    }

    @NonNull
    public String getType() {
        return this.zzb.optString("type");
    }

    @NonNull
    public String toString() {
        String var2 = String.valueOf(this.zza);
        String var1 = "SkuDetails: ";
        if (var2.length() != 0) {
            var2 = var1.concat(var2);
        } else {
            var2 = new String(var1);
        }

        return var2;
    }

    public SkuDetails(@NonNull String jsonSkuDetails) throws JSONException {
        this.zza = jsonSkuDetails;
        JSONObject jsonSkuDetails1 = new JSONObject(this.zza);
        this.zzb = jsonSkuDetails1;
        IllegalArgumentException var2;
        if (TextUtils.isEmpty(this.zzb.optString("productId"))) {
            var2 = new IllegalArgumentException("SKU cannot be empty.");
            throw var2;
        } else if (TextUtils.isEmpty(this.zzb.optString("type"))) {
            var2 = new IllegalArgumentException("SkuType cannot be empty.");
            throw var2;
        }
    }

    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof SkuDetails)) {
            return false;
        } else {
            SkuDetails o1 = (SkuDetails)o;
            return TextUtils.equals(this.zza, o1.zza);
        }
    }

    public int zza() {
        return this.zzb.optInt("offer_type");
    }

    @NonNull
    public String zzb() {
        return this.zzb.optString("offer_id");
    }

    @NonNull
    public final String zzc() {
        return this.zzb.optString("packageName");
    }

    @NonNull
    public String zzd() {
        return this.zzb.optString("serializedDocid");
    }

    final String zze() {
        return this.zzb.optString("skuDetailsToken");
    }
}
