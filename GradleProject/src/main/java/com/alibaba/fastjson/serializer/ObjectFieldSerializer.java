package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.util.FieldInfo;
import dc.squareup.okhttp3.HttpUrl;
import io.dcloud.common.constant.AbsoluteConst;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public class ObjectFieldSerializer extends FieldSerializer {
    public String format;
    public RuntimeSerializerInfo runtimeInfo;
    public boolean writeEnumUsingToString;
    public boolean writeNullBooleanAsFalse;
    public boolean writeNullListAsEmpty;
    public boolean writeNullStringAsEmpty;
    public boolean writeNumberAsZero;

    public static class RuntimeSerializerInfo {
        public ObjectSerializer fieldSerializer;
        public Class<?> runtimeFieldClass;

        public RuntimeSerializerInfo(ObjectSerializer objectSerializer, Class<?> cls) {
            this.fieldSerializer = objectSerializer;
            this.runtimeFieldClass = cls;
        }
    }

    public ObjectFieldSerializer(FieldInfo fieldInfo) {
        super(fieldInfo);
        this.writeNumberAsZero = false;
        this.writeNullStringAsEmpty = false;
        this.writeNullBooleanAsFalse = false;
        this.writeNullListAsEmpty = false;
        this.writeEnumUsingToString = false;
        JSONField jSONField = (JSONField) fieldInfo.getAnnotation(JSONField.class);
        if (jSONField != null) {
            String str = jSONField.format();
            this.format = str;
            if (str.trim().length() == 0) {
                this.format = null;
            }
            for (SerializerFeature serializerFeature : jSONField.serialzeFeatures()) {
                if (serializerFeature == SerializerFeature.WriteNullNumberAsZero) {
                    this.writeNumberAsZero = true;
                } else if (serializerFeature == SerializerFeature.WriteNullStringAsEmpty) {
                    this.writeNullStringAsEmpty = true;
                } else if (serializerFeature == SerializerFeature.WriteNullBooleanAsFalse) {
                    this.writeNullBooleanAsFalse = true;
                } else if (serializerFeature == SerializerFeature.WriteNullListAsEmpty) {
                    this.writeNullListAsEmpty = true;
                } else if (serializerFeature == SerializerFeature.WriteEnumUsingToString) {
                    this.writeEnumUsingToString = true;
                }
            }
        }
    }

    @Override // com.alibaba.fastjson.serializer.FieldSerializer
    public void writeProperty(JSONSerializer jSONSerializer, Object obj) throws Exception {
        writePrefix(jSONSerializer);
        writeValue(jSONSerializer, obj);
    }

    @Override // com.alibaba.fastjson.serializer.FieldSerializer
    public void writeValue(JSONSerializer jSONSerializer, Object obj) throws Exception {
        String str = this.format;
        if (str != null) {
            jSONSerializer.writeWithFormat(obj, str);
            return;
        }
        if (this.runtimeInfo == null) {
            Class<?> fieldClass = obj == null ? this.fieldInfo.getFieldClass() : obj.getClass();
            this.runtimeInfo = new RuntimeSerializerInfo(jSONSerializer.getObjectWriter(fieldClass), fieldClass);
        }
        RuntimeSerializerInfo runtimeSerializerInfo = this.runtimeInfo;
        if (obj != null) {
            if (this.writeEnumUsingToString && runtimeSerializerInfo.runtimeFieldClass.isEnum()) {
                jSONSerializer.getWriter().writeString(((Enum) obj).name());
                return;
            }
            Class<?> cls = obj.getClass();
            if (cls == runtimeSerializerInfo.runtimeFieldClass) {
                runtimeSerializerInfo.fieldSerializer.write(jSONSerializer, obj, this.fieldInfo.getName(), this.fieldInfo.getFieldType());
                return;
            } else {
                jSONSerializer.getObjectWriter(cls).write(jSONSerializer, obj, this.fieldInfo.getName(), this.fieldInfo.getFieldType());
                return;
            }
        }
        if (this.writeNumberAsZero && Number.class.isAssignableFrom(runtimeSerializerInfo.runtimeFieldClass)) {
            jSONSerializer.getWriter().write('0');
            return;
        }
        if (this.writeNullStringAsEmpty && String.class == runtimeSerializerInfo.runtimeFieldClass) {
            jSONSerializer.getWriter().write("\"\"");
            return;
        }
        if (this.writeNullBooleanAsFalse && Boolean.class == runtimeSerializerInfo.runtimeFieldClass) {
            jSONSerializer.getWriter().write(AbsoluteConst.FALSE);
        } else if (this.writeNullListAsEmpty && Collection.class.isAssignableFrom(runtimeSerializerInfo.runtimeFieldClass)) {
            jSONSerializer.getWriter().write(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
        } else {
            runtimeSerializerInfo.fieldSerializer.write(jSONSerializer, null, this.fieldInfo.getName(), null);
        }
    }
}
