package io.dcloud.common.adapter.io;

import android.content.Context;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AdaService {
    public static final String TAG = "AdaService";
    public static HashMap<String, AdaService> mServicesHandler = new HashMap<>(2);
    public Context mContextWrapper;
    public String mServiceName;

    public AdaService(Context context, String str) {
        this.mContextWrapper = null;
        this.mServiceName = null;
        this.mContextWrapper = context;
        this.mServiceName = str;
    }

    public static final AdaService getServiceListener(String str) {
        return mServicesHandler.get(str);
    }

    public static final void removeServiceListener(String str) {
        mServicesHandler.remove(str);
    }

    public abstract void onDestroy();

    public abstract void onExecute();
}
