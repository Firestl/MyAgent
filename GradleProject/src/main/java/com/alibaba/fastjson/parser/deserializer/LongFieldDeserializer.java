package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Type;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class LongFieldDeserializer extends FieldDeserializer {
    public final ObjectDeserializer fieldValueDeserilizer;

    public LongFieldDeserializer(ParserConfig parserConfig, Class<?> cls, FieldInfo fieldInfo) {
        super(cls, fieldInfo);
        this.fieldValueDeserilizer = parserConfig.getDeserializer(fieldInfo);
    }

    @Override // com.alibaba.fastjson.parser.deserializer.FieldDeserializer
    public int getFastMatchToken() {
        return this.fieldValueDeserilizer.getFastMatchToken();
    }

    @Override // com.alibaba.fastjson.parser.deserializer.FieldDeserializer
    public void parseField(DefaultJSONParser defaultJSONParser, Object obj, Type type, Map<String, Object> map) {
        Long lCastToLong;
        JSONLexer lexer = defaultJSONParser.getLexer();
        if (lexer.token() == 2) {
            long jLongValue = lexer.longValue();
            lexer.nextToken(16);
            if (obj == null) {
                map.put(this.fieldInfo.getName(), Long.valueOf(jLongValue));
                return;
            } else {
                setValue(obj, jLongValue);
                return;
            }
        }
        if (lexer.token() == 8) {
            lCastToLong = null;
            lexer.nextToken(16);
        } else {
            lCastToLong = TypeUtils.castToLong(defaultJSONParser.parse());
        }
        if (lCastToLong == null && getFieldClass() == Long.TYPE) {
            return;
        }
        if (obj == null) {
            map.put(this.fieldInfo.getName(), lCastToLong);
        } else {
            setValue(obj, lCastToLong);
        }
    }
}
