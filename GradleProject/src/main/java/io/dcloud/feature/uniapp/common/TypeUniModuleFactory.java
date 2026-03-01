package io.dcloud.feature.uniapp.common;

import com.taobao.weex.WXEnvironment;
import com.taobao.weex.bridge.Invoker;
import com.taobao.weex.bridge.MethodInvoker;
import com.taobao.weex.utils.WXLogUtils;
import io.dcloud.feature.uniapp.annotation.UniJSMethod;
import io.dcloud.feature.uniapp.bridge.UniModuleFactory;
import io.dcloud.feature.uniapp.common.UniModule;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class TypeUniModuleFactory<T extends UniModule> implements UniModuleFactory<T> {
    public static final String TAG = "TypeModuleFactory";
    public Class<T> mClazz;
    public Map<String, Invoker> mMethodMap;

    public TypeUniModuleFactory(Class<T> cls) {
        this.mClazz = cls;
    }

    private void generateMethodMap() {
        if (WXEnvironment.isApkDebugable()) {
            WXLogUtils.d("TypeModuleFactory", "extractMethodNames:" + this.mClazz.getSimpleName());
        }
        HashMap map = new HashMap();
        try {
            for (Method method : this.mClazz.getMethods()) {
                Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
                int length = declaredAnnotations.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    Annotation annotation = declaredAnnotations[i];
                    if (annotation != null) {
                        if (!(annotation instanceof UniJSMethod)) {
                            if (annotation instanceof UniModuleAnno) {
                                map.put(method.getName(), new MethodInvoker(method, ((UniModuleAnno) annotation).runOnUIThread()));
                                break;
                            }
                        } else {
                            UniJSMethod uniJSMethod = (UniJSMethod) annotation;
                            map.put("_".equals(uniJSMethod.alias()) ? method.getName() : uniJSMethod.alias(), new MethodInvoker(method, uniJSMethod.uiThread()));
                        }
                    }
                    i++;
                }
            }
        } catch (Throwable th) {
            WXLogUtils.e("[WXModuleManager] extractMethodNames:", th);
        }
        this.mMethodMap = map;
    }

    @Override // com.taobao.weex.bridge.JavascriptInvokable
    public Invoker getMethodInvoker(String str) {
        if (this.mMethodMap == null) {
            generateMethodMap();
        }
        return this.mMethodMap.get(str);
    }

    @Override // com.taobao.weex.bridge.JavascriptInvokable
    public String[] getMethods() {
        if (this.mMethodMap == null) {
            generateMethodMap();
        }
        Set<String> setKeySet = this.mMethodMap.keySet();
        return (String[]) setKeySet.toArray(new String[setKeySet.size()]);
    }

    @Override // com.taobao.weex.bridge.ModuleFactory
    public T buildInstance() throws IllegalAccessException, InstantiationException {
        return this.mClazz.newInstance();
    }
}
