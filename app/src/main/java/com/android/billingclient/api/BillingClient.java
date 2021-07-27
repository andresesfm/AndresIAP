package com.android.billingclient.api;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import com.android.billingclient.api.Purchase.PurchasesResult;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class BillingClient {
    @AnyThread
    public abstract int getConnectionState();

    @AnyThread
    @NonNull
    public static BillingClient.Builder newBuilder(@NonNull Context context) {
        return new Builder(context);
    }

    @AnyThread
    @NonNull
    public abstract BillingResult isFeatureSupported(@NonNull String var1);

    @UiThread
    @NonNull
    public abstract BillingResult launchBillingFlow(@NonNull Activity var1, @NonNull BillingFlowParams var2);

    /** @deprecated */
    @Deprecated
    @NonNull
    public abstract PurchasesResult queryPurchases(@NonNull String var1);

    public BillingClient() {
    }

    @AnyThread
    public abstract void acknowledgePurchase(@NonNull AcknowledgePurchaseParams var1, @NonNull AcknowledgePurchaseResponseListener var2);

    @AnyThread
    public abstract void consumeAsync(@NonNull ConsumeParams var1, @NonNull ConsumeResponseListener var2);

    @AnyThread
    public abstract void endConnection();

    @UiThread
    public abstract void launchPriceChangeConfirmationFlow(@NonNull Activity var1, @NonNull PriceChangeFlowParams var2, @NonNull PriceChangeConfirmationListener var3);

    @AnyThread
    public abstract void queryPurchaseHistoryAsync(@NonNull String var1, @NonNull PurchaseHistoryResponseListener var2);

    @AnyThread
    public abstract void queryPurchasesAsync(@NonNull String var1, @NonNull PurchasesResponseListener var2);

    @AnyThread
    public abstract void querySkuDetailsAsync(@NonNull SkuDetailsParams var1, @NonNull SkuDetailsResponseListener var2);

    @AnyThread
    public abstract void startConnection(@NonNull BillingClientStateListener var1);

    @AnyThread
    public abstract boolean isReady();

    @AnyThread
    public static final class Builder {
        private volatile String zza;
        private volatile boolean zzb;
        private final Context zzc;
        private volatile PurchasesUpdatedListener zzd;

        Builder(Context context ){
            zzc = context;
        }

        @NonNull
        public BillingClient.Builder enablePendingPurchases() {
            this.zzb = true;
            return this;
        }

        @NonNull
        public BillingClient.Builder setListener(@NonNull PurchasesUpdatedListener listener) {
            this.zzd = listener;
            return this;
        }

        @NonNull
        public BillingClient build() {
            IllegalArgumentException var2;
            if (this.zzc == null) {
                var2 = new IllegalArgumentException("Please provide a valid Context.");
                throw var2;
            } else if (this.zzd == null) {
                var2 = new IllegalArgumentException("Please provide a valid listener for purchases updates.");
                throw var2;
            } else if (!this.zzb) {
                var2 = new IllegalArgumentException("Support for pending purchases must be enabled. Enable this by calling 'enablePendingPurchases()' on BillingClientBuilder.");
                throw var2;
            } else {
                String var10000 = this.zza;
                BillingClientImpl var1 = new BillingClientImpl((String)null, this.zzb, this.zzc, this.zzd);
                return var1;
            }
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ConnectionState {
        int DISCONNECTED = 0;
        int CONNECTING = 1;
        int CONNECTED = 2;
        int CLOSED = 3;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface BillingResponseCode {
        int SERVICE_TIMEOUT = -3;
        int FEATURE_NOT_SUPPORTED = -2;
        int SERVICE_DISCONNECTED = -1;
        int OK = 0;
        int USER_CANCELED = 1;
        int SERVICE_UNAVAILABLE = 2;
        int BILLING_UNAVAILABLE = 3;
        int ITEM_UNAVAILABLE = 4;
        int DEVELOPER_ERROR = 5;
        int ERROR = 6;
        int ITEM_ALREADY_OWNED = 7;
        int ITEM_NOT_OWNED = 8;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FeatureType {
        @NonNull
        String SUBSCRIPTIONS = "subscriptions";
        @NonNull
        String SUBSCRIPTIONS_UPDATE = "subscriptionsUpdate";
        @NonNull
        String IN_APP_ITEMS_ON_VR = "inAppItemsOnVr";
        @NonNull
        String SUBSCRIPTIONS_ON_VR = "subscriptionsOnVr";
        @NonNull
        String PRICE_CHANGE_CONFIRMATION = "priceChangeConfirmation";
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SkuType {
        @NonNull
        String INAPP = "inapp";
        @NonNull
        String SUBS = "subs";
    }
}
