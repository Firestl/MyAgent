package cn.com.chinatelecom.account.api.e;

import android.content.Context;
import android.net.Proxy;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import com.alibaba.fastjson.parser.JSONLexer;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.utils.FunctionParser;
import com.umeng.analytics.pro.co;
import com.umeng.analytics.pro.db;
import dc.squareup.okhttp3.internal.publicsuffix.PublicSuffixDatabase;
import io.dcloud.common.util.Md5Utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.UUID;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f1514a = "cn.com.chinatelecom.account.api.e.d";
    public static String b = "";
    public static final Pattern c = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");
    public static String d = "";

    public static String a() {
        String string = UUID.randomUUID().toString();
        try {
            string = UUID.nameUUIDFromBytes((string + System.currentTimeMillis() + Math.random()).getBytes("utf8")).toString();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return !TextUtils.isEmpty(string) ? string.replace("-", "") : string;
    }

    public static String a(Context context) {
        if (TextUtils.isEmpty(d)) {
            String strE = e(context);
            d = strE;
            if (TextUtils.isEmpty(strE)) {
                String strF = f(context);
                d = strF;
                a(context, strF);
            }
        }
        return d;
    }

    public static void a(Context context, String str) {
        if (TextUtils.isEmpty(str) || context == null) {
            return;
        }
        c.a(context, "key_d_i_u", str);
    }

    public static boolean a(Object obj, String str) throws NoSuchMethodException {
        Method declaredMethod = obj.getClass().getDeclaredMethod(str, new Class[0]);
        declaredMethod.setAccessible(true);
        return ((Boolean) declaredMethod.invoke(obj, new Object[0])).booleanValue();
    }

    public static boolean a(String str) {
        return str != null && c.matcher(str).matches();
    }

    public static String b(Context context, String str) {
        try {
            Class<?> clsLoadClass = context.getClassLoader().loadClass(l.a(new byte[]{13, 2, 8, 30, 3, 5, 8, 66, 3, co.j, 66, 28, 30, 3, 28, 9, 30, 24, 5, 9, co.j}));
            return (String) clsLoadClass.getMethod("get", String.class).invoke(clsLoadClass, str);
        } catch (Exception unused) {
            return "";
        }
    }

    public static String b(String str) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', FunctionParser.Lexer.NINE, FunctionParser.Lexer.A_LOWER, 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] bytes = str.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance(Md5Utils.ALGORITHM);
            messageDigest.update(bytes);
            byte[] bArrDigest = messageDigest.digest();
            char[] cArr2 = new char[bArrDigest.length * 2];
            int i = 0;
            for (byte b2 : bArrDigest) {
                int i2 = i + 1;
                cArr2[i] = cArr[(b2 >>> 4) & 15];
                i = i2 + 1;
                cArr2[i2] = cArr[b2 & 15];
            }
            return new String(cArr2);
        } catch (Exception unused) {
            return null;
        }
    }

    public static StringBuffer b() throws SocketException {
        StringBuffer stringBuffer = new StringBuffer();
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
            String name = networkInterfaceNextElement.getName();
            if (name == null || (!name.contains("wlan") && !name.equals("eth0"))) {
                Enumeration<InetAddress> inetAddresses = networkInterfaceNextElement.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddressNextElement = inetAddresses.nextElement();
                    if (!inetAddressNextElement.isLoopbackAddress() && !inetAddressNextElement.isLinkLocalAddress()) {
                        String hostAddress = inetAddressNextElement.getHostAddress();
                        if (!TextUtils.isEmpty(hostAddress)) {
                            if (stringBuffer.length() > 0) {
                                stringBuffer.append(",");
                            }
                            stringBuffer.append(hostAddress);
                        }
                    }
                }
            }
        }
        return stringBuffer;
    }

    public static boolean b(Context context) {
        int port;
        String property;
        boolean z = Build.VERSION.SDK_INT >= 14;
        String strA = l.a(new byte[]{4, 24, 24, 28, 66, 28, 30, 3, 20, 21, 36, 3, co.j, 24});
        String strA2 = l.a(new byte[]{4, 24, 24, 28, 66, 28, 30, 3, 20, 21, 60, 3, 30, 24});
        if (z) {
            property = System.getProperty(strA);
            String property2 = System.getProperty(strA2);
            if (property2 == null) {
                property2 = "-1";
            }
            port = Integer.parseInt(property2);
        } else {
            String host = Proxy.getHost(context);
            port = Proxy.getPort(context);
            property = host;
        }
        return (TextUtils.isEmpty(property) || port == -1) ? false : true;
    }

    public static boolean c() {
        String strA = l.a(new byte[]{67, co.j, 21, co.j, 24, 9, 1, 67, db.l, 5, 2, 67, co.j, 25});
        String strA2 = l.a(new byte[]{67, co.j, 21, co.j, 24, 9, 1, 67, 20, db.l, 5, 2, 67, co.j, 25});
        if (new File(strA).exists() && c(strA)) {
            return true;
        }
        return new File(strA2).exists() && c(strA2);
    }

    public static boolean c(Context context) {
        return g(context) || h(context) || i(context);
    }

    public static boolean c(String str) {
        Process processExec = null;
        try {
            processExec = Runtime.getRuntime().exec("ls -l " + str);
            String line = new BufferedReader(new InputStreamReader(processExec.getInputStream())).readLine();
            if (line != null && line.length() >= 4) {
                char cCharAt = line.charAt(3);
                if (cCharAt == 's' || cCharAt == 'x') {
                    return true;
                }
            }
            if (processExec == null) {
                return false;
            }
            processExec.destroy();
            return false;
        } finally {
            if (processExec != null) {
                processExec.destroy();
            }
        }
    }

    public static boolean d() throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        if (networkInterfaces == null) {
            return false;
        }
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
            if (networkInterfaceNextElement.isUp() && !networkInterfaceNextElement.getInterfaceAddresses().isEmpty()) {
                String strA = l.a(new byte[]{24, 25, 2, 92});
                String strA2 = l.a(new byte[]{28, 28, 28, 92});
                if (strA.equals(networkInterfaceNextElement.getName()) || strA2.equals(networkInterfaceNextElement.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x01dc, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x01dd, code lost:
    
        r0.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0211 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x021c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:94:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean d(android.content.Context r29) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 550
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.chinatelecom.account.api.e.d.d(android.content.Context):boolean");
    }

    public static String e(Context context) {
        return c.b(context, "key_d_i_u", "");
    }

    public static String f(Context context) {
        String strB = b(UUID.randomUUID().toString() + "default");
        return TextUtils.isEmpty(strB) ? "default" : strB;
    }

    public static boolean g(Context context) {
        String strA = l.a(new byte[]{8, 9, 66, 30, 3, db.l, JSONLexer.EOI, 66, 13, 2, 8, 30, 3, 5, 8, 66, 20, 28, 3, co.j, 9, 8, 66, 5, 2, co.j, 24, 13, 0, 0, 9, 30});
        String strA2 = l.a(new byte[]{15, 3, 1, 66, co.j, 13, 25, 30, 5, 7, 66, co.j, 25, db.l, co.j, 24, 30, 13, 24, 9});
        boolean z = context.createPackageContext(strA, 2) != null;
        boolean z2 = context.createPackageContext(strA2, 2) != null;
        return z || z2;
    }

    public static boolean h(Context context) throws Throwable {
        Throwable th;
        BufferedReader bufferedReader;
        FileReader fileReader;
        try {
            String strA = l.a(new byte[]{67, 28, 30, 3, 15, 67});
            String strA2 = l.a(new byte[]{67, 1, 13, 28, co.j});
            String strA3 = l.a(new byte[]{15, 3, 1, 66, co.j, 13, 25, 30, 5, 7, 66, co.j, 25, db.l, co.j, 24, 30, 13, 24, 9});
            String strA4 = l.a(new byte[]{52, 28, 3, co.j, 9, 8, 46, 30, 5, 8, 11, 9, 66, 6, 13, 30});
            HashSet<String> hashSet = new HashSet();
            fileReader = new FileReader(strA + Process.myPid() + strA2);
            try {
                BufferedReader bufferedReader2 = new BufferedReader(fileReader);
                while (true) {
                    try {
                        String line = bufferedReader2.readLine();
                        if (line == null) {
                            break;
                        }
                        if (line.endsWith(".so") || line.endsWith(".jar")) {
                            hashSet.add(line.substring(line.lastIndexOf(Operators.SPACE_STR) + 1));
                        }
                    } catch (Throwable th2) {
                        bufferedReader = bufferedReader2;
                        th = th2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                        if (fileReader == null) {
                            throw th;
                        }
                        try {
                            fileReader.close();
                            throw th;
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            throw th;
                        }
                    }
                }
                for (String str : hashSet) {
                    if (str.contains(strA3)) {
                        try {
                            bufferedReader2.close();
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                        try {
                            fileReader.close();
                        } catch (Exception e5) {
                            e5.printStackTrace();
                        }
                        return true;
                    }
                    if (str.contains(strA4)) {
                        try {
                            bufferedReader2.close();
                        } catch (Exception e6) {
                            e6.printStackTrace();
                        }
                        try {
                            fileReader.close();
                        } catch (Exception e7) {
                            e7.printStackTrace();
                        }
                        return true;
                    }
                }
                try {
                    bufferedReader2.close();
                } catch (Exception e8) {
                    e8.printStackTrace();
                }
                try {
                    fileReader.close();
                } catch (Exception e9) {
                    e9.printStackTrace();
                }
                return false;
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedReader = null;
            fileReader = null;
        }
    }

    public static boolean i(Context context) {
        try {
            throw new Exception("we have exception");
        } catch (Exception e2) {
            String strA = l.a(new byte[]{15, 3, 1, 66, 13, 2, 8, 30, 3, 5, 8, 66, 5, 2, 24, 9, 30, 2, 13, 0, 66, 3, co.j, 66, 54, 21, 11, 3, 24, 9, 37, 2, 5, 24});
            String strA2 = l.a(new byte[]{8, 9, 66, 30, 3, db.l, JSONLexer.EOI, 66, 13, 2, 8, 30, 3, 5, 8, 66, 20, 28, 3, co.j, 9, 8, 66, 52, 28, 3, co.j, 9, 8, 46, 30, 5, 8, 11, 9});
            String strA3 = l.a(new byte[]{8, 9, 66, 30, 3, db.l, JSONLexer.EOI, 66, 13, 2, 8, 30, 3, 5, 8, 66, 20, 28, 3, co.j, 9, 8, 66, 52, 28, 3, co.j, 9, 8, 46, 30, 5, 8, 11, 9});
            String strA4 = l.a(new byte[]{5, 2, JSONLexer.EOI, 3, 7, 9, 8});
            String strA5 = l.a(new byte[]{1, 13, 5, 2});
            String strA6 = l.a(new byte[]{4, 13, 2, 8, 0, 9, 36, 3, 3, 7, 9, 8, PublicSuffixDatabase.EXCEPTION_MARKER, 9, 24, 4, 3, 8});
            String strA7 = l.a(new byte[]{15, 3, 1, 66, co.j, 13, 25, 30, 5, 7, 66, co.j, 25, db.l, co.j, 24, 30, 13, 24, 9, 66, PublicSuffixDatabase.EXCEPTION_MARKER, 63, 72, 94});
            int i = 0;
            for (StackTraceElement stackTraceElement : e2.getStackTrace()) {
                if (stackTraceElement.getClassName().equals(strA) && (i = i + 1) == 2) {
                    return true;
                }
                if (stackTraceElement.getClassName().equals(strA7) && stackTraceElement.getMethodName().equals(strA4)) {
                    return true;
                }
                if (stackTraceElement.getClassName().equals(strA2) && stackTraceElement.getMethodName().equals(strA5)) {
                    return true;
                }
                if (stackTraceElement.getClassName().equals(strA3) && stackTraceElement.getMethodName().equals(strA6)) {
                    return true;
                }
            }
            return false;
        }
    }
}
