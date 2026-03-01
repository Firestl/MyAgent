package io.dcloud.feature.uniapp.ui;

import com.taobao.weex.bridge.Invoker;
import com.taobao.weex.bridge.JavascriptInvokable;
import com.taobao.weex.ui.ComponentCreator;

/* JADX INFO: loaded from: classes3.dex */
public interface AbsIComponentHolder extends ComponentCreator, JavascriptInvokable {
    Invoker getPropertyInvoker(String str);

    void loadIfNonLazy();
}
