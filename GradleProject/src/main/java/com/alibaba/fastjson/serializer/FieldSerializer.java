package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.util.FieldInfo;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.mipush.sdk.Constants;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public abstract class FieldSerializer {
    public final String double_quoted_fieldPrefix;
    public final FieldInfo fieldInfo;
    public final String single_quoted_fieldPrefix;
    public final String un_quoted_fieldPrefix;
    public boolean writeNull;

    public FieldSerializer(FieldInfo fieldInfo) {
        this.writeNull = false;
        this.fieldInfo = fieldInfo;
        fieldInfo.setAccessible(true);
        this.double_quoted_fieldPrefix = Operators.QUOTE + fieldInfo.getName() + "\":";
        this.single_quoted_fieldPrefix = Operators.SINGLE_QUOTE + fieldInfo.getName() + "':";
        StringBuilder sb = new StringBuilder();
        sb.append(fieldInfo.getName());
        sb.append(Constants.COLON_SEPARATOR);
        this.un_quoted_fieldPrefix = sb.toString();
        JSONField jSONField = (JSONField) fieldInfo.getAnnotation(JSONField.class);
        if (jSONField != null) {
            for (SerializerFeature serializerFeature : jSONField.serialzeFeatures()) {
                if (serializerFeature == SerializerFeature.WriteMapNullValue) {
                    this.writeNull = true;
                }
            }
        }
    }

    public Field getField() {
        return this.fieldInfo.getField();
    }

    public Method getMethod() {
        return this.fieldInfo.getMethod();
    }

    public String getName() {
        return this.fieldInfo.getName();
    }

    public Object getPropertyValue(Object obj) throws Exception {
        try {
            return this.fieldInfo.get(obj);
        } catch (Exception e2) {
            throw new JSONException("get property error。 " + this.fieldInfo.gerQualifiedName(), e2);
        }
    }

    public boolean isWriteNull() {
        return this.writeNull;
    }

    public void writePrefix(JSONSerializer jSONSerializer) throws IOException {
        SerializeWriter writer = jSONSerializer.getWriter();
        if (!jSONSerializer.isEnabled(SerializerFeature.QuoteFieldNames)) {
            writer.write(this.un_quoted_fieldPrefix);
        } else if (jSONSerializer.isEnabled(SerializerFeature.UseSingleQuotes)) {
            writer.write(this.single_quoted_fieldPrefix);
        } else {
            writer.write(this.double_quoted_fieldPrefix);
        }
    }

    public abstract void writeProperty(JSONSerializer jSONSerializer, Object obj) throws Exception;

    public abstract void writeValue(JSONSerializer jSONSerializer, Object obj) throws Exception;
}
