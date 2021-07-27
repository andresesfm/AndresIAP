package com.android.billingclient.api;

import androidx.annotation.NonNull;

public class PriceChangeFlowParams {
    private SkuDetails zza;

    @NonNull
    public static PriceChangeFlowParams.Builder newBuilder() {
        PriceChangeFlowParams.Builder var0 = new PriceChangeFlowParams.Builder();
        return var0;
    }

    @NonNull
    public SkuDetails getSkuDetails() {
        return this.zza;
    }

    public PriceChangeFlowParams() {
    }

    public static class Builder {
        private SkuDetails zza;

        @NonNull
        public PriceChangeFlowParams.Builder setSkuDetails(@NonNull SkuDetails skuDetails) {
            this.zza = skuDetails;
            return this;
        }

        @NonNull
        public PriceChangeFlowParams build() {
            if (this.zza == null) {
                IllegalArgumentException var2 = new IllegalArgumentException("SkuDetails must be set");
                throw var2;
            } else {
                PriceChangeFlowParams var1 = new PriceChangeFlowParams();
                var1.zza = this.zza;
                return var1;
            }
        }

        public Builder() {
        }
    }
}
