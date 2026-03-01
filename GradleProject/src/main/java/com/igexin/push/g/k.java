package com.igexin.push.g;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Process;
import android.util.Base64;
import com.getui.gtc.base.GtcProvider;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f3599a = Integer.MIN_VALUE;
    public static String b = "GT_PM";
    public static Object c;

    public static int a() {
        int i = f3599a;
        if (i != Integer.MIN_VALUE) {
            return i;
        }
        if (GtcProvider.context() != null && Build.VERSION.SDK_INT >= 17) {
            try {
                Class<?> cls = Class.forName(new String(Base64.decode("YW5kcm9pZC5vcy5Vc2VySGFuZGxl", 0)));
                Method declaredMethod = cls.getDeclaredMethod(new String(Base64.decode("Z2V0VXNlcklk", 0)), Integer.TYPE);
                declaredMethod.setAccessible(true);
                int iIntValue = ((Integer) declaredMethod.invoke(cls, Integer.valueOf(Process.myUid()))).intValue();
                f3599a = iIntValue;
                return iIntValue;
            } catch (Throwable th) {
                th.getMessage();
            }
        }
        return 0;
    }

    public static PackageInfo a(Object obj, String str, int i) {
        try {
            IBinder iBinderAsBinder = ((IInterface) obj).asBinder();
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
            com.igexin.c.a.c.a.a(th2);
        }
        return packageInfo;
    }

    public static PackageInfo a(String str, int i) throws PackageManager.NameNotFoundException {
        PackageInfo packageInfoA = a(str, i, a());
        if (packageInfoA != null) {
            return packageInfoA;
        }
        throw new PackageManager.NameNotFoundException(str + " not found");
    }

    public static PackageInfo a(String str, int i, int i2) {
        try {
            if (c == null) {
                c = Class.forName(new String(Base64.decode("YW5kcm9pZC5hcHAuQWN0aXZpdHlUaHJlYWQ=", 0))).getMethod(new String(Base64.decode("Z2V0UGFja2FnZU1hbmFnZXI=", 0)), new Class[0]).invoke(null, new Object[0]);
            }
            if (Build.VERSION.SDK_INT >= 33) {
                return a(c, str, i);
            }
            String str2 = new String(Base64.decode("Z2V0UGFja2FnZUluZm8=", 0));
            return (PackageInfo) (Build.VERSION.SDK_INT >= 16 ? c.getClass().getMethod(str2, String.class, Integer.TYPE, Integer.TYPE).invoke(c, str, Integer.valueOf(i), Integer.valueOf(i2)) : c.getClass().getMethod(str2, String.class, Integer.TYPE).invoke(c, str, Integer.valueOf(i)));
        } catch (Throwable th) {
            th.getMessage();
            return null;
        }
    }
}
