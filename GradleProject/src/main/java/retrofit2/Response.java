package retrofit2;

import com.taobao.weex.ui.module.WXModalUIModule;
import javax.annotation.Nullable;
import okhttp3.Protocol;
import retrofit2.OkHttpCall;
import supwisdom.bt1;
import supwisdom.dt1;
import supwisdom.et1;
import supwisdom.us1;

/* JADX INFO: loaded from: classes3.dex */
public final class Response<T> {

    @Nullable
    public final T body;

    @Nullable
    public final et1 errorBody;
    public final dt1 rawResponse;

    public Response(dt1 dt1Var, @Nullable T t, @Nullable et1 et1Var) {
        this.rawResponse = dt1Var;
        this.body = t;
        this.errorBody = et1Var;
    }

    public static <T> Response<T> error(int i, et1 et1Var) {
        Utils.checkNotNull(et1Var, "body == null");
        if (i < 400) {
            throw new IllegalArgumentException("code < 400: " + i);
        }
        dt1.a aVar = new dt1.a();
        aVar.a(new OkHttpCall.NoContentResponseBody(et1Var.contentType(), et1Var.contentLength()));
        aVar.a(i);
        aVar.a("Response.error()");
        aVar.a(Protocol.HTTP_1_1);
        bt1.a aVar2 = new bt1.a();
        aVar2.b("http://localhost/");
        aVar.a(aVar2.a());
        return error(et1Var, aVar.a());
    }

    public static <T> Response<T> success(@Nullable T t) {
        dt1.a aVar = new dt1.a();
        aVar.a(200);
        aVar.a(WXModalUIModule.OK);
        aVar.a(Protocol.HTTP_1_1);
        bt1.a aVar2 = new bt1.a();
        aVar2.b("http://localhost/");
        aVar.a(aVar2.a());
        return success(t, aVar.a());
    }

    @Nullable
    public T body() {
        return this.body;
    }

    public int code() {
        return this.rawResponse.c();
    }

    @Nullable
    public et1 errorBody() {
        return this.errorBody;
    }

    public us1 headers() {
        return this.rawResponse.e();
    }

    public boolean isSuccessful() {
        return this.rawResponse.f();
    }

    public String message() {
        return this.rawResponse.g();
    }

    public dt1 raw() {
        return this.rawResponse;
    }

    public String toString() {
        return this.rawResponse.toString();
    }

    public static <T> Response<T> success(int i, @Nullable T t) {
        if (i >= 200 && i < 300) {
            dt1.a aVar = new dt1.a();
            aVar.a(i);
            aVar.a("Response.success()");
            aVar.a(Protocol.HTTP_1_1);
            bt1.a aVar2 = new bt1.a();
            aVar2.b("http://localhost/");
            aVar.a(aVar2.a());
            return success(t, aVar.a());
        }
        throw new IllegalArgumentException("code < 200 or >= 300: " + i);
    }

    public static <T> Response<T> error(et1 et1Var, dt1 dt1Var) {
        Utils.checkNotNull(et1Var, "body == null");
        Utils.checkNotNull(dt1Var, "rawResponse == null");
        if (!dt1Var.f()) {
            return new Response<>(dt1Var, null, et1Var);
        }
        throw new IllegalArgumentException("rawResponse should not be successful response");
    }

    public static <T> Response<T> success(@Nullable T t, us1 us1Var) {
        Utils.checkNotNull(us1Var, "headers == null");
        dt1.a aVar = new dt1.a();
        aVar.a(200);
        aVar.a(WXModalUIModule.OK);
        aVar.a(Protocol.HTTP_1_1);
        aVar.a(us1Var);
        bt1.a aVar2 = new bt1.a();
        aVar2.b("http://localhost/");
        aVar.a(aVar2.a());
        return success(t, aVar.a());
    }

    public static <T> Response<T> success(@Nullable T t, dt1 dt1Var) {
        Utils.checkNotNull(dt1Var, "rawResponse == null");
        if (dt1Var.f()) {
            return new Response<>(dt1Var, t, null);
        }
        throw new IllegalArgumentException("rawResponse must be successful response");
    }
}
