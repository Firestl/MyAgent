package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import java.lang.reflect.Type;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class MapResolveFieldDeserializer extends FieldDeserializer {
    public final String key;
    public final Map map;

    public MapResolveFieldDeserializer(Map map, String str) {
        super(null, null);
        this.key = str;
        this.map = map;
    }

    @Override // com.alibaba.fastjson.parser.deserializer.FieldDeserializer
    public void parseField(DefaultJSONParser defaultJSONParser, Object obj, Type type, Map<String, Object> map) {
    }

    @Override // com.alibaba.fastjson.parser.deserializer.FieldDeserializer
    public void setValue(Object obj, Object obj2) {
        this.map.put(this.key, obj2);
    }
}
