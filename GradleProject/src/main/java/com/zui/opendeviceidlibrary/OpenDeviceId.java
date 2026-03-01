package com.zui.opendeviceidlibrary;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.zui.deviceidservice.IDeviceidInterface;

/* JADX INFO: loaded from: classes2.dex */
public class OpenDeviceId {
    public static boolean DBG = false;
    public static String TAG = "OpenDeviceId library";
    public ServiceConnection mConnection;
    public IDeviceidInterface mDeviceidInterface;
    public Context mContext = null;
    public CallBack mCallerCallBack = null;

    public interface CallBack<T> {
        void serviceConnected(T t, OpenDeviceId openDeviceId);
    }

    private void logPrintE(String str) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logPrintI(String str) {
    }

    public String getAAID() {
        Context context = this.mContext;
        if (context == null) {
            logPrintI("Context is null.");
            throw new IllegalArgumentException("Context is null, must be new OpenDeviceId first");
        }
        String packageName = context.getPackageName();
        logPrintI("liufeng, getAAID package：" + packageName);
        if (packageName == null || packageName.equals("")) {
            logPrintI("input package is null!");
            return null;
        }
        try {
            IDeviceidInterface iDeviceidInterface = this.mDeviceidInterface;
            if (iDeviceidInterface == null) {
                return null;
            }
            String aaid = iDeviceidInterface.getAAID(packageName);
            return ((aaid == null || "".equals(aaid)) && this.mDeviceidInterface.createAAIDForPackageName(packageName)) ? this.mDeviceidInterface.getAAID(packageName) : aaid;
        } catch (RemoteException unused) {
            logPrintE("getAAID error, RemoteException!");
            return null;
        }
    }

    public String getOAID() {
        if (this.mContext == null) {
            logPrintE("Context is null.");
            throw new IllegalArgumentException("Context is null, must be new OpenDeviceId first");
        }
        try {
            IDeviceidInterface iDeviceidInterface = this.mDeviceidInterface;
            if (iDeviceidInterface != null) {
                return iDeviceidInterface.getOAID();
            }
            return null;
        } catch (RemoteException e2) {
            logPrintE("getOAID error, RemoteException!");
            e2.printStackTrace();
            return null;
        }
    }

    public String getUDID() {
        if (this.mContext == null) {
            logPrintE("Context is null.");
            throw new IllegalArgumentException("Context is null, must be new OpenDeviceId first");
        }
        try {
            IDeviceidInterface iDeviceidInterface = this.mDeviceidInterface;
            if (iDeviceidInterface != null) {
                return iDeviceidInterface.getUDID();
            }
            return null;
        } catch (RemoteException e2) {
            logPrintE("getUDID error, RemoteException!");
            e2.printStackTrace();
            return null;
        } catch (Exception e3) {
            logPrintE("getUDID error, Exception!");
            e3.printStackTrace();
            return null;
        }
    }

    public String getVAID() {
        Context context = this.mContext;
        if (context == null) {
            logPrintI("Context is null.");
            throw new IllegalArgumentException("Context is null, must be new OpenDeviceId first");
        }
        String packageName = context.getPackageName();
        logPrintI("liufeng, getVAID package：" + packageName);
        if (packageName == null || packageName.equals("")) {
            logPrintI("input package is null!");
            return null;
        }
        try {
            IDeviceidInterface iDeviceidInterface = this.mDeviceidInterface;
            if (iDeviceidInterface != null) {
                return iDeviceidInterface.getVAID(packageName);
            }
            return null;
        } catch (RemoteException e2) {
            logPrintE("getVAID error, RemoteException!");
            e2.printStackTrace();
            return null;
        }
    }

    public int init(Context context, CallBack<String> callBack) {
        if (context == null) {
            throw new NullPointerException("Context can not be null.");
        }
        this.mContext = context;
        this.mCallerCallBack = callBack;
        this.mConnection = new ServiceConnection() { // from class: com.zui.opendeviceidlibrary.OpenDeviceId.1
            @Override // android.content.ServiceConnection
            public synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                OpenDeviceId.this.mDeviceidInterface = IDeviceidInterface.Stub.asInterface(iBinder);
                if (OpenDeviceId.this.mCallerCallBack != null) {
                    OpenDeviceId.this.mCallerCallBack.serviceConnected("Deviceid Service Connected", OpenDeviceId.this);
                }
                OpenDeviceId.this.logPrintI("Service onServiceConnected");
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                OpenDeviceId.this.mDeviceidInterface = null;
                OpenDeviceId.this.logPrintI("Service onServiceDisconnected");
            }
        };
        Intent intent = new Intent();
        intent.setClassName("com.zui.deviceidservice", "com.zui.deviceidservice.DeviceidService");
        if (this.mContext.bindService(intent, this.mConnection, 1)) {
            logPrintI("bindService Successful!");
            return 1;
        }
        logPrintI("bindService Failed!");
        return -1;
    }

    public boolean isSupported() {
        try {
            if (this.mDeviceidInterface == null) {
                return false;
            }
            logPrintI("Device support opendeviceid");
            return this.mDeviceidInterface.isSupport();
        } catch (RemoteException unused) {
            logPrintE("isSupport error, RemoteException!");
            return false;
        }
    }

    public void setLogEnable(boolean z) {
        DBG = z;
    }

    public void shutdown() {
        try {
            this.mContext.unbindService(this.mConnection);
            logPrintI("unBind Service successful");
        } catch (IllegalArgumentException unused) {
            logPrintE("unBind Service exception");
        }
        this.mDeviceidInterface = null;
    }
}
