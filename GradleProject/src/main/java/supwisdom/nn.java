package supwisdom;

import android.graphics.Color;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;

/* JADX INFO: compiled from: AChartColorParser.java */
/* JADX INFO: loaded from: classes.dex */
public class nn implements ObjectDeserializer {
    public static int a(int i, float f) {
        float f2 = f * 255.0f;
        return Color.argb((int) Math.ceil(f2 <= 255.0f ? f2 < 0.0f ? 0.0f : f2 : 255.0f), (16711680 & i) >> 16, (65280 & i) >> 8, i & 255);
    }
}
