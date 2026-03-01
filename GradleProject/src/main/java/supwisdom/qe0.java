package supwisdom;

import com.google.android.gms.common.Feature;
import supwisdom.bd0;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class qe0 extends ie0<Boolean> {
    public final ed0<?> c;

    public qe0(ed0<?> ed0Var, rk0<Boolean> rk0Var) {
        super(4, rk0Var);
        this.c = ed0Var;
    }

    @Override // supwisdom.sd0
    public final /* bridge */ /* synthetic */ void a(we0 we0Var, boolean z) {
    }

    @Override // supwisdom.pe0
    public final Feature[] b(bd0.a<?> aVar) {
        he0 he0Var = aVar.c().get(this.c);
        if (he0Var == null) {
            return null;
        }
        return he0Var.f7832a.b();
    }

    @Override // supwisdom.pe0
    public final boolean c(bd0.a<?> aVar) {
        he0 he0Var = aVar.c().get(this.c);
        return he0Var != null && he0Var.f7832a.c();
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*  JADX ERROR: JadxRuntimeException in pass: FinishTypeInference
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r0v5 boolean
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.dex.visitors.typeinference.FinishTypeInference.lambda$visit$0(FinishTypeInference.java:27)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.typeinference.FinishTypeInference.visit(FinishTypeInference.java:22)
        */
    @Override // supwisdom.ie0
    public final void d(supwisdom.bd0.a<?> r4) throws android.os.RemoteException {
        /*
            r3 = this;
            java.util.Map r0 = r4.c()
            supwisdom.ed0<?> r1 = r3.c
            java.lang.Object r0 = r0.remove(r1)
            supwisdom.he0 r0 = (supwisdom.he0) r0
            if (r0 == 0) goto L1f
            supwisdom.ld0<supwisdom.mc0$b, ?> r1 = r0.b
            supwisdom.mc0$f r4 = r4.b()
            supwisdom.rk0<T> r2 = r3.b
            r1.a(r4, r2)
            supwisdom.hd0<supwisdom.mc0$b, ?> r4 = r0.f7832a
            r4.a()
            return
        L1f:
            supwisdom.rk0<T> r4 = r3.b
            r0 = 0
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r4.b(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.qe0.d(supwisdom.bd0$a):void");
    }
}
