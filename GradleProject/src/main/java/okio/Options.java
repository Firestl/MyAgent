package okio;

import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes3.dex */
public final class Options extends AbstractList<ByteString> implements RandomAccess {
    public final ByteString[] byteStrings;
    public final int[] trie;

    public Options(ByteString[] byteStringArr, int[] iArr) {
        this.byteStrings = byteStringArr;
        this.trie = iArr;
    }

    public static void buildTrieRecursive(long j, Buffer buffer, int i, List<ByteString> list, int i2, int i3, List<Integer> list2) {
        int i4;
        int i5;
        int i6;
        Buffer buffer2;
        int i7 = i2;
        if (i7 >= i3) {
            throw new AssertionError();
        }
        for (int i8 = i7; i8 < i3; i8++) {
            if (list.get(i8).size() < i) {
                throw new AssertionError();
            }
        }
        ByteString byteString = list.get(i2);
        ByteString byteString2 = list.get(i3 - 1);
        int iIntValue = -1;
        if (i == byteString.size()) {
            iIntValue = list2.get(i7).intValue();
            i7++;
            byteString = list.get(i7);
        }
        int i9 = i7;
        if (byteString.getByte(i) == byteString2.getByte(i)) {
            int i10 = 0;
            int iMin = Math.min(byteString.size(), byteString2.size());
            for (int i11 = i; i11 < iMin && byteString.getByte(i11) == byteString2.getByte(i11); i11++) {
                i10++;
            }
            long jIntCount = 1 + j + ((long) intCount(buffer)) + 2 + ((long) i10);
            buffer.writeInt(-i10);
            buffer.writeInt(iIntValue);
            int i12 = i;
            while (true) {
                i4 = i + i10;
                if (i12 >= i4) {
                    break;
                }
                buffer.writeInt(byteString.getByte(i12) & 255);
                i12++;
            }
            if (i9 + 1 == i3) {
                if (i4 != list.get(i9).size()) {
                    throw new AssertionError();
                }
                buffer.writeInt(list2.get(i9).intValue());
                return;
            } else {
                Buffer buffer3 = new Buffer();
                buffer.writeInt((int) ((((long) intCount(buffer3)) + jIntCount) * (-1)));
                buildTrieRecursive(jIntCount, buffer3, i4, list, i9, i3, list2);
                buffer.write(buffer3, buffer3.size());
                return;
            }
        }
        int i13 = 1;
        for (int i14 = i9 + 1; i14 < i3; i14++) {
            if (list.get(i14 - 1).getByte(i) != list.get(i14).getByte(i)) {
                i13++;
            }
        }
        long jIntCount2 = j + ((long) intCount(buffer)) + 2 + ((long) (i13 * 2));
        buffer.writeInt(i13);
        buffer.writeInt(iIntValue);
        for (int i15 = i9; i15 < i3; i15++) {
            byte b = list.get(i15).getByte(i);
            if (i15 == i9 || b != list.get(i15 - 1).getByte(i)) {
                buffer.writeInt(b & 255);
            }
        }
        Buffer buffer4 = new Buffer();
        int i16 = i9;
        while (i16 < i3) {
            byte b2 = list.get(i16).getByte(i);
            int i17 = i16 + 1;
            int i18 = i17;
            while (true) {
                if (i18 >= i3) {
                    i5 = i3;
                    break;
                } else {
                    if (b2 != list.get(i18).getByte(i)) {
                        i5 = i18;
                        break;
                    }
                    i18++;
                }
            }
            if (i17 == i5 && i + 1 == list.get(i16).size()) {
                buffer.writeInt(list2.get(i16).intValue());
                i6 = i5;
                buffer2 = buffer4;
            } else {
                buffer.writeInt((int) ((((long) intCount(buffer4)) + jIntCount2) * (-1)));
                i6 = i5;
                buffer2 = buffer4;
                buildTrieRecursive(jIntCount2, buffer4, i + 1, list, i16, i5, list2);
            }
            buffer4 = buffer2;
            i16 = i6;
        }
        Buffer buffer5 = buffer4;
        buffer.write(buffer5, buffer5.size());
    }

    public static int intCount(Buffer buffer) {
        return (int) (buffer.size() / 4);
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x00bc, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static okio.Options of(okio.ByteString... r10) {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Options.of(okio.ByteString[]):okio.Options");
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.byteStrings.length;
    }

    @Override // java.util.AbstractList, java.util.List
    public ByteString get(int i) {
        return this.byteStrings[i];
    }
}
