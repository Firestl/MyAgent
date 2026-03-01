package supwisdom;

import android.content.Context;
import android.util.AttributeSet;
import androidx.constraintlayout.widget.ConstraintAttribute;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: compiled from: Key.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b5 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static int f7022e = -1;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f7023a;
    public int b;
    public String c;
    public HashMap<String, ConstraintAttribute> d;

    public b5() {
        int i = f7022e;
        this.f7023a = i;
        this.b = i;
        this.c = null;
    }

    public abstract void a(Context context, AttributeSet attributeSet);

    public abstract void a(HashMap<String, q5> map);

    public abstract void a(HashSet<String> hashSet);

    public boolean a(String str) {
        String str2 = this.c;
        if (str2 == null || str == null) {
            return false;
        }
        return str.matches(str2);
    }

    public void b(HashMap<String, Integer> map) {
    }
}
