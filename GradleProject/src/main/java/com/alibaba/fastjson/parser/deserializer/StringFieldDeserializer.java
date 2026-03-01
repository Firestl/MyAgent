package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.FieldInfo;
import java.lang.reflect.Type;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class StringFieldDeserializer extends FieldDeserializer {
    public final ObjectDeserializer fieldValueDeserilizer;

    public StringFieldDeserializer(ParserConfig parserConfig, Class<?> cls, FieldInfo fieldInfo) {
        super(cls, fieldInfo);
        this.fieldValueDeserilizer = parserConfig.getDeserializer(fieldInfo);
    }

    @Override // com.alibaba.fastjson.parser.deserializer.FieldDeserializer
    public int getFastMatchToken() {
        return this.fieldValueDeserilizer.getFastMatchToken();
    }

    @Override // com.alibaba.fastjson.parser.deserializer.FieldDeserializer
    public void parseField(DefaultJSONParser defaultJSONParser, Object obj, Type type, Map<String, Object> map) {
        String string;
        JSONLexer lexer = defaultJSONParser.getLexer();
        if (lexer.token() == 4) {
            string = lexer.stringVal();
            lexer.nextToken(16);
        } else {
            Object obj2 = defaultJSONParser.parse();
            string = obj2 == null ? null : obj2.toString();
        }
        if (obj == null) {
            map.put(this.fieldInfo.getName(), string);
        } else {
            setValue(obj, string);
        }
    }
}
