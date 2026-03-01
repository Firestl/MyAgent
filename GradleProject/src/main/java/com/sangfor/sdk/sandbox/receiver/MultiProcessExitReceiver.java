package com.sangfor.sdk.sandbox.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class MultiProcessExitReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.sangfor.vpn.ACTION_EXIT_PROCESS")) {
            System.exit(0);
        }
    }
}
