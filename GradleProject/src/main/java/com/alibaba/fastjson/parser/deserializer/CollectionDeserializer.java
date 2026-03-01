package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes.dex */
public class CollectionDeserializer implements ObjectDeserializer {
    public static final CollectionDeserializer instance = new CollectionDeserializer();

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        Class cls;
        Collection collection;
        cls = Object.class;
        if (defaultJSONParser.getLexer().token() == 8) {
            defaultJSONParser.getLexer().nextToken(16);
            return null;
        }
        Class<?> rawClass = getRawClass(type);
        if (rawClass == AbstractCollection.class) {
            collection = (T) new ArrayList();
        } else if (rawClass.isAssignableFrom(HashSet.class)) {
            collection = (T) new HashSet();
        } else if (rawClass.isAssignableFrom(LinkedHashSet.class)) {
            collection = (T) new LinkedHashSet();
        } else if (rawClass.isAssignableFrom(TreeSet.class)) {
            collection = (T) new TreeSet();
        } else if (rawClass.isAssignableFrom(ArrayList.class)) {
            collection = (T) new ArrayList();
        } else if (rawClass.isAssignableFrom(EnumSet.class)) {
            collection = (T) EnumSet.noneOf((Class) (type instanceof ParameterizedType ? ((ParameterizedType) type).getActualTypeArguments()[0] : cls));
        } else {
            try {
                collection = (T) ((Collection) rawClass.newInstance());
            } catch (Exception unused) {
                throw new JSONException("create instane error, class " + rawClass.getName());
            }
        }
        defaultJSONParser.parseArray(type instanceof ParameterizedType ? ((ParameterizedType) type).getActualTypeArguments()[0] : Object.class, collection, obj);
        return (T) collection;
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 14;
    }

    public Class<?> getRawClass(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return getRawClass(((ParameterizedType) type).getRawType());
        }
        throw new JSONException("TODO");
    }
}
