package io.dcloud.feature.unimp;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import io.dcloud.feature.sdk.IDCUniMPCallBack;
import io.dcloud.feature.sdk.IDCUniMPServer;

/* JADX INFO: loaded from: classes3.dex */
public class UniMPBinder {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IDCUniMPServer f6677a;
    public io.dcloud.feature.unimp.f.b b;
    public IDCUniMPCallBack c = new IDCUniMPCallBack.Stub() { // from class: io.dcloud.feature.unimp.UniMPBinder.1
        @Override // io.dcloud.feature.sdk.IDCUniMPCallBack
        public void callBack(String str, Bundle bundle) throws RemoteException {
            io.dcloud.feature.unimp.f.b bVar = UniMPBinder.this.b;
            if (bVar != null) {
                bVar.callBack(str, bundle);
            }
        }
    };

    public boolean a(IBinder iBinder, String str, String str2, String str3, io.dcloud.feature.unimp.f.b bVar) {
        IDCUniMPServer iDCUniMPServerAsInterface = IDCUniMPServer.Stub.asInterface(iBinder);
        this.f6677a = iDCUniMPServerAsInterface;
        try {
            iDCUniMPServerAsInterface.initConfig(str, str2, str3);
            this.b = bVar;
            this.f6677a.registerCallBack(this.c);
            return true;
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void b() {
        this.f6677a = null;
    }

    public IDCUniMPServer a() {
        return this.f6677a;
    }
}
