package io.dcloud.feature.nativeObj.data;

/* JADX INFO: loaded from: classes3.dex */
public class ButtonDataItem {
    public String fontSrc;
    public String fontType;
    public String onclick;
    public String text;
    public String title;
    public String webviewUuid;

    public ButtonDataItem(String str, String str2, String str3, String str4, String str5, String str6) {
        this.text = str;
        this.title = str2;
        this.onclick = str6;
        this.fontSrc = str4;
        this.fontType = str5;
        this.webviewUuid = str3;
    }

    public String getFontSrc() {
        return this.fontSrc;
    }

    public String getFontType() {
        return this.fontType;
    }

    public String getOnclick() {
        return this.onclick;
    }

    public String getText() {
        return this.text;
    }

    public String getTitle() {
        return this.title;
    }

    public String getWebviewUuid() {
        return this.webviewUuid;
    }

    public void update(String str, String str2, String str3, String str4, String str5, String str6) {
        this.text = str;
        this.title = str2;
        this.onclick = str6;
        this.fontSrc = str4;
        this.fontType = str5;
        this.webviewUuid = str3;
    }
}
