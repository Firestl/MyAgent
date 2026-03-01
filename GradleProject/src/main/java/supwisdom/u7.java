package supwisdom;

import android.app.Person;
import android.os.PersistableBundle;
import androidx.core.graphics.drawable.IconCompat;

/* JADX INFO: compiled from: Person.java */
/* JADX INFO: loaded from: classes.dex */
public class u7 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public CharSequence f9380a;
    public IconCompat b;
    public String c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f9381e;
    public boolean f;

    public IconCompat a() {
        return this.b;
    }

    public String b() {
        return this.d;
    }

    public CharSequence c() {
        return this.f9380a;
    }

    public String d() {
        return this.c;
    }

    public boolean e() {
        return this.f9381e;
    }

    public boolean f() {
        return this.f;
    }

    public String g() {
        String str = this.c;
        if (str != null) {
            return str;
        }
        if (this.f9380a == null) {
            return "";
        }
        return "name:" + ((Object) this.f9380a);
    }

    public Person h() {
        return new Person.Builder().setName(c()).setIcon(a() != null ? a().f() : null).setUri(d()).setKey(b()).setBot(e()).setImportant(f()).build();
    }

    public PersistableBundle i() {
        PersistableBundle persistableBundle = new PersistableBundle();
        CharSequence charSequence = this.f9380a;
        persistableBundle.putString("name", charSequence != null ? charSequence.toString() : null);
        persistableBundle.putString("uri", this.c);
        persistableBundle.putString("key", this.d);
        persistableBundle.putBoolean("isBot", this.f9381e);
        persistableBundle.putBoolean("isImportant", this.f);
        return persistableBundle;
    }
}
