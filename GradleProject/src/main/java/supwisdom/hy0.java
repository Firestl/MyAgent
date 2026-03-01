package supwisdom;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;

/* JADX INFO: compiled from: Network.java */
/* JADX INFO: loaded from: classes2.dex */
public final class hy0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f7887a = "";

    public static String a() {
        try {
            DatagramSocket datagramSocket = new DatagramSocket();
            datagramSocket.connect(InetAddress.getByName("114.114.114.114"), 53);
            InetAddress localAddress = datagramSocket.getLocalAddress();
            datagramSocket.close();
            return localAddress.getHostAddress();
        } catch (IOException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static synchronized boolean b() {
        String strA = a();
        if (strA.equals(f7887a)) {
            return false;
        }
        f7887a = strA;
        return true;
    }
}
