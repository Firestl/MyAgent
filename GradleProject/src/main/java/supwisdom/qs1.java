package supwisdom;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: Dns.java */
/* JADX INFO: loaded from: classes3.dex */
public interface qs1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final qs1 f8950a = new a();

    /* JADX INFO: compiled from: Dns.java */
    public class a implements qs1 {
        @Override // supwisdom.qs1
        public List<InetAddress> lookup(String str) throws UnknownHostException {
            if (str == null) {
                throw new UnknownHostException("hostname == null");
            }
            try {
                return Arrays.asList(InetAddress.getAllByName(str));
            } catch (NullPointerException e2) {
                UnknownHostException unknownHostException = new UnknownHostException("Broken system behaviour for dns lookup of " + str);
                unknownHostException.initCause(e2);
                throw unknownHostException;
            }
        }
    }

    List<InetAddress> lookup(String str) throws UnknownHostException;
}
