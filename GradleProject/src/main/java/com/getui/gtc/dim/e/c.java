package com.getui.gtc.dim.e;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Parcel;
import android.os.Parcelable;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.annotation.MutableMethod;
import com.getui.gtc.base.util.io.IOUtils;
import com.xiaomi.mipush.sdk.Constants;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class c {
    @MutableMethod
    public static Object a(int i, String str, Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null || i < 0) {
                return null;
            }
            return telephonyManager.getClass().getMethod(str, c(str)).invoke(telephonyManager, Integer.valueOf(i));
        } catch (Throwable th) {
            b.a(th);
            return null;
        }
    }

    public static Object a(byte[] bArr) throws Exception {
        byte b = bArr[0];
        byte[] bArr2 = new byte[bArr.length - 1];
        System.arraycopy(bArr, 1, bArr2, 0, bArr.length - 1);
        if (b == 0) {
            return b(bArr2);
        }
        if (b != 1) {
            throw new RuntimeException("bytesToObject failed, invalid type");
        }
        Object objC = c(bArr2);
        return objC instanceof com.getui.gtc.dim.d.c ? ((com.getui.gtc.dim.d.c) objC).getParcelables() : objC;
    }

    public static Process a(String str) throws Throwable {
        Class<?> cls = Class.forName(new String(Base64.decode("amF2YS5sYW5nLlJ1bnRpbWU=", 0)));
        Method declaredMethod = cls.getDeclaredMethod(new String(Base64.decode("Z2V0UnVudGltZQ==", 0)), new Class[0]);
        declaredMethod.setAccessible(true);
        Object objInvoke = declaredMethod.invoke(null, new Object[0]);
        Method declaredMethod2 = cls.getDeclaredMethod(new String(Base64.decode("ZXhlYw==", 0)), String.class);
        declaredMethod2.setAccessible(true);
        return (Process) declaredMethod2.invoke(objInvoke, str);
    }

    public static String a(String str, String str2) {
        try {
            String[] strArrSplit = str.split("#");
            int length = strArrSplit.length;
            int i = 0;
            while (true) {
                String str3 = "";
                if (i >= length) {
                    return "";
                }
                String str4 = strArrSplit[i];
                if (!str4.trim().isEmpty()) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(a("getprop ".concat(String.valueOf(str4))).getInputStream()));
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        str3 = str3 + line;
                    }
                    bufferedReader.close();
                    if (!str3.trim().isEmpty()) {
                        return str3;
                    }
                }
                i++;
            }
        } catch (Throwable unused) {
            return str2;
        }
    }

    public static void a(Context context, String str, boolean z) {
        try {
            z = context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
        } catch (Throwable th) {
            b.a(th);
        }
        if (z) {
            return;
        }
        throw new IllegalStateException("permission " + str + " not granted");
    }

    @MutableMethod
    @SuppressLint({"MissingPermission"})
    public static boolean a(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.isAvailable()) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            b.a(th);
            return false;
        }
    }

    public static boolean a(Context context, String str) {
        try {
            return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
        } catch (Throwable th) {
            b.a(th);
            return true;
        }
    }

    public static boolean a(Object obj) {
        if (obj == null) {
            return false;
        }
        return obj instanceof CharSequence ? !TextUtils.isEmpty((CharSequence) obj) : obj instanceof Collection ? ((Collection) obj).size() > 0 : !(obj instanceof Map) || ((Map) obj).size() > 0;
    }

    public static boolean a(byte[] bArr, File file) throws Exception {
        ByteArrayInputStream byteArrayInputStream;
        FileOutputStream fileOutputStream;
        if (bArr == null) {
            return false;
        }
        FileOutputStream fileOutputStream2 = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bArr);
            fileOutputStream = new FileOutputStream(file);
        } catch (Throwable th) {
            th = th;
        }
        try {
            byte[] bArr2 = new byte[521];
            while (true) {
                int i = byteArrayInputStream.read(bArr2);
                if (i == -1) {
                    fileOutputStream.flush();
                    IOUtils.safeClose(fileOutputStream);
                    return true;
                }
                fileOutputStream.write(bArr2, 0, i);
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            IOUtils.safeClose(fileOutputStream2);
            throw th;
        }
    }

    public static byte[] a(Parcelable parcelable) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeParcelable(parcelable, 0);
            byte[] bArrMarshall = parcelObtain.marshall();
            byte[] bArr = new byte[bArrMarshall.length + 1];
            bArr[0] = 1;
            System.arraycopy(bArrMarshall, 0, bArr, 1, bArrMarshall.length);
            return bArr;
        } finally {
            parcelObtain.recycle();
        }
    }

    public static byte[] a(File file) throws Exception {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (Throwable th) {
            th = th;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[521];
            while (true) {
                int i = fileInputStream.read(bArr);
                if (i == -1) {
                    byteArrayOutputStream.flush();
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    IOUtils.safeClose(fileInputStream);
                    return byteArray;
                }
                byteArrayOutputStream.write(bArr, 0, i);
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream2 = fileInputStream;
            IOUtils.safeClose(fileInputStream2);
            throw th;
        }
    }

    public static byte[] a(Serializable serializable) throws Throwable {
        ObjectOutputStream objectOutputStream;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream2 = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        } catch (Throwable th) {
            th = th;
        }
        try {
            objectOutputStream.writeObject(serializable);
            objectOutputStream.flush();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byte[] bArr = new byte[byteArray.length + 1];
            bArr[0] = 0;
            System.arraycopy(byteArray, 0, bArr, 1, byteArray.length);
            try {
                objectOutputStream.close();
            } catch (Throwable unused) {
            }
            return bArr;
        } catch (Throwable th2) {
            th = th2;
            objectOutputStream2 = objectOutputStream;
            if (objectOutputStream2 != null) {
                try {
                    objectOutputStream2.close();
                } catch (Throwable unused2) {
                }
            }
            throw th;
        }
    }

    public static Object b(byte[] bArr) {
        ObjectInputStream objectInputStream;
        try {
            objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bArr));
        } catch (Throwable th) {
            th = th;
            objectInputStream = null;
        }
        try {
            Object object = objectInputStream.readObject();
            try {
                objectInputStream.close();
            } catch (Throwable unused) {
            }
            return object;
        } catch (Throwable th2) {
            th = th2;
            try {
                b.a(th);
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (Throwable unused2) {
                    }
                }
                return null;
            } catch (Throwable th3) {
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (Throwable unused3) {
                    }
                }
                throw th3;
            }
        }
    }

    public static String b(String str) {
        Process processA;
        if (TextUtils.isEmpty(str) || "0.0.0.0".equalsIgnoreCase(str)) {
            return "";
        }
        BufferedReader bufferedReader = null;
        try {
            try {
                processA = a(new String(Base64.decode("aXAgbmVpZ2hib3Vy", 0)));
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(processA.getInputStream()));
                    int i = 0;
                    while (true) {
                        try {
                            String line = bufferedReader2.readLine();
                            if (line != null) {
                                try {
                                } catch (Throwable th) {
                                    th = th;
                                }
                                if (line.contains("192.168") || line.contains("wlan0")) {
                                    if (line.contains("FAILED")) {
                                        continue;
                                    } else {
                                        String[] strArrSplit = line.split(" +");
                                        if (strArrSplit.length < 6) {
                                            continue;
                                        } else {
                                            int i2 = i + 1;
                                            if (i <= 256) {
                                                try {
                                                    String strReplaceAll = strArrSplit[4].replaceAll(Constants.COLON_SEPARATOR, "");
                                                    if (str.equalsIgnoreCase(strArrSplit[0])) {
                                                        try {
                                                            bufferedReader2.close();
                                                        } catch (IOException e2) {
                                                            b.a(e2);
                                                        }
                                                        if (processA != null) {
                                                            try {
                                                                processA.destroy();
                                                            } catch (Throwable th2) {
                                                                b.a(th2);
                                                            }
                                                        }
                                                        return strReplaceAll;
                                                    }
                                                    i = i2;
                                                } catch (Throwable th3) {
                                                    th = th3;
                                                    i = i2;
                                                    b.a(th);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            try {
                                bufferedReader2.close();
                            } catch (IOException e3) {
                                b.a(e3);
                            }
                            if (processA != null) {
                                processA.destroy();
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            bufferedReader = bufferedReader2;
                            try {
                                b.a(th);
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (IOException e4) {
                                        b.a(e4);
                                    }
                                }
                                if (processA != null) {
                                    processA.destroy();
                                }
                                return "";
                            } finally {
                            }
                        }
                    }
                } catch (Throwable th5) {
                    th = th5;
                }
            } catch (Throwable th6) {
                th = th6;
                processA = null;
            }
        } catch (Throwable th7) {
            b.a(th7);
        }
    }

    @MutableMethod
    @SuppressLint({"MissingPermission"})
    public static boolean b(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.getType() == 0) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            b.a(th);
            return false;
        }
    }

    public static byte[] b(Object obj) throws Exception {
        if (obj instanceof Parcelable) {
            return a((Parcelable) obj);
        }
        if (obj instanceof List) {
            List list = (List) obj;
            if (list.get(0) instanceof Parcelable) {
                return a((Parcelable) new com.getui.gtc.dim.d.c((List<Parcelable>) list));
            }
        }
        if (obj instanceof Serializable) {
            return a((Serializable) obj);
        }
        throw new IllegalArgumentException("objectToBytes failed, object type is not support: " + obj.getClass().getName());
    }

    public static Object c(byte[] bArr) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.unmarshall(bArr, 0, bArr.length);
            parcelObtain.setDataPosition(0);
            return parcelObtain.readParcelable(GtcProvider.context().getClassLoader());
        } finally {
            parcelObtain.recycle();
        }
    }

    @MutableMethod
    @SuppressLint({"MissingPermission"})
    public static boolean c(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.getType() == 1) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            b.a(th);
            return false;
        }
    }

    @MutableMethod
    public static Class<?>[] c(String str) {
        Class<?>[] parameterTypes = null;
        try {
            for (Method method : TelephonyManager.class.getDeclaredMethods()) {
                if (str.equals(method.getName())) {
                    parameterTypes = method.getParameterTypes();
                    if (parameterTypes.length > 0) {
                        break;
                    }
                }
            }
        } catch (Throwable th) {
            b.a(th);
        }
        return parameterTypes;
    }
}
