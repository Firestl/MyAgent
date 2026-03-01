package com.bun.miitmdid.provider.freeme;

import XI.XI.K0.kM;
import XI.XI.K0.xo;
import android.content.Context;
import com.bun.miitmdid.provider.BaseProvider;
import com.netease.nis.sdkwrapper.Utils;

/* JADX INFO: loaded from: classes.dex */
public class FreemeProvider extends BaseProvider {
    public static final String TAG = "SDK call Freeme: ";
    public Context context;
    public String packagename;

    /* JADX INFO: renamed from: com.bun.miitmdid.provider.freeme.FreemeProvider$1, reason: invalid class name */
    public class AnonymousClass1 implements xo {
        public final /* synthetic */ kM val$idSupplier;

        public AnonymousClass1(kM kMVar) {
            this.val$idSupplier = kMVar;
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x003b  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x007d  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x0085  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x008d  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x004a A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:43:0x0032 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // XI.XI.K0.xo
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void connectSuccess(boolean r7) {
            /*
                r6 = this;
                if (r7 == 0) goto La5
                XI.XI.K0.kM r7 = r6.val$idSupplier
                XI.XI.K0.K0 r7 = (XI.XI.K0.K0) r7
                boolean r7 = r7.XI()
                if (r7 == 0) goto L96
                com.bun.miitmdid.provider.freeme.FreemeProvider r7 = com.bun.miitmdid.provider.freeme.FreemeProvider.this
                r0 = 1
                com.bun.miitmdid.provider.freeme.FreemeProvider.access$002(r7, r0)
                XI.XI.K0.kM r7 = r6.val$idSupplier
                XI.XI.K0.K0 r7 = (XI.XI.K0.K0) r7
                XI.XI.XI.XI r7 = r7.K0
                r0 = 0
                if (r7 == 0) goto L24
                java.lang.String r7 = r7.getOAID()     // Catch: android.os.RemoteException -> L20
                goto L25
            L20:
                r7 = move-exception
                r7.printStackTrace()
            L24:
                r7 = r0
            L25:
                XI.XI.K0.kM r1 = r6.val$idSupplier
                com.bun.miitmdid.provider.freeme.FreemeProvider r2 = com.bun.miitmdid.provider.freeme.FreemeProvider.this
                com.bun.miitmdid.provider.freeme.FreemeProvider.access$100(r2)
                XI.XI.K0.K0 r1 = (XI.XI.K0.K0) r1
                XI.XI.XI.XI r1 = r1.K0
                if (r1 == 0) goto L3b
                java.lang.String r1 = r1.getVAID()     // Catch: android.os.RemoteException -> L37
                goto L3c
            L37:
                r1 = move-exception
                r1.printStackTrace()
            L3b:
                r1 = r0
            L3c:
                XI.XI.K0.kM r2 = r6.val$idSupplier
                com.bun.miitmdid.provider.freeme.FreemeProvider r3 = com.bun.miitmdid.provider.freeme.FreemeProvider.this
                java.lang.String r3 = com.bun.miitmdid.provider.freeme.FreemeProvider.access$100(r3)
                XI.XI.K0.K0 r2 = (XI.XI.K0.K0) r2
                XI.XI.XI.XI r4 = r2.K0
                if (r4 == 0) goto L77
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: android.os.RemoteException -> L73
                r4.<init>()     // Catch: android.os.RemoteException -> L73
                java.lang.String r5 = "getAAID idsSupplier:"
                r4.append(r5)     // Catch: android.os.RemoteException -> L73
                XI.XI.XI.XI r5 = r2.K0     // Catch: android.os.RemoteException -> L73
                r4.append(r5)     // Catch: android.os.RemoteException -> L73
                r4.toString()     // Catch: android.os.RemoteException -> L73
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: android.os.RemoteException -> L73
                r4.<init>()     // Catch: android.os.RemoteException -> L73
                java.lang.String r5 = "getAAID pkgname:"
                r4.append(r5)     // Catch: android.os.RemoteException -> L73
                r4.append(r3)     // Catch: android.os.RemoteException -> L73
                r4.toString()     // Catch: android.os.RemoteException -> L73
                XI.XI.XI.XI r2 = r2.K0     // Catch: android.os.RemoteException -> L73
                java.lang.String r0 = r2.getAAID(r3)     // Catch: android.os.RemoteException -> L73
                goto L77
            L73:
                r2 = move-exception
                r2.printStackTrace()
            L77:
                com.bun.miitmdid.provider.freeme.FreemeProvider r2 = com.bun.miitmdid.provider.freeme.FreemeProvider.this
                java.lang.String r3 = ""
                if (r7 != 0) goto L7e
                r7 = r3
            L7e:
                com.bun.miitmdid.provider.freeme.FreemeProvider.access$202(r2, r7)
                com.bun.miitmdid.provider.freeme.FreemeProvider r7 = com.bun.miitmdid.provider.freeme.FreemeProvider.this
                if (r1 != 0) goto L86
                r1 = r3
            L86:
                com.bun.miitmdid.provider.freeme.FreemeProvider.access$302(r7, r1)
                com.bun.miitmdid.provider.freeme.FreemeProvider r7 = com.bun.miitmdid.provider.freeme.FreemeProvider.this
                if (r0 != 0) goto L8e
                r0 = r3
            L8e:
                com.bun.miitmdid.provider.freeme.FreemeProvider.access$402(r7, r0)
                com.bun.miitmdid.provider.freeme.FreemeProvider r7 = com.bun.miitmdid.provider.freeme.FreemeProvider.this
                r7.returnCallResult()
            L96:
                XI.XI.K0.kM r7 = r6.val$idSupplier
                XI.XI.K0.K0 r7 = (XI.XI.K0.K0) r7
                XI.XI.XI.XI r0 = r7.K0
                if (r0 == 0) goto La5
                android.content.Context r0 = r7.f1056XI
                android.content.ServiceConnection r7 = r7.xo
                r0.unbindService(r7)
            La5:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bun.miitmdid.provider.freeme.FreemeProvider.AnonymousClass1.connectSuccess(boolean):void");
        }
    }

    public FreemeProvider(Context context) {
        this.context = context;
        this.packagename = context.getPackageName();
    }

    public static /* synthetic */ boolean access$002(FreemeProvider freemeProvider, boolean z) {
        Object[] objArr = new Object[5];
        objArr[1] = freemeProvider;
        objArr[2] = Boolean.valueOf(z);
        objArr[3] = 53;
        objArr[4] = 1606976968525L;
        return ((Boolean) Utils.rL(objArr)).booleanValue();
    }

    public static /* synthetic */ String access$100(FreemeProvider freemeProvider) {
        Object[] objArr = new Object[4];
        objArr[1] = freemeProvider;
        objArr[2] = 54;
        objArr[3] = 1606976968526L;
        return (String) Utils.rL(objArr);
    }

    public static /* synthetic */ String access$202(FreemeProvider freemeProvider, String str) {
        Object[] objArr = new Object[5];
        objArr[1] = freemeProvider;
        objArr[2] = str;
        objArr[3] = 55;
        objArr[4] = 1606976968527L;
        return (String) Utils.rL(objArr);
    }

    public static /* synthetic */ String access$302(FreemeProvider freemeProvider, String str) {
        Object[] objArr = new Object[5];
        objArr[1] = freemeProvider;
        objArr[2] = str;
        objArr[3] = 56;
        objArr[4] = 1606976968528L;
        return (String) Utils.rL(objArr);
    }

    public static /* synthetic */ String access$402(FreemeProvider freemeProvider, String str) {
        Object[] objArr = new Object[5];
        objArr[1] = freemeProvider;
        objArr[2] = str;
        objArr[3] = 57;
        objArr[4] = 1606976968529L;
        return (String) Utils.rL(objArr);
    }

    @Override // com.bun.miitmdid.provider.BaseProvider
    public void doStart() {
        Utils.rL(new Object[]{this, 58, 1606976968530L});
    }

    @Override // com.bun.miitmdid.interfaces.IdSupplier
    public boolean isSupported() {
        return ((Boolean) Utils.rL(new Object[]{this, 59, 1606976968531L})).booleanValue();
    }
}
