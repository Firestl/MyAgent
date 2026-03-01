package io.dcloud.feature.nativeObj.richtext.dom;

import android.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.URLUtil;
import android.widget.TextView;
import io.dcloud.feature.nativeObj.richtext.IAssets;
import io.dcloud.feature.nativeObj.richtext.span.ImgSpan;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes3.dex */
public class ImgDomElement extends DomElement {
    public String height;
    public String href;
    public AsycLoader mAsycLoader;
    public String src;
    public String width;

    public static class AsycLoader {
        public int height;
        public String href;
        public ImgSpan self;
        public SpannableStringBuilder spaned;
        public TextView textView;
        public String url;
        public int width;

        public AsycLoader(TextView textView, SpannableStringBuilder spannableStringBuilder, ImgSpan imgSpan, String str, int i, int i2, String str2) {
            this.href = null;
            this.textView = textView;
            this.spaned = spannableStringBuilder;
            this.self = imgSpan;
            this.url = str;
            this.width = i;
            this.height = i2;
            this.href = str2;
        }

        public void onComplete(Bitmap bitmap) {
            SpannableStringBuilder spannableStringBuilder = this.spaned;
            ImgSpan imgSpan = this.self;
            String source = imgSpan.getSource();
            if (bitmap != null) {
                BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
                StringBuilder sb = new StringBuilder();
                sb.append("after w=");
                int width = this.width;
                if (width <= 0) {
                    width = bitmap.getWidth();
                }
                sb.append(width);
                sb.append(";h=");
                int height = this.height;
                if (height <= 0) {
                    height = bitmap.getHeight();
                }
                sb.append(height);
                Log.e("DnetImg", sb.toString());
                int width2 = this.width;
                if (width2 <= 0) {
                    width2 = bitmap.getWidth();
                }
                int height2 = this.height;
                if (height2 <= 0) {
                    height2 = bitmap.getHeight();
                }
                bitmapDrawable.setBounds(0, 0, width2, height2);
                ImgSpan imgSpan2 = new ImgSpan(bitmapDrawable, source, 0, imgSpan.getOnClickEvent(), this.href);
                int spanStart = spannableStringBuilder.getSpanStart(imgSpan);
                int spanEnd = spannableStringBuilder.getSpanEnd(imgSpan);
                if (spanStart < 0 || spanEnd < 0) {
                    return;
                }
                spannableStringBuilder.removeSpan(imgSpan);
                spannableStringBuilder.setSpan(imgSpan2, spanStart, spanEnd, 17);
                this.textView.post(new Runnable() { // from class: io.dcloud.feature.nativeObj.richtext.dom.ImgDomElement.AsycLoader.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AsycLoader asycLoader = AsycLoader.this;
                        asycLoader.textView.setText(asycLoader.spaned);
                        AsycLoader.this.textView.requestLayout();
                    }
                });
            }
        }
    }

    public String getSrc() {
        return this.src;
    }

    @Override // io.dcloud.feature.nativeObj.richtext.dom.DomElement
    public void makeSpan(IAssets iAssets, TextView textView, SpannableStringBuilder spannableStringBuilder) {
        String str;
        HashMap<String, String> map = this.style;
        String str2 = null;
        if (map != null) {
            String str3 = map.get("width");
            str2 = this.style.get("height");
            str = str3;
        } else {
            str = null;
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = this.height;
        }
        if (TextUtils.isEmpty(str)) {
            str = this.width;
        }
        int iConvertWidth = (int) iAssets.convertWidth(str, -2.0f);
        int iConvertHeight = (int) iAssets.convertHeight(str2, -2.0f);
        boolean zIsNetworkUrl = URLUtil.isNetworkUrl(this.src);
        Drawable drawable = zIsNetworkUrl ? Resources.getSystem().getDrawable(R.drawable.screen_background_dark_transparent) : new BitmapDrawable(BitmapFactory.decodeStream(iAssets.convert2InputStream(this.src)));
        drawable.setBounds(0, 0, iConvertWidth > 0 ? iConvertWidth : drawable.getIntrinsicWidth(), iConvertHeight > 0 ? iConvertHeight : drawable.getIntrinsicHeight());
        ImgSpan imgSpan = new ImgSpan(drawable, this.src, 0, this.onClickEvent, this.href);
        spannableStringBuilder.append("￼");
        spannableStringBuilder.setSpan(imgSpan, spannableStringBuilder.length() - 1, spannableStringBuilder.length(), 17);
        if (zIsNetworkUrl) {
            iAssets.loadResource(new AsycLoader(textView, spannableStringBuilder, imgSpan, this.src, iConvertWidth, iConvertHeight, this.href));
        }
    }

    @Override // io.dcloud.feature.nativeObj.richtext.dom.DomElement
    public void parseDomElement(XmlPullParser xmlPullParser) {
        super.parseDomElement(xmlPullParser);
        this.src = xmlPullParser.getAttributeValue(xmlPullParser.getNamespace(), "src");
        this.width = xmlPullParser.getAttributeValue(xmlPullParser.getNamespace(), "width");
        this.height = xmlPullParser.getAttributeValue(xmlPullParser.getNamespace(), "height");
        this.href = xmlPullParser.getAttributeValue(xmlPullParser.getNamespace(), "href");
    }
}
