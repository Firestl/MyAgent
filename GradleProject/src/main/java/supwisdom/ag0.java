package supwisdom;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public abstract class ag0 implements DialogInterface.OnClickListener {
    public static ag0 a(Activity activity, Intent intent, int i) {
        return new cg0(intent, activity, i);
    }

    public abstract void a();

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            a();
        } catch (ActivityNotFoundException e2) {
            Log.e("DialogRedirect", Build.FINGERPRINT.contains("generic") ? "Failed to start resolution intent.".concat(" This may occur when resolving Google Play services connection issues on emulators with Google APIs but not Google Play Store.") : "Failed to start resolution intent.", e2);
        } finally {
            dialogInterface.dismiss();
        }
    }
}
