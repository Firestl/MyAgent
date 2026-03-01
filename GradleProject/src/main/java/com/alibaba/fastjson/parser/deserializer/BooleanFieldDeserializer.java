package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Type;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class BooleanFieldDeserializer extends FieldDeserializer {
    public BooleanFieldDeserializer(ParserConfig parserConfig, Class<?> cls, FieldInfo fieldInfo) {
        super(cls, fieldInfo);
    }

    @Override // com.alibaba.fastjson.parser.deserializer.FieldDeserializer
    public int getFastMatchToken() {
        return 6;
    }

    @Override // com.alibaba.fastjson.parser.deserializer.FieldDeserializer
    public void parseField(DefaultJSONParser defaultJSONParser, Object obj, Type type, Map<String, Object> map) {
        JSONLexer lexer = defaultJSONParser.getLexer();
        if (lexer.token() == 6) {
            lexer.nextToken(16);
            if (obj == null) {
                map.put(this.fieldInfo.getName(), Boolean.TRUE);
                return;
            } else {
                setValue(obj, true);
                return;
            }
        }
        if (lexer.token() == 2) {
            int iIntValue = lexer.intValue();
            lexer.nextToken(16);
            boolean z = iIntValue == 1;
            if (obj == null) {
                map.put(this.fieldInfo.getName(), Boolean.valueOf(z));
                return;
            } else {
                setValue(obj, z);
                return;
            }
        }
        if (lexer.token() == 8) {
            lexer.nextToken(16);
            if (getFieldClass() == Boolean.TYPE || obj == null) {
                return;
            }
            setValue(obj, (String) null);
            return;
        }
        if (lexer.token() == 7) {
            lexer.nextToken(16);
            if (obj == null) {
                map.put(this.fieldInfo.getName(), Boolean.FALSE);
                return;
            } else {
                setValue(obj, false);
                return;
            }
        }
        Boolean boolCastToBoolean = TypeUtils.castToBoolean(defaultJSONParser.parse());
        if (boolCastToBoolean == null && getFieldClass() == Boolean.TYPE) {
            return;
        }
        if (obj == null) {
            map.put(this.fieldInfo.getName(), boolCastToBoolean);
        } else {
            setValue(obj, boolCastToBoolean);
        }
    }
}
