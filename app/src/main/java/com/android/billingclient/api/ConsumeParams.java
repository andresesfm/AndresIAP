package com.android.billingclient.api;

import androidx.annotation.NonNull;

public final class ConsumeParams {
    private String zza;

    @NonNull
    public static ConsumeParams.Builder newBuilder() {
        ConsumeParams.Builder var0 = new ConsumeParams.Builder();
        return var0;
    }

    @NonNull
    public String getPurchaseToken() {
        return this.zza;
    }

    private ConsumeParams() {
    }

    public static final class Builder {
        private String zza;

        @NonNull
        public ConsumeParams.Builder setPurchaseToken(@NonNull String purchaseToken) {
            this.zza = purchaseToken;
            return this;
        }

        @NonNull
        public ConsumeParams build() {
            if (this.zza == null) {
                IllegalArgumentException var2 = new IllegalArgumentException("Purchase token must be set");
                throw var2;
            } else {
                ConsumeParams var1 = new ConsumeParams();
                var1.zza = this.zza;
                return var1;
            }
        }

        private Builder() {
        }
    }
}
