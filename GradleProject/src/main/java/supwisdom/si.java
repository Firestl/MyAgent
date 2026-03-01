package supwisdom;

import android.content.Context;
import android.text.TextUtils;
import com.taobao.weex.common.Constants;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.ui.component.list.BasicListComponent;
import io.dcloud.feature.weex_amap.adapter.Constant;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.json.JSONObject;

/* JADX INFO: compiled from: BindingXCore.java */
/* JADX INFO: loaded from: classes.dex */
public class si {
    public static final Map<String, e<vi, Context, yi>> d = new HashMap(4);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Map<String, Map<String, vi>> f9174a;
    public final Map<String, e<vi, Context, yi>> b = new HashMap(8);
    public final yi c;

    /* JADX INFO: compiled from: BindingXCore.java */
    public class a implements e<vi, Context, yi> {
        public a(si siVar) {
        }

        @Override // supwisdom.si.e
        public vi a(Context context, yi yiVar, Object... objArr) {
            return new fj(context, yiVar, objArr);
        }
    }

    /* JADX INFO: compiled from: BindingXCore.java */
    public class b implements e<vi, Context, yi> {
        public b(si siVar) {
        }

        @Override // supwisdom.si.e
        public vi a(Context context, yi yiVar, Object... objArr) {
            return new dj(context, yiVar, objArr);
        }
    }

    /* JADX INFO: compiled from: BindingXCore.java */
    public class c implements e<vi, Context, yi> {
        public c(si siVar) {
        }

        @Override // supwisdom.si.e
        public vi a(Context context, yi yiVar, Object... objArr) {
            return new ej(context, yiVar, objArr);
        }
    }

    /* JADX INFO: compiled from: BindingXCore.java */
    public interface d {
        void a(Object obj);
    }

    /* JADX INFO: compiled from: BindingXCore.java */
    public interface e<Type, ParamA, ParamB> {
        Type a(ParamA parama, ParamB paramb, Object... objArr);
    }

    public si(yi yiVar) {
        this.c = yiVar;
        a(BasicListComponent.DragTriggerType.PAN, new a(this));
        a(Constants.Name.ORIENTATION, new b(this));
        a("timing", new c(this));
    }

    public String a(Context context, String str, Map<String, Object> map, d dVar, Object... objArr) {
        Map<String, Object> mapA;
        String strB = tj.b(map, "eventType");
        String strB2 = tj.b(map, "instanceId");
        xi.a(map);
        Object obj = map.get("options");
        if (obj == null || !(obj instanceof Map)) {
            mapA = null;
        } else {
            try {
                mapA = tj.a(new JSONObject((Map) obj));
            } catch (Exception e2) {
                xi.a("parse external config failed.\n", e2);
                mapA = null;
            }
        }
        jj jjVarA = tj.a(map, "exitExpression");
        return a(tj.b(map, Constant.JSONKEY.ANCHOR), strB2, strB, mapA, jjVarA, tj.b(map), tj.a(map), dVar, context, str, objArr);
    }

    public final String b() {
        return UUID.randomUUID().toString();
    }

    public void c() {
        Map<String, Map<String, vi>> map = this.f9174a;
        if (map == null) {
            return;
        }
        try {
            Iterator<Map<String, vi>> it = map.values().iterator();
            while (it.hasNext()) {
                Iterator<vi> it2 = it.next().values().iterator();
                while (it2.hasNext()) {
                    try {
                        it2.next().onActivityPause();
                    } catch (Exception e2) {
                        xi.a("execute activity pause failed.", e2);
                    }
                }
            }
        } catch (Exception e3) {
            xi.a("activity pause failed", e3);
        }
    }

    public void d() {
        Map<String, Map<String, vi>> map = this.f9174a;
        if (map == null) {
            return;
        }
        try {
            Iterator<Map<String, vi>> it = map.values().iterator();
            while (it.hasNext()) {
                Iterator<vi> it2 = it.next().values().iterator();
                while (it2.hasNext()) {
                    try {
                        it2.next().onActivityResume();
                    } catch (Exception e2) {
                        xi.a("execute activity pause failed.", e2);
                    }
                }
            }
        } catch (Exception e3) {
            xi.a("activity pause failed", e3);
        }
    }

    public void a(Map<String, Object> map) {
        if (map == null) {
            return;
        }
        a(tj.b(map, "token"), tj.b(map, "eventType"));
    }

