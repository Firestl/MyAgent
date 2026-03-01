package com.zx.a.I8b7;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.analytics.pro.a;
import com.umeng.analytics.pro.az;
import com.umeng.analytics.pro.bc;
import com.zui.deviceidservice.IDeviceidInterface;
import io.dcloud.common.constant.AbsoluteConst;
import java.security.MessageDigest;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class p0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f6254a;
    public static volatile p0 b = null;
    public static Context c = null;
    public static boolean d = false;

    public interface a {
        boolean a(Context context);

        String b(Context context);

        boolean c(Context context);
    }

    public static class b implements a {
        public static String f = null;
        public static boolean g = false;
        public static boolean h = false;
        public static final CountDownLatch i = new CountDownLatch(1);

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f6255a;
        public final String b;
        public final String c;
        public final String d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public e f6256e;

        public b(String str, String str2, String str3, String str4) {
            this.f6255a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
        }

        public int a() {
            return 1;
        }

        @Override // com.zx.a.I8b7.p0.a
        public boolean a(Context context) {
            if (context == null || TextUtils.isEmpty(this.f6255a)) {
                return false;
            }
            if (this.f6256e == null) {
                this.f6256e = new e(this.d, i);
            }
            Intent intent = new Intent();
            if (TextUtils.isEmpty(this.b)) {
                intent.setPackage(this.f6255a);
            } else {
                intent.setComponent(new ComponentName(this.f6255a, this.b));
            }
            if (!TextUtils.isEmpty(this.c)) {
                intent.setAction(this.c);
            }
            return this.f6256e.a(context, intent);
        }

        public String b() {
            return null;
        }

        @Override // com.zx.a.I8b7.p0.a
        public String b(Context context) {
            e eVar;
            d dVar;
            e eVar2;
            if (!TextUtils.isEmpty(f) || (eVar = this.f6256e) == null || (dVar = eVar.f6260a) == null) {
                return f;
            }
            try {
                String strA = dVar.a(d(context), e(context), b(), a());
                f = strA;
                if (!TextUtils.isEmpty(strA) && (eVar2 = this.f6256e) != null) {
                    context.unbindService(eVar2);
                }
            } catch (Throwable unused) {
            }
            return f;
        }

        @Override // com.zx.a.I8b7.p0.a
        public boolean c(Context context) {
            if (h) {
                return g;
            }
            boolean z = false;
            if (context == null || TextUtils.isEmpty(this.f6255a)) {
                g = false;
            } else {
                try {
                    PackageInfo packageInfoA = p2.a(this.f6255a, 0);
                    if (Build.VERSION.SDK_INT >= 28) {
                        if (packageInfoA != null) {
                            if (packageInfoA.getLongVersionCode() >= 1) {
                                return true;
                            }
                        }
                        return false;
                    }
                    if (packageInfoA != null && packageInfoA.versionCode >= 1) {
                        z = true;
                    }
                    g = z;
                } catch (Throwable unused) {
                    return false;
                }
            }
            h = true;
            return g;
        }

        public String d(Context context) {
            return null;
        }

        public String e(Context context) {
            return null;
        }
    }

    public static class c implements a {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static String f6257e = null;
        public static boolean f = false;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f6258a;
        public String b;
        public String[] c;
        public boolean d = false;

        public c(String str, String str2) {
            this.f6258a = str;
            this.b = str2;
        }

        @Override // com.zx.a.I8b7.p0.a
        public boolean a(Context context) {
            return true;
        }

        @Override // com.zx.a.I8b7.p0.a
        public String b(Context context) {
            Cursor cursorQuery;
            if (TextUtils.isEmpty(f6257e)) {
                StringBuilder sbA = m2.a("content://");
                sbA.append(this.f6258a);
                sbA.append("/");
                sbA.append(this.b);
                try {
                    cursorQuery = context.getContentResolver().query(Uri.parse(sbA.toString()), null, null, this.c, null);
                    if (cursorQuery != null) {
                        try {
                            cursorQuery.moveToFirst();
                            f6257e = cursorQuery.getString(cursorQuery.getColumnIndex("value"));
                        } catch (Throwable unused) {
                            try {
                                f6257e = null;
                                return f6257e;
                            } finally {
                                if (cursorQuery != null) {
                                    cursorQuery.close();
                                }
                            }
                        }
                    }
                } catch (Throwable unused2) {
                    cursorQuery = null;
                }
                if (cursorQuery != null) {
                }
            }
            return f6257e;
        }

        @Override // com.zx.a.I8b7.p0.a
        public boolean c(Context context) {
            if (this.d) {
                return f;
            }
            if (context == null) {
                return false;
            }
            try {
                PackageManager packageManagerC = d3.c(context);
                f = (packageManagerC == null || packageManagerC.resolveContentProvider(this.f6258a, 0) == null) ? false : true;
            } catch (Throwable unused) {
                f = false;
            }
            this.d = true;
            return f;
        }
    }

    public static class d implements IInterface {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public IBinder f6259a;
        public String b;

        public d(IBinder iBinder, String str) {
            this.f6259a = iBinder;
            this.b = str;
        }

        public String a(String str, String str2, String str3, int i) {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                try {
                    parcelObtain.writeInterfaceToken(this.b);
                    if (!TextUtils.isEmpty(str)) {
                        parcelObtain.writeString(str);
                    }
                    if (!TextUtils.isEmpty(str2)) {
                        parcelObtain.writeString(str2);
                    }
                    if (!TextUtils.isEmpty(str3)) {
                        parcelObtain.writeString(str3);
                    }
                    this.f6259a.transact(i, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } catch (Throwable unused) {
                    parcelObtain.recycle();
                    parcelObtain2.recycle();
                    return "";
                }
            } catch (Exception unused2) {
                return "";
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this.f6259a;
        }
    }

    public static class e implements ServiceConnection {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public d f6260a;
        public String b;
        public CountDownLatch c;
        public IBinder d;

        public e(String str, CountDownLatch countDownLatch) {
            this.b = str;
            this.c = countDownLatch;
        }

        public boolean a(Context context, Intent intent) {
            d dVar;
            if (this.f6260a != null) {
                return true;
            }
            try {
                boolean zBindService = context.bindService(intent, this, 1);
                this.c.await(1L, TimeUnit.SECONDS);
                IBinder iBinder = this.d;
                String str = this.b;
                if (iBinder == null) {
                    dVar = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(str);
                    dVar = iInterfaceQueryLocalInterface instanceof d ? (d) iInterfaceQueryLocalInterface : new d(iBinder, str);
                }
                this.f6260a = dVar;
                return zBindService;
            } catch (Throwable unused) {
                return false;
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.d = iBinder;
                this.c.countDown();
            } catch (Throwable unused) {
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            this.f6260a = null;
            this.d = null;
        }
    }

    public static class f extends b {
        public f() {
            super(a("Y29tLmFzdXMubXNhLlN1cHBsZW1lbnRhcnlESUQ="), a("Y29tLmFzdXMubXNhLlN1cHBsZW1lbnRhcnlESUQuU3VwcGxlbWVudGFyeURJRFNlcnZpY2U="), a("Y29tLmFzdXMubXNhLmFjdGlvbi5BQ0NFU1NfRElE"), a("Y29tLmFzdXMubXNhLlN1cHBsZW1lbnRhcnlESUQuSURpZEFpZGxJbnRlcmZhY2U="));
        }

        public static String a(String str) {
            return new String(Base64.decode(str, 0));
        }

        @Override // com.zx.a.I8b7.p0.b
        public int a() {
            return 2;
        }
    }

    public static class g extends b {
        public g() {
            super(az.b, az.c, null, a.b.h);
        }

        @Override // com.zx.a.I8b7.p0.b
        public int a() {
            return 2;
        }
    }

    public static class h extends b {
        public h() {
            super("com.huawei.hwid", null, "com.uodis.opendevice.OPENIDS_SERVICE", bc.f5194a);
        }
    }

    public static class i extends c {
        public i() {
            super("com.meizu.flyme.openidsdk", "");
        }

        @Override // com.zx.a.I8b7.p0.c, com.zx.a.I8b7.p0.a
        public String b(Context context) {
            this.c = new String[]{com.umeng.commonsdk.statistics.idtracking.i.d};
            return super.b(context);
        }

        @Override // com.zx.a.I8b7.p0.c, com.zx.a.I8b7.p0.a
        public boolean c(Context context) {
            if (super.c(context)) {
                c.f = true;
            } else {
                try {
                    Cursor cursorQuery = context.getContentResolver().query(Uri.parse("content://com.meizu.flyme.openidsdk/"), null, null, new String[]{AbsoluteConst.PULL_REFRESH_SUPPORT}, null);
                    if (cursorQuery == null) {
                        return false;
                    }
                    cursorQuery.moveToFirst();
                    int columnIndex = cursorQuery.getColumnIndex("value");
                    if (columnIndex >= 0) {
                        String string = cursorQuery.getString(columnIndex);
                        if (TextUtils.isEmpty(string)) {
                            return false;
                        }
                        c.f = "0".equals(string);
                    } else {
                        c.f = false;
                    }
                } catch (Throwable unused) {
                    c.f = false;
                    return false;
                }
            }
            this.d = true;
            return c.f;
        }
    }

    public static class j implements a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f6261a = false;
        public boolean b = false;
        public String c = null;

        @Override // com.zx.a.I8b7.p0.a
        public boolean a(Context context) {
            return true;
        }

        @Override // com.zx.a.I8b7.p0.a
        public String b(Context context) {
            Bundle bundleCall;
            try {
                if (TextUtils.isEmpty(this.c)) {
                    Uri uri = Uri.parse("content://cn.nubia.identity/identity");
                    int i = Build.VERSION.SDK_INT;
                    if (i >= 17) {
                        ContentProviderClient contentProviderClientAcquireContentProviderClient = context.getContentResolver().acquireContentProviderClient(uri);
                        if (contentProviderClientAcquireContentProviderClient != null) {
                            bundleCall = contentProviderClientAcquireContentProviderClient.call("getOAID", null, null);
                            try {
                                if (i >= 24) {
                                    Class.forName("android.content.ContentProviderClient").getMethod("close", new Class[0]).invoke(contentProviderClientAcquireContentProviderClient, new Object[0]);
                                } else {
                                    contentProviderClientAcquireContentProviderClient.release();
                                }
                            } catch (Throwable unused) {
                            }
                        } else {
                            bundleCall = null;
                        }
                    } else {
                        bundleCall = context.getContentResolver().call(uri, "getOAID", (String) null, (Bundle) null);
                    }
                    if (bundleCall == null) {
                        return this.c;
                    }
                    if (bundleCall.getInt("code", -1) == 0) {
                        this.c = bundleCall.getString("id");
                    }
                }
            } catch (Throwable unused2) {
                this.c = null;
            }
            return this.c;
        }

        @Override // com.zx.a.I8b7.p0.a
        public boolean c(Context context) {
            if (this.b) {
                return this.f6261a;
            }
            if (context == null) {
                return false;
            }
            try {
                PackageManager packageManagerC = d3.c(context);
                this.f6261a = (packageManagerC == null || packageManagerC.resolveContentProvider("cn.nubia.identity", 0) == null) ? false : true;
            } catch (Throwable unused) {
                this.f6261a = false;
            }
            this.b = true;
            return this.f6261a;
        }
    }

    public static class k extends b {
        public String j;
        public String k;

        public k() {
            super("com.heytap.openid", "com.heytap.openid.IdentifyService", "action.com.heytap.openid.OPEN_ID_SERVICE", "com.heytap.openid.IOpenID");
        }

        @Override // com.zx.a.I8b7.p0.b
        public String b() {
            return "OUID";
        }

        @Override // com.zx.a.I8b7.p0.b
        public String d(Context context) {
            if (TextUtils.isEmpty(this.k)) {
                this.k = context.getPackageName();
            }
            return this.k;
        }

        @Override // com.zx.a.I8b7.p0.b
        @SuppressLint({"PackageManagerGetSignatures"})
        public String e(Context context) {
            if (TextUtils.isEmpty(this.j)) {
                try {
                    if (TextUtils.isEmpty(this.k)) {
                        this.k = context.getPackageName();
                    }
                    String str = this.k;
                    this.k = str;
                    Signature[] signatureArr = p2.a(str, 64).signatures;
                    if (signatureArr != null && signatureArr.length > 0) {
                        byte[] bArrDigest = MessageDigest.getInstance("SHA1").digest(signatureArr[0].toByteArray());
                        StringBuilder sb = new StringBuilder();
                        for (byte b : bArrDigest) {
                            sb.append(Integer.toHexString((b & 255) | 256).substring(1, 3));
                        }
                        this.j = sb.toString();
                    }
                } catch (Throwable unused) {
                }
            }
            return this.j;
        }
    }

    public static class l extends b {
        public l() {
            super("com.samsung.android.deviceidservice", "com.samsung.android.deviceidservice.DeviceIdService", null, "com.samsung.android.deviceidservice.IDeviceIdService");
        }
    }

    public static class m extends c {
        public m() {
            super("com.vivo.vms.IdProvider", "IdentifierId/OAID");
        }
    }

    public static class n implements a {
        public static String b;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Class<?> f6262a = null;

        @Override // com.zx.a.I8b7.p0.a
        public boolean a(Context context) {
            return true;
        }

        @Override // com.zx.a.I8b7.p0.a
        public String b(Context context) {
            if (TextUtils.isEmpty(b)) {
                try {
                    b = String.valueOf(this.f6262a.getMethod("getOAID", Context.class).invoke(this.f6262a.newInstance(), context));
                } catch (Throwable unused) {
                    b = null;
                }
            }
            return b;
        }

        @Override // com.zx.a.I8b7.p0.a
        @SuppressLint({"PrivateApi"})
        public boolean c(Context context) {
            try {
                this.f6262a = Class.forName("com.android.id.impl.IdProviderImpl");
                return true;
            } catch (Throwable unused) {
                return false;
            }
        }
    }

    public static class o extends b {
        public o() {
            super("com.zui.deviceidservice", "com.zui.deviceidservice.DeviceidService", null, IDeviceidInterface.Stub.DESCRIPTOR);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0112  */
    static {
        /*
            Method dump skipped, instruction units count: 468
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zx.a.I8b7.p0.<clinit>():void");
    }
}
