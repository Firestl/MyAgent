package io.dcloud.feature.nativeObj.richtext.span;

import android.text.TextUtils;
import android.view.View;
import io.dcloud.common.DHInterface.IWebview;

/* JADX INFO: loaded from: classes3.dex */
public class AHrefSpan extends FontSpan implements ClickSpanAble {
    public String mHref;
    public String strOnClickEvent;

    public AHrefSpan(float f, int i, int i2, int i3, int i4, String str, String str2) {
        super(f, i, i2, i3, i4);
        this.mHref = "";
        this.strOnClickEvent = str;
        this.mHref = str2;
    }

    @Override // io.dcloud.feature.nativeObj.richtext.span.ClickSpanAble
    public String getHref() {
        return this.mHref;
    }

    @Override // io.dcloud.feature.nativeObj.richtext.span.ClickSpanAble
    public String getOnClickEvent() {
        return this.strOnClickEvent;
    }

    @Override // io.dcloud.feature.nativeObj.richtext.span.ClickSpanAble
    public boolean hasClickEvent() {
        return !TextUtils.isEmpty(getOnClickEvent());
    }

    @Override // io.dcloud.feature.nativeObj.richtext.span.ClickSpanAble
    public void onClick(View view, IWebview iWebview) {
        String onClickEvent = getOnClickEvent();
        if (TextUtils.isEmpty(onClickEvent)) {
            return;
        }
        iWebview.executeScript(onClickEvent);
    }
}
