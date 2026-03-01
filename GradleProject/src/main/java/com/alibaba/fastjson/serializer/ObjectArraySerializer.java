package com.alibaba.fastjson.serializer;

import com.taobao.weex.el.parse.Operators;
import dc.squareup.okhttp3.HttpUrl;
import java.io.IOException;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public class ObjectArraySerializer implements ObjectSerializer {
    public static final ObjectArraySerializer instance = new ObjectArraySerializer();

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public final void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter writer = jSONSerializer.getWriter();
        Object[] objArr = (Object[]) obj;
        if (obj == null) {
            if (writer.isEnabled(SerializerFeature.WriteNullListAsEmpty)) {
                writer.write(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
                return;
            } else {
                writer.writeNull();
                return;
            }
        }
        int length = objArr.length;
        int i = length - 1;
        if (i == -1) {
            writer.append(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
            return;
        }
        SerialContext context = jSONSerializer.getContext();
        int i2 = 0;
        jSONSerializer.setContext(context, obj, obj2, 0);
        try {
            writer.append(Operators.ARRAY_START);
            if (writer.isEnabled(SerializerFeature.PrettyFormat)) {
                jSONSerializer.incrementIndent();
                jSONSerializer.println();
                while (i2 < length) {
                    if (i2 != 0) {
                        writer.write(',');
                        jSONSerializer.println();
                    }
                    jSONSerializer.write(objArr[i2]);
                    i2++;
                }
                jSONSerializer.decrementIdent();
                jSONSerializer.println();
                writer.write(Operators.ARRAY_END);
                return;
            }
            Class<?> cls = null;
            ObjectSerializer objectSerializer = null;
            while (i2 < i) {
                Object obj3 = objArr[i2];
                if (obj3 == null) {
                    writer.append("null,");
                } else {
                    if (jSONSerializer.containsReference(obj3)) {
                        jSONSerializer.writeReference(obj3);
                    } else {
                        Class<?> cls2 = obj3.getClass();
                        if (cls2 == cls) {
                            objectSerializer.write(jSONSerializer, obj3, null, null);
                        } else {
                            ObjectSerializer objectWriter = jSONSerializer.getObjectWriter(cls2);
                            objectWriter.write(jSONSerializer, obj3, null, null);
                            objectSerializer = objectWriter;
                            cls = cls2;
                        }
                    }
                    writer.append(',');
                }
                i2++;
            }
            Object obj4 = objArr[i];
            if (obj4 == null) {
                writer.append("null]");
            } else {
                if (jSONSerializer.containsReference(obj4)) {
                    jSONSerializer.writeReference(obj4);
                } else {
                    jSONSerializer.writeWithFieldName(obj4, Integer.valueOf(i));
                }
                writer.append(Operators.ARRAY_END);
            }
        } finally {
            jSONSerializer.setContext(context);
        }
    }
}
