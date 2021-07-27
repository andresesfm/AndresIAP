package com.android.billingclient.api;

import androidx.annotation.NonNull;

public final class BillingResult {
    private int zza;
    private String zzb;

    public int getResponseCode() {
        return this.zza;
    }

    @NonNull
    public static BillingResult.Builder newBuilder() {
        BillingResult.Builder var0 = new BillingResult.Builder((zzaj)null);
        return var0;
    }

    @NonNull
    public String getDebugMessage() {
        return this.zzb;
    }

    public BillingResult() {
    }

    public static class Builder {
        private int zza;
        private String zzb = "";

        @NonNull
        public BillingResult.Builder setDebugMessage(@NonNull String debugMessage) {
            this.zzb = debugMessage;
            return this;
        }

        @NonNull
        public BillingResult.Builder setResponseCode(int responseCode) {
            this.zza = responseCode;
            return this;
        }

        @NonNull
        public BillingResult build() {
            BillingResult var1 = new BillingResult();
            var1.zza = this.zza;
            var1.zzb = this.zzb;
            return var1;
        }

        private Builder() {
        }
    }
}
