package com.sangfor.dx.rop.annotation;

import com.baidu.idl.face.platform.common.ConstantHelper;
import com.igexin.push.core.b;
import supwisdom.t61;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public enum AnnotationVisibility implements t61 {
    RUNTIME(b.Z),
    BUILD("build"),
    SYSTEM(ConstantHelper.LOG_OS),
    EMBEDDED("embedded");

    public final String human;

    AnnotationVisibility(String str) {
        this.human = str;
    }

    @Override // supwisdom.t61
    public String toHuman() {
        return this.human;
    }
}
