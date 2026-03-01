package io.dcloud.feature.speech;

import android.content.Context;
import com.taobao.weex.ui.view.gesture.WXGesture;
import dc.squareup.okhttp3.HttpUrl;
import io.dcloud.common.DHInterface.IReflectAble;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.StringUtil;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AbsSpeechEngine implements IReflectAble {
    public static final int ERROR_CODE_DEVICE_NO_SUPPORT = 61002;
    public static final int ERROR_CODE_DONT_RECOGNISE = 61007;
    public static final int ERROR_CODE_GRAMMAR = 61005;
    public static final int ERROR_CODE_INNER_WRONG = 61006;
    public static final int ERROR_CODE_IN_USE = 61003;
    public static final int ERROR_CODE_NETWORK = 61008;
    public static final int ERROR_CODE_PARAM_WRONG = 61004;
    public static final int ERROR_CODE_UNKNOWN = 61009;
    public static final int ERROR_CODE_USER_STOP = 61001;
    public Map<String, String> event;
    public String mCallbackId;
    public Context mContext;
    public JSONObject mEventCallbackIds;
    public ISpeechListener mListener;
    public ISpeechListener mSpeechListener = new ISpeechListener() { // from class: io.dcloud.feature.speech.AbsSpeechEngine.1
        private String convert(String str) {
            if (str.contains("'")) {
                str = str.replaceAll("'", "\\\\\\'");
            }
            return str.contains(JSUtil.QUOTE) ? str.replaceAll(JSUtil.QUOTE, "\\\\\\\"") : str;
        }

        @Override // io.dcloud.feature.speech.ISpeechListener
        public void onStateChange(byte b, Object obj, boolean z) {
            String string;
            SpeechManager speechManager = SpeechManager.getInstance();
            if (b == 1) {
                Deprecated_JSUtil.execCallback(AbsSpeechEngine.this.mWebview, JSONUtil.getString(AbsSpeechEngine.this.mEventCallbackIds, AbsoluteConst.JSON_KEY_ONSTART), "", JSUtil.OK, false, false);
                speechManager.eventListener("start", "{}", JSUtil.OK, true);
                return;
            }
            if (b == 2) {
                Deprecated_JSUtil.execCallback(AbsSpeechEngine.this.mWebview, JSONUtil.getString(AbsSpeechEngine.this.mEventCallbackIds, AbsoluteConst.JSON_KEY_ONEND), "", JSUtil.OK, false, false);
                speechManager.eventListener(WXGesture.END, "{}", JSUtil.OK, true);
                return;
            }
            if (b == 7) {
                String[] strArr = (String[]) obj;
                String str = StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(Integer.parseInt(strArr[0])), String.valueOf(strArr[1]));
                AbsSpeechEngine absSpeechEngine = AbsSpeechEngine.this;
                Deprecated_JSUtil.execCallback(absSpeechEngine.mWebview, absSpeechEngine.mCallbackId, str, JSUtil.ERROR, true, false);
                speechManager.eventListener("error", str, JSUtil.OK, true);
                return;
            }
            if (b != 8) {
                if (b == 10) {
                    speechManager.eventListener("recognizing", StringUtil.format("{partialResult:\"%s\"}", convert((String) obj)), JSUtil.OK, z);
                    return;
                } else {
                    if (b != 11) {
                        return;
                    }
                    speechManager.eventListener("volumeChange", StringUtil.format("{volume:%f}", Float.valueOf(((Integer) obj).intValue() * 0.14285715f)), JSUtil.OK, z);
                    return;
                }
            }
            String str2 = "";
            if (obj != null) {
                if (obj instanceof String[]) {
                    String[] strArr2 = (String[]) obj;
                    if (strArr2.length > 0) {
                        str2 = strArr2[0];
                    }
                } else {
                    str2 = (String) obj;
                }
            }
            String strConvert = convert(str2);
            AbsSpeechEngine absSpeechEngine2 = AbsSpeechEngine.this;
            Deprecated_JSUtil.execCallback(absSpeechEngine2.mWebview, absSpeechEngine2.mCallbackId, strConvert, JSUtil.OK, false, z);
            try {
                Object[] objArr = new Object[2];
                objArr[0] = strConvert;
                if (obj == null) {
                    string = HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
                } else if (obj instanceof String) {
                    string = "[\"" + convert((String) obj) + "\"]";
                } else {
                    string = new JSONArray(obj).toString();
                }
                objArr[1] = string;
                speechManager.eventListener("recognition", StringUtil.format("{result:\"%s\",results:%s}", objArr), JSUtil.OK, true);
            } catch (JSONException unused) {
            }
        }
    };
    public IWebview mWebview;

    private final void setSpeechListener(ISpeechListener iSpeechListener) {
        this.mListener = iSpeechListener;
    }

    public void addEventListener(String str, String str2) {
        if (this.event == null) {
            this.event = new HashMap();
        }
        this.event.put(str, str2);
    }

    public void init(Context context, IWebview iWebview) {
        this.mContext = context;
        this.mWebview = iWebview;
        setSpeechListener(this.mSpeechListener);
    }

    public final void startRecognize(String str, JSONObject jSONObject, JSONObject jSONObject2) {
        this.mCallbackId = str;
        this.mEventCallbackIds = jSONObject2;
        startRecognize(jSONObject);
    }

    public abstract void startRecognize(JSONObject jSONObject);

    public abstract void stopRecognize(boolean z);
}
