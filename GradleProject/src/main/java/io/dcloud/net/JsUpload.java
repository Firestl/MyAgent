package io.dcloud.net;

import android.database.Cursor;
import android.net.Uri;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IReqListener;
import io.dcloud.common.DHInterface.IResponseListener;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.FileUtil;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.common.util.net.RequestData;
import io.dcloud.common.util.net.UploadMgr;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class JsUpload implements IReqListener, IResponseListener {
    public static final int STATE_COMPLETED = 4;
    public static final int STATE_CONNECTED = 2;
    public static final int STATE_CONNECTING = 1;
    public static final int STATE_INIT = 0;
    public static final int STATE_PAUSE = 5;
    public static final int STATE_RECEIVING = 3;
    public static final int STATE_UNKOWN = -1;
    public boolean isStart = false;
    public RequestData mRequestData;
    public int mState;
    public String mUUID;
    public UploadNetWork mUploadNetWork;
    public String mUrl;
    public IWebview mWebview;

    public class UploadFile implements UploadItem {
        public InputStream mFileInputS;
        public long mFileSize;
        public String mFilename;
        public String mMimetype;
        public String mRange;

        public UploadFile() {
        }
    }

    public interface UploadItem {
    }

    public static class UploadString implements UploadItem {
        public String mData;

        public UploadString(String str) {
            this.mData = str;
        }
    }

    public JsUpload(IWebview iWebview, JSONObject jSONObject) {
        this.mWebview = null;
        this.mWebview = iWebview;
        this.mUrl = jSONObject.optString("url");
        this.mUUID = jSONObject.optString(AbsoluteConst.JSON_KEY_UUID);
        RequestData requestData = new RequestData(this.mUrl, jSONObject.optString("method", "POST"));
        this.mRequestData = requestData;
        requestData.mChunkSize = jSONObject.optInt("chunkSize", 0);
        this.mRequestData.unTrustedCAType = iWebview.obtainApp().obtainConfigProperty(IApp.ConfigProperty.CONFIG_UNTRUSTEDCA);
        this.mRequestData.addHeader("User-Agent", iWebview.getWebviewProperty("User-Agent"));
        UploadNetWork uploadNetWork = new UploadNetWork(1, this.mRequestData, this, this);
        this.mUploadNetWork = uploadNetWork;
        uploadNetWork.mPriority = jSONObject.optInt("priority");
        if (jSONObject.has("timeout")) {
            this.mRequestData.mTimeout = jSONObject.optInt("timeout") * 1000;
        }
        this.mUploadNetWork.MAX_TIMES = jSONObject.optInt(AbsoluteConst.JSON_KEY_RETRY);
        this.mUploadNetWork.setRetryIntervalTime(jSONObject.optLong(AbsoluteConst.JSON_KEY_RETRY_INTERVAL_TIME) * 1000);
    }

    public boolean addData(String str, String str2) {
        return this.mUploadNetWork.addParemeter(str, str2);
    }

    public boolean addFile(IWebview iWebview, String str, JSONObject jSONObject) {
        UploadFile uploadFile = new UploadFile();
        try {
            if (!str.startsWith("content://")) {
                File file = new File(str);
                if (FileUtil.needMediaStoreOpenFile(iWebview.getContext())) {
                    uploadFile.mFileInputS = FileUtil.getFileInputStream(iWebview.getContext(), file);
                } else {
                    uploadFile.mFileInputS = new FileInputStream(file);
                }
                if (uploadFile.mFileInputS == null) {
                    return false;
                }
                uploadFile.mFileSize = file.length();
                String strOptString = jSONObject.optString("key", file.getName());
                uploadFile.mFilename = jSONObject.optString("name", file.getName());
                uploadFile.mMimetype = jSONObject.optString("mime", PdrUtil.getMimeType(str));
                return this.mUploadNetWork.addFile(strOptString, uploadFile);
            }
            Uri uri = Uri.parse(str);
            Cursor cursorQuery = iWebview.getContext().getContentResolver().query(uri, null, null, null, null);
            if (cursorQuery == null) {
                return false;
            }
            InputStream inputStreamOpenInputStream = iWebview.getContext().getContentResolver().openInputStream(uri);
            cursorQuery.moveToFirst();
            int i = cursorQuery.getInt(cursorQuery.getColumnIndex("_size"));
            String string = cursorQuery.getString(cursorQuery.getColumnIndex("_display_name"));
            String string2 = cursorQuery.getString(cursorQuery.getColumnIndex("mime_type"));
            uploadFile.mFileInputS = inputStreamOpenInputStream;
            uploadFile.mFileSize = i;
            String strOptString2 = jSONObject.optString("key", string);
            uploadFile.mFilename = jSONObject.optString("name", string);
            uploadFile.mMimetype = jSONObject.optString("mime", string2);
            boolean zAddFile = this.mUploadNetWork.addFile(strOptString2, uploadFile);
            cursorQuery.close();
            return zAddFile;
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @Override // io.dcloud.common.DHInterface.IReqListener
    public void onNetStateChanged(IReqListener.NetState netState, boolean z) {
        if (netState == IReqListener.NetState.NET_INIT) {
            this.mState = 0;
            Deprecated_JSUtil.excUploadCallBack(this.mWebview, toJsonUpload(), this.mUUID);
            return;
        }
        if (netState == IReqListener.NetState.NET_REQUEST_BEGIN) {
            this.mState = 1;
            Deprecated_JSUtil.excUploadCallBack(this.mWebview, toJsonUpload(), this.mUUID);
            return;
        }
        if (netState == IReqListener.NetState.NET_CONNECTED) {
            this.mState = 2;
            Deprecated_JSUtil.excUploadCallBack(this.mWebview, toJsonUpload(), this.mUUID);
            return;
        }
        if (netState == IReqListener.NetState.NET_HANDLE_ING) {
            this.mState = 3;
            Deprecated_JSUtil.excUploadCallBack(this.mWebview, toJsonUpload(), this.mUUID);
        } else if (netState == IReqListener.NetState.NET_HANDLE_END || netState == IReqListener.NetState.NET_ERROR) {
            this.mState = 4;
            UploadMgr.getUploadMgr().removeNetWork(this.mUploadNetWork);
            Deprecated_JSUtil.excUploadCallBack(this.mWebview, StringUtil.format("{state:%d,status:%d,filename:'%s',responseText:%s,headers:%s}", Integer.valueOf(this.mState), Integer.valueOf(this.mUploadNetWork.mStatus), this.mUploadNetWork.mUploadingFile.toString(), JSONUtil.toJSONableString(this.mUploadNetWork.getResponseText()), this.mUploadNetWork.responseHeaders), this.mUUID);
        }
    }

    @Override // io.dcloud.common.DHInterface.IReqListener
    public int onReceiving(InputStream inputStream) {
        return 0;
    }

    @Override // io.dcloud.common.DHInterface.IResponseListener
    public void onResponseState(int i, String str) {
    }

    @Override // io.dcloud.common.DHInterface.IReqListener
    public void onResponsing(InputStream inputStream) {
    }

    public void setRequestHeader(String str, String str2) {
        this.mRequestData.addHeader(str, str2);
    }

    public String toJsonUpload() {
        return StringUtil.format("{state:%d,status:%d,uploadedSize:%d,totalSize:%d,headers:%s}", Integer.valueOf(this.mState), Integer.valueOf(this.mUploadNetWork.mStatus), Long.valueOf(this.mUploadNetWork.mUploadedSize), Long.valueOf(this.mUploadNetWork.mTotalSize), this.mUploadNetWork.responseHeaders);
    }
}
