package io.dcloud.share;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.text.TextUtils;
import androidx.core.content.FileProvider;
import com.bumptech.glide.load.engine.GlideException;
import com.taobao.weex.el.parse.Operators;
import com.umeng.analytics.pro.bq;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.share.sina.SinaWeiboApiManager;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AbsMgr f6769a;
    public HashMap<String, IFShareApi> b = new HashMap<>();
    public HashMap<String, ShareAuthorizeView> c;
    public HashMap<String, String> d;

    public a(AbsMgr absMgr, String str) {
        this.f6769a = absMgr;
        this.d = (HashMap) this.f6769a.processEvent(IMgr.MgrType.FeatureMgr, 4, str);
        new ArrayList();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0089  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String a(io.dcloud.common.DHInterface.IWebview r11, java.lang.String r12, java.lang.String[] r13) {
        /*
            Method dump skipped, instruction units count: 534
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.share.a.a(io.dcloud.common.DHInterface.IWebview, java.lang.String, java.lang.String[]):java.lang.String");
    }

    public String a(String str) {
        if (PdrUtil.isEquals(str, SinaWeiboApiManager.SINAWEIBO_ID)) {
            return this.d.get("sina");
        }
        if (PdrUtil.isEquals(str, "tencentweibo")) {
            return this.d.get("tencent");
        }
        return null;
    }

    private String a(IWebview iWebview) {
        String str;
        Exception e2;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Operators.ARRAY_START_STR);
        HashMap<String, String> map = this.d;
        if (map != null && !map.isEmpty()) {
            String str2 = null;
            int i = 0;
            for (String str3 : this.d.keySet()) {
                try {
                    IFShareApi iFShareApi = this.b.get(str3);
                    if (iFShareApi == null) {
                        str = this.d.get(str3);
                        try {
                            iFShareApi = (IFShareApi) Class.forName(str).newInstance();
                            iFShareApi.initConfig();
                            this.b.put(iFShareApi.getId(), iFShareApi);
                            str2 = str;
                        } catch (Resources.NotFoundException unused) {
                            str2 = str;
                            Logger.e("ShareApiManager getServices " + str2 + " is Not found!");
                        } catch (Exception e3) {
                            e2 = e3;
                            Logger.e("ShareApiManager getServices " + str + " Exception =" + e2);
                            str2 = str;
                        }
                    }
                    stringBuffer.append(iFShareApi.getJsonObject(iWebview));
                    if (i != this.b.size()) {
                        stringBuffer.append(",");
                    }
                } catch (Resources.NotFoundException unused2) {
                } catch (Exception e4) {
                    str = str2;
                    e2 = e4;
                }
                i++;
            }
        }
        stringBuffer.append(Operators.ARRAY_END_STR);
        return stringBuffer.toString();
    }

    public void a(IWebview iWebview, String str, String str2) {
        Uri uriFromFile;
        try {
            JSONObject jSONObject = new JSONObject(str2);
            String strOptString = jSONObject.optString("content");
            String strOptString2 = jSONObject.optString("title");
            String strOptString3 = jSONObject.optString("href");
            if (!TextUtils.isEmpty(strOptString3)) {
                strOptString = strOptString + GlideException.IndentedAppendable.INDENT + strOptString3;
            }
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("pictures");
            Intent intent = new Intent();
            if (!PdrUtil.isEmpty(jSONArrayOptJSONArray)) {
                ArrayList<Uri> arrayList = new ArrayList<>();
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    String strConvert2AbsFullPath = iWebview.obtainApp().convert2AbsFullPath(iWebview.obtainFullUrl(), jSONArrayOptJSONArray.optString(i));
                    if (strConvert2AbsFullPath.startsWith(DeviceInfo.sPrivateDir)) {
                        String str3 = DeviceInfo.sPrivateExternalDir + strConvert2AbsFullPath.substring(DeviceInfo.sPrivateDir.length());
                        if (DHFile.copyFile(strConvert2AbsFullPath, str3, true, false) == 1) {
                            strConvert2AbsFullPath = str3;
                        }
                    }
                    if (Build.VERSION.SDK_INT >= 24) {
                        uriFromFile = a(iWebview.getContext(), new File(strConvert2AbsFullPath), intent);
                    } else {
                        uriFromFile = Uri.fromFile(new File(strConvert2AbsFullPath));
                    }
                    arrayList.add(uriFromFile);
                }
                a(intent, strOptString, strOptString2, arrayList);
            } else {
                a(intent, strOptString, strOptString2, null);
            }
            Intent intentCreateChooser = Intent.createChooser(intent, "");
            intentCreateChooser.addFlags(1);
            iWebview.getActivity().startActivity(intentCreateChooser);
            Deprecated_JSUtil.execCallback(iWebview, str, "", JSUtil.OK, false, false);
        } catch (Exception unused) {
            Deprecated_JSUtil.execCallback(iWebview, str, StringUtil.format(DOMException.JSON_ERROR_INFO, -99, DOMException.MSG_UNKNOWN_ERROR), JSUtil.ERROR, true, false);
        }
    }

    private Intent a(Intent intent, String str, String str2, ArrayList<Uri> arrayList) {
        if (!PdrUtil.isEmpty(str)) {
            intent.putExtra("android.intent.extra.TEXT", str);
        }
        if (!PdrUtil.isEmpty(str2)) {
            intent.putExtra("android.intent.extra.SUBJECT", str2);
        }
        if (!PdrUtil.isEmpty(arrayList) && arrayList.size() > 0) {
            intent.setType("image/*");
            if (arrayList.size() > 1) {
                intent.setAction("android.intent.action.SEND_MULTIPLE");
                intent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList);
            } else {
                intent.setAction("android.intent.action.SEND");
                intent.putExtra("android.intent.extra.STREAM", arrayList.get(0));
            }
        } else {
            intent.setAction("android.intent.action.SEND");
            intent.setType("text/plain");
        }
        return intent;
    }

    public Uri a(Context context, File file, Intent intent) {
        String absolutePath = file.getAbsolutePath();
        Cursor cursorQuery = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{bq.d}, "_data=? ", new String[]{absolutePath}, null);
        Uri uriForFile = null;
        if (cursorQuery != null) {
            if (cursorQuery.moveToFirst()) {
                int i = cursorQuery.getInt(cursorQuery.getColumnIndex(bq.d));
                uriForFile = Uri.withAppendedPath(Uri.parse("content://media/external/images/media"), "" + i);
            }
            cursorQuery.close();
        }
        if (uriForFile == null) {
            uriForFile = FileProvider.getUriForFile(context, context.getPackageName() + ".dc.fileprovider", file);
        }
        if (uriForFile != null) {
            return uriForFile;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("_data", absolutePath);
        return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
    }

    public void a() {
        HashMap<String, IFShareApi> map = this.b;
        if (map != null) {
            Iterator<Map.Entry<String, IFShareApi>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                it.next().getValue().dispose();
            }
        }
        HashMap<String, ShareAuthorizeView> map2 = this.c;
        if (map2 != null) {
            Iterator<Map.Entry<String, ShareAuthorizeView>> it2 = map2.entrySet().iterator();
            while (it2.hasNext()) {
                it2.next().getValue().dispose();
            }
        }
        HashMap<String, String> map3 = this.d;
        if (map3 != null) {
            map3.clear();
        }
        HashMap<String, IFShareApi> map4 = this.b;
        if (map4 != null) {
            map4.clear();
        }
        HashMap<String, ShareAuthorizeView> map5 = this.c;
        if (map5 != null) {
            map5.clear();
        }
    }
}
