package com.alibaba.fastjson.serializer;

import com.igexin.push.core.b;
import com.taobao.weex.el.parse.Operators;
import dc.squareup.okhttp3.HttpUrl;
import java.io.IOException;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public class ArraySerializer implements ObjectSerializer {
    public final ObjectSerializer compObjectSerializer;
    public final Class<?> componentType;

    public ArraySerializer(Class<?> cls, ObjectSerializer objectSerializer) {
        this.componentType = cls;
        this.compObjectSerializer = objectSerializer;
    }

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
        Object[] objArr = (Object[]) obj;
        int length = objArr.length;
        SerialContext context = jSONSerializer.getContext();
        jSONSerializer.setContext(context, obj, obj2, 0);
        try {
            writer.append(Operators.ARRAY_START);
            for (int i = 0; i < length; i++) {
                if (i != 0) {
                    writer.append(',');
                }
                Object obj3 = objArr[i];
                if (obj3 == null) {
                    writer.append(b.m);
                } else if (obj3.getClass() == this.componentType) {
                    this.compObjectSerializer.write(jSONSerializer, obj3, Integer.valueOf(i), null);
                } else {
                    jSONSerializer.getObjectWriter(obj3.getClass()).write(jSONSerializer, obj3, Integer.valueOf(i), null);
                }
            }
            writer.append(Operators.ARRAY_END);
        } finally {
            jSONSerializer.setContext(context);
        }
    }
}
