package supwisdom;

/* JADX INFO: compiled from: UtilityFunctions.java */
/* JADX INFO: loaded from: classes3.dex */
public final class gy1 {

    /* JADX INFO: compiled from: UtilityFunctions.java */
    public enum a implements fx1<Object, Boolean> {
        INSTANCE;

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // supwisdom.fx1
        public Boolean call(Object obj) {
            return true;
        }
    }

    public static <T> fx1<? super T, Boolean> a() {
        return a.INSTANCE;
    }
}
