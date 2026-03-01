package supwisdom;

import androidtranscoder.format.MediaFormatExtraConstants;
import com.google.android.gms.common.api.Scope;
import supwisdom.mc0;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class lk0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final mc0.g<fk0> f8296a = new mc0.g<>();
    public static final mc0.g<fk0> b = new mc0.g<>();
    public static final mc0.a<fk0, ek0> c = new kk0();
    public static final mc0.a<fk0, mk0> d = new nk0();

    static {
        new Scope(MediaFormatExtraConstants.KEY_PROFILE);
        new Scope("email");
        new mc0("SignIn.API", c, f8296a);
        new mc0("SignIn.INTERNAL_API", d, b);
    }
}
