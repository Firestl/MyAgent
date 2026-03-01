package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public class MapSerializer implements ObjectSerializer {
    public static MapSerializer instance = new MapSerializer();

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        boolean z;
        SerializeWriter writer = jSONSerializer.getWriter();
        if (obj == null) {
            writer.writeNull();
            return;
        }
        Map treeMap = (Map) obj;
        if (writer.isEnabled(SerializerFeature.SortField) && !(treeMap instanceof SortedMap) && !(treeMap instanceof LinkedHashMap)) {
            try {
                treeMap = new TreeMap(treeMap);
            } catch (Exception unused) {
            }
        }
        if (jSONSerializer.containsReference(obj)) {
            jSONSerializer.writeReference(obj);
            return;
        }
        SerialContext context = jSONSerializer.getContext();
        jSONSerializer.setContext(context, obj, obj2, 0);
        try {
            writer.write(Operators.BLOCK_START);
            jSONSerializer.incrementIndent();
            if (writer.isEnabled(SerializerFeature.WriteClassName)) {
                writer.writeFieldName(JSON.DEFAULT_TYPE_KEY);
                writer.writeString(obj.getClass().getName());
                z = false;
            } else {
                z = true;
            }
            Class<?> cls = null;
            ObjectSerializer objectSerializer = null;
            for (Map.Entry entry : treeMap.entrySet()) {
                Object value = entry.getValue();
                Object key = entry.getKey();
                List<PropertyPreFilter> propertyPreFiltersDirect = jSONSerializer.getPropertyPreFiltersDirect();
                if (propertyPreFiltersDirect != null && propertyPreFiltersDirect.size() > 0) {
                    if (key == null || (key instanceof String)) {
                        if (!FilterUtils.applyName(jSONSerializer, obj, (String) key)) {
                        }
                    } else if ((key.getClass().isPrimitive() || (key instanceof Number)) && !FilterUtils.applyName(jSONSerializer, obj, JSON.toJSONString(key))) {
                    }
                }
                List<PropertyFilter> propertyFiltersDirect = jSONSerializer.getPropertyFiltersDirect();
                if (propertyFiltersDirect != null && propertyFiltersDirect.size() > 0) {
                    if (key == null || (key instanceof String)) {
                        if (!FilterUtils.apply(jSONSerializer, obj, (String) key, value)) {
                        }
                    } else if ((key.getClass().isPrimitive() || (key instanceof Number)) && !FilterUtils.apply(jSONSerializer, obj, JSON.toJSONString(key), value)) {
                    }
                }
                List<NameFilter> nameFiltersDirect = jSONSerializer.getNameFiltersDirect();
                if (nameFiltersDirect != null && nameFiltersDirect.size() > 0) {
                    if (key == null || (key instanceof String)) {
                        key = FilterUtils.processKey(jSONSerializer, obj, (String) key, value);
                    } else if (key.getClass().isPrimitive() || (key instanceof Number)) {
                        key = FilterUtils.processKey(jSONSerializer, obj, JSON.toJSONString(key), value);
                    }
                }
                List<ValueFilter> valueFiltersDirect = jSONSerializer.getValueFiltersDirect();
                if (valueFiltersDirect != null && valueFiltersDirect.size() > 0) {
                    if (key == null || (key instanceof String)) {
                        value = FilterUtils.processValue(jSONSerializer, obj, (String) key, value);
                    } else if (key.getClass().isPrimitive() || (key instanceof Number)) {
                        value = FilterUtils.processValue(jSONSerializer, obj, JSON.toJSONString(key), value);
                    }
                }
                if (value != null || jSONSerializer.isEnabled(SerializerFeature.WriteMapNullValue)) {
                    if (key instanceof String) {
                        String str = (String) key;
                        if (!z) {
                            writer.write(',');
                        }
                        if (writer.isEnabled(SerializerFeature.PrettyFormat)) {
                            jSONSerializer.println();
                        }
                        writer.writeFieldName(str, true);
                    } else {
                        if (!z) {
                            writer.write(',');
                        }
                        if (writer.isEnabled(SerializerFeature.BrowserCompatible) || writer.isEnabled(SerializerFeature.WriteNonStringKeyAsString)) {
                            jSONSerializer.write(JSON.toJSONString(key));
                        } else {
                            jSONSerializer.write(key);
                        }
                        writer.write(Operators.CONDITION_IF_MIDDLE);
                    }
                    if (value == null) {
                        writer.writeNull();
                    } else {
                        Class<?> cls2 = value.getClass();
                        if (cls2 == cls) {
                            objectSerializer.write(jSONSerializer, value, key, null);
                        } else {
                            ObjectSerializer objectWriter = jSONSerializer.getObjectWriter(cls2);
                            objectWriter.write(jSONSerializer, value, key, null);
                            objectSerializer = objectWriter;
                            cls = cls2;
                        }
                    }
                    z = false;
                }
            }
            jSONSerializer.setContext(context);
            jSONSerializer.decrementIdent();
            if (writer.isEnabled(SerializerFeature.PrettyFormat) && treeMap.size() > 0) {
                jSONSerializer.println();
            }
            writer.write(Operators.BLOCK_END);
        } catch (Throwable th) {
            jSONSerializer.setContext(context);
            throw th;
        }
    }
}
