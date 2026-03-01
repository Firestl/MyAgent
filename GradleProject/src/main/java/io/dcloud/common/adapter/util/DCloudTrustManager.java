package io.dcloud.common.adapter.util;

import android.text.TextUtils;
import com.huawei.secure.android.common.ssl.SSLUtil;
import io.dcloud.application.DCLoudApplicationImpl;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.PdrUtil;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;

/* JADX INFO: loaded from: classes2.dex */
public class DCloudTrustManager {
    public static SecureRandom createSecureRandom() {
        return new SecureRandom();
    }

    public static X509HostnameVerifier getHostnameVerifier(boolean z) {
        return (z || !(PdrUtil.isEquals(BaseInfo.untrustedca, "refuse") || PdrUtil.isEquals(BaseInfo.untrustedca, "warning"))) ? SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER : SSLSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;
    }

    public static javax.net.ssl.SSLSocketFactory getSSLSocketFactory() throws NoSuchAlgorithmException, KeyManagementException {
        return getSSLSocketFactory(SSLUtil.f2970e);
    }

    public static javax.net.ssl.SSLSocketFactory getSSLSocketFactory(String str) throws NoSuchAlgorithmException, KeyManagementException {
        if (DCLoudApplicationImpl.self().getContext() != null) {
            Object objNewInstance = PlatformUtil.newInstance(DCLoudApplicationImpl.self().getContext().getPackageName() + ".CustomTrustMgr", null, null);
            if (objNewInstance != null) {
                if (TextUtils.isEmpty(str)) {
                    str = SSLUtil.f2970e;
                }
                SSLContext sSLContext = SSLContext.getInstance(str);
                sSLContext.init(null, new TrustManager[]{(TrustManager) objNewInstance}, createSecureRandom());
                return sSLContext.getSocketFactory();
            }
        }
        return null;
    }
}
