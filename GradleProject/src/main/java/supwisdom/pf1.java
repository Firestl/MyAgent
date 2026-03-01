package supwisdom;

/* JADX INFO: compiled from: HttpMethod.java */
/* JADX INFO: loaded from: classes2.dex */
public final class pf1 {
    public static boolean a(String str) {
        return str.equals("POST") || str.equals("PATCH") || str.equals("PUT") || str.equals("DELETE");
    }

    public static boolean b(String str) {
        return c(str) || str.equals("DELETE");
    }

    public static boolean c(String str) {
        return str.equals("POST") || str.equals("PUT") || str.equals("PATCH");
    }
}
