package io.dcloud.feature.nativeObj.richtext.dom;

import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.widget.TextView;
import com.taobao.weex.common.Constants;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.feature.nativeObj.richtext.IAssets;
import io.dcloud.feature.nativeObj.richtext.span.AHrefSpan;
import io.dcloud.feature.nativeObj.richtext.span.FontSpan;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes3.dex */
public class TextDomElement extends DomElement {
    public String color;
    public String href;
    public String text;

    private int getFontStyleInt() {
        return Constants.Value.ITALIC.equalsIgnoreCase(getFontStyle()) ? 1 : 0;
    }

    private int getFontWeightInt() {
        return Constants.Value.BOLD.equalsIgnoreCase(getFontWeight()) ? 1 : 0;
    }

    public String getColor() {
        HashMap<String, String> map = this.style;
        if (map != null && map.containsKey("color")) {
            return this.style.get("color");
        }
        if (!TextUtils.isEmpty(this.color) && !"a".equalsIgnoreCase(this.tagName)) {
            return this.color;
        }
        DomElement domElement = this.parentDomElement;
        if (domElement == null || !(domElement instanceof TextDomElement)) {
            return null;
        }
        return ((TextDomElement) domElement).getColor();
    }

    public String getFontSize() {
        HashMap<String, String> map = this.style;
        if (map != null && map.containsKey("font-size")) {
            return this.style.get("font-size");
        }
        DomElement domElement = this.parentDomElement;
        if (domElement == null || !(domElement instanceof TextDomElement)) {
            return null;
        }
        return ((TextDomElement) domElement).getFontSize();
    }

    public String getFontStyle() {
        HashMap<String, String> map = this.style;
        if (map != null && map.containsKey("font-style")) {
            return this.style.get("font-style");
        }
        DomElement domElement = this.parentDomElement;
        if (domElement == null || !(domElement instanceof TextDomElement)) {
            return null;
        }
        return ((TextDomElement) domElement).getFontStyle();
    }

    public String getFontWeight() {
        HashMap<String, String> map = this.style;
        if (map != null && map.containsKey("font-weight")) {
            return this.style.get("font-weight");
        }
        DomElement domElement = this.parentDomElement;
        if (domElement == null || !(domElement instanceof TextDomElement)) {
            return null;
        }
        return ((TextDomElement) domElement).getFontWeight();
    }

    public String getTextDecoration() {
        HashMap<String, String> map = this.style;
        if (map != null && map.containsKey("text-decoration")) {
            return this.style.get("text-decoration");
        }
        DomElement domElement = this.parentDomElement;
        if (domElement == null || !(domElement instanceof TextDomElement)) {
            return null;
        }
        return ((TextDomElement) domElement).getTextDecoration();
    }

    public int getTextDecorationInt() {
        String textDecoration = getTextDecoration();
        if (!"underline".equalsIgnoreCase(textDecoration)) {
            if ("line-through".equalsIgnoreCase(textDecoration)) {
                return 2;
            }
            if (!"a".equalsIgnoreCase(this.tagName)) {
                return 0;
            }
        }
        return 1;
    }

    @Override // io.dcloud.feature.nativeObj.richtext.dom.DomElement
    public void makeSpan(IAssets iAssets, TextView textView, SpannableStringBuilder spannableStringBuilder) {
        if (TextUtils.isEmpty(this.text)) {
            return;
        }
        boolean zEqualsIgnoreCase = "a".equalsIgnoreCase(this.tagName);
        boolean zEqualsIgnoreCase2 = "p".equalsIgnoreCase(this.tagName);
        if (zEqualsIgnoreCase2) {
            spannableStringBuilder.append("\n");
        }
        spannableStringBuilder.append((CharSequence) this.text);
        float f = PdrUtil.parseFloat(getFontSize(), 100.0f, FontSpan.DEF_FONT_SIZE, 1.0f);
        int defaultColor = iAssets.getDefaultColor(zEqualsIgnoreCase);
        int fontWeightInt = getFontWeightInt();
        int fontStyleInt = getFontStyleInt();
        int textDecorationInt = getTextDecorationInt();
        String color = getColor();
        int iStringToColor = !TextUtils.isEmpty(color) ? iAssets.stringToColor(color) : defaultColor;
        if (zEqualsIgnoreCase) {
            spannableStringBuilder.setSpan(new AHrefSpan(f, iStringToColor, fontWeightInt, fontStyleInt, textDecorationInt, this.onClickEvent, this.href), spannableStringBuilder.length() - this.text.length(), spannableStringBuilder.length(), 17);
        } else {
            spannableStringBuilder.setSpan(new FontSpan(f, iStringToColor, fontWeightInt, fontStyleInt, textDecorationInt), spannableStringBuilder.length() - this.text.length(), spannableStringBuilder.length(), 17);
        }
        if (zEqualsIgnoreCase2) {
            spannableStringBuilder.append("\n");
        }
    }

    @Override // io.dcloud.feature.nativeObj.richtext.dom.DomElement
    public void parseDomElement(XmlPullParser xmlPullParser) {
        super.parseDomElement(xmlPullParser);
        this.color = xmlPullParser.getAttributeValue(xmlPullParser.getNamespace(), "color");
        this.href = xmlPullParser.getAttributeValue(xmlPullParser.getNamespace(), "href");
    }
}
