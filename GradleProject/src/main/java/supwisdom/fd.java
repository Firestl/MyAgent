package supwisdom;

import android.util.Log;
import androidx.fragment.app.Fragment;
import com.taobao.weex.el.parse.Operators;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import supwisdom.he;

/* JADX INFO: compiled from: FragmentManagerViewModel.java */
/* JADX INFO: loaded from: classes.dex */
public class fd extends ge {
    public static final he.a h = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f7590e;
    public final HashSet<Fragment> b = new HashSet<>();
    public final HashMap<String, fd> c = new HashMap<>();
    public final HashMap<String, ie> d = new HashMap<>();
    public boolean f = false;
    public boolean g = false;

    /* JADX INFO: compiled from: FragmentManagerViewModel.java */
    public static class a implements he.a {
        @Override // supwisdom.he.a
        public <T extends ge> T a(Class<T> cls) {
            return new fd(true);
        }
    }

    public fd(boolean z) {
        this.f7590e = z;
    }

    public static fd a(ie ieVar) {
        return (fd) new he(ieVar, h).a(fd.class);
    }

    @Override // supwisdom.ge
    public void b() {
        if (ed.H) {
            Log.d("FragmentManager", "onCleared called for " + this);
        }
        this.f = true;
    }

    public Collection<Fragment> c() {
        return this.b;
    }

    public boolean d() {
        return this.f;
    }

    public boolean e(Fragment fragment) {
        return this.b.remove(fragment);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || fd.class != obj.getClass()) {
            return false;
        }
        fd fdVar = (fd) obj;
        return this.b.equals(fdVar.b) && this.c.equals(fdVar.c) && this.d.equals(fdVar.d);
    }

    public boolean f(Fragment fragment) {
        if (this.b.contains(fragment)) {
            return this.f7590e ? this.f : !this.g;
        }
        return true;
    }

    public int hashCode() {
        return (((this.b.hashCode() * 31) + this.c.hashCode()) * 31) + this.d.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator<Fragment> it = this.b.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") Child Non Config (");
        Iterator<String> it2 = this.c.keySet().iterator();
        while (it2.hasNext()) {
            sb.append(it2.next());
            if (it2.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") ViewModelStores (");
        Iterator<String> it3 = this.d.keySet().iterator();
        while (it3.hasNext()) {
            sb.append(it3.next());
            if (it3.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(Operators.BRACKET_END);
        return sb.toString();
    }

    public fd c(Fragment fragment) {
        fd fdVar = this.c.get(fragment.mWho);
        if (fdVar != null) {
            return fdVar;
        }
        fd fdVar2 = new fd(this.f7590e);
        this.c.put(fragment.mWho, fdVar2);
        return fdVar2;
    }

    public ie d(Fragment fragment) {
        ie ieVar = this.d.get(fragment.mWho);
        if (ieVar != null) {
            return ieVar;
        }
        ie ieVar2 = new ie();
        this.d.put(fragment.mWho, ieVar2);
        return ieVar2;
    }

    public boolean a(Fragment fragment) {
        return this.b.add(fragment);
    }

    public void b(Fragment fragment) {
        if (ed.H) {
            Log.d("FragmentManager", "Clearing non-config state for " + fragment);
        }
        fd fdVar = this.c.get(fragment.mWho);
        if (fdVar != null) {
            fdVar.b();
            this.c.remove(fragment.mWho);
        }
        ie ieVar = this.d.get(fragment.mWho);
        if (ieVar != null) {
            ieVar.a();
            this.d.remove(fragment.mWho);
        }
    }
}
