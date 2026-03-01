package com.getui.gtc.dim.e;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Process;
import android.util.Base64;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f2187a = Integer.MIN_VALUE;
    public static Object b;
    public static Method c;
    public static Method d;

    public static int a() {
        int i = f2187a;
        if (i != Integer.MIN_VALUE) {
            return i;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            try {
                Class<?> cls = Class.forName(new String(Base64.decode("YW5kcm9pZC5vcy5Vc2VySGFuZGxl", 0)));
                Method declaredMethod = cls.getDeclaredMethod(new String(Base64.decode("Z2V0VXNlcklk", 0)), Integer.TYPE);
                declaredMethod.setAccessible(true);
                int iIntValue = ((Integer) declaredMethod.invoke(cls, Integer.valueOf(Process.myUid()))).intValue();
                f2187a = iIntValue;
                return iIntValue;
            } catch (Throwable th) {
                b.a(th);
            }
        }
        return 0;
    }

    public static PackageInfo a(int i) {
        try {
            if (b == null) {
                b = Class.forName(new String(Base64.decode("YW5kcm9pZC5hcHAuQWN0aXZpdHlUaHJlYWQ=", 0))).getMethod(new String(Base64.decode("Z2V0UGFja2FnZU1hbmFnZXI=", 0)), new Class[0]).invoke(null, new Object[0]);
            }
            if (d == null) {
                d = b.getClass().getMethod(new String(Base64.decode("Z2V0UGFja2FnZXNGb3JVaWQ=", 0)), Integer.TYPE);
            }
            String[] strArr = (String[]) d.invoke(b, Integer.valueOf(i));
            if (strArr == null || strArr.length != 1) {
                return null;
            }
            return a(strArr[0], 0);
        } catch (Throwable th) {
            b.a(th);
            return null;
        }
    }

    public static PackageInfo a(String str, int i) throws PackageManager.NameNotFoundException {
        PackageInfo packageInfoA = a(str, i, a());
        if (packageInfoA != null) {
            return packageInfoA;
        }
        throw new PackageManager.NameNotFoundException(str);
    }

    public static PackageInfo a(String str, int i, int i2) {
        try {
            if (b == null) {
                b = Class.forName(new String(Base64.decode("YW5kcm9pZC5hcHAuQWN0aXZpdHlUaHJlYWQ=", 0))).getMethod(new String(Base64.decode("Z2V0UGFja2FnZU1hbmFnZXI=", 0)), new Class[0]).invoke(null, new Object[0]);
            }
            if (Build.VERSION.SDK_INT >= 33) {
                return b(str, i);
            }
            if (c == null) {
                String str2 = new String(Base64.decode("Z2V0UGFja2FnZUluZm8=", 0));
                c = Build.VERSION.SDK_INT >= 16 ? b.getClass().getMethod(str2, String.class, Integer.TYPE, Integer.TYPE) : b.getClass().getMethod(str2, String.class, Integer.TYPE);
            }
            return (PackageInfo) (Build.VERSION.SDK_INT >= 16 ? c.invoke(b, str, Integer.valueOf(i), Integer.valueOf(i2)) : c.invoke(b, str, Integer.valueOf(i)));
        } catch (Throwable th) {
            b.a(th);
            return null;
        }
    }

    public static PackageInfo b(String str, int i) {
        try {
            IBinder iBinderAsBinder = ((IInterface) b).asBinder();
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken(iBinderAsBinder.getInterfaceDescriptor());
                parcelObtain.writeString(str);
                parcelObtain.writeLong(i);
                parcelObtain.writeInt(a());
                iBinderAsBinder.transact(3, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                packageInfo = parcelObtain2.readInt() != 0 ? (PackageInfo) PackageInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                parcelObtain2.recycle();
                parcelObtain.recycle();
            } catch (Throwable th) {
                parcelObtain2.recycle();
                parcelObtain.recycle();
                throw th;
            }
        } catch (Throwable th2) {
            b.a(th2);
        }
        return packageInfo;
    }
}
