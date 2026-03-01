package supwisdom;

import com.bumptech.glide.load.engine.GlideException;
import io.dcloud.common.util.PdrUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import org.bouncycastle.asn1.util.ASN1Dump;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class u21 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HashMap<String, a> f9358a = new HashMap<>(50);

    /* JADX INFO: compiled from: Proguard */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f9359a;
        public int b;
        public int c;
        public int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f9360e;

        public a(f21 f21Var, String str) {
            int iC = f21Var.c();
            this.f9359a = str;
            this.b = 1;
            this.c = iC;
            this.d = iC;
            this.f9360e = iC;
        }

        public void a(f21 f21Var) {
            int iC = f21Var.c();
            this.b++;
            this.c += iC;
            if (iC > this.d) {
                this.d = iC;
            }
            if (iC < this.f9360e) {
                this.f9360e = iC;
            }
        }

        public String a() {
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(GlideException.IndentedAppendable.INDENT);
            sb2.append(this.f9359a);
            sb2.append(": ");
            sb2.append(this.b);
            sb2.append(" item");
            sb2.append(this.b == 1 ? "" : "s");
            sb2.append("; ");
            sb2.append(this.c);
            sb2.append(" bytes total\n");
            sb.append(sb2.toString());
            if (this.f9360e == this.d) {
                sb.append(ASN1Dump.TAB + this.f9360e + " bytes/item\n");
            } else {
                sb.append(ASN1Dump.TAB + this.f9360e + PdrUtil.FILE_PATH_ENTRY_BACK + this.d + " bytes/item; average " + (this.c / this.b) + "\n");
            }
            return sb.toString();
        }

        public void a(h61 h61Var) {
            h61Var.a(a());
        }
    }

    public void a(f21 f21Var) {
        String strB = f21Var.b();
        a aVar = this.f9358a.get(strB);
        if (aVar == null) {
            this.f9358a.put(strB, new a(f21Var, strB));
        } else {
            aVar.a(f21Var);
        }
    }

    public void a(t21 t21Var) {
        Iterator<? extends f21> it = t21Var.d().iterator();
        while (it.hasNext()) {
            a(it.next());
        }
    }

    public final void a(h61 h61Var) {
        if (this.f9358a.size() == 0) {
            return;
        }
        h61Var.a(0, "\nstatistics:\n");
        TreeMap treeMap = new TreeMap();
        for (a aVar : this.f9358a.values()) {
            treeMap.put(aVar.f9359a, aVar);
        }
        Iterator it = treeMap.values().iterator();
        while (it.hasNext()) {
            ((a) it.next()).a(h61Var);
        }
    }
}
