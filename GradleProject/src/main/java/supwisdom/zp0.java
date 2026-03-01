package supwisdom;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.WireFormat;
import com.google.crypto.tink.shaded.protobuf.Writer;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: ExtensionSchemaLite.java */
/* JADX INFO: loaded from: classes.dex */
public final class zp0 extends yp0<GeneratedMessageLite.e> {

    /* JADX INFO: compiled from: ExtensionSchemaLite.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f10020a;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            f10020a = iArr;
            try {
                iArr[WireFormat.FieldType.DOUBLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10020a[WireFormat.FieldType.FLOAT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10020a[WireFormat.FieldType.INT64.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f10020a[WireFormat.FieldType.UINT64.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f10020a[WireFormat.FieldType.INT32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f10020a[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f10020a[WireFormat.FieldType.FIXED32.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f10020a[WireFormat.FieldType.BOOL.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f10020a[WireFormat.FieldType.UINT32.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f10020a[WireFormat.FieldType.SFIXED32.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f10020a[WireFormat.FieldType.SFIXED64.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f10020a[WireFormat.FieldType.SINT32.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f10020a[WireFormat.FieldType.SINT64.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f10020a[WireFormat.FieldType.ENUM.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f10020a[WireFormat.FieldType.BYTES.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f10020a[WireFormat.FieldType.STRING.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f10020a[WireFormat.FieldType.GROUP.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f10020a[WireFormat.FieldType.MESSAGE.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
        }
    }

    @Override // supwisdom.yp0
    public boolean a(uq0 uq0Var) {
        return uq0Var instanceof GeneratedMessageLite.c;
    }

    @Override // supwisdom.yp0
    public cq0<GeneratedMessageLite.e> b(Object obj) {
        return ((GeneratedMessageLite.c) obj).n();
    }

    @Override // supwisdom.yp0
    public void c(Object obj) {
        a(obj).h();
    }

    @Override // supwisdom.yp0
    public cq0<GeneratedMessageLite.e> a(Object obj) {
        return ((GeneratedMessageLite.c) obj).extensions;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // supwisdom.yp0
    public <UT, UB> UB a(jr0 jr0Var, Object obj, xp0 xp0Var, cq0<GeneratedMessageLite.e> cq0Var, UB ub, rr0<UT, UB> rr0Var) throws IOException {
        Object objA;
        ArrayList arrayList;
        GeneratedMessageLite.f fVar = (GeneratedMessageLite.f) obj;
        int iC = fVar.c();
        if (fVar.b.D() && fVar.b.isPacked()) {
            switch (a.f10020a[fVar.a().ordinal()]) {
                case 1:
                    arrayList = new ArrayList();
                    jr0Var.p(arrayList);
                    break;
                case 2:
                    arrayList = new ArrayList();
                    jr0Var.n(arrayList);
                    break;
                case 3:
                    arrayList = new ArrayList();
                    jr0Var.f(arrayList);
                    break;
                case 4:
                    arrayList = new ArrayList();
                    jr0Var.e(arrayList);
                    break;
                case 5:
                    arrayList = new ArrayList();
                    jr0Var.h(arrayList);
                    break;
                case 6:
                    arrayList = new ArrayList();
                    jr0Var.q(arrayList);
                    break;
                case 7:
                    arrayList = new ArrayList();
                    jr0Var.j(arrayList);
                    break;
                case 8:
                    arrayList = new ArrayList();
                    jr0Var.k(arrayList);
                    break;
                case 9:
                    arrayList = new ArrayList();
                    jr0Var.d(arrayList);
                    break;
                case 10:
                    arrayList = new ArrayList();
                    jr0Var.b(arrayList);
                    break;
                case 11:
                    arrayList = new ArrayList();
                    jr0Var.g(arrayList);
                    break;
                case 12:
                    arrayList = new ArrayList();
                    jr0Var.a(arrayList);
                    break;
                case 13:
                    arrayList = new ArrayList();
                    jr0Var.c(arrayList);
                    break;
                case 14:
                    arrayList = new ArrayList();
                    jr0Var.i(arrayList);
                    ub = (UB) nr0.a(iC, arrayList, fVar.b.a(), ub, rr0Var);
                    break;
                default:
                    throw new IllegalStateException("Type cannot be packed: " + fVar.b.E());
            }
            cq0Var.b(fVar.b, arrayList);
        } else {
            Object objValueOf = null;
            if (fVar.a() == WireFormat.FieldType.ENUM) {
                int iM = jr0Var.m();
                if (fVar.b.a().a(iM) == null) {
                    return (UB) nr0.a(iC, iM, ub, rr0Var);
                }
                objValueOf = Integer.valueOf(iM);
            } else {
                switch (a.f10020a[fVar.a().ordinal()]) {
                    case 1:
                        objValueOf = Double.valueOf(jr0Var.readDouble());
                        break;
                    case 2:
                        objValueOf = Float.valueOf(jr0Var.readFloat());
                        break;
                    case 3:
                        objValueOf = Long.valueOf(jr0Var.p());
                        break;
                    case 4:
                        objValueOf = Long.valueOf(jr0Var.a());
                        break;
                    case 5:
                        objValueOf = Integer.valueOf(jr0Var.m());
                        break;
                    case 6:
                        objValueOf = Long.valueOf(jr0Var.b());
                        break;
                    case 7:
                        objValueOf = Integer.valueOf(jr0Var.c());
                        break;
                    case 8:
                        objValueOf = Boolean.valueOf(jr0Var.d());
                        break;
                    case 9:
                        objValueOf = Integer.valueOf(jr0Var.f());
                        break;
                    case 10:
                        objValueOf = Integer.valueOf(jr0Var.o());
                        break;
                    case 11:
                        objValueOf = Long.valueOf(jr0Var.e());
                        break;
                    case 12:
                        objValueOf = Integer.valueOf(jr0Var.h());
                        break;
                    case 13:
                        objValueOf = Long.valueOf(jr0Var.i());
                        break;
                    case 14:
                        throw new IllegalStateException("Shouldn't reach here.");
                    case 15:
                        objValueOf = jr0Var.l();
                        break;
                    case 16:
                        objValueOf = jr0Var.j();
                        break;
                    case 17:
                        objValueOf = jr0Var.a(fVar.b().getClass(), xp0Var);
                        break;
                    case 18:
                        objValueOf = jr0Var.b(fVar.b().getClass(), xp0Var);
                        break;
                }
            }
            if (fVar.d()) {
                cq0Var.a(fVar.b, objValueOf);
            } else {
                int i = a.f10020a[fVar.a().ordinal()];
                if ((i == 17 || i == 18) && (objA = cq0Var.a(fVar.b)) != null) {
                    objValueOf = gq0.a(objA, objValueOf);
                }
                cq0Var.b(fVar.b, objValueOf);
            }
        }
        return ub;
    }

    @Override // supwisdom.yp0
    public int a(Map.Entry<?, ?> entry) {
        return ((GeneratedMessageLite.e) entry.getKey()).getNumber();
    }

    @Override // supwisdom.yp0
    public void a(Writer writer, Map.Entry<?, ?> entry) throws IOException {
        GeneratedMessageLite.e eVar = (GeneratedMessageLite.e) entry.getKey();
        if (eVar.D()) {
            switch (a.f10020a[eVar.E().ordinal()]) {
                case 1:
                    nr0.b(eVar.getNumber(), (List<Double>) entry.getValue(), writer, eVar.isPacked());
                    break;
                case 2:
                    nr0.f(eVar.getNumber(), (List) entry.getValue(), writer, eVar.isPacked());
                    break;
                case 3:
                    nr0.h(eVar.getNumber(), (List) entry.getValue(), writer, eVar.isPacked());
                    break;
                case 4:
                    nr0.n(eVar.getNumber(), (List) entry.getValue(), writer, eVar.isPacked());
                    break;
                case 5:
                    nr0.g(eVar.getNumber(), (List) entry.getValue(), writer, eVar.isPacked());
                    break;
                case 6:
                    nr0.e(eVar.getNumber(), (List) entry.getValue(), writer, eVar.isPacked());
                    break;
                case 7:
                    nr0.d(eVar.getNumber(), (List) entry.getValue(), writer, eVar.isPacked());
                    break;
                case 8:
                    nr0.a(eVar.getNumber(), (List<Boolean>) entry.getValue(), writer, eVar.isPacked());
                    break;
                case 9:
                    nr0.m(eVar.getNumber(), (List) entry.getValue(), writer, eVar.isPacked());
                    break;
                case 10:
                    nr0.i(eVar.getNumber(), (List) entry.getValue(), writer, eVar.isPacked());
                    break;
                case 11:
                    nr0.j(eVar.getNumber(), (List) entry.getValue(), writer, eVar.isPacked());
                    break;
                case 12:
                    nr0.k(eVar.getNumber(), (List) entry.getValue(), writer, eVar.isPacked());
                    break;
                case 13:
                    nr0.l(eVar.getNumber(), (List) entry.getValue(), writer, eVar.isPacked());
                    break;
                case 14:
                    nr0.g(eVar.getNumber(), (List) entry.getValue(), writer, eVar.isPacked());
                    break;
                case 15:
                    nr0.a(eVar.getNumber(), (List<ByteString>) entry.getValue(), writer);
                    break;
                case 16:
                    nr0.b(eVar.getNumber(), (List<String>) entry.getValue(), writer);
                    break;
                case 17:
                    List list = (List) entry.getValue();
                    if (list != null && !list.isEmpty()) {
                        nr0.a(eVar.getNumber(), (List<?>) entry.getValue(), writer, fr0.a().a((Class) list.get(0).getClass()));
                        break;
                    }
                    break;
                case 18:
                    List list2 = (List) entry.getValue();
                    if (list2 != null && !list2.isEmpty()) {
                        nr0.b(eVar.getNumber(), (List<?>) entry.getValue(), writer, fr0.a().a((Class) list2.get(0).getClass()));
                        break;
                    }
                    break;
            }
        }
        switch (a.f10020a[eVar.E().ordinal()]) {
            case 1:
                writer.a(eVar.getNumber(), ((Double) entry.getValue()).doubleValue());
                break;
            case 2:
                writer.a(eVar.getNumber(), ((Float) entry.getValue()).floatValue());
                break;
            case 3:
                writer.c(eVar.getNumber(), ((Long) entry.getValue()).longValue());
                break;
            case 4:
                writer.b(eVar.getNumber(), ((Long) entry.getValue()).longValue());
                break;
            case 5:
                writer.d(eVar.getNumber(), ((Integer) entry.getValue()).intValue());
                break;
            case 6:
                writer.a(eVar.getNumber(), ((Long) entry.getValue()).longValue());
                break;
            case 7:
                writer.b(eVar.getNumber(), ((Integer) entry.getValue()).intValue());
                break;
            case 8:
                writer.a(eVar.getNumber(), ((Boolean) entry.getValue()).booleanValue());
                break;
            case 9:
                writer.a(eVar.getNumber(), ((Integer) entry.getValue()).intValue());
                break;
            case 10:
                writer.c(eVar.getNumber(), ((Integer) entry.getValue()).intValue());
                break;
            case 11:
                writer.d(eVar.getNumber(), ((Long) entry.getValue()).longValue());
                break;
            case 12:
                writer.f(eVar.getNumber(), ((Integer) entry.getValue()).intValue());
                break;
            case 13:
                writer.e(eVar.getNumber(), ((Long) entry.getValue()).longValue());
                break;
            case 14:
                writer.d(eVar.getNumber(), ((Integer) entry.getValue()).intValue());
                break;
            case 15:
                writer.a(eVar.getNumber(), (ByteString) entry.getValue());
                break;
            case 16:
                writer.a(eVar.getNumber(), (String) entry.getValue());
                break;
            case 17:
                writer.b(eVar.getNumber(), entry.getValue(), fr0.a().a((Class) entry.getValue().getClass()));
                break;
            case 18:
                writer.a(eVar.getNumber(), entry.getValue(), fr0.a().a((Class) entry.getValue().getClass()));
                break;
        }
    }

    @Override // supwisdom.yp0
    public Object a(xp0 xp0Var, uq0 uq0Var, int i) {
        return xp0Var.a(uq0Var, i);
    }

    @Override // supwisdom.yp0
    public void a(jr0 jr0Var, Object obj, xp0 xp0Var, cq0<GeneratedMessageLite.e> cq0Var) throws IOException {
        GeneratedMessageLite.f fVar = (GeneratedMessageLite.f) obj;
        cq0Var.b(fVar.b, jr0Var.b(fVar.b().getClass(), xp0Var));
    }

    @Override // supwisdom.yp0
    public void a(ByteString byteString, Object obj, xp0 xp0Var, cq0<GeneratedMessageLite.e> cq0Var) throws IOException {
        GeneratedMessageLite.f fVar = (GeneratedMessageLite.f) obj;
        uq0 uq0VarS = fVar.b().e().S();
        np0 np0VarA = np0.a(ByteBuffer.wrap(byteString.toByteArray()), true);
        fr0.a().a(uq0VarS, np0VarA, xp0Var);
        cq0Var.b(fVar.b, uq0VarS);
        if (np0VarA.k() != Integer.MAX_VALUE) {
            throw InvalidProtocolBufferException.invalidEndTag();
        }
    }
}
