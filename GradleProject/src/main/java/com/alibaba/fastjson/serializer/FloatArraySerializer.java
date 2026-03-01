package com.alibaba.fastjson.serializer;

import com.taobao.weex.el.parse.Operators;
import dc.squareup.okhttp3.HttpUrl;
import java.io.IOException;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public class FloatArraySerializer implements ObjectSerializer {
    public static final FloatArraySerializer instance = new FloatArraySerializer();

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public final void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter writer = jSONSerializer.getWriter();
        if (obj == null) {
            if (writer.isEnabled(SerializerFeature.WriteNullListAsEmpty)) {
                writer.write(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
                return;
            } else {
                writer.writeNull();
                return;
            }
        }
        float[] fArr = (float[]) obj;
        int length = fArr.length - 1;
        if (length == -1) {
            writer.append(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
            return;
        }
        writer.append(Operators.ARRAY_START);
        for (int i = 0; i < length; i++) {
            float f = fArr[i];
            if (Float.isNaN(f)) {
                writer.writeNull();
            } else {
                writer.append((CharSequence) Float.toString(f));
            }
            writer.append(',');
        }
        float f2 = fArr[length];
        if (Float.isNaN(f2)) {
            writer.writeNull();
        } else {
            writer.append((CharSequence) Float.toString(f2));
        }
        writer.append(Operators.ARRAY_END);
    }
}
