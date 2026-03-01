package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public class FloatCodec implements ObjectSerializer, ObjectDeserializer {
    public static FloatCodec instance = new FloatCodec();

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return (T) deserialze(defaultJSONParser);
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 2;
    }

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter writer = jSONSerializer.getWriter();
        if (obj == null) {
            if (jSONSerializer.isEnabled(SerializerFeature.WriteNullNumberAsZero)) {
                writer.write('0');
                return;
            } else {
                writer.writeNull();
                return;
            }
        }
        float fFloatValue = ((Float) obj).floatValue();
        if (Float.isNaN(fFloatValue)) {
            writer.writeNull();
            return;
        }
        if (Float.isInfinite(fFloatValue)) {
            writer.writeNull();
            return;
        }
        String string = Float.toString(fFloatValue);
        if (string.endsWith(".0")) {
            string = string.substring(0, string.length() - 2);
        }
        writer.write(string);
        if (jSONSerializer.isEnabled(SerializerFeature.WriteClassName)) {
            writer.write('F');
        }
    }

    public static <T> T deserialze(DefaultJSONParser defaultJSONParser) {
        JSONLexer lexer = defaultJSONParser.getLexer();
        if (lexer.token() == 2) {
            String strNumberString = lexer.numberString();
            lexer.nextToken(16);
            return (T) Float.valueOf(Float.parseFloat(strNumberString));
        }
        if (lexer.token() == 3) {
            float fFloatValue = lexer.floatValue();
            lexer.nextToken(16);
            return (T) Float.valueOf(fFloatValue);
        }
        Object obj = defaultJSONParser.parse();
        if (obj == null) {
            return null;
        }
        return (T) TypeUtils.castToFloat(obj);
    }
}
