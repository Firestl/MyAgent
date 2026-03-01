package com.supwisdom.superapp.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.lzy.okgo.model.HttpHeaders;
import com.supwisdom.superapp.WXBaseActivity;
import com.supwisdom.superapp.util.HashUtil;
import com.supwisdom.zueb.R;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.feature.sdk.DCUniMPSDK;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import supwisdom.et1;
import supwisdom.mj1;
import supwisdom.om1;
import supwisdom.sh1;

/* JADX INFO: loaded from: classes2.dex */
public class MiniLoadingActivity extends WXBaseActivity {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f4266a;
    public String b;
    public String c;

    public class a implements Callback<et1> {
        public a() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<et1> call, Throwable th) {
            MiniLoadingActivity.this.finish();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<et1> call, Response<et1> response) {
            MiniLoadingActivity.this.a(response);
        }
    }

    public class b implements ICallBack {
        public b() {
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            if (i != 1) {
                Toast.makeText(MiniLoadingActivity.this, "资源释放失败", 0).show();
                MiniLoadingActivity.this.finish();
                return null;
            }
            Log.d("小程序", "释放成功打开" + MiniLoadingActivity.this.b);
            try {
                DCUniMPSDK.getInstance().openUniMP(MiniLoadingActivity.this, MiniLoadingActivity.this.b);
                return null;
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }
    }

    public final void l() {
        Log.d("小程序", "开始下载");
        mj1.b().h(this.f4266a, sh1.c.c(this.b)).enqueue(new a());
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_loadingmini);
        String stringExtra = getIntent().getStringExtra("loadPath");
        this.f4266a = stringExtra;
        this.b = stringExtra.substring(stringExtra.lastIndexOf("/") + 1, this.f4266a.lastIndexOf(Operators.DOT_STR));
        this.c = om1.c(this) + File.separator + this.b + ".wgt";
        StringBuilder sb = new StringBuilder();
        sb.append(DCUniMPSDK.getInstance().getAppBasePath(this));
        sb.append(this.b);
        sb.append(".wgt");
        sb.toString();
        l();
    }

    public final void a(Response<et1> response) {
        InputStream inputStreamByteStream;
        File file;
        FileOutputStream fileOutputStream;
        byte[] bArr;
        if (response.code() != 200) {
            if (response.code() == 304) {
                try {
                    DCUniMPSDK.getInstance().openUniMP(this, this.b);
                    return;
                } catch (Throwable th) {
                    th.printStackTrace();
                    return;
                }
            }
            return;
        }
        Log.d("小程序", "下载" + this.b);
        try {
            inputStreamByteStream = response.body().byteStream();
            file = new File(this.c);
            if (file.exists()) {
                file.delete();
            }
            fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            }
            bArr = new byte[8192];
        } catch (Throwable th2) {
            th2.printStackTrace();
            return;
        }
        while (true) {
            try {
                int i = inputStreamByteStream.read(bArr);
                if (i == -1) {
                    break;
                } else {
                    fileOutputStream.write(bArr, 0, i);
                }
            } catch (IOException e3) {
                e3.printStackTrace();
                return;
            }
            th2.printStackTrace();
            return;
        }
        inputStreamByteStream.close();
        fileOutputStream.close();
        String strA = response.headers().a(HttpHeaders.HEAD_KEY_E_TAG);
        if (strA == null || strA.trim().equals("")) {
            strA = HashUtil.a(file);
        }
        sh1.c.b(this.b, strA);
        DCUniMPSDK.getInstance().releaseWgtToRunPathFromePath(this.b, this.c, new b());
    }
}
