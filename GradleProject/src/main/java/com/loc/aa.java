package com.loc;

import com.lzy.okgo.model.HttpHeaders;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: LogUpdateRequest.java */
/* JADX INFO: loaded from: classes2.dex */
public final class aa extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f3623a;
    public String b;

    public aa(byte[] bArr, String str) {
        this.b = "1";
        this.f3623a = (byte[]) bArr.clone();
        this.b = str;
    }

    @Override // com.loc.av
    public final Map<String, String> b() {
        HashMap map = new HashMap();
        map.put("Content-Type", "application/zip");
        map.put(HttpHeaders.HEAD_KEY_CONTENT_LENGTH, String.valueOf(this.f3623a.length));
        return map;
    }

    @Override // com.loc.av
    public final Map<String, String> b_() {
        return null;
    }

    @Override // com.loc.av
    public final String c() {
        String strC = u.c(v.b);
        byte[] bArrA = u.a(v.f3838a);
        byte[] bArr = new byte[bArrA.length + 50];
        System.arraycopy(this.f3623a, 0, bArr, 0, 50);
        System.arraycopy(bArrA, 0, bArr, 50, bArrA.length);
        return String.format(strC, "1", this.b, "1", "open", r.a(bArr));
    }

    @Override // com.loc.av
    public final byte[] e() {
        return this.f3623a;
    }
}
