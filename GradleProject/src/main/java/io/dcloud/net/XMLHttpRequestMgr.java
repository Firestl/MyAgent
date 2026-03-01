package io.dcloud.net;

import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.net.NetWorkLoop;
import io.dcloud.common.util.net.RequestData;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class XMLHttpRequestMgr {
    public NetWorkLoop mNetWorkLoop = new NetWorkLoop();
    public HashMap<String, ArrayList<XMLHttpRequest>> mXMLHttpRequests;

    public XMLHttpRequestMgr() {
        this.mXMLHttpRequests = null;
        this.mXMLHttpRequests = new HashMap<>();
        this.mNetWorkLoop.startThreadPool();
    }

    private XMLHttpRequest findXMLHttpRequest(String str, String str2) {
        ArrayList<XMLHttpRequest> arrayList = this.mXMLHttpRequests.get(str);
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                XMLHttpRequest xMLHttpRequest = arrayList.get(i);
                if (str2.equals(xMLHttpRequest.mUUID)) {
                    return xMLHttpRequest;
                }
            }
        }
        return null;
    }

    private void pushXMLHttpRequest(String str, XMLHttpRequest xMLHttpRequest) {
        ArrayList<XMLHttpRequest> arrayList = this.mXMLHttpRequests.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.mXMLHttpRequests.put(str, arrayList);
        }
        arrayList.add(xMLHttpRequest);
    }

    public String execute(IWebview iWebview, String str, String[] strArr) {
        String strObtainAppId = iWebview.obtainFrameView().obtainApp().obtainAppId();
        if ("send".equals(str)) {
            XMLHttpRequest xMLHttpRequestFindXMLHttpRequest = findXMLHttpRequest(strObtainAppId, strArr[0]);
            xMLHttpRequestFindXMLHttpRequest.setCallbackId(strArr[1]);
            String str2 = strArr[2];
            RequestData requestData = xMLHttpRequestFindXMLHttpRequest.getRequestData();
            if (!requestData.getReqmethod().equalsIgnoreCase("get")) {
                requestData.addBody(str2);
            }
            try {
                JSONObject jSONObject = new JSONObject(strArr[3]);
                JSONArray jSONArrayNames = jSONObject.names();
                if (jSONArrayNames != null && jSONArrayNames.length() > 0) {
                    for (int i = 0; i < jSONArrayNames.length(); i++) {
                        String strOptString = jSONArrayNames.optString(i);
                        requestData.addHeader(strOptString, jSONObject.optString(strOptString));
                    }
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            this.mNetWorkLoop.addNetWork(xMLHttpRequestFindXMLHttpRequest.getNetWork());
            return null;
        }
        if ("open".equals(str)) {
            String str3 = strArr[0];
            String str4 = strArr[1];
            String str5 = strArr[2];
            String str6 = strArr[3];
            String str7 = strArr[4];
            XMLHttpRequest xMLHttpRequest = new XMLHttpRequest(str3, str5, str4, iWebview);
            RequestData requestData2 = xMLHttpRequest.getRequestData();
            requestData2.mTimeout = PdrUtil.parseInt(strArr[5], requestData2.mTimeout);
            requestData2.addHeader(str6, str7);
            pushXMLHttpRequest(strObtainAppId, xMLHttpRequest);
            return null;
        }
        if (!"overrideMimeType".equals(str)) {
            if (!"abort".equals(str)) {
                return null;
            }
            this.mNetWorkLoop.removeNetWork(findXMLHttpRequest(strObtainAppId, strArr[0]).getNetWork());
            return null;
        }
        XMLHttpRequest xMLHttpRequestFindXMLHttpRequest2 = findXMLHttpRequest(strObtainAppId, strArr[0]);
        if (xMLHttpRequestFindXMLHttpRequest2 == null) {
            return null;
        }
        xMLHttpRequestFindXMLHttpRequest2.getRequestData().mOverrideMimeType = strArr[1];
        return null;
    }
}
