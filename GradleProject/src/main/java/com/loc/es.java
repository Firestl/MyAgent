package com.loc;

import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/* JADX INFO: compiled from: Table.java */
/* JADX INFO: loaded from: classes2.dex */
public class es {
    public static final ThreadLocal<CharsetDecoder> b = new ThreadLocal<CharsetDecoder>() { // from class: com.loc.es.1
        @Override // java.lang.ThreadLocal
        public final /* synthetic */ CharsetDecoder initialValue() {
            return Charset.forName("UTF-8").newDecoder();
        }
    };

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ThreadLocal<Charset> f3794a = new ThreadLocal<Charset>() { // from class: com.loc.es.2
        @Override // java.lang.ThreadLocal
        public final /* synthetic */ Charset initialValue() {
            return Charset.forName("UTF-8");
        }
    };
    public static final ThreadLocal<CharBuffer> c = new ThreadLocal<>();
}
