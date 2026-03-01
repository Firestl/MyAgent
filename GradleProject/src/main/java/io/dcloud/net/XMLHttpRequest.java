package io.dcloud.net;

import com.taobao.weex.http.WXStreamModule;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IReqListener;
import io.dcloud.common.DHInterface.IResponseListener;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.net.NetWork;
import io.dcloud.common.util.net.RequestData;
import java.io.InputStream;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class XMLHttpRequest implements IReqListener, IResponseListener {
    public static final int ERROR_OTHER_CODE = 1;
    public static final int ERROR_TIME_OUT_CODE = 0;
    public static final int LOADED = 4;
    public static final int RECEIVING = 3;
    public String mCallbackId;
    public int mErrorCode = -1;
    public NetWork mNetWork;
    public int mReadyState;
    public RequestData mRequestData;
    public int mState;
    public String mStatusText;
    public String mUUID;
    public IWebview mWebview;

    /* JADX INFO: renamed from: io.dcloud.net.XMLHttpRequest$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$io$dcloud$common$DHInterface$IReqListener$NetState;

        static {
            int[] iArr = new int[IReqListener.NetState.values().length];
            $SwitchMap$io$dcloud$common$DHInterface$IReqListener$NetState = iArr;
            try {
                iArr[IReqListener.NetState.NET_HANDLE_END.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$dcloud$common$DHInterface$IReqListener$NetState[IReqListener.NetState.NET_HANDLE_ING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$dcloud$common$DHInterface$IReqListener$NetState[IReqListener.NetState.NET_ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$dcloud$common$DHInterface$IReqListener$NetState[IReqListener.NetState.NET_TIMEOUT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public XMLHttpRequest(String str, String str2, String str3, IWebview iWebview) {
        this.mUUID = str;
        RequestData requestData = new RequestData(str2, str3);
        this.mRequestData = requestData;
        requestData.unTrustedCAType = iWebview.obtainApp().obtainConfigProperty(IApp.ConfigProperty.CONFIG_UNTRUSTEDCA);
        this.mRequestData.addHeader("User-Agent", iWebview.getWebviewProperty("User-Agent"));
        this.mNetWork = new NetWork(3, this.mRequestData, this, this);
        this.mWebview = iWebview;
    }

    private JSONObject headersToJSON(Map<String, String> map) {
        JSONObject jSONObject = new JSONObject();
        for (String str : map.keySet()) {
            try {
                jSONObject.put(str, map.get(str));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return jSONObject;
    }

    private String toJsResponseText(String str) {
        return JSONUtil.toJSONableString(str);
    }

    public NetWork getNetWork() {
        return this.mNetWork;
    }

    public RequestData getRequestData() {
        return this.mRequestData;
    }

    @Override // io.dcloud.common.DHInterface.IReqListener
    public void onNetStateChanged(IReqListener.NetState netState, boolean z) {
        if (z) {
            this.mReadyState = 4;
            return;
        }
        int i = AnonymousClass1.$SwitchMap$io$dcloud$common$DHInterface$IReqListener$NetState[netState.ordinal()];
        if (i == 1) {
            this.mReadyState = 4;
            JSUtil.execCallback(this.mWebview, this.mCallbackId, toJSON(), JSUtil.OK, true);
            return;
        }
        if (i == 2) {
            this.mReadyState = 3;
            JSUtil.execCallback(this.mWebview, this.mCallbackId, toJSON(), JSUtil.OK, true);
        } else if (i == 3) {
            this.mReadyState = 4;
            this.mErrorCode = 1;
            JSUtil.execCallback(this.mWebview, this.mCallbackId, toJSON(), JSUtil.OK, true);
        } else {
            if (i != 4) {
                return;
            }
            this.mReadyState = 4;
            this.mErrorCode = 0;
            JSUtil.execCallback(this.mWebview, this.mCallbackId, toJSON(), JSUtil.OK, true);
        }
    }

    @Override // io.dcloud.common.DHInterface.IReqListener
    public int onReceiving(InputStream inputStream) {
        return 0;
    }

    @Override // io.dcloud.common.DHInterface.IResponseListener
    public void onResponseState(int i, String str) {
        this.mState = i;
        this.mStatusText = str;
        Logger.d("xhr", "onResponseState pState=" + i + ";mCallbackId=" + this.mCallbackId);
    }

    @Override // io.dcloud.common.DHInterface.IReqListener
    public void onResponsing(InputStream inputStream) {
    }

    public void setCallbackId(String str) {
        this.mCallbackId = str;
    }

    public JSONObject toJSON() {
        JSONObject jSONObject = new JSONObject();
        String responseText = this.mNetWork.getResponseText();
        try {
            jSONObject.put("readyState", this.mReadyState);
            jSONObject.put("status", this.mState);
            jSONObject.put(WXStreamModule.STATUS_TEXT, this.mStatusText);
            jSONObject.put("responseText", responseText);
            jSONObject.put("header", headersToJSON(this.mNetWork.getHeadersAndValues()));
            int i = this.mErrorCode;
            if (i > -1) {
                jSONObject.put("error", i);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }
}
