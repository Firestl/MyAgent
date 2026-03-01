package com.sangfor.atrust.sdp_tunnel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.VpnService;
import android.os.Bundle;
import java.lang.ref.WeakReference;
import supwisdom.ky0;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class VpnPermissionActivity extends Activity {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f3876a = "-----VpnPermissionActivity";
    public static WeakReference<Activity> b = new WeakReference<>(null);

    public static void Sangfor_a(Context context) {
        ky0.a(f3876a, "VpnPermissionActivity start called");
        Activity activity = b.get();
        if (activity != null) {
            ky0.a(f3876a, "finish other activity");
            activity.finish();
        }
        Intent intent = new Intent(context, (Class<?>) VpnPermissionActivity.class);
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    public final void a() {
        Intent intentPrepare = VpnService.prepare(this);
        if (intentPrepare != null) {
            ky0.a(f3876a, "requestVPNPermission startActivityForResult");
            startActivityForResult(intentPrepare, 15);
        } else {
            ky0.a(f3876a, "requestVPNPermission onActivityResult");
            onActivityResult(15, -1, null);
        }
    }

    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 15) {
            finish();
            if (i2 == -1) {
                VpnServiceSession.d().b(true);
                ky0.a(f3876a, "requestVPNPermission success");
            } else {
                VpnServiceSession.d().b(false);
                ky0.a(f3876a, "requestVPNPermission failed");
            }
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        b = new WeakReference<>(this);
        requestWindowFeature(1);
        a();
    }
}
