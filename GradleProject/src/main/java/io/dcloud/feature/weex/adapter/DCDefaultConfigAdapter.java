package io.dcloud.feature.weex.adapter;

import com.taobao.weex.adapter.IWXConfigAdapter;

/* JADX INFO: loaded from: classes3.dex */
public class DCDefaultConfigAdapter implements IWXConfigAdapter {
    @Override // com.taobao.weex.adapter.IWXConfigAdapter
    public boolean checkMode(String str) {
        return false;
    }

    @Override // com.taobao.weex.adapter.IWXConfigAdapter
    public String getConfig(String str, String str2, String str3) {
        return str3;
    }

    @Override // com.taobao.weex.adapter.IWXConfigAdapter
    public String getConfigWhenInit(String str, String str2, String str3) {
        return str3;
    }
}
