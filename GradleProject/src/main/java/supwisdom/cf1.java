package supwisdom;

import java.net.InetAddress;
import java.net.UnknownHostException;

/* JADX INFO: compiled from: Network.java */
/* JADX INFO: loaded from: classes2.dex */
public interface cf1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final cf1 f7203a = new a();

    /* JADX INFO: compiled from: Network.java */
    public static class a implements cf1 {
        @Override // supwisdom.cf1
        public InetAddress[] a(String str) throws UnknownHostException {
            if (str != null) {
                return InetAddress.getAllByName(str);
            }
            throw new UnknownHostException("host == null");
        }
    }

    InetAddress[] a(String str) throws UnknownHostException;
}
