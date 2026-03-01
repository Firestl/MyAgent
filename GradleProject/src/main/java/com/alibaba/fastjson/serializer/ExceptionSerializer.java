package com.alibaba.fastjson.serializer;

import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public class ExceptionSerializer extends JavaBeanSerializer {
    public ExceptionSerializer(Class<?> cls) {
        super(cls);
    }

    @Override // com.alibaba.fastjson.serializer.JavaBeanSerializer
    public boolean isWriteClassName(JSONSerializer jSONSerializer, Object obj, Type type, Object obj2) {
        return true;
    }
}
