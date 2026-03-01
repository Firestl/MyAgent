package cz.msebera.android.httpclient.impl.cookie;

import com.lzy.okgo.model.HttpHeaders;
import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import supwisdom.io1;
import supwisdom.lo1;
import supwisdom.yo1;

/* JADX INFO: loaded from: classes2.dex */
public class BasicClientCookie implements lo1, io1, Cloneable, Serializable {
    public static final long serialVersionUID = -3869795591041535538L;
    public Map<String, String> attribs;
    public String cookieComment;
    public String cookieDomain;
    public Date cookieExpiryDate;
    public String cookiePath;
    public int cookieVersion;
    public boolean isSecure;
    public final String name;
    public String value;

    public BasicClientCookie(String str, String str2) {
        yo1.a(str, "Name");
        this.name = str;
        this.attribs = new HashMap();
        this.value = str2;
    }

    public Object clone() throws CloneNotSupportedException {
        BasicClientCookie basicClientCookie = (BasicClientCookie) super.clone();
        basicClientCookie.attribs = new HashMap(this.attribs);
        return basicClientCookie;
    }

    public boolean containsAttribute(String str) {
        return this.attribs.get(str) != null;
    }

    public String getAttribute(String str) {
        return this.attribs.get(str);
    }

    @Override // supwisdom.jo1
    public String getComment() {
        return this.cookieComment;
    }

    public String getCommentURL() {
        return null;
    }

    @Override // supwisdom.jo1
    public String getDomain() {
        return this.cookieDomain;
    }

    @Override // supwisdom.jo1
    public Date getExpiryDate() {
        return this.cookieExpiryDate;
    }

    @Override // supwisdom.jo1
    public String getName() {
        return this.name;
    }

    @Override // supwisdom.jo1
    public String getPath() {
        return this.cookiePath;
    }

    public int[] getPorts() {
        return null;
    }

    @Override // supwisdom.jo1
    public String getValue() {
        return this.value;
    }

    @Override // supwisdom.jo1
    public int getVersion() {
        return this.cookieVersion;
    }

    @Override // supwisdom.jo1
    public boolean isExpired(Date date) {
        yo1.a(date, HttpHeaders.HEAD_KEY_DATE);
        Date date2 = this.cookieExpiryDate;
        return date2 != null && date2.getTime() <= date.getTime();
    }

    public boolean isPersistent() {
        return this.cookieExpiryDate != null;
    }

    @Override // supwisdom.jo1
    public boolean isSecure() {
        return this.isSecure;
    }

    public void setAttribute(String str, String str2) {
        this.attribs.put(str, str2);
    }

    public void setComment(String str) {
        this.cookieComment = str;
    }

    public void setDomain(String str) {
        if (str != null) {
            this.cookieDomain = str.toLowerCase(Locale.ENGLISH);
        } else {
            this.cookieDomain = null;
        }
    }

    public void setExpiryDate(Date date) {
        this.cookieExpiryDate = date;
    }

    public void setPath(String str) {
        this.cookiePath = str;
    }

    public void setSecure(boolean z) {
        this.isSecure = z;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public void setVersion(int i) {
        this.cookieVersion = i;
    }

    public String toString() {
        return "[version: " + Integer.toString(this.cookieVersion) + Operators.ARRAY_END_STR + "[name: " + this.name + Operators.ARRAY_END_STR + "[value: " + this.value + Operators.ARRAY_END_STR + "[domain: " + this.cookieDomain + Operators.ARRAY_END_STR + "[path: " + this.cookiePath + Operators.ARRAY_END_STR + "[expiry: " + this.cookieExpiryDate + Operators.ARRAY_END_STR;
    }
}
