package com.getui.gtc.base.log.d;

import com.getui.gtc.base.log.ILogController;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class b implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<ILogController> f2138a = new ArrayList();

    @Override // com.getui.gtc.base.log.d.a
    public final void a(int i, String str, String str2, Throwable th) {
        for (ILogController iLogController : this.f2138a) {
            try {
                if (iLogController.isLoggable(i, str)) {
                    iLogController.log(i, str, str2, th);
                }
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
    }

    @Override // com.getui.gtc.base.log.d.a
    public final void a(ILogController iLogController) {
        if (iLogController == null) {
            return;
        }
        this.f2138a.add(iLogController);
    }

    @Override // com.getui.gtc.base.log.d.a
    public final void b(ILogController iLogController) {
        if (this.f2138a.contains(iLogController)) {
            this.f2138a.remove(iLogController);
        }
    }
}
