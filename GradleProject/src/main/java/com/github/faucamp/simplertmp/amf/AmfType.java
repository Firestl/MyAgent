package com.github.faucamp.simplertmp.amf;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public enum AmfType {
    NUMBER(0),
    BOOLEAN(1),
    STRING(2),
    OBJECT(3),
    NULL(5),
    UNDEFINED(6),
    MAP(8),
    ARRAY(10);

    public static final Map<Byte, AmfType> quickLookupMap = new HashMap();
    public byte value;

    static {
        for (AmfType amfType : values()) {
            quickLookupMap.put(Byte.valueOf(amfType.getValue()), amfType);
        }
    }

    AmfType(int i) {
        this.value = (byte) i;
    }

    public byte getValue() {
        return this.value;
    }

    public static AmfType valueOf(byte b) {
        return quickLookupMap.get(Byte.valueOf(b));
    }
}
