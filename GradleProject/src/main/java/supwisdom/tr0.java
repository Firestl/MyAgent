package supwisdom;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import com.google.crypto.tink.shaded.protobuf.WireFormat;
import com.google.crypto.tink.shaded.protobuf.Writer;
import java.io.IOException;

/* JADX INFO: compiled from: UnknownFieldSetLiteSchema.java */
/* JADX INFO: loaded from: classes.dex */
public class tr0 extends rr0<sr0, sr0> {
    @Override // supwisdom.rr0
    public boolean a(jr0 jr0Var) {
        return false;
    }

    @Override // supwisdom.rr0
    public void e(Object obj) {
        b(obj).e();
    }

    @Override // supwisdom.rr0
    public /* bridge */ /* synthetic */ sr0 f(sr0 sr0Var) {
        sr0 sr0Var2 = sr0Var;
        c2(sr0Var2);
        return sr0Var2;
    }

    /* JADX INFO: renamed from: c, reason: avoid collision after fix types in other method */
    public sr0 c2(sr0 sr0Var) {
        sr0Var.e();
        return sr0Var;
    }

    @Override // supwisdom.rr0
    public void b(sr0 sr0Var, int i, long j) {
        sr0Var.a(WireFormat.a(i, 0), Long.valueOf(j));
    }

    @Override // supwisdom.rr0
    /* JADX INFO: renamed from: b, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public void c(Object obj, sr0 sr0Var) {
        ((GeneratedMessageLite) obj).unknownFields = sr0Var;
    }

    @Override // supwisdom.rr0
    public sr0 b(Object obj) {
        return ((GeneratedMessageLite) obj).unknownFields;
    }

    @Override // supwisdom.rr0
    public void b(sr0 sr0Var, Writer writer) throws IOException {
        sr0Var.b(writer);
    }

    @Override // supwisdom.rr0
    public sr0 a() {
        return sr0.g();
    }

    @Override // supwisdom.rr0
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public int d(sr0 sr0Var) {
        return sr0Var.d();
    }

    @Override // supwisdom.rr0
    public void a(sr0 sr0Var, int i, int i2) {
        sr0Var.a(WireFormat.a(i, 5), Integer.valueOf(i2));
    }

    @Override // supwisdom.rr0
    public void a(sr0 sr0Var, int i, long j) {
        sr0Var.a(WireFormat.a(i, 1), Long.valueOf(j));
    }

    @Override // supwisdom.rr0
    public void a(sr0 sr0Var, int i, ByteString byteString) {
        sr0Var.a(WireFormat.a(i, 2), byteString);
    }

    @Override // supwisdom.rr0
    public void a(sr0 sr0Var, int i, sr0 sr0Var2) {
        sr0Var.a(WireFormat.a(i, 3), sr0Var2);
    }

    @Override // supwisdom.rr0
    public sr0 a(Object obj) {
        sr0 sr0VarB = b(obj);
        if (sr0VarB != sr0.f()) {
            return sr0VarB;
        }
        sr0 sr0VarG = sr0.g();
        c(obj, sr0VarG);
        return sr0VarG;
    }

    @Override // supwisdom.rr0
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void b(Object obj, sr0 sr0Var) {
        c(obj, sr0Var);
    }

    @Override // supwisdom.rr0
    public void a(sr0 sr0Var, Writer writer) throws IOException {
        sr0Var.a(writer);
    }

    @Override // supwisdom.rr0
    public sr0 a(sr0 sr0Var, sr0 sr0Var2) {
        return sr0Var2.equals(sr0.f()) ? sr0Var : sr0.a(sr0Var, sr0Var2);
    }

    @Override // supwisdom.rr0
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int c(sr0 sr0Var) {
        return sr0Var.c();
    }
}
