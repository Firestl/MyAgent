package io.dcloud.feature.nativeObj.richtext;

import io.dcloud.feature.nativeObj.richtext.dom.ImgDomElement;
import java.io.InputStream;

/* JADX INFO: loaded from: classes3.dex */
public interface IAssets {
    InputStream convert2InputStream(String str);

    float convertHeight(String str, float f);

    float convertWidth(String str, float f);

    int getDefaultColor(boolean z);

    String getOnClickCallBackId();

    float getScale();

    boolean isClick();

    void loadResource(ImgDomElement.AsycLoader asycLoader);

    void setClick(boolean z);

    void setOnClickCallBackId(String str);

    int stringToColor(String str);
}
