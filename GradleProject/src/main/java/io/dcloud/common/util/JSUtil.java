package io.dcloud.common.util;

import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.AdaUniWebView;
import io.dcloud.common.constant.DOMException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class JSUtil extends Deprecated_JSUtil {
    public static int CLASS_NOT_FOUND_EXCEPTION = 2;
    public static final String COMMA = ",";
    public static int ERROR = 9;
    public static int ILLEGAL_ACCESS_EXCEPTION = 3;
    public static int INSTANTIATION_EXCEPTION = 4;
    public static int INVALID_ACTION = 7;
    public static int IO_EXCEPTION = 6;
    public static int JSON_EXCEPTION = 8;
    public static int MALFORMED_URL_EXCEPTION = 5;
    public static int NO_RESULT = 0;
    public static int OK = 1;
    public static final String QUOTE = "\"";

    public static String arrayList2JsStringArray(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer(Operators.ARRAY_START_STR);
        if (arrayList != null && !arrayList.isEmpty()) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                stringBuffer.append("'");
                stringBuffer.append(arrayList.get(i));
                stringBuffer.append("'");
                if (i != size - 1) {
                    stringBuffer.append(",");
                }
            }
        }
        stringBuffer.append(Operators.ARRAY_END_STR);
        return stringBuffer.toString();
    }

    public static void broadcastWebviewEvent(IWebview iWebview, String str, String str2, String str3) {
        String str4 = StringUtil.format(iWebview instanceof AdaUniWebView ? "if(plus.webview.__Webview_LoadEvent_CallBack_) {plus.webview.__Webview_LoadEvent_CallBack_(%s);}" : "(function(w,n){var p = ((w.__html5plus__&&w.__html5plus__.isReady)?w.__html5plus__:(n.plus&&n.plus.isReady)?n.plus:window.plus);p && p.webview && p.webview.__Webview_LoadEvent_CallBack_ && p.webview.__Webview_LoadEvent_CallBack_(%s);})(window,navigator)", StringUtil.format("{WebviewID:'%s',Event:'%s',Msg:%s}", str, str2, str3));
        if (iWebview != null) {
            iWebview.executeScript(str4);
        }
    }

    public static boolean checkOperateDirErrorAndCallback(IWebview iWebview, String str, String str2) {
        if (!iWebview.obtainFrameView().obtainApp().checkPrivateDir(str2)) {
            return false;
        }
        Deprecated_JSUtil.execCallback(iWebview, str, StringUtil.format(DOMException.JSON_ERROR_INFO, 9, DOMException.MSG_OPERATE_DIR_ERROR), ERROR, true, false);
        return true;
    }

    public static String consoleTest(String str) {
        return "console.error('" + str + "');";
    }

    public static void execCallback(IWebview iWebview, String str, String str2, int i, boolean z) {
        Deprecated_JSUtil.execCallback(iWebview, str, str2, i, false, z);
    }

    public static void execGEOCallback(IWebview iWebview, String str, String str2, int i, boolean z, boolean z2) {
        if (iWebview != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("(function(){try{var result= {};result.status = %d;result.message = ");
            sb.append(z ? "%s" : "'%s'");
            sb.append(";result.keepCallback = ");
            sb.append(z2);
            sb.append(";");
            sb.append("__geo__.callbackFromNative('%s', result);}catch(e){console.error(e.stack)};})(window,navigator);");
            iWebview.executeScript(StringUtil.format(sb.toString(), Integer.valueOf(i), str2, str));
        }
    }

    public static String[] jsonArrayToStringArr(JSONArray jSONArray) throws JSONException {
        String[] strArr = new String[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            strArr[i] = jSONArray.getString(i);
        }
        return strArr;
    }

    public static String toJsResponseText(String str) {
        return !PdrUtil.isEmpty(str) ? str.replace("'", "'").replace(QUOTE, "\\\"").replace("\n", "\\n").replace("\r", "\\r") : str;
    }

    public static String wrapJsVar(double d) {
        return Deprecated_JSUtil.wrapJsVar(String.valueOf(d), false);
    }

    public static void execCallback(IWebview iWebview, String str, double d, int i, boolean z) {
        Deprecated_JSUtil.execCallback(iWebview, str, String.valueOf(d), i, true, z);
    }

    public static String wrapJsVar(float f) {
        return Deprecated_JSUtil.wrapJsVar(String.valueOf(f), false);
    }

    public static void execCallback(IWebview iWebview, String str, JSONArray jSONArray, int i, boolean z) {
        Deprecated_JSUtil.execCallback(iWebview, str, jSONArray.toString(), i, true, z);
    }

    public static String wrapJsVar(JSONArray jSONArray) {
        return Deprecated_JSUtil.wrapJsVar(jSONArray.toString(), false);
    }

    public static void execCallback(IWebview iWebview, String str, JSONObject jSONObject, int i, boolean z) {
        Deprecated_JSUtil.execCallback(iWebview, str, jSONObject.toString(), i, true, z);
    }

    public static String wrapJsVar(JSONObject jSONObject) {
        return Deprecated_JSUtil.wrapJsVar(jSONObject.toString(), false);
    }

    public static String wrapJsVar(String str) {
        return Deprecated_JSUtil.wrapJsVar(str, true);
    }

    public static String wrapJsVar(boolean z) {
        return Deprecated_JSUtil.wrapJsVar(String.valueOf(z), false);
    }
}
