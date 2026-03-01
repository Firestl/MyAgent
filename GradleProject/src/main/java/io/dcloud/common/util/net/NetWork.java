package io.dcloud.common.util.net;

import android.text.TextUtils;
import com.bumptech.glide.load.engine.GlideException;
import com.lzy.okgo.model.HttpHeaders;
import com.taobao.weex.el.parse.Operators;
import com.umeng.ccg.c;
import io.dcloud.common.DHInterface.IReqListener;
import io.dcloud.common.DHInterface.IResponseListener;
import io.dcloud.common.adapter.util.DCloudTrustManager;
import io.dcloud.common.util.NetTool;
import io.dcloud.common.util.PdrUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: classes2.dex */
public class NetWork implements Runnable {
    public static long AUTO_RECONNECTTIME = 30000;
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String CONTENT_TYPE_COMMON = "text/plain;charset=utf-8";
    public static final String CONTENT_TYPE_UPLOAD = "application/x-www-form-urlencoded";
    public static final String DEFALUT_CHARSET = "utf-8";
    public static final String PARAM_CHARSET = ";charset=";
    public static final int WORK_COMMON = 3;
    public static final int WORK_DOWNLOAD = 2;
    public static final int WORK_UPLOAD = 1;
    public boolean isAbort;
    public int mPriority;
    public IReqListener mReqListener;
    public HttpURLConnection mRequest;
    public RequestData mRequestData;
    public InputStream mResponseInput;
    public IResponseListener mResponseListener;
    public String mResponseText;
    public int mWorkType;
    public NetWorkLoop mNetWorkLoop = null;
    public int mTimes = 1;
    public int MAX_TIMES = 3;
    public long mRetryIntervalTime = AUTO_RECONNECTTIME;
    public Map<String, String> mHeaderList = new HashMap();
    public String mMainBoundry = getBoundry();

    public NetWork(int i, RequestData requestData, IReqListener iReqListener, IResponseListener iResponseListener) {
        this.mWorkType = i;
        this.mRequestData = requestData;
        this.mReqListener = iReqListener;
        this.mResponseListener = iResponseListener;
    }

    public static String getBoundry() {
        StringBuffer stringBuffer = new StringBuffer("------");
        for (int i = 1; i < 7; i++) {
            long jCurrentTimeMillis = System.currentTimeMillis() + ((long) i);
            long j = jCurrentTimeMillis % 3;
            if (j == 0) {
                stringBuffer.append(((char) jCurrentTimeMillis) % '\t');
            } else if (j == 1) {
                stringBuffer.append((char) ((jCurrentTimeMillis % 26) + 65));
            } else {
                stringBuffer.append((char) ((jCurrentTimeMillis % 26) + 97));
            }
        }
        return stringBuffer.toString();
    }

    private String getCharset(String str) {
        if (str != null) {
            String strReplace = str.replace(Operators.SPACE_STR, "");
            if (strReplace.contains(PARAM_CHARSET)) {
                return strReplace.substring(strReplace.indexOf(PARAM_CHARSET) + 9);
            }
        }
        return null;
    }

