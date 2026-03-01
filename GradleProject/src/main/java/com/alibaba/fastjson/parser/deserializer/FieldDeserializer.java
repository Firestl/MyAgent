package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.util.FieldInfo;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class FieldDeserializer {
    public final Class<?> clazz;
    public final FieldInfo fieldInfo;

    public FieldDeserializer(Class<?> cls, FieldInfo fieldInfo) {
        this.clazz = cls;
        this.fieldInfo = fieldInfo;
    }

    public int getFastMatchToken() {
        return 0;
    }

    public Field getField() {
        return this.fieldInfo.getField();
    }

    public Class<?> getFieldClass() {
        return this.fieldInfo.getFieldClass();
    }

    public Type getFieldType() {
        return this.fieldInfo.getFieldType();
    }

    public Method getMethod() {
        return this.fieldInfo.getMethod();
    }

    public abstract void parseField(DefaultJSONParser defaultJSONParser, Object obj, Type type, Map<String, Object> map);

    public void setValue(Object obj, boolean z) {
        setValue(obj, Boolean.valueOf(z));
    }

    public void setValue(Object obj, int i) {
        setValue(obj, Integer.valueOf(i));
    }

    public void setValue(Object obj, long j) {
        setValue(obj, Long.valueOf(j));
    }

    public void setValue(Object obj, String str) {
        setValue(obj, (Object) str);
    }

    public void setValue(Object obj, Object obj2) {
        Method method = this.fieldInfo.getMethod();
        if (method != null) {
            try {
                if (this.fieldInfo.isGetOnly()) {
                    if (Map.class.isAssignableFrom(method.getReturnType())) {
                        Map map = (Map) method.invoke(obj, new Object[0]);
                        if (map != null) {
                            map.putAll((Map) obj2);
                            return;
                        }
                        return;
                    }
                    Collection collection = (Collection) method.invoke(obj, new Object[0]);
                    if (collection != null) {
                        collection.addAll((Collection) obj2);
                        return;
                    }
                    return;
                }
                if (obj2 == null && this.fieldInfo.getFieldClass().isPrimitive()) {
                    return;
                }
                method.invoke(obj, obj2);
                return;
            } catch (Exception e2) {
                throw new JSONException("set property error, " + this.fieldInfo.getName(), e2);
            }
        }
        Field field = this.fieldInfo.getField();
        if (field != null) {
            try {
                field.set(obj, obj2);
            } catch (Exception e3) {
                throw new JSONException("set property error, " + this.fieldInfo.getName(), e3);
            }
        }
    }
}
