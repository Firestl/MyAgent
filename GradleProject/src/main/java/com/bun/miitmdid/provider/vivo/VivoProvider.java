package com.bun.miitmdid.provider.vivo;

import android.content.Context;
import com.bun.miitmdid.provider.BaseProvider;
import com.netease.nis.sdkwrapper.Utils;

/* JADX INFO: loaded from: classes.dex */
public class VivoProvider extends BaseProvider {
    public static final String TAG = "SDK call Vivo: ";
    public String appID;
    public Context context;

    public VivoProvider(Context context, String str) {
        String str2 = "constructer is called, appid is " + str;
        this.context = context;
        this.appID = str;
    }

    @Override // com.bun.miitmdid.provider.BaseProvider
    public void doStart() {
        Utils.rL(new Object[]{this, 94, 1606976968566L});
    }

    @Override // com.bun.miitmdid.interfaces.IdSupplier
    public boolean isSupported() {
        return ((Boolean) Utils.rL(new Object[]{this, 95, 1606976968567L})).booleanValue();
    }
}
