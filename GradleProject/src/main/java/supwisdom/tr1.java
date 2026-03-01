package supwisdom;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import net.grandcentrix.tray.core.TrayException;
import supwisdom.sr1;

/* JADX WARN: Unexpected interfaces in signature: [java.lang.Object<T>] */
/* JADX INFO: compiled from: Preferences.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class tr1<T, S extends sr1<T>> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f9312a = false;
    public S b;
    public int c;

    public tr1(S s, int i) {
        this.b = s;
        this.c = i;
        b();
    }

    public boolean a(String str) {
        return b(str) != null;
    }

    public T b(String str) {
        return (T) this.b.a(str);
    }

    public void b(int i) {
    }

    public boolean a(String str, String str2) {
        if (!b()) {
            return false;
        }
        vr1.b("put '" + str + "=\"" + str2 + "\"' into " + this);
        return a(str, (Object) str2);
    }

    public void b(int i, int i2) {
        throw new IllegalStateException("Can't upgrade database from version " + i + " to " + i2 + ", not implemented.");
    }

    public boolean b() {
        if (!this.f9312a) {
            a(this.c);
        }
        return this.f9312a;
    }

    public boolean a(String str, int i) {
        if (!b()) {
            return false;
        }
        vr1.b("put '" + str + ContainerUtils.KEY_VALUE_DELIMITER + i + "' into " + this);
        return a(str, Integer.valueOf(i));
    }

    public boolean a(String str, boolean z) {
        if (!b()) {
            return false;
        }
        vr1.b("put '" + str + ContainerUtils.KEY_VALUE_DELIMITER + z + "' into " + this);
        return a(str, Boolean.valueOf(z));
    }

    public S a() {
        return this.b;
    }

    public void a(int i, int i2) {
        throw new IllegalStateException("Can't downgrade " + this + " from version " + i + " to " + i2);
    }

    public synchronized void a(int i) {
        if (i >= 1) {
            try {
                int version = a().getVersion();
                if (version != i) {
                    if (version != 0) {
                        if (version > i) {
                            vr1.b("downgrading " + this + "from " + version + " to " + i);
                            a(version, i);
                            throw null;
                        }
                        vr1.b("upgrading " + this + " from " + version + " to " + i);
                        b(version, i);
                        throw null;
                    }
                    vr1.b("create " + this + " with initial version 0");
                    b(i);
                    a().a(i);
                }
                this.f9312a = true;
            } catch (TrayException e2) {
                e2.printStackTrace();
                vr1.b("could not change the version, retrying with the next interaction");
            }
        } else {
            throw new IllegalArgumentException("Version must be >= 1, was " + i);
        }
    }

    public final boolean a(String str, Object obj) {
        if (!TextUtils.isEmpty(str)) {
            return a().a(str, obj);
        }
        throw new IllegalArgumentException("Preference key value cannot be empty.");
    }
}
