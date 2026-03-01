package io.dcloud.common.util;

import android.graphics.Color;
import android.text.TextUtils;
import com.taobao.weex.appfram.pickers.WXPickersModule;
import com.taobao.weex.common.Constants;
import com.taobao.weex.utils.WXResourceUtils;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IFrameView;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.ITitleNView;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.AdaFrameItem;
import io.dcloud.common.adapter.ui.AdaFrameView;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.core.ui.l;
import io.dcloud.js.map.amap.util.AMapUtil;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class TitleNViewUtil {
    public static final String TRANSPARENT_BUTTON_BACKGROUND_COLOR = "#7F333333";
    public static final String TRANSPARENT_BUTTON_TEXT_COLOR = "#FFFFFF";

    public static boolean backButtonIsChanged(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject != null || jSONObject2 == null) {
            if (jSONObject != null && jSONObject2 != null) {
                if (jSONObject.has("autoBackButton") || jSONObject.has("backButton")) {
                    if (jSONObject.has("autoBackButton")) {
                        boolean zOptBoolean = jSONObject.optBoolean("autoBackButton");
                        if (jSONObject2.has("autoBackButton")) {
                            if (zOptBoolean != jSONObject2.optBoolean("autoBackButton")) {
                                return true;
                            }
                        } else if (jSONObject2.has("backButton") && ((zOptBoolean && (jSONObject2.optJSONObject("backButton") == null || "undefined" == jSONObject2.optString("backButton"))) || jSONObject2.optJSONObject("backButton") != null)) {
                            return true;
                        }
                    } else if (jSONObject.has("backButton")) {
                        boolean z = jSONObject.optJSONObject("backButton") != null;
                        if (jSONObject2.has("autoBackButton")) {
                            if (z != jSONObject2.optBoolean("autoBackButton")) {
                                return true;
                            }
                        } else if (jSONObject2.has("backButton") && ((z && (jSONObject2.optJSONObject("backButton") == null || "undefined" == jSONObject2.optString("backButton"))) || jSONObject2.optJSONObject("backButton") != null)) {
                            return true;
                        }
                    }
                } else {
                    if (jSONObject2.has("autoBackButton") && jSONObject2.optBoolean("autoBackButton")) {
                        return true;
                    }
                    if (jSONObject2.has("backButton") && jSONObject2.optJSONObject("backButton") != null) {
                        return true;
                    }
                }
            }
        } else {
            if (jSONObject2.has("autoBackButton")) {
                return true;
            }
            if (jSONObject2.has("backButton") && (jSONObject2.optJSONObject("backButton") != null || "undefined" != jSONObject2.optString("backButton"))) {
                return true;
            }
        }
        return false;
    }

    public static boolean backgroundImageIsChanged(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject2 == null || !jSONObject2.has(Constants.Name.BACKGROUND_IMAGE)) {
            return false;
        }
        if (jSONObject == null) {
            return jSONObject2.has(Constants.Name.BACKGROUND_IMAGE);
        }
        String strOptString = jSONObject.optString(Constants.Name.BACKGROUND_IMAGE);
        String strOptString2 = jSONObject2.optString(Constants.Name.BACKGROUND_IMAGE);
        if (jSONObject.has(Constants.Name.BACKGROUND_IMAGE) || !jSONObject2.has(Constants.Name.BACKGROUND_IMAGE)) {
            return jSONObject.has(Constants.Name.BACKGROUND_IMAGE) && jSONObject2.has(Constants.Name.BACKGROUND_IMAGE) && !strOptString.equals(strOptString2);
        }
        return true;
    }

    public static boolean backgroundIsChanged(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject2 != null && jSONObject2.has("backgroundColor")) {
            if (jSONObject != null || jSONObject2 == null) {
                if (jSONObject != null && jSONObject2 != null) {
                    String strOptString = jSONObject.optString("backgroundColor");
                    String strOptString2 = jSONObject2.optString("backgroundColor");
                    if (TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString2)) {
                        return true;
                    }
                    if (!TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString2) && !strOptString.equals(strOptString2)) {
                        return true;
                    }
                }
            } else if (!TextUtils.isEmpty(jSONObject2.optString("backgroundColor"))) {
                return true;
            }
        }
        return false;
    }

    public static int changeColorAlpha(int i, float f) {
        if (0.0f > f || f > 1.0f) {
            return -1;
        }
        return Color.argb((int) (f * 255.0f), Color.red(i), Color.green(i), Color.blue(i));
    }

    public static boolean checkKeyValueIsModify(String str, JSONObject jSONObject, JSONObject jSONObject2) {
        if (!jSONObject2.has(str) || jSONObject2 == null) {
            return false;
        }
        if (jSONObject == null || !jSONObject.has(str)) {
            return true;
        }
        return !jSONObject.optString(str).equals(jSONObject2.optString(str));
    }

    public static String color2Color(String str, String str2, float f) {
        try {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || 0.0f > f || f > 1.0f) {
                return null;
            }
            if (!str.startsWith("#") || !str2.startsWith("#")) {
                if (!str.startsWith(WXResourceUtils.RGBA) || !str.contains("(") || !str.contains(")") || !str.contains(",") || !str2.startsWith(WXResourceUtils.RGBA) || !str2.contains("(") || !str2.contains(")") || !str2.contains(",")) {
                    return null;
                }
                String strSubstring = str.substring(str.indexOf("(") + 1, str.indexOf(")"));
                String strSubstring2 = str2.substring(str2.indexOf("(") + 1, str2.indexOf(")"));
                String[] strArrStringSplit = PdrUtil.stringSplit(strSubstring, ",");
                String[] strArrStringSplit2 = PdrUtil.stringSplit(strSubstring2, ",");
                if (strArrStringSplit == null || 4 != strArrStringSplit.length || strArrStringSplit2 == null || 4 != strArrStringSplit2.length) {
                    return null;
                }
                int iIntValue = Integer.valueOf(strArrStringSplit[0]).intValue();
                int iIntValue2 = Integer.valueOf(strArrStringSplit[1]).intValue();
                int iIntValue3 = Integer.valueOf(strArrStringSplit[2]).intValue();
                int iIntValue4 = Integer.valueOf(strArrStringSplit2[0]).intValue();
                int iIntValue5 = Integer.valueOf(strArrStringSplit2[1]).intValue();
                int iIntValue6 = Integer.valueOf(strArrStringSplit2[2]).intValue();
                String str3 = strArrStringSplit[3];
                float f2 = iIntValue;
                if (iIntValue != iIntValue4) {
                    f2 = iIntValue > iIntValue4 ? f2 - ((iIntValue - iIntValue4) * f) : f2 + ((iIntValue - iIntValue4) * f);
                }
                float f3 = iIntValue2;
                if (iIntValue2 != iIntValue5) {
                    f3 = iIntValue2 > iIntValue5 ? f3 - ((iIntValue2 - iIntValue5) * f) : f3 + ((iIntValue5 - iIntValue2) * f);
                }
                float f4 = iIntValue3;
                if (iIntValue3 != iIntValue6) {
                    f4 = iIntValue3 > iIntValue6 ? f4 - ((iIntValue3 - iIntValue6) * f) : f4 + ((iIntValue6 - iIntValue3) * f);
                }
                return "rgba(" + ((int) f2) + "," + ((int) f3) + "," + ((int) f4) + "," + str3 + ")";
            }
            String strSubstring3 = str.substring(1, str.length());
            String strSubstring4 = str2.substring(1, str2.length());
            if (strSubstring3.length() == 3) {
                str = "#" + strSubstring3.charAt(0) + strSubstring3.charAt(0) + strSubstring3.charAt(1) + strSubstring3.charAt(1) + strSubstring3.charAt(2) + strSubstring3.charAt(2);
            }
            if (strSubstring4.length() == 3) {
                str2 = "#" + strSubstring4.charAt(0) + strSubstring4.charAt(0) + strSubstring4.charAt(1) + strSubstring4.charAt(1) + strSubstring4.charAt(2) + strSubstring4.charAt(2);
            }
            int color = Color.parseColor(str);
            int iRed = Color.red(color);
            int iGreen = Color.green(color);
            int iBlue = Color.blue(color);
            int color2 = Color.parseColor(str2);
            int iRed2 = Color.red(color2);
            int iGreen2 = Color.green(color2);
            int iBlue2 = Color.blue(color2);
            String hexString = Integer.toHexString(Color.alpha(color));
            if (1 == hexString.length()) {
                hexString = "0" + hexString;
            }
            float f5 = iRed;
            if (iRed != iRed2) {
                f5 = iRed > iRed2 ? f5 - ((iRed - iRed2) * f) : f5 + ((iRed - iRed2) * f);
            }
            String hexString2 = Integer.toHexString((int) f5);
            if (1 == hexString2.length()) {
                hexString2 = "0" + hexString2;
            }
            float f6 = iGreen;
            if (iGreen != iGreen2) {
                f6 = iGreen > iGreen2 ? f6 - ((iGreen - iGreen2) * f) : f6 + ((iGreen2 - iGreen) * f);
            }
            String hexString3 = Integer.toHexString((int) f6);
            if (1 == hexString3.length()) {
                hexString3 = "0" + hexString3;
            }
            float f7 = iBlue;
            if (iBlue != iBlue2) {
                f7 = iBlue > iBlue2 ? f7 - ((iBlue - iBlue2) * f) : f7 + ((iBlue2 - iBlue) * f);
            }
            String hexString4 = Integer.toHexString((int) f7);
            if (1 == hexString4.length()) {
                hexString4 = "0" + hexString4;
            }
            return "#" + hexString + hexString2 + hexString3 + hexString4;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static boolean compareColor(String str, String str2) {
        try {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                if (str.startsWith("#") && str2.startsWith("#")) {
                    String strSubstring = str.substring(1, str.length());
                    String strSubstring2 = str2.substring(1, str2.length());
                    if (strSubstring.length() == 3) {
                        str = "#" + strSubstring.charAt(0) + strSubstring.charAt(0) + strSubstring.charAt(1) + strSubstring.charAt(1) + strSubstring.charAt(2) + strSubstring.charAt(2);
                    }
                    if (strSubstring2.length() == 3) {
                        str2 = "#" + strSubstring2.charAt(0) + strSubstring2.charAt(0) + strSubstring2.charAt(1) + strSubstring2.charAt(1) + strSubstring2.charAt(2) + strSubstring2.charAt(2);
                    }
                    int color = Color.parseColor(str);
                    int color2 = Color.parseColor(str2);
                    return Color.rgb(Color.red(color), Color.green(color), Color.blue(color)) == Color.rgb(Color.red(color2), Color.green(color2), Color.blue(color2));
                }
                if (str.startsWith(WXResourceUtils.RGBA) && str.contains("(") && str.contains(")") && str.contains(",") && str2.startsWith(WXResourceUtils.RGBA) && str2.contains("(") && str2.contains(")") && str2.contains(",")) {
                    String strSubstring3 = str.substring(str.indexOf("(") + 1, str.indexOf(")"));
                    String strSubstring4 = str2.substring(str2.indexOf("(") + 1, str2.indexOf(")"));
                    String[] strArrStringSplit = PdrUtil.stringSplit(strSubstring3, ",");
                    String[] strArrStringSplit2 = PdrUtil.stringSplit(strSubstring4, ",");
                    return strArrStringSplit != null && 4 == strArrStringSplit.length && strArrStringSplit2 != null && 4 == strArrStringSplit2.length && Color.rgb(Integer.valueOf(strArrStringSplit[0]).intValue(), Integer.valueOf(strArrStringSplit[1]).intValue(), Integer.valueOf(strArrStringSplit[2]).intValue()) == Color.rgb(Integer.valueOf(strArrStringSplit2[0]).intValue(), Integer.valueOf(strArrStringSplit2[1]).intValue(), Integer.valueOf(strArrStringSplit2[2]).intValue());
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return false;
    }

    public static void drawTitle(IFrameView iFrameView, final ITitleNView iTitleNView, final String str, final String str2, final String str3, final String str4, final String str5, final String str6, final String str7, final String str8, final String str9, final String str10, final String str11, final String str12) {
        if (iFrameView == null || iTitleNView == null) {
            return;
        }
        if (!BaseInfo.sDoingAnimation) {
            iTitleNView.setTitle(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12);
        } else if (iFrameView.obtainWebView().obtainFrameView() instanceof AdaFrameView) {
            ((AdaFrameView) iFrameView.obtainWebView().obtainFrameView()).obtainWindowMgr().processEvent(IMgr.MgrType.WindowMgr, 71, new l.m() { // from class: io.dcloud.common.util.TitleNViewUtil.1
                @Override // io.dcloud.common.core.ui.l.m
                public void onAnimationEnd() {
                    iTitleNView.setTitle(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12);
                }
            });
        }
    }

    public static int getColorAlpha(String str) {
        int iStringToColor;
        try {
            iStringToColor = Color.parseColor(str);
        } catch (Exception unused) {
            iStringToColor = PdrUtil.stringToColor(str);
        }
        return Color.alpha(iStringToColor);
    }

    public static Object getTitleNView(AbsMgr absMgr, IWebview iWebview, IFrameView iFrameView, String str) {
        if (absMgr == null || iWebview == null || iFrameView == null || TextUtils.isEmpty(str)) {
            return null;
        }
        return absMgr.processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[]{iWebview, "nativeobj", "getNativeView", new Object[]{iFrameView, str}});
    }

    public static String getTitleNViewId(IFrameView iFrameView) {
        if (iFrameView == null || iFrameView.obtainWebView() == null || iFrameView.obtainWebView().obtainWindowView() == null) {
            return null;
        }
        return (2 == iFrameView.getFrameType() || 4 == iFrameView.getFrameType() || 5 == iFrameView.getFrameType()) ? String.valueOf(iFrameView.obtainWebView().obtainWindowView().hashCode()) : iFrameView.obtainWebView().getWebviewUUID();
    }

    public static String getTitleNViewSearchInputText(ITitleNView iTitleNView) {
        return iTitleNView.getTitleNViewSearchInputText();
    }

    public static String intColor2String(int i, boolean z) {
        StringBuilder sb = new StringBuilder();
        String hexString = Integer.toHexString(Color.alpha(i));
        String hexString2 = Integer.toHexString(Color.red(i));
        String hexString3 = Integer.toHexString(Color.green(i));
        String hexString4 = Integer.toHexString(Color.blue(i));
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
        }
        if (hexString2.length() == 1) {
            hexString2 = "0" + hexString2;
        }
        if (hexString3.length() == 1) {
            hexString3 = "0" + hexString3;
        }
        if (hexString4.length() == 1) {
            hexString4 = "0" + hexString4;
        }
        sb.append("#");
        if (z) {
            sb.append(hexString);
        }
        sb.append(hexString2);
        sb.append(hexString3);
        sb.append(hexString4);
        return sb.toString();
    }

    public static boolean isButtonsIsChanged(JSONObject jSONObject, JSONObject jSONObject2) {
        return jSONObject2.has("buttons");
    }

    public static boolean isSearchInputChange(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject2 != null && jSONObject2.has("searchInput")) {
            JSONObject jSONObjectOptJSONObject = jSONObject != null ? jSONObject.optJSONObject("searchInput") : null;
            JSONObject jSONObjectOptJSONObject2 = jSONObject2.optJSONObject("searchInput");
            if (jSONObjectOptJSONObject == null && jSONObjectOptJSONObject2 != null) {
                return true;
            }
            if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject2 == null) {
                return true;
            }
            if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject2.length() > 0 && (!jSONObjectOptJSONObject.optString(AbsoluteConst.JSON_KEY_ALIGN).equalsIgnoreCase(jSONObjectOptJSONObject2.optString(AbsoluteConst.JSON_KEY_ALIGN)) || !jSONObjectOptJSONObject.optString("backgroundColor").equalsIgnoreCase(jSONObjectOptJSONObject2.optString("backgroundColor")) || !jSONObjectOptJSONObject.optString(Constants.Name.BORDER_RADIUS).equalsIgnoreCase(jSONObjectOptJSONObject2.optString(Constants.Name.BORDER_RADIUS)) || !jSONObjectOptJSONObject.optString(Constants.Name.PLACEHOLDER).equalsIgnoreCase(jSONObjectOptJSONObject2.optString(Constants.Name.PLACEHOLDER)) || !jSONObjectOptJSONObject.optString(Constants.Name.PLACEHOLDER_COLOR).equalsIgnoreCase(jSONObjectOptJSONObject2.optString(Constants.Name.PLACEHOLDER_COLOR)) || !jSONObjectOptJSONObject.optString("color").equalsIgnoreCase(jSONObjectOptJSONObject2.optString("color")) || jSONObjectOptJSONObject.optBoolean(Constants.Name.DISABLED) == jSONObjectOptJSONObject2.optBoolean(Constants.Name.DISABLED) || jSONObjectOptJSONObject.optBoolean("autoFocus") == jSONObjectOptJSONObject2.optBoolean("autoFocus"))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isShadowChanged(JSONObject jSONObject, JSONObject jSONObject2) {
        JSONObject jSONObjectOptJSONObject;
        if (jSONObject2 == null || !jSONObject2.has("shadow")) {
            return false;
        }
        JSONObject jSONObjectOptJSONObject2 = jSONObject2.optJSONObject("shadow");
        if (jSONObject == null && jSONObjectOptJSONObject2 != null && jSONObjectOptJSONObject2.has("color")) {
            return true;
        }
        if (jSONObject == null) {
            return false;
        }
        if (jSONObject.has("shadow")) {
            return (!jSONObject.has("shadow") || (jSONObjectOptJSONObject = jSONObject.optJSONObject("shadow")) == null || jSONObjectOptJSONObject2 == null || jSONObjectOptJSONObject2.optString("color").equals(jSONObjectOptJSONObject.optString("color"))) ? false : true;
        }
        if (jSONObjectOptJSONObject2 != null) {
            return !TextUtils.isEmpty(jSONObjectOptJSONObject2.optString("color"));
        }
        return false;
    }

    public static boolean isTitleTypeForDef(JSONObject jSONObject) {
        if (jSONObject != null) {
            return !jSONObject.has("type") || "default".equals(jSONObject.opt("type"));
        }
        return false;
    }

    private void jsonCompare(JSONObject jSONObject, JSONObject jSONObject2) {
        jSONObject.keys();
    }

    public static boolean paddingIsChanged(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject2.has("padding")) {
            return checkKeyValueIsModify("padding", jSONObject, jSONObject2);
        }
        if (jSONObject2.has(Constants.Name.PADDING_RIGHT)) {
            return checkKeyValueIsModify(Constants.Name.PADDING_RIGHT, jSONObject, jSONObject2);
        }
        if (jSONObject2.has(Constants.Name.PADDING_LEFT)) {
            return checkKeyValueIsModify(Constants.Name.PADDING_LEFT, jSONObject, jSONObject2);
        }
        if (jSONObject2.has(AbsoluteConst.JSON_KEY_PADDING_LEFT)) {
            return checkKeyValueIsModify(AbsoluteConst.JSON_KEY_PADDING_LEFT, jSONObject, jSONObject2);
        }
        if (jSONObject2.has(AbsoluteConst.JSON_KEY_PADDING_RIGHT)) {
            return checkKeyValueIsModify(AbsoluteConst.JSON_KEY_PADDING_RIGHT, jSONObject, jSONObject2);
        }
        return false;
    }

    public static boolean progressIsChanged(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject2 != null && jSONObject2.has(AbsoluteConst.JSON_KEY_PROGRESS) && jSONObject != null && jSONObject2 != null) {
            if (jSONObject.has(AbsoluteConst.JSON_KEY_PROGRESS) || !jSONObject2.has(AbsoluteConst.JSON_KEY_PROGRESS)) {
                if (jSONObject.has(AbsoluteConst.JSON_KEY_PROGRESS) && jSONObject2.has(AbsoluteConst.JSON_KEY_PROGRESS)) {
                    if ((jSONObject.optJSONObject(AbsoluteConst.JSON_KEY_PROGRESS) == null || "undefined".equals(jSONObject.optString(AbsoluteConst.JSON_KEY_PROGRESS))) && jSONObject2.optJSONObject(AbsoluteConst.JSON_KEY_PROGRESS) != null && !"undefined".equals(jSONObject2.optString(AbsoluteConst.JSON_KEY_PROGRESS))) {
                        return true;
                    }
                    if (jSONObject.optJSONObject(AbsoluteConst.JSON_KEY_PROGRESS) != null && !"undefined".equals(jSONObject.optString(AbsoluteConst.JSON_KEY_PROGRESS)) && (jSONObject2.optJSONObject(AbsoluteConst.JSON_KEY_PROGRESS) == null || "undefined".equals(jSONObject2.optString(AbsoluteConst.JSON_KEY_PROGRESS)))) {
                        return true;
                    }
                    if (jSONObject.optJSONObject(AbsoluteConst.JSON_KEY_PROGRESS) != null && !"undefined".equals(jSONObject.optString(AbsoluteConst.JSON_KEY_PROGRESS))) {
                        if ((!"undefined".equals(jSONObject2.optString(AbsoluteConst.JSON_KEY_PROGRESS))) & (jSONObject2.optJSONObject(AbsoluteConst.JSON_KEY_PROGRESS) != null)) {
                            String strOptString = jSONObject.optString("height");
                            String strOptString2 = jSONObject.optString("color");
                            String strOptString3 = jSONObject2.optString("height");
                            String strOptString4 = jSONObject2.optString("color");
                            if (!strOptString.equals(strOptString3) || !strOptString2.equals(strOptString4)) {
                                return true;
                            }
                        }
                    }
                }
            } else if (jSONObject2.optJSONObject(AbsoluteConst.JSON_KEY_PROGRESS) != null && "undefined".equals(jSONObject2.optString(AbsoluteConst.JSON_KEY_PROGRESS))) {
                return true;
            }
        }
        return false;
    }

    public static boolean redDotChange(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject2 != null && jSONObject2.has("redDotColor")) {
            if (jSONObject != null || jSONObject2 == null) {
                if (jSONObject != null && jSONObject2 != null) {
                    String strOptString = jSONObject.optString("redDotColor");
                    String strOptString2 = jSONObject2.optString("redDotColor");
                    if (TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString2)) {
                        return true;
                    }
                    if (!TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString2) && !strOptString.equals(strOptString2)) {
                        return true;
                    }
                }
            } else if (!TextUtils.isEmpty(jSONObject2.optString("redDotColor"))) {
                return true;
            }
        }
        return false;
    }

    public static void setBackButton(ITitleNView iTitleNView, JSONObject jSONObject, int i) {
        if (iTitleNView != null) {
            JSONObject jSONObjectOptJSONObject = null;
            if (!jSONObject.has("autoBackButton")) {
                iTitleNView.removeBackButton();
                return;
            }
            if (!jSONObject.optBoolean("autoBackButton")) {
                iTitleNView.removeBackButton();
                return;
            }
            if (jSONObject.has("backButton") && jSONObject.optJSONObject("backButton") != null && !"undefined".equalsIgnoreCase(jSONObject.optString("backButton"))) {
                jSONObjectOptJSONObject = jSONObject.optJSONObject("backButton");
            }
            String strOptString = jSONObject.optString("titlecolor");
            if (jSONObjectOptJSONObject == null || !jSONObjectOptJSONObject.has("color")) {
                if (TextUtils.isEmpty(strOptString)) {
                    strOptString = jSONObject.optString(WXPickersModule.KEY_TITLE_COLOR);
                }
                if (!TextUtils.isEmpty(strOptString) && strOptString.startsWith("#") && strOptString.length() == 9) {
                    strOptString = "#" + strOptString.substring(3, strOptString.length());
                }
            } else {
                strOptString = jSONObjectOptJSONObject.optString("color");
            }
            if (!TextUtils.isEmpty(strOptString) || strOptString.startsWith("#")) {
                iTitleNView.addBackButton(strOptString, (jSONObjectOptJSONObject == null || !jSONObjectOptJSONObject.has("colorPressed")) ? changeColorAlpha(strOptString, 0.3f) : jSONObjectOptJSONObject.optString("colorPressed"), jSONObject.optString("type"), jSONObjectOptJSONObject);
            }
        }
    }

    public static void setBackgroundImage(ITitleNView iTitleNView, JSONObject jSONObject) {
        if (jSONObject != null) {
            iTitleNView.setBackgroundImage(jSONObject.optString(Constants.Name.BACKGROUND_IMAGE));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x017e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void setButtons(io.dcloud.common.DHInterface.ITitleNView r26, org.json.JSONObject r27, io.dcloud.common.DHInterface.IWebview r28) {
        /*
            Method dump skipped, instruction units count: 407
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.util.TitleNViewUtil.setButtons(io.dcloud.common.DHInterface.ITitleNView, org.json.JSONObject, io.dcloud.common.DHInterface.IWebview):void");
    }

    public static void setCapsuleButtonStyle(ITitleNView iTitleNView, JSONObject jSONObject) {
        if (iTitleNView == null || jSONObject == null || !jSONObject.has("capsuleButtonStyle")) {
            return;
        }
        iTitleNView.setCapsuleButtonStyle(jSONObject.optJSONObject("capsuleButtonStyle"));
    }

    public static void setHomeButton(ITitleNView iTitleNView, JSONObject jSONObject, int i) {
        if (iTitleNView != null) {
            if (!((jSONObject.has("homeButton") && jSONObject.optBoolean("homeButton")) ? true : i == 5)) {
                iTitleNView.removeHomeButton();
                return;
            }
            String strOptString = jSONObject.optString("titlecolor");
            if (TextUtils.isEmpty(strOptString)) {
                strOptString = jSONObject.optString(WXPickersModule.KEY_TITLE_COLOR);
            }
            if (!TextUtils.isEmpty(strOptString) && strOptString.startsWith("#") && strOptString.length() == 9) {
                strOptString = "#" + strOptString.substring(3, strOptString.length());
            }
            if (!TextUtils.isEmpty(strOptString) || strOptString.startsWith("#")) {
                iTitleNView.addHomeButton(strOptString, changeColorAlpha(strOptString, 0.3f), jSONObject.optString("type"));
            }
        }
    }

    public static void setProgress(ITitleNView iTitleNView, JSONObject jSONObject) {
        if (iTitleNView != null && jSONObject.has(AbsoluteConst.JSON_KEY_PROGRESS)) {
            if (jSONObject.optJSONObject(AbsoluteConst.JSON_KEY_PROGRESS) == null || "undefined".equalsIgnoreCase(jSONObject.optString(AbsoluteConst.JSON_KEY_PROGRESS))) {
                iTitleNView.setProgress("", "");
                return;
            }
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(AbsoluteConst.JSON_KEY_PROGRESS);
            String strOptString = jSONObjectOptJSONObject.optString("height");
            if (!jSONObjectOptJSONObject.has("height") || "".equals(strOptString)) {
                strOptString = "2px";
            }
            String strOptString2 = jSONObjectOptJSONObject.optString("color");
            if (!jSONObjectOptJSONObject.has("color") || "".equals(strOptString2)) {
                strOptString2 = "#00FF00";
            }
            iTitleNView.setProgress(strOptString, strOptString2);
        }
    }

    public static void setRedDotColor(ITitleNView iTitleNView, JSONObject jSONObject) {
        if (jSONObject != null) {
            iTitleNView.setRedDotColor(PdrUtil.stringToColor(jSONObject.optString("redDotColor")));
        }
    }

    public static void setSearchInput(ITitleNView iTitleNView, JSONObject jSONObject, IWebview iWebview) {
        if (iTitleNView == null) {
            return;
        }
        if (!jSONObject.has("searchInput")) {
            iTitleNView.clearSearchInput();
        } else {
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("searchInput");
            iTitleNView.addSearchInput(jSONObjectOptJSONObject.optString(AbsoluteConst.JSON_KEY_ALIGN), jSONObjectOptJSONObject.optString("backgroundColor"), jSONObjectOptJSONObject.optString(Constants.Name.BORDER_RADIUS, "0px"), jSONObjectOptJSONObject.optString(Constants.Name.PLACEHOLDER), jSONObjectOptJSONObject.optString(Constants.Name.PLACEHOLDER_COLOR, "#CCCCCC"), jSONObjectOptJSONObject.optString("color", AMapUtil.HtmlBlack), jSONObjectOptJSONObject.optBoolean(Constants.Name.DISABLED, false), jSONObjectOptJSONObject.optBoolean("autoFocus", false), iWebview);
        }
    }

    public static void setShadow(ITitleNView iTitleNView, JSONObject jSONObject) {
        if (iTitleNView == null) {
            return;
        }
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("shadow");
        if (jSONObjectOptJSONObject != null) {
            iTitleNView.setShadow(jSONObjectOptJSONObject);
        } else {
            iTitleNView.setShadow(new JSONObject());
        }
    }

    public static void setSplitLine(ITitleNView iTitleNView, IWebview iWebview, JSONObject jSONObject, JSONObject jSONObject2, boolean z, String str) {
        if (iTitleNView != null) {
            if (jSONObject2 == null) {
                if (jSONObject != null) {
                    iTitleNView.removeSplitLine();
                    return;
                }
                return;
            }
            JSONObject jSONObjectCombinJSONObject = JSONUtil.combinJSONObject(jSONObject, jSONObject2);
            String strOptString = jSONObjectCombinJSONObject.optString("color");
            if ((jSONObjectCombinJSONObject.has("color") && "".equals(strOptString)) || !jSONObjectCombinJSONObject.has("color")) {
                strOptString = "#CCCCCC";
            }
            if (z && "transparent".equals(str)) {
                strOptString = changeColorAlpha(strOptString, 0.0f);
            }
            try {
                if ("transparent".equals(str) && iWebview.obtainWindowView().getScrollY() == 0) {
                    strOptString = changeColorAlpha(strOptString, 0.0f);
                }
            } catch (Exception unused) {
            }
            String strOptString2 = jSONObjectCombinJSONObject.optString("height");
            if (TextUtils.isEmpty(strOptString)) {
                return;
            }
            if (TextUtils.isEmpty(strOptString2)) {
                strOptString2 = "1px";
            }
            iTitleNView.setSplitLine(strOptString2, strOptString);
        }
    }

    public static void setSubTitleIcon(ITitleNView iTitleNView, JSONObject jSONObject) {
        if (jSONObject != null) {
            String strOptString = jSONObject.optString(WXPickersModule.KEY_TITLE_COLOR);
            if (TextUtils.isEmpty(strOptString)) {
                strOptString = jSONObject.optString("titlecolor");
            }
            iTitleNView.setIconSubTitleStyle(jSONObject.optString("titleIcon"), jSONObject.optString("titleIconRadius"), jSONObject.optString("subtitleText"), jSONObject.optString("subtitleColor"), jSONObject.optString("subtitleSize"), jSONObject.optString("subtitleOverflow"), strOptString, jSONObject.optString("titleAlign"), jSONObject.optString("titleIconWidth"));
        }
    }

    public static void setTitleAlign(ITitleNView iTitleNView, JSONObject jSONObject) {
        if (jSONObject != null) {
            iTitleNView.setTitleAlign(jSONObject.optString("titleAlign"));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void setTitleNViewButtonStyle(ITitleNView iTitleNView, String str, JSONObject jSONObject, IFrameView iFrameView) {
        int i;
        String str2;
        String strOptString;
        JSONObject jSONObjectOptJSONObject;
        try {
            i = Integer.parseInt(str);
        } catch (Exception unused) {
            i = -1;
        }
        if (jSONObject != null) {
            String strOptString2 = "";
            String strOptString3 = jSONObject.optString("type", "");
            String strOptString4 = jSONObject.optString("color");
            String strOptString5 = jSONObject.optString("colorPressed");
            String strOptString6 = jSONObject.optString("width");
            String strOptString7 = jSONObject.optString("background");
            if (!PdrUtil.isEmpty(strOptString4) && PdrUtil.isEmpty(strOptString5)) {
                strOptString5 = changeColorAlpha(strOptString4, 0.3f);
            }
            String str3 = strOptString5;
            if (jSONObject.has("__cb__")) {
                JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("__cb__");
                String strOptString8 = jSONObjectOptJSONObject2.optString("id");
                jSONObjectOptJSONObject2.optString("htmlId");
                str2 = strOptString8;
            } else {
                if (jSONObject.has("onClick")) {
                    strOptString = jSONObject.optString("onClick");
                } else if (jSONObject.has("onclick")) {
                    strOptString = jSONObject.optString("onclick");
                } else {
                    str2 = null;
                }
                str2 = strOptString;
            }
            AdaFrameItem adaFrameItem = (AdaFrameItem) iFrameView;
            JSONObject jSONObject2 = adaFrameItem.obtainFrameOptions().titleNView;
            JSONArray jSONArrayOptJSONArray = jSONObject2.optJSONArray("buttons");
            if (jSONArrayOptJSONArray != null && i < jSONArrayOptJSONArray.length() && i >= 0 && (jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i)) != null) {
                strOptString2 = jSONObjectOptJSONObject.optString("width");
            }
            if (!iTitleNView.setTitleNViewButtonStyle(i, jSONObject.optString("text"), jSONObject.optString("title"), strOptString4, str3, jSONObject.optString(Constants.Name.FONT_WEIGHT), jSONObject.optString(Constants.Name.FONT_SIZE), jSONObject.optString("fontSrc"), strOptString3, str2, iFrameView.obtainWebView(), strOptString7, jSONObject.optString("redDot"), jSONObject.has("badgeText") ? jSONObject.optString("badgeText") : null, jSONObject.optString("select"), strOptString6, jSONObject2.optString("type"), jSONObject.optString(Constants.Name.MAX_WIDTH), strOptString2) || jSONArrayOptJSONArray == null || i >= jSONArrayOptJSONArray.length() || i < 0) {
                return;
            }
            JSONUtil.combinJSONObject(jSONArrayOptJSONArray.optJSONObject(i), jSONObject);
            adaFrameItem.obtainFrameOptions().setTitleNView(jSONObject2, iFrameView.obtainWebView());
        }
    }

    public static void setTitleNViewPadding(ITitleNView iTitleNView, IWebview iWebview, JSONObject jSONObject) {
        int iConvertToScreenInt;
        int iConvertToScreenInt2;
        if (jSONObject.has("padding")) {
            iConvertToScreenInt2 = PdrUtil.convertToScreenInt(jSONObject.optString("padding"), 0, 0, iWebview.getScale());
            iConvertToScreenInt = PdrUtil.convertToScreenInt(jSONObject.optString("padding"), 0, 0, iWebview.getScale());
        } else {
            iConvertToScreenInt = 0;
            iConvertToScreenInt2 = 0;
        }
        if (jSONObject.has(Constants.Name.PADDING_LEFT)) {
            iConvertToScreenInt2 = PdrUtil.convertToScreenInt(jSONObject.optString(Constants.Name.PADDING_LEFT), 0, 0, iWebview.getScale());
        } else if (jSONObject.has(AbsoluteConst.JSON_KEY_PADDING_LEFT)) {
            iConvertToScreenInt2 = PdrUtil.convertToScreenInt(jSONObject.optString(AbsoluteConst.JSON_KEY_PADDING_LEFT), 0, 0, iWebview.getScale());
        }
        if (jSONObject.has(Constants.Name.PADDING_RIGHT)) {
            iConvertToScreenInt = PdrUtil.convertToScreenInt(jSONObject.optString(Constants.Name.PADDING_RIGHT), 0, 0, iWebview.getScale());
        } else if (jSONObject.has(AbsoluteConst.JSON_KEY_PADDING_RIGHT)) {
            iConvertToScreenInt = PdrUtil.convertToScreenInt(jSONObject.optString(AbsoluteConst.JSON_KEY_PADDING_RIGHT), 0, 0, iWebview.getScale());
        }
        iTitleNView.setTitleNViewPadding(iConvertToScreenInt2, 0, iConvertToScreenInt, 0);
    }

    public static void setTitleNViewSearchInputFocus(ITitleNView iTitleNView, String str) {
        iTitleNView.setSearchInputFocus(Boolean.parseBoolean(str));
    }

    public static void setTitleNViewSearchInputText(ITitleNView iTitleNView, String str) {
        iTitleNView.setTitleNViewSearchInputText(str);
    }

    public static boolean splitLineIsChanged(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject2 != null && jSONObject2.has("splitLine")) {
            JSONObject jSONObjectOptJSONObject = jSONObject != null ? jSONObject.optJSONObject("splitLine") : null;
            JSONObject jSONObjectOptJSONObject2 = jSONObject2 != null ? jSONObject2.optJSONObject("splitLine") : null;
            if (jSONObjectOptJSONObject == null && jSONObjectOptJSONObject2 != null) {
                return true;
            }
            if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject2 == null) {
                return true;
            }
            if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject2 != null) {
                String strOptString = jSONObjectOptJSONObject.optString("color");
                String strOptString2 = jSONObjectOptJSONObject2.optString("color");
                if (strOptString == null && strOptString2 != null) {
                    return true;
                }
                if (strOptString != null && strOptString2 != null && !strOptString.equals(strOptString2)) {
                    return true;
                }
                String strOptString3 = jSONObjectOptJSONObject.optString("height");
                String strOptString4 = jSONObjectOptJSONObject2.optString("height");
                if (strOptString3 == null && strOptString4 != null) {
                    return true;
                }
                if (strOptString3 != null && strOptString4 != null && !strOptString3.equals(strOptString4)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void startProcess(ITitleNView iTitleNView) {
        if (iTitleNView == null) {
            return;
        }
        iTitleNView.startProgress();
    }

    public static void stopProcess(ITitleNView iTitleNView) {
        if (iTitleNView == null) {
            return;
        }
        iTitleNView.stopProgress();
    }

    public static boolean subTitleIconChanged(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject2 == null) {
            return false;
        }
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        return (jSONObject2.optString("titleIcon").equals(jSONObject.optString("titleIcon")) && jSONObject2.optString("titleIconRadius").equals(jSONObject.optString("titleIconRadius")) && jSONObject2.optString("subtitleText").equals(jSONObject.optString("subtitleText")) && jSONObject2.optString("subtitleColor").equals(jSONObject.optString("subtitleColor")) && jSONObject2.optString("subtitleSize").equals(jSONObject.optString("subtitleSize")) && jSONObject2.optString("subtitleOverflow").equals(jSONObject.optString("subtitleOverflow"))) ? false : true;
    }

    public static boolean titleAlignIsChanged(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject2 != null && jSONObject2.has("titleAlign")) {
            if (jSONObject == null) {
                return !TextUtils.isEmpty(jSONObject2.optString("titleAlign"));
            }
            String strOptString = jSONObject.optString("titleAlign");
            String strOptString2 = jSONObject2.optString("titleAlign");
            if (TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString2)) {
                return true;
            }
            if (!TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString2) && !strOptString.equals(strOptString2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean titleColorIsChanged(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject2 != null && (jSONObject2.has(WXPickersModule.KEY_TITLE_COLOR) || jSONObject2.has("titlecolor"))) {
            if (jSONObject == null && jSONObject2 != null && ((jSONObject2.has(WXPickersModule.KEY_TITLE_COLOR) && !TextUtils.isEmpty(jSONObject2.optString(WXPickersModule.KEY_TITLE_COLOR))) || (jSONObject2.has("titlecolor") && !TextUtils.isEmpty(jSONObject2.optString("titlecolor"))))) {
                return true;
            }
            if (jSONObject != null && jSONObject2 != null) {
                if (jSONObject.has(WXPickersModule.KEY_TITLE_COLOR)) {
                    String strOptString = jSONObject.optString(WXPickersModule.KEY_TITLE_COLOR);
                    if (jSONObject2.has(WXPickersModule.KEY_TITLE_COLOR)) {
                        String strOptString2 = jSONObject2.optString(WXPickersModule.KEY_TITLE_COLOR);
                        if (TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString2)) {
                            return true;
                        }
                        if (!TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString2) && !strOptString.equals(strOptString2)) {
                            return true;
                        }
                    } else if (jSONObject2.has("titlecolor")) {
                        String strOptString3 = jSONObject2.optString("titlecolor");
                        if (TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString3)) {
                            return true;
                        }
                        if (!TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString3) && !strOptString.equals(strOptString3)) {
                            return true;
                        }
                    }
                } else if (jSONObject.has("titlecolor")) {
                    String strOptString4 = jSONObject.optString("titlecolor");
                    if (jSONObject2.has(WXPickersModule.KEY_TITLE_COLOR)) {
                        String strOptString5 = jSONObject2.optString(WXPickersModule.KEY_TITLE_COLOR);
                        if (TextUtils.isEmpty(strOptString4) && !TextUtils.isEmpty(strOptString5)) {
                            return true;
                        }
                        if (!TextUtils.isEmpty(strOptString4) && !TextUtils.isEmpty(strOptString5) && !strOptString4.equals(strOptString5)) {
                            return true;
                        }
                    } else if (jSONObject2.has("titlecolor")) {
                        String strOptString6 = jSONObject2.optString("titlecolor");
                        if (TextUtils.isEmpty(strOptString4) && !TextUtils.isEmpty(strOptString6)) {
                            return true;
                        }
                        if (!TextUtils.isEmpty(strOptString4) && !TextUtils.isEmpty(strOptString6) && !strOptString4.equals(strOptString6)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean titleIsChanged(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject2 != null && (jSONObject2.has("titleText") || jSONObject2.has("titletext"))) {
            if (jSONObject == null && jSONObject2 != null && ((jSONObject2.has("titleText") && !TextUtils.isEmpty(jSONObject2.optString("titleText"))) || (jSONObject2.has("titletext") && !TextUtils.isEmpty(jSONObject2.optString("titletext"))))) {
                return true;
            }
            if (jSONObject != null && jSONObject2 != null) {
                if (jSONObject.has("titleText")) {
                    String strOptString = jSONObject.optString("titleText");
                    if (jSONObject2.has("titleText")) {
                        String strOptString2 = jSONObject2.optString("titleText");
                        if ((!TextUtils.isEmpty(strOptString) || TextUtils.isEmpty(strOptString2)) && !TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString2) && !strOptString.equals(strOptString2)) {
                            return true;
                        }
                    } else if (jSONObject2.has("titletext")) {
                        String strOptString3 = jSONObject2.optString("titletext");
                        if ((!TextUtils.isEmpty(strOptString) || TextUtils.isEmpty(strOptString3)) && !TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString3) && !strOptString.equals(strOptString3)) {
                            return true;
                        }
                    }
                } else if (jSONObject.has("titletext")) {
                    String strOptString4 = jSONObject.optString("titletext");
                    if (jSONObject2.has("titleText")) {
                        String strOptString5 = jSONObject2.optString("titleText");
                        if ((!TextUtils.isEmpty(strOptString4) || TextUtils.isEmpty(strOptString5)) && !TextUtils.isEmpty(strOptString4) && !TextUtils.isEmpty(strOptString5) && !strOptString4.equals(strOptString5)) {
                            return true;
                        }
                    } else if (jSONObject2.has("titletext")) {
                        String strOptString6 = jSONObject2.optString("titletext");
                        if ((!TextUtils.isEmpty(strOptString4) || TextUtils.isEmpty(strOptString6)) && !TextUtils.isEmpty(strOptString4) && !TextUtils.isEmpty(strOptString6) && !strOptString4.equals(strOptString6)) {
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static void titleNViewButtonBadge(ITitleNView iTitleNView, JSONObject jSONObject, boolean z) {
        iTitleNView.setBadgeText(jSONObject, z);
    }

    public static void titleNViewButtonRedDot(ITitleNView iTitleNView, JSONObject jSONObject, boolean z) {
        iTitleNView.setRedDot(jSONObject, z);
    }

    public static boolean titleNViewStyleNoTitle(JSONObject jSONObject) {
        if (jSONObject == null) {
            return true;
        }
        if (jSONObject.has("titleText") || jSONObject.has("titletext")) {
            return jSONObject.has("titleText") ? TextUtils.isEmpty(jSONObject.optString("titleText")) : jSONObject.has("titletext") && TextUtils.isEmpty(jSONObject.optString("titletext"));
        }
        return true;
    }

    public static boolean titleOverflowIsChanged(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject2 != null && jSONObject2.has("titleOverflow")) {
            if (jSONObject == null && jSONObject2 != null && jSONObject2.has("titleOverflow") && !TextUtils.isEmpty(jSONObject2.optString("titleOverflow"))) {
                return true;
            }
            if (jSONObject != null && jSONObject2 != null) {
                if (jSONObject.has("titleOverflow")) {
                    if (jSONObject.has("titleOverflow")) {
                        String strOptString = jSONObject.optString("titleOverflow");
                        if (jSONObject2.has("titleOverflow")) {
                            String strOptString2 = jSONObject2.optString("titleOverflow");
                            if (TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString2)) {
                                return true;
                            }
                            if (!TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString2) && !strOptString.equals(strOptString2)) {
                                return true;
                            }
                        }
                    }
                } else if (jSONObject2.has("titleOverflow") && !TextUtils.isEmpty(jSONObject2.optString("titleOverflow"))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean titleSizeIsChanged(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject2 != null && (jSONObject2.has("titleSize") || jSONObject2.has("titlesize"))) {
            if (jSONObject == null && jSONObject2 != null && ((jSONObject2.has("titleSize") && !TextUtils.isEmpty(jSONObject2.optString("titleSize"))) || (jSONObject2.has("titlesize") && !TextUtils.isEmpty(jSONObject2.optString("titlesize"))))) {
                return true;
            }
            if (jSONObject != null && jSONObject2 != null) {
                if (jSONObject.has("titleSize")) {
                    String strOptString = jSONObject.optString("titleSize");
                    if (jSONObject2.has("titleSize")) {
                        String strOptString2 = jSONObject2.optString("titleSize");
                        if (TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString2)) {
                            return true;
                        }
                        if (!TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString2) && !strOptString.equals(strOptString2)) {
                            return true;
                        }
                    } else if (jSONObject2.has("titlesize")) {
                        String strOptString3 = jSONObject2.optString("titlesize");
                        if (TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString3)) {
                            return true;
                        }
                        if (!TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString3) && !strOptString.equals(strOptString3)) {
                            return true;
                        }
                    }
                } else if (jSONObject.has("titlesize")) {
                    String strOptString4 = jSONObject.optString("titlesize");
                    if (jSONObject2.has("titleSize")) {
                        String strOptString5 = jSONObject2.optString("titleSize");
                        if (TextUtils.isEmpty(strOptString4) && !TextUtils.isEmpty(strOptString5)) {
                            return true;
                        }
                        if (!TextUtils.isEmpty(strOptString4) && !TextUtils.isEmpty(strOptString5) && !strOptString4.equals(strOptString5)) {
                            return true;
                        }
                    } else if (jSONObject2.has("titlesize")) {
                        String strOptString6 = jSONObject2.optString("titlesize");
                        if (TextUtils.isEmpty(strOptString4) && !TextUtils.isEmpty(strOptString6)) {
                            return true;
                        }
                        if (!TextUtils.isEmpty(strOptString4) && !TextUtils.isEmpty(strOptString6) && !strOptString4.equals(strOptString6)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:156:0x00df A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x007e A[Catch: Exception -> 0x02de, TryCatch #0 {Exception -> 0x02de, blocks: (B:4:0x000e, B:6:0x0031, B:11:0x0042, B:13:0x0050, B:15:0x0058, B:17:0x0062, B:21:0x006e, B:26:0x007e, B:41:0x00cf, B:42:0x00d2, B:44:0x00d7, B:52:0x00e8, B:51:0x00e5, B:54:0x00f5, B:57:0x0107, B:59:0x010d, B:61:0x0113, B:63:0x0119, B:66:0x0138, B:68:0x013e, B:70:0x0144, B:75:0x0160, B:77:0x016e, B:79:0x0174, B:81:0x017a, B:83:0x0188, B:84:0x018d, B:86:0x0193, B:88:0x019a, B:91:0x01b1, B:93:0x01b7, B:95:0x01bd, B:97:0x01cb, B:101:0x01db, B:98:0x01d1, B:100:0x01d7, B:102:0x01de, B:103:0x01e1, B:105:0x01f3, B:107:0x01f9, B:109:0x01ff, B:111:0x0205, B:113:0x0213, B:117:0x0223, B:119:0x022e, B:121:0x0241, B:123:0x024c, B:125:0x025c, B:127:0x0275, B:130:0x027c, B:132:0x0282, B:134:0x0288, B:136:0x0296, B:140:0x02ac, B:144:0x02bd, B:146:0x02ca, B:147:0x02d2, B:143:0x02b9, B:137:0x029f, B:139:0x02a8, B:126:0x026b, B:122:0x0249, B:114:0x0219, B:116:0x021f, B:27:0x0090, B:29:0x0096, B:31:0x00a2, B:33:0x00ac, B:35:0x00b2, B:39:0x00c5, B:38:0x00c1, B:8:0x0037, B:10:0x003d, B:141:0x02b4, B:47:0x00df), top: B:153:0x000e, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0090 A[Catch: Exception -> 0x02de, TryCatch #0 {Exception -> 0x02de, blocks: (B:4:0x000e, B:6:0x0031, B:11:0x0042, B:13:0x0050, B:15:0x0058, B:17:0x0062, B:21:0x006e, B:26:0x007e, B:41:0x00cf, B:42:0x00d2, B:44:0x00d7, B:52:0x00e8, B:51:0x00e5, B:54:0x00f5, B:57:0x0107, B:59:0x010d, B:61:0x0113, B:63:0x0119, B:66:0x0138, B:68:0x013e, B:70:0x0144, B:75:0x0160, B:77:0x016e, B:79:0x0174, B:81:0x017a, B:83:0x0188, B:84:0x018d, B:86:0x0193, B:88:0x019a, B:91:0x01b1, B:93:0x01b7, B:95:0x01bd, B:97:0x01cb, B:101:0x01db, B:98:0x01d1, B:100:0x01d7, B:102:0x01de, B:103:0x01e1, B:105:0x01f3, B:107:0x01f9, B:109:0x01ff, B:111:0x0205, B:113:0x0213, B:117:0x0223, B:119:0x022e, B:121:0x0241, B:123:0x024c, B:125:0x025c, B:127:0x0275, B:130:0x027c, B:132:0x0282, B:134:0x0288, B:136:0x0296, B:140:0x02ac, B:144:0x02bd, B:146:0x02ca, B:147:0x02d2, B:143:0x02b9, B:137:0x029f, B:139:0x02a8, B:126:0x026b, B:122:0x0249, B:114:0x0219, B:116:0x021f, B:27:0x0090, B:29:0x0096, B:31:0x00a2, B:33:0x00ac, B:35:0x00b2, B:39:0x00c5, B:38:0x00c1, B:8:0x0037, B:10:0x003d, B:141:0x02b4, B:47:0x00df), top: B:153:0x000e, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00cf A[Catch: Exception -> 0x02de, TryCatch #0 {Exception -> 0x02de, blocks: (B:4:0x000e, B:6:0x0031, B:11:0x0042, B:13:0x0050, B:15:0x0058, B:17:0x0062, B:21:0x006e, B:26:0x007e, B:41:0x00cf, B:42:0x00d2, B:44:0x00d7, B:52:0x00e8, B:51:0x00e5, B:54:0x00f5, B:57:0x0107, B:59:0x010d, B:61:0x0113, B:63:0x0119, B:66:0x0138, B:68:0x013e, B:70:0x0144, B:75:0x0160, B:77:0x016e, B:79:0x0174, B:81:0x017a, B:83:0x0188, B:84:0x018d, B:86:0x0193, B:88:0x019a, B:91:0x01b1, B:93:0x01b7, B:95:0x01bd, B:97:0x01cb, B:101:0x01db, B:98:0x01d1, B:100:0x01d7, B:102:0x01de, B:103:0x01e1, B:105:0x01f3, B:107:0x01f9, B:109:0x01ff, B:111:0x0205, B:113:0x0213, B:117:0x0223, B:119:0x022e, B:121:0x0241, B:123:0x024c, B:125:0x025c, B:127:0x0275, B:130:0x027c, B:132:0x0282, B:134:0x0288, B:136:0x0296, B:140:0x02ac, B:144:0x02bd, B:146:0x02ca, B:147:0x02d2, B:143:0x02b9, B:137:0x029f, B:139:0x02a8, B:126:0x026b, B:122:0x0249, B:114:0x0219, B:116:0x021f, B:27:0x0090, B:29:0x0096, B:31:0x00a2, B:33:0x00ac, B:35:0x00b2, B:39:0x00c5, B:38:0x00c1, B:8:0x0037, B:10:0x003d, B:141:0x02b4, B:47:0x00df), top: B:153:0x000e, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00d7 A[Catch: Exception -> 0x02de, TRY_LEAVE, TryCatch #0 {Exception -> 0x02de, blocks: (B:4:0x000e, B:6:0x0031, B:11:0x0042, B:13:0x0050, B:15:0x0058, B:17:0x0062, B:21:0x006e, B:26:0x007e, B:41:0x00cf, B:42:0x00d2, B:44:0x00d7, B:52:0x00e8, B:51:0x00e5, B:54:0x00f5, B:57:0x0107, B:59:0x010d, B:61:0x0113, B:63:0x0119, B:66:0x0138, B:68:0x013e, B:70:0x0144, B:75:0x0160, B:77:0x016e, B:79:0x0174, B:81:0x017a, B:83:0x0188, B:84:0x018d, B:86:0x0193, B:88:0x019a, B:91:0x01b1, B:93:0x01b7, B:95:0x01bd, B:97:0x01cb, B:101:0x01db, B:98:0x01d1, B:100:0x01d7, B:102:0x01de, B:103:0x01e1, B:105:0x01f3, B:107:0x01f9, B:109:0x01ff, B:111:0x0205, B:113:0x0213, B:117:0x0223, B:119:0x022e, B:121:0x0241, B:123:0x024c, B:125:0x025c, B:127:0x0275, B:130:0x027c, B:132:0x0282, B:134:0x0288, B:136:0x0296, B:140:0x02ac, B:144:0x02bd, B:146:0x02ca, B:147:0x02d2, B:143:0x02b9, B:137:0x029f, B:139:0x02a8, B:126:0x026b, B:122:0x0249, B:114:0x0219, B:116:0x021f, B:27:0x0090, B:29:0x0096, B:31:0x00a2, B:33:0x00ac, B:35:0x00b2, B:39:0x00c5, B:38:0x00c1, B:8:0x0037, B:10:0x003d, B:141:0x02b4, B:47:0x00df), top: B:153:0x000e, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00f5 A[Catch: Exception -> 0x02de, TRY_LEAVE, TryCatch #0 {Exception -> 0x02de, blocks: (B:4:0x000e, B:6:0x0031, B:11:0x0042, B:13:0x0050, B:15:0x0058, B:17:0x0062, B:21:0x006e, B:26:0x007e, B:41:0x00cf, B:42:0x00d2, B:44:0x00d7, B:52:0x00e8, B:51:0x00e5, B:54:0x00f5, B:57:0x0107, B:59:0x010d, B:61:0x0113, B:63:0x0119, B:66:0x0138, B:68:0x013e, B:70:0x0144, B:75:0x0160, B:77:0x016e, B:79:0x0174, B:81:0x017a, B:83:0x0188, B:84:0x018d, B:86:0x0193, B:88:0x019a, B:91:0x01b1, B:93:0x01b7, B:95:0x01bd, B:97:0x01cb, B:101:0x01db, B:98:0x01d1, B:100:0x01d7, B:102:0x01de, B:103:0x01e1, B:105:0x01f3, B:107:0x01f9, B:109:0x01ff, B:111:0x0205, B:113:0x0213, B:117:0x0223, B:119:0x022e, B:121:0x0241, B:123:0x024c, B:125:0x025c, B:127:0x0275, B:130:0x027c, B:132:0x0282, B:134:0x0288, B:136:0x0296, B:140:0x02ac, B:144:0x02bd, B:146:0x02ca, B:147:0x02d2, B:143:0x02b9, B:137:0x029f, B:139:0x02a8, B:126:0x026b, B:122:0x0249, B:114:0x0219, B:116:0x021f, B:27:0x0090, B:29:0x0096, B:31:0x00a2, B:33:0x00ac, B:35:0x00b2, B:39:0x00c5, B:38:0x00c1, B:8:0x0037, B:10:0x003d, B:141:0x02b4, B:47:0x00df), top: B:153:0x000e, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0107 A[Catch: Exception -> 0x02de, TRY_ENTER, TryCatch #0 {Exception -> 0x02de, blocks: (B:4:0x000e, B:6:0x0031, B:11:0x0042, B:13:0x0050, B:15:0x0058, B:17:0x0062, B:21:0x006e, B:26:0x007e, B:41:0x00cf, B:42:0x00d2, B:44:0x00d7, B:52:0x00e8, B:51:0x00e5, B:54:0x00f5, B:57:0x0107, B:59:0x010d, B:61:0x0113, B:63:0x0119, B:66:0x0138, B:68:0x013e, B:70:0x0144, B:75:0x0160, B:77:0x016e, B:79:0x0174, B:81:0x017a, B:83:0x0188, B:84:0x018d, B:86:0x0193, B:88:0x019a, B:91:0x01b1, B:93:0x01b7, B:95:0x01bd, B:97:0x01cb, B:101:0x01db, B:98:0x01d1, B:100:0x01d7, B:102:0x01de, B:103:0x01e1, B:105:0x01f3, B:107:0x01f9, B:109:0x01ff, B:111:0x0205, B:113:0x0213, B:117:0x0223, B:119:0x022e, B:121:0x0241, B:123:0x024c, B:125:0x025c, B:127:0x0275, B:130:0x027c, B:132:0x0282, B:134:0x0288, B:136:0x0296, B:140:0x02ac, B:144:0x02bd, B:146:0x02ca, B:147:0x02d2, B:143:0x02b9, B:137:0x029f, B:139:0x02a8, B:126:0x026b, B:122:0x0249, B:114:0x0219, B:116:0x021f, B:27:0x0090, B:29:0x0096, B:31:0x00a2, B:33:0x00ac, B:35:0x00b2, B:39:0x00c5, B:38:0x00c1, B:8:0x0037, B:10:0x003d, B:141:0x02b4, B:47:0x00df), top: B:153:0x000e, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0158  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void updateTitleNViewStatus(io.dcloud.common.DHInterface.ITitleNView r19, io.dcloud.common.DHInterface.IWebview r20, float r21, org.json.JSONObject r22, float r23) {
        /*
            Method dump skipped, instruction units count: 735
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.util.TitleNViewUtil.updateTitleNViewStatus(io.dcloud.common.DHInterface.ITitleNView, io.dcloud.common.DHInterface.IWebview, float, org.json.JSONObject, float):void");
    }

    public static String changeColorAlpha(String str, float f) {
        String[] strArrStringSplit;
        try {
            if (TextUtils.isEmpty(str) || 0.0f > f || f > 1.0f) {
                return null;
            }
            if (!str.startsWith("#")) {
                if (!str.startsWith(WXResourceUtils.RGBA) || !str.contains("(") || !str.contains(")") || !str.contains(",") || (strArrStringSplit = PdrUtil.stringSplit(str.substring(str.indexOf("(") + 1, str.indexOf(")")), ",")) == null || 4 != strArrStringSplit.length) {
                    return null;
                }
                return "rgba(" + strArrStringSplit[0] + "," + strArrStringSplit[1] + "," + strArrStringSplit[2] + "," + f + ")";
            }
            String strSubstring = str.substring(1, str.length());
            if (strSubstring.length() == 3) {
                str = "#" + strSubstring.charAt(0) + strSubstring.charAt(0) + strSubstring.charAt(1) + strSubstring.charAt(1) + strSubstring.charAt(2) + strSubstring.charAt(2);
            }
            int color = Color.parseColor(str);
            String hexString = Integer.toHexString((int) (f * 255.0f));
            if (1 == hexString.length()) {
                hexString = "0" + hexString;
            }
            String hexString2 = Integer.toHexString(Color.red(color));
            if (1 == hexString2.length()) {
                hexString2 = "0" + hexString2;
            }
            String hexString3 = Integer.toHexString(Color.green(color));
            if (1 == hexString3.length()) {
                hexString3 = "0" + hexString3;
            }
            String hexString4 = Integer.toHexString(Color.blue(color));
            if (1 == hexString4.length()) {
                hexString4 = "0" + hexString4;
            }
            return "#" + hexString + hexString2 + hexString3 + hexString4;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
