package supwisdom;

import com.huawei.hms.framework.common.ContainerUtils;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.taobao.weex.el.parse.Operators;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import supwisdom.bt1;
import supwisdom.ss1;
import supwisdom.us1;
import supwisdom.ys1;

/* JADX INFO: compiled from: HttpUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class dw0 {
    public static String a(String str, Map<String, List<String>> map) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            if (str.indexOf(38) > 0 || str.indexOf(63) > 0) {
                sb.append("&");
            } else {
                sb.append(Operators.CONDITION_IF_STRING);
            }
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                Iterator<String> it = entry.getValue().iterator();
                while (it.hasNext()) {
                    String strEncode = URLEncoder.encode(it.next(), "UTF-8");
                    sb.append(entry.getKey());
                    sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
                    sb.append(strEncode);
                    sb.append("&");
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        } catch (UnsupportedEncodingException e2) {
            fw0.a(e2);
            return str;
        }
    }

    public static bt1.a a(bt1.a aVar, HttpHeaders httpHeaders) {
        if (httpHeaders.headersMap.isEmpty()) {
            return aVar;
        }
        us1.a aVar2 = new us1.a();
        try {
            for (Map.Entry<String, String> entry : httpHeaders.headersMap.entrySet()) {
                aVar2.a(entry.getKey(), entry.getValue());
            }
        } catch (Exception e2) {
            fw0.a(e2);
        }
        aVar.a(aVar2.a());
        return aVar;
    }

    public static ct1 a(HttpParams httpParams, boolean z) {
        if (httpParams.fileParamsMap.isEmpty() && !z) {
            ss1.a aVar = new ss1.a();
            for (String str : httpParams.urlParamsMap.keySet()) {
                Iterator<String> it = httpParams.urlParamsMap.get(str).iterator();
                while (it.hasNext()) {
                    aVar.a(str, it.next());
                }
            }
            return aVar.a();
        }
        ys1.a aVar2 = new ys1.a();
        aVar2.a(ys1.f);
        if (!httpParams.urlParamsMap.isEmpty()) {
            for (Map.Entry<String, List<String>> entry : httpParams.urlParamsMap.entrySet()) {
                Iterator<String> it2 = entry.getValue().iterator();
                while (it2.hasNext()) {
                    aVar2.a(entry.getKey(), it2.next());
                }
            }
        }
        for (Map.Entry<String, List<HttpParams.FileWrapper>> entry2 : httpParams.fileParamsMap.entrySet()) {
            for (HttpParams.FileWrapper fileWrapper : entry2.getValue()) {
                aVar2.a(entry2.getKey(), fileWrapper.fileName, ct1.create(fileWrapper.contentType, fileWrapper.file));
            }
        }
        return aVar2.a();
    }

    public static xs1 a(String str) {
        String contentTypeFor = URLConnection.getFileNameMap().getContentTypeFor(str.replace("#", ""));
        if (contentTypeFor == null) {
            return HttpParams.MEDIA_TYPE_STREAM;
        }
        return xs1.b(contentTypeFor);
    }

    public static <T> T a(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static void a(Runnable runnable) {
        fv0.i().f().post(runnable);
    }
}
