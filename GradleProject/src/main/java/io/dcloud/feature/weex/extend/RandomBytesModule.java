package io.dcloud.feature.weex.extend;

import android.util.Base64;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes3.dex */
public class RandomBytesModule extends WXModule {
    private String getRandomBytes(int i) {
        byte[] bArr = new byte[i];
        new SecureRandom().nextBytes(bArr);
        return Base64.encodeToString(bArr, 2);
    }

    @JSMethod(uiThread = false)
    public String getRandomValues(int i) {
        return getRandomBytes(i);
    }
}
