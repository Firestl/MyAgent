package io.dcloud.js.map.amap;

import com.huawei.hms.framework.common.ContainerUtils;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.AdaUniWebView;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.PdrUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class MapJsUtil {
    public static void assignJsVar(StringBuffer stringBuffer, String str, String str2, String str3, boolean z) {
        stringBuffer.append(str);
        if (!PdrUtil.isEmpty(str2)) {
            stringBuffer.append(Operators.DOT_STR);
            stringBuffer.append(str2);
        }
        stringBuffer.append(ContainerUtils.KEY_VALUE_DELIMITER);
        if (!z) {
            stringBuffer.append(str3);
            stringBuffer.append(";");
        } else {
            stringBuffer.append("'");
            stringBuffer.append(str3);
            stringBuffer.append("'");
            stringBuffer.append(";");
        }
    }

    public static void create_Js_Var(StringBuffer stringBuffer, String str, String str2, String str3) {
        boolean zIsEmpty = PdrUtil.isEmpty(str);
        if (!zIsEmpty) {
            stringBuffer.append("var ");
            stringBuffer.append(str);
            stringBuffer.append(ContainerUtils.KEY_VALUE_DELIMITER);
        }
        stringBuffer.append("new ");
        stringBuffer.append(str2);
        stringBuffer.append("(");
        if (str3 == null) {
            str3 = "";
        }
        stringBuffer.append(str3);
        stringBuffer.append(")");
        if (zIsEmpty) {
            return;
        }
        stringBuffer.append(";");
    }

    public static void execCallback(IWebview iWebview, String str, String str2) {
        if (iWebview != null) {
            iWebview.executeScript((iWebview instanceof AdaUniWebView ? AbsoluteConst.JS_RUNTIME_V8_BASE : "var p=((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus);") + "p.maps.__bridge__.execCallback('" + str + "'," + str2 + ");");
        }
    }

    public static String wrapJsEvalString(String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("(function(){");
        stringBuffer.append(str);
        stringBuffer.append(";return ");
        stringBuffer.append(str2);
        stringBuffer.append(";})()");
        return stringBuffer.toString();
    }

    public static void assignJsVar(StringBuffer stringBuffer, String str, String str2, String str3) {
        assignJsVar(stringBuffer, str, str2, str3, true);
    }

    public static void assignJsVar(StringBuffer stringBuffer, String str, String str2, double d) {
        assignJsVar(stringBuffer, str, str2, String.valueOf(d), false);
    }

    public static void assignJsVar(StringBuffer stringBuffer, String str, String str2, JSONArray jSONArray) {
        StringBuffer stringBuffer2 = new StringBuffer(Operators.ARRAY_START_STR);
        try {
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                stringBuffer2.append(jSONArray.getString(i));
                if (i != length - 1) {
                    stringBuffer2.append(",");
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        stringBuffer2.append(Operators.ARRAY_END_STR);
        assignJsVar(stringBuffer, str, str2, stringBuffer2.toString(), false);
    }

    public static void assignJsVar(StringBuffer stringBuffer, String str, String str2, JSONObject jSONObject) {
        assignJsVar(stringBuffer, str, str2, jSONObject.toString(), false);
    }
}
