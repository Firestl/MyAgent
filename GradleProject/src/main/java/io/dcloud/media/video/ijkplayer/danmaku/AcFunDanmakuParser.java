package io.dcloud.media.video.ijkplayer.danmaku;

import com.taobao.weex.ui.component.WXComponent;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.parser.IDataSource;
import master.flame.danmaku.danmaku.parser.android.JSONSource;
import master.flame.danmaku.danmaku.util.DanmakuUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class AcFunDanmakuParser extends BaseDanmakuParser {
    public Danmakus _parse(JSONObject jSONObject, Danmakus danmakus) {
        int i;
        if (danmakus == null) {
            danmakus = new Danmakus();
        }
        if (jSONObject != null && jSONObject.length() != 0) {
            try {
                String[] strArrSplit = jSONObject.getString("c").split(",");
                if (strArrSplit.length > 0 && (i = Integer.parseInt(strArrSplit[2])) != 7) {
                    long j = (long) (Float.parseFloat(strArrSplit[0]) * 1000.0f);
                    int i2 = Integer.parseInt(strArrSplit[1]) | (-16777216);
                    float f = Float.parseFloat(strArrSplit[3]);
                    BaseDanmaku baseDanmakuCreateDanmaku = this.mContext.mDanmakuFactory.createDanmaku(i, this.mContext);
                    if (baseDanmakuCreateDanmaku != null) {
                        baseDanmakuCreateDanmaku.setTime(j);
                        baseDanmakuCreateDanmaku.textSize = f * (this.mDispDensity - 0.6f);
                        baseDanmakuCreateDanmaku.textColor = i2;
                        baseDanmakuCreateDanmaku.textShadowColor = i2 <= -16777216 ? -1 : -16777216;
                        DanmakuUtils.fillText(baseDanmakuCreateDanmaku, jSONObject.optString(WXComponent.PROP_FS_MATCH_PARENT, "...."));
                        baseDanmakuCreateDanmaku.setTimer(this.mTimer);
                        danmakus.addItem(baseDanmakuCreateDanmaku);
                    }
                }
            } catch (NumberFormatException | JSONException unused) {
            }
        }
        return danmakus;
    }

    public Danmakus doParse(JSONArray jSONArray) {
        Danmakus danmakus = new Danmakus();
        if (jSONArray != null && jSONArray.length() != 0) {
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    JSONArray jSONArray2 = jSONArray.getJSONArray(i);
                    if (jSONArray2.length() > 0) {
                        for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                            JSONObject jSONObject = jSONArray2.getJSONObject(i2);
                            if (jSONObject != null) {
                                danmakus = _parse(jSONObject, danmakus);
                            }
                        }
                    }
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return danmakus;
    }

    @Override // master.flame.danmaku.danmaku.parser.BaseDanmakuParser
    public Danmakus parse() {
        IDataSource<?> iDataSource = this.mDataSource;
        return (iDataSource == null || !(iDataSource instanceof JSONSource)) ? new Danmakus() : doParse(((JSONSource) iDataSource).data());
    }
}
