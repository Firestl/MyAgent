package com.alibaba.fastjson.serializer;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: loaded from: classes.dex */
public class SerialContext {
    public int features;
    public final Object fieldName;
    public final Object object;
    public final SerialContext parent;

    public SerialContext(SerialContext serialContext, Object obj, Object obj2, int i) {
        this.parent = serialContext;
        this.object = obj;
        this.fieldName = obj2;
        this.features = i;
    }

    public Object getFieldName() {
        return this.fieldName;
    }

    public Object getObject() {
        return this.object;
    }

    public SerialContext getParent() {
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

    public boolean isEnabled(SerializerFeature serializerFeature) {
        return SerializerFeature.isEnabled(this.features, serializerFeature);
    }

    public String toString() {
        return getPath();
    }
}
