package supwisdom;

import android.text.SpannableStringBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/* JADX INFO: compiled from: TtmlNode.java */
/* JADX INFO: loaded from: classes.dex */
public final class h60 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f7805a;
    public final String b;
    public final boolean c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f7806e;
    public final k60 f;
    public final String g;
    public final String[] h;
    public final HashMap<String, Integer> i;
    public final HashMap<String, Integer> j;
    public List<h60> k;

    public h60(String str, String str2, long j, long j2, k60 k60Var, String[] strArr, String str3) {
        this.f7805a = str;
        this.b = str2;
        this.f = k60Var;
        this.h = strArr;
        this.c = str2 != null;
        this.d = j;
        this.f7806e = j2;
        e80.a(str3);
        this.g = str3;
        this.i = new HashMap<>();
        this.j = new HashMap<>();
    }

    public static h60 a(String str) {
        return new h60(null, j60.a(str), -9223372036854775807L, -9223372036854775807L, null, null, "");
    }

    public long[] b() {
        TreeSet<Long> treeSet = new TreeSet<>();
        int i = 0;
        a(treeSet, false);
        long[] jArr = new long[treeSet.size()];
        Iterator<Long> it = treeSet.iterator();
        while (it.hasNext()) {
            jArr[i] = it.next().longValue();
            i++;
        }
        return jArr;
    }

    public static h60 a(String str, long j, long j2, k60 k60Var, String[] strArr, String str2) {
        return new h60(str, null, j, j2, k60Var, strArr, str2);
    }

    public boolean a(long j) {
        return (this.d == -9223372036854775807L && this.f7806e == -9223372036854775807L) || (this.d <= j && this.f7806e == -9223372036854775807L) || ((this.d == -9223372036854775807L && j < this.f7806e) || (this.d <= j && j < this.f7806e));
    }

    public void a(h60 h60Var) {
        if (this.k == null) {
            this.k = new ArrayList();
        }
        this.k.add(h60Var);
    }

    public h60 a(int i) {
        List<h60> list = this.k;
        if (list != null) {
            return list.get(i);
        }
        throw new IndexOutOfBoundsException();
    }

    public int a() {
        List<h60> list = this.k;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public final void a(TreeSet<Long> treeSet, boolean z) {
        boolean zEquals = "p".equals(this.f7805a);
        if (z || zEquals) {
            long j = this.d;
            if (j != -9223372036854775807L) {
                treeSet.add(Long.valueOf(j));
            }
            long j2 = this.f7806e;
            if (j2 != -9223372036854775807L) {
                treeSet.add(Long.valueOf(j2));
            }
        }
        if (this.k == null) {
            return;
        }
        for (int i = 0; i < this.k.size(); i++) {
            this.k.get(i).a(treeSet, z || zEquals);
        }
    }

    public List<y50> a(long j, Map<String, k60> map, Map<String, i60> map2) {
        TreeMap treeMap = new TreeMap();
        a(j, false, this.g, (Map<String, SpannableStringBuilder>) treeMap);
        a(map, treeMap);
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : treeMap.entrySet()) {
            i60 i60Var = map2.get(entry.getKey());
            SpannableStringBuilder spannableStringBuilder = (SpannableStringBuilder) entry.getValue();
            a(spannableStringBuilder);
            arrayList.add(new y50(spannableStringBuilder, null, i60Var.c, i60Var.d, i60Var.f7918e, i60Var.b, Integer.MIN_VALUE, i60Var.f));
        }
        return arrayList;
    }

    public final void a(long j, boolean z, String str, Map<String, SpannableStringBuilder> map) {
        this.i.clear();
        this.j.clear();
        String str2 = this.g;
        if (!"".equals(str2)) {
            str = str2;
        }
        if (this.c && z) {
            a(str, map).append((CharSequence) this.b);
            return;
        }
        if ("br".equals(this.f7805a) && z) {
            a(str, map).append('\n');
            return;
        }
        if (!"metadata".equals(this.f7805a) && a(j)) {
            boolean zEquals = "p".equals(this.f7805a);
            for (Map.Entry<String, SpannableStringBuilder> entry : map.entrySet()) {
                this.i.put(entry.getKey(), Integer.valueOf(entry.getValue().length()));
            }
            for (int i = 0; i < a(); i++) {
                a(i).a(j, z || zEquals, str, map);
            }
            if (zEquals) {
                j60.a(a(str, map));
            }
            for (Map.Entry<String, SpannableStringBuilder> entry2 : map.entrySet()) {
                this.j.put(entry2.getKey(), Integer.valueOf(entry2.getValue().length()));
            }
        }
    }

    public static SpannableStringBuilder a(String str, Map<String, SpannableStringBuilder> map) {
        if (!map.containsKey(str)) {
            map.put(str, new SpannableStringBuilder());
        }
        return map.get(str);
    }

    public final void a(Map<String, k60> map, Map<String, SpannableStringBuilder> map2) {
        for (Map.Entry<String, Integer> entry : this.j.entrySet()) {
            String key = entry.getKey();
            a(map, map2.get(key), this.i.containsKey(key) ? this.i.get(key).intValue() : 0, entry.getValue().intValue());
            for (int i = 0; i < a(); i++) {
                a(i).a(map, map2);
            }
        }
    }

    public final void a(Map<String, k60> map, SpannableStringBuilder spannableStringBuilder, int i, int i2) {
        k60 k60VarA;
        if (i == i2 || (k60VarA = j60.a(this.f, this.h, map)) == null) {
            return;
        }
        j60.a(spannableStringBuilder, i, i2, k60VarA);
    }

    public final SpannableStringBuilder a(SpannableStringBuilder spannableStringBuilder) {
        int i;
        int i2;
        int length = spannableStringBuilder.length();
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            if (spannableStringBuilder.charAt(i4) == ' ') {
                int i5 = i4 + 1;
                int i6 = i5;
                while (i6 < spannableStringBuilder.length() && spannableStringBuilder.charAt(i6) == ' ') {
                    i6++;
                }
                int i7 = i6 - i5;
                if (i7 > 0) {
                    spannableStringBuilder.delete(i4, i4 + i7);
                    length -= i7;
                }
            }
        }
        if (length > 0 && spannableStringBuilder.charAt(0) == ' ') {
            spannableStringBuilder.delete(0, 1);
            length--;
        }
        int i8 = 0;
        while (true) {
            i = length - 1;
            if (i8 >= i) {
                break;
            }
            if (spannableStringBuilder.charAt(i8) == '\n') {
                int i9 = i8 + 1;
                if (spannableStringBuilder.charAt(i9) == ' ') {
                    spannableStringBuilder.delete(i9, i8 + 2);
                    length--;
                }
            }
            i8++;
        }
        if (length > 0 && spannableStringBuilder.charAt(i) == ' ') {
            spannableStringBuilder.delete(i, length);
            length--;
        }
        while (true) {
            i2 = length - 1;
            if (i3 >= i2) {
                break;
            }
            if (spannableStringBuilder.charAt(i3) == ' ') {
                int i10 = i3 + 1;
                if (spannableStringBuilder.charAt(i10) == '\n') {
                    spannableStringBuilder.delete(i3, i10);
                    length--;
                }
            }
            i3++;
        }
        if (length > 0 && spannableStringBuilder.charAt(i2) == '\n') {
            spannableStringBuilder.delete(i2, length);
        }
        return spannableStringBuilder;
    }
}
