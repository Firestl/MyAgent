package com.supwisdom.superapp.util;

import com.taobao.weex.utils.FunctionParser;
import io.dcloud.common.util.Md5Utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes2.dex */
public class HashUtil {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f4395a = "MD5";
    public static String b = "SHA1";
    public static final char[] c = {'0', '1', '2', '3', '4', '5', '6', '7', '8', FunctionParser.Lexer.NINE, FunctionParser.Lexer.A_LOWER, 'b', 'c', 'd', 'e', 'f'};

    public enum HashType {
        MD5(HashUtil.f4395a),
        SHA1(HashUtil.b);

        public String str;

        HashType(String str) {
            this.str = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.str;
        }
    }

    public static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            sb.append(Character.forDigit((bArr[i] & 240) >> 4, 16));
            sb.append(Character.forDigit(bArr[i] & 15, 16));
        }
        return sb.toString();
    }

    public static String a(byte[] bArr, String str) {
        byte[] bArrDigest;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str.toString());
            messageDigest.update(bArr);
            bArrDigest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            bArrDigest = null;
        }
        return a(bArrDigest);
    }

    public static String a(String str, String str2) {
        return a(str.getBytes(Charset.forName("UTF-8")), str2);
    }

    public static String a(File file) throws Throwable {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException unused) {
        } catch (IOException unused2) {
        } catch (Throwable th) {
            th = th;
        }
        try {
            String strA = a(fileInputStream.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, file.length()));
            try {
                fileInputStream.close();
            } catch (IOException unused3) {
            }
            return strA;
        } catch (FileNotFoundException unused4) {
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (IOException unused5) {
                }
            }
            return "";
        } catch (IOException unused6) {
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (IOException unused7) {
                }
            }
            return "";
        } catch (Throwable th2) {
            th = th2;
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (IOException unused8) {
                }
            }
            throw th;
        }
    }

    public static String a(ByteBuffer byteBuffer) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(Md5Utils.ALGORITHM);
            messageDigest.update(byteBuffer);
            byte[] bArrDigest = messageDigest.digest();
            char[] cArr = new char[32];
            int i = 0;
            for (int i2 = 0; i2 < 16; i2++) {
                byte b2 = bArrDigest[i2];
                int i3 = i + 1;
                cArr[i] = c[(b2 >>> 4) & 15];
                i = i3 + 1;
                cArr[i3] = c[b2 & 15];
            }
            return new String(cArr);
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return "";
        }
    }
}
