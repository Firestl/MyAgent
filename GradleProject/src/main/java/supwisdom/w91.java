package supwisdom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class w91 extends o91 {
    public w91(Object obj, Class cls) {
        super(obj, cls);
    }

    public static Object d() {
        if (ba1.f7044a == null) {
            t91.b("TransactionHandlerStub", "ClientTransactionHandler.getTransactionExecutor is null");
        } else {
            t91.c("TransactionHandlerStub", "ClientTransactionHandler.getTransactionExecutor success");
        }
        return ba1.f7044a.a(aa1.f6877a.a(new Object[0]), new Object[0]);
    }

    public static w91 e() {
        if (ha1.f7820a == null) {
            t91.b("TransactionHandlerStub", "TransactionExecutor.mTransactionHandler failed");
        } else {
            t91.c("TransactionHandlerStub", "TransactionExecutor.mTransactionHandler success");
        }
        qa1<Object> qa1Var = ha1.f7820a;
        if (qa1Var == null) {
            return null;
        }
        if (qa1Var.a(d()) == null) {
            t91.b("TransactionHandlerStub", "TransactionExecutor.mTransactionHandler.get(getTransactionExecutor()) is null");
        } else {
            t91.c("TransactionHandlerStub", "TransactionExecutor.mTransactionHandler.get(getTransactionExecutor()) success");
        }
        Object objA = ha1.f7820a.a(d());
        Class<? super Object> superclass = objA.getClass().getSuperclass();
        if (superclass.toString().contains("VivoBaseActivityThread")) {
            superclass = superclass.getSuperclass();
            t91.c("TransactionHandlerStub", "proxyObj super super class = " + superclass);
        }
        return new w91(objA, superclass);
    }

    @Override // supwisdom.q91
    public void a() {
        if (ha1.f7820a == null) {
            t91.b("TransactionHandlerStub", "TransactionExecutor.mTransactionHandler is null");
        } else {
            t91.c("TransactionHandlerStub", "TransactionExecutor.mTransactionHandler success");
        }
        ha1.f7820a.a(d(), c());
    }

    @Override // supwisdom.q91
    public boolean b() {
        if (ha1.f7820a.a(d()) == c()) {
            t91.c("TransactionHandlerStub", "TransactionExecutor.mTransactionHandler inject success");
        } else {
            t91.b("TransactionHandlerStub", "TransactionExecutor.mTransactionHandler inject failed");
        }
        return ha1.f7820a.a(d()) == c();
    }
}
