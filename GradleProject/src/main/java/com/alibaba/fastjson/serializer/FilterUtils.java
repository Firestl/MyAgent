package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FilterUtils {
    public static boolean apply(JSONSerializer jSONSerializer, Object obj, String str, Object obj2) {
        List<PropertyFilter> propertyFiltersDirect = jSONSerializer.getPropertyFiltersDirect();
        if (propertyFiltersDirect == null) {
            return true;
        }
        Iterator<PropertyFilter> it = propertyFiltersDirect.iterator();
        while (it.hasNext()) {
            if (!it.next().apply(obj, str, obj2)) {
                return false;
            }
        }
        return true;
    }

    public static boolean applyName(JSONSerializer jSONSerializer, Object obj, String str) {
        List<PropertyPreFilter> propertyPreFiltersDirect = jSONSerializer.getPropertyPreFiltersDirect();
        if (propertyPreFiltersDirect == null) {
            return true;
        }
        Iterator<PropertyPreFilter> it = propertyPreFiltersDirect.iterator();
        while (it.hasNext()) {
            if (!it.next().apply(jSONSerializer, obj, str)) {
                return false;
            }
        }
        return true;
    }

    public static Type getExtratype(DefaultJSONParser defaultJSONParser, Object obj, String str) {
        List<ExtraTypeProvider> extraTypeProvidersDirect = defaultJSONParser.getExtraTypeProvidersDirect();
        Type extraType = null;
        if (extraTypeProvidersDirect == null) {
            return null;
        }
        Iterator<ExtraTypeProvider> it = extraTypeProvidersDirect.iterator();
        while (it.hasNext()) {
            extraType = it.next().getExtraType(obj, str);
        }
        return extraType;
    }

    public static void processExtra(DefaultJSONParser defaultJSONParser, Object obj, String str, Object obj2) {
        List<ExtraProcessor> extraProcessorsDirect = defaultJSONParser.getExtraProcessorsDirect();
        if (extraProcessorsDirect == null) {
            return;
        }
        Iterator<ExtraProcessor> it = extraProcessorsDirect.iterator();
        while (it.hasNext()) {
            it.next().processExtra(obj, str, obj2);
        }
    }

    public static String processKey(JSONSerializer jSONSerializer, Object obj, String str, Object obj2) {
        List<NameFilter> nameFiltersDirect = jSONSerializer.getNameFiltersDirect();
        if (nameFiltersDirect != null) {
            Iterator<NameFilter> it = nameFiltersDirect.iterator();
            while (it.hasNext()) {
                str = it.next().process(obj, str, obj2);
            }
        }
        return str;
    }

    public static Object processValue(JSONSerializer jSONSerializer, Object obj, String str, Object obj2) {
        List<ValueFilter> valueFiltersDirect = jSONSerializer.getValueFiltersDirect();
        if (valueFiltersDirect != null) {
            Iterator<ValueFilter> it = valueFiltersDirect.iterator();
            while (it.hasNext()) {
                obj2 = it.next().process(obj, str, obj2);
            }
        }
        return obj2;
    }

    public static char writeAfter(JSONSerializer jSONSerializer, Object obj, char c) {
        List<AfterFilter> afterFiltersDirect = jSONSerializer.getAfterFiltersDirect();
        if (afterFiltersDirect != null) {
            Iterator<AfterFilter> it = afterFiltersDirect.iterator();
            while (it.hasNext()) {
                c = it.next().writeAfter(jSONSerializer, obj, c);
            }
        }
        return c;
    }

    public static char writeBefore(JSONSerializer jSONSerializer, Object obj, char c) {
        List<BeforeFilter> beforeFiltersDirect = jSONSerializer.getBeforeFiltersDirect();
        if (beforeFiltersDirect != null) {
            Iterator<BeforeFilter> it = beforeFiltersDirect.iterator();
            while (it.hasNext()) {
                c = it.next().writeBefore(jSONSerializer, obj, c);
            }
        }
        return c;
    }
}
