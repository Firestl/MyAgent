package com.alibaba.fastjson;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.taobao.weex.el.parse.Operators;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

/* JADX INFO: loaded from: classes.dex */
public class JSONWriter implements Closeable, Flushable {
    public JSONStreamContext context;
    public JSONSerializer serializer;
    public SerializeWriter writer;

    public JSONWriter(Writer writer) {
        SerializeWriter serializeWriter = new SerializeWriter(writer);
        this.writer = serializeWriter;
        this.serializer = new JSONSerializer(serializeWriter);
    }

    private void afterWriter() {
        int i;
        JSONStreamContext jSONStreamContext = this.context;
        if (jSONStreamContext == null) {
            return;
        }
        switch (jSONStreamContext.getState()) {
            case 1001:
            case 1003:
                i = 1002;
                break;
            case 1002:
                i = 1003;
                break;
            case 1004:
                i = 1005;
                break;
            default:
                i = -1;
                break;
        }
        if (i != -1) {
            this.context.setState(i);
        }
    }

    private void beforeWrite() {
        JSONStreamContext jSONStreamContext = this.context;
        if (jSONStreamContext == null) {
            return;
        }
        int state = jSONStreamContext.getState();
        if (state == 1002) {
            this.writer.write(Operators.CONDITION_IF_MIDDLE);
        } else if (state == 1003) {
            this.writer.write(',');
        } else {
            if (state != 1005) {
                return;
            }
            this.writer.write(',');
        }
    }

    private void beginStructure() {
        int state = this.context.getState();
        switch (state) {
            case 1001:
            case 1004:
                return;
            case 1002:
                this.writer.write(Operators.CONDITION_IF_MIDDLE);
                return;
            case 1003:
            default:
                throw new JSONException("illegal state : " + state);
            case 1005:
                this.writer.write(',');
                return;
        }
    }

    private void endStructure() {
        JSONStreamContext parent = this.context.getParent();
        this.context = parent;
        if (parent == null) {
            return;
        }
        int state = parent.getState();
        int i = state != 1001 ? state != 1002 ? state != 1004 ? -1 : 1005 : 1003 : 1002;
        if (i != -1) {
            this.context.setState(i);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.writer.close();
    }

    public void config(SerializerFeature serializerFeature, boolean z) {
        this.writer.config(serializerFeature, z);
    }

    public void endArray() {
        this.writer.write(Operators.ARRAY_END);
        endStructure();
    }

    public void endObject() {
        this.writer.write(Operators.BLOCK_END);
        endStructure();
    }

    @Override // java.io.Flushable
    public void flush() throws IOException {
        this.writer.flush();
    }

    public void startArray() {
        if (this.context != null) {
            beginStructure();
        }
        this.context = new JSONStreamContext(this.context, 1004);
        this.writer.write(Operators.ARRAY_START);
    }

    public void startObject() {
        if (this.context != null) {
            beginStructure();
        }
        this.context = new JSONStreamContext(this.context, 1001);
        this.writer.write(Operators.BLOCK_START);
    }

    public void writeKey(String str) {
        writeObject(str);
    }

    public void writeObject(String str) {
        beforeWrite();
        this.serializer.write(str);
        afterWriter();
    }

    public void writeValue(Object obj) {
        writeObject(obj);
    }

    public void writeObject(Object obj) {
        beforeWrite();
        this.serializer.write(obj);
        afterWriter();
    }
}
