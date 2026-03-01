package supwisdom;

import android.annotation.SuppressLint;
import android.app.Person;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.net.Uri;
import android.os.Build;
import android.os.PersistableBundle;
import android.text.TextUtils;
import androidx.core.graphics.drawable.IconCompat;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: ShortcutInfoCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class c8 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f7156a;
    public String b;
    public Intent[] c;
    public ComponentName d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CharSequence f7157e;
    public CharSequence f;
    public CharSequence g;
    public IconCompat h;
    public u7[] i;
    public Set<String> j;
    public z7 k;
    public boolean l;
    public int m;
    public PersistableBundle n;

    /* JADX INFO: compiled from: ShortcutInfoCompat.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final c8 f7158a;
        public boolean b;
        public Set<String> c;
        public Map<String, Map<String, List<String>>> d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Uri f7159e;

        public a(Context context, String str) {
            c8 c8Var = new c8();
            this.f7158a = c8Var;
            c8Var.f7156a = context;
            c8Var.b = str;
        }

        public a a(CharSequence charSequence) {
            this.f7158a.f = charSequence;
            return this;
        }

        public a b(CharSequence charSequence) {
            this.f7158a.f7157e = charSequence;
            return this;
        }

        public a a(Intent intent) {
            a(new Intent[]{intent});
            return this;
        }

        public a a(Intent[] intentArr) {
            this.f7158a.c = intentArr;
            return this;
        }

        public a a(IconCompat iconCompat) {
            this.f7158a.h = iconCompat;
            return this;
        }

        @SuppressLint({"UnsafeNewApiCall"})
        public c8 a() {
            if (!TextUtils.isEmpty(this.f7158a.f7157e)) {
                c8 c8Var = this.f7158a;
                Intent[] intentArr = c8Var.c;
                if (intentArr != null && intentArr.length != 0) {
                    if (this.b) {
                        if (c8Var.k == null) {
                            c8Var.k = new z7(c8Var.b);
                        }
                        this.f7158a.l = true;
                    }
                    if (this.c != null) {
                        c8 c8Var2 = this.f7158a;
                        if (c8Var2.j == null) {
                            c8Var2.j = new HashSet();
                        }
                        this.f7158a.j.addAll(this.c);
                    }
                    if (Build.VERSION.SDK_INT >= 21) {
                        if (this.d != null) {
                            c8 c8Var3 = this.f7158a;
                            if (c8Var3.n == null) {
                                c8Var3.n = new PersistableBundle();
                            }
                            for (String str : this.d.keySet()) {
                                Map<String, List<String>> map = this.d.get(str);
                                this.f7158a.n.putStringArray(str, (String[]) map.keySet().toArray(new String[0]));
                                for (String str2 : map.keySet()) {
                                    List<String> list = map.get(str2);
                                    this.f7158a.n.putStringArray(str + "/" + str2, list == null ? new String[0] : (String[]) list.toArray(new String[0]));
                                }
                            }
                        }
                        if (this.f7159e != null) {
                            c8 c8Var4 = this.f7158a;
                            if (c8Var4.n == null) {
                                c8Var4.n = new PersistableBundle();
                            }
                            this.f7158a.n.putString("extraSliceUri", j9.a(this.f7159e));
                        }
                    }
                    return this.f7158a;
                }
                throw new IllegalArgumentException("Shortcut must have an intent");
            }
            throw new IllegalArgumentException("Shortcut must have a non-empty label");
        }
    }

    public final PersistableBundle a() {
        if (this.n == null) {
            this.n = new PersistableBundle();
        }
        u7[] u7VarArr = this.i;
        if (u7VarArr != null && u7VarArr.length > 0) {
            this.n.putInt("extraPersonCount", u7VarArr.length);
            int i = 0;
            while (i < this.i.length) {
                PersistableBundle persistableBundle = this.n;
                StringBuilder sb = new StringBuilder();
                sb.append("extraPerson_");
                int i2 = i + 1;
                sb.append(i2);
                persistableBundle.putPersistableBundle(sb.toString(), this.i[i].i());
                i = i2;
            }
        }
        z7 z7Var = this.k;
        if (z7Var != null) {
            this.n.putString("extraLocusId", z7Var.a());
        }
        this.n.putBoolean("extraLongLived", this.l);
        return this.n;
    }

    public String b() {
        return this.b;
    }

    public int c() {
        return this.m;
    }

    public ShortcutInfo d() {
        ShortcutInfo.Builder intents = new ShortcutInfo.Builder(this.f7156a, this.b).setShortLabel(this.f7157e).setIntents(this.c);
        IconCompat iconCompat = this.h;
        if (iconCompat != null) {
            intents.setIcon(iconCompat.b(this.f7156a));
        }
        if (!TextUtils.isEmpty(this.f)) {
            intents.setLongLabel(this.f);
        }
        if (!TextUtils.isEmpty(this.g)) {
            intents.setDisabledMessage(this.g);
        }
        ComponentName componentName = this.d;
        if (componentName != null) {
            intents.setActivity(componentName);
        }
        Set<String> set = this.j;
        if (set != null) {
            intents.setCategories(set);
        }
        intents.setRank(this.m);
        PersistableBundle persistableBundle = this.n;
        if (persistableBundle != null) {
            intents.setExtras(persistableBundle);
        }
        if (Build.VERSION.SDK_INT >= 29) {
            u7[] u7VarArr = this.i;
            if (u7VarArr != null && u7VarArr.length > 0) {
                int length = u7VarArr.length;
                Person[] personArr = new Person[length];
                for (int i = 0; i < length; i++) {
                    personArr[i] = this.i[i].h();
                }
                intents.setPersons(personArr);
            }
            z7 z7Var = this.k;
            if (z7Var != null) {
                intents.setLocusId(z7Var.c());
            }
            intents.setLongLived(this.l);
        } else {
            intents.setExtras(a());
        }
        return intents.build();
    }
}
