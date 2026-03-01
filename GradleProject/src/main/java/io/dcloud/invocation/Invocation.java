package io.dcloud.invocation;

import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.IWebview;

/* JADX INFO: loaded from: classes3.dex */
public class Invocation implements IFeature {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a f6704a;

    @Override // io.dcloud.common.DHInterface.IFeature
    public void dispose(String str) {
        this.f6704a.a(str);
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public String execute(IWebview iWebview, String str, String[] strArr) {
        return this.f6704a.a(iWebview, str, strArr);
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void init(AbsMgr absMgr, String str) {
        this.f6704a = new a(absMgr);
    }
}
