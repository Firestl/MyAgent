package io.dcloud.common.util.emulator;

import com.huawei.hms.framework.network.grs.local.model.CountryCodeBean;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes2.dex */
public class CommandUtil {

    public static class SingletonHolder {
        public static final CommandUtil INSTANCE = new CommandUtil();
    }

    public static final CommandUtil getSingleInstance() {
        return SingletonHolder.INSTANCE;
    }

    public String getProperty(String str) throws IllegalAccessException, InvocationTargetException {
        try {
            Object objInvoke = Class.forName(CountryCodeBean.ANDRIOD_SYSTEMPROP).getMethod("get", String.class).invoke(null, str);
            if (objInvoke != null) {
                return (String) objInvoke;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public CommandUtil() {
    }
}
