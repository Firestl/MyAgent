package io.dcloud.net;

import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import com.igexin.push.core.b;
import com.lzy.okgo.model.HttpHeaders;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IReqListener;
import io.dcloud.common.DHInterface.IResponseListener;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.IOUtil;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.common.util.ThreadPool;
import io.dcloud.common.util.net.DownloadMgr;
import io.dcloud.common.util.net.RequestData;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class JsDownload implements IReqListener, IResponseListener {
    public static final String DOWNLOAD_NAME = "_download_dcloud";
    public static final int STATE_COMPLETED = 4;
    public static final int STATE_CONNECTED = 2;
    public static final int STATE_CONNECTING = 1;
    public static final int STATE_INIT = 0;
    public static final int STATE_PAUSE = 5;
    public static final int STATE_RECEIVING = 3;
    public static final int STATE_UNDEFINED = -1000;
    public static final int STATE_UNKOWN = -1;
    public boolean mAbort;
    public String mData;
    public boolean mDownloadComplete;
    public DownloadJSMgr mDownloadMgr;
    public DownloadNetWork mDownloadNetWork;
    public String mMethod;
    public boolean mPause;
    public int mPriority;
    public ArrayList<IWebview> mRelWebviews;
    public RequestData mRequestData;
    public long mRetryIntervalTime;
    public IWebview mWebview;
    public String sAppid;
    public String sharedPreferenceName;
    public int mState = -1000;
    public String mUUID = null;
    public String mUrl = null;
    public String mRealURL = null;
    public boolean append = false;
    public long responseOffset = 0;
    public JSONObject mOptions = null;
    public String mFileName = "";
    public String mParentPath = null;
    public File mFile = null;
    public RandomAccessFile mFileOs = null;
    public long mFileSize = 0;
    public long mTotalSize = 0;
    public int mRetry = 3;
    public String mConfigFilePath = null;
    public long preTime = 0;

    public JsDownload(DownloadJSMgr downloadJSMgr, IWebview iWebview, JSONObject jSONObject) {
        this.mDownloadNetWork = null;
        this.mRequestData = null;
        this.mWebview = null;
        this.mRelWebviews = null;
        this.mDownloadMgr = null;
        this.mDownloadMgr = downloadJSMgr;
        this.mWebview = iWebview;
        ArrayList<IWebview> arrayList = new ArrayList<>();
        this.mRelWebviews = arrayList;
        arrayList.add(iWebview);
        parseJson(jSONObject);
        RequestData requestData = new RequestData(this.mUrl, this.mMethod);
        this.mRequestData = requestData;
        requestData.unTrustedCAType = iWebview.obtainApp().obtainConfigProperty(IApp.ConfigProperty.CONFIG_UNTRUSTEDCA);
        if (!jSONObject.isNull("timeout")) {
            this.mRequestData.mTimeout = jSONObject.optInt("timeout") * 1000;
        }
        this.mRequestData.addHeader("User-Agent", iWebview.getWebviewProperty("User-Agent"));
        DownloadNetWork downloadNetWork = new DownloadNetWork(2, this.mRequestData, this, this);
        this.mDownloadNetWork = downloadNetWork;
        downloadNetWork.MAX_TIMES = this.mRetry;
        downloadNetWork.mPriority = this.mPriority;
        downloadNetWork.setRetryIntervalTime(this.mRetryIntervalTime);
        this.sAppid = this.mWebview.obtainFrameView().obtainApp().obtainAppId();
        this.sharedPreferenceName = this.sAppid + DOWNLOAD_NAME;
    }

    private void checkSpecialFile(String str) {
        if (TextUtils.isEmpty(str) || !BaseInfo.ISAMU) {
            return;
        }
        int length = str.length();
        if (((length - str.indexOf(".apk")) - 4 == 0 || (length - str.indexOf(".wgt")) - 4 == 0 || (length - str.indexOf(".wgtu")) - 5 == 0) && this.mWebview != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("type", AbsoluteConst.SPNAME_DOWNLOAD);
                jSONObject.put("file", this.mParentPath + this.mFileName);
                jSONObject.put("url", this.mUrl);
                jSONObject.put("appid", this.mWebview.obtainApp().obtainOriginalAppId());
                jSONObject.put("version", this.mWebview.obtainApp().obtainAppVersionName());
                Log.i(AbsoluteConst.HBUILDER_TAG, jSONObject.toString());
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    private File createDownloadFile(boolean z) {
        try {
            String realPath = getRealPath(this.mFileName);
            if (realPath == null) {
                return null;
            }
            File file = new File(realPath);
            if (z && file.exists()) {
                return file;
            }
            String str = this.mFileName;
            int iLastIndexOf = str.lastIndexOf(Operators.DOT_STR);
            String strSubstring = iLastIndexOf < 0 ? str : str.substring(0, iLastIndexOf);
            String strSubstring2 = iLastIndexOf < 0 ? "" : str.substring(iLastIndexOf);
            int i = 1;
            while (file.exists()) {
                str = strSubstring + "(" + i + ")" + strSubstring2;
                i++;
                file = new File(getRealPath(str));
            }
            this.mFileName = str;
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
            return file;
        } catch (Exception e2) {
            String str2 = this.mFileName;
            if (str2 == null || !str2.toLowerCase().startsWith("file://")) {
                e2.printStackTrace();
                return null;
            }
            this.mFileName = this.mFileName.substring(7);
            return createDownloadFile(this.append);
        }
    }

    private void initPath(String str) {
        IApp iAppObtainApp = this.mWebview.obtainFrameView().obtainApp();
        if (PdrUtil.isEmpty(str)) {
            this.mParentPath = new File(BaseInfo.sDownloadFullPath).getParent() + "/";
            this.mFileName = "_downloads/";
            return;
        }
        this.mFileName = str;
        if (startsWith(str, BaseInfo.REL_PRIVATE_DOC_DIR, true)) {
            this.mParentPath = new File(iAppObtainApp.obtainAppDocPath()).getParent() + "/";
            return;
        }
        if (startsWith(str, BaseInfo.REL_PUBLIC_DOCUMENTS_DIR, true)) {
            this.mParentPath = new File(BaseInfo.sDocumentFullPath).getParent() + "/";
            return;
        }
        if (startsWith(str, BaseInfo.REL_PUBLIC_DOWNLOADS_DIR, true)) {
            this.mParentPath = new File(BaseInfo.sDownloadFullPath).getParent() + "/";
            return;
        }
        this.mParentPath = new File(BaseInfo.sDownloadFullPath).getParent() + "/";
    }

    private boolean justDirPath() {
        return TextUtils.isEmpty(this.mFileName) || this.mFileName.endsWith("/");
    }

    private void onStateChanged(int i) {
        if (i == 3 || i == 5) {
            this.mState = i;
        }
        boolean z = i == 3 && SystemClock.elapsedRealtime() - this.preTime >= 10;
        if (z || i != 3 || this.mTotalSize - this.mFileSize <= 5120 || this.mAbort) {
            if (z) {
                this.preTime = SystemClock.elapsedRealtime();
            }
            String json = toJSON();
            int size = this.mRelWebviews.size();
            for (int i2 = 0; i2 < size; i2++) {
                Deprecated_JSUtil.excDownloadCallBack(this.mRelWebviews.get(i2), json, this.mUUID);
            }
        }
        if (this.mAbort) {
            return;
        }
        saveDownloadState();
    }

    private void parseJson(JSONObject jSONObject) {
        this.mOptions = jSONObject.optJSONObject("options");
        this.mUrl = JSONUtil.getString(jSONObject, "url");
        String string = JSONUtil.getString(jSONObject, AbsoluteConst.JSON_KEY_REALURL);
        if (string != null && !string.equalsIgnoreCase(b.m) && !string.equalsIgnoreCase(this.mUrl)) {
            this.mUrl = string;
        }
        String string2 = JSONUtil.getString(jSONObject, "id");
        this.mUUID = string2;
        if (TextUtils.isEmpty(string2)) {
            this.mUUID = JSONUtil.getString(jSONObject, "uuid");
        }
        this.mMethod = JSONUtil.getString(jSONObject, "method");
        this.mPriority = JSONUtil.getInt(jSONObject, "priority");
        this.mRetry = JSONUtil.getInt(jSONObject, AbsoluteConst.JSON_KEY_RETRY);
        this.mFileSize = JSONUtil.getInt(jSONObject, AbsoluteConst.JSON_KEY_DOWNLOADEDSIZE);
        this.mTotalSize = JSONUtil.getInt(jSONObject, "totalSize");
        String string3 = JSONUtil.getString(jSONObject, "state");
        if (!PdrUtil.isEmpty(string3)) {
            try {
                this.mState = Integer.parseInt(string3);
            } catch (NumberFormatException e2) {
                e2.printStackTrace();
            }
        }
        this.mConfigFilePath = BaseInfo.sDownloadFullPath + this.mUUID + ".download";
        File file = new File(this.mConfigFilePath);
        try {
            if (file.exists()) {
                String string4 = IOUtil.toString(new FileInputStream(file));
                string4.replace("\n", "");
                String[] strArrSplit = string4.split("-");
                this.mTotalSize = Long.parseLong(strArrSplit[0]);
                this.mState = Integer.parseInt(strArrSplit[2]);
                File file2 = new File(strArrSplit[3].replace("\n", ""));
                this.mFile = file2;
                if (file2.exists()) {
                    this.mFileSize = this.mFile.length();
                } else {
                    this.mFileSize = 0L;
                }
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        String string5 = JSONUtil.getString(jSONObject, AbsoluteConst.JSON_KEY_FILENAME);
        if (TextUtils.isEmpty(string5)) {
            string5 = JSONUtil.getString(this.mOptions, AbsoluteConst.JSON_KEY_FILENAME);
        }
        initPath(string5);
        this.mData = JSONUtil.getString(jSONObject, "data");
        this.mRetryIntervalTime = JSONUtil.getLong(jSONObject, AbsoluteConst.JSON_KEY_RETRY_INTERVAL_TIME) * 1000;
    }

    private void saveDownloadState() {
        if (this.mFileSize < this.mTotalSize) {
            try {
                File file = new File(this.mConfigFilePath);
                if (file.exists()) {
                    FileOutputStream fileOutputStream = new FileOutputStream(file, false);
                    fileOutputStream.write((this.mTotalSize + "-" + this.mFileSize + "-" + this.mState + "-" + this.mFile.getAbsolutePath()).getBytes());
                    fileOutputStream.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static boolean startsWith(String str, String str2, boolean z) {
        if (str == null || !str.startsWith(str2)) {
            return false;
        }
        if (!z) {
            return true;
        }
        String strSubstring = str.substring(str2.length());
        return strSubstring.length() == 0 || (strSubstring.length() > 1 && strSubstring.charAt(0) == '/');
    }

    public void abort() {
        try {
            this.mAbort = true;
            ThreadPool.self().addThreadTask(new Runnable() { // from class: io.dcloud.net.JsDownload.1
                @Override // java.lang.Runnable
                public void run() {
                    JsDownload.this.mDownloadNetWork.cancelWork();
                    DownloadMgr.getDownloadMgr().removeTask(JsDownload.this.mDownloadNetWork);
                    JsDownload.this.deleteDownloadData();
                    if (!PdrUtil.isEmpty(JsDownload.this.mFileOs)) {
                        try {
                            JsDownload.this.mFileOs.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    if (JsDownload.this.mFile != null) {
                        JsDownload.this.mFile.delete();
                    }
                    JsDownload jsDownload = JsDownload.this;
                    jsDownload.mWebview = null;
                    jsDownload.mRelWebviews.clear();
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void addRelWebview(IWebview iWebview) {
        if (this.mRelWebviews.contains(iWebview)) {
            return;
        }
        this.mRelWebviews.add(iWebview);
    }

    public void deleteDownloadData() {
        this.mDownloadMgr.deleteDownloadTaskInfo(this.sAppid, this.mUUID);
        new File(this.mConfigFilePath).delete();
    }

    public String getRealPath(String str) {
        if (str != null && str.startsWith("/") && !str.toLowerCase().startsWith("/sdcard")) {
            String strConvert2AbsFullPath = this.mWebview.obtainApp().convert2AbsFullPath(str.substring(1));
            if (strConvert2AbsFullPath == null || !strConvert2AbsFullPath.contains("/www/")) {
                return strConvert2AbsFullPath;
            }
            return strConvert2AbsFullPath.replace("/www/", "/" + BaseInfo.REAL_PUBLIC_DOWNLOADS_DIR);
        }
        if (str != null && str.toLowerCase().startsWith("/sdcard")) {
            return str;
        }
        String strConvert2AbsFullPath2 = this.mWebview.obtainApp().convert2AbsFullPath(str);
        if (strConvert2AbsFullPath2 == null || !strConvert2AbsFullPath2.contains("/www/")) {
            return strConvert2AbsFullPath2;
        }
        return strConvert2AbsFullPath2.replace("/www/", "/" + BaseInfo.REAL_PUBLIC_DOWNLOADS_DIR);
    }

    @Override // io.dcloud.common.DHInterface.IReqListener
    public void onNetStateChanged(IReqListener.NetState netState, boolean z) {
        String string;
        int iLastIndexOf;
        if (this.mPause) {
            return;
        }
        if (netState == IReqListener.NetState.NET_INIT) {
            this.mState = 0;
            this.mDownloadComplete = false;
        } else if (netState == IReqListener.NetState.NET_CONNECTED) {
            this.mState = 2;
        } else if (netState == IReqListener.NetState.NET_HANDLE_END) {
            this.mState = 4;
            Logger.d("----NetState.NET_HANDLE_END-----");
            DownloadMgr.getDownloadMgr().removeTask(this.mDownloadNetWork);
            deleteDownloadData();
            this.mDownloadComplete = true;
            checkSpecialFile(this.mFileName);
        } else if (netState == IReqListener.NetState.NET_ERROR) {
            this.mState = 4;
            this.mDownloadNetWork.mStatus = 400;
            Logger.d("----NetState.NET_ERROR-----");
            DownloadMgr.getDownloadMgr().removeTask(this.mDownloadNetWork);
            if (this.mDownloadComplete) {
                return;
            }
        } else if (netState == IReqListener.NetState.NET_TIMEOUT) {
            this.mState = 4;
            this.mDownloadNetWork.mStatus = 0;
            Logger.d("----NetState.NET_TIMEOUT-----");
            DownloadMgr.getDownloadMgr().removeTask(this.mDownloadNetWork);
            if (this.mDownloadComplete) {
                return;
            }
        } else if (netState == IReqListener.NetState.NET_REQUEST_BEGIN) {
            try {
                if (this.mFileSize > 0) {
                    this.mDownloadNetWork.mUrlConn.setRequestProperty(HttpHeaders.HEAD_KEY_RANGE, "bytes=" + String.valueOf(this.mFileSize) + "-");
                }
                this.mDownloadNetWork.mUrlConn.setRequestMethod(this.mMethod);
                if (this.mMethod.equals("POST")) {
                    this.mDownloadNetWork.mUrlConn.setDoOutput(true);
                    this.mDownloadNetWork.mUrlConn.getOutputStream().write(this.mData.getBytes("utf8"));
                    this.mDownloadNetWork.mUrlConn.getOutputStream().flush();
                    this.mDownloadNetWork.mUrlConn.getOutputStream().close();
                    this.mDownloadNetWork.mUrlConn.setChunkedStreamingMode(0);
                }
                this.mDownloadNetWork.mUrlConn.setConnectTimeout(this.mRequestData.mTimeout);
                this.mDownloadNetWork.mUrlConn.setReadTimeout(this.mRequestData.mTimeout);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else if (netState == IReqListener.NetState.NET_HANDLE_BEGIN) {
            String headerField = this.mDownloadNetWork.mUrlConn.getHeaderField(HttpHeaders.HEAD_KEY_CONTENT_LENGTH);
            String headerField2 = this.mDownloadNetWork.mUrlConn.getHeaderField(HttpHeaders.HEAD_KEY_CONTENT_RANGE);
            String headerField3 = this.mDownloadNetWork.mUrlConn.getHeaderField("Content-Type");
            String headerField4 = this.mDownloadNetWork.mUrlConn.getHeaderField("Set-Cookie");
            if (!PdrUtil.isEmpty(headerField4)) {
                CookieManager.getInstance().setCookie(this.mRequestData.getUrl(), headerField4);
            }
            if (headerField2 == null) {
                this.mTotalSize = PdrUtil.parseLong(headerField, 0L);
                this.mFileSize = 0L;
                this.responseOffset = 0L;
                File file = this.mFile;
                if (file != null && file.exists()) {
                    this.mFile.delete();
                    new File(this.mConfigFilePath).delete();
                }
            } else {
                this.append = true;
                try {
                    MessageFormat messageFormat = new MessageFormat("bytes {0,number}-{1,number}");
                    messageFormat.setLocale(Locale.US);
                    long jLongValue = ((Number) messageFormat.parse(headerField2)[0]).longValue();
                    this.responseOffset = jLongValue;
                    if (jLongValue < 0) {
                        this.responseOffset = 0L;
                    }
                } catch (Exception unused) {
                    this.responseOffset = 0L;
                }
            }
            if (justDirPath()) {
                String headerField5 = this.mDownloadNetWork.mUrlConn.getHeaderField("content-disposition");
                try {
                    if (PdrUtil.isEmpty(headerField5) && (iLastIndexOf = (string = this.mDownloadNetWork.mUrlConn.getURL().getFile().toString()).lastIndexOf(47)) >= 0) {
                        String strSubstring = string.substring(iLastIndexOf + 1);
                        if (strSubstring.indexOf(46) >= 0) {
                            if (strSubstring.contains(Operators.CONDITION_IF_STRING)) {
                                strSubstring = strSubstring.substring(0, strSubstring.indexOf(Operators.CONDITION_IF_STRING));
                            }
                            this.mFileName += strSubstring;
                        }
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                if (justDirPath()) {
                    this.mFileName += PdrUtil.getDownloadFilename(headerField5, headerField3, this.mUrl);
                }
            }
            if (this.mDownloadNetWork.isStop) {
                return;
            }
            try {
                this.mFile = createDownloadFile(this.append);
                RandomAccessFile randomAccessFile = new RandomAccessFile(this.mFile, "rw");
                this.mFileOs = randomAccessFile;
                randomAccessFile.seek(this.responseOffset);
                return;
            } catch (IOException e4) {
                e4.printStackTrace();
                saveInDatabase();
                return;
            }
        }
        onStateChanged(this.mState);
    }

    @Override // io.dcloud.common.DHInterface.IReqListener
    public int onReceiving(InputStream inputStream) throws Exception {
        byte[] bArr = new byte[10240];
        if (inputStream != null) {
            this.mDownloadNetWork.mTimes = 1;
            boolean z = false;
            while (true) {
                int i = inputStream.read(bArr);
                if (i == -1) {
                    this.mFileOs.close();
                    break;
                }
                if (this.mPause) {
                    onStateChanged(5);
                    return -1;
                }
                this.mFileOs.write(bArr, 0, i);
                this.mFileSize += (long) i;
                onStateChanged(3);
                if (!z) {
                    this.mRealURL = this.mDownloadNetWork.mUrlConn.getURL().toString();
                    saveInDatabase();
                    z = true;
                }
            }
        }
        if (this.mFileSize < this.mTotalSize) {
            throw new RuntimeException();
        }
        new File(this.mConfigFilePath).delete();
        return -1;
    }

    @Override // io.dcloud.common.DHInterface.IResponseListener
    public void onResponseState(int i, String str) {
    }

    @Override // io.dcloud.common.DHInterface.IReqListener
    public void onResponsing(InputStream inputStream) {
        String json = toJSON();
        this.mState = 1;
        int size = this.mRelWebviews.size();
        for (int i = 0; i < size; i++) {
            Deprecated_JSUtil.excDownloadCallBack(this.mRelWebviews.get(i), json, this.mUUID);
        }
    }

    public void saveInDatabase() {
        this.mDownloadMgr.saveDownloadTaskInfo(this.sAppid, this.mUUID, toSaveJSON());
    }

    public void setRequestHeader(String str, String str2) {
        this.mRequestData.addHeader(str, str2);
    }

    public void start() {
        this.mDownloadNetWork.mTimes = 1;
        this.mPause = false;
        DownloadMgr.getDownloadMgr().addQuestTask(this.mDownloadNetWork);
        saveInDatabase();
    }

    public String toJSON() {
        return this.mState == -1000 ? StringUtil.format("{status: %d,filename: '%s',uuid: '%s',downloadedSize:%d,totalSize:%d,headers:%s}", Integer.valueOf(this.mDownloadNetWork.mStatus), this.mFileName, this.mUUID, Long.valueOf(this.mFileSize), Long.valueOf(this.mTotalSize), this.mDownloadNetWork.getResponseHeaders()) : StringUtil.format("{status: %d,state: %d,filename: '%s',uuid: '%s',downloadedSize:%d,totalSize:%d,headers:%s}", Integer.valueOf(this.mDownloadNetWork.mStatus), Integer.valueOf(this.mState), this.mFileName, this.mUUID, Long.valueOf(this.mFileSize), Long.valueOf(this.mTotalSize), this.mDownloadNetWork.getResponseHeaders());
    }

    public String toSaveJSON() {
        return StringUtil.format("{url: '%s',uuid: '%s',method: '%s',priority: %d,timeout:%d,retry:%d,filename:'%s',downloadedSize:%d,totalSize:%d,state: %d,options:%s,RealURL:'%s'}", this.mUrl, this.mUUID, this.mMethod, Integer.valueOf(this.mPriority), Integer.valueOf(this.mRequestData.mTimeout), Integer.valueOf(this.mRetry), this.mFileName, Long.valueOf(this.mFileSize), Long.valueOf(this.mTotalSize), Integer.valueOf(this.mState), this.mOptions.toString(), this.mRealURL);
    }
}
