package io.dcloud.js.geolocation;

import android.content.Context;
import io.dcloud.common.DHInterface.IReflectAble;
import io.dcloud.common.DHInterface.IWebview;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public abstract class GeoManagerBase implements IReflectAble {
    public ArrayList<String> keySet;
    public Context mContext;

    public GeoManagerBase(Context context) {
        this.keySet = null;
        this.mContext = context;
        this.keySet = new ArrayList<>();
    }

    public abstract String execute(IWebview iWebview, String str, String[] strArr);

    public boolean hasKey(String str) {
        return this.keySet.contains(str);
    }

    public abstract void onDestroy();
}
