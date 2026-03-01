package io.dcloud.invocation;

import android.util.Log;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.constant.AbsoluteConst;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public io.dcloud.invocation.a f6713a;
    public Class b;
    public Object c;

    public c(IWebview iWebview, io.dcloud.invocation.a aVar, Class cls, String str, JSONArray jSONArray) throws Exception {
        this.f6713a = null;
        this.b = null;
        this.c = null;
        this.f6713a = aVar;
        this.b = cls;
        this.c = a(iWebview, aVar, cls, jSONArray);
    }

    public static Object a(IWebview iWebview, io.dcloud.invocation.a aVar, Class cls, JSONArray jSONArray) throws Exception {
        Constructor<?> constructor;
        if (jSONArray == null) {
            return cls.newInstance();
        }
        Object[] objArrA = a(iWebview, aVar, jSONArray);
        int i = 0;
        Class<?>[] clsArr = (Class[]) objArrA[0];
        Object[] objArr = (Object[]) objArrA[1];
        try {
            constructor = cls.getConstructor(clsArr);
        } catch (NoSuchMethodException unused) {
            Constructor<?>[] constructors = cls.getConstructors();
            while (true) {
                if (i >= constructors.length) {
                    constructor = null;
                    break;
                }
                Class<?>[] parameterTypes = constructors[i].getParameterTypes();
                if (parameterTypes.length == clsArr.length && a(parameterTypes, clsArr, objArr)) {
                    constructor = constructors[i];
                    break;
                }
                i++;
            }
        }
        if (constructor != null) {
            return constructor.newInstance(objArr);
        }
        return null;
    }

    public static void b(IWebview iWebview, io.dcloud.invocation.a aVar, Class cls, Object obj, String str, JSONArray jSONArray) {
        Object[] objArrA = a(iWebview, aVar, jSONArray);
        try {
            Field field = cls.getField(str);
            if (field != null) {
                field.setAccessible(true);
                field.set(obj, ((Object[]) objArrA[1])[0]);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static boolean c(Class cls, Class[] clsArr, int i) {
        boolean zC = c(cls, clsArr[i]);
        if (zC) {
            clsArr[i] = cls;
        }
        return zC;
    }

    public static boolean d(Class cls) {
        return Character.TYPE.equals(cls) || Character.class.equals(cls);
    }

    public static boolean e(Class cls) {
        return cls.equals(Byte.TYPE) || cls.equals(Byte.class) || cls.equals(Integer.TYPE) || cls.equals(Integer.class) || cls.equals(Short.TYPE) || cls.equals(Short.class) || cls.equals(Float.TYPE) || cls.equals(Float.class) || cls.equals(Long.TYPE) || cls.equals(Long.class) || cls.equals(Double.TYPE) || cls.equals(Double.class);
    }

    public boolean equals(Object obj) {
        return super.equals(obj) || this.c.equals(obj);
    }

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int[] f6714a;

        public a(int i) {
            this.f6714a = null;
            this.f6714a = new int[i];
        }

        public int[] a(int i) {
            int[] iArr = this.f6714a;
            if (i > iArr.length) {
                return null;
            }
            return Arrays.copyOfRange(iArr, iArr.length - i, iArr.length);
        }

        public void a(int i, int i2) {
            int[] iArr = this.f6714a;
            if (i > iArr.length) {
                return;
            }
            iArr[iArr.length - i] = i2;
        }
    }

    public static boolean c(Class cls, Class cls2) {
        return (Number.class.isAssignableFrom(cls) && Number.class.isAssignableFrom(cls2)) || (e(cls) && e(cls2));
    }

    public static boolean c(Class cls) {
        return Boolean.TYPE.equals(cls) || Boolean.class.equals(cls);
    }

    public static Method b(Class cls, String str, Class[] clsArr, Object[] objArr) {
        int length;
        try {
            Method[] declaredMethods = cls.getDeclaredMethods();
            for (int i = 0; i < declaredMethods.length; i++) {
                Method method = declaredMethods[i];
                if (method.getName().equals(str) && (length = method.getParameterTypes().length) == clsArr.length && (length == 0 || a(method.getParameterTypes(), clsArr, objArr))) {
                    return method;
                }
            }
            if (cls != Object.class) {
                return b(cls.getSuperclass(), str, clsArr, objArr);
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public c(io.dcloud.invocation.a aVar, Class cls, String str, Object obj) {
        this.f6713a = null;
        this.b = null;
        this.c = null;
        this.f6713a = aVar;
        this.b = cls;
        this.c = obj;
    }

    public static Object a(Class cls, Object obj, String str) {
        try {
            Field field = cls.getField(str);
            if (field != null) {
                field.setAccessible(true);
                return field.get(obj);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return null;
    }

    public static Object[] b(Class cls) {
        Class<?> cls2;
        String name = cls.getName();
        Object[] objArr = new Object[2];
        Class<?> cls3 = null;
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i >= name.length()) {
                break;
            }
            char cCharAt = name.charAt(i);
            if (cCharAt == '[') {
                i2++;
            } else if (cCharAt == 'B') {
                cls3 = Byte.TYPE;
            } else if (cCharAt == 'I') {
                cls3 = Integer.TYPE;
            } else if (cCharAt == 'F') {
                cls3 = Float.TYPE;
            } else if (cCharAt == 'D') {
                cls3 = Double.TYPE;
            } else if (cCharAt == 'Z') {
                cls3 = Boolean.TYPE;
            } else if (cCharAt == 'J') {
                cls3 = Long.TYPE;
            } else if (cCharAt == 'S') {
                cls3 = Short.TYPE;
            } else if (cCharAt == 'L') {
                try {
                    cls2 = Class.forName(name.substring(i + 1));
                    cls3 = cls2;
                } catch (ClassNotFoundException e2) {
                    e2.printStackTrace();
                }
            } else {
                try {
                    cls2 = Class.forName(name);
                    cls3 = cls2;
                } catch (ClassNotFoundException e3) {
                    e3.printStackTrace();
                }
            }
            i++;
        }
        objArr[0] = Integer.valueOf(i2);
        objArr[1] = cls3;
        return objArr;
    }

    public String a(IWebview iWebview, io.dcloud.invocation.a aVar) {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        for (Field field : this.b.getFields()) {
            d.a(iWebview, aVar, this.c, field, false, stringBuffer, stringBuffer2);
        }
        return stringBuffer.toString();
    }

    public Object a(IWebview iWebview, String str, JSONArray jSONArray) throws Exception {
        return a(iWebview, this.f6713a, this.b, this.c, str, jSONArray);
    }

    public static final Object a(IWebview iWebview, io.dcloud.invocation.a aVar, Class cls, Object obj, String str, JSONArray jSONArray) throws Exception {
        Object[] objArrA = a(iWebview, aVar, jSONArray);
        Class[] clsArr = (Class[]) objArrA[0];
        Object[] objArr = (Object[]) objArrA[1];
        return a(obj, a(cls, str, clsArr, objArr), objArr, true);
    }

    public static final Object a(Object obj, Method method, Object[] objArr, boolean z) throws Exception {
        try {
        } catch (IllegalArgumentException e2) {
            if (z) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes != null && objArr != null && parameterTypes.length == objArr.length) {
                    for (int i = 0; i < parameterTypes.length; i++) {
                        if (objArr[i] != null) {
                            Class<?> cls = objArr[i].getClass();
                            Class<?> cls2 = parameterTypes[i];
                            if (cls.isAssignableFrom(cls2)) {
                                continue;
                            } else if (e(cls) && e(cls2)) {
                                objArr[i] = a(objArr[i], cls2);
                            } else {
                                throw e2;
                            }
                        }
                    }
                    return a(obj, method, objArr, false);
                }
            } else {
                throw e2;
            }
        }
        if (method.getReturnType().equals(Void.class)) {
            method.invoke(obj, objArr);
            return null;
        }
        return method.invoke(obj, objArr);
    }

    public static boolean b(Class cls, Class cls2) {
        return d(cls) && d(cls2);
    }

    public static boolean b(Class cls, Class[] clsArr, int i) {
        boolean zB = b(cls, clsArr[i]);
        if (zB) {
            clsArr[i] = cls;
        }
        return zB;
    }

    public static Method a(Class cls, String str, Class[] clsArr, Object[] objArr) {
        boolean zEquals = "getClass".equals(str);
        Class superclass = cls;
        while (true) {
            if (!zEquals && superclass == Object.class) {
                return b(cls, str, clsArr, objArr);
            }
            try {
                Method method = cls.getMethod(str, clsArr);
                if (method != null) {
                    method.setAccessible(true);
                    return method;
                }
                continue;
            } catch (NoSuchMethodException unused) {
            }
            superclass = superclass.getSuperclass();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean a(Class[] clsArr, Class[] clsArr2, Object[] objArr) {
        boolean z = false;
        for (int i = 0; i < clsArr.length; i++) {
            Class cls = clsArr2[i];
            Class cls2 = clsArr[i];
            z = cls == null ? !(e(cls2) || Boolean.TYPE.equals(cls2) || Boolean.class.equals(cls2) || Character.TYPE.equals(cls2) || Character.class.equals(cls2)) : cls2.isAssignableFrom(cls) || c(cls2, clsArr2, i) || a(cls2, clsArr2, i) || b(cls2, clsArr2, i) || (cls2.isArray() && cls2.isArray() && a(cls2, clsArr2, objArr, i));
            if (!z) {
                return z;
            }
        }
        return z;
    }

    public static boolean a(Class cls, Class[] clsArr, Object[] objArr, int i) {
        if (cls == clsArr[i]) {
            return true;
        }
        Object[] objArrB = b(cls);
        Object[] objArrB2 = b(clsArr[i]);
        int i2 = Integer.parseInt(String.valueOf(objArrB[0]));
        Class<?> cls2 = (Class) objArrB[1];
        int i3 = Integer.parseInt(String.valueOf(objArrB2[0]));
        Class cls3 = (Class) objArrB2[1];
        if (i2 == i3 && (a((Class) cls2, cls3) || b(cls2, cls3) || c(cls2, cls3) || cls3.isAssignableFrom(cls2))) {
            try {
                Object objA = a(objArr[i], cls2, i2, new a(i2));
                if (objA != null) {
                    clsArr[i] = cls;
                    objArr[i] = objA;
                    return true;
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public static Object a(Object obj, Class cls, int i, a aVar) throws Exception {
        if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            Object objNewInstance = null;
            for (int i2 = 0; i2 < length; i2++) {
                try {
                    Object objA = a(Array.get(obj, i2), cls, i - 1, aVar);
                    if (objNewInstance == null) {
                        aVar.a(i, length);
                        if (i == 1) {
                            objNewInstance = Array.newInstance((Class<?>) cls, length);
                        } else {
                            objNewInstance = Array.newInstance((Class<?>) cls, aVar.a(i));
                        }
                    }
                    Array.set(objNewInstance, i2, objA);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    Log.w("test", "i=" + i2 + ";mAw=" + i);
                }
            }
            return objNewInstance;
        }
        return a(obj, cls);
    }

    public static boolean a(Class cls, Class cls2) {
        return c(cls) && c(cls2);
    }

    public static boolean a(Class cls, Class[] clsArr, int i) {
        boolean zA = a(cls, clsArr[i]);
        if (zA) {
            clsArr[i] = cls;
        }
        return zA;
    }

    public static Object a(Object obj, Class cls) {
        if (cls == obj.getClass() || obj == null) {
            return obj;
        }
        if (cls != Byte.TYPE && cls != Byte.class) {
            if (cls != Integer.TYPE && cls != Integer.class) {
                if (cls != Boolean.TYPE && cls != Boolean.class) {
                    if (cls != Short.TYPE && cls != Short.class) {
                        if (cls != Long.TYPE && cls != Long.class) {
                            if (cls != Double.TYPE && cls != Double.class) {
                                if (cls != Float.TYPE && cls != Float.class) {
                                    if (cls != Character.TYPE && cls != Character.class) {
                                        return cls.cast(obj);
                                    }
                                    return String.valueOf(obj);
                                }
                                return Float.valueOf(Float.parseFloat(String.valueOf(obj)));
                            }
                            return Double.valueOf(Double.parseDouble(String.valueOf(obj)));
                        }
                        return Long.valueOf(Long.parseLong(String.valueOf(obj)));
                    }
                    return Short.valueOf(Short.parseShort(String.valueOf(obj)));
                }
                return Boolean.valueOf(Boolean.parseBoolean(String.valueOf(obj)));
            }
            return Integer.valueOf(Integer.parseInt(String.valueOf(obj)));
        }
        return Byte.valueOf(Byte.parseByte(String.valueOf(obj)));
    }

    public static Class a(Class cls) {
        if (cls == Integer.class) {
            return Integer.TYPE;
        }
        return cls == Boolean.class ? Boolean.TYPE : cls;
    }

    public static Object[] a(IWebview iWebview, io.dcloud.invocation.a aVar, Object obj) {
        Object[] objArr = new Object[2];
        if (obj instanceof JSONArray) {
            JSONArray jSONArray = (JSONArray) obj;
            int length = jSONArray.length();
            Object objNewInstance = null;
            for (int i = 0; i < length; i++) {
                Object[] objArrA = a(iWebview, aVar, jSONArray.opt(i));
                Class<?> cls = objArrA[1].getClass();
                if (i == 0) {
                    objNewInstance = Array.newInstance((Class<?>) a(objArrA[1].getClass()), length);
                }
                if (cls == Boolean.class) {
                    Array.setBoolean(objNewInstance, i, ((Boolean) objArrA[1]).booleanValue());
                } else if (cls == Byte.class) {
                    Array.setByte(objNewInstance, i, ((Byte) objArrA[1]).byteValue());
                } else if (cls == Double.class) {
                    Array.setDouble(objNewInstance, i, ((Double) objArrA[1]).doubleValue());
                } else if (cls == Float.class) {
                    Array.setFloat(objNewInstance, i, ((Float) objArrA[1]).floatValue());
                } else if (cls == Integer.class) {
                    Array.setInt(objNewInstance, i, ((Integer) objArrA[1]).intValue());
                } else if (cls == Long.class) {
                    Array.setLong(objNewInstance, i, ((Long) objArrA[1]).longValue());
                } else if (cls instanceof Object) {
                    Array.set(objNewInstance, i, objArrA[1]);
                }
            }
            if (objNewInstance != null) {
                objArr[0] = objNewInstance.getClass();
                objArr[1] = objNewInstance;
            }
        } else if (obj instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) obj;
            String strOptString = jSONObject.optString("type");
            Object objOpt = jSONObject.opt("value");
            if (strOptString.equals("object")) {
                c cVarA = aVar.a(iWebview, String.valueOf(objOpt));
                objArr[0] = cVarA.b;
                objArr[1] = cVarA.c;
            } else if (strOptString.equals("string")) {
                objArr[0] = String.class;
                objArr[1] = objOpt;
            } else if (strOptString.equals("number")) {
                if (objOpt instanceof Integer) {
                    objArr[0] = Integer.TYPE;
                    objArr[1] = Integer.valueOf(((Integer) objOpt).intValue());
                } else if (objOpt instanceof Double) {
                    objArr[0] = Double.class;
                    objArr[1] = (Double) objOpt;
                } else if (objOpt instanceof Float) {
                    objArr[0] = Float.class;
                    objArr[1] = (Float) objOpt;
                } else if (objOpt instanceof Long) {
                    objArr[0] = Long.class;
                    objArr[1] = (Long) objOpt;
                }
            } else if (strOptString.equals("boolean")) {
                objArr[0] = Boolean.class;
                objArr[1] = (Boolean) objOpt;
            } else if ("JSBObject".equals(jSONObject.optString("__TYPE__"))) {
                c cVarA2 = aVar.a(iWebview, jSONObject.optString(AbsoluteConst.JSON_KEY_UUID));
                objArr[0] = cVarA2.b;
                objArr[1] = cVarA2.c;
            }
        } else {
            objArr[0] = obj.getClass();
            objArr[1] = obj;
        }
        return objArr;
    }

    public static Object[] a(IWebview iWebview, io.dcloud.invocation.a aVar, JSONArray jSONArray) {
        int length;
        Object[] objArr = new Object[2];
        if (jSONArray != null) {
            try {
                length = jSONArray.length();
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        } else {
            length = 0;
        }
        Class[] clsArr = new Class[length];
        Object[] objArr2 = new Object[length];
        for (int i = 0; i < length; i++) {
            Object[] objArrA = a(iWebview, aVar, jSONArray.get(i));
            clsArr[i] = (Class) objArrA[0];
            objArr2[i] = objArrA[1];
        }
        objArr[0] = clsArr;
        objArr[1] = objArr2;
        return objArr;
    }

    public void a() {
        this.f6713a = null;
        this.b = null;
        this.c = null;
    }
}
