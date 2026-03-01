package io.dcloud.common.adapter.util;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public class InvokeExecutorHelper {
    public static InvokeExecutor create(String str) {
        InvokeExecutor invokeExecutor = new InvokeExecutor();
        try {
            invokeExecutor.mCls = Class.forName(str);
        } catch (Exception unused) {
        }
        return invokeExecutor;
    }

    public static InvokeExecutor createInvokeExecutor(String str, Class[] clsArr, Object... objArr) {
        InvokeExecutor invokeExecutor = new InvokeExecutor();
        try {
            Class<?> cls = Class.forName(str);
            invokeExecutor.mCls = cls;
            invokeExecutor.mObj = cls.getConstructor(clsArr).newInstance(objArr);
        } catch (Exception e2) {
            e2.printStackTrace();
            Logger.d("createInvokeExecutor clsName=" + str);
        }
        return invokeExecutor;
    }

    public static class InvokeExecutor {
        public Class mCls = null;
        public Object mObj = null;

        public final int getInt(String str) {
            try {
                Class cls = this.mCls;
                if (cls != null) {
                    return ((Integer) cls.getField(str).get(this.mObj)).intValue();
                }
                return -10000;
            } catch (Exception e2) {
                e2.printStackTrace();
                return -10000;
            }
        }

        public final String getString(String str) {
            try {
                Class cls = this.mCls;
                if (cls != null) {
                    return (String) cls.getField(str).get(this.mObj);
                }
                return null;
            } catch (Exception unused) {
                return null;
            }
        }

        public final boolean hasObject() {
            return this.mObj != null;
        }

        public final String invoke(String str) {
            Method method;
            try {
                Class cls = this.mCls;
                return (cls == null || (method = cls.getMethod(str, new Class[0])) == null) ? "" : (String) method.invoke(this.mObj, new Object[0]);
            } catch (Exception e2) {
                e2.printStackTrace();
                return "";
            }
        }

        public final InvokeExecutor setInstance(Object obj) {
            this.mObj = obj;
            return this;
        }

        public final String invoke(String str, String str2) {
            Method method;
            try {
                Class cls = this.mCls;
                if (cls != null && (method = cls.getMethod(str, String.class)) != null) {
                    return (String) method.invoke(this.mObj, str2);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return str2;
        }

        public final boolean invoke(String str, String str2, boolean z) {
            Method method;
            try {
                Class cls = this.mCls;
                if (cls != null && (method = cls.getMethod(str, String.class)) != null) {
                    return ((Boolean) method.invoke(this.mObj, str2)).booleanValue();
                }
            } catch (Exception unused) {
            }
            return z;
        }

        public final Object invoke(String str, Class[] clsArr, Object... objArr) {
            Method method;
            try {
                Class cls = this.mCls;
                if (cls == null || (method = cls.getMethod(str, clsArr)) == null) {
                    return null;
                }
                return method.invoke(this.mObj, objArr);
            } catch (Exception unused) {
                return null;
            }
        }
    }
}
