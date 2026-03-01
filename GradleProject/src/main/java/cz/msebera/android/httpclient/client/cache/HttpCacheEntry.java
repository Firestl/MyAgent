package cz.msebera.android.httpclient.client.cache;

import com.lzy.okgo.model.HttpHeaders;
import com.taobao.weex.el.parse.Operators;
import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.message.HeaderGroup;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import supwisdom.eo1;
import supwisdom.go1;
import supwisdom.xn1;
import supwisdom.yo1;

/* JADX INFO: loaded from: classes2.dex */
public class HttpCacheEntry implements Serializable {
    public static final long serialVersionUID = -6300496422359477413L;
    public final Date date;
    public final Date requestDate;
    public final Resource resource;
    public final Date responseDate;
    public final HeaderGroup responseHeaders;
    public final eo1 statusLine;
    public final Map<String, String> variantMap;

    public HttpCacheEntry(Date date, Date date2, eo1 eo1Var, xn1[] xn1VarArr, Resource resource, Map<String, String> map) {
        yo1.a(date, "Request date");
        yo1.a(date2, "Response date");
        yo1.a(eo1Var, "Status line");
        yo1.a(xn1VarArr, "Response headers");
        this.requestDate = date;
        this.responseDate = date2;
        this.statusLine = eo1Var;
        HeaderGroup headerGroup = new HeaderGroup();
        this.responseHeaders = headerGroup;
        headerGroup.setHeaders(xn1VarArr);
        this.resource = resource;
        this.variantMap = map != null ? new HashMap(map) : null;
        this.date = parseDate();
    }

    private Date parseDate() {
        xn1 firstHeader = getFirstHeader(HttpHeaders.HEAD_KEY_DATE);
        if (firstHeader == null) {
            return null;
        }
        return go1.a(firstHeader.getValue());
    }

    public xn1[] getAllHeaders() {
        return this.responseHeaders.getAllHeaders();
    }

    public Date getDate() {
        return this.date;
    }

    public xn1 getFirstHeader(String str) {
        return this.responseHeaders.getFirstHeader(str);
    }

    public xn1[] getHeaders(String str) {
        return this.responseHeaders.getHeaders(str);
    }

    public ProtocolVersion getProtocolVersion() {
        return this.statusLine.getProtocolVersion();
    }

    public String getReasonPhrase() {
        return this.statusLine.getReasonPhrase();
    }

    public Date getRequestDate() {
        return this.requestDate;
    }

    public Resource getResource() {
        return this.resource;
    }

    public Date getResponseDate() {
        return this.responseDate;
    }

    public int getStatusCode() {
        return this.statusLine.getStatusCode();
    }

    public eo1 getStatusLine() {
        return this.statusLine;
    }

    public Map<String, String> getVariantMap() {
        return Collections.unmodifiableMap(this.variantMap);
    }

    public boolean hasVariants() {
        return getFirstHeader("Vary") != null;
    }

    public String toString() {
        return "[request date=" + this.requestDate + "; response date=" + this.responseDate + "; statusLine=" + this.statusLine + Operators.ARRAY_END_STR;
    }

    public HttpCacheEntry(Date date, Date date2, eo1 eo1Var, xn1[] xn1VarArr, Resource resource) {
        this(date, date2, eo1Var, xn1VarArr, resource, new HashMap());
    }
}
