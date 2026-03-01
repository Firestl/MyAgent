package com.baidu.speech.asr;

import android.content.Context;
import com.baidu.speech.EventListener;
import com.baidu.speech.core.BDSErrorDescription;
import com.baidu.speech.core.BDSMessage;
import com.baidu.speech.core.BDSParamBase;
import com.baidu.speech.core.BDSSDKLoader;
import com.baidu.speech.utils.Policy;
import com.baidu.speech.utils.Utility;
import com.taobao.weex.ui.component.WXImage;
import java.util.HashMap;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SlotControl implements BDSSDKLoader.BDSCoreEventListener {
    public static String ASR_PARAM_KEY_NETWORK_STATUS = "asr_param_key_network_status.int";
    public static String ASR_PARAM_KEY_PLATFORM = "asr_param_key_platform.string";
    public static String ASR_PARAM_KEY_SDK_VERSION = "asr_param_key_sdk_version.string";
    public static final int PLOADER_ERROR_CODE_REQUEST_ERROR = 2;
    public static final String UPLOADER_CMD_CANCEL = "uploader.cancel";
    public static final String UPLOADER_CMD_CONFIG = "uploader.config";
    public static final String UPLOADER_CMD_START = "uploader.start";
    public static final String UPLOADER_CUID = "upl_param_key_cuid.string";
    public static final int UPLOADER_ERROR_CODE_NET_UNAVAILAVLE = 4;
    public static final int UPLOADER_ERROR_CODE_OK = 0;
    public static final int UPLOADER_ERROR_CODE_PARAM_ERROR = 1;
    public static final int UPLOADER_ERROR_CODE_RESPONSE_ERROR = 3;
    public static final String UPLOADER_NET_STATUS_KEY = "upl_param_key_network_status.int";
    public static final String UPLOADER_PRODUCT_ID = "upl_param_key_product_id.string";
    public static final String UPLOADER_SLOT_NAME_KEY = "upl_param_key_upload_slot_name.string";
    public static final String UPLOADER_URL = "upl_param_key_url.string";
    public static final String UPLOADER_WORDS_KEY = "upl_param_key_upload_words.vector<string>";
    public Context context;
    public EventListener mListener;
    public JSONObject mParams;
    public BDSSDKLoader.BDSSDKInterface m_Uploadcore;
    public String outFile = null;

    public SlotControl(Context context) throws Exception {
        this.context = context;
        try {
            BDSSDKLoader.loadLibraries();
            BDSSDKLoader.BDSSDKInterface sDKObjectForSDKType = BDSSDKLoader.getSDKObjectForSDKType("UploaderCore", context);
            this.m_Uploadcore = sDKObjectForSDKType;
            if (sDKObjectForSDKType == null) {
                throw new Exception("ASR core support is not linked in package");
            }
            if (!sDKObjectForSDKType.instanceInitialized()) {
                throw new Exception("Failed initialize ASR Core native layer");
            }
            this.m_Uploadcore.setListener(this);
        } catch (Exception unused) {
            throw new Exception("Can not load so library");
        }
    }

    private void asrCallBack(BDSMessage bDSMessage, EventListener eventListener) {
        if (bDSMessage.m_messageName.equals(SpeechConstant.UPLOAD_CALLBACK_NAME)) {
            int i = ((BDSParamBase.BDSIntParam) bDSMessage.m_messageParams.get(SpeechConstant.CALLBACK_ERROR_DOMAIN)).iValue;
            String str = (String) ((BDSParamBase.BDSObjectParam) bDSMessage.m_messageParams.get(SpeechConstant.CALLBACK_ERROR_DESC)).iValue;
            int i2 = ((BDSParamBase.BDSIntParam) bDSMessage.m_messageParams.get(SpeechConstant.CALLBACK_ERROR_CODE)).iValue;
            HashMap map = new HashMap();
            map.put("errorDomain", Integer.valueOf(i));
            map.put("errorCode", Integer.valueOf(i2));
            map.put(WXImage.ERRORDESC, str);
            eventListener.onEvent(SpeechConstant.CALLBACK_EVENT_UPLOAD_FINISH, new JSONObject(map).toString(), null, 0, 0);
        }
    }

    private BDSErrorDescription postEvent(BDSErrorDescription bDSErrorDescription, String str) {
        BDSMessage bDSMessage = new BDSMessage();
        bDSMessage.m_messageName = str;
        bDSMessage.m_messageParams = new HashMap<>();
        this.mParams.optString(SpeechConstant.APP_NAME, Policy.app(this.context));
        String strOptString = this.mParams.optString("pid");
        String strOptString2 = this.mParams.optString("url", "https://upl.baidu.com//words/add");
        String strOptString3 = this.mParams.optString("decoder-server.uid", Policy.uid(this.context));
        bDSMessage.m_messageParams.put(ASR_PARAM_KEY_PLATFORM, BDSParamBase.objectParam("Android", "java.lang.String"));
        bDSMessage.m_messageParams.put(ASR_PARAM_KEY_SDK_VERSION, BDSParamBase.objectParam("C++ ASR core", "java.lang.String"));
        bDSMessage.m_messageParams.put(UPLOADER_NET_STATUS_KEY, BDSParamBase.intParam(Utility.getWifiOr2gOr3G(this.context)));
        bDSMessage.m_messageParams.put(UPLOADER_PRODUCT_ID, BDSParamBase.objectParam(strOptString, "java.lang.String"));
        bDSMessage.m_messageParams.put(UPLOADER_URL, BDSParamBase.objectParam(strOptString2, "java.lang.String"));
        bDSMessage.m_messageParams.put(UPLOADER_CUID, BDSParamBase.objectParam(strOptString3, "java.lang.String"));
        int iPostMessage = this.m_Uploadcore.postMessage(bDSMessage);
        if (iPostMessage == 0) {
            return bDSErrorDescription;
        }
        BDSErrorDescription bDSErrorDescription2 = new BDSErrorDescription();
        bDSErrorDescription2.errorCode = -2;
        bDSErrorDescription2.errorDomain = 1;
        bDSErrorDescription2.errorDescription = "JNI: readyParamsAsrStart Call to Native layer returned error! err( " + iPostMessage + " )";
        return bDSErrorDescription2;
    }

    private BDSErrorDescription uploadSlotWords(BDSErrorDescription bDSErrorDescription, JSONObject jSONObject) {
        String strOptString = jSONObject.optString("name");
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(SpeechConstant.WP_WORDS);
        if (!this.m_Uploadcore.instanceInitialized()) {
            return bDSErrorDescription;
        }
        BDSMessage bDSMessage = new BDSMessage();
        bDSMessage.m_messageName = UPLOADER_CMD_CONFIG;
        bDSMessage.m_messageParams = new HashMap<>();
        Vector vector = new Vector();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                try {
                    vector.add(jSONArrayOptJSONArray.getString(i));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
        bDSMessage.m_messageParams.put(ASR_PARAM_KEY_PLATFORM, BDSParamBase.objectParam("Android", "java.lang.String"));
        bDSMessage.m_messageParams.put(ASR_PARAM_KEY_SDK_VERSION, BDSParamBase.objectParam("C++ ASR core", "java.lang.String"));
        bDSMessage.m_messageParams.put(UPLOADER_NET_STATUS_KEY, BDSParamBase.intParam(Utility.getWifiOr2gOr3G(this.context)));
        bDSMessage.m_messageParams.put(UPLOADER_SLOT_NAME_KEY, BDSParamBase.objectParam(strOptString, "java.lang.String"));
        bDSMessage.m_messageParams.put(UPLOADER_WORDS_KEY, BDSParamBase.objectParam(vector, "java.util.Vector;"));
        int iPostMessage = this.m_Uploadcore.postMessage(bDSMessage);
        if (iPostMessage == 0) {
            return bDSErrorDescription;
        }
        BDSErrorDescription bDSErrorDescription2 = new BDSErrorDescription();
        bDSErrorDescription2.errorCode = -2;
        bDSErrorDescription2.errorDomain = 1;
        bDSErrorDescription2.errorDescription = "JNI: readyParamsAsrStart Call to Native layer returned error! err( " + iPostMessage + " )";
        return bDSErrorDescription2;
    }

    public BDSErrorDescription postEvent(String str, String str2) {
        if (!this.m_Uploadcore.instanceInitialized()) {
            BDSErrorDescription bDSErrorDescription = new BDSErrorDescription();
            bDSErrorDescription.errorCode = -1;
            bDSErrorDescription.errorDomain = 1;
            bDSErrorDescription.errorDescription = "JNI: ASR Core native layer is not initialized!";
            return bDSErrorDescription;
        }
        if (str2 != null) {
            try {
                if (str2.equals("")) {
                    this.mParams = new JSONObject();
                } else {
                    this.mParams = new JSONObject(str2);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
                this.mParams = new JSONObject();
            }
        } else {
            this.mParams = new JSONObject();
        }
        BDSErrorDescription bDSErrorDescriptionUploadSlotWords = uploadSlotWords(null, this.mParams);
        return bDSErrorDescriptionUploadSlotWords != null ? bDSErrorDescriptionUploadSlotWords : postEvent(bDSErrorDescriptionUploadSlotWords, str);
    }

    @Override // com.baidu.speech.core.BDSSDKLoader.BDSCoreEventListener
    public void receiveCoreEvent(BDSMessage bDSMessage, BDSSDKLoader.BDSSDKInterface bDSSDKInterface) {
        EventListener eventListener = this.mListener;
        if (eventListener == null || bDSMessage == null) {
            return;
        }
        asrCallBack(bDSMessage, eventListener);
    }

    public void setListener(EventListener eventListener) {
        this.mListener = eventListener;
    }
}
