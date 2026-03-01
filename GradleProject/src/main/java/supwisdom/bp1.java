package supwisdom;

/* JADX INFO: compiled from: TextUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bp1 {
    public static boolean a(CharSequence charSequence) {
        if (charSequence == null) {
            return true;
        }
        for (int i = 0; i < charSequence.length(); i++) {
            if (!Character.isWhitespace(charSequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean b(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }
}
