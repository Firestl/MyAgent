package com.alibaba.fastjson.serializer;

import com.igexin.push.core.b;
import com.taobao.weex.el.parse.Operators;
import dc.squareup.okhttp3.HttpUrl;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

/* JADX INFO: loaded from: classes.dex */
public final class ListSerializer implements ObjectSerializer {
    public static final ListSerializer instance = new ListSerializer();

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public final void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        boolean zIsEnabled = jSONSerializer.isEnabled(SerializerFeature.WriteClassName);
        SerializeWriter writer = jSONSerializer.getWriter();
        Type type2 = (zIsEnabled && (type instanceof ParameterizedType)) ? ((ParameterizedType) type).getActualTypeArguments()[0] : null;
        if (obj == null) {
            if (writer.isEnabled(SerializerFeature.WriteNullListAsEmpty)) {
                writer.write(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
                return;
            } else {
                writer.writeNull();
                return;
            }
        }
        List list = (List) obj;
        if (list.size() == 0) {
            writer.append(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
            return;
        }
        SerialContext context = jSONSerializer.getContext();
        jSONSerializer.setContext(context, obj, obj2, 0);
        try {
            if (writer.isEnabled(SerializerFeature.PrettyFormat)) {
                writer.append(Operators.ARRAY_START);
                jSONSerializer.incrementIndent();
                int i = 0;
                for (Object obj3 : list) {
                    if (i != 0) {
                        writer.append(',');
                    }
                    jSONSerializer.println();
                    if (obj3 == null) {
                        jSONSerializer.getWriter().writeNull();
                    } else if (jSONSerializer.containsReference(obj3)) {
                        jSONSerializer.writeReference(obj3);
                    } else {
                        ObjectSerializer objectWriter = jSONSerializer.getObjectWriter(obj3.getClass());
                        jSONSerializer.setContext(new SerialContext(context, obj, obj2, 0));
                        objectWriter.write(jSONSerializer, obj3, Integer.valueOf(i), type2);
                    }
                    i++;
                }
                jSONSerializer.decrementIdent();
                jSONSerializer.println();
                writer.append(Operators.ARRAY_END);
                return;
            }
            writer.append(Operators.ARRAY_START);
            int i2 = 0;
            for (Object obj4 : list) {
                if (i2 != 0) {
                    writer.append(',');
                }
                if (obj4 == null) {
                    writer.append(b.m);
                } else {
                    Class<?> cls = obj4.getClass();
                    if (cls == Integer.class) {
                        writer.writeInt(((Integer) obj4).intValue());
                    } else if (cls == Long.class) {
                        long jLongValue = ((Long) obj4).longValue();
                        if (zIsEnabled) {
                            writer.writeLongAndChar(jLongValue, Matrix.MATRIX_TYPE_RANDOM_LT);
                        } else {
                            writer.writeLong(jLongValue);
                        }
                    } else {
                        jSONSerializer.setContext(new SerialContext(context, obj, obj2, 0));
                        if (jSONSerializer.containsReference(obj4)) {
                            jSONSerializer.writeReference(obj4);
                        } else {
                            jSONSerializer.getObjectWriter(obj4.getClass()).write(jSONSerializer, obj4, Integer.valueOf(i2), type2);
                        }
                    }
                }
                i2++;
            }
            writer.append(Operators.ARRAY_END);
        } finally {
            jSONSerializer.setContext(context);
        }
    }
}
