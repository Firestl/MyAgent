package io.dcloud.media.weex.weex_video.ijkplayer.danmaku;

import android.graphics.Color;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.util.DanmakuUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class StandardDanmaKuParser extends AcFunDanmakuParser {
    public int count = 0;

    @Override // io.dcloud.media.weex.weex_video.ijkplayer.danmaku.AcFunDanmakuParser
    public Danmakus _parse(JSONObject jSONObject, Danmakus danmakus) {
        if (danmakus == null) {
            danmakus = new Danmakus();
        }
        if (jSONObject != null && jSONObject.length() != 0) {
            try {
                String strOptString = jSONObject.optString("text", "....");
                String string = jSONObject.getString("color");
                BaseDanmaku baseDanmakuCreateDanmaku = this.mContext.mDanmakuFactory.createDanmaku(1, this.mContext);
                if (baseDanmakuCreateDanmaku != null) {
                    baseDanmakuCreateDanmaku.setTime(jSONObject.optLong("time", 0L) * 1000);
                    baseDanmakuCreateDanmaku.textSize = (this.mDispDensity - 0.6f) * 25.0f;
                    int color = Color.parseColor(string);
                    baseDanmakuCreateDanmaku.textColor = color;
                    baseDanmakuCreateDanmaku.textShadowColor = color <= -16777216 ? -1 : -16777216;
                    DanmakuUtils.fillText(baseDanmakuCreateDanmaku, strOptString);
                    baseDanmakuCreateDanmaku.setTimer(this.mTimer);
                    danmakus.addItem(baseDanmakuCreateDanmaku);
                }
            } catch (JSONException unused) {
            }
        }
        return danmakus;
    }

    @Override // io.dcloud.media.weex.weex_video.ijkplayer.danmaku.AcFunDanmakuParser
    public Danmakus doParse(JSONArray jSONArray) {
        Danmakus danmakus = new Danmakus();
        if (jSONArray == null) {
            return danmakus;
        }
        this.count = jSONArray.length();
        for (int i = 0; i < this.count; i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject != null) {
                    danmakus = _parse(jSONObject, danmakus);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return danmakus;
    }
}
