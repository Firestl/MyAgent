package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONScanner;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractDateDeserializer implements ObjectDeserializer {
    public abstract <T> T cast(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2);

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        Object obj2;
        JSONLexer lexer = defaultJSONParser.getLexer();
        if (lexer.token() == 2) {
            Long lValueOf = Long.valueOf(lexer.longValue());
            lexer.nextToken(16);
            obj2 = lValueOf;
        } else if (lexer.token() == 4) {
            String strStringVal = lexer.stringVal();
            lexer.nextToken(16);
            obj2 = strStringVal;
            if (lexer.isEnabled(Feature.AllowISO8601DateFormat)) {
                JSONScanner jSONScanner = new JSONScanner(strStringVal);
                Object time = strStringVal;
                if (jSONScanner.scanISO8601DateIfMatch()) {
                    time = jSONScanner.getCalendar().getTime();
                }
                jSONScanner.close();
                obj2 = time;
            }
        } else if (lexer.token() == 8) {
            lexer.nextToken();
            obj2 = null;
        } else if (lexer.token() == 12) {
            lexer.nextToken();
            if (lexer.token() != 4) {
                throw new JSONException("syntax error");
            }
            if (JSON.DEFAULT_TYPE_KEY.equals(lexer.stringVal())) {
                lexer.nextToken();
                defaultJSONParser.accept(17);
                Class<?> clsLoadClass = TypeUtils.loadClass(lexer.stringVal());
                if (clsLoadClass != null) {
                    type = clsLoadClass;
                }
                defaultJSONParser.accept(4);
                defaultJSONParser.accept(16);
            }
            lexer.nextTokenWithColon(2);
            if (lexer.token() != 2) {
                throw new JSONException("syntax error : " + lexer.tokenName());
            }
            long jLongValue = lexer.longValue();
            lexer.nextToken();
            Long lValueOf2 = Long.valueOf(jLongValue);
            defaultJSONParser.accept(13);
            obj2 = lValueOf2;
        } else if (defaultJSONParser.getResolveStatus() == 2) {
            defaultJSONParser.setResolveStatus(0);
            defaultJSONParser.accept(16);
            if (lexer.token() != 4) {
                throw new JSONException("syntax error");
            }
            if (!"val".equals(lexer.stringVal())) {
                throw new JSONException("syntax error");
            }
            lexer.nextToken();
            defaultJSONParser.accept(17);
            Object obj3 = defaultJSONParser.parse();
            defaultJSONParser.accept(13);
            obj2 = obj3;
        } else {
            obj2 = defaultJSONParser.parse();
        }
        return (T) cast(defaultJSONParser, type, obj, obj2);
    }
}
