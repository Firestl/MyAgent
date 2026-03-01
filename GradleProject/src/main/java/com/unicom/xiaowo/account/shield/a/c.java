package com.unicom.xiaowo.account.shield.a;

import com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser;
import com.taobao.weex.utils.FunctionParser;

/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final char[] f5531a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', FunctionParser.Lexer.NINE, FunctionParser.Lexer.A_UPPER, 'B', 'C', 'D', 'E', 'F'};

    public static byte[] a(String str) {
        if (str == null) {
            return null;
        }
        char[] charArray = str.toCharArray();
        int length = charArray.length / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            int iDigit = Character.digit(charArray[i2 + 1], 16) | (Character.digit(charArray[i2], 16) << 4);
            if (iDigit > 127) {
                iDigit += DefaultImageHeaderParser.VP8_HEADER_MASK;
            }
            bArr[i] = (byte) iDigit;
        }
        return bArr;
    }
}
