package retrofit2.adapter.rxjava;

import com.taobao.weex.el.parse.Operators;
import javax.annotation.Nullable;
import retrofit2.Response;

/* JADX INFO: loaded from: classes3.dex */
public final class Result<T> {

    @Nullable
    public final Throwable error;

    @Nullable
    public final Response<T> response;

    public Result(@Nullable Response<T> response, @Nullable Throwable th) {
        this.response = response;
        this.error = th;
    }

    public static <T> Result<T> error(Throwable th) {
        if (th != null) {
            return new Result<>(null, th);
        }
        throw new NullPointerException("error == null");
    }

    public static <T> Result<T> response(Response<T> response) {
        if (response != null) {
            return new Result<>(response, null);
        }
        throw new NullPointerException("response == null");
    }

    public boolean isError() {
        return this.error != null;
    }

    public String toString() {
        if (this.error != null) {
            return "Result{isError=true, error=\"" + this.error + "\"}";
        }
        return "Result{isError=false, response=" + this.response + Operators.BLOCK_END;
    }

    @Nullable
    public Throwable error() {
        return this.error;
    }

    @Nullable
    public Response<T> response() {
        return this.response;
    }
}
