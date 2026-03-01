package io.dcloud.common.ui;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.internal.bind.TypeAdapters;
import io.dcloud.PdrR;
import io.dcloud.base.R;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.adapter.util.SP;
import io.dcloud.common.ui.Info.AndroidPrivacyResponse;
import io.dcloud.common.ui.a;
import io.dcloud.common.util.AppRuntime;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.TitleNViewUtil;
import io.dcloud.common.util.language.LanguageUtil;
import io.dcloud.feature.internal.sdk.SDK;
import io.dcloud.js.map.amap.util.AMapUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static b f6457e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AndroidPrivacyResponse f6458a = new AndroidPrivacyResponse();
    public boolean b = false;
    public boolean c = false;
    public io.dcloud.common.ui.a d;

    public class a implements a.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Activity f6459a;
        public final /* synthetic */ InterfaceC0157b b;

        public a(Activity activity, InterfaceC0157b interfaceC0157b) {
            this.f6459a = activity;
            this.b = interfaceC0157b;
        }

        @Override // io.dcloud.common.ui.a.d
        public void a(String str) {
            this.f6459a.getSharedPreferences("privacy_config_uni_sp_file", 0).edit().putString("privacy_config_version_uni_current_key", str).apply();
            SP.setBundleData(this.f6459a, "pdr", "scok", "1");
            this.b.a();
        }

        @Override // io.dcloud.common.ui.a.d
        public void onCancel() {
            SP.setBundleData(this.f6459a, "pdr", "scok", "0");
            this.b.a(b.this.f6458a);
        }
    }

    /* JADX INFO: renamed from: io.dcloud.common.ui.b$b, reason: collision with other inner class name */
    public interface InterfaceC0157b {
        void a();

        void a(AndroidPrivacyResponse androidPrivacyResponse);

        void a(String str);
    }

    private boolean d(Context context) {
        String string = context.getSharedPreferences("privacy_config_uni_sp_file", 0).getString("privacy_config_version_uni_current_key", "emptyPrivacyVersion");
        if (string.equals(this.f6458a.version) || "emptyPrivacyVersion".equals(string)) {
            if (!this.f6458a.prompt.equals("template") || "1".equals(SP.getBundleData(context, "pdr", "scok"))) {
                return false;
            }
            SP.setBundleData(context, "pdr", "scok", "0");
            return true;
        }
        SP.setBundleData(context, "pdr", "scok", "");
        if (!this.f6458a.prompt.equals("template")) {
            return false;
        }
        SP.setBundleData(context, "pdr", "scok", "0");
        return true;
    }

    public boolean b() {
        return this.c;
    }

    public boolean c(Context context) {
        this.b = false;
        a(context);
        return (SDK.isUniMPSDK() || !"template".equals(this.f6458a.prompt) || context.getSharedPreferences("privacy_config_uni_sp_file", 0).getString("privacy_config_version_uni_current_key", "").equals(this.f6458a.version)) ? false : true;
    }

    public static b a() {
        if (f6457e == null) {
            f6457e = new b();
        }
        return f6457e;
    }

    public boolean b(Context context) {
        if (!this.b) {
            a(context);
        }
        String str = this.f6458a.prompt;
        String bundleData = SP.getBundleData(context, "pdr", "scok");
        return (PdrUtil.isEmpty(bundleData) || !bundleData.equals("1")) && !PdrUtil.isEmpty(str) && str.equalsIgnoreCase("template");
    }

    public void a(Context context) {
        JSONObject object;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        JSONObject object2;
        if (this.b) {
            return;
        }
        this.c = AppRuntime.isAppResourcesInAssetsPath(context, "");
        String str = BaseInfo.sCacheFsAppsPath;
        String str2 = BaseInfo.sDefaultBootApp + "/www/androidPrivacy.json";
        JSONObject jSONObject = null;
        Object object3 = null;
        Object obj = null;
        jSONObject = null;
        if (!this.c) {
            File file = new File(str + str2);
            if (file.exists()) {
                String strA = a(file.getPath());
                try {
                    Object object4 = JSON.parseObject(strA, (Class<Object>) AndroidPrivacyResponse.class);
                    object2 = JSON.parseObject(strA);
                    obj = object4;
                } catch (Exception unused) {
                    Log.e("uniapp", "privacy json format error");
                    object2 = null;
                }
                if (obj instanceof AndroidPrivacyResponse) {
                    this.f6458a = (AndroidPrivacyResponse) obj;
                } else {
                    this.f6458a = new AndroidPrivacyResponse();
                }
                jSONObject = object2;
            }
        } else {
            String strA2 = a(context, "apps/" + str2);
            if (!TextUtils.isEmpty(strA2)) {
                try {
                    object3 = JSON.parseObject(strA2, (Class<Object>) AndroidPrivacyResponse.class);
                    object = JSON.parseObject(strA2);
                } catch (Exception unused2) {
                    Log.e("uniapp", "privacy json format error");
                    object = null;
                }
                if (object3 instanceof AndroidPrivacyResponse) {
                    this.f6458a = (AndroidPrivacyResponse) object3;
                } else {
                    this.f6458a = new AndroidPrivacyResponse();
                }
                jSONObject = object;
            }
        }
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        if (this.f6458a == null) {
            this.f6458a = new AndroidPrivacyResponse();
        }
        AndroidPrivacyResponse androidPrivacyResponse = this.f6458a;
        if (androidPrivacyResponse.second == null) {
            androidPrivacyResponse.second = new AndroidPrivacyResponse.SecondDTO();
        }
        AndroidPrivacyResponse androidPrivacyResponse2 = this.f6458a;
        if (androidPrivacyResponse2.styles == null) {
            androidPrivacyResponse2.styles = new AndroidPrivacyResponse.StylesDTO();
        }
        AndroidPrivacyResponse.StylesDTO stylesDTO = this.f6458a.styles;
        if (stylesDTO.title == null) {
            stylesDTO.title = new AndroidPrivacyResponse.StylesDTO.TitleDTO();
        }
        AndroidPrivacyResponse.StylesDTO stylesDTO2 = this.f6458a.styles;
        if (stylesDTO2.buttonRefuse == null) {
            stylesDTO2.buttonRefuse = new AndroidPrivacyResponse.StylesDTO.ButtonRefuseDTO();
        }
        AndroidPrivacyResponse.StylesDTO stylesDTO3 = this.f6458a.styles;
        if (stylesDTO3.buttonAccept == null) {
            stylesDTO3.buttonAccept = new AndroidPrivacyResponse.StylesDTO.ButtonAcceptDTO();
        }
        JSONObject jSONObject2 = jSONObject.getJSONObject("titleLocales");
        if (jSONObject2 != null) {
            AndroidPrivacyResponse androidPrivacyResponse3 = this.f6458a;
            androidPrivacyResponse3.title = LanguageUtil.getString(jSONObject2, androidPrivacyResponse3.title);
        }
        JSONObject jSONObject3 = jSONObject.getJSONObject("messageLocales");
        if (jSONObject3 != null) {
            AndroidPrivacyResponse androidPrivacyResponse4 = this.f6458a;
            androidPrivacyResponse4.message = LanguageUtil.getString(jSONObject3, androidPrivacyResponse4.message);
        }
        JSONObject jSONObject4 = jSONObject.getJSONObject("buttonAcceptLocales");
        if (jSONObject4 != null) {
            AndroidPrivacyResponse androidPrivacyResponse5 = this.f6458a;
            androidPrivacyResponse5.buttonAccept = LanguageUtil.getString(jSONObject4, androidPrivacyResponse5.buttonAccept);
        }
        JSONObject jSONObject5 = jSONObject.getJSONObject("buttonRefuseLocales");
        if (jSONObject5 != null) {
            AndroidPrivacyResponse androidPrivacyResponse6 = this.f6458a;
            androidPrivacyResponse6.buttonRefuse = LanguageUtil.getString(jSONObject5, androidPrivacyResponse6.buttonRefuse);
        }
        JSONObject jSONObject6 = jSONObject.getJSONObject(TypeAdapters.AnonymousClass27.SECOND);
        if (jSONObject6 != null) {
            JSONObject jSONObject7 = jSONObject6.getJSONObject("titleLocales");
            if (jSONObject7 != null) {
                AndroidPrivacyResponse.SecondDTO secondDTO = this.f6458a.second;
                secondDTO.title = LanguageUtil.getString(jSONObject7, secondDTO.title);
            }
            JSONObject jSONObject8 = jSONObject6.getJSONObject("messageLocales");
            if (jSONObject8 != null) {
                AndroidPrivacyResponse.SecondDTO secondDTO2 = this.f6458a.second;
                secondDTO2.message = LanguageUtil.getString(jSONObject8, secondDTO2.message);
            }
            JSONObject jSONObject9 = jSONObject6.getJSONObject("buttonAcceptLocales");
            if (jSONObject9 != null) {
                AndroidPrivacyResponse.SecondDTO secondDTO3 = this.f6458a.second;
                secondDTO3.buttonAccept = LanguageUtil.getString(jSONObject9, secondDTO3.buttonAccept);
            }
            JSONObject jSONObject10 = jSONObject6.getJSONObject("buttonRefuseLocales");
            if (jSONObject10 != null) {
                AndroidPrivacyResponse.SecondDTO secondDTO4 = this.f6458a.second;
                secondDTO4.buttonRefuse = LanguageUtil.getString(jSONObject10, secondDTO4.buttonRefuse);
            }
        }
        if (TextUtils.isEmpty(this.f6458a.version)) {
            this.f6458a.version = "emptyPrivacyVersion";
        }
        if (TextUtils.isEmpty(this.f6458a.prompt)) {
            String metaValue = AndroidResources.getMetaValue("DCLOUD_PRIVACY_PROMPT");
            if (metaValue == null) {
                metaValue = "";
            }
            this.f6458a.prompt = metaValue;
        }
        if (TextUtils.isEmpty(this.f6458a.title) && (i8 = PdrR.getInt(context, "string", "dcloud_privacy_prompt_title")) != 0) {
            String string = context.getString(i8);
            if (string == null) {
                string = "";
            }
            this.f6458a.title = string;
        }
        if (TextUtils.isEmpty(this.f6458a.message) && (i7 = PdrR.getInt(context, "string", "dcloud_privacy_prompt_message")) != 0) {
            String string2 = context.getString(i7);
            if (string2 == null) {
                string2 = "";
            }
            this.f6458a.message = string2;
        }
        if (TextUtils.isEmpty(this.f6458a.buttonAccept) && (i6 = PdrR.getInt(context, "string", "dcloud_privacy_prompt_accept_button_text")) != 0) {
            String string3 = context.getString(i6);
            if (string3 == null) {
                string3 = "";
            }
            this.f6458a.buttonAccept = string3;
        }
        if (TextUtils.isEmpty(this.f6458a.buttonRefuse) && (i5 = PdrR.getInt(context, "string", "dcloud_privacy_prompt_refuse_button_text")) != 0) {
            String string4 = context.getString(i5);
            if (string4 == null) {
                string4 = "";
            }
            this.f6458a.buttonRefuse = string4;
        }
        if (TextUtils.isEmpty(this.f6458a.second.message) && (i4 = PdrR.getInt(context, "string", "dcloud_second_privacy_prompt_message")) != 0) {
            String string5 = context.getString(i4);
            if (string5 == null) {
                string5 = "";
            }
            this.f6458a.second.message = string5;
        }
        if (TextUtils.isEmpty(this.f6458a.second.title) && (i3 = PdrR.getInt(context, "string", "dcloud_second_privacy_prompt_title")) != 0) {
            String string6 = context.getString(i3);
            if (string6 == null) {
                string6 = "";
            }
            this.f6458a.second.title = string6;
        }
        if (TextUtils.isEmpty(this.f6458a.second.buttonAccept) && (i2 = PdrR.getInt(context, "string", "dcloud_second_privacy_prompt_accept_button_text")) != 0) {
            String string7 = context.getString(i2);
            if (string7 == null) {
                string7 = "";
            }
            this.f6458a.second.buttonAccept = string7;
        }
        if (TextUtils.isEmpty(this.f6458a.second.buttonRefuse) && (i = PdrR.getInt(context, "string", "dcloud_second_privacy_prompt_refuse_button_text")) != 0) {
            String string8 = context.getString(i);
            this.f6458a.second.buttonRefuse = string8 != null ? string8 : "";
        }
        if (TextUtils.isEmpty(this.f6458a.styles.backgroundColor)) {
            this.f6458a.styles.backgroundColor = TitleNViewUtil.TRANSPARENT_BUTTON_TEXT_COLOR;
        }
        if (TextUtils.isEmpty(this.f6458a.styles.borderRadius)) {
            this.f6458a.styles.borderRadius = "10px";
        }
        if (TextUtils.isEmpty(this.f6458a.styles.title.color)) {
            this.f6458a.styles.title.color = AMapUtil.HtmlBlack;
        }
        if (TextUtils.isEmpty(this.f6458a.styles.buttonAccept.color)) {
            this.f6458a.styles.buttonAccept.color = AMapUtil.HtmlBlack;
        }
        if (TextUtils.isEmpty(this.f6458a.styles.buttonRefuse.color)) {
            this.f6458a.styles.buttonRefuse.color = AMapUtil.HtmlBlack;
        }
        if (TextUtils.isEmpty(this.f6458a.buttonAccept)) {
            this.f6458a.styles.buttonAccept.color = AMapUtil.HtmlBlack;
        }
        if (TextUtils.isEmpty(this.f6458a.styles.buttonRefuse.color)) {
            this.f6458a.styles.buttonRefuse.color = AMapUtil.HtmlBlack;
        }
        this.b = true;
    }

    public static String a(String str) throws Throwable {
        File file = new File(str);
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = null;
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file));
                while (true) {
                    try {
                        String line = bufferedReader2.readLine();
                        if (line == null) {
                            break;
                        }
                        stringBuffer.append(line);
                    } catch (IOException e2) {
                        e = e2;
                        bufferedReader = bufferedReader2;
                        e.printStackTrace();
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        return stringBuffer.toString();
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
                bufferedReader2.close();
                String string = stringBuffer.toString();
                try {
                    bufferedReader2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
                return string;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e6) {
            e = e6;
        }
    }

    public static String a(Context context, String str) {
        try {
            InputStream inputStreamOpen = context.getResources().getAssets().open(str);
            byte[] bArr = new byte[inputStreamOpen.available()];
            inputStreamOpen.read(bArr);
            return new String(bArr);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public void a(Activity activity, InterfaceC0157b interfaceC0157b, boolean z, boolean z2) {
        if (!d(activity)) {
            interfaceC0157b.a(this.f6458a.prompt);
            return;
        }
        a aVar = new a(activity, interfaceC0157b);
        io.dcloud.common.ui.a aVar2 = this.d;
        if (aVar2 != null) {
            aVar2.a();
            this.d = null;
        }
        if (z) {
            io.dcloud.common.ui.a aVar3 = new io.dcloud.common.ui.a(activity);
            this.d = aVar3;
            aVar3.a(interfaceC0157b);
            this.d.a(true);
            this.d.a(this.f6458a, true, (a.d) aVar);
            this.d.a(R.layout.dcloud_custom_privacy_second_dialog_layout);
            this.d.d();
            return;
        }
        io.dcloud.common.ui.a aVar4 = new io.dcloud.common.ui.a(activity);
        this.d = aVar4;
        aVar4.a(interfaceC0157b);
        this.d.a(false);
        this.d.a(R.layout.dcloud_custom_privacy_dialog_layout);
        this.d.a(this.f6458a, false, (a.d) aVar);
        this.d.d();
        if (z2 || !PdrUtil.checkIntl()) {
            return;
        }
        "zh-CN".equalsIgnoreCase(LanguageUtil.getDeviceDefLocalLanguage());
    }

    public void a(Activity activity) {
        io.dcloud.common.ui.a aVar = this.d;
        if (aVar != null) {
            InterfaceC0157b interfaceC0157bB = aVar.b();
            boolean zC = this.d.c();
            this.d.a();
            this.d = null;
            a(activity, interfaceC0157bB, zC, true);
        }
    }
}
