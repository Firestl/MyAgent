package org.bouncycastle.pqc.crypto.xmss;

import org.bouncycastle.util.Pack;

/* JADX INFO: loaded from: classes3.dex */
public abstract class XMSSAddress {
    public final int keyAndMask;
    public final int layerAddress;
    public final long treeAddress;
    public final int type;

    public static abstract class Builder<T extends Builder> {
        public final int type;
        public int layerAddress = 0;
        public long treeAddress = 0;
        public int keyAndMask = 0;

        public Builder(int i) {
            this.type = i;
        }

        public abstract XMSSAddress build();

        public abstract T getThis();

        public T withKeyAndMask(int i) {
            this.keyAndMask = i;
            return (T) getThis();
        }

        public T withLayerAddress(int i) {
            this.layerAddress = i;
            return (T) getThis();
        }

        public T withTreeAddress(long j) {
            this.treeAddress = j;
            return (T) getThis();
        }
    }

    public XMSSAddress(Builder builder) {
        this.layerAddress = builder.layerAddress;
        this.treeAddress = builder.treeAddress;
        this.type = builder.type;
        this.keyAndMask = builder.keyAndMask;
    }

    public final int getKeyAndMask() {
        return this.keyAndMask;
    }

    public final int getLayerAddress() {
        return this.layerAddress;
    }

    public final long getTreeAddress() {
        return this.treeAddress;
    }

    public final int getType() {
        return this.type;
    }

    public byte[] toByteArray() {
        byte[] bArr = new byte[32];
        Pack.intToBigEndian(this.layerAddress, bArr, 0);
        Pack.longToBigEndian(this.treeAddress, bArr, 4);
        Pack.intToBigEndian(this.type, bArr, 12);
        Pack.intToBigEndian(this.keyAndMask, bArr, 28);
        return bArr;
    }
}
