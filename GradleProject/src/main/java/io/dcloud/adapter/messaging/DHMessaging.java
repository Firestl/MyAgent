package io.dcloud.adapter.messaging;

import android.net.Uri;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.umeng.ccg.a;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class DHMessaging {
    public static final int TYPE_EMAIL = 3;
    public static final int TYPE_MMS = 2;
    public static final int TYPE_SMS = 1;
    public ArrayList<Uri> mAttachments;
    public String[] mBcc;
    public String mBody;
    public String mCallbackId;
    public String[] mCc;
    public boolean mSilent = false;
    public String mSubject;
    public String[] mTo;
    public int mType;
    public IWebview mWebview;

    public static DHMessaging parseMessage(IWebview iWebview, String str, String str2) {
        DHMessaging dHMessaging = new DHMessaging();
        dHMessaging.mCallbackId = str;
        dHMessaging.setWebview(iWebview);
        try {
            JSONObject jSONObject = new JSONObject(str2);
            dHMessaging.mType = jSONObject.getInt("type");
            if (!jSONObject.isNull(RemoteMessageConst.TO)) {
                dHMessaging.mTo = JSUtil.jsonArrayToStringArr((JSONArray) jSONObject.get(RemoteMessageConst.TO));
            }
            if (!jSONObject.isNull("body")) {
                dHMessaging.mBody = (String) jSONObject.get("body");
            }
            if (!jSONObject.isNull("silent")) {
                dHMessaging.mSilent = PdrUtil.parseBoolean(JSONUtil.getString(jSONObject, "silent"), dHMessaging.mSilent, false);
            }
            if (dHMessaging.mType == 1) {
                DHMessagCenter.sendMessage(dHMessaging);
            } else if (dHMessaging.mType == 2) {
                if (!jSONObject.isNull("attachment")) {
                    dHMessaging.mAttachments = new ArrayList<>();
                    String strConvert2AbsFullPath = iWebview.obtainFrameView().obtainApp().convert2AbsFullPath(iWebview.obtainFullUrl(), ((JSONArray) jSONObject.get("attachment")).getString(0));
                    if (!strConvert2AbsFullPath.startsWith("file://")) {
                        strConvert2AbsFullPath = "file://" + strConvert2AbsFullPath;
                    }
                    dHMessaging.mAttachments.add(Uri.parse(strConvert2AbsFullPath));
                }
                DHMessagCenter.sendMMS(dHMessaging);
            } else if (dHMessaging.mType == 3) {
                if (!jSONObject.isNull(a.f5341a)) {
                    dHMessaging.mCc = JSUtil.jsonArrayToStringArr((JSONArray) jSONObject.get(a.f5341a));
                }
                if (!jSONObject.isNull("bcc")) {
                    dHMessaging.mBcc = JSUtil.jsonArrayToStringArr((JSONArray) jSONObject.get("bcc"));
                }
                if (!jSONObject.isNull("subject")) {
                    dHMessaging.mSubject = jSONObject.getString("subject");
                }
                if (!jSONObject.isNull("attachment")) {
                    dHMessaging.mAttachments = new ArrayList<>();
                    JSONArray jSONArray = (JSONArray) jSONObject.get("attachment");
                    IApp iAppObtainApp = iWebview.obtainFrameView().obtainApp();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        String strConvert2AbsFullPath2 = iAppObtainApp.convert2AbsFullPath(iWebview.obtainFullUrl(), jSONArray.getString(i));
                        if (!strConvert2AbsFullPath2.startsWith("file://")) {
                            strConvert2AbsFullPath2 = "file://" + strConvert2AbsFullPath2;
                        }
                        dHMessaging.mAttachments.add(Uri.parse(strConvert2AbsFullPath2));
                    }
                }
                DHMessagCenter.sendMail(dHMessaging);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return dHMessaging;
    }

    public void setWebview(IWebview iWebview) {
        this.mWebview = iWebview;
    }
}
