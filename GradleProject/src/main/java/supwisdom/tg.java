package supwisdom;

import android.view.View;
import androidx.transition.Transition;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.util.ASN1Dump;

/* JADX INFO: compiled from: TransitionValues.java */
/* JADX INFO: loaded from: classes.dex */
public class tg {
    public View b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<String, Object> f9283a = new HashMap();
    public final ArrayList<Transition> c = new ArrayList<>();

    public boolean equals(Object obj) {
        if (!(obj instanceof tg)) {
            return false;
        }
        tg tgVar = (tg) obj;
        return this.b == tgVar.b && this.f9283a.equals(tgVar.f9283a);
    }

    public int hashCode() {
        return (this.b.hashCode() * 31) + this.f9283a.hashCode();
    }

    public String toString() {
        String str = (("TransitionValues@" + Integer.toHexString(hashCode()) + ":\n") + "    view = " + this.b + "\n") + "    values:";
        for (String str2 : this.f9283a.keySet()) {
            str = str + ASN1Dump.TAB + str2 + ": " + this.f9283a.get(str2) + "\n";
        }
        return str;
    }
}
