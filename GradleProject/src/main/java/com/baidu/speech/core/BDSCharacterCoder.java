package com.baidu.speech.core;

import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;

/* JADX INFO: loaded from: classes.dex */
public class BDSCharacterCoder {
    public static String[] encodingNames = {"UTF-8", "GB18030"};

    public static int convertCharacterEncoding(byte[] bArr, int i, byte[] bArr2, int i2) {
        String[] strArr = encodingNames;
        if (i >= strArr.length || i2 >= strArr.length) {
            Log.e("BDSCharacterCoder", "requested source or target encoding is invalid, min: 0, max: " + encodingNames.length + " requested source: " + i + " requested target: " + i2);
            return 0;
        }
        Charset charsetForName = Charset.forName(strArr[i]);
        Charset charsetForName2 = Charset.forName(encodingNames[i2]);
        CharsetDecoder charsetDecoderNewDecoder = charsetForName.newDecoder();
        CharsetEncoder charsetEncoderNewEncoder = charsetForName2.newEncoder();
        char[] cArr = new char[bArr.length];
        CharBuffer charBufferWrap = CharBuffer.wrap(cArr);
        CoderResult coderResultDecode = charsetDecoderNewDecoder.decode(ByteBuffer.wrap(bArr), charBufferWrap, true);
        CharBuffer charBufferWrap2 = CharBuffer.wrap(cArr, 0, charBufferWrap.position());
        boolean zIsError = coderResultDecode.isError();
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr2);
        boolean z = zIsError || charsetEncoderNewEncoder.encode(charBufferWrap2, byteBufferWrap, true).isError();
        int iPosition = byteBufferWrap.position();
        return z ? iPosition * (-1) : iPosition;
    }
}
