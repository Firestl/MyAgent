package supwisdom;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-appindexing@@20.0.0 */
/* JADX INFO: loaded from: classes.dex */
public final class wj0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String[] f9637a = {"text1", "text2", "icon", "intent_action", "intent_data", "intent_data_id", "intent_extra_data", "suggest_large_icon", "intent_activity", "thing_proto"};
    public static final Map<String, Integer> b;

    static {
        int length = f9637a.length;
        b = new HashMap(10);
        int i = 0;
        while (true) {
            String[] strArr = f9637a;
            int length2 = strArr.length;
            if (i >= 10) {
                return;
            }
            b.put(strArr[i], Integer.valueOf(i));
            i++;
        }
    }

    public static String a(int i) {
        if (i < 0) {
            return null;
        }
        String[] strArr = f9637a;
        int length = strArr.length;
        if (i >= 10) {
            return null;
        }
        return strArr[i];
    }
}
