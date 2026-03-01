package io.dcloud.feature.nativeObj.richtext.dom;

import android.text.SpannableStringBuilder;
import android.widget.TextView;
import com.xiaomi.mipush.sdk.Constants;
import io.dcloud.feature.nativeObj.richtext.IAssets;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes3.dex */
public class DomElement {
    public String id;
    public String onClickEvent;
    public DomElement parentDomElement;
    public HashMap<String, String> style;
    public String tagName;

    public void makeSpan(IAssets iAssets, TextView textView, SpannableStringBuilder spannableStringBuilder) {
        spannableStringBuilder.append("\n");
    }

    public void parseDomElement(XmlPullParser xmlPullParser) {
        this.id = xmlPullParser.getAttributeValue(xmlPullParser.getNamespace(), "id");
        this.tagName = xmlPullParser.getName();
        parseStyle(xmlPullParser.getAttributeValue(xmlPullParser.getNamespace(), "style"));
        this.onClickEvent = xmlPullParser.getAttributeValue(xmlPullParser.getNamespace(), "onclick");
    }

    public void parseStyle(String str) {
        String[] strArrSplit;
        if (str == null || (strArrSplit = str.split(";")) == null || strArrSplit.length <= 0) {
            return;
        }
        this.style = new HashMap<>(2);
        for (String str2 : strArrSplit) {
            try {
                String[] strArrSplit2 = str2.split(Constants.COLON_SEPARATOR);
                this.style.put(strArrSplit2[0].trim(), strArrSplit2[1].trim());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
