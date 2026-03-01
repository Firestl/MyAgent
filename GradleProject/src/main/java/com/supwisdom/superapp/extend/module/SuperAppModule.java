package com.supwisdom.superapp.extend.module;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.zxing.client.android.CaptureActivity;
import com.igexin.sdk.PushManager;
import com.supwisdom.superapp.WXBaseActivity;
import com.supwisdom.superapp.ui.activity.H5Activity;
import com.supwisdom.superapp.ui.activity.LoginActivity;
import com.supwisdom.zueb.R;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.umeng.analytics.MobclickAgent;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.feature.uniapp.common.UniModule;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import supwisdom.bj1;
import supwisdom.fn1;
import supwisdom.ij1;
import supwisdom.j7;
import supwisdom.qm1;
import supwisdom.r0;
import supwisdom.sh1;
import supwisdom.um1;
import supwisdom.y7;

/* JADX INFO: loaded from: classes2.dex */
public class SuperAppModule extends UniModule {
    public static final int CAMERA_PERMISSION_REQUEST_CODE = 9;
    public static final int SCAN_REQUEST_CODE = 1;
    public String contentType;
    public String desc;
    public bj1 hintDialog;
    public String icon;
    public JSCallback mCallback;
    public boolean mIsHandle = false;
    public byte[] mWechatDefaultIconBytes;
    public String mcontent;
    public ij1 shareDialog;
    public String title;

