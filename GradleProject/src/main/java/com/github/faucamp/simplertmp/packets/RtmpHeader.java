package com.github.faucamp.simplertmp.packets;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.math.ec.rfc7748.X25519Field;
import supwisdom.j00;
import supwisdom.u00;
import supwisdom.x00;

/* JADX INFO: loaded from: classes.dex */
public class RtmpHeader {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ChunkType f2256a;
    public int b;
    public int c;
    public int d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2257e;
    public MessageType f;
    public int g;
    public int h;

    public enum ChunkType {
        TYPE_0_FULL(0),
        TYPE_1_RELATIVE_LARGE(1),
        TYPE_2_RELATIVE_TIMESTAMP_ONLY(2),
        TYPE_3_RELATIVE_SINGLE_BYTE(3);

        public static final Map<Byte, ChunkType> quickLookupMap = new HashMap();
        public byte value;

        static {
            for (ChunkType chunkType : values()) {
                quickLookupMap.put(Byte.valueOf(chunkType.getValue()), chunkType);
            }
        }

        ChunkType(int i) {
            this.value = (byte) i;
        }

        public byte getValue() {
            return this.value;
        }

        public static ChunkType valueOf(byte b) {
            if (quickLookupMap.containsKey(Byte.valueOf(b))) {
                return quickLookupMap.get(Byte.valueOf(b));
            }
            throw new IllegalArgumentException("Unknown chunk header type byte: " + j00.a(b));
        }
    }

    public enum MessageType {
        SET_CHUNK_SIZE(1),
        ABORT(2),
        ACKNOWLEDGEMENT(3),
        USER_CONTROL_MESSAGE(4),
        WINDOW_ACKNOWLEDGEMENT_SIZE(5),
        SET_PEER_BANDWIDTH(6),
        AUDIO(8),
        VIDEO(9),
        DATA_AMF3(15),
        SHARED_OBJECT_AMF3(16),
        COMMAND_AMF3(17),
        DATA_AMF0(18),
        COMMAND_AMF0(20),
        SHARED_OBJECT_AMF0(19),
        AGGREGATE_MESSAGE(22);

        public static final Map<Byte, MessageType> quickLookupMap = new HashMap();
        public byte value;

        static {
            for (MessageType messageType : values()) {
                quickLookupMap.put(Byte.valueOf(messageType.getValue()), messageType);
            }
        }

        MessageType(int i) {
            this.value = (byte) i;
        }

        public byte getValue() {
            return this.value;
        }

        public static MessageType valueOf(byte b) {
            if (quickLookupMap.containsKey(Byte.valueOf(b))) {
                return quickLookupMap.get(Byte.valueOf(b));
            }
            throw new IllegalArgumentException("Unknown message type byte: " + j00.a(b));
        }
    }

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f2258a;

