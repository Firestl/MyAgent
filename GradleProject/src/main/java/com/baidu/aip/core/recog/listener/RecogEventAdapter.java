package com.baidu.aip.core.recog.listener;

import android.util.Log;
import com.baidu.aip.core.recog.RecogResult;
import com.baidu.speech.EventListener;
import com.baidu.speech.asr.SpeechConstant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class RecogEventAdapter implements EventListener {
    public static final String TAG = "RecogEventAdapter";
    public IRecogListener listener;

    public class Volume {
        public String origalJson;
        public int volume;
        public int volumePercent;

        public Volume() {
            this.volumePercent = -1;
            this.volume = -1;
        }
    }

    public RecogEventAdapter(IRecogListener iRecogListener) {
        this.listener = iRecogListener;
    }

    private Volume parseVolumeJson(String str) {
        Volume volume = new Volume();
        volume.origalJson = str;
        try {
            JSONObject jSONObject = new JSONObject(str);
            volume.volumePercent = jSONObject.getInt("volume-percent");
            volume.volume = jSONObject.getInt("volume");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return volume;
    }

    @Override // com.baidu.speech.EventListener
    public void onEvent(String str, String str2, byte[] bArr, int i, int i2) {
        Log.i(TAG, "name:" + str + "; params:" + str2);
        if (str.equals(SpeechConstant.CALLBACK_EVENT_ASR_LOADED)) {
            this.listener.onOfflineLoaded();
            return;
        }
        if (str.equals(SpeechConstant.CALLBACK_EVENT_ASR_UNLOADED)) {
            this.listener.onOfflineUnLoaded();
            return;
        }
        if (str.equals(SpeechConstant.CALLBACK_EVENT_ASR_READY)) {
            this.listener.onAsrReady();
            return;
        }
        if (str.equals(SpeechConstant.CALLBACK_EVENT_ASR_BEGIN)) {
            this.listener.onAsrBegin();
            return;
        }
        if (str.equals(SpeechConstant.CALLBACK_EVENT_ASR_END)) {
            this.listener.onAsrEnd();
            return;
        }
        if (str.equals(SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL)) {
            RecogResult json = RecogResult.parseJson(str2);
            String[] resultsRecognition = json.getResultsRecognition();
            if (json.isFinalResult()) {
                this.listener.onAsrFinalResult(resultsRecognition, json);
                return;
            } else if (json.isPartialResult()) {
                this.listener.onAsrPartialResult(resultsRecognition, json);
                return;
            } else {
                if (json.isNluResult()) {
                    this.listener.onAsrOnlineNluResult(new String(bArr, i, i2));
                    return;
                }
                return;
            }
        }
        if (str.equals(SpeechConstant.CALLBACK_EVENT_ASR_FINISH)) {
            RecogResult json2 = RecogResult.parseJson(str2);
            if (!json2.hasError()) {
                this.listener.onAsrFinish(json2);
                return;
            }
            this.listener.onAsrFinishError(json2.getError(), json2.getSubError(), json2.getDesc(), json2);
            return;
        }
        if (str.equals(SpeechConstant.CALLBACK_EVENT_ASR_LONG_SPEECH)) {
            this.listener.onAsrLongFinish();
            return;
        }
        if (str.equals(SpeechConstant.CALLBACK_EVENT_ASR_EXIT)) {
            this.listener.onAsrExit();
            return;
        }
        if (str.equals(SpeechConstant.CALLBACK_EVENT_ASR_VOLUME)) {
            Volume volumeJson = parseVolumeJson(str2);
            this.listener.onAsrVolume(volumeJson.volumePercent, volumeJson.volume);
        } else if (str.equals(SpeechConstant.CALLBACK_EVENT_ASR_AUDIO)) {
            int length = bArr.length;
            this.listener.onAsrAudio(bArr, i, i2);
        }
    }
}
