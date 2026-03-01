package io.dcloud.feature.audio;

import android.media.AudioManager;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.MessageHandler;
import io.dcloud.common.util.JSONUtil;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes2.dex */
public class AudioFeatureImpl implements IFeature, MessageHandler.IMessages {
    public static final String TAG = "AudioFeatureImpl";
    public HashMap<String, ArrayList> mAppsAudioObj = null;

    private Object findAppObj(String str, String str2) {
        ArrayList appObjList = getAppObjList(str);
        if (!appObjList.isEmpty()) {
            for (Object obj : appObjList) {
                if ((obj instanceof AbsAudio) && ((AbsAudio) obj).mUuid.equals(str2)) {
                    return obj;
                }
            }
        }
        return null;
    }

    private ArrayList getAppObjList(String str) {
        ArrayList arrayList = this.mAppsAudioObj.get(str);
        if (arrayList != null) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList(2);
        this.mAppsAudioObj.put(str, arrayList2);
        return arrayList2;
    }

    private void putAppObjList(String str, Object obj) {
        getAppObjList(str).add(obj);
    }

    private void removeAppObjFromList(String str, Object obj) {
        ArrayList appObjList = getAppObjList(str);
        if (appObjList != null) {
            appObjList.remove(obj);
        }
    }

    private void setCanPlay(String str, String str2, boolean z) {
        for (Object obj : getAppObjList(str)) {
            if (obj instanceof AudioPlayer) {
                AudioPlayer audioPlayer = (AudioPlayer) obj;
                if (!audioPlayer.mUuid.equals(str2) && !z) {
                    audioPlayer.pause();
                }
                audioPlayer.setCanMix(z);
            }
        }
    }

    private void setSpeakerphoneOn(AudioManager audioManager, boolean z) {
        if (z) {
            audioManager.setSpeakerphoneOn(true);
            audioManager.setMode(1);
        } else {
            audioManager.setSpeakerphoneOn(false);
            audioManager.setRouting(0, 1, -1);
            audioManager.setMode(3);
        }
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void dispose(String str) {
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // io.dcloud.common.DHInterface.IFeature
    public String execute(IWebview iWebview, String str, String[] strArr) {
        String strObtainAppId = iWebview.obtainFrameView().obtainApp().obtainAppId();
        Logger.d(TAG, "execute pJsArgs[0]=" + strArr[0]);
        if (!"AudioSyncExecMethod".equals(str)) {
            MessageHandler.sendMessage(this, new Object[]{iWebview, str, strArr});
            return null;
        }
        String str2 = strArr[0];
        JSONArray jSONArrayCreateJSONArray = JSONUtil.createJSONArray(strArr[1]);
        str2.hashCode();
        byte b = -1;
        switch (str2.hashCode()) {
            case -1469262177:
                if (str2.equals("getPosition")) {
                    b = 0;
                }
                break;
            case 85887754:
                if (str2.equals("getDuration")) {
                    b = 1;
                }
                break;
            case 555863637:
                if (str2.equals("getBuffered")) {
                    b = 2;
                }
                break;
            case 700693540:
                if (str2.equals("getPaused")) {
                    b = 3;
                }
                break;
            case 804240344:
                if (str2.equals("getStyles")) {
                    b = 4;
                }
                break;
            case 1606340381:
                if (str2.equals("CreatePlayer")) {
                    b = 5;
                }
                break;
        }
        if (b == 0) {
            return String.valueOf(((AudioPlayer) findAppObj(strObtainAppId, JSONUtil.getString(jSONArrayCreateJSONArray, 0))).getPosition());
        }
        if (b == 1) {
            return String.valueOf(((AudioPlayer) findAppObj(strObtainAppId, JSONUtil.getString(jSONArrayCreateJSONArray, 0))).getDuration());
        }
        if (b == 2) {
            return ((AudioPlayer) findAppObj(strObtainAppId, JSONUtil.getString(jSONArrayCreateJSONArray, 0))).getBuffer();
        }
        if (b == 3) {
            return ((AudioPlayer) findAppObj(strObtainAppId, JSONUtil.getString(jSONArrayCreateJSONArray, 0))).isPause();
        }
        if (b == 4) {
            return ((AudioPlayer) findAppObj(strObtainAppId, JSONUtil.getString(jSONArrayCreateJSONArray, 0))).getStyles(jSONArrayCreateJSONArray.length() > 1 ? JSONUtil.getString(jSONArrayCreateJSONArray, 1) : null);
        }
        if (b != 5) {
            return null;
        }
        String string = JSONUtil.getString(jSONArrayCreateJSONArray, 0);
        AudioPlayer audioPlayerCreateAudioPlayer = AudioPlayer.createAudioPlayer(JSONUtil.getJSONObject(jSONArrayCreateJSONArray, 1), iWebview);
        audioPlayerCreateAudioPlayer.mUuid = string;
        putAppObjList(iWebview.obtainFrameView().obtainApp().obtainAppId(), audioPlayerCreateAudioPlayer);
        return null;
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void init(AbsMgr absMgr, String str) {
        this.mAppsAudioObj = new HashMap<>(2);
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x0146  */
    @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void execute(java.lang.Object r18) {
        /*
            Method dump skipped, instruction units count: 602
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.audio.AudioFeatureImpl.execute(java.lang.Object):void");
    }
}
