package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public class SimpleDateFormatSerializer implements ObjectSerializer {
    public final String pattern;

    public SimpleDateFormatSerializer(String str) {
        this.pattern = str;
    }

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        if (obj == null) {
            jSONSerializer.getWriter().writeNull();
        } else {
            jSONSerializer.write(new SimpleDateFormat(this.pattern).format((Date) obj));
        }
    }
}
