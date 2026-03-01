package supwisdom;

import android.graphics.Bitmap;

/* JADX INFO: compiled from: Cache.java */
/* JADX INFO: loaded from: classes2.dex */
public interface ug1 {

    /* JADX INFO: compiled from: Cache.java */
    public static class a implements ug1 {
        @Override // supwisdom.ug1
        public int a() {
            return 0;
        }

        @Override // supwisdom.ug1
        public Bitmap a(String str) {
            return null;
        }

        @Override // supwisdom.ug1
        public void a(String str, Bitmap bitmap) {
        }

        @Override // supwisdom.ug1
        public int size() {
            return 0;
        }
    }

    static {
        new a();
    }

    int a();

    Bitmap a(String str);

    void a(String str, Bitmap bitmap);

    int size();
}
