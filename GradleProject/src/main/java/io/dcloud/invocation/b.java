package io.dcloud.invocation;

import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.StringUtil;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes3.dex */
public class b implements InvocationHandler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f6711a = null;
    public Object b = null;
    public Class c;
    public IWebview d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f6712e;
    public ArrayList<String> f;

    public b(IWebview iWebview, String str, JSONArray jSONArray, String str2) {
        this.c = null;
        this.d = null;
        this.f6712e = null;
        this.f = null;
        try {
            this.d = iWebview;
            this.f6712e = str2;
            this.f = new ArrayList<>();
            for (int i = 0; i < jSONArray.length(); i++) {
                this.f.add(JSONUtil.getString(jSONArray, i));
            }
            this.c = Class.forName(str);
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
        }
    }

    public Object a(JSONArray jSONArray) {
        Object objNewProxyInstance = Proxy.newProxyInstance(this.c.getClassLoader(), new Class[]{this.c}, this);
        this.b = objNewProxyInstance;
        return objNewProxyInstance;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        StringBuffer stringBuffer = new StringBuffer(Operators.ARRAY_START_STR);
        String name = method.getName();
        if (this.f.contains(name)) {
            if (objArr != null) {
                int length = objArr.length;
                for (int i = 0; i < length; i++) {
                    if (objArr[i] == null) {
                        stringBuffer.append("undefined");
                    } else {
                        stringBuffer.append(a.b(this.d, objArr[i]));
                    }
                    if (i != length - 1) {
                        stringBuffer.append(",");
                    }
                }
            }
            stringBuffer.append(Operators.ARRAY_END_STR);
            Deprecated_JSUtil.execCallback(this.d, this.f6712e, StringUtil.format("{method:'%s',arguments:%s}", method.getName(), stringBuffer.toString()), JSUtil.OK, true, true);
            return null;
        }
        if ("hashCode".equals(name)) {
            return Integer.valueOf(hashCode());
        }
        if ("equals".equals(name)) {
            return Boolean.valueOf(equals(objArr));
        }
        if ("getClass".equals(name)) {
            return this.c;
        }
        if ("finalize".equals(name)) {
            finalize();
        } else if ("notify".equals(name)) {
            notify();
        } else if ("notifyAll".equals(name)) {
            notifyAll();
        } else {
            if ("toString".equals(name)) {
                return this.f6711a + this.c.toString();
            }
            if (!"wait".equals(name)) {
                try {
                    return method.invoke(this.b, objArr);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } else if (objArr.length == 0) {
                wait();
            } else if (objArr.length == 1) {
                wait(Long.parseLong(String.valueOf(objArr[0])));
            } else if (objArr.length == 2) {
                wait(Long.parseLong(String.valueOf(objArr[0])), Integer.parseInt(String.valueOf(objArr[0])));
            }
        }
        return null;
    }
}
