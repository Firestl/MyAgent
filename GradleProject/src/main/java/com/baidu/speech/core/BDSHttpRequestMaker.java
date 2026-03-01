package com.baidu.speech.core;

import android.util.Log;
import com.baidu.speech.utils.CommonParam;
import com.huawei.secure.android.common.ssl.SSLUtil;
import com.taobao.weex.common.Constants;
import com.taobao.weex.el.parse.Operators;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: classes.dex */
public class BDSHttpRequestMaker {
    public static final String BACKUP_URL_NORTH = "119.75.222.172";
    public static final String BACKUP_URL_SOUTH = "182.61.62.25";
    public static final int CONNECTION_TIMEOUT = 3;
    public static final String TAG = "BDSHttpRequestMaker";
    public static final int TIMEOUT_DURATION = 10;
    public static final int TYPE_DOWNLOAD_FINAL = 241;
    public static final int TYPE_DOWNLOAD_FINISH = 243;
    public static final int TYPE_DOWNLOAD_PARTIAL = 240;
    public static final int TYPE_DOWNLOAD_THIRD_DATA = 242;
    public static final int TYPE_UPLOAD_AUDIO = 1;
    public static final int TYPE_UPLOAD_CANCEL = 4;
    public static final int TYPE_UPLOAD_FINISH = 3;
    public static final int TYPE_UPLOAD_PARAM = 0;
    public static final int TYPE_UPLOAD_THIRD_DATA = 2;
    public String mNorthDownUrl;
    public String mNorthUpUrl;
    public String mSouthDownUrl;
    public String mSouthUpUrl;
    public static final Boolean DEBUG = true;
    public static SSLContext sSSLContext = null;
    public static String mHostIp = "";
    public static SSLSocketFactory defaultSslFactory = HttpsURLConnection.getDefaultSSLSocketFactory();
    public static HostnameVerifier defaulthostVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
    public boolean mRetriedNorth = false;
    public boolean mRetriedSouth = false;
    public final int CONNECTION_STATUS_INIT = 0;
    public final int CONNECTION_STATUS_WORKING = 1;
    public final int CONNECTION_STATUS_CLOSE = 2;
    public HttpURLConnection mUploadConnection = null;
    public HttpURLConnection mDownloadConnection = null;
    public OutputStream mUploadOutputStream = null;
    public DataInputStream mDownloadInputStream = null;
    public int mUploadConnctionStatus = 0;
    public int mDownloadConnectionStatus = 0;
    public MyUploadThread mUploadThread = new MyUploadThread();
    public ArrayList<BDSHTTPResponse> mErrorArray = new ArrayList<>();
    public boolean mUploadedData = false;
    public boolean mAgentUpload = false;
    public boolean mAgentDownload = false;
    public final HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() { // from class: com.baidu.speech.core.BDSHttpRequestMaker.1
        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            String strConvertHostname = BDSHttpRequestMaker.convertHostname(str);
            if (Log.isLoggable(BDSHttpRequestMaker.TAG, 3) || BDSHttpRequestMaker.DEBUG.booleanValue()) {
                BDSHttpRequestMaker.this.logD(BDSHttpRequestMaker.TAG, "hostname : " + str + " verifyUrl : " + strConvertHostname);
            }
            return strConvertHostname.equals("vse.baidu.com") || strConvertHostname.equals("vop.baidu.com") || strConvertHostname.equals("openapi.baidu.com") || strConvertHostname.equals("audiotest.baidu.com") || strConvertHostname.equals(BDSHttpRequestMaker.BACKUP_URL_NORTH) || strConvertHostname.equals(BDSHttpRequestMaker.BACKUP_URL_SOUTH) || strConvertHostname.equals("httpsdns.baidu.com") || strConvertHostname.equals("upl.baidu.com") || strConvertHostname.contains(".baidu.");
        }
    };

    public class AudioData {
        public byte[] mData;
        public boolean mIsLast;
        public int mType;

        public AudioData(int i, byte[] bArr, boolean z) {
            this.mType = i;
            this.mIsLast = z;
            int length = bArr.length + 1;
            byte[] bArr2 = {(byte) i};
            byte[] bArr3 = new byte[length + 4];
            this.mData = bArr3;
            System.arraycopy(new byte[]{(byte) (length & 255), (byte) ((length >> 8) & 255), (byte) ((length >> 16) & 255), (byte) (length >> 24)}, 0, bArr3, 0, 4);
            System.arraycopy(bArr2, 0, this.mData, 4, 1);
            System.arraycopy(bArr, 0, this.mData, 5, bArr.length);
            if (Log.isLoggable(BDSHttpRequestMaker.TAG, 3) || BDSHttpRequestMaker.DEBUG.booleanValue()) {
                BDSHttpRequestMaker.this.logI(BDSHttpRequestMaker.TAG, "AudioData : mType = " + this.mType + " | mIsLast = " + this.mIsLast + " | mData = " + this.mData.length);
            }
        }
    }

    public class MyDownloadThread extends Thread {
        public MyDownloadThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
        }
    }

    public class MyUploadThread extends Thread {
        public MyUploadThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
        }
    }

    public static String convertHostname(String str) {
        return (str.equals("vse.baidu.com") || str.equals("vop.baidu.com") || str.equals("openapi.baidu.com") || str.equals("upl.baidu.com") || !str.equals(mHostIp)) ? str : CommonParam.REQUEST_URL;
    }

    private void generateBackupUrls(String str, int i) {
        if (1 == i) {
            this.mNorthUpUrl = str.replace(mHostIp, BACKUP_URL_NORTH);
            this.mSouthUpUrl = str.replace(mHostIp, BACKUP_URL_SOUTH);
        } else if (2 == i) {
            this.mNorthDownUrl = str.replace(mHostIp, BACKUP_URL_NORTH);
            this.mSouthDownUrl = str.replace(mHostIp, BACKUP_URL_SOUTH);
        }
    }

    public static BDSHttpRequestMaker newRequestMaker() {
        if (sSSLContext == null) {
            try {
                SSLContext sSLContext = SSLContext.getInstance(SSLUtil.d);
                sSSLContext = sSLContext;
                sSLContext.init(null, null, null);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return new BDSHttpRequestMaker();
    }

    private Proxy setAgent() {
        String str = CommonParam.AGENT_URL;
        if (str == "") {
            return null;
        }
        try {
            URL url = new URL(str);
            Log.e(TAG, "ip: " + InetAddress.getByName(url.getHost()).getHostAddress() + " port: " + url.getPort());
            return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(url.getHost(), url.getPort()));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void cancelRequest() {
        try {
            this.mAgentDownload = false;
            this.mAgentUpload = false;
            if (this.mUploadConnection != null) {
                this.mUploadConnection.disconnect();
                this.mUploadConnection = null;
            }
            if (this.mDownloadConnection != null) {
                this.mDownloadConnection.disconnect();
                this.mDownloadConnection = null;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.e(TAG, "BDSHttpRequestMaker cancelRequest exception");
        }
    }

    public void logD(String str, String str2) {
        Log.d(str, str2 + " &" + Integer.toHexString(hashCode()) + ", ");
    }

    public void logI(String str, String str2) {
        Log.i(str, str2 + " &" + Integer.toHexString(hashCode()) + ", ");
    }

    public void logW(String str, String str2) {
        Log.w(str, str2 + " &" + Integer.toHexString(hashCode()) + ", ");
    }

    /* JADX WARN: Code restructure failed: missing block: B:80:0x024c, code lost:
    
        if (r13 == null) goto L231;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x024e, code lost:
    
        r13.disconnect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0251, code lost:
    
        r9.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0255, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0256, code lost:
    
        r0.printStackTrace();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:143:0x02f4  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0304 A[Catch: IOException -> 0x025f, TRY_ENTER, TRY_LEAVE, TryCatch #37 {IOException -> 0x025f, blocks: (B:86:0x025a, B:182:0x0389, B:150:0x0304, B:196:0x03af), top: B:245:0x004b }] */
    /* JADX WARN: Removed duplicated region for block: B:157:0x031a A[Catch: all -> 0x03b3, TryCatch #8 {all -> 0x03b3, blocks: (B:155:0x0313, B:157:0x031a, B:159:0x0327, B:173:0x0352, B:141:0x02d3, B:187:0x0393), top: B:220:0x0313 }] */
    /* JADX WARN: Removed duplicated region for block: B:189:0x039f  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x03af A[Catch: IOException -> 0x025f, TRY_ENTER, TRY_LEAVE, TryCatch #37 {IOException -> 0x025f, blocks: (B:86:0x025a, B:182:0x0389, B:150:0x0304, B:196:0x03af), top: B:245:0x004b }] */
    /* JADX WARN: Removed duplicated region for block: B:201:0x03b7  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x03bc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:229:0x02f9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:237:0x03c7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:241:0x03a4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:254:0x024c A[EDGE_INSN: B:254:0x024c->B:80:0x024c BREAK  A[LOOP:0: B:8:0x0026->B:172:0x0345], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0352 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:263:0x011f A[EDGE_INSN: B:263:0x011f->B:53:0x011f BREAK  A[LOOP:2: B:49:0x010d->B:52:0x0117], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:264:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0117 A[Catch: IOException -> 0x0266, all -> 0x0268, Exception -> 0x026b, SocketTimeoutException -> 0x0273, LOOP:2: B:49:0x010d->B:52:0x0117, LOOP_END, TryCatch #3 {all -> 0x0268, blocks: (B:48:0x010b, B:50:0x0110, B:52:0x0117, B:53:0x011f, B:55:0x0189, B:58:0x01b6, B:60:0x01c5, B:65:0x01da, B:67:0x01e8, B:68:0x0200, B:70:0x0206, B:73:0x0218, B:75:0x0221, B:77:0x0236, B:78:0x0244, B:64:0x01cd, B:57:0x0191), top: B:215:0x010b }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0191 A[Catch: IOException -> 0x0266, all -> 0x0268, Exception -> 0x026b, SocketTimeoutException -> 0x0273, TRY_LEAVE, TryCatch #3 {all -> 0x0268, blocks: (B:48:0x010b, B:50:0x0110, B:52:0x0117, B:53:0x011f, B:55:0x0189, B:58:0x01b6, B:60:0x01c5, B:65:0x01da, B:67:0x01e8, B:68:0x0200, B:70:0x0206, B:73:0x0218, B:75:0x0221, B:77:0x0236, B:78:0x0244, B:64:0x01cd, B:57:0x0191), top: B:215:0x010b }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01c5 A[Catch: Exception -> 0x01cb, all -> 0x0268, TRY_LEAVE, TryCatch #3 {all -> 0x0268, blocks: (B:48:0x010b, B:50:0x0110, B:52:0x0117, B:53:0x011f, B:55:0x0189, B:58:0x01b6, B:60:0x01c5, B:65:0x01da, B:67:0x01e8, B:68:0x0200, B:70:0x0206, B:73:0x0218, B:75:0x0221, B:77:0x0236, B:78:0x0244, B:64:0x01cd, B:57:0x0191), top: B:215:0x010b }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01e8 A[Catch: IOException -> 0x0266, all -> 0x0268, Exception -> 0x026b, SocketTimeoutException -> 0x0273, TryCatch #3 {all -> 0x0268, blocks: (B:48:0x010b, B:50:0x0110, B:52:0x0117, B:53:0x011f, B:55:0x0189, B:58:0x01b6, B:60:0x01c5, B:65:0x01da, B:67:0x01e8, B:68:0x0200, B:70:0x0206, B:73:0x0218, B:75:0x0221, B:77:0x0236, B:78:0x0244, B:64:0x01cd, B:57:0x0191), top: B:215:0x010b }] */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r15v1 */
    /* JADX WARN: Type inference failed for: r15v10 */
    /* JADX WARN: Type inference failed for: r15v11 */
    /* JADX WARN: Type inference failed for: r15v12 */
    /* JADX WARN: Type inference failed for: r15v13 */
    /* JADX WARN: Type inference failed for: r15v14 */
    /* JADX WARN: Type inference failed for: r15v16 */
    /* JADX WARN: Type inference failed for: r15v2 */
    /* JADX WARN: Type inference failed for: r15v25 */
    /* JADX WARN: Type inference failed for: r15v3 */
    /* JADX WARN: Type inference failed for: r15v4, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r15v5, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r15v6, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r15v7, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r15v8 */
    /* JADX WARN: Type inference failed for: r15v9 */
    /* JADX WARN: Type inference failed for: r9v19 */
    /* JADX WARN: Type inference failed for: r9v20 */
    /* JADX WARN: Type inference failed for: r9v21 */
    /* JADX WARN: Type inference failed for: r9v22 */
    /* JADX WARN: Type inference failed for: r9v29 */
    /* JADX WARN: Type inference failed for: r9v30 */
    /* JADX WARN: Type inference failed for: r9v31 */
    /* JADX WARN: Type inference failed for: r9v32 */
    /* JADX WARN: Type inference failed for: r9v33 */
    /* JADX WARN: Type inference failed for: r9v38 */
    /* JADX WARN: Type inference failed for: r9v39 */
    /* JADX WARN: Type inference failed for: r9v42 */
    /* JADX WARN: Type inference failed for: r9v43 */
    /* JADX WARN: Type inference failed for: r9v6, types: [java.io.BufferedInputStream] */
    /* JADX WARN: Type inference failed for: r9v7, types: [java.io.BufferedInputStream] */
    /* JADX WARN: Type inference failed for: r9v8, types: [java.io.BufferedInputStream] */
    /* JADX WARN: Type inference failed for: r9v9, types: [java.io.BufferedInputStream] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:89:0x0260 -> B:244:0x03b2). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.baidu.speech.core.BDSHTTPResponse makeRequest(java.lang.String r24, byte[] r25, java.lang.String[] r26, float r27, int r28) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 977
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.speech.core.BDSHttpRequestMaker.makeRequest(java.lang.String, byte[], java.lang.String[], float, int):com.baidu.speech.core.BDSHTTPResponse");
    }

    /* JADX WARN: Removed duplicated region for block: B:67:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0190  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01aa A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.baidu.speech.core.BDSHTTPResponse readData() {
        /*
            Method dump skipped, instruction units count: 427
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.speech.core.BDSHttpRequestMaker.readData():com.baidu.speech.core.BDSHTTPResponse");
    }

    public int sendData(byte[] bArr, boolean z) {
        if (this.mUploadConnctionStatus == 2) {
            logW(TAG, "Upload connection stauts has already been closed.");
            return 0;
        }
        int i = bArr[4] & 255;
        if (Log.isLoggable(TAG, 3) || DEBUG.booleanValue()) {
            logI(TAG, "sendData  dataType : " + i + Operators.SPACE_STR + this.mUploadConnection.getURL());
        }
        try {
            try {
            } catch (Exception e2) {
                e2.printStackTrace();
                BDSHTTPResponse bDSHTTPResponse = new BDSHTTPResponse();
                bDSHTTPResponse.m_http_status = this.mAgentUpload ? 2102 : 2003;
                bDSHTTPResponse.m_response_data = null;
                bDSHTTPResponse.m_request_status = 0;
                this.mErrorArray.add(bDSHTTPResponse);
                this.mAgentUpload = false;
            }
            if (this.mUploadConnection == null) {
                Log.e(TAG, "Upload conncetion not exist");
                return -1;
            }
            if (this.mUploadOutputStream == null) {
                this.mUploadOutputStream = this.mUploadConnection.getOutputStream();
            }
            this.mUploadOutputStream.write(bArr);
            this.mUploadOutputStream.flush();
            if (z) {
                this.mUploadOutputStream.close();
                try {
                    this.mUploadConnection.getInputStream().close();
                } catch (EOFException e3) {
                    e3.printStackTrace();
                }
                this.mUploadConnection.disconnect();
            }
            return 0;
        } catch (EOFException unused) {
            logW(TAG, "send data EOFException");
            BDSHTTPResponse bDSHTTPResponse2 = new BDSHTTPResponse();
            bDSHTTPResponse2.m_http_status = 2100;
            bDSHTTPResponse2.m_response_data = null;
            bDSHTTPResponse2.m_request_status = 0;
            this.mErrorArray.add(bDSHTTPResponse2);
            return -1;
        }
    }

    public int setupConnection(String str, String[] strArr, float f, int i) {
        if (Log.isLoggable(TAG, 3) || DEBUG.booleanValue()) {
            logI(TAG, "url = " + str);
        }
        this.mErrorArray.clear();
        try {
            mHostIp = new URL(str).getHost();
            if (Log.isLoggable(TAG, 3) || DEBUG.booleanValue()) {
                logD(TAG, "url: " + str + " mHostIp: " + mHostIp);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (str.contains("up")) {
            generateBackupUrls(str, 1);
            return setupUploadConnection(str, strArr, f, i);
        }
        if (str.contains(Constants.Value.DIRECTION_DOWN)) {
            generateBackupUrls(str, 2);
            return setupDownloadConnection(str, strArr, f, i);
        }
        Log.e(TAG, "Error url : " + str);
        return -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00f2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int setupDownloadConnection(java.lang.String r7, java.lang.String[] r8, float r9, int r10) {
        /*
            Method dump skipped, instruction units count: 265
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.speech.core.BDSHttpRequestMaker.setupDownloadConnection(java.lang.String, java.lang.String[], float, int):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x009b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int setupUploadConnection(java.lang.String r6, java.lang.String[] r7, float r8, int r9) {
        /*
            r5 = this;
            r0 = 0
            r1 = 1
            java.net.Proxy r2 = r5.setAgent()     // Catch: java.lang.Exception -> L74
            if (r2 == 0) goto L18
            java.net.URL r3 = new java.net.URL     // Catch: java.lang.Exception -> L74
            r3.<init>(r6)     // Catch: java.lang.Exception -> L74
            java.net.URLConnection r6 = r3.openConnection(r2)     // Catch: java.lang.Exception -> L74
            java.net.HttpURLConnection r6 = (java.net.HttpURLConnection) r6     // Catch: java.lang.Exception -> L74
            r5.mUploadConnection = r6     // Catch: java.lang.Exception -> L74
            r5.mAgentUpload = r1     // Catch: java.lang.Exception -> L74
            goto L25
        L18:
            java.net.URL r2 = new java.net.URL     // Catch: java.lang.Exception -> L74
            r2.<init>(r6)     // Catch: java.lang.Exception -> L74
            java.net.URLConnection r6 = r2.openConnection()     // Catch: java.lang.Exception -> L74
            java.net.HttpURLConnection r6 = (java.net.HttpURLConnection) r6     // Catch: java.lang.Exception -> L74
            r5.mUploadConnection = r6     // Catch: java.lang.Exception -> L74
        L25:
            java.net.HttpURLConnection r6 = r5.mUploadConnection     // Catch: java.lang.Exception -> L74
            boolean r6 = r6 instanceof javax.net.ssl.HttpsURLConnection     // Catch: java.lang.Exception -> L74
            if (r6 == 0) goto L34
            java.net.HttpURLConnection r6 = r5.mUploadConnection     // Catch: java.lang.Exception -> L74
            javax.net.ssl.HttpsURLConnection r6 = (javax.net.ssl.HttpsURLConnection) r6     // Catch: java.lang.Exception -> L74
            javax.net.ssl.HostnameVerifier r2 = r5.DO_NOT_VERIFY     // Catch: java.lang.Exception -> L74
            r6.setHostnameVerifier(r2)     // Catch: java.lang.Exception -> L74
        L34:
            java.net.HttpURLConnection r6 = r5.mUploadConnection     // Catch: java.lang.Exception -> L74
            r2 = 3000(0xbb8, float:4.204E-42)
            r6.setConnectTimeout(r2)     // Catch: java.lang.Exception -> L74
            java.net.HttpURLConnection r6 = r5.mUploadConnection     // Catch: java.lang.Exception -> L74
            r2 = 10000(0x2710, float:1.4013E-41)
            r6.setReadTimeout(r2)     // Catch: java.lang.Exception -> L74
            java.net.HttpURLConnection r6 = r5.mUploadConnection     // Catch: java.lang.Exception -> L74
            java.lang.String r2 = "POST"
            r6.setRequestMethod(r2)     // Catch: java.lang.Exception -> L74
            r6 = 0
        L4a:
            if (r7 == 0) goto L5e
            int r2 = r7.length     // Catch: java.lang.Exception -> L74
            int r2 = r2 - r1
            if (r6 >= r2) goto L5e
            java.net.HttpURLConnection r2 = r5.mUploadConnection     // Catch: java.lang.Exception -> L74
            r3 = r7[r6]     // Catch: java.lang.Exception -> L74
            int r4 = r6 + 1
            r4 = r7[r4]     // Catch: java.lang.Exception -> L74
            r2.setRequestProperty(r3, r4)     // Catch: java.lang.Exception -> L74
            int r6 = r6 + 2
            goto L4a
        L5e:
            java.net.HttpURLConnection r6 = r5.mUploadConnection     // Catch: java.lang.Exception -> L74
            java.lang.String r2 = "Connection"
            java.lang.String r3 = "close"
            r6.setRequestProperty(r2, r3)     // Catch: java.lang.Exception -> L74
            java.net.HttpURLConnection r6 = r5.mUploadConnection     // Catch: java.lang.Exception -> L74
            r6.setChunkedStreamingMode(r0)     // Catch: java.lang.Exception -> L74
            java.net.HttpURLConnection r6 = r5.mUploadConnection     // Catch: java.lang.Exception -> L74
            r6.connect()     // Catch: java.lang.Exception -> L74
            r5.mUploadConnctionStatus = r1     // Catch: java.lang.Exception -> L74
            goto Lb2
        L74:
            r6 = move-exception
            r6.printStackTrace()
            boolean r2 = r5.mRetriedNorth
            if (r2 != 0) goto L84
            r5.mRetriedNorth = r1
            java.lang.String r1 = r5.mNorthUpUrl
        L80:
            r5.setupUploadConnection(r1, r7, r8, r9)
            goto L8d
        L84:
            boolean r2 = r5.mRetriedSouth
            if (r2 != 0) goto L8d
            r5.mRetriedSouth = r1
            java.lang.String r1 = r5.mSouthUpUrl
            goto L80
        L8d:
            com.baidu.speech.core.BDSHTTPResponse r7 = new com.baidu.speech.core.BDSHTTPResponse
            r7.<init>()
            boolean r6 = r6 instanceof java.net.SocketTimeoutException
            if (r6 == 0) goto L9b
            r6 = 1003(0x3eb, float:1.406E-42)
        L98:
            r7.m_http_status = r6
            goto La6
        L9b:
            boolean r6 = r5.mAgentUpload
            if (r6 == 0) goto La2
            r6 = 2002(0x7d2, float:2.805E-42)
            goto L98
        La2:
            r6 = 2106(0x83a, float:2.951E-42)
            r7.m_request_status = r6
        La6:
            r5.mAgentUpload = r0
            r6 = 0
            r7.m_response_data = r6
            r7.m_request_status = r0
            java.util.ArrayList<com.baidu.speech.core.BDSHTTPResponse> r6 = r5.mErrorArray
            r6.add(r7)
        Lb2:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.speech.core.BDSHttpRequestMaker.setupUploadConnection(java.lang.String, java.lang.String[], float, int):int");
    }
}
