package io.dcloud.common.DHInterface;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public interface ITitleNView {
    void addBackButton(String str, String str2, String str3, JSONObject jSONObject);

    void addHomeButton(String str, String str2, String str3);

    void addLeftButton(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, IWebview iWebview, String str11, String str12, boolean z, String str13, boolean z2, String str14);

    void addRightButton(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, IWebview iWebview, String str11, String str12, boolean z, String str13, boolean z2, String str14);

    void addSearchInput(String str, String str2, String str3, String str4, String str5, String str6, boolean z, boolean z2, IWebview iWebview);

    void clearButtons();

    void clearSearchInput();

    String getBackgroundColor();

    String getStatusBarColor();

    int getTitleColor();

    String getTitleNViewSearchInputText();

    void removeBackButton();

    void removeHomeButton();

    void removeSplitLine();

    void setBackButtonColor(String str, String str2, String str3);

    void setBackgroundColor(String str);

    void setBackgroundImage(String str);

    void setBackgroundRepeat(String str);

    void setBadgeText(JSONObject jSONObject, boolean z);

    void setButtonColorByIndex(int i, String str, String str2, String str3);

    void setButtonsColor(String str, String str2, String str3);

    void setCapsuleButtonStyle(JSONObject jSONObject);

    void setHomeButtonColor(String str, String str2, String str3);

    void setIconSubTitleStyle(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9);

    void setProgress(String str, String str2);

    void setRedDot(JSONObject jSONObject, boolean z);

    void setRedDotColor(int i);

    void setSearchInputColor(String str);

    void setSearchInputFocus(boolean z);

    void setShadow(JSONObject jSONObject);

    void setSplitLine(String str, String str2);

    void setStatusBarColor(int i);

    void setTitle(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12);

    void setTitleAlign(String str);

    void setTitleColor(int i);

    void setTitleColor(String str);

    boolean setTitleNViewButtonStyle(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, IWebview iWebview, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17);

    void setTitleNViewPadding(int i, int i2, int i3, int i4);

    void setTitleNViewSearchInputText(String str);

    void setTitleOverflow(String str);

    void setTitleSize(String str);

    void setTitleText(String str);

    void startProgress();

    void stopProgress();
}
