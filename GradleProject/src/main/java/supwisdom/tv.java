package supwisdom;

/* JADX INFO: loaded from: classes.dex */
public final class tv extends uv {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final uv f9322a;

    public tv(uv uvVar) {
        super(uvVar.getWidth(), uvVar.getHeight());
        this.f9322a = uvVar;
    }

    @Override // supwisdom.uv
    public uv crop(int i, int i2, int i3, int i4) {
        return new tv(this.f9322a.crop(i, i2, i3, i4));
    }

    @Override // supwisdom.uv
    public byte[] getMatrix() {
        byte[] matrix = this.f9322a.getMatrix();
        int width = getWidth() * getHeight();
        byte[] bArr = new byte[width];
        for (int i = 0; i < width; i++) {
            bArr[i] = (byte) (255 - (matrix[i] & 255));
        }
        return bArr;
    }

    @Override // supwisdom.uv
    public byte[] getRow(int i, byte[] bArr) {
        byte[] row = this.f9322a.getRow(i, bArr);
        int width = getWidth();
        for (int i2 = 0; i2 < width; i2++) {
            row[i2] = (byte) (255 - (row[i2] & 255));
        }
        return row;
    }

    @Override // supwisdom.uv
    public uv invert() {
        return this.f9322a;
    }

    @Override // supwisdom.uv
    public boolean isCropSupported() {
        return this.f9322a.isCropSupported();
    }

    @Override // supwisdom.uv
    public boolean isRotateSupported() {
        return this.f9322a.isRotateSupported();
    }

    @Override // supwisdom.uv
    public uv rotateCounterClockwise() {
        return new tv(this.f9322a.rotateCounterClockwise());
    }

    @Override // supwisdom.uv
    public uv rotateCounterClockwise45() {
        return new tv(this.f9322a.rotateCounterClockwise45());
    }
}
