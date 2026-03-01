package io.dcloud.f.a;

import android.app.Application;
import io.dcloud.common.DHInterface.IConfusionMgr;
import io.dcloud.common.DHInterface.INativeAppInfo;
import java.lang.ref.SoftReference;

/* JADX INFO: loaded from: classes2.dex */
public class b implements INativeAppInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IConfusionMgr f6474a;
    public SoftReference<Application> b;

    public b(Application application) {
        a(application);
        a(this.f6474a);
    }

    private void a(Application application) {
        this.b = new SoftReference<>(application);
    }

    @Override // io.dcloud.common.DHInterface.INativeAppInfo
    public Application getApplication() {
        SoftReference<Application> softReference = this.b;
        if (softReference != null) {
            return softReference.get();
        }
        return null;
    }

    @Override // io.dcloud.common.DHInterface.INativeAppInfo
    public IConfusionMgr getCofusionMgr() {
        return this.f6474a;
    }

    private void a(IConfusionMgr iConfusionMgr) {
        if (iConfusionMgr == null) {
            iConfusionMgr = io.dcloud.f.b.b.c();
        }
        this.f6474a = iConfusionMgr;
    }
}
