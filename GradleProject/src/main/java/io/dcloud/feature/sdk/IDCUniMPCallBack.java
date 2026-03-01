package io.dcloud.feature.sdk;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IDCUniMPCallBack extends IInterface {

    public static class Default implements IDCUniMPCallBack {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // io.dcloud.feature.sdk.IDCUniMPCallBack
        public void callBack(String str, Bundle bundle) throws RemoteException {
        }
    }

    public static abstract class Stub extends Binder implements IDCUniMPCallBack {

        public static class Proxy implements IDCUniMPCallBack {
            public static IDCUniMPCallBack b;

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public IBinder f6596a;

            public Proxy(IBinder iBinder) {
                this.f6596a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f6596a;
            }

            @Override // io.dcloud.feature.sdk.IDCUniMPCallBack
            public void callBack(String str, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("io.dcloud.feature.sdk.IDCUniMPCallBack");
                    parcelObtain.writeString(str);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f6596a.transact(1, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().callBack(str, bundle);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return "io.dcloud.feature.sdk.IDCUniMPCallBack";
            }
        }

        public Stub() {
            attachInterface(this, "io.dcloud.feature.sdk.IDCUniMPCallBack");
        }

        public static IDCUniMPCallBack asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("io.dcloud.feature.sdk.IDCUniMPCallBack");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IDCUniMPCallBack)) ? new Proxy(iBinder) : (IDCUniMPCallBack) iInterfaceQueryLocalInterface;
        }

        public static IDCUniMPCallBack getDefaultImpl() {
            return Proxy.b;
        }

        public static boolean setDefaultImpl(IDCUniMPCallBack iDCUniMPCallBack) {
            if (Proxy.b != null || iDCUniMPCallBack == null) {
                return false;
            }
            Proxy.b = iDCUniMPCallBack;
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("io.dcloud.feature.sdk.IDCUniMPCallBack");
                return true;
            }
            parcel.enforceInterface("io.dcloud.feature.sdk.IDCUniMPCallBack");
            callBack(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            return true;
        }
    }

    void callBack(String str, Bundle bundle) throws RemoteException;
}
