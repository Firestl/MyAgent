package supwisdom;

import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* JADX INFO: compiled from: Expression.java */
/* JADX INFO: loaded from: classes.dex */
public class hj {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public JSONObject f7843a;

    public hj(String str) {
        try {
            this.f7843a = (JSONObject) new JSONTokener(str).nextValue();
        } catch (Throwable th) {
            xi.a("[Expression] expression is illegal. \n ", th);
        }
    }

    public Object a(Map<String, Object> map) throws JSONException, IllegalArgumentException {
        return a(this.f7843a, map);
    }

    public final double b(Object obj) {
        return obj instanceof String ? Double.parseDouble((String) obj) : obj instanceof Boolean ? ((Boolean) obj).booleanValue() ? 1.0d : 0.0d : ((Double) obj).doubleValue();
    }

    public final boolean a(Object obj) {
        return obj instanceof String ? "".equals(obj) : obj instanceof Double ? ((Double) obj).doubleValue() != 0.0d : ((Boolean) obj).booleanValue();
    }

    public final boolean b(Object obj, Object obj2) {
        if ((obj instanceof mj) && !(obj2 instanceof mj)) {
            return false;
        }
        if ((obj instanceof Boolean) && !(obj2 instanceof Boolean)) {
            return false;
        }
        if (!(obj instanceof Double) || (obj2 instanceof Double)) {
            return (!(obj instanceof String) || (obj2 instanceof String)) && obj == obj2;
        }
        return false;
    }

    public final boolean a(Object obj, Object obj2) {
        if ((obj instanceof mj) && (obj2 instanceof mj)) {
            return obj == obj2;
        }
        if ((obj instanceof String) && (obj2 instanceof String)) {
            return obj.equals(obj2);
        }
        return ((obj instanceof Boolean) && (obj2 instanceof Boolean)) ? a(obj) == a(obj2) : b(obj) == b(obj2);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:74:0x011a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object a(org.json.JSONObject r8, java.util.Map<java.lang.String, java.lang.Object> r9) throws org.json.JSONException, java.lang.IllegalArgumentException {
        /*
            Method dump skipped, instruction units count: 1038
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.hj.a(org.json.JSONObject, java.util.Map):java.lang.Object");
    }
}
