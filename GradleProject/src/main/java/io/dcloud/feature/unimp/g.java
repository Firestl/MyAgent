package io.dcloud.feature.unimp;

import io.dcloud.feature.sdk.Interface.IUniMP;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class g implements IUniMP {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f6698a;
    public io.dcloud.feature.unimp.f.a b;

    public g(String str, io.dcloud.feature.unimp.f.a aVar) {
        this.f6698a = str;
        this.b = aVar;
        new ArrayList();
    }

    @Override // io.dcloud.feature.sdk.Interface.IUniMP
    public boolean closeUniMP() {
        io.dcloud.feature.unimp.f.a aVar = this.b;
        if (aVar != null) {
            return aVar.b(this.f6698a);
        }
        return false;
    }

    @Override // io.dcloud.feature.sdk.Interface.IUniMP
    public String getAppid() {
        return this.f6698a;
    }

    @Override // io.dcloud.feature.sdk.Interface.IUniMP
    public String getCurrentPageUrl() {
        io.dcloud.feature.unimp.f.a aVar = this.b;
        if (aVar != null) {
            return aVar.f(this.f6698a);
        }
        return null;
    }

    @Override // io.dcloud.feature.sdk.Interface.IUniMP
    public boolean hideUniMP() {
        io.dcloud.feature.unimp.f.a aVar = this.b;
        if (aVar != null) {
            return aVar.e(this.f6698a);
        }
        return false;
    }

    @Override // io.dcloud.feature.sdk.Interface.IUniMP
    public boolean isRuning() {
        io.dcloud.feature.unimp.f.a aVar = this.b;
        if (aVar != null) {
            return aVar.d(this.f6698a);
        }
        return false;
    }

    @Override // io.dcloud.feature.sdk.Interface.IUniMP
    public boolean sendUniMPEvent(String str, Object obj) {
        io.dcloud.feature.unimp.f.a aVar = this.b;
        if (aVar != null) {
            return aVar.a(this.f6698a, str, obj);
        }
        return false;
    }

    @Override // io.dcloud.feature.sdk.Interface.IUniMP
    public boolean showUniMP() {
        io.dcloud.feature.unimp.f.a aVar = this.b;
        if (aVar != null) {
            return aVar.a(this.f6698a);
        }
        return false;
    }
}
