package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public class NumberDeserializer implements ObjectDeserializer {
    public static final NumberDeserializer instance = new NumberDeserializer();

    /* JADX WARN: Type inference failed for: r8v11, types: [T, java.math.BigDecimal] */
    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        JSONLexer lexer = defaultJSONParser.getLexer();
        if (lexer.token() == 2) {
            if (type == Double.TYPE || type == Double.class) {
                String strNumberString = lexer.numberString();
                lexer.nextToken(16);
                return (T) Double.valueOf(Double.parseDouble(strNumberString));
            }
            long jLongValue = lexer.longValue();
            lexer.nextToken(16);
            return (type == Short.TYPE || type == Short.class) ? (T) Short.valueOf((short) jLongValue) : (type == Byte.TYPE || type == Byte.class) ? (T) Byte.valueOf((byte) jLongValue) : (jLongValue < -2147483648L || jLongValue > 2147483647L) ? (T) Long.valueOf(jLongValue) : (T) Integer.valueOf((int) jLongValue);
        }
        if (lexer.token() != 3) {
            Object obj2 = defaultJSONParser.parse();
            if (obj2 == null) {
                return null;
            }
            return (type == Double.TYPE || type == Double.class) ? (T) TypeUtils.castToDouble(obj2) : (type == Short.TYPE || type == Short.class) ? (T) TypeUtils.castToShort(obj2) : (type == Byte.TYPE || type == Byte.class) ? (T) TypeUtils.castToByte(obj2) : (T) TypeUtils.castToBigDecimal(obj2);
        }
        if (type == Double.TYPE || type == Double.class) {
            String strNumberString2 = lexer.numberString();
            lexer.nextToken(16);
            return (T) Double.valueOf(Double.parseDouble(strNumberString2));
        }
        ?? r8 = (T) lexer.decimalValue();
        lexer.nextToken(16);
        return (type == Short.TYPE || type == Short.class) ? (T) Short.valueOf(r8.shortValue()) : (type == Byte.TYPE || type == Byte.class) ? (T) Byte.valueOf(r8.byteValue()) : r8;
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 2;
    }
}
