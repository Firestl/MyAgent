package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public class EnumSerializer implements ObjectSerializer {
    public static final EnumSerializer instance = new EnumSerializer();

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter writer = jSONSerializer.getWriter();
        if (obj == null) {
            jSONSerializer.getWriter().writeNull();
        } else if (jSONSerializer.isEnabled(SerializerFeature.WriteEnumUsingToString)) {
            jSONSerializer.write(((Enum) obj).toString());
        } else {
            writer.writeInt(((Enum) obj).ordinal());
        }
    }
}
