package io.dcloud.feature.sdk;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import io.dcloud.feature.sdk.IDCUniMPCallBack;

/* JADX INFO: loaded from: classes3.dex */
public interface IDCUniMPServer extends IInterface {

    public static class Default implements IDCUniMPServer {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // io.dcloud.feature.sdk.IDCUniMPServer
        public void callBack(String str, Bundle bundle) throws RemoteException {
        }

        @Override // io.dcloud.feature.sdk.IDCUniMPServer
        public boolean closeCurrentApp() throws RemoteException {
            return false;
        }

        @Override // io.dcloud.feature.sdk.IDCUniMPServer
        public String execute(String str, Bundle bundle) throws RemoteException {
            return null;
        }

        @Override // io.dcloud.feature.sdk.IDCUniMPServer
        public String getCurrentPageUrl() throws RemoteException {
            return null;
        }

        @Override // io.dcloud.feature.sdk.IDCUniMPServer
        public String getRunningAppid() throws RemoteException {
            return null;
        }

        @Override // io.dcloud.feature.sdk.IDCUniMPServer
        public void initConfig(String str, String str2, String str3) throws RemoteException {
        }

        @Override // io.dcloud.feature.sdk.IDCUniMPServer
        public void preUniMP(String str) throws RemoteException {
        }

        @Override // io.dcloud.feature.sdk.IDCUniMPServer
        public void registerCallBack(IDCUniMPCallBack iDCUniMPCallBack) throws RemoteException {
        }

        @Override // io.dcloud.feature.sdk.IDCUniMPServer
        public void setRunningAppid(String str) throws RemoteException {
        }

        @Override // io.dcloud.feature.sdk.IDCUniMPServer
        public boolean stopApp(String str) throws RemoteException {
            return false;
        }

        @Override // io.dcloud.feature.sdk.IDCUniMPServer
        public void unregisterCallBack(IDCUniMPCallBack iDCUniMPCallBack) throws RemoteException {
        }
    }

    public static abstract class Stub extends Binder implements IDCUniMPServer {

        public static class Proxy implements IDCUniMPServer {
            public static IDCUniMPServer b;

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public IBinder f6597a;

            public Proxy(IBinder iBinder) {
                this.f6597a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f6597a;
            }

