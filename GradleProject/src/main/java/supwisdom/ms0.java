package supwisdom;

import com.google.android.gms.common.api.Status;
import com.google.firebase.appindexing.FirebaseAppIndexingException;
import com.google.firebase.appindexing.FirebaseAppIndexingInvalidArgumentException;
import com.google.firebase.appindexing.FirebaseAppIndexingTooManyArgumentsException;
import com.google.firebase.appindexing.zza;
import com.google.firebase.appindexing.zzb;

/* JADX INFO: compiled from: com.google.firebase:firebase-appindexing@@20.0.0 */
/* JADX INFO: loaded from: classes.dex */
public final class ms0 {
    public static FirebaseAppIndexingException a(Status status, String str) {
        pf0.a(status);
        String strF = status.f();
        if (strF != null && !strF.isEmpty()) {
            str = strF;
        }
        switch (status.e()) {
            case 17510:
                return new FirebaseAppIndexingInvalidArgumentException(str);
            case 17511:
                return new FirebaseAppIndexingTooManyArgumentsException(str);
            case 17512:
            default:
                return new FirebaseAppIndexingException(str);
            case 17513:
                return new zzb(str);
            case 17514:
                return new zza(str);
        }
    }
}
