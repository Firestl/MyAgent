package com.getui.gtc.dim.c;

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
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.analytics.pro.a;
import com.umeng.analytics.pro.az;
import com.umeng.analytics.pro.b;
import com.umeng.analytics.pro.bb;
import com.umeng.analytics.pro.bc;
import com.umeng.analytics.pro.c;
import com.zui.deviceidservice.IDeviceidInterface;
import io.dcloud.common.constant.AbsoluteConst;
import java.security.MessageDigest;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f2174a;
    public static Context b = null;
    public static boolean c = false;
    public static final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static volatile d f2175e;

    public interface a {
        boolean a(Context context);

        String b(Context context);

        boolean c(Context context);
    }

    public static class b implements a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f2176a;
        public e b;
        public boolean c = false;
        public boolean d = false;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final CountDownLatch f2177e = new CountDownLatch(1);
        public final String f;
        public final String g;
        public final String h;
        public final String i;

        public b(String str, String str2, String str3, String str4) {
            this.f = str;
            this.g = str2;
            this.h = str3;
            this.i = str4;
        }

        public String a() {
            return null;
        }

        @Override // com.getui.gtc.dim.c.d.a
        public boolean a(Context context) {
            if (this.d) {
                return this.c;
            }
            if (context == null || TextUtils.isEmpty(this.f)) {
                this.c = false;
            } else {
                try {
                    PackageInfo packageInfoA = com.getui.gtc.dim.e.d.a(this.f, 0);
                    if (Build.VERSION.SDK_INT >= 28) {
                        return packageInfoA.getLongVersionCode() >= 1;
                    }
                    this.c = packageInfoA.versionCode > 0;
                } catch (Throwable unused) {
                    return false;
                }
            }
            this.d = true;
            return this.c;
        }

        public int b() {
            return 1;
        }

        @Override // com.getui.gtc.dim.c.d.a
        public String b(Context context) {
            e eVar;
            C0039d c0039d;
            if (!TextUtils.isEmpty(this.f2176a) || (eVar = this.b) == null || (c0039d = eVar.f2181a) == null) {
                return this.f2176a;
            }
            try {
                String strA = c0039d.a(d(context), e(context), a(), b());
                this.f2176a = strA;
                if (!TextUtils.isEmpty(strA) && this.b != null) {
                    context.unbindService(this.b);
                }
            } catch (Throwable unused) {
            }
            return this.f2176a;
        }

        @Override // com.getui.gtc.dim.c.d.a
        public boolean c(Context context) {
            if (context == null || TextUtils.isEmpty(this.f)) {
                return false;
            }
            if (this.b == null) {
                this.b = new e(this.i, this.f2177e);
            }
            Intent intent = new Intent();
            if (TextUtils.isEmpty(this.g)) {
                intent.setPackage(this.f);
            } else {
                intent.setComponent(new ComponentName(this.f, this.g));
            }
            if (!TextUtils.isEmpty(this.h)) {
                intent.setAction(this.h);
            }
            return this.b.a(context, intent);
        }

        public String d(Context context) {
            return null;
        }

        public String e(Context context) {
            return null;
        }
    }

    public static class c implements a {
        public static boolean b = false;
        public static String d;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String[] f2178a;
        public boolean c = false;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f2179e;
        public String f;

        public c(String str, String str2) {
            this.f2179e = str;
            this.f = str2;
        }

        @Override // com.getui.gtc.dim.c.d.a
        public boolean a(Context context) {
            if (this.c) {
                return b;
            }
            if (context == null) {
                return false;
            }
            try {
                PackageManager packageManager = context.getPackageManager();
                b = (packageManager == null || packageManager.resolveContentProvider(this.f2179e, 0) == null) ? false : true;
            } catch (Throwable unused) {
                b = false;
            }
            this.c = true;
            return b;
        }

        @Override // com.getui.gtc.dim.c.d.a
        public String b(Context context) {
            Cursor cursorQuery;
            if (TextUtils.isEmpty(d)) {
                try {
                    cursorQuery = context.getContentResolver().query(Uri.parse("content://" + this.f2179e + "/" + this.f), null, null, this.f2178a, null);
                    if (cursorQuery != null) {
                        try {
                            cursorQuery.moveToFirst();
                            d = cursorQuery.getString(cursorQuery.getColumnIndex("value"));
                        } catch (Throwable unused) {
                            try {
                                d = null;
                                return d;
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
            return d;
        }

        @Override // com.getui.gtc.dim.c.d.a
        public final boolean c(Context context) {
            return true;
        }
    }

    /* JADX INFO: renamed from: com.getui.gtc.dim.c.d$d, reason: collision with other inner class name */
    public static class C0039d implements IInterface {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public IBinder f2180a;
        public String b;

        public C0039d(IBinder iBinder, String str) {
            this.f2180a = iBinder;
            this.b = str;
        }

        public static C0039d a(IBinder iBinder, String str) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(str);
            return iInterfaceQueryLocalInterface instanceof C0039d ? (C0039d) iInterfaceQueryLocalInterface : new C0039d(iBinder, str);
        }

        public final String a(String str, String str2, String str3, int i) {
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
                    this.f2180a.transact(i, parcelObtain, parcelObtain2, 0);
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
        public final IBinder asBinder() {
            return this.f2180a;
        }
    }

    public static class e implements ServiceConnection {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public C0039d f2181a;
        public IBinder b;
        public String c;
        public CountDownLatch d;

        public e(String str, CountDownLatch countDownLatch) {
            this.c = str;
            this.d = countDownLatch;
        }

        public final boolean a(Context context, Intent intent) {
            if (context == null) {
                return false;
            }
            if (this.f2181a != null) {
                return true;
            }
            try {
                boolean zBindService = context.bindService(intent, this, 1);
                this.d.await(1L, TimeUnit.SECONDS);
                this.f2181a = C0039d.a(this.b, this.c);
                return zBindService;
            } catch (Throwable unused) {
                return false;
            }
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.b = iBinder;
                this.d.countDown();
            } catch (Throwable unused) {
            }
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            this.f2181a = null;
            this.b = null;
        }
    }

    public static class f extends b {
        public f() {
            super(a("Y29tLmFzdXMubXNhLlN1cHBsZW1lbnRhcnlESUQ="), a("Y29tLmFzdXMubXNhLlN1cHBsZW1lbnRhcnlESUQuU3VwcGxlbWVudGFyeURJRFNlcnZpY2U="), a("Y29tLmFzdXMubXNhLmFjdGlvbi5BQ0NFU1NfRElE"), a("Y29tLmFzdXMubXNhLlN1cHBsZW1lbnRhcnlESUQuSURpZEFpZGxJbnRlcmZhY2U="));
        }

        public static String a(String str) {
            return new String(Base64.decode(str, 0));
        }

        @Override // com.getui.gtc.dim.c.d.b, com.getui.gtc.dim.c.d.a
        public final /* bridge */ /* synthetic */ boolean a(Context context) {
            return super.a(context);
        }

        @Override // com.getui.gtc.dim.c.d.b
        public final int b() {
            return 2;
        }

        @Override // com.getui.gtc.dim.c.d.b, com.getui.gtc.dim.c.d.a
        public final /* bridge */ /* synthetic */ String b(Context context) {
            return super.b(context);
        }

        @Override // com.getui.gtc.dim.c.d.b, com.getui.gtc.dim.c.d.a
        public final /* bridge */ /* synthetic */ boolean c(Context context) {
            return super.c(context);
        }
    }

    public static class g extends b {
        public g() {
            super(az.b, az.c, null, a.b.h);
        }

        @Override // com.getui.gtc.dim.c.d.b, com.getui.gtc.dim.c.d.a
        public final /* bridge */ /* synthetic */ boolean a(Context context) {
            return super.a(context);
        }

        @Override // com.getui.gtc.dim.c.d.b
        public final int b() {
            return 2;
        }

        @Override // com.getui.gtc.dim.c.d.b, com.getui.gtc.dim.c.d.a
        public final /* bridge */ /* synthetic */ String b(Context context) {
            return super.b(context);
        }

        @Override // com.getui.gtc.dim.c.d.b, com.getui.gtc.dim.c.d.a
        public final /* bridge */ /* synthetic */ boolean c(Context context) {
            return super.c(context);
        }
    }

    public static class h extends b {
        public h() {
            super("com.huawei.hwid", null, "com.uodis.opendevice.OPENIDS_SERVICE", bc.f5194a);
        }

        @Override // com.getui.gtc.dim.c.d.b, com.getui.gtc.dim.c.d.a
        public final /* bridge */ /* synthetic */ boolean a(Context context) {
            return super.a(context);
        }

        @Override // com.getui.gtc.dim.c.d.b, com.getui.gtc.dim.c.d.a
        public final /* bridge */ /* synthetic */ String b(Context context) {
            return super.b(context);
        }

        @Override // com.getui.gtc.dim.c.d.b, com.getui.gtc.dim.c.d.a
        public final /* bridge */ /* synthetic */ boolean c(Context context) {
            return super.c(context);
        }
    }

    public static class i extends b {
        public final CountDownLatch c;

        public static abstract class a extends Binder implements IInterface {
            public a() {
                attachInterface(this, b.AbstractBinderC0120b.c);
            }

            public abstract void a(int i, Bundle bundle);

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this;
            }

            @Override // android.os.Binder
            public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
                if (i != 2) {
                    if (i != 1598968902) {
                        return super.onTransact(i, parcel, parcel2, i2);
                    }
                    parcel2.writeString(b.AbstractBinderC0120b.c);
                    return true;
                }
                parcel.enforceInterface(b.AbstractBinderC0120b.c);
                a(parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            }
        }

        public i() {
            super("com.hihonor.id", null, "com.hihonor.id.HnOaIdService", c.b.d);
            this.c = new CountDownLatch(1);
        }

        public static boolean c() {
            PackageInfo packageInfoA;
            try {
                packageInfoA = com.getui.gtc.dim.e.d.a("com.hihonor.id", 0);
            } catch (Throwable unused) {
            }
            return Build.VERSION.SDK_INT >= 28 ? packageInfoA.getLongVersionCode() >= 1 : packageInfoA.versionCode > 0;
        }

        @Override // com.getui.gtc.dim.c.d.b, com.getui.gtc.dim.c.d.a
        public final /* bridge */ /* synthetic */ boolean a(Context context) {
            return super.a(context);
        }

        @Override // com.getui.gtc.dim.c.d.b, com.getui.gtc.dim.c.d.a
        public final String b(Context context) {
            e eVar;
            if (!TextUtils.isEmpty(this.f2176a) || (eVar = this.b) == null || eVar.f2181a == null) {
                return this.f2176a;
            }
            try {
                IBinder iBinder = eVar.b;
                a aVar = new a() { // from class: com.getui.gtc.dim.c.d.i.1
                    @Override // com.getui.gtc.dim.c.d.i.a
                    public final void a(int i, Bundle bundle) {
                        if (i == 0 && bundle != null) {
                            i.this.f2176a = bundle.getString(bb.c.b);
                        }
                        i.this.c.countDown();
                    }
                };
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(c.b.d);
                    parcelObtain.writeStrongBinder(aVar.asBinder());
                    iBinder.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    this.c.await(1L, TimeUnit.SECONDS);
                    if (!TextUtils.isEmpty(this.f2176a) && this.b != null) {
                        context.unbindService(this.b);
                    }
                } catch (Throwable th) {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            } catch (Throwable unused) {
            }
            return this.f2176a;
        }

        @Override // com.getui.gtc.dim.c.d.b, com.getui.gtc.dim.c.d.a
        public final /* bridge */ /* synthetic */ boolean c(Context context) {
            return super.c(context);
        }
    }

    public static class j extends c {
        public j() {
            super("com.meizu.flyme.openidsdk", "");
        }

        @Override // com.getui.gtc.dim.c.d.c, com.getui.gtc.dim.c.d.a
        public final boolean a(Context context) {
            if (super.a(context)) {
                c.b = true;
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
                        c.b = "0".equals(string);
                    } else {
                        c.b = false;
                    }
                } catch (Throwable unused) {
                    c.b = false;
                    return false;
                }
            }
            this.c = true;
            return c.b;
        }

        @Override // com.getui.gtc.dim.c.d.c, com.getui.gtc.dim.c.d.a
        public final String b(Context context) {
            this.f2178a = new String[]{com.umeng.commonsdk.statistics.idtracking.i.d};
            return super.b(context);
        }
    }

    public static class k implements a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f2183a;
        public boolean b;
        public String c;

        public k() {
            this.f2183a = false;
            this.b = false;
            this.c = null;
        }

        public /* synthetic */ k(byte b) {
            this();
        }

        @Override // com.getui.gtc.dim.c.d.a
        public final boolean a(Context context) {
            if (this.b) {
                return this.f2183a;
            }
            if (context == null) {
                return false;
            }
            try {
                PackageManager packageManager = context.getPackageManager();
                this.f2183a = (packageManager == null || packageManager.resolveContentProvider("cn.nubia.identity", 0) == null) ? false : true;
            } catch (Throwable unused) {
                this.f2183a = false;
            }
            this.b = true;
            return this.f2183a;
        }

        @Override // com.getui.gtc.dim.c.d.a
        public final String b(Context context) {
            Bundle bundleCall;
            try {
                if (TextUtils.isEmpty(this.c)) {
                    Uri uri = Uri.parse("content://cn.nubia.identity/identity");
                    if (Build.VERSION.SDK_INT >= 17) {
                        ContentProviderClient contentProviderClientAcquireContentProviderClient = context.getContentResolver().acquireContentProviderClient(uri);
                        if (contentProviderClientAcquireContentProviderClient != null) {
                            bundleCall = contentProviderClientAcquireContentProviderClient.call("getOAID", null, null);
                            try {
                                if (Build.VERSION.SDK_INT >= 24) {
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

        @Override // com.getui.gtc.dim.c.d.a
        public final boolean c(Context context) {
            return true;
        }
    }

    public static class l extends b {
        public String c;
        public String d;

        public l() {
            super("com.heytap.openid", "com.heytap.openid.IdentifyService", "action.com.heytap.openid.OPEN_ID_SERVICE", "com.heytap.openid.IOpenID");
        }

        @Override // com.getui.gtc.dim.c.d.b
        public final String a() {
            return "OUID";
        }

        @Override // com.getui.gtc.dim.c.d.b, com.getui.gtc.dim.c.d.a
        public final /* bridge */ /* synthetic */ boolean a(Context context) {
            return super.a(context);
        }

        @Override // com.getui.gtc.dim.c.d.b, com.getui.gtc.dim.c.d.a
        public final /* bridge */ /* synthetic */ String b(Context context) {
            return super.b(context);
        }

        @Override // com.getui.gtc.dim.c.d.b, com.getui.gtc.dim.c.d.a
        public final /* bridge */ /* synthetic */ boolean c(Context context) {
            return super.c(context);
        }

        @Override // com.getui.gtc.dim.c.d.b
        public final String d(Context context) {
            if (TextUtils.isEmpty(this.d)) {
                this.d = context.getPackageName();
            }
            return this.d;
        }

        @Override // com.getui.gtc.dim.c.d.b
        @SuppressLint({"PackageManagerGetSignatures"})
        public final String e(Context context) {
            if (TextUtils.isEmpty(this.c)) {
                try {
                    String strD = d(context);
                    this.d = strD;
                    Signature[] signatureArr = com.getui.gtc.dim.e.d.a(strD, 64).signatures;
                    if (signatureArr != null && signatureArr.length > 0) {
                        byte[] bArrDigest = MessageDigest.getInstance("SHA1").digest(signatureArr[0].toByteArray());
                        StringBuilder sb = new StringBuilder();
                        for (byte b : bArrDigest) {
                            sb.append(Integer.toHexString((b & 255) | 256).substring(1, 3));
                        }
                        this.c = sb.toString();
                    }
                } catch (Throwable unused) {
                }
            }
            return this.c;
        }
    }

    public static class m extends b {
        public m() {
            super("com.samsung.android.deviceidservice", "com.samsung.android.deviceidservice.DeviceIdService", null, "com.samsung.android.deviceidservice.IDeviceIdService");
        }

        @Override // com.getui.gtc.dim.c.d.b, com.getui.gtc.dim.c.d.a
        public final /* bridge */ /* synthetic */ boolean a(Context context) {
            return super.a(context);
        }

        @Override // com.getui.gtc.dim.c.d.b, com.getui.gtc.dim.c.d.a
        public final /* bridge */ /* synthetic */ String b(Context context) {
            return super.b(context);
        }

        @Override // com.getui.gtc.dim.c.d.b, com.getui.gtc.dim.c.d.a
        public final /* bridge */ /* synthetic */ boolean c(Context context) {
            return super.c(context);
        }
    }

    public static class n extends c {
        public n() {
            super("com.vivo.vms.IdProvider", "IdentifierId/OAID");
        }
    }

    public static class o implements a {
        public static String b;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Class<?> f2184a = null;

        @Override // com.getui.gtc.dim.c.d.a
        @SuppressLint({"PrivateApi"})
        public final boolean a(Context context) {
            try {
                this.f2184a = Class.forName("com.android.id.impl.IdProviderImpl");
                return true;
            } catch (Throwable unused) {
                return false;
            }
        }

        @Override // com.getui.gtc.dim.c.d.a
        public final String b(Context context) {
            if (TextUtils.isEmpty(b)) {
                try {
                    b = String.valueOf(this.f2184a.getMethod("getOAID", Context.class).invoke(this.f2184a.newInstance(), context));
                } catch (Throwable unused) {
                    b = null;
                }
            }
            return b;
        }

        @Override // com.getui.gtc.dim.c.d.a
        public final boolean c(Context context) {
            return true;
        }
    }

    public static class p extends b {
        public p() {
            super("com.zui.deviceidservice", "com.zui.deviceidservice.DeviceidService", null, IDeviceidInterface.Stub.DESCRIPTOR);
        }

        @Override // com.getui.gtc.dim.c.d.b, com.getui.gtc.dim.c.d.a
        public final /* bridge */ /* synthetic */ boolean a(Context context) {
            return super.a(context);
        }

        @Override // com.getui.gtc.dim.c.d.b, com.getui.gtc.dim.c.d.a
        public final /* bridge */ /* synthetic */ String b(Context context) {
            return super.b(context);
        }

        @Override // com.getui.gtc.dim.c.d.b, com.getui.gtc.dim.c.d.a
        public final /* bridge */ /* synthetic */ boolean c(Context context) {
            return super.c(context);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00f0  */
    static {
        /*
            Method dump skipped, instruction units count: 440
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.dim.c.d.<clinit>():void");
    }

    public static d a() {
        if (f2175e == null) {
            synchronized (d.class) {
                if (f2175e == null) {
                    f2175e = new d();
                }
            }
        }
        return f2175e;
    }

    public static boolean b() {
        try {
            if (b == null || f2174a == null) {
                return false;
            }
            return f2174a.a(b);
        } catch (Throwable unused) {
            return false;
        }
    }

    public static String c() {
        try {
            if (b != null && f2174a != null && c) {
                return f2174a.b(b);
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }
}
