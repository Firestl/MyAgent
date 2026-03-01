package supwisdom;

/* JADX INFO: compiled from: ViewModelProvider.java */
/* JADX INFO: loaded from: classes.dex */
public class he {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f7831a;
    public final ie b;

    /* JADX INFO: compiled from: ViewModelProvider.java */
    public interface a {
        <T extends ge> T a(Class<T> cls);
    }

    /* JADX INFO: compiled from: ViewModelProvider.java */
    public static abstract class b implements a {
        @Override // supwisdom.he.a
        public <T extends ge> T a(Class<T> cls) {
            throw new UnsupportedOperationException("create(String, Class<?>) must be called on implementaions of KeyedFactory");
        }

        public abstract <T extends ge> T a(String str, Class<T> cls);
    }

    public he(ie ieVar, a aVar) {
        this.f7831a = aVar;
        this.b = ieVar;
    }

    public <T extends ge> T a(Class<T> cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName == null) {
            throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
        }
        return (T) a("androidx.lifecycle.ViewModelProvider.DefaultKey:" + canonicalName, cls);
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
    public <T extends ge> T a(String str, Class<T> cls) {
        T t;
        T t2 = (T) this.b.a(str);
        if (cls.isInstance(t2)) {
            return t2;
        }
        a aVar = this.f7831a;
        if (aVar instanceof b) {
            t = (T) ((b) aVar).a(str, cls);
        } else {
            t = (T) aVar.a(cls);
        }
        this.b.a(str, t);
        return t;
    }
}
