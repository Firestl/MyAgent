package supwisdom;

/* JADX INFO: compiled from: LangUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ap1 {
    public static int a(int i, int i2) {
        return (i * 37) + i2;
    }

    public static int a(int i, Object obj) {
        return a(i, obj != null ? obj.hashCode() : 0);
    }

    public static boolean a(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    public static boolean a(Object[] objArr, Object[] objArr2) {
        if (objArr == null) {
            return objArr2 == null;
        }
        if (objArr2 == null || objArr.length != objArr2.length) {
            return false;
        }
        for (int i = 0; i < objArr.length; i++) {
            if (!a(objArr[i], objArr2[i])) {
                return false;
            }
        }
        return true;
    }
}
