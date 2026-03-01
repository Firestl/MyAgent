package supwisdom;

import java.util.Set;

/* JADX INFO: compiled from: AbstractHttpParams.java */
/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public abstract class vo1 implements wo1 {
    public boolean getBooleanParameter(String str, boolean z) {
        Object parameter = getParameter(str);
        return parameter == null ? z : ((Boolean) parameter).booleanValue();
    }

    public double getDoubleParameter(String str, double d) {
        Object parameter = getParameter(str);
        return parameter == null ? d : ((Double) parameter).doubleValue();
    }

    public int getIntParameter(String str, int i) {
        Object parameter = getParameter(str);
        return parameter == null ? i : ((Integer) parameter).intValue();
    }

    public long getLongParameter(String str, long j) {
        Object parameter = getParameter(str);
        return parameter == null ? j : ((Long) parameter).longValue();
    }

    public abstract Set<String> getNames();

    public boolean isParameterFalse(String str) {
        return !getBooleanParameter(str, false);
    }

    public boolean isParameterTrue(String str) {
        return getBooleanParameter(str, false);
    }

    public wo1 setBooleanParameter(String str, boolean z) {
        setParameter(str, z ? Boolean.TRUE : Boolean.FALSE);
        return this;
    }

    public wo1 setDoubleParameter(String str, double d) {
        setParameter(str, Double.valueOf(d));
        return this;
    }

    public wo1 setIntParameter(String str, int i) {
        setParameter(str, Integer.valueOf(i));
        return this;
    }

    public wo1 setLongParameter(String str, long j) {
        setParameter(str, Long.valueOf(j));
        return this;
    }
}
