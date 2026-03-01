package io.dcloud.feature.pdr;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.igexin.assist.util.AssistUtils;
import com.vivo.push.PushClientConstants;
import io.dcloud.PandoraEntry;
import io.dcloud.PdrR;
import io.dcloud.WebviewActivity;
import io.dcloud.base.R;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.DHInterface.message.ActionBus;
import io.dcloud.common.DHInterface.message.action.BadgeSyncAction;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.adapter.util.MessageHandler;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class RuntimeFeatureImpl implements IFeature, MessageHandler.IMessages {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f6574a = PandoraEntry.class.getName();
    public AbsMgr b = null;

    public class a extends Thread {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String[] f6575a;
        public final /* synthetic */ String b;
        public final /* synthetic */ IWebview c;

        public a(String[] strArr, String str, IWebview iWebview) {
            this.f6575a = strArr;
            this.b = str;
            this.c = iWebview;
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            String[] strArr = this.f6575a;
            String str = strArr[1];
            Object[] objArr = (Object[]) RuntimeFeatureImpl.this.b.processEvent(IMgr.MgrType.AppMgr, 4, new Object[]{this.b, !PdrUtil.isEmpty(strArr[2]) ? JSONUtil.createJSONObject(this.f6575a[2]) : null, this.c});
            boolean zBooleanValue = Boolean.valueOf(String.valueOf(objArr[0])).booleanValue();
            String strValueOf = String.valueOf(objArr[1]);
            if (zBooleanValue) {
                Deprecated_JSUtil.execCallback(this.c, str, strValueOf, JSUtil.ERROR, true, false);
            } else {
                Deprecated_JSUtil.execCallback(this.c, str, strValueOf, JSUtil.OK, true, false);
            }
        }
    }

    public class b implements ICallBack {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ IWebview f6576a;
        public final /* synthetic */ String b;

        public b(RuntimeFeatureImpl runtimeFeatureImpl, IWebview iWebview, String str) {
            this.f6576a = iWebview;
            this.b = str;
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            if (i == 1) {
                return null;
            }
            Deprecated_JSUtil.excCallbackError(this.f6576a, this.b, String.valueOf(obj), true);
            return null;
        }
    }

    public class c implements ICallBack {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ IWebview f6577a;
        public final /* synthetic */ String b;

        public c(RuntimeFeatureImpl runtimeFeatureImpl, IWebview iWebview, String str) {
            this.f6577a = iWebview;
            this.b = str;
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            if (i == 1) {
                Deprecated_JSUtil.execCallback(this.f6577a, this.b, "{}", JSUtil.OK, true, false);
                return null;
            }
            Deprecated_JSUtil.excCallbackError(this.f6577a, this.b, String.valueOf(obj), true);
            return null;
        }
    }

    private void a(IWebview iWebview, String str, String str2) throws Throwable {
        String strValueOf = (TextUtils.isEmpty(str) || str.equals("0")) ? "" : String.valueOf(Math.max(0, Integer.valueOf(str).intValue()));
        try {
            String str3 = Build.MANUFACTURER;
            if (str3.equalsIgnoreCase("Xiaomi")) {
                b(iWebview, strValueOf, str2);
                return;
            }
            if (str3.equalsIgnoreCase("samsung")) {
                b(iWebview, strValueOf);
                return;
            }
            if (str3.toLowerCase().contains("sony")) {
                c(iWebview, strValueOf);
                return;
            }
            if (str3.toLowerCase().contains(AssistUtils.BRAND_HW)) {
                d(iWebview, strValueOf);
            } else if (str3.toLowerCase().contains(AssistUtils.BRAND_VIVO)) {
                f(iWebview, strValueOf);
            } else if (str3.toLowerCase().contains(AssistUtils.BRAND_OPPO)) {
                e(iWebview, strValueOf);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void b(IWebview iWebview, String str, String str2) throws Throwable {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(str2);
        } catch (JSONException unused) {
            jSONObject = new JSONObject();
        }
        NotificationManager notificationManager = (NotificationManager) iWebview.getContext().getSystemService("notification");
        Notification notificationBuild = null;
        boolean z = false;
        try {
            try {
                Notification.Builder builder = Build.VERSION.SDK_INT >= 26 ? new Notification.Builder(iWebview.getContext(), "LOCAL_BADGE_NUM") : new Notification.Builder(iWebview.getContext());
                builder.setContentText(PdrUtil.isEmpty(jSONObject.optString("content")) ? StringUtil.format(iWebview.getContext().getString(R.string.dcloud_common_msg_unread_prompt), str) : jSONObject.optString("content"));
                builder.setAutoCancel(true);
                int i = PdrR.getInt(iWebview.getContext(), "drawable", "push");
                if (i <= 0) {
                    builder.setSmallIcon(iWebview.getContext().getApplicationInfo().icon);
                } else {
                    builder.setSmallIcon(i);
                }
                builder.setDefaults(4);
                String packageName = iWebview.getActivity().getPackageName();
                PackageManager packageManager = iWebview.getActivity().getPackageManager();
                Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(packageName);
                builder.setContentTitle(PdrUtil.isEmpty(jSONObject.optString("title")) ? AndroidResources.mApplicationInfo.applicationInfo.loadLabel(packageManager) : jSONObject.optString("title"));
                builder.setContentIntent(PendingIntent.getActivity(iWebview.getActivity(), 10019, launchIntentForPackage, 1073741824));
                builder.setAutoCancel(true);
                notificationBuild = builder.build();
                notificationBuild.flags = 16;
                Object obj = notificationBuild.getClass().getDeclaredField("extraNotification").get(notificationBuild);
                obj.getClass().getDeclaredMethod("setMessageCount", Integer.TYPE).invoke(obj, Integer.valueOf(Integer.parseInt(str)));
                if (notificationBuild != null) {
                    notificationManager.notify(101010, notificationBuild);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                try {
                    Intent intent = new Intent("android.intent.action.APPLICATION_MESSAGE_UPDATE");
                    intent.putExtra("android.intent.extra.update_application_component_name", this.f6574a);
                    intent.putExtra("android.intent.extra.update_application_message_text", str);
                    iWebview.getContext().sendBroadcast(intent);
                } catch (Throwable th) {
                    th = th;
                    if (notificationBuild != null) {
                        notificationManager.notify(101010, notificationBuild);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            z = true;
            if (notificationBuild != null && z) {
                notificationManager.notify(101010, notificationBuild);
            }
            throw th;
        }
    }

    private void c(IWebview iWebview, String str) {
        if (iWebview.getContext().getPackageManager().resolveContentProvider("com.sonymobile.home.resourceprovider", 0) == null) {
            Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
            intent.putExtra("badge_count", str);
            intent.putExtra("badge_count_package_name", iWebview.getContext().getPackageName());
            intent.putExtra("badge_count_class_name", this.f6574a);
            iWebview.getActivity().sendBroadcast(intent);
            return;
        }
        boolean z = Integer.parseInt(str) != 0;
        Intent intent2 = new Intent();
        intent2.setAction("com.sonyericsson.home.action.UPDATE_BADGE");
        intent2.putExtra("com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE", z);
        intent2.putExtra("com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME", this.f6574a);
        intent2.putExtra("com.sonyericsson.home.intent.extra.badge.MESSAGE", str);
        intent2.putExtra("com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME", iWebview.getContext().getPackageName());
        iWebview.getContext().sendBroadcast(intent2);
    }

    private void d(IWebview iWebview, String str) {
        int i = !str.equals("") ? Integer.parseInt(str) : 0;
        int i2 = i >= 0 ? i : 0;
        Bundle bundle = new Bundle();
        bundle.putString("package", iWebview.getContext().getPackageName());
        bundle.putString("class", iWebview.getContext().getPackageManager().getLaunchIntentForPackage(iWebview.getContext().getPackageName()).getComponent().getClassName());
        bundle.putInt("badgenumber", i2);
        iWebview.getContext().getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", (String) null, bundle);
        ActionBus.getInstance().sendToBus(BadgeSyncAction.obtain(BadgeSyncAction.ENUM_ACTION_TYPE.SYNC_NUM).setSyncNum(i2));
    }

    private void e(IWebview iWebview, String str) {
        int i = Integer.parseInt(str);
        if (i == 0) {
            i = -1;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("app_badge_count", i);
        iWebview.getContext().getContentResolver().call(Uri.parse("content://com.android.badge/badge"), "setAppBadgeCount", (String) null, bundle);
    }

    private void f(IWebview iWebview, String str) {
        try {
            Intent intent = new Intent("launcher.action.CHANGE_APPLICATION_NOTIFICATION_NUM");
            intent.putExtra("packageName", iWebview.getContext().getPackageName());
            intent.putExtra(PushClientConstants.TAG_CLASS_NAME, iWebview.getContext().getPackageManager().getLaunchIntentForPackage(iWebview.getContext().getPackageName()).getComponent().getClassName());
            intent.putExtra("notificationNum", Integer.parseInt(str));
            iWebview.getContext().sendBroadcast(intent);
        } catch (Exception unused) {
        }
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void dispose(String str) {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x01c9, code lost:
    
        if (io.dcloud.common.util.BaseInfo.existsLibso() != false) goto L133;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x01f4, code lost:
    
        if (r16.obtainFrameView().obtainApp().checkIsCustomPath() != false) goto L133;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:41:0x007d  */
    @Override // io.dcloud.common.DHInterface.IFeature
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String execute(io.dcloud.common.DHInterface.IWebview r16, java.lang.String r17, java.lang.String[] r18) {
        /*
            Method dump skipped, instruction units count: 652
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.pdr.RuntimeFeatureImpl.execute(io.dcloud.common.DHInterface.IWebview, java.lang.String, java.lang.String[]):java.lang.String");
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void init(AbsMgr absMgr, String str) {
        this.b = absMgr;
        if (Build.VERSION.SDK_INT < 26 || !Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {
            return;
        }
        NotificationManager notificationManager = (NotificationManager) absMgr.getContext().getSystemService("notification");
        notificationManager.createNotificationChannelGroup(new NotificationChannelGroup("LOCAL_BADGE_SETTING", "badge"));
        NotificationChannel notificationChannel = new NotificationChannel("LOCAL_BADGE_NUM", absMgr.getContext().getString(R.string.dcloud_nf_desktop_icon_corner), 3);
        notificationChannel.enableLights(true);
        notificationChannel.setShowBadge(true);
        notificationManager.createNotificationChannel(notificationChannel);
    }

    private void a(IWebview iWebview, String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            Intent intent = new Intent();
            intent.setClass(iWebview.getActivity(), WebviewActivity.class);
            intent.putExtra("url", str);
            intent.setData(Uri.parse(str));
            intent.setAction("android.intent.action.VIEW");
            intent.setFlags(268435456);
            iWebview.getContext().startActivity(intent);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void a(String str, IApp iApp) {
        if (TextUtils.isEmpty(str) || !BaseInfo.ISAMU) {
            return;
        }
        int length = str.length();
        if ((length - str.indexOf(".apk")) - 4 == 0 || (length - str.indexOf(".wgt")) - 4 == 0 || (length - str.indexOf(".wgtu")) - 5 == 0) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("type", "install");
                jSONObject.put("file", str);
                jSONObject.put("appid", iApp.obtainOriginalAppId());
                jSONObject.put("version", iApp.obtainAppVersionName());
                Log.i(AbsoluteConst.HBUILDER_TAG, jSONObject.toString());
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    private void b(IWebview iWebview, String str) {
        Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        intent.putExtra("badge_count", Integer.parseInt(str));
        intent.putExtra("badge_count_package_name", iWebview.getContext().getPackageName());
        intent.putExtra("badge_count_class_name", this.f6574a);
        iWebview.getContext().sendBroadcast(intent);
    }

    private void b(String str, IApp iApp) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", "openurl");
            jSONObject.put("url", str);
            jSONObject.put("appid", iApp.obtainOriginalAppId());
            jSONObject.put("version", iApp.obtainAppVersionName());
            Log.i(AbsoluteConst.HBUILDER_TAG, jSONObject.toString());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:101:0x01de  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00a6  */
    @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void execute(java.lang.Object r17) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 878
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.pdr.RuntimeFeatureImpl.execute(java.lang.Object):void");
    }
}
