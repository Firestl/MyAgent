package io.dcloud.feature.sdk;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import io.dcloud.feature.sdk.IDCUniMPCallBack;
import io.src.dcloud.adapter.DCloudBaseService;

/* JADX INFO: loaded from: classes3.dex */
public abstract class DCUniMPIntentService extends DCloudBaseService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Binder f6592a = new IDCUniMPCallBack.Stub() { // from class: io.dcloud.feature.sdk.DCUniMPIntentService.1
        @Override // io.dcloud.feature.sdk.IDCUniMPCallBack
        public void callBack(String str, Bundle bundle) throws RemoteException {
            str.hashCode();
            if (str.equals("unimp_connection") && DCUniMPIntentService.this.b != null) {
                Message message = new Message();
                message.arg1 = 8001;
                DCUniMPIntentService.this.b.sendMessage(message);
            }
        }
    };
    public Handler b = null;

    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.arg1 == 8001) {
                DCUniMPIntentService.this.onUniMPServiceDisconnected();
            }
            super.handleMessage(message);
        }
    }

    @Override // io.src.dcloud.adapter.DCloudBaseService
    public IBinder onBindImpl(Intent intent) {
        return this.f6592a;
    }

    @Override // io.src.dcloud.adapter.DCloudBaseService
    public void onCreateImpl() {
        this.b = new a(getMainLooper());
        super.onCreateImpl();
    }

    @Override // io.src.dcloud.adapter.DCloudBaseService
    public void onDestroyImpl() {
        super.onDestroyImpl();
    }

    @Override // io.src.dcloud.adapter.DCloudBaseService
    public int onStartCommandImpl(Intent intent, int i, int i2) {
        return super.onStartCommandImpl(intent, i, i2);
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public abstract void onUniMPServiceDisconnected();

    @Override // android.content.ContextWrapper, android.content.Context
    public boolean stopService(Intent intent) {
        return super.stopService(intent);
    }
}
