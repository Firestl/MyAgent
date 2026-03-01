package supwisdom;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: Utils.java */
/* JADX INFO: loaded from: classes.dex */
public final class tj {
    public static float a(float f) {
        float f2 = f % 360.0f;
        return f2 >= 0.0f ? (f2 < 0.0f || f2 > 180.0f) ? (f2 % 180.0f) - 180.0f : f2 : (f2 <= -180.0f || f2 >= 0.0f) ? (f2 % 180.0f) + 180.0f : f2;
    }

    public static Map<String, Object> a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return Collections.emptyMap();
        }
        HashMap map = new HashMap();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            map.put(next, a(jSONObject.get(next)));
        }
        return map;
    }

    public static String b(Map<String, Object> map, String str) {
        Object obj = map.get(str);
        if (obj == null) {
            return null;
        }
        return obj instanceof String ? (String) obj : obj.toString();
    }

    public static List<Map<String, Object>> b(Map<String, Object> map) {
        Object obj = map.get("props");
        if (obj == null) {
            return null;
        }
        try {
            return (List) obj;
        } catch (Exception unused) {
            return null;
        }
    }

    public static List a(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(a(jSONArray.get(i)));
        }
        return arrayList;
    }

    @SafeVarargs
    public static <E> HashSet<E> b(E... eArr) {
        HashSet<E> hashSet = new HashSet<>(eArr.length);
        Collections.addAll(hashSet, eArr);
        return hashSet;
    }

    public static Object a(Object obj) throws JSONException {
        if (obj == JSONObject.NULL) {
            return null;
        }
        if (obj instanceof JSONObject) {
            return a((JSONObject) obj);
        }
        return obj instanceof JSONArray ? a((JSONArray) obj) : obj;
    }

    public static Map<String, jj> a(Map<String, Object> map) {
        jj jjVarA;
        Object obj = map.get("interceptors");
        if (obj == null || !(obj instanceof Map)) {
            return null;
        }
        HashMap map2 = new HashMap(8);
        for (Map.Entry entry : ((Map) obj).entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            if ((key instanceof String) && (value instanceof Map)) {
                try {
                    jjVarA = a((Map<String, Object>) value, "expression");
                } catch (Exception unused) {
                    jjVarA = null;
                }
                if (jjVarA != null) {
                    map2.put((String) key, jjVarA);
                }
            }
        }
        return map2;
    }

    public static jj a(Map<String, Object> map, String str) {
        JSONObject jSONObject;
        Object obj = map.get(str);
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            return jj.a(null, (String) obj);
        }
        if (!(obj instanceof Map)) {
            return null;
        }
        try {
            jSONObject = new JSONObject((Map) obj);
        } catch (Throwable th) {
            xi.a("unexpected json parse error.", th);
            jSONObject = null;
        }
        if (jSONObject == null) {
            return jj.a(null, null);
        }
        String strOptString = jSONObject.optString("origin", null);
        String strOptString2 = jSONObject.optString("transformed", null);
        if (TextUtils.isEmpty(strOptString) && TextUtils.isEmpty(strOptString2)) {
            return jj.a(null, null);
        }
        return jj.a(strOptString, strOptString2);
    }

    @SafeVarargs
    public static <E> ArrayList<E> a(E... eArr) {
        ArrayList<E> arrayList = new ArrayList<>(eArr.length);
        Collections.addAll(arrayList, eArr);
        return arrayList;
    }

    public static int a(Context context, int i) {
        return (int) (context.getApplicationContext().getResources().getDisplayMetrics().density * i * 5.0f);
    }

    public static Pair<Float, Float> a(String str, View view) {
        int iIndexOf;
        int width;
        float f;
        int height;
        if (!TextUtils.isEmpty(str) && (iIndexOf = str.indexOf(32)) != -1) {
            int i = iIndexOf;
            while (i < str.length() && str.charAt(i) == ' ') {
                i++;
            }
            if (i < str.length() && str.charAt(i) != ' ') {
                String strTrim = str.substring(0, iIndexOf).trim();
                String strTrim2 = str.substring(i, str.length()).trim();
                float f2 = 0.0f;
                if ("left".equals(strTrim)) {
                    f = 0.0f;
                } else {
                    if ("right".equals(strTrim)) {
                        width = view.getWidth();
                    } else if ("center".equals(strTrim)) {
                        width = view.getWidth() / 2;
                    } else {
                        width = view.getWidth() / 2;
                    }
                    f = width;
                }
                if (!"top".equals(strTrim2)) {
                    if ("bottom".equals(strTrim2)) {
                        height = view.getHeight();
                    } else if ("center".equals(strTrim2)) {
                        height = view.getHeight() / 2;
                    } else {
                        height = view.getHeight() / 2;
                    }
                    f2 = height;
                }
                return new Pair<>(Float.valueOf(f), Float.valueOf(f2));
            }
        }
        return null;
    }
}
