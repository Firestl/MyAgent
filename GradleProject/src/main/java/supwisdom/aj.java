package supwisdom;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import supwisdom.si;
import supwisdom.yi;

/* JADX INFO: compiled from: AbstractEventHandler.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class aj implements vi {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile Map<String, List<ij>> f6927a;
    public volatile Map<String, jj> b;
    public si.d c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f6928e;
    public String f;
    public String g;
    public yi h;
    public jj i;
    public Object[] j;
    public final Map<String, Object> d = new HashMap();
    public a<String, hj> k = new a<>(16);

    /* JADX INFO: compiled from: AbstractEventHandler.java */
    public static class a<K, V> extends LinkedHashMap<K, V> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f6929a;

        public a(int i) {
            super(4, 0.75f, true);
            this.f6929a = Math.max(i, 4);
        }

        @Override // java.util.LinkedHashMap
        public boolean removeEldestEntry(Map.Entry entry) {
            return size() > this.f6929a;
        }
    }

    public aj(Context context, yi yiVar, Object... objArr) {
        this.h = yiVar;
        this.f6928e = (objArr == null || objArr.length <= 0 || !(objArr[0] instanceof String)) ? null : (String) objArr[0];
    }

    @Override // supwisdom.vi
    public void a(String str) {
        this.f = str;
    }

    public abstract void a(String str, Map<String, Object> map);

    public final void b() {
        lj.a(this.d);
        sj.a(this.d);
        Map<String, kj> mapA = ti.b().a();
        if (mapA == null || mapA.isEmpty()) {
            return;
        }
        this.d.putAll(mapA);
    }

    @Override // supwisdom.vi
    public void b(Map<String, Object> map) {
    }

    public void c() {
        xi.a("all expression are cleared");
        if (this.f6927a != null) {
            this.f6927a.clear();
            this.f6927a = null;
        }
        this.i = null;
    }

    public abstract void c(Map<String, Object> map);

    public final void d(Map<String, Object> map) {
        if (this.b == null || this.b.isEmpty()) {
            return;
        }
        for (Map.Entry<String, jj> entry : this.b.entrySet()) {
            String key = entry.getKey();
            jj value = entry.getValue();
            if (!TextUtils.isEmpty(key) && value != null) {
                a(key, value, map);
            }
        }
    }

    @Override // supwisdom.vi
    public void onDestroy() {
        this.k.clear();
        ui.b().a();
    }

    @Override // supwisdom.vi
    public void a(String str, Map<String, Object> map, jj jjVar, List<Map<String, Object>> list, si.d dVar) {
        c();
        a(str, list);
        this.c = dVar;
        this.i = jjVar;
        if (!this.d.isEmpty()) {
            this.d.clear();
        }
        b();
    }

    @Override // supwisdom.vi
    public void b(String str) {
        this.g = str;
    }

    public final void a(String str, List<Map<String, Object>> list) throws JSONException {
        Map<String, Object> mapA;
        if (this.f6927a == null) {
            this.f6927a = new HashMap();
        }
        for (Map<String, Object> map : list) {
            String strB = tj.b(map, "element");
            String strB2 = tj.b(map, "instanceId");
            String strB3 = tj.b(map, "property");
            jj jjVarA = tj.a(map, "expression");
            Object obj = map.get(com.igexin.push.core.b.Y);
            if (obj == null || !(obj instanceof Map)) {
                mapA = null;
            } else {
                try {
                    mapA = tj.a(new JSONObject((Map) obj));
                } catch (Exception e2) {
                    xi.a("parse config failed", e2);
                    mapA = null;
                }
            }
            if (TextUtils.isEmpty(strB) || TextUtils.isEmpty(strB3) || jjVarA == null) {
                xi.b("skip illegal binding args[" + strB + "," + strB3 + "," + jjVarA + Operators.ARRAY_END_STR);
            } else {
                ij ijVar = new ij(strB, strB2, jjVarA, strB3, str, mapA);
                List<ij> list2 = this.f6927a.get(strB);
                if (list2 == null) {
                    ArrayList arrayList = new ArrayList(4);
                    this.f6927a.put(strB, arrayList);
                    arrayList.add(ijVar);
                } else if (!list2.contains(ijVar)) {
                    list2.add(ijVar);
                }
            }
        }
    }

    public boolean a(jj jjVar, Map<String, Object> map) {
        boolean zBooleanValue;
        if (jj.a(jjVar)) {
            try {
                zBooleanValue = ((Boolean) new hj(jjVar.b).a(map)).booleanValue();
            } catch (Exception e2) {
                xi.a("evaluateExitExpression failed. ", e2);
                zBooleanValue = false;
            }
        } else {
            zBooleanValue = false;
        }
        if (zBooleanValue) {
            c();
            try {
                c(map);
            } catch (Exception e3) {
                xi.a("execute exit expression failed: ", e3);
            }
            xi.a("exit = true,consume finished");
        }
        return zBooleanValue;
    }

    @Override // supwisdom.wi
    public void a(Map<String, jj> map) {
        this.b = map;
    }

    public void a(String str, jj jjVar, Map<String, Object> map) {
        if (jj.a(jjVar)) {
            hj hjVar = new hj(jjVar.b);
            boolean zBooleanValue = false;
            try {
                zBooleanValue = ((Boolean) hjVar.a(map)).booleanValue();
            } catch (Exception e2) {
                xi.a("evaluate interceptor [" + str + "] expression failed. ", e2);
            }
            if (zBooleanValue) {
                a(str, map);
            }
        }
    }

    public void a(Map<String, List<ij>> map, Map<String, Object> map2, String str) throws JSONException, IllegalArgumentException {
        Map<String, Object> map3 = map2;
        d(map3);
        if (map == null) {
            xi.b("expression args is null");
            return;
        }
        if (map.isEmpty()) {
            xi.b("no expression need consumed");
            return;
        }
        int i = 2;
        if (xi.f9767a) {
            xi.a(String.format(Locale.getDefault(), "consume expression with %d tasks. event type is %s", Integer.valueOf(map.size()), str));
        }
        LinkedList linkedList = new LinkedList();
        Iterator<List<ij>> it = map.values().iterator();
        while (it.hasNext()) {
            for (ij ijVar : it.next()) {
                if (str.equals(ijVar.f7959e)) {
                    linkedList.clear();
                    Object[] objArr = this.j;
                    if (objArr != null && objArr.length > 0) {
                        Collections.addAll(linkedList, objArr);
                    }
                    String str2 = TextUtils.isEmpty(ijVar.b) ? this.f6928e : ijVar.b;
                    if (!TextUtils.isEmpty(str2)) {
                        linkedList.add(str2);
                    }
                    jj jjVar = ijVar.c;
                    if (jj.a(jjVar)) {
                        hj hjVar = this.k.get(jjVar.b);
                        if (hjVar == null) {
                            hjVar = new hj(jjVar.b);
                            this.k.put(jjVar.b, hjVar);
                        }
                        Object objA = hjVar.a(map3);
                        if (objA == null) {
                            xi.b("failed to execute expression,expression result is null");
                        } else if (((objA instanceof Double) && Double.isNaN(((Double) objA).doubleValue())) || ((objA instanceof Float) && Float.isNaN(((Float) objA).floatValue()))) {
                            xi.b("failed to execute expression,expression result is NaN");
                        } else {
                            View viewA = this.h.b().a(ijVar.f7958a, linkedList.toArray());
                            ui uiVarB = ui.b();
                            String str3 = ijVar.d;
                            yi.c cVarA = this.h.a();
                            Map<String, Object> map4 = ijVar.f;
                            Object[] objArr2 = new Object[i];
                            objArr2[0] = ijVar.f7958a;
                            objArr2[1] = str2;
                            uiVarB.a(viewA, str3, objA, cVarA, map4, objArr2);
                            if (viewA == null) {
                                xi.b("failed to execute expression,target view not found.[ref:" + ijVar.f7958a + Operators.ARRAY_END_STR);
                                map3 = map2;
                                i = 2;
                            } else {
                                i = 2;
                                this.h.c().a(viewA, ijVar.d, objA, this.h.a(), ijVar.f, ijVar.f7958a, str2);
                                map3 = map2;
                            }
                        }
                    }
                } else {
                    xi.a("skip expression with wrong event type.[expected:" + str + ",found:" + ijVar.f7959e + Operators.ARRAY_END_STR);
                }
            }
            map3 = map2;
        }
    }

    @Override // supwisdom.vi
    public void a(Object[] objArr) {
        this.j = objArr;
    }
}
