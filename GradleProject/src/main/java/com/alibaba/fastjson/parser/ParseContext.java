package com.alibaba.fastjson.parser;

import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public class ParseContext {
    public final Object fieldName;
    public Object object;
    public final ParseContext parent;
    public Type type;

    public ParseContext(ParseContext parseContext, Object obj, Object obj2) {
        this.parent = parseContext;
        this.object = obj;
        this.fieldName = obj2;
    }

    public Object getObject() {
        return this.object;
    }

    public ParseContext getParentContext() {
        return this.parent;
    }

    public String getPath() {
        if (this.parent == null) {
            return Operators.DOLLAR_STR;
        }
        if (!(this.fieldName instanceof Integer)) {
            return this.parent.getPath() + Operators.DOT_STR + this.fieldName;
        }
        return this.parent.getPath() + Operators.ARRAY_START_STR + this.fieldName + Operators.ARRAY_END_STR;
    }

    public Type getType() {
        return this.type;
    }

    public void setObject(Object obj) {
        this.object = obj;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String toString() {
        return getPath();
    }
}
