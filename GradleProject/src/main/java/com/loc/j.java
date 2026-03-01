package com.loc;

/* JADX INFO: compiled from: AMapCoreException.java */
/* JADX INFO: loaded from: classes2.dex */
public final class j extends Exception {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f3807a;
    public String b;
    public String c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f3808e;
    public int f;
    public volatile boolean g;

    public j(String str) {
        super(str);
        this.f3807a = "未知的错误";
        this.b = "";
        this.c = "";
        this.d = "1900";
        this.f3808e = "UnknownError";
        this.f = -1;
        this.g = false;
        this.f3807a = str;
        if ("IO 操作异常 - IOException".equals(str)) {
            this.f = 21;
            this.d = "1902";
            this.f3808e = "IOException";
            return;
        }
        if ("socket 连接异常 - SocketException".equals(str)) {
            this.f = 22;
            return;
        }
        if ("socket 连接超时 - SocketTimeoutException".equals(str)) {
            this.f = 23;
            this.d = "1802";
            this.f3808e = "SocketTimeoutException";
            return;
        }
        if ("无效的参数 - IllegalArgumentException".equals(str)) {
            this.f = 24;
            this.d = "1901";
            this.f3808e = "IllegalArgumentException";
            return;
        }
        if ("空指针异常 - NullPointException".equals(str)) {
            this.f = 25;
            this.d = "1903";
            this.f3808e = "NullPointException";
            return;
        }
        if ("url异常 - MalformedURLException".equals(str)) {
            this.f = 26;
            this.d = "1803";
            this.f3808e = "MalformedURLException";
            return;
        }
        if ("未知主机 - UnKnowHostException".equals(str)) {
            this.f = 27;
            this.d = "1804";
            this.f3808e = "UnknownHostException";
            return;
        }
        if ("服务器连接失败 - UnknownServiceException".equals(str)) {
            this.f = 28;
            this.d = "1805";
            this.f3808e = "CannotConnectToHostException";
            return;
        }
        if ("协议解析错误 - ProtocolException".equals(str)) {
            this.f = 29;
            this.d = "1801";
            this.f3808e = "ProtocolException";
            return;
        }
        if ("http连接失败 - ConnectionException".equals(str)) {
            this.f = 30;
            this.d = "1806";
            this.f3808e = "ConnectionException";
            return;
        }
        if ("服务QPS超限".equalsIgnoreCase(str)) {
            this.f = 30;
            this.d = "2001";
            this.f3808e = "ConnectionException";
            return;
        }
        if ("未知的错误".equals(str)) {
            this.f = 31;
            return;
        }
        if ("key鉴权失败".equals(str)) {
            this.f = 32;
            return;
        }
        if ("requeust is null".equals(str)) {
            this.f = 1;
            return;
        }
        if ("request url is empty".equals(str)) {
            this.f = 2;
            return;
        }
        if ("response is null".equals(str)) {
            this.f = 3;
            return;
        }
        if ("thread pool has exception".equals(str)) {
            this.f = 4;
            return;
        }
        if ("sdk name is invalid".equals(str)) {
            this.f = 5;
            return;
        }
        if ("sdk info is null".equals(str)) {
            this.f = 6;
            return;
        }
        if ("sdk packages is null".equals(str)) {
            this.f = 7;
            return;
        }
        if ("线程池为空".equals(str)) {
            this.f = 8;
        } else if ("获取对象错误".equals(str)) {
            this.f = 101;
        } else {
            this.f = -1;
        }
    }

    public j(String str, String str2, String str3) {
        this(str);
        this.b = str2;
        this.c = str3;
    }

    public final String a() {
        return this.f3807a;
    }

    public final void a(int i) {
        this.f = i;
    }

    public final String b() {
        return this.d;
    }

    public final String c() {
        return this.f3808e;
    }

    public final String d() {
        return this.b;
    }

    public final String e() {
        return this.c;
    }

    public final int f() {
        return this.f;
    }

    public final boolean g() {
        return this.g;
    }

    public final void h() {
        this.g = true;
    }
}
