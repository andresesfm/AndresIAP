package com.android.billingclient.api;

import androidx.annotation.Nullable;

public final class AccountIdentifiers {
    @Nullable
    private final String zza;
    @Nullable
    private final String zzb;

    @Nullable
    public String getObfuscatedAccountId() {
        return this.zza;
    }

    @Nullable
    public String getObfuscatedProfileId() {
        return this.zzb;
    }

    AccountIdentifiers(@Nullable String var1, @Nullable String var2) {
        this.zza = var1;
        this.zzb = var2;
    }
}
