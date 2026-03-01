package io.dcloud.feature.audio.recorder;

import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.feature.audio.AudioRecorderMgr;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class RecordOption {
    public boolean isRateDeft;
    public String mFileName;
    public String mFormat;
    public int mSamplingRate;
    public IWebview mWebview;

    public RecordOption(IWebview iWebview, JSONObject jSONObject) {
        this.isRateDeft = false;
        this.mWebview = iWebview;
        String string = JSONUtil.getString(jSONObject, AbsoluteConst.JSON_KEY_SAMPLERATE);
        String string2 = JSONUtil.getString(jSONObject, "format");
        this.mFormat = string2;
        if (PdrUtil.isEmpty(string2)) {
            this.mFormat = "amr";
        }
        int i = AudioRecorderMgr.isPause(this.mFormat) ? 44100 : 8000;
        if (PdrUtil.isEmpty(string)) {
            this.isRateDeft = true;
            this.mSamplingRate = i;
        } else {
            this.isRateDeft = false;
            this.mSamplingRate = PdrUtil.parseInt(JSONUtil.getString(jSONObject, AbsoluteConst.JSON_KEY_SAMPLERATE), i);
        }
        this.mFileName = this.mWebview.obtainFrameView().obtainApp().convert2AbsFullPath(this.mWebview.obtainFullUrl(), PdrUtil.getDefaultPrivateDocPath(JSONUtil.getString(jSONObject, AbsoluteConst.JSON_KEY_FILENAME), this.mFormat));
    }
}
