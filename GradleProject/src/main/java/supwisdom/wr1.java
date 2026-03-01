package supwisdom;

import android.content.Context;
import com.taobao.weex.el.parse.Operators;
import java.util.List;
import java.util.WeakHashMap;
import net.grandcentrix.tray.core.TrayException;
import net.grandcentrix.tray.core.TrayRuntimeException;
import net.grandcentrix.tray.core.TrayStorage;
import supwisdom.bs1;

/* JADX INFO: compiled from: ContentProviderStorage.java */
/* JADX INFO: loaded from: classes3.dex */
public class wr1 extends TrayStorage {
    public final Context c;
    public final as1 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final bs1 f9653e;

    public wr1(Context context, String str, TrayStorage.Type type) {
        super(str, type);
        new WeakHashMap();
        Context applicationContext = context.getApplicationContext();
        this.c = applicationContext;
        this.f9653e = new bs1(applicationContext);
        this.d = new as1(this.c);
    }

    @Override // supwisdom.sr1
    public int getVersion() throws TrayException {
        bs1.a aVarA = this.f9653e.a();
        aVarA.a(true);
        aVarA.a(b());
        aVarA.b(a());
        aVarA.a("version");
        List<ur1> listA = this.d.a(aVarA.a());
        if (listA.size() == 0) {
            return 0;
        }
        return Integer.valueOf(listA.get(0).a()).intValue();
    }

    @Override // supwisdom.sr1
    public ur1 a(String str) {
        bs1.a aVarA = this.f9653e.a();
        aVarA.a(b());
        aVarA.b(a());
        aVarA.a(str);
        List<ur1> listB = this.d.b(aVarA.a());
        int size = listB.size();
        if (size > 1) {
            vr1.c("found more than one item for key '" + str + "' in module " + a() + ". This can be caused by using the same name for a device and user specific preference.");
            for (int i = 0; i < listB.size(); i++) {
                vr1.a("item #" + i + Operators.SPACE_STR + listB.get(i));
            }
        }
        if (size > 0) {
            return listB.get(0);
        }
        return null;
    }

    @Override // supwisdom.sr1
    public boolean a(String str, Object obj) {
        return a(str, null, obj);
    }

    public boolean a(String str, String str2, Object obj) {
        if (b() != TrayStorage.Type.UNDEFINED) {
            String strValueOf = obj == null ? null : String.valueOf(obj);
            bs1.a aVarA = this.f9653e.a();
            aVarA.a(b());
            aVarA.b(a());
            aVarA.a(str);
            return this.d.a(aVarA.a(), strValueOf, str2);
        }
        throw new TrayRuntimeException("writing data into a storage with type UNDEFINED is forbidden. Only Read and delete is allowed.");
    }

    @Override // supwisdom.sr1
    public boolean a(int i) {
        if (b() != TrayStorage.Type.UNDEFINED) {
            bs1.a aVarA = this.f9653e.a();
            aVarA.a(true);
            aVarA.a(b());
            aVarA.b(a());
            aVarA.a("version");
            return this.d.a(aVarA.a(), String.valueOf(i));
        }
        throw new TrayRuntimeException("writing data into a storage with type UNDEFINED is forbidden. Only Read and delete is allowed.");
    }
}
