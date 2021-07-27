package com.android.billingclient.api;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

public class SkuDetailsParams {
    private String zza;
    private List<String> zzb;

    @NonNull
    public static SkuDetailsParams.Builder newBuilder() {
        SkuDetailsParams.Builder var0 = new SkuDetailsParams.Builder((zzan)null);
        return var0;
    }

    @NonNull
    public String getSkuType() {
        return this.zza;
    }

    @NonNull
    public List<String> getSkusList() {
        return this.zzb;
    }

    public SkuDetailsParams() {
    }

    public static class Builder {
        private String zza;
        private List<String> zzb;

        @NonNull
        public SkuDetailsParams.Builder setSkusList(@NonNull List<String> skusList) {
            ArrayList var2 = new ArrayList(skusList);
            this.zzb = var2;
            return this;
        }

        @NonNull
        public SkuDetailsParams.Builder setType(@NonNull String type) {
            this.zza = type;
            return this;
        }

        @NonNull
        public SkuDetailsParams build() {
            IllegalArgumentException var2;
            if (this.zza == null) {
                var2 = new IllegalArgumentException("SKU type must be set");
                throw var2;
            } else if (this.zzb == null) {
                var2 = new IllegalArgumentException("SKU list or SkuWithOffer list must be set");
                throw var2;
            } else {
                SkuDetailsParams var1 = new SkuDetailsParams();
                var1.zza = this.zza;
                var1.zzb = this.zzb;
                return var1;
            }
        }

        private Builder() {
        }
    }
}
