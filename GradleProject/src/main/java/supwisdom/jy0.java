package supwisdom;

import com.qiniu.android.dns.NetworkInfo;
import java.util.Hashtable;
import java.util.LinkedList;

/* JADX INFO: compiled from: Hosts.java */
/* JADX INFO: loaded from: classes2.dex */
public final class jy0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Hashtable<String, LinkedList<a>> f8097a = new Hashtable<>();

    /* JADX INFO: compiled from: Hosts.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f8098a;
        public final int b;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.f8098a.equals(aVar.f8098a) && this.b == aVar.b;
        }
    }

    public synchronized String[] a(ey0 ey0Var, NetworkInfo networkInfo) {
        LinkedList<a> linkedList = this.f8097a.get(ey0Var.f7544a);
        if (linkedList != null && !linkedList.isEmpty()) {
            if (linkedList.size() > 1) {
                a aVar = linkedList.get(0);
                linkedList.remove(0);
                linkedList.add(aVar);
            }
            return a(a(linkedList, networkInfo));
        }
        return null;
    }

    public final LinkedList<a> a(LinkedList<a> linkedList, NetworkInfo networkInfo) {
        LinkedList<a> linkedList2 = new LinkedList<>();
        LinkedList<a> linkedList3 = new LinkedList<>();
        for (a aVar : linkedList) {
            if (aVar.b == 0) {
                linkedList2.add(aVar);
            }
            int i = networkInfo.f3866a;
            if (i != 0 && aVar.b == i) {
                linkedList3.add(aVar);
            }
        }
        return linkedList3.size() != 0 ? linkedList3 : linkedList2;
    }

    public synchronized String[] a(LinkedList<a> linkedList) {
        String[] strArr;
        int size = linkedList.size();
        strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = linkedList.get(i).f8098a;
        }
        return strArr;
    }
}
