package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Type;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class IntegerFieldDeserializer extends FieldDeserializer {
    public IntegerFieldDeserializer(ParserConfig parserConfig, Class<?> cls, FieldInfo fieldInfo) {
        super(cls, fieldInfo);
    }

    @Override // com.alibaba.fastjson.parser.deserializer.FieldDeserializer
    public int getFastMatchToken() {
        return 2;
    }

    @Override // com.alibaba.fastjson.parser.deserializer.FieldDeserializer
    public void parseField(DefaultJSONParser defaultJSONParser, Object obj, Type type, Map<String, Object> map) {
        Integer numCastToInt;
        JSONLexer lexer = defaultJSONParser.getLexer();
        if (lexer.token() == 2) {
            int iIntValue = lexer.intValue();
            lexer.nextToken(16);
            if (obj == null) {
                map.put(this.fieldInfo.getName(), Integer.valueOf(iIntValue));
                return;
            } else {
                setValue(obj, iIntValue);
                return;
            }
        }
        if (lexer.token() == 8) {
            numCastToInt = null;
            lexer.nextToken(16);
        } else {
            numCastToInt = TypeUtils.castToInt(defaultJSONParser.parse());
        }
        if (numCastToInt == null && getFieldClass() == Integer.TYPE) {
            return;
        }
        if (obj == null) {
            map.put(this.fieldInfo.getName(), numCastToInt);
        } else {
            setValue(obj, numCastToInt);
        }
    }
}
