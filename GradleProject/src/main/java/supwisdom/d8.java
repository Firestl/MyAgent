package supwisdom;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ShortcutInfoCompatSaver.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class d8<T> {

    /* JADX INFO: compiled from: ShortcutInfoCompatSaver.java */
    public static class a extends d8<Void> {
        @Override // supwisdom.d8
        public /* bridge */ /* synthetic */ Void a(List list) {
            return a2((List<c8>) list);
        }

        @Override // supwisdom.d8
        /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
        public Void a2(List<c8> list) {
            return null;
        }

        @Override // supwisdom.d8
        public Void b() {
            return null;
        }

        @Override // supwisdom.d8
        /* JADX INFO: renamed from: b, reason: avoid collision after fix types in other method */
        public Void b2(List<String> list) {
            return null;
        }

        @Override // supwisdom.d8
        public /* bridge */ /* synthetic */ Void b(List list) {
            return b2((List<String>) list);
        }
    }

    public abstract T a(List<c8> list);

    public List<c8> a() throws Exception {
        return new ArrayList();
    }

    public abstract T b();

    public abstract T b(List<String> list);
}
