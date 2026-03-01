package io.dcloud.feature.uniapp.common;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes3.dex */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Deprecated
public @interface UniModuleAnno {
    @Deprecated
    boolean moduleMethod() default true;

    boolean runOnUIThread() default true;
}
