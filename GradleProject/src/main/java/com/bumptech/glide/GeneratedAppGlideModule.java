package com.bumptech.glide;

import com.bumptech.glide.manager.RequestManagerRetriever;
import com.bumptech.glide.module.AppGlideModule;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class GeneratedAppGlideModule extends AppGlideModule {
    public abstract Set<Class<?>> getExcludedModuleClasses();

    public RequestManagerRetriever.RequestManagerFactory getRequestManagerFactory() {
        return null;
    }
}
