package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.Base64;
import io.dcloud.common.util.JSUtil;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public final class JSONScanner extends JSONLexerBase {
    public static final char[] typeFieldName = (JSUtil.QUOTE + JSON.DEFAULT_TYPE_KEY + "\":\"").toCharArray();
    public final int ISO8601_LEN_0;
    public final int ISO8601_LEN_1;
    public final int ISO8601_LEN_2;
    public final String text;

    public JSONScanner(String str) {
        this(str, JSON.DEFAULT_PARSER_FEATURE);
    }

    public static boolean checkDate(char c, char c2, char c3, char c4, char c5, char c6, int i, int i2) {
        if ((c == '1' || c == '2') && c2 >= '0' && c2 <= '9' && c3 >= '0' && c3 <= '9' && c4 >= '0' && c4 <= '9') {
            if (c5 == '0') {
                if (c6 < '1' || c6 > '9') {
                    return false;
                }
            } else if (c5 != '1' || (c6 != '0' && c6 != '1' && c6 != '2')) {
                return false;
            }
            if (i == 48) {
                return i2 >= 49 && i2 <= 57;
            }
            if (i != 49 && i != 50) {
                return i == 51 && (i2 == 48 || i2 == 49);
            }
            if (i2 >= 48 && i2 <= 57) {
                return true;
            }
        }
        return false;
    }

    private boolean checkTime(char c, char c2, char c3, char c4, char c5, char c6) {
        if (c == '0') {
            if (c2 < '0' || c2 > '9') {
                return false;
            }
        } else {
            if (c != '1') {
                if (c == '2' && c2 >= '0' && c2 <= '4') {
                }
                return false;
            }
            if (c2 < '0' || c2 > '9') {
                return false;
            }
        }
        if (c3 < '0' || c3 > '5') {
            if (c3 != '6' || c4 != '0') {
                return false;
            }
        } else if (c4 < '0' || c4 > '9') {
            return false;
        }
        return (c5 < '0' || c5 > '5') ? c5 == '6' && c6 == '0' : c6 >= '0' && c6 <= '9';
    }

    private void setCalendar(char c, char c2, char c3, char c4, char c5, char c6, char c7, char c8) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        this.calendar = calendar;
        int[] iArr = JSONLexerBase.digits;
        int i = (iArr[c] * 1000) + (iArr[c2] * 100) + (iArr[c3] * 10) + iArr[c4];
        int i2 = ((iArr[c5] * 10) + iArr[c6]) - 1;
        int i3 = (iArr[c7] * 10) + iArr[c8];
        calendar.set(1, i);
        this.calendar.set(2, i2);
        this.calendar.set(5, i3);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final String addSymbol(int i, int i2, int i3, SymbolTable symbolTable) {
        return symbolTable.addSymbol(this.text, i, i2, i3);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final void arrayCopy(int i, char[] cArr, int i2, int i3) {
        this.text.getChars(i, i3 + i, cArr, i2);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public byte[] bytesValue() {
        return Base64.decodeFast(this.text, this.np + 1, this.sp);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final char charAt(int i) {
        if (i >= this.text.length()) {
            return (char) 26;
        }
        return this.text.charAt(i);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final void copyTo(int i, int i2, char[] cArr) {
        this.text.getChars(i, i2 + i, cArr, 0);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final int indexOf(char c, int i) {
        return this.text.indexOf(c, i);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public boolean isEOF() {
        if (this.bp != this.text.length()) {
            return this.ch == 26 && this.bp + 1 == this.text.length();
        }
        return true;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final char next() {
        int i = this.bp + 1;
        this.bp = i;
        char cCharAt = charAt(i);
        this.ch = cCharAt;
        return cCharAt;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final String numberString() {
        char cCharAt = charAt((this.np + this.sp) - 1);
        int i = this.sp;
        if (cCharAt == 'L' || cCharAt == 'S' || cCharAt == 'B' || cCharAt == 'F' || cCharAt == 'D') {
            i--;
        }
        return subString(this.np, i);
    }

    public boolean scanISO8601DateIfMatch() {
        return scanISO8601DateIfMatch(true);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final String stringVal() {
        return !this.hasSpecial ? subString(this.np + 1, this.sp) : new String(this.sbuf, 0, this.sp);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final String subString(int i, int i2) {
        char[] cArr = new char[i2];
        for (int i3 = i; i3 < i + i2; i3++) {
            cArr[i3 - i] = this.text.charAt(i3);
        }
        return new String(cArr);
    }

    public JSONScanner(String str, int i) {
        this.ISO8601_LEN_0 = 10;
        this.ISO8601_LEN_1 = 19;
        this.ISO8601_LEN_2 = 23;
        this.features = i;
        this.text = str;
        this.bp = -1;
        next();
        if (this.ch == 65279) {
            next();
        }
    }

    public boolean scanISO8601DateIfMatch(boolean z) {
        int i;
        int i2;
        int i3;
        int i4;
        char cCharAt;
        int i5;
        char cCharAt2;
        int i6;
        int i7;
        char cCharAt3;
        int length = this.text.length();
        int i8 = this.bp;
        int i9 = length - i8;
        if (!z && i9 > 13) {
            char cCharAt4 = charAt(i8);
            char cCharAt5 = charAt(this.bp + 1);
            char cCharAt6 = charAt(this.bp + 2);
            char cCharAt7 = charAt(this.bp + 3);
            char cCharAt8 = charAt(this.bp + 4);
            char cCharAt9 = charAt(this.bp + 5);
            char cCharAt10 = charAt((this.bp + i9) - 1);
            char cCharAt11 = charAt((this.bp + i9) - 2);
            if (cCharAt4 == '/' && cCharAt5 == 'D' && cCharAt6 == 'a' && cCharAt7 == 't' && cCharAt8 == 'e' && cCharAt9 == '(' && cCharAt10 == '/' && cCharAt11 == ')') {
                int i10 = -1;
                for (int i11 = 6; i11 < i9; i11++) {
                    char cCharAt12 = charAt(this.bp + i11);
                    if (cCharAt12 != '+') {
                        if (cCharAt12 < '0' || cCharAt12 > '9') {
                            break;
                        }
                    } else {
                        i10 = i11;
                    }
                }
                if (i10 == -1) {
                    return false;
                }
                int i12 = this.bp + 6;
                long j = Long.parseLong(subString(i12, i10 - i12));
                Calendar calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
                this.calendar = calendar;
                calendar.setTimeInMillis(j);
                this.token = 5;
                return true;
            }
        }
        if (i9 == 8 || i9 == 14 || i9 == 17) {
            int i13 = 0;
            if (z) {
                return false;
            }
            char cCharAt13 = charAt(this.bp);
            char cCharAt14 = charAt(this.bp + 1);
            char cCharAt15 = charAt(this.bp + 2);
            char cCharAt16 = charAt(this.bp + 3);
            char cCharAt17 = charAt(this.bp + 4);
            char cCharAt18 = charAt(this.bp + 5);
            char cCharAt19 = charAt(this.bp + 6);
            char cCharAt20 = charAt(this.bp + 7);
            if (!checkDate(cCharAt13, cCharAt14, cCharAt15, cCharAt16, cCharAt17, cCharAt18, cCharAt19, cCharAt20)) {
                return false;
            }
            setCalendar(cCharAt13, cCharAt14, cCharAt15, cCharAt16, cCharAt17, cCharAt18, cCharAt19, cCharAt20);
            if (i9 != 8) {
                char cCharAt21 = charAt(this.bp + 8);
                char cCharAt22 = charAt(this.bp + 9);
                char cCharAt23 = charAt(this.bp + 10);
                char cCharAt24 = charAt(this.bp + 11);
                char cCharAt25 = charAt(this.bp + 12);
                char cCharAt26 = charAt(this.bp + 13);
                if (!checkTime(cCharAt21, cCharAt22, cCharAt23, cCharAt24, cCharAt25, cCharAt26)) {
                    return false;
                }
                if (i9 == 17) {
                    char cCharAt27 = charAt(this.bp + 14);
                    char cCharAt28 = charAt(this.bp + 15);
                    char cCharAt29 = charAt(this.bp + 16);
                    if (cCharAt27 < '0' || cCharAt27 > '9' || cCharAt28 < '0' || cCharAt28 > '9' || cCharAt29 < '0' || cCharAt29 > '9') {
                        return false;
                    }
                    int[] iArr = JSONLexerBase.digits;
                    i4 = (iArr[cCharAt27] * 100) + (iArr[cCharAt28] * 10) + iArr[cCharAt29];
                } else {
                    i4 = 0;
                }
                int[] iArr2 = JSONLexerBase.digits;
                int i14 = (iArr2[cCharAt21] * 10) + iArr2[cCharAt22];
                i2 = (iArr2[cCharAt23] * 10) + iArr2[cCharAt24];
                i = iArr2[cCharAt26] + (iArr2[cCharAt25] * 10);
                i13 = i4;
                i3 = i14;
            } else {
                i = 0;
                i2 = 0;
                i3 = 0;
            }
            this.calendar.set(11, i3);
            this.calendar.set(12, i2);
            this.calendar.set(13, i);
            this.calendar.set(14, i13);
            this.token = 5;
            return true;
        }
        if (i9 < this.ISO8601_LEN_0 || charAt(this.bp + 4) != '-' || charAt(this.bp + 7) != '-') {
            return false;
        }
        char cCharAt30 = charAt(this.bp);
        char cCharAt31 = charAt(this.bp + 1);
        char cCharAt32 = charAt(this.bp + 2);
        char cCharAt33 = charAt(this.bp + 3);
        char cCharAt34 = charAt(this.bp + 5);
        char cCharAt35 = charAt(this.bp + 6);
        char cCharAt36 = charAt(this.bp + 8);
        char cCharAt37 = charAt(this.bp + 9);
        if (!checkDate(cCharAt30, cCharAt31, cCharAt32, cCharAt33, cCharAt34, cCharAt35, cCharAt36, cCharAt37)) {
            return false;
        }
        setCalendar(cCharAt30, cCharAt31, cCharAt32, cCharAt33, cCharAt34, cCharAt35, cCharAt36, cCharAt37);
        char cCharAt38 = charAt(this.bp + 10);
        if (cCharAt38 != 'T' && (cCharAt38 != ' ' || z)) {
            if (cCharAt38 != '\"' && cCharAt38 != 26) {
                return false;
            }
            this.calendar.set(11, 0);
            this.calendar.set(12, 0);
            this.calendar.set(13, 0);
            this.calendar.set(14, 0);
            int i15 = this.bp + 10;
            this.bp = i15;
            this.ch = charAt(i15);
            this.token = 5;
            return true;
        }
        if (i9 < this.ISO8601_LEN_1 || charAt(this.bp + 13) != ':' || charAt(this.bp + 16) != ':') {
            return false;
        }
        char cCharAt39 = charAt(this.bp + 11);
        char cCharAt40 = charAt(this.bp + 12);
        char cCharAt41 = charAt(this.bp + 14);
        char cCharAt42 = charAt(this.bp + 15);
        char cCharAt43 = charAt(this.bp + 17);
        char cCharAt44 = charAt(this.bp + 18);
        if (!checkTime(cCharAt39, cCharAt40, cCharAt41, cCharAt42, cCharAt43, cCharAt44)) {
            return false;
        }
        int[] iArr3 = JSONLexerBase.digits;
        int i16 = (iArr3[cCharAt39] * 10) + iArr3[cCharAt40];
        int i17 = (iArr3[cCharAt41] * 10) + iArr3[cCharAt42];
        int i18 = (iArr3[cCharAt43] * 10) + iArr3[cCharAt44];
        this.calendar.set(11, i16);
        this.calendar.set(12, i17);
        this.calendar.set(13, i18);
        if (charAt(this.bp + 19) != '.') {
            this.calendar.set(14, 0);
            int i19 = this.bp + 19;
            this.bp = i19;
            this.ch = charAt(i19);
            this.token = 5;
            return true;
        }
        if (i9 >= this.ISO8601_LEN_2 && (cCharAt = charAt(this.bp + 20)) >= '0' && cCharAt <= '9') {
            int i20 = JSONLexerBase.digits[cCharAt];
            char cCharAt45 = charAt(this.bp + 21);
            if (cCharAt45 < '0' || cCharAt45 > '9') {
                i5 = 1;
            } else {
                i20 = (i20 * 10) + JSONLexerBase.digits[cCharAt45];
                i5 = 2;
            }
            if (i5 == 2 && (cCharAt3 = charAt(this.bp + 22)) >= '0' && cCharAt3 <= '9') {
                i20 = (i20 * 10) + JSONLexerBase.digits[cCharAt3];
                i5 = 3;
            }
            this.calendar.set(14, i20);
            char cCharAt46 = charAt(this.bp + 20 + i5);
            if (cCharAt46 == '+' || cCharAt46 == '-') {
                char cCharAt47 = charAt(this.bp + 20 + i5 + 1);
                if (cCharAt47 >= '0' && cCharAt47 <= '1' && (cCharAt2 = charAt(this.bp + 20 + i5 + 2)) >= '0' && cCharAt2 <= '9') {
                    char cCharAt48 = charAt(this.bp + 20 + i5 + 3);
                    if (cCharAt48 == ':') {
                        if (charAt(this.bp + 20 + i5 + 4) != '0' || charAt(this.bp + 20 + i5 + 5) != '0') {
                            return false;
                        }
                        i6 = 6;
                    } else if (cCharAt48 != '0') {
                        i6 = 3;
                    } else {
                        if (charAt(this.bp + 20 + i5 + 4) != '0') {
                            return false;
                        }
                        i6 = 5;
                    }
                    int[] iArr4 = JSONLexerBase.digits;
                    int i21 = ((iArr4[cCharAt47] * 10) + iArr4[cCharAt2]) * 3600 * 1000;
                    if (cCharAt46 == '-') {
                        i21 = -i21;
                    }
                    if (this.calendar.getTimeZone().getRawOffset() != i21) {
                        String[] availableIDs = TimeZone.getAvailableIDs(i21);
                        if (availableIDs.length > 0) {
                            this.calendar.setTimeZone(TimeZone.getTimeZone(availableIDs[0]));
                        }
                    }
                    i7 = i6;
                }
            } else {
                i7 = 0;
            }
            int i22 = i5 + 20 + i7;
            char cCharAt49 = charAt(this.bp + i22);
            if (cCharAt49 != 26 && cCharAt49 != '\"') {
                return false;
            }
            int i23 = this.bp + i22;
            this.bp = i23;
            this.ch = charAt(i23);
            this.token = 5;
            return true;
        }
        return false;
    }

    public JSONScanner(char[] cArr, int i) {
        this(cArr, i, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONScanner(char[] cArr, int i, int i2) {
        this(new String(cArr, 0, i), i2);
    }
}
