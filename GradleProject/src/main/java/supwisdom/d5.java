package supwisdom;

import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: compiled from: KeyCache.java */
/* JADX INFO: loaded from: classes.dex */
public class d5 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public HashMap<Object, HashMap<String, float[]>> f7295a = new HashMap<>();

    public void a(Object obj, String str, int i, float f) {
        if (!this.f7295a.containsKey(obj)) {
            HashMap<String, float[]> map = new HashMap<>();
            float[] fArr = new float[i + 1];
            fArr[i] = f;
            map.put(str, fArr);
            this.f7295a.put(obj, map);
            return;
        }
        HashMap<String, float[]> map2 = this.f7295a.get(obj);
        if (!map2.containsKey(str)) {
            float[] fArr2 = new float[i + 1];
            fArr2[i] = f;
            map2.put(str, fArr2);
            this.f7295a.put(obj, map2);
            return;
        }
        float[] fArrCopyOf = map2.get(str);
        if (fArrCopyOf.length <= i) {
            fArrCopyOf = Arrays.copyOf(fArrCopyOf, i + 1);
        }
        fArrCopyOf[i] = f;
        map2.put(str, fArrCopyOf);
    }

    public float a(Object obj, String str, int i) {
        if (!this.f7295a.containsKey(obj)) {
            return Float.NaN;
        }
        HashMap<String, float[]> map = this.f7295a.get(obj);
        if (!map.containsKey(str)) {
            return Float.NaN;
        }
        float[] fArr = map.get(str);
        if (fArr.length > i) {
            return fArr[i];
        }
        return Float.NaN;
    }
}
