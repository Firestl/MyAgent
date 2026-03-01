package io.dcloud.feature.weex.config;

import android.text.TextUtils;
import com.huawei.secure.android.common.ssl.SSLUtil;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.http.CertDTO;
import io.dcloud.feature.weex.config.MimeInfoParser;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.HashMap;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/* JADX INFO: loaded from: classes3.dex */
public class UserCustomTrustManager {
    public static HashMap<AndroidTlsConfig, SSLSocketFactory> cacheSSLFactory = new HashMap<>();
    public static HashMap<CertDTO, SSLSocketFactory> cacheCertSSLFactory = new HashMap<>();

    public static SSLSocketFactory getSSLSocketFactory(AndroidTlsConfig androidTlsConfig, WXSDKInstance wXSDKInstance) {
        SSLSocketFactory sSLSocketFactory;
        if (androidTlsConfig == null) {
            return null;
        }
        if (cacheSSLFactory.containsKey(androidTlsConfig) && (sSLSocketFactory = cacheSSLFactory.get(androidTlsConfig)) != null) {
            return sSLSocketFactory;
        }
        if (wXSDKInstance == null) {
            return null;
        }
        try {
            SSLContext sSLContext = SSLContext.getInstance(SSLUtil.d);
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            if ((!TextUtils.isEmpty(androidTlsConfig.getKeystore())) && (true ^ TextUtils.isEmpty(androidTlsConfig.getStorePass()))) {
                MimeInfoParser.MimeInfo mimeInfoObtainMimeInfo = MimeInfoParser.getInstance().obtainMimeInfo(androidTlsConfig.getKeystore());
                if (mimeInfoObtainMimeInfo == null) {
                    return null;
                }
                keyStore.load(mimeInfoObtainMimeInfo.getDataBytes(wXSDKInstance), androidTlsConfig.getStorePass().toCharArray());
                keyManagerFactory.init(keyStore, androidTlsConfig.getStorePass().toCharArray());
            } else {
                keyManagerFactory = null;
            }
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore2 = KeyStore.getInstance("PKCS12");
            for (int i = 0; i < androidTlsConfig.getCa().length; i++) {
                MimeInfoParser.MimeInfo mimeInfoObtainMimeInfo2 = MimeInfoParser.getInstance().obtainMimeInfo(androidTlsConfig.getCa()[i]);
                if (mimeInfoObtainMimeInfo2 != null) {
                    InputStream dataBytes = mimeInfoObtainMimeInfo2.getDataBytes(wXSDKInstance);
                    keyStore2.load(null);
                    keyStore2.setCertificateEntry(Integer.toString(i), certificateFactory.generateCertificate(dataBytes));
                }
            }
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore2);
            if (keyManagerFactory == null) {
                sSLContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            } else {
                sSLContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());
            }
            SSLSocketFactory socketFactory = sSLContext.getSocketFactory();
            if (cacheSSLFactory.size() > 3) {
                cacheSSLFactory.clear();
            }
            cacheSSLFactory.put(androidTlsConfig, socketFactory);
            return socketFactory;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        } catch (KeyManagementException e3) {
            e3.printStackTrace();
            return null;
        } catch (KeyStoreException e4) {
            e4.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e5) {
            e5.printStackTrace();
            return null;
        } catch (UnrecoverableKeyException e6) {
            e6.printStackTrace();
            return null;
        } catch (CertificateException e7) {
            e7.printStackTrace();
            return null;
        }
    }

    public static SSLSocketFactory getSSLSocketFactory(CertDTO certDTO, WXSDKInstance wXSDKInstance) {
        InputStream filePathStream;
        InputStream filePathStream2;
        SSLSocketFactory sSLSocketFactory;
        if (certDTO == null) {
            return null;
        }
        if (cacheCertSSLFactory.containsKey(certDTO) && (sSLSocketFactory = cacheCertSSLFactory.get(certDTO)) != null) {
            return sSLSocketFactory;
        }
        if (wXSDKInstance == null) {
            return null;
        }
        try {
            SSLContext sSLContext = SSLContext.getInstance(SSLUtil.d);
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            if ((!TextUtils.isEmpty(certDTO.client)) && (true ^ TextUtils.isEmpty(certDTO.clientPassword))) {
                MimeInfoParser.MimeInfo mimeInfoObtainMimeInfo = MimeInfoParser.getInstance().obtainMimeInfo(certDTO.client);
                if (mimeInfoObtainMimeInfo != null) {
                    filePathStream2 = mimeInfoObtainMimeInfo.getDataBytes(wXSDKInstance);
                } else {
                    filePathStream2 = MimeInfoParser.getFilePathStream(wXSDKInstance, certDTO.client);
                }
                if (filePathStream2 != null) {
                    keyStore.load(filePathStream2, certDTO.getClientPassword().toCharArray());
                    keyManagerFactory.init(keyStore, certDTO.getClientPassword().toCharArray());
                }
            } else {
                keyManagerFactory = null;
            }
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore2 = KeyStore.getInstance("PKCS12");
            for (int i = 0; i < certDTO.getServer().length; i++) {
                MimeInfoParser.MimeInfo mimeInfoObtainMimeInfo2 = MimeInfoParser.getInstance().obtainMimeInfo(certDTO.getServer()[i]);
                if (mimeInfoObtainMimeInfo2 != null) {
                    filePathStream = mimeInfoObtainMimeInfo2.getDataBytes(wXSDKInstance);
                } else {
                    filePathStream = MimeInfoParser.getFilePathStream(wXSDKInstance, certDTO.getServer()[i]);
                }
                if (filePathStream != null) {
                    keyStore2.load(null);
                    keyStore2.setCertificateEntry(Integer.toString(i), certificateFactory.generateCertificate(filePathStream));
                }
            }
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore2);
            if (keyManagerFactory == null) {
                sSLContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            } else {
                sSLContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());
            }
            SSLSocketFactory socketFactory = sSLContext.getSocketFactory();
            if (cacheCertSSLFactory.size() > 3) {
                cacheCertSSLFactory.clear();
            }
            cacheCertSSLFactory.put(certDTO, socketFactory);
            return socketFactory;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        } catch (KeyManagementException e3) {
            e3.printStackTrace();
            return null;
        } catch (KeyStoreException e4) {
            e4.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e5) {
            e5.printStackTrace();
            return null;
        } catch (UnrecoverableKeyException e6) {
            e6.printStackTrace();
            return null;
        } catch (CertificateException e7) {
            e7.printStackTrace();
            return null;
        }
    }
}
