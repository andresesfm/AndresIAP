package com.android.billingclient.api;

import androidx.annotation.NonNull;

public final class AcknowledgePurchaseParams {
    private String zza;

    @NonNull
    public static AcknowledgePurchaseParams.Builder newBuilder() {
        AcknowledgePurchaseParams.Builder var0 = new AcknowledgePurchaseParams.Builder((zza)null);
        return var0;
    }

    @NonNull
    public String getPurchaseToken() {
        return this.zza;
    }

    private AcknowledgePurchaseParams() {
    }

    public static final class Builder {
        private String zza;

        @NonNull
        public AcknowledgePurchaseParams.Builder setPurchaseToken(@NonNull String purchaseToken) {
            this.zza = purchaseToken;
            return this;
        }

        @NonNull
        public AcknowledgePurchaseParams build() {
            if (this.zza == null) {
                IllegalArgumentException var2 = new IllegalArgumentException("Purchase token must be set");
                throw var2;
            } else {
                AcknowledgePurchaseParams var1 = new AcknowledgePurchaseParams((zza)null);
                var1.zza = this.zza;
                return var1;
            }
        }

        private Builder() {
        }
    }
}
