package io.dcloud.feature.nativeObj.richtext.dom;

import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.widget.TextView;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.feature.nativeObj.richtext.IAssets;
import io.dcloud.feature.nativeObj.richtext.span.HrSpan;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes3.dex */
public class HrDomElement extends DomElement {
    public String align;
    public String size;
    public String width;

    public String getColor() {
        HashMap<String, String> map = this.style;
        return (map == null || !map.containsKey("border-color")) ? "#EEEEEE" : this.style.get("border-color");
    }

    @Override // io.dcloud.feature.nativeObj.richtext.dom.DomElement
    public void makeSpan(IAssets iAssets, TextView textView, SpannableStringBuilder spannableStringBuilder) {
        spannableStringBuilder.append("\n");
        spannableStringBuilder.append("1");
        spannableStringBuilder.setSpan(new HrSpan("left".equalsIgnoreCase(this.align) ? 1 : "right".equalsIgnoreCase(this.align) ? 2 : 0, (int) iAssets.convertHeight(this.size, 1.0f), (int) iAssets.convertWidth(this.width, 1.0f), iAssets.stringToColor(getColor()), (int) iAssets.convertWidth("100%", 1.0f)), spannableStringBuilder.length() - 1, spannableStringBuilder.length(), 17);
    }

    @Override // io.dcloud.feature.nativeObj.richtext.dom.DomElement
    public void parseDomElement(XmlPullParser xmlPullParser) {
        super.parseDomElement(xmlPullParser);
        this.align = xmlPullParser.getAttributeValue(xmlPullParser.getNamespace(), AbsoluteConst.JSON_KEY_ALIGN);
        String attributeValue = xmlPullParser.getAttributeValue(xmlPullParser.getNamespace(), "width");
        this.width = attributeValue;
        if (TextUtils.isEmpty(attributeValue)) {
            this.width = "100%";
        }
        String attributeValue2 = xmlPullParser.getAttributeValue(xmlPullParser.getNamespace(), AbsoluteConst.JSON_KEY_SIZE);
        this.size = attributeValue2;
        if (TextUtils.isEmpty(attributeValue2)) {
            this.size = "1px";
        }
    }
}
