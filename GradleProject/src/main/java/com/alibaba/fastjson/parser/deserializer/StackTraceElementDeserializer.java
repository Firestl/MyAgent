package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONToken;
import com.lzy.okgo.model.Progress;
import com.vivo.push.PushClientConstants;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public class StackTraceElementDeserializer implements ObjectDeserializer {
    public static final StackTraceElementDeserializer instance = new StackTraceElementDeserializer();

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        JSONLexer lexer = defaultJSONParser.getLexer();
        if (lexer.token() == 8) {
            lexer.nextToken();
            return null;
        }
        if (lexer.token() != 12 && lexer.token() != 16) {
            throw new JSONException("syntax error: " + JSONToken.name(lexer.token()));
        }
        String strStringVal = null;
        String strStringVal2 = null;
        String strStringVal3 = null;
        int iIntValue = 0;
        while (true) {
            String strScanSymbol = lexer.scanSymbol(defaultJSONParser.getSymbolTable());
            if (strScanSymbol == null) {
                if (lexer.token() == 13) {
                    lexer.nextToken(16);
                    break;
                }
                if (lexer.token() != 16 || !lexer.isEnabled(Feature.AllowArbitraryCommas)) {
                }
            }
            lexer.nextTokenWithColon(4);
            if (PushClientConstants.TAG_CLASS_NAME.equals(strScanSymbol)) {
                if (lexer.token() == 8) {
                    strStringVal = null;
                } else {
                    if (lexer.token() != 4) {
                        throw new JSONException("syntax error");
                    }
                    strStringVal = lexer.stringVal();
                }
            } else if ("methodName".equals(strScanSymbol)) {
                if (lexer.token() == 8) {
                    strStringVal2 = null;
                } else {
                    if (lexer.token() != 4) {
                        throw new JSONException("syntax error");
                    }
                    strStringVal2 = lexer.stringVal();
                }
            } else if (Progress.FILE_NAME.equals(strScanSymbol)) {
                if (lexer.token() == 8) {
                    strStringVal3 = null;
                } else {
                    if (lexer.token() != 4) {
                        throw new JSONException("syntax error");
                    }
                    strStringVal3 = lexer.stringVal();
                }
            } else if ("lineNumber".equals(strScanSymbol)) {
                if (lexer.token() == 8) {
                    iIntValue = 0;
                } else {
                    if (lexer.token() != 2) {
                        throw new JSONException("syntax error");
                    }
                    iIntValue = lexer.intValue();
                }
            } else if ("nativeMethod".equals(strScanSymbol)) {
                if (lexer.token() != 8 && lexer.token() != 6 && lexer.token() != 7) {
                    throw new JSONException("syntax error");
                }
                lexer.nextToken(16);
            } else {
                if (strScanSymbol != JSON.DEFAULT_TYPE_KEY) {
                    throw new JSONException("syntax error : " + strScanSymbol);
                }
                if (lexer.token() == 4) {
                    String strStringVal4 = lexer.stringVal();
                    if (!strStringVal4.equals("java.lang.StackTraceElement")) {
                        throw new JSONException("syntax error : " + strStringVal4);
                    }
                } else if (lexer.token() != 8) {
                    throw new JSONException("syntax error");
                }
            }
            if (lexer.token() == 13) {
                lexer.nextToken(16);
                break;
            }
        }
        return (T) new StackTraceElement(strStringVal, strStringVal2, strStringVal3, iIntValue);
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 12;
    }
}
