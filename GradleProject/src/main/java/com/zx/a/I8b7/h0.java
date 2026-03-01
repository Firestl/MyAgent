package com.zx.a.I8b7;

import com.taobao.weex.el.parse.Operators;
import com.zx.a.I8b7.a1;
import com.zx.a.I8b7.c0;
import com.zx.a.I8b7.d1;
import com.zx.a.I8b7.e1;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes2.dex */
public class h0 implements c0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public g0 f6225a;

    public h0(g0 g0Var, int i) {
        this.f6225a = g0Var;
    }

    @Override // com.zx.a.I8b7.c0
    public d1 a(c0.a aVar) throws IOException {
        u0 u0Var = (u0) aVar;
        a1 a1Var = u0Var.c;
        a1.a aVar2 = new a1.a(a1Var);
        StringBuilder sb = new StringBuilder();
        sb.append(a1Var.b + Operators.SPACE_STR + a1Var.f6193a.toString() + Operators.SPACE_STR + a1Var.f6194e + "\n");
        c1 c1Var = a1Var.d;
        if (c1Var != null && ((b1) c1Var).f6200a.a() != null) {
            if (((b1) a1Var.d).b > 2147483647L) {
                StringBuilder sbA = m2.a("request body content length: ");
                sbA.append(((b1) a1Var.d).b);
                sbA.append("\n");
                sb.append(sbA.toString());
            } else {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                b1 b1Var = (b1) a1Var.d;
                byteArrayOutputStream.write(b1Var.c, b1Var.d, b1Var.b);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                sb.append(new String(byteArray, StandardCharsets.UTF_8) + "\n");
                aVar2.d = c1.a(((b1) a1Var.d).f6200a, byteArray);
            }
        }
        this.f6225a.a(sb.toString());
        d1 d1VarA = u0Var.a(new a1(aVar2), u0Var.d);
        d1.a aVar3 = new d1.a(d1VarA);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(d1VarA.b + Operators.SPACE_STR + d1VarA.c + Operators.SPACE_STR + a1Var.f6193a.toString() + Operators.SPACE_STR + a1Var.f6194e + "\n");
        e1 e1Var = d1VarA.f6207e;
        if (e1Var != null && ((e1.a) e1Var).f6212a.a() != null) {
            e1 e1Var2 = d1VarA.f6207e;
            if (((e1.a) e1Var2).b > 2147483647L) {
                StringBuilder sbA2 = m2.a("response body content length: ");
                sbA2.append(((e1.a) d1VarA.f6207e).b);
                sbA2.append("\n");
                sb2.append(sbA2.toString());
            } else {
                byte[] bArrA = e1Var2.a();
                sb2.append("response body size: ");
                sb2.append(bArrA.length);
                sb2.append(", ");
                sb2.append(new String(bArrA, StandardCharsets.UTF_8) + "\n");
                aVar3.f6209e = e1.a(((e1.a) d1VarA.f6207e).f6212a, (long) bArrA.length, new ByteArrayInputStream(bArrA));
            }
        }
        this.f6225a.a(sb2.toString());
        return aVar3.a();
    }
}
