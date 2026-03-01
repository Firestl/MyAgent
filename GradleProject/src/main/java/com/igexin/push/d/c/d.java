package com.igexin.push.d.c;

/* JADX INFO: loaded from: classes2.dex */
public final class d extends c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f3522a = 6;
    public String b;
    public String c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f3523e;

    public d() {
        this.m = 6;
        this.n = (byte) 20;
        this.b = "";
        this.c = "";
        this.d = "";
        this.f3523e = "";
    }

    public d(String str, String str2, String str3, String str4) {
        this.m = 6;
        this.n = (byte) 20;
        this.b = str == null ? "" : str;
        this.c = str2 == null ? "" : str2;
        this.d = str3 == null ? "" : str3;
        this.f3523e = str4 == null ? "" : str4;
    }

    private String d() {
        return this.d;
    }

    @Override // com.igexin.push.d.c.c, com.igexin.c.a.d.a.a
    public final void a() {
        this.b = null;
        this.c = null;
        this.d = null;
        this.f3523e = null;
    }

    @Override // com.igexin.push.d.c.c
    public final void a(byte[] bArr) {
        try {
            int i = bArr[0] & 255;
            this.b = new String(bArr, 1, i, "utf-8");
            int i2 = i + 1;
            int i3 = bArr[i2] & 255;
            int i4 = i2 + 1;
            this.c = new String(bArr, i4, i3, "utf-8");
            int i5 = i4 + i3;
            int i6 = bArr[i5] & 255;
            int i7 = i5 + 1;
            this.d = new String(bArr, i7, i6, "utf-8");
            int i8 = i7 + i6;
            this.f3523e = new String(bArr, i8 + 1, bArr[i8] & 255, "utf-8");
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
        }
    }

    @Override // com.igexin.push.d.c.c
    public final byte[] b() {
        byte[] bytes = this.c.getBytes();
        byte[] bytes2 = this.b.getBytes();
        byte[] bytes3 = this.d.getBytes();
        byte[] bytes4 = this.f3523e.getBytes();
        byte[] bArr = new byte[bytes.length + bytes2.length + bytes3.length + bytes4.length + 4];
        bArr[0] = (byte) bytes.length;
        System.arraycopy(bytes, 0, bArr, 1, bytes.length);
        int length = bytes.length + 1;
        int i = length + 1;
        bArr[length] = (byte) bytes2.length;
        System.arraycopy(bytes2, 0, bArr, i, bytes2.length);
        int length2 = i + bytes2.length;
        int i2 = length2 + 1;
        bArr[length2] = (byte) bytes3.length;
        System.arraycopy(bytes3, 0, bArr, i2, bytes3.length);
        int length3 = i2 + bytes3.length;
        bArr[length3] = (byte) bytes4.length;
        System.arraycopy(bytes4, 0, bArr, length3 + 1, bytes4.length);
        return bArr;
    }
}
