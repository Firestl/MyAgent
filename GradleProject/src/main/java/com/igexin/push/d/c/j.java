package com.igexin.push.d.c;

/* JADX INFO: loaded from: classes2.dex */
public final class j extends c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte f3532a;
    public Object b;

    @Override // com.igexin.push.d.c.c
    public final void a(byte[] bArr) {
    }

    @Override // com.igexin.push.d.c.c
    public final byte[] b() {
        byte b = this.f3532a;
        byte[] bytes = (b == 1 || b == 2 || (b != 3 && (b == 4 || b == 5 || b == 6 || b == 7))) ? ((String) this.b).getBytes() : null;
        if (bytes == null) {
            return null;
        }
        byte[] bArr = new byte[bytes.length + 2];
        bArr[0] = this.f3532a;
        bArr[1] = (byte) bytes.length;
        System.arraycopy(bytes, 0, bArr, 2, bytes.length);
        return bArr;
    }
}
