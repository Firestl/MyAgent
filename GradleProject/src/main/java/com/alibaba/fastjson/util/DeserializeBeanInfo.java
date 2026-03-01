package com.alibaba.fastjson.util;

import com.alibaba.fastjson.annotation.JSONCreator;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DeserializeBeanInfo {
    public Constructor<?> creatorConstructor;
    public Constructor<?> defaultConstructor;
    public Method factoryMethod;
    public int parserFeatures;
    public final List<FieldInfo> fieldList = new ArrayList();
    public final List<FieldInfo> sortedFieldList = new ArrayList();

    public DeserializeBeanInfo(Class<?> cls) {
        this.parserFeatures = 0;
        this.parserFeatures = TypeUtils.getParserFeatures(cls);
    }

    /* JADX WARN: Removed duplicated region for block: B:115:0x02b8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.alibaba.fastjson.util.DeserializeBeanInfo computeSetters(java.lang.Class<?> r19, java.lang.reflect.Type r20) {
        /*
            Method dump skipped, instruction units count: 974
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.util.DeserializeBeanInfo.computeSetters(java.lang.Class, java.lang.reflect.Type):com.alibaba.fastjson.util.DeserializeBeanInfo");
    }

    public boolean add(FieldInfo fieldInfo) {
        for (FieldInfo fieldInfo2 : this.fieldList) {
            if (fieldInfo2.getName().equals(fieldInfo.getName()) && (!fieldInfo2.isGetOnly() || fieldInfo.isGetOnly())) {
                return false;
            }
        }
        this.fieldList.add(fieldInfo);
        this.sortedFieldList.add(fieldInfo);
        Collections.sort(this.sortedFieldList);
        return true;
    }

    public Constructor<?> getCreatorConstructor() {
        return this.creatorConstructor;
    }

    public Constructor<?> getDefaultConstructor() {
        return this.defaultConstructor;
    }

    public Method getFactoryMethod() {
        return this.factoryMethod;
    }

    public List<FieldInfo> getFieldList() {
        return this.fieldList;
    }

    public int getParserFeatures() {
        return this.parserFeatures;
    }

    public List<FieldInfo> getSortedFieldList() {
        return this.sortedFieldList;
    }

    public void setCreatorConstructor(Constructor<?> constructor) {
        this.creatorConstructor = constructor;
    }

    public void setDefaultConstructor(Constructor<?> constructor) {
        this.defaultConstructor = constructor;
    }

    public void setFactoryMethod(Method method) {
        this.factoryMethod = method;
    }

    public static Constructor<?> getCreatorConstructor(Class<?> cls) {
        for (Constructor<?> constructor : cls.getDeclaredConstructors()) {
            if (((JSONCreator) constructor.getAnnotation(JSONCreator.class)) != null) {
                return constructor;
            }
        }
        return null;
    }

    public static Constructor<?> getDefaultConstructor(Class<?> cls) {
        Constructor<?> constructor = null;
        if (Modifier.isAbstract(cls.getModifiers())) {
            return null;
        }
        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
        int length = declaredConstructors.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            Constructor<?> constructor2 = declaredConstructors[i];
            if (constructor2.getParameterTypes().length == 0) {
                constructor = constructor2;
                break;
            }
            i++;
        }
        if (constructor != null || !cls.isMemberClass() || Modifier.isStatic(cls.getModifiers())) {
            return constructor;
        }
        for (Constructor<?> constructor3 : cls.getDeclaredConstructors()) {
            if (constructor3.getParameterTypes().length == 1 && constructor3.getParameterTypes()[0].equals(cls.getDeclaringClass())) {
                return constructor3;
            }
        }
        return constructor;
    }

    public static Method getFactoryMethod(Class<?> cls) {
        for (Method method : cls.getDeclaredMethods()) {
            if (Modifier.isStatic(method.getModifiers()) && cls.isAssignableFrom(method.getReturnType()) && ((JSONCreator) method.getAnnotation(JSONCreator.class)) != null) {
                return method;
            }
        }
        return null;
    }
}
