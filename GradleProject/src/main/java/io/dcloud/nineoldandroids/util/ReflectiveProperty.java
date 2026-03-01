package io.dcloud.nineoldandroids.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes3.dex */
public class ReflectiveProperty<T, V> extends Property<T, V> {
    public static final String PREFIX_GET = "get";
    public static final String PREFIX_IS = "is";
    public static final String PREFIX_SET = "set";
    public Field mField;
    public Method mGetter;
    public Method mSetter;

    public ReflectiveProperty(Class<T> cls, Class<V> cls2, String str) {
        super(cls2, str);
        char upperCase = Character.toUpperCase(str.charAt(0));
        String str2 = String.valueOf(upperCase) + str.substring(1);
        String str3 = "get" + str2;
        try {
            try {
                this.mGetter = cls.getMethod(str3, null);
            } catch (NoSuchMethodException unused) {
                Method declaredMethod = cls.getDeclaredMethod(str3, null);
                this.mGetter = declaredMethod;
                declaredMethod.setAccessible(true);
            }
        } catch (NoSuchMethodException unused2) {
            String str4 = "is" + str2;
            try {
                try {
                    try {
                        this.mGetter = cls.getMethod(str4, null);
                    } catch (NoSuchMethodException unused3) {
                        Method declaredMethod2 = cls.getDeclaredMethod(str4, null);
                        this.mGetter = declaredMethod2;
                        declaredMethod2.setAccessible(true);
                    }
                } catch (NoSuchFieldException unused4) {
                    throw new NoSuchPropertyException("No accessor method or field found for property with name " + str);
                }
            } catch (NoSuchMethodException unused5) {
                Field field = cls.getField(str);
                this.mField = field;
                Class<?> type = field.getType();
                if (typesMatch(cls2, type)) {
                    return;
                }
                throw new NoSuchPropertyException("Underlying type (" + type + ") does not match Property type (" + cls2 + ")");
            }
        }
        Class<?> returnType = this.mGetter.getReturnType();
        if (typesMatch(cls2, returnType)) {
            try {
                Method declaredMethod3 = cls.getDeclaredMethod(PREFIX_SET + str2, returnType);
                this.mSetter = declaredMethod3;
                declaredMethod3.setAccessible(true);
                return;
            } catch (NoSuchMethodException unused6) {
                return;
            }
        }
        throw new NoSuchPropertyException("Underlying type (" + returnType + ") does not match Property type (" + cls2 + ")");
    }

    private boolean typesMatch(Class<V> cls, Class cls2) {
        if (cls2 != cls) {
            return cls2.isPrimitive() && ((cls2 == Float.TYPE && cls == Float.class) || ((cls2 == Integer.TYPE && cls == Integer.class) || ((cls2 == Boolean.TYPE && cls == Boolean.class) || ((cls2 == Long.TYPE && cls == Long.class) || ((cls2 == Double.TYPE && cls == Double.class) || ((cls2 == Short.TYPE && cls == Short.class) || ((cls2 == Byte.TYPE && cls == Byte.class) || (cls2 == Character.TYPE && cls == Character.class))))))));
        }
        return true;
    }

    @Override // io.dcloud.nineoldandroids.util.Property
    public V get(T t) {
        Method method = this.mGetter;
        if (method != null) {
            try {
                return (V) method.invoke(t, null);
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2.getCause());
            }
        }
        Field field = this.mField;
        if (field == null) {
            throw new AssertionError();
        }
        try {
            return (V) field.get(t);
        } catch (IllegalAccessException unused2) {
            throw new AssertionError();
        }
    }

    @Override // io.dcloud.nineoldandroids.util.Property
    public boolean isReadOnly() {
        return this.mSetter == null && this.mField == null;
    }

    @Override // io.dcloud.nineoldandroids.util.Property
    public void set(T t, V v) {
        Method method = this.mSetter;
        if (method != null) {
            try {
                method.invoke(t, v);
                return;
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2.getCause());
            }
        }
        Field field = this.mField;
        if (field != null) {
            try {
                field.set(t, v);
            } catch (IllegalAccessException unused2) {
                throw new AssertionError();
            }
        } else {
            throw new UnsupportedOperationException("Property " + getName() + " is read-only");
        }
    }
}
