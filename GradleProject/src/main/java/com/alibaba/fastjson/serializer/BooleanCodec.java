package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import io.dcloud.common.constant.AbsoluteConst;
import java.io.IOException;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public class BooleanCodec implements ObjectSerializer, ObjectDeserializer {
    public static final BooleanCodec instance = new BooleanCodec();

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        JSONLexer lexer = defaultJSONParser.getLexer();
        if (lexer.token() == 6) {
            lexer.nextToken(16);
            return (T) Boolean.TRUE;
        }
        if (lexer.token() == 7) {
            lexer.nextToken(16);
            return (T) Boolean.FALSE;
        }
        if (lexer.token() == 2) {
            int iIntValue = lexer.intValue();
            lexer.nextToken(16);
            return iIntValue == 1 ? (T) Boolean.TRUE : (T) Boolean.FALSE;
        }
        Object obj2 = defaultJSONParser.parse();
        if (obj2 == null) {
            return null;
        }
        return (T) TypeUtils.castToBoolean(obj2);
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 6;
    }

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter writer = jSONSerializer.getWriter();
        Boolean bool = (Boolean) obj;
        if (bool == null) {
            if (writer.isEnabled(SerializerFeature.WriteNullBooleanAsFalse)) {
                writer.write(AbsoluteConst.FALSE);
                return;
            } else {
                writer.writeNull();
                return;
            }
        }
        if (bool.booleanValue()) {
            writer.write("true");
        } else {
            writer.write(AbsoluteConst.FALSE);
        }
    }
}
