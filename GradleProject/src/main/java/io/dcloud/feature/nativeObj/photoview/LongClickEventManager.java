package io.dcloud.feature.nativeObj.photoview;

import io.dcloud.common.DHInterface.IWebview;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class LongClickEventManager {
    public static LongClickEventManager instance;
    public Map<String, OnLongClickListener> clicks = new HashMap();

    public static abstract class OnLongClickListener {
        public String callbackIds;
        public IWebview pwebview;

        public OnLongClickListener(IWebview iWebview, String str) {
            this.pwebview = iWebview;
            this.callbackIds = str;
        }

        public String getCallbackIds() {
            return this.callbackIds;
        }

        public IWebview getPwebview() {
            return this.pwebview;
        }

        public abstract void onLongClickListener(JSONObject jSONObject);
    }

    public static LongClickEventManager getInstance() {
        if (instance == null) {
            synchronized (LongClickEventManager.class) {
                if (instance == null) {
                    instance = new LongClickEventManager();
                }
            }
        }
        return instance;
    }

    public void addOnlongClickListener(String str, OnLongClickListener onLongClickListener) {
        this.clicks.put(str, onLongClickListener);
    }

    public void fireEvent(JSONObject jSONObject) {
        Iterator<String> it = this.clicks.keySet().iterator();
        while (it.hasNext()) {
            OnLongClickListener onLongClickListener = this.clicks.get(it.next());
            if (onLongClickListener != null) {
                onLongClickListener.onLongClickListener(jSONObject);
            }
        }
    }

    public void removeOnLongClickListener(String str) {
        this.clicks.remove(str);
    }
}
