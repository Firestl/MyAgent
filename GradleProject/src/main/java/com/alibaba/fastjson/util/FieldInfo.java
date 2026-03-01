package com.alibaba.fastjson.util;

import com.taobao.weex.el.parse.Operators;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* JADX INFO: loaded from: classes.dex */
public class FieldInfo implements Comparable<FieldInfo> {
    public final Class<?> declaringClass;
    public final Field field;
    public final Class<?> fieldClass;
    public final Type fieldType;
    public boolean getOnly;
    public final Method method;
    public final String name;
    public int ordinal;

    public FieldInfo(String str, Class<?> cls, Class<?> cls2, Type type, Field field, int i, int i2) {
        this.ordinal = 0;
        this.getOnly = false;
        this.name = str;
        this.declaringClass = cls;
        this.fieldClass = cls2;
        this.fieldType = type;
        this.method = null;
        this.field = field;
        this.ordinal = i;
        if (field != null) {
            TypeUtils.setAccessible(field);
        }
    }

    public static Type getFieldType(Class<?> cls, Type type, Type type2) {
        if (cls != null && type != null) {
            if (type2 instanceof GenericArrayType) {
                Type genericComponentType = ((GenericArrayType) type2).getGenericComponentType();
                Type fieldType = getFieldType(cls, type, genericComponentType);
                return genericComponentType != fieldType ? Array.newInstance(TypeUtils.getClass(fieldType), 0).getClass() : type2;
            }
            if (!TypeUtils.isGenericParamType(type)) {
                return type2;
            }
            if (type2 instanceof TypeVariable) {
                ParameterizedType parameterizedType = (ParameterizedType) TypeUtils.getGenericParamType(type);
                Class<?> cls2 = TypeUtils.getClass(parameterizedType);
                TypeVariable typeVariable = (TypeVariable) type2;
                for (int i = 0; i < cls2.getTypeParameters().length; i++) {
                    if (cls2.getTypeParameters()[i].getName().equals(typeVariable.getName())) {
                        return parameterizedType.getActualTypeArguments()[i];
                    }
                }
            }
            if (type2 instanceof ParameterizedType) {
                ParameterizedType parameterizedType2 = (ParameterizedType) type2;
                Type[] actualTypeArguments = parameterizedType2.getActualTypeArguments();
                boolean z = false;
                for (int i2 = 0; i2 < actualTypeArguments.length; i2++) {
                    Type type3 = actualTypeArguments[i2];
                    if (type3 instanceof TypeVariable) {
                        TypeVariable typeVariable2 = (TypeVariable) type3;
                        if (type instanceof ParameterizedType) {
                            ParameterizedType parameterizedType3 = (ParameterizedType) type;
                            for (int i3 = 0; i3 < cls.getTypeParameters().length; i3++) {
                                if (cls.getTypeParameters()[i3].getName().equals(typeVariable2.getName())) {
                                    actualTypeArguments[i2] = parameterizedType3.getActualTypeArguments()[i3];
                                    z = true;
                                }
                            }
                        }
                    }
                }
                if (z) {
                    return new ParameterizedTypeImpl(actualTypeArguments, parameterizedType2.getOwnerType(), parameterizedType2.getRawType());
                }
            }
        }
        return type2;
    }

    public static Type getInheritGenericType(Class<?> cls, TypeVariable<?> typeVariable) {
        GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
        while (true) {
            Type genericSuperclass = cls.getGenericSuperclass();
            if (genericSuperclass == null) {
                return null;
            }
            if (genericSuperclass instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
                if (parameterizedType.getRawType() == genericDeclaration) {
                    TypeVariable<?>[] typeParameters = genericDeclaration.getTypeParameters();
                    Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                    for (int i = 0; i < typeParameters.length; i++) {
                        if (typeParameters[i] == typeVariable) {
                            return actualTypeArguments[i];
                        }
                    }
                    return null;
                }
            }
            Class<?> cls2 = TypeUtils.getClass(genericSuperclass);
            if (genericSuperclass == null) {
                return null;
            }
            cls = cls2;
        }
    }