    public class a implements DialogInterface.OnClickListener {
        public a() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            j7.a((Activity) SuperAppModule.this.mWXSDKInstance.getContext(), new String[]{"android.permission.CAMERA"}, 9);
            SuperAppModule.this.hintDialog = new bj1(SuperAppModule.this.mWXSDKInstance.getContext(), AndroidResources.getString(R.string.string_camera), AndroidResources.getString(R.string.string_camera_content));
            SuperAppModule.this.hintDialog.show();
        }
    }

    private String imageTranslateUri(int i) {
        Resources resources = this.mWXSDKInstance.getContext().getResources();
        return Uri.parse("android.resource://" + resources.getResourcePackageName(i) + "/" + resources.getResourceTypeName(i) + "/" + resources.getResourceEntryName(i)).toString();
    }

    private void initService() {
    }

    private void initWechatDefaultIcon() {
        ByteArrayOutputStream byteArrayOutputStream;
        IOException e2;
        try {
            InputStream inputStreamOpen = this.mWXSDKInstance.getContext().getResources().getAssets().open("share/appicon.png");
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byte[] bArr = new byte[1024];
                while (inputStreamOpen.read(bArr) != -1) {
                    byteArrayOutputStream.write(bArr, 0, 1024);
                }
                byteArrayOutputStream.close();
                inputStreamOpen.close();
            } catch (IOException e3) {
                e2 = e3;
                e2.printStackTrace();
            }
        } catch (IOException e4) {
            byteArrayOutputStream = null;
            e2 = e4;
        }
        this.mWechatDefaultIconBytes = byteArrayOutputStream.toByteArray();
    }

    private void shareToWechat() {
    }

    private void shareToWechatTimeline() {
    }

    private void showCameraPermissionRationale() {
        if (this.mWXSDKInstance.getContext() instanceof Activity) {
            r0.a aVar = new r0.a(this.mWXSDKInstance.getContext());
            aVar.a("需要相机权限扫码二维码");
            aVar.a(android.R.string.ok, new a());
            aVar.a().show();
        }
    }

    @Override // com.taobao.weex.common.WXModule
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 1 || this.mIsHandle || this.mCallback == null || intent == null) {
            return;
        }
        String stringExtra = intent.getStringExtra(CaptureActivity.SCNA_CODE_RESULT);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("codeString", (Object) stringExtra);
        this.mCallback.invoke(jSONObject);
    }

    @Override // com.taobao.weex.common.WXModule
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 9) {
            if (iArr.length <= 0 || iArr[0] != 0) {
                Toast.makeText(this.mWXSDKInstance.getContext(), "请求相机权限失败", 0).show();
            } else {
                Intent intent = new Intent(this.mWXSDKInstance.getContext(), (Class<?>) CaptureActivity.class);
                intent.putExtra(CaptureActivity.KEY_HANDLE_SCAN_RESULT, this.mIsHandle);
                if (this.mIsHandle) {
                    this.mWXSDKInstance.getContext().startActivity(intent);
                } else {
                    ((Activity) this.mWXSDKInstance.getContext()).startActivityForResult(intent, 1);
                }
            }
            if (this.hintDialog.isShowing()) {
                this.hintDialog.dismiss();
            }
        }
    }

    @JSMethod
    public void refreshPage(JSONObject jSONObject) {
        this.mWXSDKInstance.refreshInstance(new HashMap());
    }

    @JSMethod
    public void sysCleanCache() {
    }

    @JSMethod
    public void sysGetCacheSize() {
    }

    @JSMethod(uiThread = true)
    public void sysGetUserToken(JSCallback jSCallback) {
        String strA = sh1.c.a(fn1.o, fn1.w);
        Log.d("小程序", "aa-----" + strA);
        jSCallback.invoke(strA);
    }

    @JSMethod
    public void sysGetVideoDuration() {
    }

    @JSMethod
    public void sysLogout() {
        um1.a(this.mWXSDKInstance.getContext());
        um1.a();
        fn1.w = "";
        sh1.c.b(fn1.o, "");
        sh1.c.b(fn1.n, "");
        sh1.c.b(fn1.m, "");
        sh1.c.b(fn1.l, "");
        Context context = this.mWXSDKInstance.getContext();
        PushManager.getInstance().unBindAlias(this.mWXSDKInstance.getContext(), sh1.c.c(fn1.t), false);
        MobclickAgent.onProfileSignOff();
        qm1.a(this.mWXSDKInstance.getContext(), 0);
        sh1.c.b(fn1.t, "");
        Intent intent = new Intent();
        intent.addFlags(268468224);
        intent.setClass(context, LoginActivity.class);
        context.startActivity(intent);
        ((Activity) context).finish();
    }

    @JSMethod(uiThread = true)
    public void sysNavigateToMiniProgram(JSONObject jSONObject) {
        Log.d("小程序", "sysNavigateToMiniProgram");
        String string = jSONObject.getString("engineType");
        String string2 = jSONObject.getString("loadPath");
        Log.d("小程序", "engineType" + string + "  loadPath" + string2);
        Log.d("小程序", new Gson().toJson(jSONObject));
        if (!string2.contains(".wgt")) {
            Intent intent = new Intent();
            intent.setClass(this.mWXSDKInstance.getContext(), H5Activity.class);
            intent.setData(Uri.parse(string2));
            this.mWXSDKInstance.getContext().startActivity(intent);
            return;
        }
        String strSubstring = string2.substring(0, string2.indexOf(AbsoluteConst.JSON_VALUE_POSITION_STATIC));
        Intent intent2 = new Intent();
        intent2.setClass(this.mWXSDKInstance.getContext(), H5Activity.class);
        intent2.setData(Uri.parse(strSubstring));
        this.mWXSDKInstance.getContext().startActivity(intent2);
    }

    @JSMethod
    public void sysNavigationBarTextStyle(String str) {
        if (str != null && str.trim().equalsIgnoreCase("light")) {
            WXBaseActivity.setLightStatusBar((Activity) this.mWXSDKInstance.getContext(), false);
        } else if (str != null && str.trim().equalsIgnoreCase("dark")) {
            WXBaseActivity.setLightStatusBar((Activity) this.mWXSDKInstance.getContext(), true);
        }
        Log.d("SysCommon", "" + str);
    }

    @JSMethod
    public void sysPickImage() {
    }

    @JSMethod(uiThread = true)
    public void sysScan(JSONObject jSONObject, JSCallback jSCallback) {
        Log.d("小程序", "sysScan");
        this.mIsHandle = jSONObject.getBoolean("isHandle").booleanValue();
        this.mCallback = jSCallback;
        if (y7.a(this.mWXSDKInstance.getContext(), "android.permission.CAMERA") != 0) {
            j7.a((Activity) this.mWXSDKInstance.getContext(), new String[]{"android.permission.CAMERA"}, 9);
            bj1 bj1Var = new bj1(this.mWXSDKInstance.getContext(), AndroidResources.getString(R.string.string_camera), AndroidResources.getString(R.string.string_camera_content));
            this.hintDialog = bj1Var;
            bj1Var.show();
            return;
        }
        Intent intent = new Intent(this.mWXSDKInstance.getContext(), (Class<?>) CaptureActivity.class);
        intent.putExtra(CaptureActivity.KEY_HANDLE_SCAN_RESULT, this.mIsHandle);
        if (this.mIsHandle) {
            this.mWXSDKInstance.getContext().startActivity(intent);
        } else {
            ((Activity) this.mWXSDKInstance.getContext()).startActivityForResult(intent, 1);
        }
    }

    @JSMethod
    public void sysVersionUpdate() {
    }
}
