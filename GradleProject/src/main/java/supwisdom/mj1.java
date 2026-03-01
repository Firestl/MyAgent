package supwisdom;

import android.graphics.Bitmap;
import com.alibaba.fastjson.JSONObject;
import com.baidu.idl.face.platform.utils.Base64Utils;
import com.baidu.idl.face.platform.utils.BitmapUtils;
import com.google.gson.JsonObject;
import com.lzy.okgo.model.HttpHeaders;
import com.qiniu.android.dns.NetworkInfo;
import com.supwisdom.superapp.service.model.AppVersionInfo;
import com.supwisdom.superapp.service.model.MoreBaseUrlInterceptor;
import com.supwisdom.superapp.service.model.Response;
import com.taobao.weex.common.Constants;
import io.dcloud.feature.payment.weixin.WeiXinPay;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import supwisdom.ys1;
import supwisdom.zs1;

/* JADX INFO: compiled from: SuperAppService.java */
/* JADX INFO: loaded from: classes2.dex */
public class mj1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static a f8399a;
    public static qs1 b;

    /* JADX INFO: compiled from: SuperAppService.java */
    public interface a {
        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/app/user/completed/securityEmailAddress/checkCodeBindEmailAddress")
        Call<Response<JSONObject>> A(@Query("idToken") String str, @Body ct1 ct1Var);

        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/app/user/completed/realname/preMobile/existsPreMobile")
        Call<Response<JSONObject>> B(@Query("idToken") String str, @Body ct1 ct1Var);

        @Headers({"urlName:PersonalBaseUrl"})
        @GET("api/v1/personal/open/app/passwordStrategy/tips")
        Call<Response<JsonObject>> a();

        @Headers({"urlName:PersonalBaseUrl"})
        @GET("api/v1/me/user")
        Call<Response<JSONObject>> a(@Header("authorization") String str);

        @POST("https://message-service.zueb.edu.cn/manager/api/v1/cid/cidUnBind")
        Call<et1> a(@Header("X-Id-Token") String str, @Query("cid") String str2);

        @Headers({"urlname:LoginBaseURL"})
        @GET("federation/federated/openweixinmobile")
        Call<Response<JsonObject>> a(@Query("appId") String str, @Query("state") String str2, @Query("deviceId") String str3);

        @Headers({"urlname:LoginBaseURL"})
        @GET
        Call<Response<JSONObject>> a(@Url String str, @Query("nonce") String str2, @Query("access_token") String str3, @Query("auth_code") String str4);

        @Headers({"urlname:LoginBaseURL"})
        @GET
        Call<Response<JSONObject>> a(@Url String str, @Query("nonce") String str2, @Query("access_token") String str3, @Query("openid") String str4, @Query("unionid") String str5);

        @GET("https://my-service.sias.edu.cn/v1/search/globalSearch")
        Call<Response<JsonObject>> a(@Header("X-Id-Token") String str, @Header("X-Device-Info") String str2, @Header("X-Terminal-Info") String str3, @Query("keyWordType") String str4, @Query("orderingRule") String str5, @Query("scope") String str6, @Query("keyWord") String str7);

        @Headers({"urlname:LoginBaseURL"})
        @POST("passwordless/smsLogin")
        Call<Response<JSONObject>> a(@Query("mobile") String str, @Query("smscode") String str2, @Query("nonce") String str3, @Query("appId") String str4, @Query("deviceId") String str5, @Query("osType") String str6, @Query("geo") String str7, @Query("clientId") String str8);

        @FormUrlEncoded
        @Headers({"urlname:LoginBaseURL"})
        @POST("face/faceLogin")
        Call<Response<JSONObject>> a(@Field("username") String str, @Field("photoFileBase64") String str2, @Field("appId") String str3, @Field("deviceId") String str4, @Field("osType") String str5, @Field("geo") String str6, @Field("timestamp") String str7, @Field("clientId") String str8, @Field(WeiXinPay.PayInfoResult.KEY_SIGN) String str9);

        @POST("https://aip.baidubce.com/rpc/2.0/nlp/v1/lexer_custom")
        Call<et1> a(@Query("access_token") String str, @Query("charset") String str2, @Body ct1 ct1Var);

        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/user/completed/securityMobile/sendCodeByMobile")
        Call<Response<JSONObject>> a(@Query("idToken") String str, @Body ct1 ct1Var);

        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/app/user/completed/portrait/uploadPortrait")
        @Multipart
        Call<Response<JSONObject>> a(@Query("idToken") String str, @Part ys1.b bVar);

        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/open/app/forgotPassword/changePassword")
        Call<Response<JSONObject>> a(@Body ct1 ct1Var);

        @Headers({"urlName:PersonalBaseUrl"})
        @POST("api/v1/personal/app/user/completed/realname/faceVerify/checkFaceVerify")
        @Multipart
        Call<Response<JsonObject>> a(@Part("nonce") ct1 ct1Var, @Part ys1.b bVar);

        @Headers({"urlname:PersonalBaseUrl"})
        @GET("api/v1/personal/open/app/forgotPassword/initForgotPassword")
        Call<Response<JsonObject>> b();

        @Headers({"urlname:PersonalBaseUrl"})
        @GET("api/v1/personal/app/user/completed/userCompletedStatus")
        Call<Response<JSONObject>> b(@Query("idToken") String str);

        @Headers({"urlName:PersonalBaseUrl"})
        @GET("api/v1/me/portrait")
        Call<et1> b(@Query("token") String str, @Query("imgSrc") String str2);

        @Headers({"urlname:LoginBaseURL"})
        @GET("federation/federated/alipaymobile")
        Call<Response<JSONObject>> b(@Query("appId") String str, @Query("state") String str2, @Query("deviceId") String str3);

        @Headers({"urlname:LoginBaseURL"})
        @GET
        Call<Response<JSONObject>> b(@Url String str, @Query("nonce") String str2, @Query("access_token") String str3, @Query("code") String str4);

        @POST
        Call<Response<JSONObject>> b(@Url String str, @Query("nonce") String str2, @Query("auth_code") String str3, @Query("access_token") String str4, @Query("openid") String str5, @Query("unionid") String str6, @Query("code") String str7);

        @Headers({"urlname:LoginBaseURL", "Accept-Language: zh_CN"})
        @POST("password/passwordLogin")
        Call<Response<JSONObject>> b(@Query("username") String str, @Query(Constants.Value.PASSWORD) String str2, @Query("appId") String str3, @Query("geo") String str4, @Query("deviceId") String str5, @Query("osType") String str6, @Query("clientId") String str7, @Query("mfaState") String str8);

        @Headers({"urlname:PortalBaseUrl"})
        @POST("v1/cms/content/collect")
        Call<et1> b(@Header("X-Id-Token") String str, @Query("newsType") String str2, @Query("id") String str3, @Query("type") String str4, @Query("title") String str5, @Query("titleImage") String str6, @Query("contentDesc") String str7, @Query("externalNewsUrl") String str8, @Query("columnId") String str9);

        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/open/forgotPassword/checkQuestion")
        Call<Response<JsonObject>> b(@Query("token") String str, @Body ct1 ct1Var);

        @Headers({"urlName:PersonalBaseUrl"})
        @POST("api/v1/personal/open/app/activeUser/existsPreMobile")
        Call<Response<JsonObject>> b(@Body ct1 ct1Var);

        @Headers({"urlName:PersonalBaseUrl"})
        @POST("api/v1/personal/open/app/activeUser/checkFaceVerify")
        @Multipart
        Call<Response<JsonObject>> b(@Part("nonce") ct1 ct1Var, @Part ys1.b bVar);

        @Headers({"urlname:LoginBaseURL"})
        @GET("loginConfigs")
        Call<Response<JSONObject>> c();

        @Headers({"urlname:PersonalBaseUrl"})
        @GET("api/v1/personal/app/user/completed/realname/initRealname")
        Call<Response<JSONObject>> c(@Query("idToken") String str);

        @Headers({"urlname:PortalBaseUrl"})
        @GET("v1/service/collectCancelService")
        Call<et1> c(@Header("X-Id-Token") String str, @Query("id") String str2, @Query("type") String str3);

        @Headers({"accept:application/json", "urlname:PortalBaseUrl"})
        @GET("appVersionInfo")
        Call<Response<AppVersionInfo>> c(@Header("X-Device-Info") String str, @Header("X-Terminal-Info") String str2, @Query("mobileSystemType") String str3, @Query("userAppVersion") String str4);

        @Headers({"urlname:LoginBaseURL"})
        @POST("phoneNumber/login")
        Call<Response<JSONObject>> c(@Query("token") String str, @Query("gyuid") String str2, @Query("appId") String str3, @Query("geo") String str4, @Query("deviceId") String str5, @Query("osType") String str6, @Query("clientId") String str7, @Query("mfaState") String str8);

        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/app/user/infoMonitor/safetyCertificateIdentity/secure/checkQuestion")
        Call<Response<JsonObject>> c(@Header("X-Id-Token") String str, @Body ct1 ct1Var);

        @Headers({"urlName:PersonalBaseUrl"})
        @POST("api/v1/personal/open/app/activeUser/useAsSecureMobileByPreMobile")
        Call<Response<JsonObject>> c(@Body ct1 ct1Var);

        @Streaming
        @GET
        rw1<et1> c(@Url String str, @Header("Cookie") String str2);

        @Headers({"urlname:PortalBaseUrl"})
        @GET("v1/agreement/getAllPrivacyAgreement")
        Call<et1> d();

        @Headers({"urlname:PersonalBaseUrl"})
        @GET("api/v1/personal/app/user/completed/securityQuestion/initQuestion")
        Call<Response<JSONObject>> d(@Query("idToken") String str);

        @GET("mfa/initByType/{type}")
        Call<Response<JSONObject>> d(@Path("type") String str, @Query("state") String str2);

        @Headers({"urlname:LoginBaseURL"})
        @POST("federation/federatedLogin")
        Call<Response<JSONObject>> d(@Query("nonce") String str, @Query("accountId") String str2, @Query("clientId") String str3);

        @Headers({"urlname:LoginBaseURL"})
        @GET
        Call<Response<JSONObject>> d(@Url String str, @Query("nonce") String str2, @Query("access_token") String str3, @Query("code") String str4);

        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/app/user/completed/realname/preMobile/checkCodeByPreMobile")
        Call<Response<JSONObject>> d(@Query("idToken") String str, @Body ct1 ct1Var);

        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/open/app/forgotPassword/checkUserInfo")
        Call<Response<JSONObject>> d(@Body ct1 ct1Var);

        @Headers({"urlname:PersonalBaseUrl"})
        @GET("api/v1/personal/open/app/activeUser/initActiveUser")
        Call<Response<JsonObject>> e();

        @Headers({"urlname:PortalBaseUrl"})
        @GET("portalCenter/api/xzx/getToken")
        Call<Response<JSONObject>> e(@Header("X-Id-Token") String str);

        @Headers({"urlname:LoginBaseURL"})
        @POST("federation/federatedBinding")
        Call<Response<JSONObject>> e(@Query("nonce") String str, @Header("X-Id-Token") String str2);

        @Headers({"urlname:LoginBaseURL"})
        @GET("federation/federated/alipaymobile")
        Call<Response<JSONObject>> e(@Query("appId") String str, @Query("state") String str2, @Query("deviceId") String str3);

        @Headers({"urlname:LoginBaseURL"})
        @GET
        Call<Response<JSONObject>> e(@Url String str, @Query("nonce") String str2, @Query("access_token") String str3, @Query("code") String str4);

        @Headers({"urlname:PersonalBaseUrl"})
        @PUT("api/v1/personal/app/user/completed/portrait/savePortrait")
        Call<Response<JSONObject>> e(@Query("idToken") String str, @Body ct1 ct1Var);

        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/open/app/activeUser/openPreMobileVerify")
        Call<Response<JsonObject>> e(@Body ct1 ct1Var);

        @Headers({"urlname:LoginBaseURL"})
        @GET("passwordless/smsInit")
        Call<Response<JSONObject>> f();

        @Headers({"urlname:PersonalBaseUrl"})
        @GET("api/v1/personal/app/user/completed/securityEmailAddress/initEmailAddress")
        Call<Response<JSONObject>> f(@Query("idToken") String str);

        @Headers({"urlname:LoginBaseURL"})
        @POST("passwordless/smsSend")
        Call<Response<JSONObject>> f(@Query("mobile") String str, @Query("nonce") String str2);

        @Headers({"urlname:LoginBaseURL"})
        @GET("federation/federated/qqmobile")
        Call<Response<JSONObject>> f(@Query("appId") String str, @Query("state") String str2, @Query("deviceId") String str3);

        @Headers({"urlname:LoginBaseURL"})
        @POST("login/userOnlineDetect")
        Call<et1> f(@Header("X-Id-Token") String str, @Query("appId") String str2, @Query("deviceId") String str3, @Query("username") String str4);

        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/app/user/infoMonitor/safetyCertificateIdentity/realName/preMobile/checkCode")
        Call<Response<JsonObject>> f(@Header("X-Id-Token") String str, @Body ct1 ct1Var);

        @Headers({"urlName:PersonalBaseUrl"})
        @POST("api/v1/personal/open/app/passwordStrategy/verify")
        Call<Response<JsonObject>> f(@Body ct1 ct1Var);

        @Headers({"urlname:PortalBaseUrl"})
        @GET("portalApi/api/open/app/menu")
        Call<et1> g();

        @Headers({"urlname:PortalBaseUrl"})
        @GET("v1/agreement/getPrivacyAgreement")
        Call<Response<JSONObject>> g(@Query("agreementType") String str);

        @POST("https://message-service.zueb.edu.cn/manager/api/v1/cid/cidCover")
        Call<et1> g(@Header("X-Id-Token") String str, @Query("cid") String str2);

        @POST("https://aip.baidubce.com/oauth/2.0/token")
        Call<et1> g(@Query("grant_type") String str, @Query(com.tencent.connect.common.Constants.PARAM_CLIENT_ID) String str2, @Query("client_secret") String str3);

        @Headers({"urlname:LoginBaseURL"})
        @POST("login/accountChoose")
        Call<Response<JSONObject>> g(@Query("appId") String str, @Query("deviceId") String str2, @Query("cid") String str3, @Query("accountId") String str4);

        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/app/user/infoMonitor/completed/unbindDingtalk")
        Call<Response<JSONObject>> g(@Header("X-Id-Token") String str, @Body ct1 ct1Var);

        @Headers({"urlName:PersonalBaseUrl"})
        @POST("api/v1/personal/open/app/activeUser/openFaceVerify")
        Call<Response<JsonObject>> g(@Body ct1 ct1Var);

        @GET("jwt/publicKey")
        Call<et1> getPublicKey();

        @Headers({"urlname:PersonalBaseUrl"})
        @GET("api/v1/personal/app/user/infoMonitor/initInfoMonitor")
        Call<et1> h(@Header("X-Id-Token") String str);

        @GET
        Call<et1> h(@Url String str, @Header(HttpHeaders.HEAD_KEY_IF_NONE_MATCH) String str2);

        @Headers({"urlname:LoginBaseURL"})
        @GET("federation/federated/dingtalkmobile")
        Call<Response<JSONObject>> h(@Query("appId") String str, @Query("state") String str2, @Query("deviceId") String str3);

        @GET("https://my-service.sias.edu.cn/v1/service/getService")
        Call<Response<JSONObject>> h(@Header("X-Id-Token") String str, @Header("X-Device-Info") String str2, @Header("X-Terminal-Info") String str3, @Query("serviceId") String str4);

        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/app/user/completed/realname/preMobile/sendCodeByPreMobile")
        Call<Response<JSONObject>> h(@Query("idToken") String str, @Body ct1 ct1Var);

        @Headers({"urlname:refactorServerUrl"})
        @POST("api/guard/securephone/send")
        Call<Response<JSONObject>> h(@Body ct1 ct1Var);

        @Headers({"urlname:PersonalBaseUrl"})
        @GET("api/v1/personal/app/user/completed/portrait/initPortrait")
        Call<Response<JSONObject>> i(@Query("idToken") String str);

        @POST
        Call<Response<JSONObject>> i(@Url String str, @Query("nonce") String str2, @Query("code") String str3);

        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/app/user/infoMonitor/completed/unbindWorkweixin")
        Call<Response<JSONObject>> i(@Header("X-Id-Token") String str, @Body ct1 ct1Var);

        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/open/app/activeUser/existsMobile")
        Call<Response<JsonObject>> i(@Body ct1 ct1Var);

        @Headers({"urlname:PersonalBaseUrl"})
        @GET("api/v1/personal/app/user/completed/securityMobile/initMobile")
        Call<Response<JSONObject>> j(@Query("idToken") String str);

        @Headers({"urlname:LoginBaseURL"})
        @GET("federation/federated/workweixinmobile")
        Call<Response<JSONObject>> j(@Query("appId") String str, @Query("state") String str2, @Query("deviceId") String str3);

        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/app/user/infoMonitor/safetyCertificateBasic/checkUserInfo")
        Call<et1> j(@Header("X-Id-Token") String str, @Body ct1 ct1Var);

        @Headers({"urlname:refactorServerUrl"})
        @POST("api/guard/fedauth/send")
        Call<Response<JSONObject>> j(@Body ct1 ct1Var);

        @Headers({"urlname:LoginBaseURL"})
        @GET("federation/federatedUserInfo")
        Call<Response<JSONObject>> k(@Query("nonce") String str);

        @Headers({"urlname:LoginBaseURL"})
        @POST("login/accountChoose")
        Call<Response<JSONObject>> k(@Query("cid") String str, @Query("accountId") String str2, @Query("clientId") String str3);

        @Headers({"urlname:PersonalBaseUrl"})
        @PUT("api/v1/personal/app/user/completed/userCompletedStatus")
        Call<Response<JSONObject>> k(@Header("X-Id-Token") String str, @Body ct1 ct1Var);

        @Headers({"urlName:PersonalBaseUrl"})
        @POST("api/v1/personal/open/app/activeUser/sendCodeByEmailAddress")
        Call<Response<JsonObject>> k(@Body ct1 ct1Var);

        @POST("mfa/detect")
        Call<Response<JSONObject>> l(@Query("username") String str, @Query("deviceId") String str2, @Query(Constants.Value.PASSWORD) String str3);

        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/app/user/completed/realname/password/checkPassword")
        Call<Response<JSONObject>> l(@Query("idToken") String str, @Body ct1 ct1Var);

        @Headers({"urlName:PersonalBaseUrl"})
        @POST("api/v1/personal/open/app/activeUser/sendCodeByPreMobile")
        Call<Response<JsonObject>> l(@Body ct1 ct1Var);

        @Streaming
        @GET
        rw1<et1> l(@Url String str);

        @Headers({"urlname:LoginBaseURL"})
        @GET("federation/federated/openweixinmobile")
        Call<Response<JSONObject>> m(@Query("appId") String str, @Query("state") String str2, @Query("deviceId") String str3);

        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/app/user/infoMonitor/changePassword")
        Call<Response<JSONObject>> m(@Header("X-Id-Token") String str, @Body ct1 ct1Var);

        @Headers({"urlName:PersonalBaseUrl"})
        @POST("api/v1/personal/open/app/activeUser/activeUser")
        Call<Response<JsonObject>> m(@Body ct1 ct1Var);

        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/app/user/infoMonitor/safetyCertificateIdentity/secure/checkCode")
        Call<Response<JsonObject>> n(@Header("X-Id-Token") String str, @Body ct1 ct1Var);

        @Headers({"urlName:PersonalBaseUrl"})
        @POST("api/v1/personal/open/app/activeUser/checkCodeByPreMobile")
        Call<Response<JsonObject>> n(@Body ct1 ct1Var);

        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/app/user/completed/securityMobile/checkCodeBindMobile")
        Call<Response<JSONObject>> o(@Query("idToken") String str, @Body ct1 ct1Var);

        @Headers({"urlname:refactorServerUrl"})
        @POST("/api/guard/fedauth/valid")
        Call<Response<JSONObject>> o(@Body ct1 ct1Var);

        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/app/user/infoMonitor/safetyCertificateIdentity/realName/preMobile/sendCode")
        Call<Response<JSONObject>> p(@Header("X-Id-Token") String str, @Body ct1 ct1Var);

        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/open/app/forgotPassword/checkCode")
        Call<Response<JsonObject>> p(@Body ct1 ct1Var);

        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/app/user/infoMonitor/completed/unbindAlipay")
        Call<Response<JSONObject>> q(@Header("X-Id-Token") String str, @Body ct1 ct1Var);

        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/open/app/activeUser/existsEmailAddress")
        Call<Response<JsonObject>> q(@Body ct1 ct1Var);

        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/app/user/infoMonitor/safetyCertificateIdentity/secure/sendCode")
        Call<Response<JsonObject>> r(@Header("X-Id-Token") String str, @Body ct1 ct1Var);

        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/open/app/activeUser/checkUserInfo")
        Call<Response<JsonObject>> r(@Body ct1 ct1Var);

        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/app/user/completed/securityEmailAddress/sendCodeByEmailAddress")
        Call<Response<JSONObject>> s(@Query("idToken") String str, @Body ct1 ct1Var);

        @Headers({"urlname:refactorServerUrl"})
        @POST("api/guard/secureemail/send")
        Call<Response<JSONObject>> s(@Body ct1 ct1Var);

        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/app/user/completed/realname/certificateNumber/checkCertificateNumber")
        Call<Response<JSONObject>> t(@Query("idToken") String str, @Body ct1 ct1Var);

        @Headers({"urlname:refactorServerUrl"})
        @POST("api/guard/securephone/valid")
        Call<Response<JSONObject>> t(@Body ct1 ct1Var);

        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/app/user/completed/realname/preMobile/loadPreMobile")
        Call<Response<JSONObject>> u(@Query("idToken") String str, @Body ct1 ct1Var);

        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/open/app/activeUser/checkCode")
        Call<Response<JsonObject>> u(@Body ct1 ct1Var);

        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/app/user/infoMonitor/completed/unbindQq")
        Call<Response<JSONObject>> v(@Header("X-Id-Token") String str, @Body ct1 ct1Var);

        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/open/app/forgotPassword/sendCode")
        Call<Response<JsonObject>> v(@Body ct1 ct1Var);

        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/app/user/completed/securityQuestion/bindQuestion")
        Call<Response<JSONObject>> w(@Query("idToken") String str, @Body ct1 ct1Var);

        @Headers({"urlname:refactorServerUrl"})
        @POST("api/guard/secureemail/valid")
        Call<Response<JSONObject>> w(@Body ct1 ct1Var);

        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/app/user/infoMonitor/completed/unbindOpenweixin")
        Call<Response<JSONObject>> x(@Header("X-Id-Token") String str, @Body ct1 ct1Var);

        @Headers({"urlname:PersonalBaseUrl"})
        @POST("api/v1/personal/open/app/activeUser/sendCodeByMobile")
        Call<Response<JsonObject>> x(@Body ct1 ct1Var);

        @Headers({"urlname:PortalBaseUrl"})
        @POST("v1/evaluate/saveEvaluate")
        Call<et1> y(@Header("X-Id-Token") String str, @Body ct1 ct1Var);

        @Headers({"urlname:LoginBaseURL"})
        @POST("api/securityKey/publicKey")
        Call<Response<JSONObject>> z(@Header("X-Id-Token") String str, @Body ct1 ct1Var);
    }

    public static synchronized qs1 a() {
        qs1 qs1Var = qs1.f8950a;
        b = qs1Var;
        if (qs1Var == null) {
            fy0[] fy0VarArr = new fy0[2];
            fy0VarArr[0] = new iy0();
            final dy0 dy0Var = new dy0(NetworkInfo.c, fy0VarArr);
            b = new qs1() { // from class: supwisdom.lj1
                @Override // supwisdom.qs1
                public final List lookup(String str) {
                    return mj1.a(dy0Var, str);
                }
            };
        }
        return b;
    }

    public static synchronized a b() {
        if (f8399a == null) {
            zs1.b bVar = new zs1.b();
            bVar.c(10L, TimeUnit.SECONDS);
            bVar.a(10L, TimeUnit.SECONDS);
            bVar.a(new MoreBaseUrlInterceptor());
            bVar.a(a());
            f8399a = (a) new Retrofit.Builder().client(bVar.a()).addConverterFactory(GsonConverterFactory.create()).addConverterFactory(ScalarsConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).baseUrl(fn1.d).build().create(a.class);
        }
        return f8399a;
    }

    public static /* synthetic */ List a(dy0 dy0Var, String str) throws UnknownHostException {
        try {
            ArrayList arrayList = new ArrayList();
            String[] strArrA = dy0Var.a(str);
            if (strArrA != null && strArrA.length != 0) {
                for (String str2 : strArrA) {
                    arrayList.add(InetAddress.getByName(str2));
                }
                return arrayList;
            }
            return null;
        } catch (Throwable unused) {
            return qs1.f8950a.lookup(str);
        }
    }

    public static ys1.b a(byte[] bArr) {
        return ys1.b.a("photoFile", "ws.jpg", ct1.create(xs1.b("image/jpeg"), bArr));
    }

    public static ys1.b a(Bitmap bitmap, String str) {
        return ys1.b.a(str, "xx.jpg", ct1.create(xs1.b("image/jpeg"), Base64Utils.decode(BitmapUtils.bitmapToJpegBase64(bitmap, 80), 2)));
    }
}
