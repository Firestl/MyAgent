package com.github.faucamp.simplertmp.packets;

import com.github.faucamp.simplertmp.packets.RtmpHeader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import supwisdom.f10;
import supwisdom.j00;
import supwisdom.u00;

/* JADX INFO: loaded from: classes.dex */
public class UserControl extends f10 {
    public Type b;
    public int[] c;

    public enum Type {
        STREAM_BEGIN(0),
        STREAM_EOF(1),
        STREAM_DRY(2),
        SET_BUFFER_LENGTH(3),
        STREAM_IS_RECORDED(4),
        PING_REQUEST(6),
        PONG_REPLY(7),
        BUFFER_EMPTY(31),
        BUFFER_READY(32);

        public static final Map<Integer, Type> quickLookupMap = new HashMap();
        public int intValue;

        static {
            for (Type type : values()) {
                quickLookupMap.put(Integer.valueOf(type.getIntValue()), type);
            }
        }

        Type(int i) {
            this.intValue = i;
        }

        public int getIntValue() {
            return this.intValue;
        }

        public static Type valueOf(int i) {
            return quickLookupMap.get(Integer.valueOf(i));
        }
    }

    public UserControl(RtmpHeader rtmpHeader) {
        super(rtmpHeader);
    }

    public void a(int i) {
        if (this.b == Type.SET_BUFFER_LENGTH) {
            throw new IllegalStateException("SET_BUFFER_LENGTH requires two event data values; use setEventData(int, int) instead");
        }
        this.c = new int[]{i};
    }

    @Override // supwisdom.f10
    public byte[] a() {
        return null;
    }

    @Override // supwisdom.f10
    public int c() {
        return 0;
    }

    public int d() {
        return this.c[0];
    }

    public Type e() {
        return this.b;
    }

    public String toString() {
        return "RTMP User Control (type: " + this.b + ", event data: " + this.c + ")";
    }

    public UserControl(u00 u00Var) {
        super(new RtmpHeader(u00Var.a(RtmpHeader.MessageType.USER_CONTROL_MESSAGE) ? RtmpHeader.ChunkType.TYPE_2_RELATIVE_TIMESTAMP_ONLY : RtmpHeader.ChunkType.TYPE_0_FULL, 2, RtmpHeader.MessageType.USER_CONTROL_MESSAGE));
    }

    public UserControl(UserControl userControl, u00 u00Var) {
        this(Type.PONG_REPLY, u00Var);
        this.c = userControl.c;
    }

    public void a(int i, int i2) {
        if (this.b == Type.SET_BUFFER_LENGTH) {
            this.c = new int[]{i, i2};
            return;
        }
        throw new IllegalStateException("User control type " + this.b + " requires only one event data value; use setEventData(int) instead");
    }

    public UserControl(Type type, u00 u00Var) {
        this(u00Var);
        this.b = type;
    }

    @Override // supwisdom.f10
    public void a(InputStream inputStream) throws IOException {
        Type typeValueOf = Type.valueOf(j00.b(inputStream));
        this.b = typeValueOf;
        if (typeValueOf == Type.SET_BUFFER_LENGTH) {
            a(j00.d(inputStream), j00.d(inputStream));
        } else {
            a(j00.d(inputStream));
        }
    }

    @Override // supwisdom.f10
    public void a(OutputStream outputStream) throws IOException {
        j00.a(outputStream, this.b.getIntValue());
        j00.c(outputStream, this.c[0]);
        if (this.b == Type.SET_BUFFER_LENGTH) {
            j00.c(outputStream, this.c[1]);
        }
    }
}
