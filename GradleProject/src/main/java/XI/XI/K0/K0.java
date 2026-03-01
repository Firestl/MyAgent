package XI.XI.K0;

import XI.XI.XI.XI;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public class K0 implements kM {
    public XI.XI.XI.XI K0;

    /* JADX INFO: renamed from: XI, reason: collision with root package name */
    public Context f1056XI;
    public xo kM;
    public ServiceConnection xo = new XI();

    public class XI implements ServiceConnection {
        public XI() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            XI.XI.XI.XI c0005xi;
            K0 k0 = K0.this;
            int i = XI.AbstractBinderC0004XI.f1059XI;
            if (iBinder == null) {
                c0005xi = null;
            } else {
                IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.android.creator.IdsSupplier");
                c0005xi = (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof XI.XI.XI.XI)) ? new XI.AbstractBinderC0004XI.C0005XI(iBinder) : (XI.XI.XI.XI) iInterfaceQueryLocalInterface;
            }
            k0.K0 = c0005xi;
            K0.this.kM.connectSuccess(true);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            K0.this.K0 = null;
        }
    }

    public K0(Context context) {
        this.f1056XI = context;
    }

    public boolean XI() {
        boolean zIsSupported;
        XI.XI.XI.XI xi = this.K0;
        if (xi != null) {
            try {
                zIsSupported = xi.isSupported();
            } catch (RemoteException e2) {
                String str = "isSupported exception:" + e2.getMessage();
                e2.printStackTrace();
                zIsSupported = false;
            }
        } else {
            zIsSupported = false;
        }
        String str2 = "isSupported:" + this.K0;
        String str3 = "isSupported ret:" + zIsSupported;
        return zIsSupported;
    }
}
