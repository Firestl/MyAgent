package io.dcloud.feature.speech;

import io.dcloud.common.DHInterface.IEventCallback;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.AdaFrameItem;
import io.dcloud.common.adapter.ui.AdaFrameView;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.PdrUtil;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes3.dex */
public class SpeechManager {
    public static SpeechManager mgr;
    public AbsSpeechEngine mSpeechEngine = null;
    public HashMap<String, ConcurrentHashMap<String, IWebview>> webCallBackIds = new HashMap<>();

    private void addEventListener(IWebview iWebview, String str, String str2) {
        ConcurrentHashMap<String, IWebview> concurrentHashMap = this.webCallBackIds.get(str);
        if (concurrentHashMap == null) {
            concurrentHashMap = new ConcurrentHashMap<>();
        }
        concurrentHashMap.put(str2, iWebview);
        this.webCallBackIds.put(str, concurrentHashMap);
        addWindowCloseListener(iWebview);
    }

    private void addWindowCloseListener(IWebview iWebview) {
        ((AdaFrameView) iWebview.obtainFrameView()).addFrameViewListener(new IEventCallback() { // from class: io.dcloud.feature.speech.SpeechManager.1
            @Override // io.dcloud.common.DHInterface.IEventCallback
            public Object onCallBack(String str, Object obj) {
                if ((!PdrUtil.isEquals(str, AbsoluteConst.EVENTS_WINDOW_CLOSE) && !PdrUtil.isEquals(str, "close")) || !(obj instanceof IWebview)) {
                    return null;
                }
                IWebview iWebview2 = (IWebview) obj;
                SpeechManager.this.removeWebviewCallback(iWebview2);
                ((AdaFrameView) iWebview2.obtainFrameView()).removeFrameViewListener(this);
                return null;
            }
        });
    }

    public static SpeechManager getInstance() {
        if (mgr == null) {
            synchronized (SpeechManager.class) {
                if (mgr == null) {
                    mgr = new SpeechManager();
                }
            }
        }
        return mgr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void eventListener(String str, String str2, int i, boolean z) {
        HashMap<String, ConcurrentHashMap<String, IWebview>> map = this.webCallBackIds;
        if (map == null || !map.containsKey(str) || this.webCallBackIds.get(str) == null) {
            return;
        }
        for (String str3 : this.webCallBackIds.get(str).keySet()) {
            IWebview iWebview = this.webCallBackIds.get(str).get(str3);
            if (!((AdaFrameItem) iWebview).isDisposed()) {
                Deprecated_JSUtil.execCallback(iWebview, str3, str2, i, true, z);
            }
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:4|(3:41|5|6)|(5:8|(4:11|(1:13)(4:14|15|39|16)|26|46)(1:10)|43|20|(2:22|51)(1:45))(1:18)|19|43|20|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00ad, code lost:
    
        r12 = r2;
     */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0094 A[Catch: Exception -> 0x00ad, IllegalAccessException -> 0x00e5, InstantiationException -> 0x00ea, TRY_LEAVE, TryCatch #2 {Exception -> 0x00ad, blocks: (B:20:0x0088, B:22:0x0094), top: B:43:0x0088 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void execute(io.dcloud.common.DHInterface.IWebview r18, java.lang.String r19, java.lang.String[] r20, java.util.HashMap<java.lang.String, java.lang.String> r21) {
        /*
            Method dump skipped, instruction units count: 268
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.speech.SpeechManager.execute(io.dcloud.common.DHInterface.IWebview, java.lang.String, java.lang.String[], java.util.HashMap):void");
    }

    public void removeWebviewCallback(IWebview iWebview) {
        HashMap<String, ConcurrentHashMap<String, IWebview>> map = this.webCallBackIds;
        if (map != null) {
            for (String str : map.keySet()) {
                for (String str2 : this.webCallBackIds.get(str).keySet()) {
                    if (this.webCallBackIds.get(str).get(str2) == iWebview) {
                        this.webCallBackIds.get(str).remove(str2);
                    }
                }
            }
        }
    }

    public void stopRecognize(boolean z) {
        AbsSpeechEngine absSpeechEngine = this.mSpeechEngine;
        if (absSpeechEngine != null) {
            absSpeechEngine.stopRecognize(z);
            this.mSpeechEngine = null;
        }
    }
}
