package io.dcloud.feature.weex.adapter;

import com.taobao.weex.appfram.websocket.IWebSocketAdapter;
import com.taobao.weex.appfram.websocket.IWebSocketAdapterFactory;

/* JADX INFO: loaded from: classes3.dex */
public class DefaultWebSocketAdapterFactory implements IWebSocketAdapterFactory {
    @Override // com.taobao.weex.appfram.websocket.IWebSocketAdapterFactory
    public IWebSocketAdapter createWebSocketAdapter() {
        return new DefaultWebSocketAdapter();
    }
}
