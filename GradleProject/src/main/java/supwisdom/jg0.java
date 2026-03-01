package supwisdom;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.view.View;
import com.google.android.gms.common.internal.zal;
import com.google.android.gms.common.internal.zam;
import com.google.android.gms.common.internal.zaw;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class jg0 extends RemoteCreator<zam> {
    public static final jg0 c = new jg0();

    public jg0() {
        super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
    }

    public static View b(Context context, int i, int i2) throws RemoteCreator.RemoteCreatorException {
        return c.a(context, i, i2);
    }

    public final View a(Context context, int i, int i2) throws RemoteCreator.RemoteCreatorException {
        try {
            zaw zawVar = new zaw(i, i2, null);
            return (View) ObjectWrapper.unwrap(a(context).zaa(ObjectWrapper.wrap(context), zawVar));
        } catch (Exception e2) {
            StringBuilder sb = new StringBuilder(64);
            sb.append("Could not get button with size ");
            sb.append(i);
            sb.append(" and color ");
            sb.append(i2);
            throw new RemoteCreator.RemoteCreatorException(sb.toString(), e2);
        }
    }

    @Override // com.google.android.gms.dynamic.RemoteCreator
    public final /* synthetic */ zam a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.ISignInButtonCreator");
        if (iInterfaceQueryLocalInterface instanceof zam) {
            return (zam) iInterfaceQueryLocalInterface;
        }
        return new zal(iBinder);
    }
}
