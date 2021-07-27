package com.android.billingclient.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.os.ResultReceiver;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.internal.play_billing.zza;

public class ProxyBillingActivity extends Activity {
    @Nullable
    private ResultReceiver zza;
    private boolean zzb;

    public ProxyBillingActivity() {
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        StringBuilder resultCode1;
        if (requestCode == 100) {
            label33: {
                int var4 = com.google.android.gms.internal.play_billing.zza.zzg(data, "ProxyBillingActivity").getResponseCode();
                if (resultCode == -1) {
                    if (var4 == 0) {
                        requestCode = 0;
                        break label33;
                    }

                    requestCode = -1;
                } else {
                    requestCode = resultCode;
                }

                resultCode1 = new StringBuilder(85);
                resultCode1.append("Activity finished with resultCode ");
                resultCode1.append(requestCode);
                resultCode1.append(" and billing's responseCode: ");
                resultCode1.append(var4);
                com.google.android.gms.internal.play_billing.zza.zzk("ProxyBillingActivity", resultCode1.toString());
                requestCode = var4;
            }

            ResultReceiver var8 = this.zza;
            if (var8 != null) {
                Bundle resultCode2;
                if (data == null) {
                    resultCode2 = null;
                } else {
                    resultCode2 = data.getExtras();
                }

                var8.send(requestCode, resultCode2);
            } else {
                Intent requestCode1 = this.zza();
                if (data != null) {
                    if (data.getExtras() != null) {
                        requestCode1.putExtras(data.getExtras());
                    } else {
                        com.google.android.gms.internal.play_billing.zza.zzk("ProxyBillingActivity", "Got null bundle!");
                        requestCode1.putExtra("RESPONSE_CODE", 6);
                        requestCode1.putExtra("DEBUG_MESSAGE", "An internal error occurred.");
                    }
                }

                this.sendBroadcast(requestCode1);
            }
        } else {
            resultCode1 = new StringBuilder(69);
            resultCode1.append("Got onActivityResult with wrong requestCode: ");
            resultCode1.append(requestCode);
            resultCode1.append("; skipping...");
            com.google.android.gms.internal.play_billing.zza.zzk("ProxyBillingActivity", resultCode1.toString());
        }

        this.zzb = false;
        this.finish();
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            com.google.android.gms.internal.play_billing.zza.zzj("ProxyBillingActivity", "Launching Play Store billing flow");
            PendingIntent savedInstanceState1;
            if (this.getIntent().hasExtra("BUY_INTENT")) {
                savedInstanceState1 = (PendingIntent)this.getIntent().getParcelableExtra("BUY_INTENT");
            } else if (this.getIntent().hasExtra("SUBS_MANAGEMENT_INTENT")) {
                savedInstanceState1 = (PendingIntent)this.getIntent().getParcelableExtra("SUBS_MANAGEMENT_INTENT");
                this.zza = (ResultReceiver)this.getIntent().getParcelableExtra("result_receiver");
            } else {
                savedInstanceState1 = null;
            }

            try {
                this.zzb = true;
                IntentSender savedInstanceState5 = savedInstanceState1.getIntentSender();
                Intent var8 = new Intent();
                this.startIntentSenderForResult(savedInstanceState5, 100, var8, 0, 0, 0);
            } catch (SendIntentException var4) {
                String savedInstanceState2 = String.valueOf(var4);
                String var2 = String.valueOf(savedInstanceState2);
                int var7 = var2.length();
                StringBuilder var3 = new StringBuilder(var7 + 53);
                var3.append("Got exception while trying to start a purchase flow: ");
                var3.append(savedInstanceState2);
                com.google.android.gms.internal.play_billing.zza.zzk("ProxyBillingActivity", var3.toString());
                ResultReceiver savedInstanceState3 = this.zza;
                if (savedInstanceState3 != null) {
                    savedInstanceState3.send(6, (Bundle)null);
                } else {
                    Intent savedInstanceState4 = this.zza();
                    savedInstanceState4.putExtra("RESPONSE_CODE", 6);
                    savedInstanceState4.putExtra("DEBUG_MESSAGE", "An internal error occurred.");
                    this.sendBroadcast(savedInstanceState4);
                }

                this.zzb = false;
                this.finish();
            }
        } else {
            com.google.android.gms.internal.play_billing.zza.zzj("ProxyBillingActivity", "Launching Play Store billing flow from savedInstanceState");
            this.zzb = savedInstanceState.getBoolean("send_cancelled_broadcast_if_finished", false);
            if (savedInstanceState.containsKey("result_receiver")) {
                this.zza = (ResultReceiver)savedInstanceState.getParcelable("result_receiver");
            }
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.isFinishing()) {
            if (this.zzb) {
                Intent var1 = this.zza();
                var1.putExtra("RESPONSE_CODE", 1);
                var1.putExtra("DEBUG_MESSAGE", "Billing dialog closed.");
                this.sendBroadcast(var1);
            }
        }
    }

    protected void onSaveInstanceState(@NonNull Bundle outState) {
        ResultReceiver var2 = this.zza;
        if (var2 != null) {
            outState.putParcelable("result_receiver", var2);
        }

        outState.putBoolean("send_cancelled_broadcast_if_finished", this.zzb);
    }

    private final Intent zza() {
        Intent var1 = new Intent("com.android.vending.billing.PURCHASES_UPDATED");
        var1.setPackage(this.getApplicationContext().getPackageName());
        return var1;
    }
}
