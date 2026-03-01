package supwisdom;

import com.squareup.okhttp.Protocol;
import java.net.Proxy;
import java.net.URL;

/* JADX INFO: compiled from: RequestLine.java */
/* JADX INFO: loaded from: classes2.dex */
public final class tf1 {
    public static String a(se1 se1Var, Proxy.Type type, Protocol protocol) {
        StringBuilder sb = new StringBuilder();
        sb.append(se1Var.e());
        sb.append(' ');
        if (a(se1Var, type)) {
            sb.append(se1Var.h());
        } else {
            sb.append(a(se1Var.h()));
        }
        sb.append(' ');
        sb.append(a(protocol));
        return sb.toString();
    }

    public static boolean a(se1 se1Var, Proxy.Type type) {
        return !se1Var.d() && type == Proxy.Type.HTTP;
    }

    public static String a(URL url) {
        String file = url.getFile();
        if (file == null) {
            return "/";
        }
        if (file.startsWith("/")) {
            return file;
        }
        return "/" + file;
    }

    public static String a(Protocol protocol) {
        return protocol == Protocol.HTTP_1_0 ? "HTTP/1.0" : "HTTP/1.1";
    }
}