        static {
            int[] iArr = new int[ChunkType.values().length];
            f2258a = iArr;
            try {
                iArr[ChunkType.TYPE_0_FULL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2258a[ChunkType.TYPE_1_RELATIVE_LARGE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2258a[ChunkType.TYPE_2_RELATIVE_TIMESTAMP_ONLY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2258a[ChunkType.TYPE_3_RELATIVE_SINGLE_BYTE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public RtmpHeader() {
    }

    public static RtmpHeader b(InputStream inputStream, x00 x00Var) throws IOException {
        RtmpHeader rtmpHeader = new RtmpHeader();
        rtmpHeader.a(inputStream, x00Var);
        return rtmpHeader;
    }

    public final void a(InputStream inputStream, x00 x00Var) throws IOException {
        int iD;
        int i = inputStream.read();
        if (i == -1) {
            throw new EOFException("Unexpected EOF while reading RTMP packet basic header");
        }
        byte b = (byte) i;
        a(b);
        int i2 = a.f2258a[this.f2256a.ordinal()];
        int i3 = X25519Field.M24;
        if (i2 == 1) {
            this.c = j00.c(inputStream);
            this.d = 0;
            this.f2257e = j00.c(inputStream);
            this.f = MessageType.valueOf((byte) inputStream.read());
            byte[] bArr = new byte[4];
            j00.a(inputStream, bArr);
            this.g = j00.b(bArr);
            iD = this.c >= 16777215 ? j00.d(inputStream) : 0;
            this.h = iD;
            if (iD != 0) {
                this.c = iD;
                return;
            }
            return;
        }
        if (i2 == 2) {
            this.d = j00.c(inputStream);
            this.f2257e = j00.c(inputStream);
            this.f = MessageType.valueOf((byte) inputStream.read());
            this.h = this.d >= 16777215 ? j00.d(inputStream) : 0;
            RtmpHeader rtmpHeaderF = x00Var.a(this.b).f();
            if (rtmpHeaderF != null) {
                this.g = rtmpHeaderF.g;
                int i4 = this.h;
                if (i4 == 0) {
                    i4 = this.d + rtmpHeaderF.c;
                }
                this.c = i4;
                return;
            }
            this.g = 0;
            int i5 = this.h;
            if (i5 == 0) {
                i5 = this.d;
            }
            this.c = i5;
            return;
        }
        if (i2 == 3) {
            int iC = j00.c(inputStream);
            this.d = iC;
            this.h = iC >= 16777215 ? j00.d(inputStream) : 0;
            RtmpHeader rtmpHeaderF2 = x00Var.a(this.b).f();
            this.f2257e = rtmpHeaderF2.f2257e;
            this.f = rtmpHeaderF2.f;
            this.g = rtmpHeaderF2.g;
            int i6 = this.h;
            if (i6 == 0) {
                i6 = this.d + rtmpHeaderF2.c;
            }
            this.c = i6;
            return;
        }
        if (i2 != 4) {
            throw new IOException("Invalid chunk type; basic header byte was: " + j00.a(b));
        }
        RtmpHeader rtmpHeaderF3 = x00Var.a(this.b).f();
        iD = rtmpHeaderF3.d >= 16777215 ? j00.d(inputStream) : 0;
        this.h = iD;
        if (iD == 0) {
            i3 = rtmpHeaderF3.d;
        }
        this.d = i3;
        this.f2257e = rtmpHeaderF3.f2257e;
        this.f = rtmpHeaderF3.f;
        this.g = rtmpHeaderF3.g;
        int i7 = this.h;
        if (i7 == 0) {
            i7 = rtmpHeaderF3.c + i3;
        }
        this.c = i7;
    }

    public MessageType c() {
        return this.f;
    }

    public int d() {
        return this.f2257e;
    }

    public void c(int i) {
        this.g = i;
    }

    public void d(int i) {
        this.f2257e = i;
    }

    public RtmpHeader(ChunkType chunkType, int i, MessageType messageType) {
        this.f2256a = chunkType;
        this.b = i;
        this.f = messageType;
    }

    public int b() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }

    public void a(OutputStream outputStream, ChunkType chunkType, u00 u00Var) throws IOException {
        outputStream.write(((byte) (chunkType.getValue() << 6)) | this.b);
        int i = a.f2258a[chunkType.ordinal()];
        if (i == 1) {
            u00Var.e();
            int i2 = this.c;
            if (i2 >= 16777215) {
                i2 = X25519Field.M24;
            }
            j00.b(outputStream, i2);
            j00.b(outputStream, this.f2257e);
            outputStream.write(this.f.getValue());
            j00.d(outputStream, this.g);
            int i3 = this.c;
            if (i3 >= 16777215) {
                this.h = i3;
                j00.c(outputStream, i3);
                return;
            }
            return;
        }
        if (i == 2) {
            this.d = (int) u00Var.e();
            int iA = u00Var.b().a();
            int i4 = this.d;
            int i5 = iA + i4;
            this.c = i5;
            if (i5 >= 16777215) {
                i4 = X25519Field.M24;
            }
            j00.b(outputStream, i4);
            j00.b(outputStream, this.f2257e);
            outputStream.write(this.f.getValue());
            int i6 = this.c;
            if (i6 >= 16777215) {
                this.h = i6;
                j00.c(outputStream, i6);
                return;
            }
            return;
        }
        if (i != 3) {
            if (i == 4) {
                int i7 = this.h;
                if (i7 > 0) {
                    j00.c(outputStream, i7);
                    return;
                }
                return;
            }
            throw new IOException("Invalid chunk type: " + chunkType);
        }
        this.d = (int) u00Var.e();
        int iA2 = u00Var.b().a();
        int i8 = this.d;
        int i9 = iA2 + i8;
        this.c = i9;
        if (i9 >= 16777215) {
            i8 = X25519Field.M24;
        }
        j00.b(outputStream, i8);
        int i10 = this.c;
        if (i10 >= 16777215) {
            this.h = i10;
            j00.c(outputStream, i10);
        }
    }

    public final void a(byte b) {
        this.f2256a = ChunkType.valueOf((byte) ((b & 255) >>> 6));
        this.b = b & 63;
    }

    public int a() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }
}