    public String gerQualifiedName() {
        Member member = getMember();
        return member.getDeclaringClass().getName() + Operators.DOT_STR + member.getName();
    }

    public Object get(Object obj) throws IllegalAccessException, InvocationTargetException {
        Method method = this.method;
        return method != null ? method.invoke(obj, new Object[0]) : this.field.get(obj);
    }

    public <T extends Annotation> T getAnnotation(Class<T> cls) {
        Field field;
        Method method = this.method;
        T t = method != null ? (T) method.getAnnotation(cls) : null;
        return (t != null || (field = this.field) == null) ? t : (T) field.getAnnotation(cls);
    }

    public Class<?> getDeclaringClass() {
        return this.declaringClass;
    }

    public Field getField() {
        return this.field;
    }

    public Class<?> getFieldClass() {
        return this.fieldClass;
    }

    public Member getMember() {
        Method method = this.method;
        return method != null ? method : this.field;
    }

    public Method getMethod() {
        return this.method;
    }

    public String getName() {
        return this.name;
    }

    public boolean isGetOnly() {
        return this.getOnly;
    }

    public void set(Object obj, Object obj2) throws IllegalAccessException, InvocationTargetException {
        Method method = this.method;
        if (method != null) {
            method.invoke(obj, obj2);
        } else {
            this.field.set(obj, obj2);
        }
    }

    public void setAccessible(boolean z) throws SecurityException {
        Method method = this.method;
        if (method != null) {
            TypeUtils.setAccessible(method);
        } else {
            TypeUtils.setAccessible(this.field);
        }
    }

    public String toString() {
        return this.name;
    }

    @Override // java.lang.Comparable
    public int compareTo(FieldInfo fieldInfo) {
        int i = this.ordinal;
        int i2 = fieldInfo.ordinal;
        if (i < i2) {
            return -1;
        }
        if (i > i2) {
            return 1;
        }
        return this.name.compareTo(fieldInfo.name);
    }

    public FieldInfo(String str, Method method, Field field) {
        this(str, method, field, (Class<?>) null, (Type) null);
    }

    public FieldInfo(String str, Method method, Field field, int i, int i2) {
        this(str, method, field, (Class<?>) null, (Type) null, i, i2);
    }

    public FieldInfo(String str, Method method, Field field, Class<?> cls, Type type) {
        this(str, method, field, cls, type, 0, 0);
    }

    public FieldInfo(String str, Method method, Field field, Class<?> cls, Type type, int i, int i2) {
        Class<?> type2;
        Type type3;
        Type inheritGenericType;
        this.ordinal = 0;
        this.getOnly = false;
        this.name = str;
        this.method = method;
        this.field = field;
        this.ordinal = i;
        if (method != null) {
            TypeUtils.setAccessible(method);
        }
        if (field != null) {
            TypeUtils.setAccessible(field);
        }
        if (method != null) {
            if (method.getParameterTypes().length == 1) {
                type2 = method.getParameterTypes()[0];
                type3 = method.getGenericParameterTypes()[0];
            } else {
                type2 = method.getReturnType();
                Type genericReturnType = method.getGenericReturnType();
                this.getOnly = true;
                type3 = genericReturnType;
            }
            this.declaringClass = method.getDeclaringClass();
        } else {
            type2 = field.getType();
            Type genericType = field.getGenericType();
            this.declaringClass = field.getDeclaringClass();
            type3 = genericType;
        }
        if (cls != null && type2 == Object.class && (type3 instanceof TypeVariable) && (inheritGenericType = getInheritGenericType(cls, (TypeVariable) type3)) != null) {
            this.fieldClass = TypeUtils.getClass(inheritGenericType);
            this.fieldType = inheritGenericType;
            return;
        }
        Type fieldType = getFieldType(cls, type, type3);
        if (fieldType != type3 && ((fieldType instanceof ParameterizedType) || (fieldType instanceof Class))) {
            type2 = TypeUtils.getClass(fieldType);
        }
        this.fieldType = fieldType;
        this.fieldClass = type2;
    }

    public Type getFieldType() {
        return this.fieldType;
    }
}
