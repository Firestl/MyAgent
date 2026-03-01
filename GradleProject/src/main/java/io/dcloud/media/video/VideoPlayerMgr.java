package io.dcloud.media.video;

import android.view.ViewGroup;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IFrameView;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.AdaFrameItem;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class VideoPlayerMgr {
    public static VideoPlayerMgr mInstance;
    public ViewGroup.LayoutParams _vlps;
    public AbsMgr mFeatureMgr;
    public HashMap<String, DHVideoFrameItem> mPlayerCaches = new HashMap<>();

    public static VideoPlayerMgr getInstance() {
        if (mInstance == null) {
            mInstance = new VideoPlayerMgr();
        }
        return mInstance;
    }

    public void addEventListener(IWebview iWebview, String str, String str2, String str3, String str4) {
        HashMap<String, DHVideoFrameItem> map = this.mPlayerCaches;
        DHVideoFrameItem dHVideoFrameItem = (map == null || !map.containsKey(str)) ? null : this.mPlayerCaches.get(str);
        if (dHVideoFrameItem != null) {
            dHVideoFrameItem.addEventListener(str2, str3, str4);
        }
    }

    public AdaFrameItem appendVideoPlayer(String str, IFrameView iFrameView) {
        HashMap<String, DHVideoFrameItem> map = this.mPlayerCaches;
        DHVideoFrameItem dHVideoFrameItem = (map == null || !map.containsKey(str)) ? null : this.mPlayerCaches.get(str);
        if (dHVideoFrameItem != null) {
            dHVideoFrameItem.appendToFrame(iFrameView);
        }
        return dHVideoFrameItem;
    }

    public void close(IWebview iWebview, String str) {
        HashMap<String, DHVideoFrameItem> map = this.mPlayerCaches;
        DHVideoFrameItem dHVideoFrameItem = (map == null || !map.containsKey(str)) ? null : this.mPlayerCaches.get(str);
        if (dHVideoFrameItem != null) {
            dHVideoFrameItem.close();
            dHVideoFrameItem.removeFrameItem();
        }
    }

    public void createVideoPlayer(IWebview iWebview, String str, JSONArray jSONArray, JSONObject jSONObject, String str2, boolean z) {
        DHVideoFrameItem dHVideoFrameItem;
        if (this.mPlayerCaches.containsKey(str)) {
            dHVideoFrameItem = this.mPlayerCaches.get(str);
        } else {
            DHVideoFrameItem dHVideoFrameItem2 = new DHVideoFrameItem(iWebview.getContext(), str, iWebview, jSONArray, jSONObject, str2);
            this.mPlayerCaches.put(str, dHVideoFrameItem2);
            dHVideoFrameItem = dHVideoFrameItem2;
        }
        if (z) {
            return;
        }
        dHVideoFrameItem.appendToFrame(iWebview.obtainFrameView());
    }

    public void exitFullScreen(String str) {
        HashMap<String, DHVideoFrameItem> map = this.mPlayerCaches;
        DHVideoFrameItem dHVideoFrameItem = (map == null || !map.containsKey(str)) ? null : this.mPlayerCaches.get(str);
        if (dHVideoFrameItem != null) {
            dHVideoFrameItem.exitFullScreen();
        }
    }

    public JSONObject findVideoPlayer(String str) {
        DHVideoFrameItem dHVideoFrameItem;
        if (str == null) {
            return null;
        }
        HashMap<String, DHVideoFrameItem> map = this.mPlayerCaches;
        if (map != null) {
            Iterator<String> it = map.keySet().iterator();
            dHVideoFrameItem = null;
            while (it.hasNext()) {
                DHVideoFrameItem dHVideoFrameItem2 = this.mPlayerCaches.get(it.next());
                if (str.equals(dHVideoFrameItem2.getUserId())) {
                    dHVideoFrameItem = dHVideoFrameItem2;
                }
            }
        } else {
            dHVideoFrameItem = null;
        }
        if (dHVideoFrameItem != null) {
            try {
                return new JSONObject("{'name':'" + dHVideoFrameItem.getUserId() + "','uid':'" + dHVideoFrameItem.getmId() + "'}");
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public IWebview findWebview(IWebview iWebview, String str) {
        IApp iAppObtainApp;
        if (iWebview == null || (iAppObtainApp = iWebview.obtainApp()) == null) {
            return null;
        }
        Object objProcessEvent = this.mFeatureMgr.processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[]{iWebview, "ui", "findWebview", new String[]{iAppObtainApp.obtainAppId(), str}});
        if (objProcessEvent instanceof IWebview) {
            return (IWebview) objProcessEvent;
        }
        return null;
    }

    public void hidden(String str) {
        HashMap<String, DHVideoFrameItem> map = this.mPlayerCaches;
        DHVideoFrameItem dHVideoFrameItem = (map == null || !map.containsKey(str)) ? null : this.mPlayerCaches.get(str);
        if (dHVideoFrameItem != null) {
            dHVideoFrameItem.hidden();
        }
    }

    public void initFeature(AbsMgr absMgr) {
        this.mFeatureMgr = absMgr;
    }

    public void pause(String str) {
        HashMap<String, DHVideoFrameItem> map = this.mPlayerCaches;
        DHVideoFrameItem dHVideoFrameItem = (map == null || !map.containsKey(str)) ? null : this.mPlayerCaches.get(str);
        if (dHVideoFrameItem != null) {
            dHVideoFrameItem.pause();
        }
    }

    public void play(String str) {
        HashMap<String, DHVideoFrameItem> map = this.mPlayerCaches;
        DHVideoFrameItem dHVideoFrameItem = (map == null || !map.containsKey(str)) ? null : this.mPlayerCaches.get(str);
        if (dHVideoFrameItem != null) {
            dHVideoFrameItem.play();
        }
    }

    public void recovery() {
        HashMap<String, DHVideoFrameItem> map = this.mPlayerCaches;
        if (map != null && map.size() > 0) {
            Iterator<String> it = this.mPlayerCaches.keySet().iterator();
            while (it.hasNext()) {
                this.mPlayerCaches.get(it.next()).release();
            }
        }
        this.mPlayerCaches.clear();
    }

    public void requestFullScreen(String str, String str2) {
        HashMap<String, DHVideoFrameItem> map = this.mPlayerCaches;
        DHVideoFrameItem dHVideoFrameItem = (map == null || !map.containsKey(str)) ? null : this.mPlayerCaches.get(str);
        if (dHVideoFrameItem != null) {
            dHVideoFrameItem.requestFullScreen(str2);
        }
    }

    public void resize(IWebview iWebview, String str, JSONArray jSONArray) {
        DHVideoFrameItem dHVideoFrameItem = this.mPlayerCaches.containsKey(str) ? this.mPlayerCaches.get(str) : null;
        if (dHVideoFrameItem == null) {
            return;
        }
        dHVideoFrameItem.resize(jSONArray);
    }

    public void rmovePlayer(String str) {
        HashMap<String, DHVideoFrameItem> map = this.mPlayerCaches;
        if (map == null || map.size() <= 0 || !this.mPlayerCaches.containsKey(str)) {
            return;
        }
        this.mPlayerCaches.remove(str);
    }

    public void seekTo(String str, String str2) {
        HashMap<String, DHVideoFrameItem> map = this.mPlayerCaches;
        DHVideoFrameItem dHVideoFrameItem = (map == null || !map.containsKey(str)) ? null : this.mPlayerCaches.get(str);
        if (dHVideoFrameItem != null) {
            dHVideoFrameItem.seek(str2);
        }
    }

    public void sendDanmu(String str, JSONObject jSONObject) {
        HashMap<String, DHVideoFrameItem> map = this.mPlayerCaches;
        DHVideoFrameItem dHVideoFrameItem = (map == null || !map.containsKey(str)) ? null : this.mPlayerCaches.get(str);
        if (dHVideoFrameItem != null) {
            dHVideoFrameItem.sendDanmu(jSONObject);
        }
    }

    public void setOptions(String str, JSONObject jSONObject) {
        HashMap<String, DHVideoFrameItem> map = this.mPlayerCaches;
        DHVideoFrameItem dHVideoFrameItem = (map == null || !map.containsKey(str)) ? null : this.mPlayerCaches.get(str);
        if (dHVideoFrameItem != null) {
            dHVideoFrameItem.setOptions(jSONObject);
        }
    }

    public void setPlayBackRate(String str, String str2) {
        HashMap<String, DHVideoFrameItem> map = this.mPlayerCaches;
        DHVideoFrameItem dHVideoFrameItem = (map == null || !map.containsKey(str)) ? null : this.mPlayerCaches.get(str);
        if (dHVideoFrameItem != null) {
            dHVideoFrameItem.sendPlayBackRate(str2);
        }
    }

    public void show(String str) {
        HashMap<String, DHVideoFrameItem> map = this.mPlayerCaches;
        DHVideoFrameItem dHVideoFrameItem = (map == null || !map.containsKey(str)) ? null : this.mPlayerCaches.get(str);
        if (dHVideoFrameItem != null) {
            dHVideoFrameItem.show();
        }
    }

    public void stop(String str) {
        HashMap<String, DHVideoFrameItem> map = this.mPlayerCaches;
        DHVideoFrameItem dHVideoFrameItem = (map == null || !map.containsKey(str)) ? null : this.mPlayerCaches.get(str);
        if (dHVideoFrameItem != null) {
            dHVideoFrameItem.stop();
        }
    }
}
