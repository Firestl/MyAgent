package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

/* JADX INFO: loaded from: classes.dex */
public class DateFormatSerializer implements ObjectSerializer {
    public static final DateFormatSerializer instance = new DateFormatSerializer();

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter writer = jSONSerializer.getWriter();
        if (obj == null) {
            writer.writeNull();
            return;
        }
        String pattern = ((SimpleDateFormat) obj).toPattern();
        if (!writer.isEnabled(SerializerFeature.WriteClassName) || obj.getClass() == type) {
            writer.writeString(pattern);
            return;
        }
        writer.write(Operators.BLOCK_START);
        writer.writeFieldName(JSON.DEFAULT_TYPE_KEY);
        jSONSerializer.write(obj.getClass().getName());
        writer.writeFieldValue(',', "val", pattern);
        writer.write(Operators.BLOCK_END);
    }
}
