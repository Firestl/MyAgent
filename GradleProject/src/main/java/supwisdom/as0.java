package supwisdom;

import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import supwisdom.bs0;

/* JADX INFO: compiled from: EngineFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class as0<T_WRAPPER extends bs0<T_ENGINE>, T_ENGINE> {
    public static final Logger d = Logger.getLogger(as0.class.getName());

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final List<Provider> f6974e;
    public static final as0<bs0.a, Cipher> f;
    public static final as0<bs0.e, Mac> g;
    public static final as0<bs0.c, KeyFactory> h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public T_WRAPPER f6975a;
    public List<Provider> b = f6974e;
    public boolean c = true;

    static {
        if (hs0.a()) {
            f6974e = a("GmsCore_OpenSSL", "AndroidOpenSSL");
        } else {
            f6974e = new ArrayList();
        }
        f = new as0<>(new bs0.a());
        g = new as0<>(new bs0.e());
        new as0(new bs0.g());
        new as0(new bs0.f());
        new as0(new bs0.b());
        new as0(new bs0.d());
        h = new as0<>(new bs0.c());
    }

    public as0(T_WRAPPER t_wrapper) {
        this.f6975a = t_wrapper;
    }

    public static List<Provider> a(String... strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            Provider provider = Security.getProvider(str);
            if (provider != null) {
                arrayList.add(provider);
            } else {
                d.info(String.format("Provider %s not available", str));
            }
        }
        return arrayList;
    }

    public T_ENGINE a(String str) throws GeneralSecurityException {
        Iterator<Provider> it = this.b.iterator();
        Exception exc = null;
        while (it.hasNext()) {
            try {
                return (T_ENGINE) this.f6975a.a(str, it.next());
            } catch (Exception e2) {
                if (exc == null) {
                    exc = e2;
                }
            }
        }
        if (this.c) {
            return (T_ENGINE) this.f6975a.a(str, null);
        }
        throw new GeneralSecurityException("No good Provider found.", exc);
    }
}
