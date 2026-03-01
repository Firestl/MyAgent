package com.loc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: WifiCollector.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bz {
    public dc b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public List<dd> f3701a = new ArrayList();
    public ArrayList<dd> c = new ArrayList<>();

    /* JADX WARN: Multi-variable type inference failed */
    private void a(List<dd> list, List<dd> list2) {
        list.clear();
        if (list2 != null) {
            ArrayList arrayList = new ArrayList();
            HashMap map = new HashMap();
            for (int i = 0; i < list2.size(); i++) {
                dd ddVar = list2.get(i);
                map.put(Integer.valueOf(ddVar.c), ddVar);
            }
            arrayList.addAll(map.values());
            Collections.sort(arrayList, new Comparator<dd>() { // from class: com.loc.bz.1
                @Override // java.util.Comparator
                public final /* bridge */ /* synthetic */ int compare(dd ddVar2, dd ddVar3) {
                    return ddVar3.c - ddVar2.c;
                }
            });
            int size = arrayList.size();
            if (size > 40) {
                size = 40;
            }
            for (int i2 = 0; i2 < size; i2++) {
                list.add(arrayList.get(i2));
            }
        }
    }

    public final List<dd> a(dc dcVar, List<dd> list, boolean z, long j, long j2) {
        List<dd> list2;
        boolean z2 = false;
        if (z) {
            float f = 10.0f;
            if ((j > 0 && j2 - j < ((long) (dcVar.g >= 10.0f ? 2000 : 3500))) && list != null && list.size() > 0) {
                if (this.b != null) {
                    float f2 = dcVar.g;
                    if (f2 > 10.0f) {
                        f = 200.0f;
                    } else if (f2 > 2.0f) {
                        f = 50.0f;
                    }
                    boolean z3 = dcVar.a(this.b) > ((double) f);
                    if (z3) {
                        z2 = z3;
                    } else {
                        List<dd> list3 = this.f3701a;
                        if (list != null && list3 != null) {
                            int size = list.size();
                            int size2 = list3.size();
                            int i = size + size2;
                            if (size > size2) {
                                list2 = list3;
                                list3 = list;
                            } else {
                                list2 = list;
                            }
                            HashMap map = new HashMap(list3.size());
                            Iterator<dd> it = list3.iterator();
                            while (it.hasNext()) {
                                map.put(Long.valueOf(it.next().f3722a), 1);
                            }
                            Iterator<dd> it2 = list2.iterator();
                            int i2 = 0;
                            while (it2.hasNext()) {
                                if (((Integer) map.get(Long.valueOf(it2.next().f3722a))) != null) {
                                    i2++;
                                }
                            }
                            if (((double) i2) * 2.0d >= ((double) i) * 0.5d) {
                                z2 = true;
                            }
                        }
                        z2 = !z2;
                    }
                } else {
                    z2 = true;
                }
            }
        }
        if (!z2) {
            return null;
        }
        a(this.c, list);
        this.f3701a.clear();
        this.f3701a.addAll(list);
        this.b = dcVar;
        return this.c;
    }
}
