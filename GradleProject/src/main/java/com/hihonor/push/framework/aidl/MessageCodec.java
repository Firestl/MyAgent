package com.hihonor.push.framework.aidl;

import android.os.Bundle;
import android.os.Parcelable;
import com.hihonor.push.framework.aidl.annotation.Packed;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MessageCodec {
    public static final String BUNDLE_NEXT = "_next_item_";
    public static final String BUNDLE_VALUE = "_value_";
    public static final String PACKED_TYPE = "_packed_type_";
    public static final String TAG = "MessageCodec";
    public static final int VAL_ENTITY = 0;
    public static final int VAL_LIST = 1;
    public static final int VAL_NULL = -1;

    public static Bundle formMessageEntity(IMessageEntity iMessageEntity, Bundle bundle) {
        if (iMessageEntity != null && bundle != null) {
            for (Class<?> superclass = iMessageEntity.getClass(); superclass != null; superclass = superclass.getSuperclass()) {
                for (Field field : superclass.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Packed.class)) {
                        try {
                            formMessageField(iMessageEntity, field, bundle);
                        } catch (IllegalAccessException | IllegalArgumentException unused) {
                            String str = "encode, get value of the field exception, field name: " + field.getName();
                        }
                    }
                }
            }
        }
        return bundle;
    }

    public static void formMessageField(IMessageEntity iMessageEntity, Field field, Bundle bundle) {
        boolean zIsAccessible = field.isAccessible();
        field.setAccessible(true);
        put(field.getName(), field.get(iMessageEntity), bundle);
        field.setAccessible(zIsAccessible);
    }

    public static Object newInstance(Field field, Bundle bundle) {
        String name = field.getName();
        Object obj = bundle.get(name);
        if (obj instanceof Bundle) {
            try {
                Bundle bundle2 = (Bundle) obj;
                int i = bundle2.getInt(PACKED_TYPE, -1);
                if (i == 1) {
                    return parseGenericType(field.getGenericType(), bundle2);
                }
                if (i == 0) {
                    return parseMessageEntity((Bundle) obj, (IMessageEntity) field.getType().newInstance());
                }
            } catch (Exception unused) {
                String str = "decode, read value of the field exception, field name: " + name;
                return null;
            }
        }
        return obj;
    }

    public static List<Object> parseGenericType(Type type, Bundle bundle) throws InstantiationException {
        ArrayList arrayList = new ArrayList();
        while (true) {
            bundle = bundle.getBundle("_next_item_");
            if (bundle == null) {
                return arrayList;
            }
            Object messageEntity = bundle.get("_value_");
            if (!messageEntity.getClass().isPrimitive() && !(messageEntity instanceof Serializable)) {
                if (messageEntity instanceof Bundle) {
                    Bundle bundle2 = (Bundle) messageEntity;
                    int i = bundle2.getInt(PACKED_TYPE, -1);
                    if (i == 1) {
                        throw new InstantiationException("Nested List can not be supported");
                    }
                    if (i != 0) {
                        throw new InstantiationException("Unknown type can not be supported");
                    }
                    messageEntity = parseMessageEntity(bundle2, (IMessageEntity) ((Class) ((ParameterizedType) type).getActualTypeArguments()[0]).newInstance());
                } else {
                    continue;
                }
            }
            arrayList.add(messageEntity);
        }
    }

    public static IMessageEntity parseMessageEntity(Bundle bundle, IMessageEntity iMessageEntity) {
        if (bundle != null && iMessageEntity != null) {
            bundle.setClassLoader(iMessageEntity.getClass().getClassLoader());
            for (Class<?> superclass = iMessageEntity.getClass(); superclass != null; superclass = superclass.getSuperclass()) {
                for (Field field : superclass.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Packed.class)) {
                        try {
                            parseMessageField(iMessageEntity, field, bundle);
                        } catch (IllegalAccessException | IllegalArgumentException unused) {
                            String str = "decode, set value of the field exception, field name:" + field.getName();
                        }
                    }
                }
            }
        }
        return iMessageEntity;
    }

    public static void parseMessageField(IMessageEntity iMessageEntity, Field field, Bundle bundle) throws IllegalAccessException {
        Object objNewInstance = newInstance(field, bundle);
        if (objNewInstance != null) {
            boolean zIsAccessible = field.isAccessible();
            field.setAccessible(true);
            field.set(iMessageEntity, objNewInstance);
            field.setAccessible(zIsAccessible);
        }
    }

    public static void put(String str, Object obj, Bundle bundle) {
        if (obj == null || putGenericType(str, obj, bundle)) {
            return;
        }
        if (obj instanceof CharSequence) {
            bundle.putCharSequence(str, (CharSequence) obj);
            return;
        }
        if (obj instanceof Parcelable) {
            bundle.putParcelable(str, (Parcelable) obj);
            return;
        }
        if (obj instanceof byte[]) {
            bundle.putByteArray(str, (byte[]) obj);
            return;
        }
        if (obj instanceof List) {
            putList(str, (List) obj, bundle);
            return;
        }
        if (obj instanceof Serializable) {
            bundle.putSerializable(str, (Serializable) obj);
            return;
        }
        if (obj instanceof IMessageEntity) {
            Bundle bundleFormMessageEntity = formMessageEntity((IMessageEntity) obj, new Bundle());
            bundleFormMessageEntity.putInt(PACKED_TYPE, 0);
            bundle.putBundle(str, bundleFormMessageEntity);
        } else {
            String str2 = "cannot support type, " + str;
        }
    }

    public static boolean putGenericType(String str, Object obj, Bundle bundle) {
        if (obj instanceof String) {
            bundle.putString(str, (String) obj);
            return true;
        }
        if (obj instanceof Integer) {
            bundle.putInt(str, ((Integer) obj).intValue());
            return true;
        }
        if (obj instanceof Short) {
            bundle.putShort(str, ((Short) obj).shortValue());
            return true;
        }
        if (obj instanceof Long) {
            bundle.putLong(str, ((Long) obj).longValue());
            return true;
        }
        if (obj instanceof Float) {
            bundle.putFloat(str, ((Float) obj).floatValue());
            return true;
        }
        if (obj instanceof Double) {
            bundle.putDouble(str, ((Double) obj).doubleValue());
            return true;
        }
        if (!(obj instanceof Boolean)) {
            return false;
        }
        bundle.putBoolean(str, ((Boolean) obj).booleanValue());
        return true;
    }

    public static void putList(String str, List list, Bundle bundle) {
        Bundle bundlePutNext = null;
        for (Object obj : list) {
            if (bundlePutNext == null) {
                bundlePutNext = new Bundle();
                bundle.putBundle(str, bundlePutNext);
                bundlePutNext.putInt(PACKED_TYPE, 1);
            }
            bundlePutNext = putNext("_value_", bundlePutNext, obj);
        }
    }

    public static Bundle putNext(String str, Bundle bundle, Object obj) {
        Bundle bundle2 = new Bundle();
        put(str, obj, bundle2);
        bundle.putBundle("_next_item_", bundle2);
        return bundle2;
    }
}
