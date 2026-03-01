package io.dcloud.common.util;

import android.text.TextUtils;
import com.taobao.weex.utils.FunctionParser;
import io.dcloud.application.DCLoudApplicationImpl;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes2.dex */
public class Md5Utils {
    public static final String ALGORITHM = "MD5";
    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final char[] HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', FunctionParser.Lexer.NINE, FunctionParser.Lexer.A_UPPER, 'B', 'C', 'D', 'E', 'F'};
    public static final char[] HEX_LOWER_CASE = {'0', '1', '2', '3', '4', '5', '6', '7', '8', FunctionParser.Lexer.NINE, FunctionParser.Lexer.A_LOWER, 'b', 'c', 'd', 'e', 'f'};

    public static String md5(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            messageDigest.update(str.getBytes("UTF-8"));
            return toHex(messageDigest.digest());
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | Exception unused) {
            return str;
        }
    }

    public static String md5LowerCase(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            messageDigest.update(str.getBytes("UTF-8"));
            return toLowerCaseHex(messageDigest.digest());
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | Exception unused) {
            return str;
        }
    }

    public static String toHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            char[] cArr = HEX;
            sb.append(cArr[(b & 240) >> 4]);
            sb.append(cArr[b & 15]);
        }
        return sb.toString();
    }

    public static String toLowerCaseHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            char[] cArr = HEX_LOWER_CASE;
            sb.append(cArr[(b & 240) >> 4]);
            sb.append(cArr[b & 15]);
        }
        return sb.toString();
    }

    public static boolean verifyFileMd5(String str, String str2) {
        String strMd5;
        return str.length() > 0 && (strMd5 = md5(new File(str))) != null && str2 != null && strMd5.compareToIgnoreCase(str2) == 0;
    }

    public static String md5(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            messageDigest.update(bArr);
            return toHex(messageDigest.digest());
        } catch (NoSuchAlgorithmException unused) {
            return "";
        }
    }

    public static String md5(File file) {
        return md5(file, ALGORITHM);
    }

    public static String md5(File file, String str) throws Throwable {
        InputStream fileInputStream;
        MessageDigest messageDigest;
        byte[] bArr = new byte[1024];
        InputStream inputStream = null;
        try {
            if (FileUtil.checkPrivatePath(DCLoudApplicationImpl.self().getContext(), file.getPath())) {
                fileInputStream = new FileInputStream(file);
            } else {
                fileInputStream = FileUtil.getFileInputStream(DCLoudApplicationImpl.self().getContext(), file);
            }
        } catch (Exception e2) {
            e = e2;
            fileInputStream = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            try {
                messageDigest = MessageDigest.getInstance(str);
            } catch (Throwable th2) {
                th = th2;
                inputStream = fileInputStream;
            }
            while (true) {
                int i = fileInputStream.read(bArr);
                if (i <= 0) {
                    break;
                }
                messageDigest.update(bArr, 0, i);
                th = th2;
                inputStream = fileInputStream;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                throw th;
            }
            String hex = toHex(messageDigest.digest());
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            return hex;
        } catch (Exception e5) {
            e = e5;
            e.printStackTrace();
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            }
            return null;
        }
    }
}
