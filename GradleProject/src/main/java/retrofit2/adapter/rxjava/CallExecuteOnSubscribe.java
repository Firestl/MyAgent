package retrofit2.adapter.rxjava;

import retrofit2.Call;
import retrofit2.Response;
import supwisdom.rw1;
import supwisdom.xw1;
import supwisdom.zw1;

/* JADX INFO: loaded from: classes3.dex */
public final class CallExecuteOnSubscribe<T> implements rw1.a<Response<T>> {
    public final Call<T> originalCall;

    public CallExecuteOnSubscribe(Call<T> call) {
        this.originalCall = call;
    }

    @Override // supwisdom.bx1
    public void call(xw1<? super Response<T>> xw1Var) {
        Call<T> callClone = this.originalCall.clone();
        CallArbiter callArbiter = new CallArbiter(callClone, xw1Var);
        xw1Var.add(callArbiter);
        xw1Var.setProducer(callArbiter);
        try {
            callArbiter.emitResponse(callClone.execute());
        } catch (Throwable th) {
            zw1.b(th);
            callArbiter.emitError(th);
        }
    }
}
