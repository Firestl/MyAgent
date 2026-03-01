package com.vivo.push.c;

/* JADX INFO: compiled from: ChangeNetPermissionTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b extends com.vivo.push.l {
    public b(com.vivo.push.o oVar) {
        super(oVar);
    }

    @Override // com.vivo.push.l
    public final void a(com.vivo.push.o oVar) {
        com.vivo.push.model.b bVarA = com.vivo.push.util.s.a(this.f5616a);
        try {
            if (((com.vivo.push.b.d) oVar).d() ? f.a(this.f5616a) : f.b(this.f5616a)) {
                com.vivo.push.model.b bVarA2 = com.vivo.push.util.s.a(this.f5616a);
                if (bVarA == null || bVarA2 == null || bVarA2.a() == null || !bVarA2.a().equals(bVarA.a())) {
                    if (bVarA != null && bVarA.a() != null) {
                        com.vivo.push.a.a.a(this.f5616a, bVarA.a(), new com.vivo.push.b.y(bVarA.a()));
                    }
                    if (bVarA2 == null || bVarA2.a() == null) {
                        return;
                    }
                    com.vivo.push.a.a.a(this.f5616a, bVarA2.a(), new com.vivo.push.b.f());
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
