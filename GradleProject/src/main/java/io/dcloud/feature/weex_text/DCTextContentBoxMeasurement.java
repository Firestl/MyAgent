package io.dcloud.feature.weex_text;

import android.text.Html;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.AlignmentSpan;
import com.taobao.weex.dom.TextDecorationSpan;
import com.taobao.weex.dom.WXAttr;
import com.taobao.weex.dom.WXCustomStyleSpan;
import com.taobao.weex.dom.WXLineHeightSpan;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.layout.measurefunc.TextContentBoxMeasurement;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXTextDecoration;
import com.taobao.weex.utils.WXViewUtils;
import io.dcloud.common.constant.AbsoluteConst;

/* JADX INFO: loaded from: classes3.dex */
public class DCTextContentBoxMeasurement extends TextContentBoxMeasurement {
    public boolean decode;
    public String space;

    public DCTextContentBoxMeasurement(WXComponent wXComponent) {
        super(wXComponent);
        this.space = "";
        this.decode = false;
    }

    @Override // com.taobao.weex.layout.measurefunc.TextContentBoxMeasurement
    public Spanned createSpanned(String str) {
        String str2 = this.space;
        if (str2 != null) {
            if (str2.equals("ensp")) {
                str = Html.fromHtml(str.replaceAll(Operators.SPACE_STR, "&ensp;")).toString();
            } else if (this.space.equals("emsp")) {
                str = Html.fromHtml(str.replaceAll(Operators.SPACE_STR, "&emsp;")).toString();
            }
        }
        return super.createSpanned(str);
    }

    @Override // com.taobao.weex.layout.measurefunc.TextContentBoxMeasurement, com.taobao.weex.layout.ContentBoxMeasurement
    public void layoutBefore() {
        WXAttr attrs = this.mComponent.getAttrs();
        this.space = (String) attrs.get("space");
        this.decode = Boolean.valueOf(attrs.containsKey("decode") ? attrs.get("decode").toString() : AbsoluteConst.FALSE).booleanValue();
        super.layoutBefore();
    }

    @Override // com.taobao.weex.layout.measurefunc.TextContentBoxMeasurement
    public void updateSpannable(Spannable spannable, int i) {
        WXComponent wXComponent = this.mComponent;
        if (wXComponent == null || wXComponent.getInstance() == null) {
            return;
        }
        int length = spannable.length();
        int i2 = this.mFontSize;
        if (i2 == -1) {
            this.mTextPaint.setTextSize(WXViewUtils.getRealPxByWidth(this.mComponent.getInstance().getDefaultFontSize(), this.mComponent.getInstance().getInstanceViewPortWidthWithFloat()));
        } else {
            this.mTextPaint.setTextSize(i2);
        }
        int i3 = this.mLineHeight;
        if (i3 != -1) {
            setSpan(spannable, new WXLineHeightSpan(i3), 0, length, i);
        }
        setSpan(spannable, new AlignmentSpan.Standard(this.mAlignment), 0, length, i);
        if (this.mFontStyle != -1 || this.mFontWeight != -1 || this.mFontFamily != null) {
            setSpan(spannable, new WXCustomStyleSpan(this.mFontStyle, this.mFontWeight, this.mFontFamily), 0, length, i);
        }
        if (this.mIsColorSet) {
            this.mTextPaint.setColor(this.mColor);
        }
        WXTextDecoration wXTextDecoration = this.mTextDecoration;
        if (wXTextDecoration == WXTextDecoration.UNDERLINE || wXTextDecoration == WXTextDecoration.LINETHROUGH) {
            setSpan(spannable, new TextDecorationSpan(this.mTextDecoration), 0, length, i);
        }
    }
}
