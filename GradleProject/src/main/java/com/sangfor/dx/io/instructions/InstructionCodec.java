package com.sangfor.dx.io.instructions;

import com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser;
import com.sangfor.dex.DexException;
import com.sangfor.dx.io.IndexType;
import java.util.Arrays;
import supwisdom.e31;
import supwisdom.h31;
import supwisdom.i31;
import supwisdom.j31;
import supwisdom.k31;
import supwisdom.l31;
import supwisdom.m31;
import supwisdom.m61;
import supwisdom.n31;
import supwisdom.o31;
import supwisdom.p31;
import supwisdom.q31;
import supwisdom.r31;
import supwisdom.s31;
import supwisdom.t31;
import supwisdom.u31;
import supwisdom.v31;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class InstructionCodec {
    public static final /* synthetic */ InstructionCodec[] $VALUES;
    public static final InstructionCodec FORMAT_00X;
    public static final InstructionCodec FORMAT_10T;
    public static final InstructionCodec FORMAT_10X;
    public static final InstructionCodec FORMAT_11N;
    public static final InstructionCodec FORMAT_11X;
    public static final InstructionCodec FORMAT_12X;
    public static final InstructionCodec FORMAT_20BC;
    public static final InstructionCodec FORMAT_20T;
    public static final InstructionCodec FORMAT_21C;
    public static final InstructionCodec FORMAT_21H;
    public static final InstructionCodec FORMAT_21S;
    public static final InstructionCodec FORMAT_21T;
    public static final InstructionCodec FORMAT_22B;
    public static final InstructionCodec FORMAT_22C;
    public static final InstructionCodec FORMAT_22CS;
    public static final InstructionCodec FORMAT_22S;
    public static final InstructionCodec FORMAT_22T;
    public static final InstructionCodec FORMAT_22X;
    public static final InstructionCodec FORMAT_23X;
    public static final InstructionCodec FORMAT_30T;
    public static final InstructionCodec FORMAT_31C;
    public static final InstructionCodec FORMAT_31I;
    public static final InstructionCodec FORMAT_31T;
    public static final InstructionCodec FORMAT_32X;
    public static final InstructionCodec FORMAT_35C;
    public static final InstructionCodec FORMAT_35MI;
    public static final InstructionCodec FORMAT_35MS;
    public static final InstructionCodec FORMAT_3RC;
    public static final InstructionCodec FORMAT_3RMI;
    public static final InstructionCodec FORMAT_3RMS;
    public static final InstructionCodec FORMAT_45CC;
    public static final InstructionCodec FORMAT_4RCC;
    public static final InstructionCodec FORMAT_51L;
    public static final InstructionCodec FORMAT_FILL_ARRAY_DATA_PAYLOAD;
    public static final InstructionCodec FORMAT_PACKED_SWITCH_PAYLOAD;
    public static final InstructionCodec FORMAT_SPARSE_SWITCH_PAYLOAD;

    /* JADX INFO: compiled from: Proguard */
    public enum k extends InstructionCodec {
        public k(String str, int i) {
            super(str, i, null);
        }

        @Override // com.sangfor.dx.io.instructions.InstructionCodec
        public j31 decode(int i, h31 h31Var) {
            return new v31(this, i, 0, null, 0, 0L);
        }

        @Override // com.sangfor.dx.io.instructions.InstructionCodec
        public void encode(j31 j31Var, i31 i31Var) {
            i31Var.a(j31Var.q());
        }
    }

    static {
        k kVar = new k("FORMAT_00X", 0);
        FORMAT_00X = kVar;
        InstructionCodec instructionCodec = new InstructionCodec("FORMAT_10X", 1) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.v
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                return new v31(this, InstructionCodec.byte0(i2), 0, null, 0, InstructionCodec.byte1(i2));
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                i31Var.a(j31Var.q());
            }
        };
        FORMAT_10X = instructionCodec;
        InstructionCodec instructionCodec2 = new InstructionCodec("FORMAT_12X", 2) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.d0
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                return new u31(this, InstructionCodec.byte0(i2), 0, null, 0, 0L, InstructionCodec.nibble2(i2), InstructionCodec.nibble3(i2));
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                i31Var.a(InstructionCodec.codeUnit(j31Var.q(), InstructionCodec.makeByte(j31Var.a(), j31Var.c())));
            }
        };
        FORMAT_12X = instructionCodec2;
        InstructionCodec instructionCodec3 = new InstructionCodec("FORMAT_11N", 3) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.e0
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                return new p31(this, InstructionCodec.byte0(i2), 0, null, 0, (InstructionCodec.nibble3(i2) << 28) >> 28, InstructionCodec.nibble2(i2));
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                i31Var.a(InstructionCodec.codeUnit(j31Var.q(), InstructionCodec.makeByte(j31Var.a(), j31Var.n())));
            }
        };
        FORMAT_11N = instructionCodec3;
        InstructionCodec instructionCodec4 = new InstructionCodec("FORMAT_11X", 4) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.f0
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                return new p31(this, InstructionCodec.byte0(i2), 0, null, 0, 0L, InstructionCodec.byte1(i2));
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                i31Var.a(InstructionCodec.codeUnit(j31Var.p(), j31Var.a()));
            }
        };
        FORMAT_11X = instructionCodec4;
        InstructionCodec instructionCodec5 = new InstructionCodec("FORMAT_10T", 5) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.g0
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                return new v31(this, InstructionCodec.byte0(i2), 0, null, (h31Var.b() - 1) + ((byte) InstructionCodec.byte1(i2)), 0L);
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                i31Var.a(InstructionCodec.codeUnit(j31Var.p(), j31Var.b(i31Var.b())));
            }
        };
        FORMAT_10T = instructionCodec5;
        InstructionCodec instructionCodec6 = new InstructionCodec("FORMAT_20T", 6) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.h0
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                return new v31(this, InstructionCodec.byte0(i2), 0, null, (h31Var.b() - 1) + ((short) h31Var.read()), InstructionCodec.byte1(i2));
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                i31Var.a(j31Var.q(), j31Var.c(i31Var.b()));
            }
        };
        FORMAT_20T = instructionCodec6;
        InstructionCodec instructionCodec7 = new InstructionCodec("FORMAT_20BC", 7) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.i0
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                return new v31(this, InstructionCodec.byte0(i2), h31Var.read(), IndexType.VARIES, 0, InstructionCodec.byte1(i2));
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                i31Var.a(InstructionCodec.codeUnit(j31Var.p(), j31Var.l()), j31Var.j());
            }
        };
        FORMAT_20BC = instructionCodec7;
        InstructionCodec instructionCodec8 = new InstructionCodec("FORMAT_22X", 8) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.j0
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                return new u31(this, InstructionCodec.byte0(i2), 0, null, 0, 0L, InstructionCodec.byte1(i2), h31Var.read());
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                i31Var.a(InstructionCodec.codeUnit(j31Var.p(), j31Var.a()), j31Var.d());
            }
        };
        FORMAT_22X = instructionCodec8;
        InstructionCodec instructionCodec9 = new InstructionCodec("FORMAT_21T", 9) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.a
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                return new p31(this, InstructionCodec.byte0(i2), 0, null, (h31Var.b() - 1) + ((short) h31Var.read()), 0L, InstructionCodec.byte1(i2));
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                i31Var.a(InstructionCodec.codeUnit(j31Var.p(), j31Var.a()), j31Var.c(i31Var.b()));
            }
        };
        FORMAT_21T = instructionCodec9;
        InstructionCodec instructionCodec10 = new InstructionCodec("FORMAT_21S", 10) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.b
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                return new p31(this, InstructionCodec.byte0(i2), 0, null, 0, (short) h31Var.read(), InstructionCodec.byte1(i2));
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                i31Var.a(InstructionCodec.codeUnit(j31Var.p(), j31Var.a()), j31Var.o());
            }
        };
        FORMAT_21S = instructionCodec10;
        InstructionCodec instructionCodec11 = new InstructionCodec("FORMAT_21H", 11) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.c
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                int iByte0 = InstructionCodec.byte0(i2);
                return new p31(this, iByte0, 0, null, 0, ((long) ((short) h31Var.read())) << (iByte0 == 21 ? (char) 16 : '0'), InstructionCodec.byte1(i2));
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                int iP = j31Var.p();
                i31Var.a(InstructionCodec.codeUnit(iP, j31Var.a()), (short) (j31Var.k() >> (iP == 21 ? (char) 16 : '0')));
            }
        };
        FORMAT_21H = instructionCodec11;
        InstructionCodec instructionCodec12 = new InstructionCodec("FORMAT_21C", 12) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.d
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                int iByte0 = InstructionCodec.byte0(i2);
                return new p31(this, iByte0, h31Var.read(), e31.b(iByte0), 0, 0L, InstructionCodec.byte1(i2));
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                i31Var.a(InstructionCodec.codeUnit(j31Var.p(), j31Var.a()), j31Var.j());
            }
        };
        FORMAT_21C = instructionCodec12;
        InstructionCodec instructionCodec13 = new InstructionCodec("FORMAT_23X", 13) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.e
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                int iByte0 = InstructionCodec.byte0(i2);
                int iByte1 = InstructionCodec.byte1(i2);
                int i3 = h31Var.read();
                return new t31(this, iByte0, 0, null, 0, 0L, iByte1, InstructionCodec.byte0(i3), InstructionCodec.byte1(i3));
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                i31Var.a(InstructionCodec.codeUnit(j31Var.p(), j31Var.a()), InstructionCodec.codeUnit(j31Var.c(), j31Var.e()));
            }
        };
        FORMAT_23X = instructionCodec13;
        InstructionCodec instructionCodec14 = new InstructionCodec("FORMAT_22B", 14) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.f
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                return new u31(this, InstructionCodec.byte0(i2), 0, null, 0, (byte) InstructionCodec.byte1(r11), InstructionCodec.byte1(i2), InstructionCodec.byte0(h31Var.read()));
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                i31Var.a(InstructionCodec.codeUnit(j31Var.p(), j31Var.a()), InstructionCodec.codeUnit(j31Var.c(), j31Var.l()));
            }
        };
        FORMAT_22B = instructionCodec14;
        InstructionCodec instructionCodec15 = new InstructionCodec("FORMAT_22T", 15) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.g
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                return new u31(this, InstructionCodec.byte0(i2), 0, null, (h31Var.b() - 1) + ((short) h31Var.read()), 0L, InstructionCodec.nibble2(i2), InstructionCodec.nibble3(i2));
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                i31Var.a(InstructionCodec.codeUnit(j31Var.p(), InstructionCodec.makeByte(j31Var.a(), j31Var.c())), j31Var.c(i31Var.b()));
            }
        };
        FORMAT_22T = instructionCodec15;
        InstructionCodec instructionCodec16 = new InstructionCodec("FORMAT_22S", 16) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.h
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                return new u31(this, InstructionCodec.byte0(i2), 0, null, 0, (short) h31Var.read(), InstructionCodec.nibble2(i2), InstructionCodec.nibble3(i2));
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                i31Var.a(InstructionCodec.codeUnit(j31Var.p(), InstructionCodec.makeByte(j31Var.a(), j31Var.c())), j31Var.o());
            }
        };
        FORMAT_22S = instructionCodec16;
        InstructionCodec instructionCodec17 = new InstructionCodec("FORMAT_22C", 17) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.i
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                int iByte0 = InstructionCodec.byte0(i2);
                return new u31(this, iByte0, h31Var.read(), e31.b(iByte0), 0, 0L, InstructionCodec.nibble2(i2), InstructionCodec.nibble3(i2));
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                i31Var.a(InstructionCodec.codeUnit(j31Var.p(), InstructionCodec.makeByte(j31Var.a(), j31Var.c())), j31Var.j());
            }
        };
        FORMAT_22C = instructionCodec17;
        InstructionCodec instructionCodec18 = new InstructionCodec("FORMAT_22CS", 18) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.j
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                return new u31(this, InstructionCodec.byte0(i2), h31Var.read(), IndexType.FIELD_OFFSET, 0, 0L, InstructionCodec.nibble2(i2), InstructionCodec.nibble3(i2));
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                i31Var.a(InstructionCodec.codeUnit(j31Var.p(), InstructionCodec.makeByte(j31Var.a(), j31Var.c())), j31Var.j());
            }
        };
        FORMAT_22CS = instructionCodec18;
        InstructionCodec instructionCodec19 = new InstructionCodec("FORMAT_30T", 19) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.l
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                return new v31(this, InstructionCodec.byte0(i2), 0, null, (h31Var.b() - 1) + h31Var.readInt(), InstructionCodec.byte1(i2));
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                int iA = j31Var.a(i31Var.b());
                i31Var.a(j31Var.q(), InstructionCodec.unit0(iA), InstructionCodec.unit1(iA));
            }
        };
        FORMAT_30T = instructionCodec19;
        InstructionCodec instructionCodec20 = new InstructionCodec("FORMAT_32X", 20) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.m
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                return new u31(this, InstructionCodec.byte0(i2), 0, null, 0, InstructionCodec.byte1(i2), h31Var.read(), h31Var.read());
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                i31Var.a(j31Var.q(), j31Var.b(), j31Var.d());
            }
        };
        FORMAT_32X = instructionCodec20;
        InstructionCodec instructionCodec21 = new InstructionCodec("FORMAT_31I", 21) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.n
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                return new p31(this, InstructionCodec.byte0(i2), 0, null, 0, h31Var.readInt(), InstructionCodec.byte1(i2));
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                int iM = j31Var.m();
                i31Var.a(InstructionCodec.codeUnit(j31Var.p(), j31Var.a()), InstructionCodec.unit0(iM), InstructionCodec.unit1(iM));
            }
        };
        FORMAT_31I = instructionCodec21;
        InstructionCodec instructionCodec22 = new InstructionCodec("FORMAT_31T", 22) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.o
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                int iB = h31Var.b() - 1;
                int iByte0 = InstructionCodec.byte0(i2);
                int iByte1 = InstructionCodec.byte1(i2);
                int i3 = iB + h31Var.readInt();
                if (iByte0 == 43 || iByte0 == 44) {
                    h31Var.a(i3, iB);
                }
                return new p31(this, iByte0, 0, null, i3, 0L, iByte1);
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                int iA = j31Var.a(i31Var.b());
                i31Var.a(InstructionCodec.codeUnit(j31Var.p(), j31Var.a()), InstructionCodec.unit0(iA), InstructionCodec.unit1(iA));
            }
        };
        FORMAT_31T = instructionCodec22;
        InstructionCodec instructionCodec23 = new InstructionCodec("FORMAT_31C", 23) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.p
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                int iByte0 = InstructionCodec.byte0(i2);
                return new p31(this, iByte0, h31Var.readInt(), e31.b(iByte0), 0, 0L, InstructionCodec.byte1(i2));
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                int i2 = j31Var.i();
                i31Var.a(InstructionCodec.codeUnit(j31Var.p(), j31Var.a()), InstructionCodec.unit0(i2), InstructionCodec.unit1(i2));
            }
        };
        FORMAT_31C = instructionCodec23;
        InstructionCodec instructionCodec24 = new InstructionCodec("FORMAT_35C", 24) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.q
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                return InstructionCodec.decodeRegisterList(this, i2, h31Var);
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                InstructionCodec.encodeRegisterList(j31Var, i31Var);
            }
        };
        FORMAT_35C = instructionCodec24;
        InstructionCodec instructionCodec25 = new InstructionCodec("FORMAT_35MS", 25) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.r
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                return InstructionCodec.decodeRegisterList(this, i2, h31Var);
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                InstructionCodec.encodeRegisterList(j31Var, i31Var);
            }
        };
        FORMAT_35MS = instructionCodec25;
        InstructionCodec instructionCodec26 = new InstructionCodec("FORMAT_35MI", 26) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.s
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                return InstructionCodec.decodeRegisterList(this, i2, h31Var);
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                InstructionCodec.encodeRegisterList(j31Var, i31Var);
            }
        };
        FORMAT_35MI = instructionCodec26;
        InstructionCodec instructionCodec27 = new InstructionCodec("FORMAT_3RC", 27) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.t
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                return InstructionCodec.decodeRegisterRange(this, i2, h31Var);
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                InstructionCodec.encodeRegisterRange(j31Var, i31Var);
            }
        };
        FORMAT_3RC = instructionCodec27;
        InstructionCodec instructionCodec28 = new InstructionCodec("FORMAT_3RMS", 28) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.u
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                return InstructionCodec.decodeRegisterRange(this, i2, h31Var);
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                InstructionCodec.encodeRegisterRange(j31Var, i31Var);
            }
        };
        FORMAT_3RMS = instructionCodec28;
        InstructionCodec instructionCodec29 = new InstructionCodec("FORMAT_3RMI", 29) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.w
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                return InstructionCodec.decodeRegisterRange(this, i2, h31Var);
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                InstructionCodec.encodeRegisterRange(j31Var, i31Var);
            }
        };
        FORMAT_3RMI = instructionCodec29;
        InstructionCodec instructionCodec30 = new InstructionCodec("FORMAT_51L", 30) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.x
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                return new p31(this, InstructionCodec.byte0(i2), 0, null, 0, h31Var.readLong(), InstructionCodec.byte1(i2));
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                long jK = j31Var.k();
                i31Var.a(InstructionCodec.codeUnit(j31Var.p(), j31Var.a()), InstructionCodec.unit0(jK), InstructionCodec.unit1(jK), InstructionCodec.unit2(jK), InstructionCodec.unit3(jK));
            }
        };
        FORMAT_51L = instructionCodec30;
        InstructionCodec instructionCodec31 = new InstructionCodec("FORMAT_45CC", 31) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.y
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                int iByte0 = InstructionCodec.byte0(i2);
                if (iByte0 != 250) {
                    throw new UnsupportedOperationException(String.valueOf(iByte0));
                }
                int iNibble2 = InstructionCodec.nibble2(i2);
                int iNibble3 = InstructionCodec.nibble3(i2);
                int i3 = h31Var.read();
                int i4 = h31Var.read();
                int iNibble0 = InstructionCodec.nibble0(i4);
                int iNibble1 = InstructionCodec.nibble1(i4);
                int iNibble22 = InstructionCodec.nibble2(i4);
                int iNibble32 = InstructionCodec.nibble3(i4);
                int i5 = h31Var.read();
                IndexType indexTypeB = e31.b(iByte0);
                if (iNibble3 >= 1 && iNibble3 <= 5) {
                    return new n31(this, iByte0, i3, indexTypeB, i5, Arrays.copyOfRange(new int[]{iNibble0, iNibble1, iNibble22, iNibble32, iNibble2}, 0, iNibble3));
                }
                throw new DexException("bogus registerCount: " + m61.h(iNibble3));
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                n31 n31Var = (n31) j31Var;
                i31Var.a(InstructionCodec.codeUnit(n31Var.p(), InstructionCodec.makeByte(n31Var.u(), n31Var.s())), n31Var.j(), InstructionCodec.codeUnit(n31Var.e(), n31Var.g(), n31Var.h(), n31Var.t()), n31Var.r());
            }
        };
        FORMAT_45CC = instructionCodec31;
        InstructionCodec instructionCodec32 = new InstructionCodec("FORMAT_4RCC", 32) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.z
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                int iByte0 = InstructionCodec.byte0(i2);
                if (iByte0 != 251) {
                    throw new UnsupportedOperationException(String.valueOf(iByte0));
                }
                int iByte1 = InstructionCodec.byte1(i2);
                return new o31(this, iByte0, h31Var.read(), e31.b(iByte0), h31Var.read(), iByte1, h31Var.read());
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                i31Var.a(InstructionCodec.codeUnit(j31Var.p(), j31Var.s()), j31Var.j(), j31Var.f(), j31Var.r());
            }
        };
        FORMAT_4RCC = instructionCodec32;
        InstructionCodec instructionCodec33 = new InstructionCodec("FORMAT_PACKED_SWITCH_PAYLOAD", 33) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.a0
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                int iA = h31Var.a() - 1;
                int i3 = h31Var.read();
                int i4 = h31Var.readInt();
                int[] iArr = new int[i3];
                for (int i5 = 0; i5 < i3; i5++) {
                    iArr[i5] = h31Var.readInt() + iA;
                }
                return new q31(this, i2, i4, iArr);
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                q31 q31Var = (q31) j31Var;
                int[] iArrU = q31Var.u();
                int iA = i31Var.a();
                i31Var.a(q31Var.q());
                i31Var.a(InstructionCodec.asUnsignedUnit(iArrU.length));
                i31Var.writeInt(q31Var.t());
                for (int i2 : iArrU) {
                    i31Var.writeInt(i2 - iA);
                }
            }
        };
        FORMAT_PACKED_SWITCH_PAYLOAD = instructionCodec33;
        InstructionCodec instructionCodec34 = new InstructionCodec("FORMAT_SPARSE_SWITCH_PAYLOAD", 34) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.b0
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                int iA = h31Var.a() - 1;
                int i3 = h31Var.read();
                int[] iArr = new int[i3];
                int[] iArr2 = new int[i3];
                for (int i4 = 0; i4 < i3; i4++) {
                    iArr[i4] = h31Var.readInt();
                }
                for (int i5 = 0; i5 < i3; i5++) {
                    iArr2[i5] = h31Var.readInt() + iA;
                }
                return new s31(this, i2, iArr, iArr2);
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                s31 s31Var = (s31) j31Var;
                int[] iArrT = s31Var.t();
                int[] iArrU = s31Var.u();
                int iA = i31Var.a();
                i31Var.a(s31Var.q());
                i31Var.a(InstructionCodec.asUnsignedUnit(iArrU.length));
                for (int i2 : iArrT) {
                    i31Var.writeInt(i2);
                }
                for (int i3 : iArrU) {
                    i31Var.writeInt(i3 - iA);
                }
            }
        };
        FORMAT_SPARSE_SWITCH_PAYLOAD = instructionCodec34;
        InstructionCodec instructionCodec35 = new InstructionCodec("FORMAT_FILL_ARRAY_DATA_PAYLOAD", 35) { // from class: com.sangfor.dx.io.instructions.InstructionCodec.c0
            {
                k kVar2 = null;
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public j31 decode(int i2, h31 h31Var) {
                int i3 = h31Var.read();
                int i4 = h31Var.readInt();
                int i5 = 0;
                if (i3 == 1) {
                    byte[] bArr = new byte[i4];
                    int i6 = 0;
                    boolean z2 = true;
                    while (i5 < i4) {
                        if (z2) {
                            i6 = h31Var.read();
                        }
                        bArr[i5] = (byte) (i6 & 255);
                        i6 >>= 8;
                        i5++;
                        z2 = !z2;
                    }
                    return new k31((InstructionCodec) this, i2, bArr);
                }
                if (i3 == 2) {
                    short[] sArr = new short[i4];
                    while (i5 < i4) {
                        sArr[i5] = (short) h31Var.read();
                        i5++;
                    }
                    return new k31((InstructionCodec) this, i2, sArr);
                }
                if (i3 == 4) {
                    int[] iArr = new int[i4];
                    while (i5 < i4) {
                        iArr[i5] = h31Var.readInt();
                        i5++;
                    }
                    return new k31((InstructionCodec) this, i2, iArr);
                }
                if (i3 != 8) {
                    throw new DexException("bogus element_width: " + m61.d(i3));
                }
                long[] jArr = new long[i4];
                while (i5 < i4) {
                    jArr[i5] = h31Var.readLong();
                    i5++;
                }
                return new k31(this, i2, jArr);
            }

            @Override // com.sangfor.dx.io.instructions.InstructionCodec
            public void encode(j31 j31Var, i31 i31Var) {
                k31 k31Var = (k31) j31Var;
                short sU = k31Var.u();
                Object objT = k31Var.t();
                i31Var.a(k31Var.q());
                i31Var.a(sU);
                i31Var.writeInt(k31Var.v());
                if (sU == 1) {
                    i31Var.write((byte[]) objT);
                    return;
                }
                if (sU == 2) {
                    i31Var.a((short[]) objT);
                    return;
                }
                if (sU == 4) {
                    i31Var.a((int[]) objT);
                } else {
                    if (sU == 8) {
                        i31Var.a((long[]) objT);
                        return;
                    }
                    throw new DexException("bogus element_width: " + m61.d(sU));
                }
            }
        };
        FORMAT_FILL_ARRAY_DATA_PAYLOAD = instructionCodec35;
        $VALUES = new InstructionCodec[]{kVar, instructionCodec, instructionCodec2, instructionCodec3, instructionCodec4, instructionCodec5, instructionCodec6, instructionCodec7, instructionCodec8, instructionCodec9, instructionCodec10, instructionCodec11, instructionCodec12, instructionCodec13, instructionCodec14, instructionCodec15, instructionCodec16, instructionCodec17, instructionCodec18, instructionCodec19, instructionCodec20, instructionCodec21, instructionCodec22, instructionCodec23, instructionCodec24, instructionCodec25, instructionCodec26, instructionCodec27, instructionCodec28, instructionCodec29, instructionCodec30, instructionCodec31, instructionCodec32, instructionCodec33, instructionCodec34, instructionCodec35};
    }

    public InstructionCodec(String str, int i2) {
    }

    public static short asUnsignedUnit(int i2) {
        if (((-65536) & i2) == 0) {
            return (short) i2;
        }
        throw new IllegalArgumentException("bogus unsigned code unit");
    }

    public static int byte0(int i2) {
        return i2 & 255;
    }

    public static int byte1(int i2) {
        return (i2 >> 8) & 255;
    }

    public static int byte2(int i2) {
        return (i2 >> 16) & 255;
    }

    public static int byte3(int i2) {
        return i2 >>> 24;
    }

    public static short codeUnit(int i2, int i3) {
        if ((i2 & DefaultImageHeaderParser.VP8_HEADER_MASK) != 0) {
            throw new IllegalArgumentException("bogus lowByte");
        }
        if ((i3 & DefaultImageHeaderParser.VP8_HEADER_MASK) == 0) {
            return (short) (i2 | (i3 << 8));
        }
        throw new IllegalArgumentException("bogus highByte");
    }

    public static j31 decodeRegisterList(InstructionCodec instructionCodec, int i2, h31 h31Var) {
        int iByte0 = byte0(i2);
        int iNibble2 = nibble2(i2);
        int iNibble3 = nibble3(i2);
        int i3 = h31Var.read();
        int i4 = h31Var.read();
        int iNibble0 = nibble0(i4);
        int iNibble1 = nibble1(i4);
        int iNibble22 = nibble2(i4);
        int iNibble32 = nibble3(i4);
        IndexType indexTypeB = e31.b(iByte0);
        if (iNibble3 == 0) {
            return new v31(instructionCodec, iByte0, i3, indexTypeB, 0, 0L);
        }
        if (iNibble3 == 1) {
            return new p31(instructionCodec, iByte0, i3, indexTypeB, 0, 0L, iNibble0);
        }
        if (iNibble3 == 2) {
            return new u31(instructionCodec, iByte0, i3, indexTypeB, 0, 0L, iNibble0, iNibble1);
        }
        if (iNibble3 == 3) {
            return new t31(instructionCodec, iByte0, i3, indexTypeB, 0, 0L, iNibble0, iNibble1, iNibble22);
        }
        if (iNibble3 == 4) {
            return new m31(instructionCodec, iByte0, i3, indexTypeB, 0, 0L, iNibble0, iNibble1, iNibble22, iNibble32);
        }
        if (iNibble3 == 5) {
            return new l31(instructionCodec, iByte0, i3, indexTypeB, 0, 0L, iNibble0, iNibble1, iNibble22, iNibble32, iNibble2);
        }
        throw new DexException("bogus registerCount: " + m61.h(iNibble3));
    }

    public static j31 decodeRegisterRange(InstructionCodec instructionCodec, int i2, h31 h31Var) {
        int iByte0 = byte0(i2);
        int iByte1 = byte1(i2);
        return new r31(instructionCodec, iByte0, h31Var.read(), e31.b(iByte0), 0, 0L, h31Var.read(), iByte1);
    }

    public static void encodeRegisterList(j31 j31Var, i31 i31Var) {
        i31Var.a(codeUnit(j31Var.p(), makeByte(j31Var.h(), j31Var.s())), j31Var.j(), codeUnit(j31Var.a(), j31Var.c(), j31Var.e(), j31Var.g()));
    }

    public static void encodeRegisterRange(j31 j31Var, i31 i31Var) {
        i31Var.a(codeUnit(j31Var.p(), j31Var.s()), j31Var.j(), j31Var.b());
    }

    public static int makeByte(int i2, int i3) {
        if ((i2 & (-16)) != 0) {
            throw new IllegalArgumentException("bogus lowNibble");
        }
        if ((i3 & (-16)) == 0) {
            return i2 | (i3 << 4);
        }
        throw new IllegalArgumentException("bogus highNibble");
    }

    public static int nibble0(int i2) {
        return i2 & 15;
    }

    public static int nibble1(int i2) {
        return (i2 >> 4) & 15;
    }

    public static int nibble2(int i2) {
        return (i2 >> 8) & 15;
    }

    public static int nibble3(int i2) {
        return (i2 >> 12) & 15;
    }

    public static short unit0(int i2) {
        return (short) i2;
    }

    public static short unit0(long j2) {
        return (short) j2;
    }

    public static short unit1(int i2) {
        return (short) (i2 >> 16);
    }

    public static short unit1(long j2) {
        return (short) (j2 >> 16);
    }

    public static short unit2(long j2) {
        return (short) (j2 >> 32);
    }

    public static short unit3(long j2) {
        return (short) (j2 >> 48);
    }

    public static InstructionCodec valueOf(String str) {
        return (InstructionCodec) Enum.valueOf(InstructionCodec.class, str);
    }

    public static InstructionCodec[] values() {
        return (InstructionCodec[]) $VALUES.clone();
    }

    public abstract j31 decode(int i2, h31 h31Var);

    public abstract void encode(j31 j31Var, i31 i31Var);

    public /* synthetic */ InstructionCodec(String str, int i2, k kVar) {
        this(str, i2);
    }

    public static short codeUnit(int i2, int i3, int i4, int i5) {
        if ((i2 & (-16)) != 0) {
            throw new IllegalArgumentException("bogus nibble0");
        }
        if ((i3 & (-16)) != 0) {
            throw new IllegalArgumentException("bogus nibble1");
        }
        if ((i4 & (-16)) != 0) {
            throw new IllegalArgumentException("bogus nibble2");
        }
        if ((i5 & (-16)) == 0) {
            return (short) (i2 | (i3 << 4) | (i4 << 8) | (i5 << 12));
        }
        throw new IllegalArgumentException("bogus nibble3");
    }
}
