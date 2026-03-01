package supwisdom;

import com.synjones.mobilegroup.libofflinecodesdk.listeners.OnReceivePaySocketMessageListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class fr1 {
    public static fr1 d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Map<String, gr1> f7637a = new HashMap();
    public LinkedList<String> b = new LinkedList<>();
    public OnReceivePaySocketMessageListener c;

    public static fr1 a() {
        if (d == null) {
            synchronized (fr1.class) {
                if (d == null) {
                    d = new fr1();
                }
            }
        }
        return d;
    }

    public final void a(String str) {
        String.format("SocketManager[%s]:socketControllerMap count:%s socketCodeList count:%s", str, Integer.valueOf(this.f7637a.size()), Integer.valueOf(this.b.size()));
    }
}
