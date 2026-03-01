package com.zx.a.I8b7;

import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: classes2.dex */
public class v1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final r f6296a;
    public final List<c0> b;
    public final SSLSocketFactory c;
    public final HostnameVerifier d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f6297e;
    public final int f;
    public final int g;

    public static final class a {
        public SSLSocketFactory c;
        public final List<c0> b = new ArrayList();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public r f6298a = new r();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f6299e = true;
        public int f = 7000;
        public int g = 7000;
        public HostnameVerifier d = u1.f6289a;
    }

    public v1(a aVar) {
        this.f6296a = aVar.f6298a;
        List<c0> listA = k1.a(aVar.b);
        this.b = listA;
        this.c = aVar.c;
        this.d = aVar.d;
        this.f6297e = aVar.f6299e;
        this.f = aVar.f;
        this.g = aVar.g;
        if (listA.contains(null)) {
            throw new IllegalStateException("Null interceptor: " + listA);
        }
    }
}
