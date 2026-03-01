package supwisdom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import supwisdom.gq0;

/* JADX INFO: compiled from: ListFieldSchema.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class lq0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final lq0 f8309a;
    public static final lq0 b;

    static {
        f8309a = new b();
        b = new c();
    }

    public static lq0 a() {
        return f8309a;
    }

    public static lq0 b() {
        return b;
    }

    public abstract void a(Object obj, long j);

    public abstract <L> void a(Object obj, Object obj2, long j);

    public abstract <L> List<L> b(Object obj, long j);

    /* JADX INFO: compiled from: ListFieldSchema.java */
    public static final class c extends lq0 {
        public c() {
            super();
        }

        public static <E> gq0.i<E> c(Object obj, long j) {
            return (gq0.i) vr0.n(obj, j);
        }

        @Override // supwisdom.lq0
        public void a(Object obj, long j) {
            c(obj, j).c();
        }

        @Override // supwisdom.lq0
        public <L> List<L> b(Object obj, long j) {
            gq0.i iVarC = c(obj, j);
            if (iVarC.f()) {
                return iVarC;
            }
            int size = iVarC.size();
            gq0.i iVarA2 = iVarC.a2(size == 0 ? 10 : size * 2);
            vr0.a(obj, j, iVarA2);
            return iVarA2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1 */
        /* JADX WARN: Type inference failed for: r0v2, types: [java.util.List] */
        /* JADX WARN: Type inference failed for: r0v4 */
        /* JADX WARN: Type inference failed for: r0v5 */
        /* JADX WARN: Type inference failed for: r0v6 */
        /* JADX WARN: Type inference failed for: r0v7 */
        /* JADX WARN: Type inference failed for: r0v8 */
        /* JADX WARN: Type inference failed for: r6v1, types: [java.util.Collection, java.util.List, supwisdom.gq0$i] */
        /* JADX WARN: Type inference failed for: r6v2, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r6v3 */
        @Override // supwisdom.lq0
        public <E> void a(Object obj, Object obj2, long j) {
            gq0.i iVarC = c(obj, j);
            ?? C = c(obj2, j);
            int size = iVarC.size();
            int size2 = C.size();
            ?? r0 = iVarC;
            r0 = iVarC;
            if (size > 0 && size2 > 0) {
                boolean zF = iVarC.f();
                ?? A2 = iVarC;
                if (!zF) {
                    A2 = iVarC.a2(size2 + size);
                }
                A2.addAll(C);
                r0 = A2;
            }
            if (size > 0) {
                C = r0;
            }
            vr0.a(obj, j, (Object) C);
        }
    }

    public lq0() {
    }

    /* JADX INFO: compiled from: ListFieldSchema.java */
    public static final class b extends lq0 {
        public static final Class<?> c = Collections.unmodifiableList(Collections.emptyList()).getClass();

        public b() {
            super();
        }

        public static <E> List<E> c(Object obj, long j) {
            return (List) vr0.n(obj, j);
        }

        @Override // supwisdom.lq0
        public void a(Object obj, long j) {
            Object objUnmodifiableList;
            List list = (List) vr0.n(obj, j);
            if (list instanceof kq0) {
                objUnmodifiableList = ((kq0) list).e();
            } else {
                if (c.isAssignableFrom(list.getClass())) {
                    return;
                }
                if ((list instanceof er0) && (list instanceof gq0.i)) {
                    gq0.i iVar = (gq0.i) list;
                    if (iVar.f()) {
                        iVar.c();
                        return;
                    }
                    return;
                }
                objUnmodifiableList = Collections.unmodifiableList(list);
            }
            vr0.a(obj, j, objUnmodifiableList);
        }

        @Override // supwisdom.lq0
        public <L> List<L> b(Object obj, long j) {
            return a(obj, j, 10);
        }

        public static <L> List<L> a(Object obj, long j, int i) {
            Object obj2;
            List<L> arrayList;
            List<L> listC = c(obj, j);
            if (listC.isEmpty()) {
                if (listC instanceof kq0) {
                    arrayList = new jq0(i);
                } else if ((listC instanceof er0) && (listC instanceof gq0.i)) {
                    arrayList = ((gq0.i) listC).a2(i);
                } else {
                    arrayList = new ArrayList<>(i);
                }
                vr0.a(obj, j, arrayList);
                return arrayList;
            }
            if (c.isAssignableFrom(listC.getClass())) {
                ArrayList arrayList2 = new ArrayList(listC.size() + i);
                arrayList2.addAll(listC);
                vr0.a(obj, j, arrayList2);
                obj2 = arrayList2;
            } else if (listC instanceof ur0) {
                jq0 jq0Var = new jq0(listC.size() + i);
                jq0Var.addAll((ur0) listC);
                vr0.a(obj, j, jq0Var);
                obj2 = jq0Var;
            } else {
                if (!(listC instanceof er0) || !(listC instanceof gq0.i)) {
                    return listC;
                }
                gq0.i iVar = (gq0.i) listC;
                if (iVar.f()) {
                    return listC;
                }
                gq0.i iVarA2 = iVar.a2(listC.size() + i);
                vr0.a(obj, j, iVarA2);
                return iVarA2;
            }
            return (List<L>) obj2;
        }

        @Override // supwisdom.lq0
        public <E> void a(Object obj, Object obj2, long j) {
            List listC = c(obj2, j);
            List listA = a(obj, j, listC.size());
            int size = listA.size();
            int size2 = listC.size();
            if (size > 0 && size2 > 0) {
                listA.addAll(listC);
            }
            if (size > 0) {
                listC = listA;
            }
            vr0.a(obj, j, listC);
        }
    }
}
