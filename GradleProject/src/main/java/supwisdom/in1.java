package supwisdom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dmcbig.mediapicker.utils.FileUtils;
import com.google.gson.Gson;
import com.huawei.hms.framework.common.ContainerUtils;
import com.igexin.sdk.PushManager;
import com.igexin.sdk.Tag;
import com.supwisdom.superapp.service.GetuiIntentService;
import com.supwisdom.superapp.service.model.AccountInfo;
import com.supwisdom.superapp.service.model.Response;
import com.supwisdom.zueb.R;
import com.ta.utdid2.device.UTDevice;
import com.umeng.analytics.MobclickAgent;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONException;
import retrofit2.Call;
import retrofit2.Callback;

/* JADX INFO: compiled from: UserInfoCacheUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class in1 {

    /* JADX INFO: compiled from: UserInfoCacheUtil.java */
    public static class a implements Callback<Response<JSONObject>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ List f7968a;
        public final /* synthetic */ Context b;
        public final /* synthetic */ d c;

        public a(List list, Context context, d dVar) {
            this.f7968a = list;
            this.b = context;
            this.c = dVar;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            this.c.k();
            Context context = this.b;
            Toast.makeText(context, context.getResources().getString(R.string.net_error), 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            Response<JSONObject> responseBody = response.body();
            if (response.code() != 200 || responseBody.code != 0) {
                this.c.k();
            } else {
                sh1.c.b(fn1.A, new Gson().toJson(this.f7968a));
                in1.c(this.b, this.c);
            }
        }
    }

    /* JADX INFO: compiled from: UserInfoCacheUtil.java */
    public static class b implements Callback<Response<JSONObject>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f7969a;
        public final /* synthetic */ d b;

        public b(Context context, d dVar) {
            this.f7969a = context;
            this.b = dVar;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            this.b.k();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            Response<JSONObject> responseBody = response.body();
            if (response.code() == 200 && responseBody.code == 0) {
                in1.b(responseBody.data.getString("imageUrl"), this.f7969a, this.b);
            } else {
                this.b.k();
            }
        }
    }

    /* JADX INFO: compiled from: UserInfoCacheUtil.java */
    public static class c implements Callback<et1> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f7970a;
        public final /* synthetic */ d b;

        public c(String str, d dVar) {
            this.f7970a = str;
            this.b = dVar;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<et1> call, Throwable th) {
            this.b.k();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<et1> call, retrofit2.Response<et1> response) {
            et1 et1VarBody = response.body();
            if (response.code() != 200) {
                this.b.k();
                return;
            }
            Bitmap bitmapDecodeByteArray = null;
            try {
                byte[] bArrBytes = et1VarBody.bytes();
                bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArrBytes, 0, bArrBytes.length);
                in1.a(bitmapDecodeByteArray, this.f7970a, fn1.v);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            if (bitmapDecodeByteArray != null) {
                bitmapDecodeByteArray.recycle();
            }
            this.b.k();
        }
    }

    /* JADX INFO: compiled from: UserInfoCacheUtil.java */
    public interface d {
        void k();
    }

    public static void b(Context context, d dVar) {
        String strC = sh1.c.c(fn1.o);
        if (TextUtils.isEmpty(strC)) {
            return;
        }
        String strB = hn1.b(strC);
        sh1.c.b(fn1.t, strB);
        a(context);
        b(context);
        String strC2 = sh1.c.c(fn1.A);
        String utdid = UTDevice.getUtdid(context);
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setAccount(strB);
        accountInfo.setDeviceId(utdid);
        accountInfo.setUserPath(om1.d(context) + File.separator + strB + FileUtils.JPEG_FILE_SUFFIX);
        if (strC2.contains(strB)) {
            dVar.k();
            return;
        }
        List arrayList = TextUtils.isEmpty(strC2) ? new ArrayList() : JSON.parseArray(strC2, AccountInfo.class);
        KeyPair keyPairA = bn1.a(1024);
        String strEncodeToString = Base64.encodeToString(keyPairA.getPublic().getEncoded(), 2);
        accountInfo.setPrivateKey(Base64.encodeToString(keyPairA.getPrivate().getEncoded(), 2));
        arrayList.add(accountInfo);
        a(strEncodeToString, context, arrayList, accountInfo, dVar);
    }

    public static void c(Context context, d dVar) {
        mj1.b().a("JWTToken " + sh1.c.c(fn1.o)).enqueue(new b(context, dVar));
    }

    public static void a(Context context) {
        String strC = sh1.c.c(fn1.o);
        String strB = hn1.b(strC);
        sh1.c.b(fn1.t, strB);
        PushManager.getInstance().bindAlias(context, strB);
        GetuiIntentService.a(strC, sh1.c.c(fn1.q));
        MobclickAgent.onProfileSignIn(strB);
    }

    public static void a(String str, Context context, List<AccountInfo> list, AccountInfo accountInfo, d dVar) {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("deviceId", UTDevice.getUtdid(context));
            jSONObject.put("publicKeyPem", str);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().z(sh1.c.c(fn1.o), ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new a(list, context, dVar));
    }

    public static List<AccountInfo> a() {
        String strC = sh1.c.c(fn1.A);
        new ArrayList();
        if (TextUtils.isEmpty(strC)) {
            return null;
        }
        return JSON.parseArray(strC, AccountInfo.class);
    }

    public static String a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        TreeMap treeMap = new TreeMap();
        treeMap.put("username", str);
        treeMap.put("photoFileBase64", str2);
        treeMap.put("appId", str3);
        treeMap.put("deviceId", str4);
        treeMap.put("osType", str5);
        treeMap.put("timestamp", str7);
        treeMap.put("geo", str6);
        treeMap.put("clientId", str8);
        try {
            return bn1.a(a((TreeMap<String, String>) treeMap).substring(0, r2.length() - 1), (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(str9, 2))));
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static void b(Context context) {
        String[] strArrA = hn1.a(sh1.c.c(fn1.o));
        Tag[] tagArr = new Tag[strArrA.length];
        for (int i = 0; i < strArrA.length; i++) {
            Tag tag = new Tag();
            tag.setName(strArrA[i]);
            tagArr[i] = tag;
        }
        PushManager.getInstance().setTag(context, tagArr, String.valueOf(System.currentTimeMillis()));
    }

    public static String b(String str, Context context, d dVar) {
        String strC = sh1.c.c(fn1.o);
        String strD = om1.d(context);
        mj1.b().b(strC, str).enqueue(new c(strD, dVar));
        return strD;
    }

    public static String a(TreeMap<String, String> treeMap) {
        if (treeMap == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> entry : treeMap.entrySet()) {
            stringBuffer.append(entry.getKey() + ContainerUtils.KEY_VALUE_DELIMITER + entry.getValue());
            stringBuffer.append("&");
        }
        return stringBuffer.toString();
    }

    public static void a(Bitmap bitmap, String str, String str2) throws IOException {
        if (bitmap != null && Environment.getExternalStorageState().equals("mounted")) {
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(file.getPath() + File.separator + str2 + FileUtils.JPEG_FILE_SUFFIX)));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bufferedOutputStream);
            try {
                bufferedOutputStream.flush();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            bufferedOutputStream.close();
        }
    }
}
