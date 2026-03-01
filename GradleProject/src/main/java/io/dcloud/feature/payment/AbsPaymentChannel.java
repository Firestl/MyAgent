package io.dcloud.feature.payment;

import android.content.Context;
import io.dcloud.common.DHInterface.IReflectAble;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AbsPaymentChannel implements IReflectAble {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f6564a;
    public String description;
    public String featureName;
    public String id;
    public Context mContext;
    public final IPaymentListener mListener = new a();
    public IWebview mWebview;
    public String name;
    public boolean serviceReady;

    public class a implements IPaymentListener {
        public a() {
        }

        @Override // io.dcloud.feature.payment.IPaymentListener
        public void onError(int i, String str) {
            String json = DOMException.toJSON(i, str);
            AbsPaymentChannel absPaymentChannel = AbsPaymentChannel.this;
            Deprecated_JSUtil.execCallback(absPaymentChannel.mWebview, absPaymentChannel.f6564a, json, JSUtil.ERROR, true, false);
        }

        @Override // io.dcloud.feature.payment.IPaymentListener
        public void onSuccess(PaymentResult paymentResult) {
            AbsPaymentChannel absPaymentChannel = AbsPaymentChannel.this;
            JSUtil.execCallback(absPaymentChannel.mWebview, absPaymentChannel.f6564a, paymentResult.toJSONObject(), JSUtil.OK, false);
        }
    }

    public String getFullDescription() {
        return this.featureName + this.description;
    }

    public void init(Context context) {
        this.mContext = context;
    }

    public abstract void installService();

    public abstract void request(String str);

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.id);
            jSONObject.put("description", this.description);
            jSONObject.put("serviceReady", this.serviceReady);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public void updateWebview(IWebview iWebview) {
        this.mWebview = iWebview;
    }

    public final void a(String str, String str2) {
        this.f6564a = str2;
        request(str);
    }
}
