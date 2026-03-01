package com.supwisdom.superapp.ui.activity;

import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.alibaba.fastjson.JSONObject;
import com.supwisdom.superapp.WXBaseActivity;
import com.supwisdom.superapp.service.model.Response;
import com.supwisdom.zueb.R;
import retrofit2.Call;
import retrofit2.Callback;
import supwisdom.mj1;
import supwisdom.sh1;

/* JADX INFO: loaded from: classes2.dex */
public class PrivacyActivity extends WXBaseActivity implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f4332a;
    public TextView b;
    public TextView c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f4333e;
    public ImageButton f;
    public String g = "第一条 总则\n1.本服务协议（以下简称“本协议”）是【郑州大学移动校园App】（以下简称“我们”）与【郑州大学移动校园 App】用户（以下简称“用户”或“您”）就“人脸服务”（以下简称“本服务”）的使用等相关事项所订立的有效合约。您通过网络页面点击确认或以其他方式选择接受本协议，即表示您与我们已达成协议并同意接受本协议的全部约定内容。\n2.在接受本协议之前，请您仔细阅读本协议的全部内容，尤其是加粗的文字部分。如果您不同意本协议的任意内容，或者无法准确理解我们对条款的解释，请不要进行后续操作。\n3.本协议的成立、生效、履行和解释，均适用中华人民共和国法律。本服务协议未规定部分依照《郑州大学移动校园 App隐私政策》及相关附属规则予以履行；本协议与《郑州大学移动校园App隐私政策》及相关附属规则不一致的，以本协议为准。\n第二条 我们如何收集和使用您的信息\n1.我们将收集您在使用我们/服务提供方的服务所主动上传的信息，如拍照或上传的照片或录像等。以及收集您在使用我们的服务过程中产生相应的记录等。\n郑州大学移动校园APP采集、保存的脸部信息包括：\n（1）在郑州大学移动校园 APP进行人脸登录时\n（2）已经或将要通过郑州大学移动校园 APP线上办理郑州大学移动校园业务时通过图像采集设备获取的脸部信息。\n2.为了准确核验您的身份，尽量防止您的身份被冒用，您授权郑州大学移动校园App采集您的人脸信息和设备信息以验证您在进行认证时处于安全可信的环境。当您使用郑州大学移动校园 App登录服务时，我们会对您进行人脸识别收集您的视频影像信息。当您使用服务之前，您可选择是否开通以下权限：\n（1）基于摄像头（相机）的功能：您可开启摄像头进行身份验证（人脸识别），拍照并上传图片操作以及视频服务；\n（2）基于麦克风的功能：您可选择麦克风设备进行特定场景的语音输入与沟通（语音搜索，语音消息）或视频对话。\n3.郑州大学移动校园 APP对人脸信息的使用范围包括：\n（1）经身份验证通过，将本人脸部信息录入人脸识别信息库，并记录业务办理信息。\n（2）将通过自助郑州大学移动校园终端或其他图像采集设备获取的本人脸部信息与人脸识别信息库（或公安部门信息库，下同）中的脸部信息进行特征对比或识别。\n（3）将通过郑州大学移动校园 APP线上办理登录业务上传的脸部信息与人脸识别信息库中的脸部信息进行特征对比或识别。\n4.您勾选“已阅读并同意人脸识别服务协议”，即视为您同意并授权我们/服务提供方获取、使用您的上述信息并用于本服务，并授权我们及服务提供方用于验证身份功能。我们不会超过本协议告知的范围收集使用您的人脸信息，如您不同意提交或授权，请不要勾选“已阅读并同意人脸识别服务协议”，同时您将无法继续使用人脸识别功能，但这并不影响您使用郑州大学移动校园App的其他服务。\n第三条 我们如何共享和保护您的信息\n1.为了更好的向您提供服务，本服务的功能由第三方服务提供方提供核验数据及技术支持，第三方并不存储任何人脸相关信息，只校验实名身份。\n2.我们会要求第三方服务提供方以不低于我们的安全水准使用您的信息，并要求第三方服务提供者承诺尽到信息安全保护义务。\n3.按照相关法律法规规定，我们将把中华人民共和国境内运营过程中收集和产生的个人信息存储在中国境内，且人脸信息与您的身份信息分开加密存储，我们收集的人脸信息仅本地收集并处理（或我们收集的人脸信息会进行远程核验身份）。我们仅在本协议所述目的所必须期间和法律法规要求的时限内保留您的人脸信息。超出必要期限后，我们将对您的人脸信息进行删除或匿名化处理，但法律法规另有规定的除外。\n第四条 协议更新\n1.我们将有权随时修改本协议的相关条款，一旦相关内容发生变动，我们将会通过郑州大学移动校园 App公告等方式向您提示修改内容。\n2.如果您不同意我们对本协议相关条款及相关附属规则所做的修改，您应立即停止使用我们提供的本服务。如果您继续使用本服务，则视为您接受并认可本协议相关条款及相关附属规则的修改。\n第五条 您的权利 \n根据中国相关的法律、法规、标准，以及其他国家、地区的通行做法，我们保障您对自己的个人信息形式以下权利：\n（一）访问您的个人信息\n您可以随时通过客服热线67783086联系我们。我们将在15个工作日内回复您的访问请求。\n（二）更正您的个人信息\n当您发现我们处理的关于您的个人信息有错误时，您有权要求我们做出更正。您可以随时通过客服热线67783086联系我们。我们将在15个工作日内回复您的更正请求。\n（三）删除您的个人信息\n在以下情形中，您可以通过拨打客服电话67783086向我们提出删除个人信息的请求：\n（1）如果我们处理个人信息的行为违反法律法规；\n（2）如果我们收集、使用您的个人信息，却未征得您的同意；\n（3）如果我们处理个人信息的行为违反了与您的约定；\n（4）如果您不再使用我们的产品或服务，或您注销了账号；\n（5）如果我们终止服务及运营。\n若我们决定响应您的删除请求，我们还将同时通知从我们获得您的个人信息的实体，要求其及时删除，除非法律法规另有规定，或这些实体获得您的独立授权。\n当您从我们的服务中删除信息后，我们可能不会立即备份系统中删除相应的信息，但会在备份更新时删除这些信息。\n（四）改变您授权同意的范围\n您可以通过“我的→关于我们→授权管理”来改撤回您的服务授权。您也可以通过注销账户的方式，撤回我们继续收集您个人信息的全部授权。请您理解，每个业务功能需要一些基本的个人信息才能得以完成，当您撤回同意或授权后，我们无法继续为您提供撤回同意或授权所对应的服务，也不再处理您相应的个人信息。但您撤回同意或授权的决定，不会影响此前基于您的授权而开展的个人信息处理。\n第六条 如何联系我们\n如果您对本协议或您个人信息的相关事宜有任何问题、意见或建议，请通过拨打客服热线67783086与我们联系。\n我们设立了个人信息保护专员，如果您有任何个人信息的疑问、意见、建议或投诉举报，请通过以下联系方式与我们联系：\n个人信息保护联系人：王婧\n电子邮件： nic@zzu.edu.cn\n";
    public TextView h;
    public ConstraintLayout i;
    public ScrollView j;

    public class a implements Callback<Response<JSONObject>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f4334a;

        public a(String str) {
            this.f4334a = str;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            JSONObject jSONObject;
            if (response.code() != 200 || (jSONObject = response.body().data) == null) {
                return;
            }
            String string = jSONObject.getString("privacyAgreement");
            sh1.c.b("privacy", string);
            String string2 = jSONObject.getString("privacyAgreement");
            sh1.c.b("protocol", string2);
            String string3 = jSONObject.getString("version");
            if (this.f4334a.equals("1")) {
                sh1.c.b("protocolVersion", string3);
            } else if (this.f4334a.equals("3")) {
                sh1.c.b("privacyVersion", string3);
            }
            if (PrivacyActivity.this.d == 0) {
                if (!TextUtils.isEmpty(string2) && !PrivacyActivity.this.f4333e.equals(string2)) {
                    PrivacyActivity.this.f4333e = string2;
                }
            } else if (!TextUtils.isEmpty(string) && !PrivacyActivity.this.f4333e.equals(string)) {
                PrivacyActivity.this.f4333e = string;
            }
            PrivacyActivity.this.c.setText(Html.fromHtml(PrivacyActivity.this.f4333e));
            PrivacyActivity.this.i.setVisibility(8);
            PrivacyActivity.this.j.setVisibility(0);
        }
    }

    public final void e(String str) {
        mj1.b().g(str).enqueue(new a(str));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.backBt || id == R.id.backTxt) {
            finish();
        } else {
            if (id != R.id.tv_revoke_face_privacy) {
                return;
            }
            sh1.c.a("isAgreeFacePrivacy", (Boolean) false);
        }
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        WXBaseActivity.transparencyBar(this);
        WXBaseActivity.setLightStatusBar(this, true);
        setContentView(R.layout.activity_privacy);
        this.c = (TextView) findViewById(R.id.privacyAtyTv);
        this.f4332a = (TextView) findViewById(R.id.titleTxt);
        this.b = (TextView) findViewById(R.id.backTxt);
        this.f = (ImageButton) findViewById(R.id.backBt);
        this.i = (ConstraintLayout) findViewById(R.id.cl_empty_view);
        this.j = (ScrollView) findViewById(R.id.sv_loading_view);
        this.h = (TextView) findViewById(R.id.tv_revoke_face_privacy);
        int intExtra = getIntent().getIntExtra("policyType", 0);
        this.d = intExtra;
        if (intExtra == 0) {
            this.f4332a.setText("用户协议");
            this.f4333e = sh1.c.c("protocol");
            e("1");
        } else if (intExtra == 1) {
            this.f4332a.setText("隐私政策指引");
            this.f4333e = sh1.c.c("privacy");
            e("3");
        } else if (intExtra == 3) {
            this.f4332a.setText("人脸识别隐私协议");
            this.f4333e = this.g;
        }
        this.b.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.h.setOnClickListener(this);
        if (!"".equals(this.f4333e) && (str = this.f4333e) != null) {
            this.c.setText(Html.fromHtml(str));
            this.i.setVisibility(8);
            this.j.setVisibility(0);
        }
        if (this.d == 3) {
        }
    }
}