            @Override // io.dcloud.feature.sdk.IDCUniMPServer
            public void callBack(String str, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("io.dcloud.feature.sdk.IDCUniMPServer");
                    parcelObtain.writeString(str);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f6597a.transact(4, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().callBack(str, bundle);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // io.dcloud.feature.sdk.IDCUniMPServer
            public boolean closeCurrentApp() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("io.dcloud.feature.sdk.IDCUniMPServer");
                    if (!this.f6597a.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().closeCurrentApp();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // io.dcloud.feature.sdk.IDCUniMPServer
            public String execute(String str, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("io.dcloud.feature.sdk.IDCUniMPServer");
                    parcelObtain.writeString(str);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.f6597a.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().execute(str, bundle);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // io.dcloud.feature.sdk.IDCUniMPServer
            public String getCurrentPageUrl() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("io.dcloud.feature.sdk.IDCUniMPServer");
                    if (!this.f6597a.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentPageUrl();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return "io.dcloud.feature.sdk.IDCUniMPServer";
            }

            @Override // io.dcloud.feature.sdk.IDCUniMPServer
            public String getRunningAppid() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("io.dcloud.feature.sdk.IDCUniMPServer");
                    if (!this.f6597a.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRunningAppid();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // io.dcloud.feature.sdk.IDCUniMPServer
            public void initConfig(String str, String str2, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("io.dcloud.feature.sdk.IDCUniMPServer");
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (this.f6597a.transact(3, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().initConfig(str, str2, str3);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // io.dcloud.feature.sdk.IDCUniMPServer
            public void preUniMP(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("io.dcloud.feature.sdk.IDCUniMPServer");
                    parcelObtain.writeString(str);
                    if (this.f6597a.transact(11, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().preUniMP(str);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // io.dcloud.feature.sdk.IDCUniMPServer
            public void registerCallBack(IDCUniMPCallBack iDCUniMPCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("io.dcloud.feature.sdk.IDCUniMPServer");
                    parcelObtain.writeStrongBinder(iDCUniMPCallBack != null ? iDCUniMPCallBack.asBinder() : null);
                    if (this.f6597a.transact(5, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().registerCallBack(iDCUniMPCallBack);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // io.dcloud.feature.sdk.IDCUniMPServer
            public void setRunningAppid(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("io.dcloud.feature.sdk.IDCUniMPServer");
                    parcelObtain.writeString(str);
                    if (this.f6597a.transact(8, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().setRunningAppid(str);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // io.dcloud.feature.sdk.IDCUniMPServer
            public boolean stopApp(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("io.dcloud.feature.sdk.IDCUniMPServer");
                    parcelObtain.writeString(str);
                    if (!this.f6597a.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stopApp(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // io.dcloud.feature.sdk.IDCUniMPServer
            public void unregisterCallBack(IDCUniMPCallBack iDCUniMPCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("io.dcloud.feature.sdk.IDCUniMPServer");
                    parcelObtain.writeStrongBinder(iDCUniMPCallBack != null ? iDCUniMPCallBack.asBinder() : null);
                    if (this.f6597a.transact(6, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterCallBack(iDCUniMPCallBack);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "io.dcloud.feature.sdk.IDCUniMPServer");
        }

        public static IDCUniMPServer asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("io.dcloud.feature.sdk.IDCUniMPServer");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IDCUniMPServer)) ? new Proxy(iBinder) : (IDCUniMPServer) iInterfaceQueryLocalInterface;
        }

        public static IDCUniMPServer getDefaultImpl() {
            return Proxy.b;
        }

        public static boolean setDefaultImpl(IDCUniMPServer iDCUniMPServer) {
            if (Proxy.b != null || iDCUniMPServer == null) {
                return false;
            }
            Proxy.b = iDCUniMPServer;
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("io.dcloud.feature.sdk.IDCUniMPServer");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("io.dcloud.feature.sdk.IDCUniMPServer");
                    boolean zStopApp = stopApp(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zStopApp ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface("io.dcloud.feature.sdk.IDCUniMPServer");
                    boolean zCloseCurrentApp = closeCurrentApp();
                    parcel2.writeNoException();
                    parcel2.writeInt(zCloseCurrentApp ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface("io.dcloud.feature.sdk.IDCUniMPServer");
                    initConfig(parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface("io.dcloud.feature.sdk.IDCUniMPServer");
                    callBack(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface("io.dcloud.feature.sdk.IDCUniMPServer");
                    registerCallBack(IDCUniMPCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface("io.dcloud.feature.sdk.IDCUniMPServer");
                    unregisterCallBack(IDCUniMPCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface("io.dcloud.feature.sdk.IDCUniMPServer");
                    String runningAppid = getRunningAppid();
                    parcel2.writeNoException();
                    parcel2.writeString(runningAppid);
                    return true;
                case 8:
                    parcel.enforceInterface("io.dcloud.feature.sdk.IDCUniMPServer");
                    setRunningAppid(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface("io.dcloud.feature.sdk.IDCUniMPServer");
                    String currentPageUrl = getCurrentPageUrl();
                    parcel2.writeNoException();
                    parcel2.writeString(currentPageUrl);
                    return true;
                case 10:
                    parcel.enforceInterface("io.dcloud.feature.sdk.IDCUniMPServer");
                    String strExecute = execute(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeString(strExecute);
                    return true;
                case 11:
                    parcel.enforceInterface("io.dcloud.feature.sdk.IDCUniMPServer");
                    preUniMP(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void callBack(String str, Bundle bundle) throws RemoteException;

    boolean closeCurrentApp() throws RemoteException;

    String execute(String str, Bundle bundle) throws RemoteException;

    String getCurrentPageUrl() throws RemoteException;

    String getRunningAppid() throws RemoteException;

    void initConfig(String str, String str2, String str3) throws RemoteException;

    void preUniMP(String str) throws RemoteException;

    void registerCallBack(IDCUniMPCallBack iDCUniMPCallBack) throws RemoteException;

    void setRunningAppid(String str) throws RemoteException;

    boolean stopApp(String str) throws RemoteException;

    void unregisterCallBack(IDCUniMPCallBack iDCUniMPCallBack) throws RemoteException;
}