    public void a(String str, String str2) {
        xi.a("disable binding [" + str + "," + str2 + Operators.ARRAY_END_STR);
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            Map<String, Map<String, vi>> map = this.f9174a;
            if (map != null && !map.isEmpty()) {
                Map<String, vi> map2 = this.f9174a.get(str);
                if (map2 != null && !map2.isEmpty()) {
                    vi viVar = map2.get(str2);
                    if (viVar == null) {
                        xi.a("disable binding failed(0x4) [" + str + "," + str2 + Operators.ARRAY_END_STR);
                        return;
                    }
                    if (viVar.a(str, str2)) {
                        this.f9174a.remove(str);
                        xi.a("disable binding success[" + str + "," + str2 + Operators.ARRAY_END_STR);
                        return;
                    }
                    xi.a("disabled failed(0x4) [" + str + "," + str2 + Operators.ARRAY_END_STR);
                    return;
                }
                xi.a("disable binding failed(0x3) [" + str + "," + str2 + Operators.ARRAY_END_STR);
                return;
            }
            xi.a("disable binding failed(0x2) [" + str + "," + str2 + Operators.ARRAY_END_STR);
            return;
        }
        xi.a("disable binding failed(0x1) [" + str + "," + str2 + Operators.ARRAY_END_STR);
    }

    public void a() {
        Map<String, Map<String, vi>> map = this.f9174a;
        if (map != null) {
            try {
                for (Map<String, vi> map2 : map.values()) {
                    if (map2 != null && !map2.isEmpty()) {
                        for (vi viVar : map2.values()) {
                            if (viVar != null) {
                                viVar.onDestroy();
                            }
                        }
                    }
                }
                this.f9174a.clear();
                this.f9174a = null;
            } catch (Exception e2) {
                xi.a("release failed", e2);
            }
        }
    }

    public String a(Context context, String str, String str2, String str3, String str4, Map<String, Object> map) {
        vi viVar;
        if (TextUtils.isEmpty(str4)) {
            xi.b("[doPrepare] failed. can not found eventType");
            return null;
        }
        if (context == null) {
            xi.b("[doPrepare] failed. context or wxInstance is null");
            return null;
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = b();
        }
        if (this.f9174a == null) {
            this.f9174a = new HashMap();
        }
        Map<String, vi> map2 = this.f9174a.get(str2);
        if (map2 != null && (viVar = map2.get(str4)) != null) {
            xi.a("you have already enabled binding,[token:" + str2 + ",type:" + str4 + Operators.ARRAY_END_STR);
            viVar.c(str2, str4);
            xi.a("enableBinding success.[token:" + str2 + ",type:" + str4 + Operators.ARRAY_END_STR);
        } else {
            if (map2 == null) {
                map2 = new HashMap<>(4);
                this.f9174a.put(str2, map2);
            }
            vi viVarA = a(context, str, str4);
            if (viVarA != null) {
                viVarA.a(str3);
                viVarA.b(str2);
                viVarA.b(map);
                if (viVarA.b(str2, str4)) {
                    viVarA.c(str2, str4);
                    map2.put(str4, viVarA);
                    xi.a("enableBinding success.[token:" + str2 + ",type:" + str4 + Operators.ARRAY_END_STR);
                } else {
                    xi.b("expression enabled failed. [token:" + str2 + ",type:" + str4 + Operators.ARRAY_END_STR);
                    return null;
                }
            } else {
                xi.b("unknown eventType: " + str4);
                return null;
            }
        }
        return str2;
    }

    public String a(String str, String str2, String str3, Map<String, Object> map, jj jjVar, List<Map<String, Object>> list, Map<String, jj> map2, d dVar, Context context, String str4, Object... objArr) {
        String str5;
        Map<String, Map<String, vi>> map3;
        Map<String, vi> map4;
        Map<String, vi> map5;
        vi viVar = null;
        if (!TextUtils.isEmpty(str3) && list != null) {
            if (this.f9174a != null && !TextUtils.isEmpty(str) && (map5 = this.f9174a.get(str)) != null) {
                viVar = map5.get(str3);
            }
            vi viVar2 = viVar;
            if (viVar2 == null) {
                xi.a("binding not enabled,try auto enable it.[sourceRef:" + str + ",eventType:" + str3 + Operators.ARRAY_END_STR);
                String strA = a(context, str4, str, str2, str3, map);
                if (!TextUtils.isEmpty(strA) && (map3 = this.f9174a) != null && (map4 = map3.get(strA)) != null) {
                    viVar2 = map4.get(str3);
                }
                str5 = strA;
            } else {
                str5 = str;
            }
            if (viVar2 != null) {
                viVar2.a(str3, map, jjVar, list, dVar);
                xi.a("createBinding success.[exitExp:" + jjVar + ",args:" + list + Operators.ARRAY_END_STR);
                viVar2.a(map2);
                viVar2.a(objArr);
            } else {
                xi.b("internal error.binding failed for ref:" + str + ",type:" + str3);
            }
            return str5;
        }
        xi.b("doBind failed,illegal argument.[" + str3 + "," + list + Operators.ARRAY_END_STR);
        return null;
    }

    public void a(String str, e<vi, Context, yi> eVar) {
        if (TextUtils.isEmpty(str) || eVar == null) {
            return;
        }
        this.b.put(str, eVar);
    }

    public final vi a(Context context, String str, String str2) {
        if (this.b.isEmpty() || this.c == null) {
            return null;
        }
        e<vi, Context, yi> eVar = this.b.get(str2);
        if (eVar == null) {
            eVar = d.get(str2);
        }
        if (eVar != null) {
            return eVar.a(context, this.c, str);
        }
        return null;
    }
}
