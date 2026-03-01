package io.dcloud.common.util;

import android.text.TextUtils;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.g.a;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.Character;

/* JADX INFO: loaded from: classes2.dex */
public class DataUtil {
    public static String GBK2Unicode(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (isNeedConvert(cCharAt)) {
                stringBuffer.append("\\u" + Integer.toHexString(cCharAt));
            } else {
                stringBuffer.append(cCharAt);
            }
        }
        return stringBuffer.toString();
    }

    public static String Unicode2GBK(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = str.length();
        int i = 0;
        while (i < length) {
            if (i < length - 1) {
                int i2 = i + 2;
                if ("\\u".equals(str.substring(i, i2))) {
                    i += 6;
                    stringBuffer.append((char) Integer.parseInt(str.substring(i2, i), 16));
                }
            }
            stringBuffer.append(str.charAt(i));
            i++;
        }
        return stringBuffer.toString();
    }

    public static void datToJsString(final String str, final ICallBack iCallBack) {
        ThreadPool.self().addThreadTask(new Runnable() { // from class: io.dcloud.common.util.DataUtil.1
            @Override // java.lang.Runnable
            public void run() {
                String strDatToJsStringSync = DataUtil.datToJsStringSync(str);
                if (TextUtils.isEmpty(strDatToJsStringSync)) {
                    return;
                }
                iCallBack.onCallBack(0, strDatToJsStringSync);
            }
        }, true);
    }

    public static String datToJsStringSync(String str) {
        InputStream fileInputStream;
        File file = new File(DeviceInfo.sBaseFsRootPath + File.separator + str);
        boolean z = true;
        if (file.exists()) {
            try {
                fileInputStream = new FileInputStream(file);
                z = false;
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
                fileInputStream = null;
            }
        } else {
            fileInputStream = PlatformUtil.getResInputStream(str);
        }
        boolean z2 = str.equals(BaseInfo.sUniNViewServiceJsPath) ? false : z;
        if (fileInputStream != null) {
            try {
                if (!z2) {
                    return new String(IOUtil.getBytes(fileInputStream));
                }
                if (!TextUtils.isEmpty(a.b())) {
                    return AESUtil.decrypt(a.b(), a.a(), IOUtil.getBytes(fileInputStream));
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        return null;
    }

    public static boolean isNeedConvert(char c) {
        return (c & 255) != c;
    }

    public static String unicodeToUtf8(String str) {
        if (str == null) {
            return "";
        }
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(length);
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            char cCharAt = str.charAt(i);
            if (cCharAt == '\\') {
                i = i2 + 1;
                char cCharAt2 = str.charAt(i2);
                if (cCharAt2 == 'u') {
                    int i3 = 0;
                    int i4 = 0;
                    while (i3 < 4) {
                        int i5 = i + 1;
                        char cCharAt3 = str.charAt(i);
                        switch (cCharAt3) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                i4 = ((i4 << 4) + cCharAt3) - 48;
                                break;
                            default:
                                switch (cCharAt3) {
                                    case 'A':
                                    case 'B':
                                    case 'C':
                                    case 'D':
                                    case 'E':
                                    case 'F':
                                        i4 = (((i4 << 4) + 10) + cCharAt3) - 65;
                                        break;
                                    default:
                                        switch (cCharAt3) {
                                            case 'a':
                                            case 'b':
                                            case 'c':
                                            case 'd':
                                            case 'e':
                                            case 'f':
                                                i4 = (((i4 << 4) + 10) + cCharAt3) - 97;
                                                break;
                                            default:
                                                throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
                                        }
                                        break;
                                }
                                break;
                        }
                        i3++;
                        i = i5;
                    }
                    stringBuffer.append((char) i4);
                } else {
                    if (cCharAt2 == 't') {
                        cCharAt2 = 't';
                    } else if (cCharAt2 == 'r') {
                        cCharAt2 = 'r';
                    } else if (cCharAt2 == 'n') {
                        cCharAt2 = 'n';
                    } else if (cCharAt2 == 'f') {
                        cCharAt2 = 'f';
                    }
                    stringBuffer.append(cCharAt2);
                }
            } else {
                stringBuffer.append(cCharAt);
                i = i2;
            }
        }
        return stringBuffer.toString();
    }

    public static String utf8ToUnicode(String str) {
        char[] charArray = str.toCharArray();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            Character.UnicodeBlock unicodeBlockOf = Character.UnicodeBlock.of(charArray[i]);
            if (unicodeBlockOf == Character.UnicodeBlock.BASIC_LATIN || unicodeBlockOf == Character.UnicodeBlock.LATIN_1_SUPPLEMENT) {
                stringBuffer.append(charArray[i]);
            } else if (unicodeBlockOf == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
                stringBuffer.append((char) (charArray[i] - 65248));
            } else {
                stringBuffer.append(("\\u" + Integer.toHexString(charArray[i])).toLowerCase());
            }
        }
        return stringBuffer.toString();
    }

    public String gbk2utf8(String str) {
        return unicodeToUtf8(GBK2Unicode(str));
    }

    public String utf82gbk(String str) {
        return Unicode2GBK(utf8ToUnicode(str));
    }
}
