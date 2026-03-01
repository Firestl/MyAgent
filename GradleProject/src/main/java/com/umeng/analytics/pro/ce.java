package com.umeng.analytics.pro;

import com.umeng.analytics.pro.co;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

/* JADX INFO: compiled from: TSerializer.java */
/* JADX INFO: loaded from: classes2.dex */
public class ce {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ByteArrayOutputStream f5224a;
    public final dg b;
    public cu c;

    public ce() {
        this(new co.a());
    }

    public byte[] a(bv bvVar) throws cb {
        this.f5224a.reset();
        bvVar.write(this.c);
        return this.f5224a.toByteArray();
    }

    public String b(bv bvVar) throws cb {
        return new String(a(bvVar));
    }

    public ce(cw cwVar) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.f5224a = byteArrayOutputStream;
        dg dgVar = new dg(byteArrayOutputStream);
        this.b = dgVar;
        this.c = cwVar.a(dgVar);
    }

    public String a(bv bvVar, String str) throws cb {
        try {
            return new String(a(bvVar), str);
        } catch (UnsupportedEncodingException unused) {
            throw new cb("JVM DOES NOT SUPPORT ENCODING: " + str);
        }
    }
}
