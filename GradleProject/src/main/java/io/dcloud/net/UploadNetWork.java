package io.dcloud.net;

import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import com.bumptech.glide.load.engine.GlideException;
import com.lzy.okgo.model.HttpHeaders;
import io.dcloud.common.DHInterface.IReqListener;
import io.dcloud.common.DHInterface.IResponseListener;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.util.Base64;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.net.NetWork;
import io.dcloud.common.util.net.RequestData;
import io.dcloud.common.util.net.UploadMgr;
import io.dcloud.net.JsUpload;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class UploadNetWork extends NetWork {
    public long RANGE_BUF;
    public long mContentLength;
    public int mStatus;
    public boolean mSupport;
    public long mTotalSize;
    public LinkedHashMap<String, JsUpload.UploadItem> mUploadItems;
    public long mUploadedSize;
    public StringBuffer mUploadingFile;
    public String responseHeaders;
    public static final String TAG = UploadNetWork.class.getSimpleName();
    public static String REQMETHOD_GET = "GET";
    public static String REQMETHOD_POST = "POST";

    public UploadNetWork(int i, RequestData requestData, IReqListener iReqListener, IResponseListener iResponseListener) {
        super(i, requestData, iReqListener, iResponseListener);
        this.mSupport = false;
        this.mStatus = 0;
        this.mUploadingFile = new StringBuffer();
        this.RANGE_BUF = 102400L;
        this.mUploadItems = new LinkedHashMap<>(4);
    }

    private void addCutoffLine(String str) {
        this.mContentLength = appendPostParemeter("--" + str + Base64.CRLF, this.mContentLength);
    }

    private void addFileInputStream(String str, JsUpload.UploadFile uploadFile) {
        if (this.mSupport) {
            this.mContentLength = appendPostParemeter("Content-Disposition: attachments; name=\"" + str + "\"; filename=\"" + uploadFile.mFilename + "\"; range=\"0-777/777\"\r\n", this.mContentLength);
        } else {
            this.mContentLength = appendPostParemeter("Content-Disposition: form-data; name=\"" + str + "\"; filename=\"" + uploadFile.mFilename + "\"\r\n", this.mContentLength);
        }
        long jAppendPostParemeter = appendPostParemeter("Content-Type: " + uploadFile.mMimetype + "\r\n\r\n", this.mContentLength);
        this.mContentLength = jAppendPostParemeter;
        long jAppendPostParemeter2 = appendPostParemeter(Base64.CRLF, jAppendPostParemeter);
        this.mContentLength = jAppendPostParemeter2;
        this.mContentLength = jAppendPostParemeter2 + uploadFile.mFileSize;
    }

    private void addPropertyInputStream(String str, String str2) {
        this.mContentLength = appendPostParemeter("Content-Disposition: form-data; name=\"" + str + "\"\r\n\r\n", this.mContentLength);
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append(Base64.CRLF);
        this.mContentLength = appendPostParemeter(sb.toString(), this.mContentLength);
    }

    private void initUploadData() throws Exception {
        int i;
        DataOutputStream dataOutputStream;
        String str = this.mMainBoundry;
        this.mRequest = this.mRequestData.getHttpRequest();
        initHttpsURLConnectionVel();
        this.mRequest.setDoOutput(true);
        this.mRequest.setDoInput(true);
        this.mRequest.setRequestProperty(HttpHeaders.HEAD_KEY_CONNECTION, "Keep-Alive");
        this.mRequest.setRequestProperty("Charset", "UTF-8");
        this.mRequest.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + str);
        int i2 = 0;
        this.mRequest.setUseCaches(false);
        initContentLength();
        int i3 = this.mRequestData.mChunkSize;
        if (i3 > 0) {
            i = i3 * 1024;
            this.mRequest.setChunkedStreamingMode(i);
        } else {
            this.mRequest.setFixedLengthStreamingMode(this.mContentLength);
            i = 10240;
        }
        DataOutputStream dataOutputStream2 = new DataOutputStream(this.mRequest.getOutputStream());
        this.mReqListener.onNetStateChanged(IReqListener.NetState.NET_REQUEST_BEGIN, this.isAbort);
        LinkedHashMap<String, JsUpload.UploadItem> linkedHashMap = this.mUploadItems;
        if (linkedHashMap != null && linkedHashMap.size() > 0) {
            Set<String> setKeySet = this.mUploadItems.keySet();
            this.mTotalSize = this.mContentLength;
            for (String str2 : setKeySet) {
                JsUpload.UploadItem uploadItem = this.mUploadItems.get(str2);
                dataOutputStream2.writeBytes("--" + str + Base64.CRLF);
                if (uploadItem instanceof JsUpload.UploadFile) {
                    JsUpload.UploadFile uploadFile = (JsUpload.UploadFile) uploadItem;
                    InputStream inputStream = uploadFile.mFileInputS;
                    if (inputStream != null) {
                        this.mUploadingFile.append(str2);
                        dataOutputStream2.write(("Content-Disposition: form-data; name=\"" + str2 + "\"; filename=\"" + uploadFile.mFilename + JSUtil.QUOTE + Base64.CRLF).getBytes());
                        StringBuilder sb = new StringBuilder();
                        sb.append("Content-Type: ");
                        sb.append(uploadFile.mMimetype);
                        sb.append(Base64.CRLF);
                        dataOutputStream2.write(sb.toString().getBytes());
                        dataOutputStream2.writeBytes(Base64.CRLF);
                        int iMin = Math.min(inputStream.available(), i);
                        byte[] bArr = new byte[iMin];
                        long j = 0;
                        int i4 = inputStream.read(bArr, i2, iMin);
                        while (i4 > 0) {
                            j += (long) i4;
                            this.mUploadedSize = j;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("initUploadData: mUploadedSize==");
                            DataOutputStream dataOutputStream3 = dataOutputStream2;
                            sb2.append(this.mUploadedSize);
                            sb2.append(" ===== length =");
                            sb2.append(i4);
                            sb2.append(" ====== buffer =");
                            sb2.append(iMin);
                            Log.e("UploadNetWort", sb2.toString());
                            dataOutputStream3.write(bArr, 0, i4);
                            this.mReqListener.onNetStateChanged(IReqListener.NetState.NET_HANDLE_ING, this.isAbort);
                            i4 = inputStream.read(bArr, 0, Math.min(inputStream.available(), i));
                            dataOutputStream2 = dataOutputStream3;
                        }
                        dataOutputStream = dataOutputStream2;
                        dataOutputStream.writeBytes(Base64.CRLF);
                        inputStream.close();
                    }
                } else {
                    dataOutputStream = dataOutputStream2;
                    if (uploadItem instanceof JsUpload.UploadString) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("Content-Disposition: form-data; name=\"" + str2 + JSUtil.QUOTE + Base64.CRLF);
                        sb3.append(Base64.CRLF);
                        sb3.append(((JsUpload.UploadString) uploadItem).mData);
                        sb3.append(Base64.CRLF);
                        dataOutputStream.write(sb3.toString().getBytes());
                        sb3.delete(0, sb3.length());
                    }
                    this.mReqListener.onNetStateChanged(IReqListener.NetState.NET_HANDLE_ING, this.isAbort);
                    dataOutputStream2 = dataOutputStream;
                    i2 = 0;
                }
                this.mReqListener.onNetStateChanged(IReqListener.NetState.NET_HANDLE_ING, this.isAbort);
                dataOutputStream2 = dataOutputStream;
                i2 = 0;
            }
        }
        DataOutputStream dataOutputStream4 = dataOutputStream2;
        dataOutputStream4.writeBytes("--" + str + "--" + Base64.CRLF);
        this.mUploadedSize = (long) dataOutputStream4.size();
        dataOutputStream4.flush();
        responseUpload();
        dataOutputStream4.close();
    }

    public static boolean isRightRequest(int i) {
        return i >= 200 && i < 300;
    }

    private void uploadContent() {
        this.mTimes = 1;
        connet(true);
        dispose();
    }

    public boolean addFile(String str, JsUpload.UploadFile uploadFile) {
        if (this.mUploadItems.containsKey(uploadFile)) {
            return false;
        }
        this.mUploadItems.put(str, uploadFile);
        return true;
    }

    public boolean addParemeter(String str, String str2) {
        if (this.mUploadItems.containsKey(str)) {
            return false;
        }
        this.mUploadItems.put(str, new JsUpload.UploadString(str2));
        return true;
    }

    public long appendPostParemeter(String str, long j) {
        try {
            return ((long) new ByteArrayInputStream(str.getBytes("utf-8")).available()) + j;
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return 0L;
        }
    }

    public void connet(boolean z) {
        if (z) {
            try {
                this.mReqListener.onNetStateChanged(IReqListener.NetState.NET_CONNECTED, this.isAbort);
            } catch (Exception e2) {
                Logger.d("upload is ERROR:" + e2.getLocalizedMessage());
                this.mResponseText = e2.getMessage();
                this.mReqListener.onNetStateChanged(IReqListener.NetState.NET_ERROR, this.isAbort);
                e2.printStackTrace();
                long jCurrentTimeMillis = System.currentTimeMillis();
                long j = this.mRetryIntervalTime;
                int i = this.mTimes;
                long j2 = jCurrentTimeMillis + ((j * ((long) (1 << i))) / 2);
                if (i < this.MAX_TIMES) {
                    this.mTimes = i + 1;
                    while (System.currentTimeMillis() <= j2) {
                    }
                    connet(z);
                    return;
                }
                return;
            }
        }
        initUploadData();
        this.responseHeaders = getResponseHeaders();
    }

    @Override // io.dcloud.common.util.net.NetWork
    public void dispose() {
        this.mHeaderList = null;
        this.mRequest = null;
        this.mUploadedSize = 0L;
        this.mTotalSize = 0L;
        UploadMgr.getUploadMgr().removeNetWork(this);
    }

    public String getResponseHeaders() {
        try {
            HttpURLConnection httpURLConnection = this.mRequest;
            if (httpURLConnection == null) {
                return "";
            }
            Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
            HashMap map = new HashMap();
            for (Map.Entry<String, List<String>> entry : headerFields.entrySet()) {
                Iterator<String> it = entry.getValue().iterator();
                String str = "";
                while (it.hasNext()) {
                    str = str + GlideException.IndentedAppendable.INDENT + it.next();
                }
                if (!PdrUtil.isEmpty(entry.getKey())) {
                    map.put(entry.getKey(), str);
                }
            }
            return new JSONObject(map).toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "{}";
        }
    }

    public void initContentLength() {
        this.mContentLength = 0L;
        LinkedHashMap<String, JsUpload.UploadItem> linkedHashMap = this.mUploadItems;
        if (linkedHashMap != null && linkedHashMap.size() > 0) {
            for (String str : this.mUploadItems.keySet()) {
                JsUpload.UploadItem uploadItem = this.mUploadItems.get(str);
                if (uploadItem instanceof JsUpload.UploadFile) {
                    this.mUploadingFile.append(str);
                    addCutoffLine(this.mMainBoundry);
                    addFileInputStream(str, (JsUpload.UploadFile) uploadItem);
                } else if (uploadItem instanceof JsUpload.UploadString) {
                    addCutoffLine(this.mMainBoundry);
                    addPropertyInputStream(str, ((JsUpload.UploadString) uploadItem).mData);
                }
            }
        }
        this.mContentLength = appendPostParemeter("--" + this.mMainBoundry + "--\r\n", this.mContentLength);
    }

    public void responseUpload() {
        try {
            HttpURLConnection httpURLConnection = this.mRequest;
            if (httpURLConnection != null) {
                this.mStatus = httpURLConnection.getResponseCode();
            }
            String headerField = this.mRequest.getHeaderField("Set-Cookie");
            if (!TextUtils.isEmpty(headerField)) {
                CookieManager.getInstance().setCookie(this.mRequestData.getUrl(), headerField);
            }
            InputStream inputStream = this.mRequest.getInputStream();
            byte[] bArr = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int i = inputStream.read(bArr);
                if (i <= 0) {
                    break;
                } else {
                    byteArrayOutputStream.write(bArr, 0, i);
                }
            }
            String str = new String(byteArrayOutputStream.toByteArray(), "utf-8");
            this.mResponseText = str;
            try {
                JSONObject jSONObject = new JSONObject(str);
                JSONUtil.getString(jSONObject, "result");
                JSONUtil.getString(jSONObject, "code");
                JSONUtil.getString(jSONObject, "message");
            } catch (Exception unused) {
                Logger.e("uploadnetwork", "responseUpload JSONObject _data=" + str + ";url=" + this.mRequestData.getUrl());
            }
            if (isRightRequest(this.mStatus)) {
                this.mReqListener.onNetStateChanged(IReqListener.NetState.NET_HANDLE_ING, this.isAbort);
                this.mReqListener.onNetStateChanged(IReqListener.NetState.NET_HANDLE_END, this.isAbort);
            } else {
                this.mReqListener.onNetStateChanged(IReqListener.NetState.NET_ERROR, this.isAbort);
            }
            byteArrayOutputStream.close();
            inputStream.close();
        } catch (Exception e2) {
            Logger.e("uploadnetwork", "responseUpload " + e2.getLocalizedMessage() + ";url=" + this.mRequestData.getUrl());
            this.mResponseText = e2.getMessage();
            this.mReqListener.onNetStateChanged(IReqListener.NetState.NET_ERROR, this.isAbort);
        }
    }

    @Override // io.dcloud.common.util.net.NetWork, java.lang.Runnable
    public void run() {
        uploadContent();
    }
}
