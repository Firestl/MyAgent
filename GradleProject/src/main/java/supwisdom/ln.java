package supwisdom;

import com.alibaba.fastjson.annotation.JSONField;
import com.taobao.weex.appfram.pickers.WXPickersModule;
import com.tencent.open.SocialConstants;

/* JADX INFO: compiled from: PolarIndicatorItem.java */
/* JADX INFO: loaded from: classes.dex */
public class ln extends jn {

    @JSONField(name = "text")
    public String b;

    @JSONField(name = SocialConstants.PARAM_APP_DESC)
    public String c;

    @JSONField(name = WXPickersModule.KEY_TEXT_COLOR)
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @JSONField(name = "descColor")
    public String f8307e;

    @JSONField(name = "lineColor")
    public String i;

    @JSONField(name = "textFontSize")
    public Float f = Float.valueOf(32.0f);

    @JSONField(name = "descFontSize")
    public Float g = Float.valueOf(24.0f);

    @JSONField(name = "displayStartCircle")
    public boolean h = true;

    @JSONField(name = "lineWidth")
    public Float j = Float.valueOf(5.0f);
}
