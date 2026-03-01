package supwisdom;

/* JADX INFO: compiled from: SubtleUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class hs0 {
    public static boolean a() {
        try {
            Class.forName("android.app.Application", false, null);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
