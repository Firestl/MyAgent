package cz.msebera.android.httpclient.params;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import supwisdom.vo1;
import supwisdom.wo1;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class BasicHttpParams extends vo1 implements Serializable, Cloneable {
    public static final long serialVersionUID = -7086398485908701455L;
    public final Map<String, Object> parameters = new ConcurrentHashMap();

    public void clear() {
        this.parameters.clear();
    }

    public Object clone() throws CloneNotSupportedException {
        BasicHttpParams basicHttpParams = (BasicHttpParams) super.clone();
        copyParams(basicHttpParams);
        return basicHttpParams;
    }

    public wo1 copy() {
        try {
            return (wo1) clone();
        } catch (CloneNotSupportedException unused) {
            throw new UnsupportedOperationException("Cloning not supported");
        }
    }

    public void copyParams(wo1 wo1Var) {
        for (Map.Entry<String, Object> entry : this.parameters.entrySet()) {
            wo1Var.setParameter(entry.getKey(), entry.getValue());
        }
    }

    @Override // supwisdom.vo1
    public Set<String> getNames() {
        return new HashSet(this.parameters.keySet());
    }

    @Override // supwisdom.wo1
    public Object getParameter(String str) {
        return this.parameters.get(str);
    }

    public boolean isParameterSet(String str) {
        return getParameter(str) != null;
    }

    public boolean isParameterSetLocally(String str) {
        return this.parameters.get(str) != null;
    }

    public boolean removeParameter(String str) {
        if (!this.parameters.containsKey(str)) {
            return false;
        }
        this.parameters.remove(str);
        return true;
    }

    @Override // supwisdom.wo1
    public wo1 setParameter(String str, Object obj) {
        if (str == null) {
            return this;
        }
        if (obj != null) {
            this.parameters.put(str, obj);
        } else {
            this.parameters.remove(str);
        }
        return this;
    }

    public void setParameters(String[] strArr, Object obj) {
        for (String str : strArr) {
            setParameter(str, obj);
        }
    }
}
