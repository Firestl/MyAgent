package supwisdom;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.TypedValue;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.base.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiActivity;
import supwisdom.q7;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public class gc0 extends hc0 {
    public static final Object c = new Object();
    public static final gc0 d = new gc0();
    public String b;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
    @SuppressLint({"HandlerLeak"})
    public class a extends ji0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Context f7712a;

        public a(Context context) {
            super(Looper.myLooper() == null ? Looper.getMainLooper() : Looper.myLooper());
            this.f7712a = context.getApplicationContext();
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            int i = message.what;
            if (i != 1) {
                StringBuilder sb = new StringBuilder(50);
                sb.append("Don't know how to handle this message: ");
                sb.append(i);
                Log.w("GoogleApiAvailability", sb.toString());
                return;
            }
            int iA = gc0.this.a(this.f7712a);
            if (gc0.this.b(iA)) {
                gc0.this.b(this.f7712a, iA);
            }
        }
    }

    public static gc0 b() {
        return d;
    }

    @RecentlyNullable
    public Dialog a(@RecentlyNonNull Activity activity, int i, int i2, DialogInterface.OnCancelListener onCancelListener) {
        return a(activity, i, ag0.a(activity, a(activity, i, cn.com.chinatelecom.account.api.a.d.f1473a), i2), onCancelListener);
    }

    public boolean b(@RecentlyNonNull Activity activity, int i, int i2, DialogInterface.OnCancelListener onCancelListener) {
        Dialog dialogA = a(activity, i, i2, onCancelListener);
        if (dialogA == null) {
            return false;
        }
        a(activity, dialogA, "GooglePlayServicesErrorDialog", onCancelListener);
        return true;
    }

    @TargetApi(20)
    public final void a(Context context, int i, String str, PendingIntent pendingIntent) {
        int i2;
        Log.w("GoogleApiAvailability", String.format("GMS core API Availability. ConnectionResult=%s, tag=%s", Integer.valueOf(i), null), new IllegalArgumentException());
        if (i == 18) {
            b(context);
            return;
        }
        if (pendingIntent == null) {
            if (i == 6) {
                Log.w("GoogleApiAvailability", "Missing resolution for ConnectionResult.RESOLUTION_REQUIRED. Call GoogleApiAvailability#showErrorNotification(Context, ConnectionResult) instead.");
                return;
            }
            return;
        }
        String strB = bg0.b(context, i);
        String strD = bg0.d(context, i);
        Resources resources = context.getResources();
        Object systemService = context.getSystemService("notification");
        pf0.a(systemService);
        NotificationManager notificationManager = (NotificationManager) systemService;
        q7.d dVar = new q7.d(context);
        dVar.b(true);
        dVar.a(true);
        dVar.b(strB);
        q7.b bVar = new q7.b();
        bVar.a(strD);
        dVar.a(bVar);
        if (qh0.b(context)) {
            pf0.b(th0.c());
            dVar.b(context.getApplicationInfo().icon);
            dVar.a(2);
            if (qh0.c(context)) {
                dVar.a(R.drawable.common_full_open_on_phone, resources.getString(R.string.common_open_on_phone), pendingIntent);
            } else {
                dVar.a(pendingIntent);
            }
        } else {
            dVar.b(android.R.drawable.stat_sys_warning);
            dVar.c(resources.getString(R.string.common_google_play_services_notification_ticker));
            dVar.a(System.currentTimeMillis());
            dVar.a(pendingIntent);
            dVar.a((CharSequence) strD);
        }
        if (th0.f()) {
            pf0.b(th0.f());
            String strA = a();
            if (strA == null) {
                strA = "com.google.android.gms.availability";
                NotificationChannel notificationChannel = notificationManager.getNotificationChannel("com.google.android.gms.availability");
                String strA2 = bg0.a(context);
                if (notificationChannel == null) {
                    notificationManager.createNotificationChannel(new NotificationChannel("com.google.android.gms.availability", strA2, 4));
                } else if (!strA2.contentEquals(notificationChannel.getName())) {
                    notificationChannel.setName(strA2);
                    notificationManager.createNotificationChannel(notificationChannel);
                }
            }
            dVar.a(strA);
        }
        Notification notificationA = dVar.a();
        if (i == 1 || i == 2 || i == 3) {
            i2 = 10436;
            jc0.b.set(false);
        } else {
            i2 = 39789;
        }
        notificationManager.notify(i2, notificationA);
    }

    public void b(@RecentlyNonNull Context context, int i) {
        a(context, i, (String) null, a(context, i, 0, "n"));
    }

    @Override // supwisdom.hc0
    public final boolean b(int i) {
        return super.b(i);
    }

    public final void b(Context context) {
        new a(context).sendEmptyMessageDelayed(1, com.igexin.push.config.c.l);
    }

    public final boolean a(@RecentlyNonNull Context context, @RecentlyNonNull ConnectionResult connectionResult, int i) {
        PendingIntent pendingIntentA = a(context, connectionResult);
        if (pendingIntentA == null) {
            return false;
        }
        a(context, connectionResult.c(), (String) null, GoogleApiActivity.zaa(context, pendingIntentA, i));
        return true;
    }

    public final String a() {
        String str;
        synchronized (c) {
            str = this.b;
        }
        return str;
    }

    @Override // supwisdom.hc0
    public int a(@RecentlyNonNull Context context) {
        return super.a(context);
    }

    @Override // supwisdom.hc0
    public int a(@RecentlyNonNull Context context, int i) {
        return super.a(context, i);
    }

    @Override // supwisdom.hc0
    @RecentlyNullable
    public Intent a(Context context, int i, String str) {
        return super.a(context, i, str);
    }

    @Override // supwisdom.hc0
    @RecentlyNullable
    public PendingIntent a(@RecentlyNonNull Context context, int i, int i2) {
        return super.a(context, i, i2);
    }

    @RecentlyNullable
    public PendingIntent a(@RecentlyNonNull Context context, @RecentlyNonNull ConnectionResult connectionResult) {
        if (connectionResult.f()) {
            return connectionResult.e();
        }
        return a(context, connectionResult.c(), 0);
    }

    @Override // supwisdom.hc0
    public final String a(int i) {
        return super.a(i);
    }

    public static Dialog a(Context context, int i, ag0 ag0Var, DialogInterface.OnCancelListener onCancelListener) {
        if (i == 0) {
            return null;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.alertDialogTheme, typedValue, true);
        AlertDialog.Builder builder = "Theme.Dialog.Alert".equals(context.getResources().getResourceEntryName(typedValue.resourceId)) ? new AlertDialog.Builder(context, 5) : null;
        if (builder == null) {
            builder = new AlertDialog.Builder(context);
        }
        builder.setMessage(bg0.c(context, i));
        if (onCancelListener != null) {
            builder.setOnCancelListener(onCancelListener);
        }
        String strE = bg0.e(context, i);
        if (strE != null) {
            builder.setPositiveButton(strE, ag0Var);
        }
        String strA = bg0.a(context, i);
        if (strA != null) {
            builder.setTitle(strA);
        }
        Log.w("GoogleApiAvailability", String.format("Creating dialog for Google Play services availability issue. ConnectionResult=%s", Integer.valueOf(i)), new IllegalArgumentException());
        return builder.create();
    }

    public static void a(Activity activity, Dialog dialog, String str, DialogInterface.OnCancelListener onCancelListener) {
        if (activity instanceof FragmentActivity) {
            lc0.a(dialog, onCancelListener).a(((FragmentActivity) activity).getSupportFragmentManager(), str);
        } else {
            fc0.a(dialog, onCancelListener).show(activity.getFragmentManager(), str);
        }
    }
}
