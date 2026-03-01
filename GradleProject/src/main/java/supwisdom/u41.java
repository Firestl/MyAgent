package supwisdom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class u41 implements t61, Comparable<u41> {
    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final int compareTo(u41 u41Var) {
        Class<?> cls = getClass();
        Class<?> cls2 = u41Var.getClass();
        return cls != cls2 ? cls.getName().compareTo(cls2.getName()) : b(u41Var);
    }

    public abstract int b(u41 u41Var);

    public abstract String c();
}
