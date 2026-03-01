package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;

/* JADX INFO: loaded from: classes.dex */
public class SymbolTable {
    public static final int DEFAULT_TABLE_SIZE = 512;
    public static final int MAX_BUCKET_LENTH = 8;
    public static final int MAX_SIZE = 4096;
    public final Entry[] buckets;
    public final int indexMask;
    public int size;
    public final String[] symbols;
    public final char[][] symbols_char;

    public SymbolTable() {
        this(512);
        addSymbol("$ref", 0, 4, 1185263);
        String str = JSON.DEFAULT_TYPE_KEY;
        addSymbol(str, 0, 5, str.hashCode());
    }

    public static final int hash(char[] cArr, int i, int i2) {
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            i4 = (i4 * 31) + cArr[i];
            i3++;
            i++;
        }
        return i4;
    }

    public static String subString(String str, int i, int i2) {
        char[] cArr = new char[i2];
        for (int i3 = i; i3 < i + i2; i3++) {
            cArr[i3 - i] = str.charAt(i3);
        }
        return new String(cArr);
    }

    public String addSymbol(char[] cArr, int i, int i2) {
        return addSymbol(cArr, i, i2, hash(cArr, i, i2));
    }

    public int size() {
        return this.size;
    }

    public String addSymbol(char[] cArr, int i, int i2, int i3) {
        boolean z;
        boolean z2;
        int i4 = this.indexMask & i3;
        String str = this.symbols[i4];
        if (str == null) {
            z = true;
        } else if (str.length() == i2) {
            char[] cArr2 = this.symbols_char[i4];
            int i5 = 0;
            while (true) {
                if (i5 >= i2) {
                    z = true;
                    break;
                }
                if (cArr[i + i5] != cArr2[i5]) {
                    z = false;
                    break;
                }
                i5++;
            }
            if (z) {
                return str;
            }
        } else {
            z = false;
        }
        int i6 = 0;
        for (Entry entry = this.buckets[i4]; entry != null; entry = entry.next) {
            char[] cArr3 = entry.characters;
            if (i2 == cArr3.length && i3 == entry.hashCode) {
                int i7 = 0;
                while (true) {
                    if (i7 >= i2) {
                        z2 = true;
                        break;
                    }
                    if (cArr[i + i7] != cArr3[i7]) {
                        z2 = false;
                        break;
                    }
                    i7++;
                }
                if (z2) {
                    return entry.symbol;
                }
                i6++;
            }
        }
        if (i6 >= 8) {
            return new String(cArr, i, i2);
        }
        if (this.size >= 4096) {
            return new String(cArr, i, i2);
        }
        Entry entry2 = new Entry(cArr, i, i2, i3, this.buckets[i4]);
        this.buckets[i4] = entry2;
        if (z) {
            this.symbols[i4] = entry2.symbol;
            this.symbols_char[i4] = entry2.characters;
        }
        this.size++;
        return entry2.symbol;
    }

    public SymbolTable(int i) {
        this.size = 0;
        this.indexMask = i - 1;
        this.buckets = new Entry[i];
        this.symbols = new String[i];
        this.symbols_char = new char[i][];
    }

    public static final class Entry {
        public final byte[] bytes;
        public final char[] characters;
        public final int hashCode;
        public Entry next;
        public final String symbol;

        public Entry(char[] cArr, int i, int i2, int i3, Entry entry) {
            char[] cArr2 = new char[i2];
            this.characters = cArr2;
            System.arraycopy(cArr, i, cArr2, 0, i2);
            this.symbol = new String(this.characters).intern();
            this.next = entry;
            this.hashCode = i3;
            this.bytes = null;
        }

        public Entry(String str, int i, int i2, int i3, Entry entry) {
            String strIntern = SymbolTable.subString(str, i, i2).intern();
            this.symbol = strIntern;
            this.characters = strIntern.toCharArray();
            this.next = entry;
            this.hashCode = i3;
            this.bytes = null;
        }
    }

    public String addSymbol(String str, int i, int i2, int i3) {
        boolean z;
        boolean z2;
        int i4 = this.indexMask & i3;
        String str2 = this.symbols[i4];
        if (str2 == null) {
            z = true;
        } else if (str2.length() == i2) {
            char[] cArr = this.symbols_char[i4];
            int i5 = 0;
            while (true) {
                if (i5 >= i2) {
                    z = true;
                    break;
                }
                if (str.charAt(i + i5) != cArr[i5]) {
                    z = false;
                    break;
                }
                i5++;
            }
            if (z) {
                return str2;
            }
        } else {
            z = false;
        }
        int i6 = 0;
        for (Entry entry = this.buckets[i4]; entry != null; entry = entry.next) {
            char[] cArr2 = entry.characters;
            if (i2 == cArr2.length && i3 == entry.hashCode) {
                int i7 = 0;
                while (true) {
                    if (i7 >= i2) {
                        z2 = true;
                        break;
                    }
                    if (str.charAt(i + i7) != cArr2[i7]) {
                        z2 = false;
                        break;
                    }
                    i7++;
                }
                if (z2) {
                    return entry.symbol;
                }
                i6++;
            }
        }
        if (i6 >= 8) {
            return subString(str, i, i2);
        }
        if (this.size >= 4096) {
            return subString(str, i, i2);
        }
        Entry entry2 = new Entry(str, i, i2, i3, this.buckets[i4]);
        this.buckets[i4] = entry2;
        if (z) {
            this.symbols[i4] = entry2.symbol;
            this.symbols_char[i4] = entry2.characters;
        }
        this.size++;
        return entry2.symbol;
    }
}
