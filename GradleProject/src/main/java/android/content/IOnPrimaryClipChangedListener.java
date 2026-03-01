package android.content;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes.dex */
public interface IOnPrimaryClipChangedListener extends IInterface {

    /* JADX INFO: compiled from: Proguard */
    public static class Default implements IOnPrimaryClipChangedListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.content.IOnPrimaryClipChangedListener
        public void dispatchPrimaryClipChanged() {
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public static abstract class Stub extends Binder implements IOnPrimaryClipChangedListener {
        public static final String DESCRIPTOR = "android.content.IOnPrimaryClipChangedListener";
        public static final int TRANSACTION_dispatchPrimaryClipChanged = 1;

        /* JADX INFO: compiled from: Proguard */
        public static class Proxy implements IOnPrimaryClipChangedListener {
            public static IOnPrimaryClipChangedListener sDefaultImpl;
            public IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.content.IOnPrimaryClipChangedListener
            public void dispatchPrimaryClipChanged() {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().dispatchPrimaryClipChanged();
                } finally {
                    parcelObtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOnPrimaryClipChangedListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IOnPrimaryClipChangedListener)) ? new Proxy(iBinder) : (IOnPrimaryClipChangedListener) iInterfaceQueryLocalInterface;
        }

        public static IOnPrimaryClipChangedListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IOnPrimaryClipChangedListener iOnPrimaryClipChangedListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iOnPrimaryClipChangedListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iOnPrimaryClipChangedListener;
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                dispatchPrimaryClipChanged();
                return true;
            }
            if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeString(DESCRIPTOR);
            return true;
        }
    }

    void dispatchPrimaryClipChanged();
}
