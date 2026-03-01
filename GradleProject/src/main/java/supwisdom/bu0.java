package supwisdom;

import com.hihonor.push.framework.aidl.IMessageEntity;
import com.hihonor.push.framework.aidl.entity.RequestHeader;
import com.hihonor.push.sdk.common.data.ApiException;

/* JADX INFO: loaded from: classes.dex */
public abstract class bu0<TResult> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public hu0<TResult> f7101a;
    public final String b;
    public final IMessageEntity c;
    public final mu0 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public RequestHeader f7102e;

    public abstract void a(ApiException apiException, Object obj);

    public final void b(ApiException apiException, Object obj) {
        if (this.f7101a != null) {
            a(apiException, obj);
            return;
        }
        String str = "This Task has been canceled, uri:" + this.b;
    }
}