    private void setHeadersAndValues(Map<String, List<String>> map) {
        if (map == null) {
            return;
        }
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            if (!PdrUtil.isEmpty(entry.getValue())) {
                String str = "";
                for (int i = 0; i < entry.getValue().size(); i++) {
                    str = i == 0 ? entry.getValue().get(i) : str + GlideException.IndentedAppendable.INDENT + entry.getValue().get(i);
                }
                if (!PdrUtil.isEmpty(entry.getKey())) {
                    this.mHeaderList.put(entry.getKey(), str);
                }
            }
        }
    }

    public void cancelWork() {
        this.isAbort = true;
        HttpURLConnection httpURLConnection = this.mRequest;
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
            this.mRequest = null;
        }
    }

    public void dispose() {
        this.mReqListener = null;
        this.mResponseListener = null;
    }

    public Map<String, String> getHeadersAndValues() {
        return this.mHeaderList;
    }

    public InputStream getResponseInput() {
        return this.mResponseInput;
    }

    public String getResponseText() {
        return this.mResponseText;
    }

    public void handleResponseText(InputStream inputStream) throws IOException {
        try {
            String charset = getCharset(this.mRequestData.mOverrideMimeType);
            if (charset == null) {
                charset = getCharset(this.mRequest.getContentType());
            }
            if (charset == null) {
                charset = "utf-8";
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i = 10240;
            int iMin = Math.min(10240, inputStream.available());
            if (iMin > 0) {
                i = iMin;
            }
            byte[] bArr = new byte[i];
            while (true) {
                int i2 = inputStream.read(bArr);
                if (i2 <= 0) {
                    inputStream.close();
                    this.mResponseText = new String(byteArrayOutputStream.toByteArray(), charset);
                    return;
                }
                byteArrayOutputStream.write(bArr, 0, i2);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            this.mResponseText = "";
        }
    }

    public void initHttpsURLConnectionVel() {
        HttpURLConnection httpURLConnection = this.mRequest;
        if (httpURLConnection != null) {
            if (httpURLConnection instanceof HttpsURLConnection) {
                try {
                    SSLSocketFactory sSLSocketFactory = DCloudTrustManager.getSSLSocketFactory();
                    if (sSLSocketFactory != null) {
                        ((HttpsURLConnection) this.mRequest).setSSLSocketFactory(sSLSocketFactory);
                    }
                    ((HttpsURLConnection) this.mRequest).setHostnameVerifier(NetTool.getDefaultHostnameVerifier());
                } catch (Exception e2) {
                    throw new RuntimeException(e2);
                }
            }
            this.mRequest.setConnectTimeout(this.mRequestData.mTimeout);
            this.mRequest.setReadTimeout(this.mRequestData.mTimeout);
            this.mRequest.setInstanceFollowRedirects(true);
            this.mRequest.setDoInput(true);
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.mRequest = this.mRequestData.getHttpRequest();
            if (!this.mRequestData.containHeader("Content-Type")) {
                int i = this.mWorkType;
                if (i == 1) {
                    this.mRequest.setRequestProperty("Content-Type", CONTENT_TYPE_UPLOAD);
                } else if (i != 2) {
                    this.mRequest.setRequestProperty("Content-Type", CONTENT_TYPE_COMMON);
                }
            }
            try {
                initHttpsURLConnectionVel();
                if (!this.mRequestData.isRedirect) {
                    this.mReqListener.onNetStateChanged(IReqListener.NetState.NET_REQUEST_BEGIN, this.isAbort);
                }
                this.mRequestData.addBody(this.mRequest);
                if (!this.mRequestData.isRedirect) {
                    this.mReqListener.onNetStateChanged(IReqListener.NetState.NET_HANDLE_BEGIN, this.isAbort);
                }
                int responseCode = this.mRequest.getResponseCode();
                String responseMessage = this.mRequest.getResponseMessage();
                this.mResponseListener.onResponseState(responseCode, responseMessage);
                switch (responseCode) {
                    case 200:
                    case 201:
                    case 202:
                    case 203:
                    case 204:
                    case 205:
                    case 206:
                        setHeadersAndValues(this.mRequest.getHeaderFields());
                        IReqListener iReqListener = this.mReqListener;
                        IReqListener.NetState netState = IReqListener.NetState.NET_HANDLE_ING;
                        iReqListener.onNetStateChanged(netState, this.isAbort);
                        if (this.mWorkType == 2) {
                            InputStream inputStream = this.mRequest.getInputStream();
                            this.mResponseInput = inputStream;
                            this.mReqListener.onResponsing(inputStream);
                        } else if (this.mRequest.getInputStream() != null) {
                            handleResponseText(this.mRequest.getInputStream());
                            if (PdrUtil.isEmpty(this.mResponseText)) {
                                this.mResponseText = responseCode + Operators.SPACE_STR + responseMessage;
                            }
                        } else {
                            this.mResponseText = responseCode + Operators.SPACE_STR + responseMessage;
                        }
                        this.mReqListener.onNetStateChanged(netState, this.isAbort);
                        break;
                    default:
                        switch (responseCode) {
                            case 301:
                            case c.o /* 302 */:
                            case c.p /* 303 */:
                                String headerField = this.mRequest.getHeaderField(HttpHeaders.HEAD_KEY_LOCATION);
                                if (!TextUtils.isEmpty(headerField)) {
                                    System.out.println("重定向的URL:" + headerField);
                                    String strReplace = headerField.replace(Operators.SPACE_STR, "%20");
                                    this.mRequestData.clearData();
                                    this.mRequestData.setUrl(strReplace);
                                    run();
                                    return;
                                }
                                break;
                            default:
                                setHeadersAndValues(this.mRequest.getHeaderFields());
                                IReqListener iReqListener2 = this.mReqListener;
                                IReqListener.NetState netState2 = IReqListener.NetState.NET_HANDLE_ING;
                                iReqListener2.onNetStateChanged(netState2, this.isAbort);
                                handleResponseText(this.mRequest.getErrorStream());
                                this.mReqListener.onNetStateChanged(netState2, this.isAbort);
                                break;
                        }
                        break;
                }
                this.mReqListener.onNetStateChanged(IReqListener.NetState.NET_HANDLE_END, this.isAbort);
            } catch (Exception e2) {
                e2.printStackTrace();
                this.mResponseListener.onResponseState(0, e2.getMessage());
                this.mReqListener.onNetStateChanged(IReqListener.NetState.NET_HANDLE_ING, this.isAbort);
                if (e2 instanceof SocketTimeoutException) {
                    this.mReqListener.onNetStateChanged(IReqListener.NetState.NET_TIMEOUT, this.isAbort);
                } else {
                    this.mReqListener.onNetStateChanged(IReqListener.NetState.NET_ERROR, this.isAbort);
                }
            }
            NetWorkLoop netWorkLoop = this.mNetWorkLoop;
            if (netWorkLoop != null) {
                netWorkLoop.removeNetWork(this);
            }
        } catch (IllegalArgumentException e3) {
            this.mResponseListener.onResponseState(-1, e3.getMessage());
            this.mReqListener.onNetStateChanged(IReqListener.NetState.NET_HANDLE_ING, this.isAbort);
            this.mReqListener.onNetStateChanged(IReqListener.NetState.NET_ERROR, this.isAbort);
            NetWorkLoop netWorkLoop2 = this.mNetWorkLoop;
            if (netWorkLoop2 != null) {
                netWorkLoop2.removeNetWork(this);
            }
        }
    }

    public void setRetryIntervalTime(long j) {
        if (j > 0) {
            this.mRetryIntervalTime = j;
        }
    }

    public void startWork() {
        Thread thread = new Thread(this);
        thread.setPriority(1);
        thread.start();
        this.mReqListener.onNetStateChanged(IReqListener.NetState.NET_INIT, this.isAbort);
    }
}
