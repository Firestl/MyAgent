package supwisdom;

import com.taobao.weex.el.parse.Operators;
import net.grandcentrix.tray.core.ItemNotFoundException;
import net.grandcentrix.tray.core.TrayStorage;
import net.grandcentrix.tray.core.WrongTypeException;

/* JADX INFO: compiled from: AbstractTrayPreference.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class rr1<T extends TrayStorage> extends tr1<ur1, T> {
    public rr1(T t, int i) {
        super(t, i);
    }

    public final void a(String str, Class<?> cls, String str2) throws WrongTypeException {
        if (str != null) {
            return;
        }
        throw new WrongTypeException("The value for key <" + str2 + "> is null. You obviously saved this value as String and try to access it with type " + cls.getSimpleName() + " which cannot be null.  Always use getString(key, defaultValue) when accessing data you saved with put(String).");
    }

    public boolean b(String str, boolean z) {
        try {
            return c(str);
        } catch (ItemNotFoundException unused) {
            return z;
        }
    }

    public boolean c(String str) throws ItemNotFoundException {
        return Boolean.parseBoolean(e(str));
    }

    public int d(String str) throws ItemNotFoundException {
        String strE = e(str);
        a(strE, Integer.class, str);
        try {
            return Integer.parseInt(strE);
        } catch (NumberFormatException e2) {
            throw new WrongTypeException(e2);
        }
    }

    public String e(String str) throws ItemNotFoundException {
        ur1 ur1VarB = b(str);
        if (ur1VarB != null) {
            return ur1VarB.a();
        }
        throw new ItemNotFoundException("Value for Key <%s> not found", str);
    }

    public String toString() {
        return getClass().getSimpleName() + "(@" + Integer.toHexString(hashCode()) + "){name=" + c() + Operators.BLOCK_END_STR;
    }

    public int b(String str, int i) {
        try {
            return d(str);
        } catch (ItemNotFoundException unused) {
            return i;
        }
    }

    public String b(String str, String str2) {
        try {
            return e(str);
        } catch (ItemNotFoundException unused) {
            return str2;
        }
    }

    public String c() {
        return a().a();
    }
}
