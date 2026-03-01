package com.supwisdom.superapp.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Message;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.igexin.assist.sdk.AssistPushConsts;
import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;
import com.igexin.sdk.message.BindAliasCmdMessage;
import com.igexin.sdk.message.FeedbackCmdMessage;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTNotificationMessage;
import com.igexin.sdk.message.GTTransmitMessage;
import com.igexin.sdk.message.SetTagCmdMessage;
import com.igexin.sdk.message.UnBindAliasCmdMessage;
import com.supwisdom.superapp.WXApplication;
import com.supwisdom.superapp.ui.activity.H5Activity;
import com.supwisdom.superapp.ui.activity.LoginActivity;
import com.ta.utdid2.device.UTDevice;
import com.taobao.weex.common.Constants;
import com.umeng.analytics.MobclickAgent;
import io.dcloud.common.constant.AbsoluteConst;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import supwisdom.et1;
import supwisdom.fn1;
import supwisdom.mj1;
import supwisdom.qm1;
import supwisdom.sh1;
import supwisdom.th1;
import supwisdom.um1;

/* JADX INFO: loaded from: classes2.dex */
public class GetuiIntentService extends GTIntentService {

    public static class a implements Callback<et1> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f4005a;

        public a(String str) {
            this.f4005a = str;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<et1> call, Throwable th) {
            String str = this.f4005a;
            if (str == null || str.trim().equals("")) {
                WXApplication.CIDHandler.removeMessages(2);
            } else {
                Message message = new Message();
                message.what = 2;
                message.obj = this.f4005a;
                WXApplication.CIDHandler.sendMessageDelayed(message, 3000L);
            }
            th.printStackTrace();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<et1> call, Response<et1> response) {
            try {
                String strString = response.body().string();
                Log.e("GetuiSdkDemo", "onResponse: ====== " + strString);
                if (new JSONObject(strString).optInt("code") != 0) {
                    if (this.f4005a == null || this.f4005a.trim().equals("")) {
                        WXApplication.CIDHandler.removeMessages(2);
                    } else {
                        Message message = new Message();
                        message.what = 2;
                        message.obj = this.f4005a;
                        WXApplication.CIDHandler.sendMessageDelayed(message, 3000L);
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
                String str = this.f4005a;
                if (str == null || str.trim().equals("")) {
                    WXApplication.CIDHandler.removeMessages(2);
                    return;
                }
                Message message2 = new Message();
                message2.what = 2;
                message2.obj = this.f4005a;
                WXApplication.CIDHandler.sendMessageDelayed(message2, 3000L);
            }
        }
    }

    public static class b implements Callback<et1> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f4006a;

        public b(String str) {
            this.f4006a = str;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<et1> call, Throwable th) {
            String str = this.f4006a;
            if (str == null || str.trim().equals("")) {
                WXApplication.CIDHandler.removeMessages(1);
            } else {
                Message message = new Message();
                message.what = 1;
                message.obj = this.f4006a;
                WXApplication.CIDHandler.sendMessageDelayed(message, 3000L);
            }
            th.printStackTrace();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<et1> call, Response<et1> response) {
            try {
                if (new JSONObject(response.body().string()).optInt("code") != 0) {
                    if (this.f4006a == null || this.f4006a.trim().equals("")) {
                        WXApplication.CIDHandler.removeMessages(1);
                    } else {
                        Message message = new Message();
                        message.what = 1;
                        message.obj = this.f4006a;
                        WXApplication.CIDHandler.sendMessageDelayed(message, 3000L);
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
                String str = this.f4006a;
                if (str == null || str.trim().equals("")) {
                    WXApplication.CIDHandler.removeMessages(1);
                    return;
                }
                Message message2 = new Message();
                message2.what = 1;
                message2.obj = this.f4006a;
                WXApplication.CIDHandler.sendMessageDelayed(message2, 3000L);
            }
        }
    }

    public static void b(String str, String str2) {
        WXApplication.CIDHandler.removeMessages(2);
        mj1.b().a(str, str2).enqueue(new a(str));
    }

    public final void a(SetTagCmdMessage setTagCmdMessage) {
        Log.d("GetuiSdkDemo", "settag result sn = " + setTagCmdMessage.getSn() + ", code = " + setTagCmdMessage.getCode() + ", text = ");
    }

    @Override // com.igexin.sdk.GTIntentService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.igexin.sdk.GTIntentService
    public void onNotificationMessageArrived(Context context, GTNotificationMessage gTNotificationMessage) {
        Log.d("GetuiSdkDemo", "onNotificationMessageArrived -> appid = " + gTNotificationMessage.getAppid() + "\ntaskid = " + gTNotificationMessage.getTaskId() + "\nmessageid = " + gTNotificationMessage.getMessageId() + "\npkg = " + gTNotificationMessage.getPkgName() + "\ncid = " + gTNotificationMessage.getClientId() + "\ntitle = " + gTNotificationMessage.getTitle() + "\ncontent = " + gTNotificationMessage.getContent());
    }

    @Override // com.igexin.sdk.GTIntentService
    public void onNotificationMessageClicked(Context context, GTNotificationMessage gTNotificationMessage) {
        Log.d("GetuiSdkDemo", "onNotificationMessageClicked -> appid = " + gTNotificationMessage.getAppid() + "\ntaskid = " + gTNotificationMessage.getTaskId() + "\nmessageid = " + gTNotificationMessage.getMessageId() + "\npkg = " + gTNotificationMessage.getPkgName() + "\ncid = " + gTNotificationMessage.getClientId() + "\ntitle = " + gTNotificationMessage.getTitle() + "\ncontent = " + gTNotificationMessage.getContent());
    }

    @Override // com.igexin.sdk.GTIntentService
    public void onReceiveClientId(Context context, String str) {
        Log.e("GetuiSdkDemo", "onReceiveClientId -> clientid = " + str);
        sh1.c.b(fn1.q, str);
        String strA = sh1.c.a(fn1.o, "");
        if (strA == null || strA.equals("")) {
            return;
        }
        a(strA, str);
    }

    @Override // com.igexin.sdk.GTIntentService
    public void onReceiveCommandResult(Context context, GTCmdMessage gTCmdMessage) {
        Log.d("GetuiSdkDemo", "onReceiveCommandResult -> " + gTCmdMessage);
        int action = gTCmdMessage.getAction();
        if (action == 10009) {
            a((SetTagCmdMessage) gTCmdMessage);
            return;
        }
        if (action == 10010) {
            a((BindAliasCmdMessage) gTCmdMessage);
        } else if (action == 10011) {
            a((UnBindAliasCmdMessage) gTCmdMessage);
        } else if (action == 10006) {
            a((FeedbackCmdMessage) gTCmdMessage);
        }
    }

    @Override // com.igexin.sdk.GTIntentService
    public void onReceiveDeviceToken(Context context, String str) {
        super.onReceiveDeviceToken(context, str);
        fn1.x = str;
        Log.d("GetuiSdkDemo", "onReceiveCommandResult -> deviceToken = " + str);
    }

    @Override // com.igexin.sdk.GTIntentService
    public void onReceiveMessageData(Context context, GTTransmitMessage gTTransmitMessage) {
        String appid = gTTransmitMessage.getAppid();
        String taskId = gTTransmitMessage.getTaskId();
        String messageId = gTTransmitMessage.getMessageId();
        byte[] payload = gTTransmitMessage.getPayload();
        String pkgName = gTTransmitMessage.getPkgName();
        String clientId = gTTransmitMessage.getClientId();
        boolean zSendFeedbackMessage = PushManager.getInstance().sendFeedbackMessage(context, taskId, messageId, PushConsts.MIN_FEEDBACK_ACTION);
        StringBuilder sb = new StringBuilder();
        sb.append("call sendFeedbackMessage = ");
        sb.append(zSendFeedbackMessage ? "success" : AbsoluteConst.EVENTS_FAILED);
        Log.d("GetuiSdkDemo", sb.toString());
        Log.d("GetuiSdkDemo", "onReceiveMessageData -> appid = " + appid + "\ntaskid = " + taskId + "\nmessageid = " + messageId + "\npkg = " + pkgName + "\ncid = " + clientId);
        if (payload == null) {
            Log.e("GetuiSdkDemo", "receiver payload = null");
        } else {
            String str = new String(payload);
            Log.d("GetuiSdkDemo", "receiver payload = " + str);
            com.alibaba.fastjson.JSONObject object = JSON.parseObject(JSON.parseObject(str).getString(AssistPushConsts.MSG_TYPE_PAYLOAD));
            String string = object.getString("messageType");
            String string2 = object.getString("userId");
            String string3 = object.getString("deviceId");
            String strC = sh1.c.c(fn1.t);
            if (string.equals("Password") || string.equals("login") || string.equals(Constants.Value.PASSWORD)) {
                if (string2.equals(strC) && !string3.equals(UTDevice.getUtdid(this))) {
                    a(context, string);
                }
            } else if (string.equals("attestApppush")) {
                Intent intent = new Intent(this, (Class<?>) H5Activity.class);
                String str2 = object.getString("callbackUrl") + "&idToken=" + fn1.w;
                intent.putExtra(H5Activity.z0, false);
                intent.setData(Uri.parse(str2));
                startActivity(intent);
            } else {
                object.put("type", (Object) Constants.Event.CLICK);
                object.put(RemoteMessageConst.MSGID, (Object) messageId);
                th1.c.a("", messageId, new Gson().toJson(object));
                List arrayList = (List) th1.c.a("", th1.b);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(messageId);
                th1.c.a("", th1.b, arrayList);
                Log.d("GetuiSdkDemo", new Gson().toJson(object));
                try {
                    WXApplication.homeUniMP.sendUniMPEvent("addEventListener", object);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        Log.d("GetuiSdkDemo", "----------------------------------------------------------------------------------------------");
    }

    @Override // com.igexin.sdk.GTIntentService
    public void onReceiveOnlineState(Context context, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("onReceiveOnlineState -> ");
        sb.append(z ? "online" : "offline");
        Log.d("GetuiSdkDemo", sb.toString());
    }

    @Override // com.igexin.sdk.GTIntentService
    public void onReceiveServicePid(Context context, int i) {
        Log.d("GetuiSdkDemo", "onReceiveServicePid -> " + i);
    }

    public final void a(BindAliasCmdMessage bindAliasCmdMessage) {
        Log.d("GetuiSdkDemo", "bindAlias result sn = " + bindAliasCmdMessage.getSn() + ", code = " + bindAliasCmdMessage.getCode() + ", text = ");
    }

    public final void a(UnBindAliasCmdMessage unBindAliasCmdMessage) {
        Log.d("GetuiSdkDemo", "unbindAlias result sn = " + unBindAliasCmdMessage.getSn() + ", code = " + unBindAliasCmdMessage.getCode() + ", text = ");
    }

    public final void a(FeedbackCmdMessage feedbackCmdMessage) {
        String appid = feedbackCmdMessage.getAppid();
        String taskId = feedbackCmdMessage.getTaskId();
        String actionId = feedbackCmdMessage.getActionId();
        String result = feedbackCmdMessage.getResult();
        long timeStamp = feedbackCmdMessage.getTimeStamp();
        Log.d("GetuiSdkDemo", "onReceiveCommandResult -> appid = " + appid + "\ntaskid = " + taskId + "\nactionid = " + actionId + "\nresult = " + result + "\ncid = " + feedbackCmdMessage.getClientId() + "\ntimestamp = " + timeStamp);
    }

    public static void a(Context context, String str) {
        um1.a(context);
        um1.a();
        b(fn1.w, sh1.c.c(fn1.q));
        fn1.w = "";
        sh1.c.b(fn1.o, "");
        sh1.c.b(fn1.n, "");
        sh1.c.b(fn1.m, "");
        sh1.c.b(fn1.l, "");
        PushManager.getInstance().unBindAlias(context, sh1.c.c(fn1.t), false);
        MobclickAgent.onProfileSignOff();
        qm1.a(context, 0);
        sh1.c.b(fn1.t, "");
        Intent intent = new Intent();
        intent.addFlags(268468224);
        intent.setClass(context, LoginActivity.class);
        intent.putExtra("messageTypeSuperApp", str);
        context.startActivity(intent);
        ((Activity) context).finish();
    }

    public static void a(String str, String str2) {
        WXApplication.CIDHandler.removeMessages(1);
        mj1.b().g(str, str2).enqueue(new b(str));
    }
}
