package io.dcloud.feature.fingerprint;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.KeyguardManager;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.DHInterface.StandardFeature;
import io.dcloud.common.util.JSUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import supwisdom.e9;
import supwisdom.l9;

/* JADX INFO: loaded from: classes2.dex */
public class FingerPrintsImpl extends StandardFeature implements ISysEventListener {
    public IWebview iWebview;
    public l9 mCancellationSignal;
    public e9 mFingerprintManager;

    public interface IFingerprintResultListener {
        void onAuthenticateError(int i, CharSequence charSequence);

        void onAuthenticateFailed();

        void onAuthenticateHelp(int i, CharSequence charSequence);

        void onAuthenticateSucceeded(e9.c cVar);
    }

    @SuppressLint({"NewApi"})
    private boolean isHasEnrolledFingerprints() {
        try {
            return this.mFingerprintManager.a();
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resultCallback(IWebview iWebview, String str, int i, String str2, int i2, Boolean bool) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", i);
            jSONObject.put("message", str2);
        } catch (Exception unused) {
        }
        JSUtil.execCallback(iWebview, str, jSONObject, i2, bool.booleanValue());
    }

    public void authenticate(final IWebview iWebview, JSONArray jSONArray) {
        final String strOptString = jSONArray.optString(0);
        if (this.mFingerprintManager == null) {
            this.mFingerprintManager = e9.a(iWebview.getActivity());
        }
        if (!isHardwareDetected()) {
            resultCallback(iWebview, strOptString, 1, "no fingerprint device", JSUtil.ERROR, false);
            return;
        }
        if (!isHasEnrolledFingerprints()) {
            resultCallback(iWebview, strOptString, 3, "UNENROLLED", JSUtil.ERROR, false);
        } else {
            if (!isKeyguardSecure(iWebview.getActivity())) {
                resultCallback(iWebview, strOptString, 2, "IN SECURE", JSUtil.ERROR, false);
                return;
            }
            this.iWebview = iWebview;
            registerSysEvent(iWebview, ISysEventListener.SysEventType.onKeyUp);
            callFingerPrintVerify(new IFingerprintResultListener() { // from class: io.dcloud.feature.fingerprint.FingerPrintsImpl.1
                @Override // io.dcloud.feature.fingerprint.FingerPrintsImpl.IFingerprintResultListener
                public void onAuthenticateError(int i, CharSequence charSequence) {
                    FingerPrintsImpl.this.resultCallback(iWebview, strOptString, i == 5 ? 6 : i == 7 ? 5 : 7, charSequence.toString(), JSUtil.ERROR, false);
                }

                @Override // io.dcloud.feature.fingerprint.FingerPrintsImpl.IFingerprintResultListener
                public void onAuthenticateFailed() {
                    FingerPrintsImpl.this.resultCallback(iWebview, strOptString, 4, "Authenticate not match", JSUtil.ERROR, true);
                }

                @Override // io.dcloud.feature.fingerprint.FingerPrintsImpl.IFingerprintResultListener
                public void onAuthenticateHelp(int i, CharSequence charSequence) {
                }

                @Override // io.dcloud.feature.fingerprint.FingerPrintsImpl.IFingerprintResultListener
                public void onAuthenticateSucceeded(e9.c cVar) {
                    FingerPrintsImpl.this.resultCallback(iWebview, strOptString, 0, "Authenticate Succeeded", JSUtil.OK, false);
                }
            });
        }
    }

    @SuppressLint({"NewApi"})
    public void callFingerPrintVerify(final IFingerprintResultListener iFingerprintResultListener) {
        if (this.mCancellationSignal == null) {
            this.mCancellationSignal = new l9();
        }
        try {
            this.mFingerprintManager.a(null, 0, this.mCancellationSignal, new e9.b() { // from class: io.dcloud.feature.fingerprint.FingerPrintsImpl.2
                @Override // supwisdom.e9.b
                public void onAuthenticationError(int i, CharSequence charSequence) {
                    IFingerprintResultListener iFingerprintResultListener2 = iFingerprintResultListener;
                    if (iFingerprintResultListener2 != null) {
                        iFingerprintResultListener2.onAuthenticateError(i, charSequence);
                    }
                }

                @Override // supwisdom.e9.b
                public void onAuthenticationFailed() {
                    IFingerprintResultListener iFingerprintResultListener2 = iFingerprintResultListener;
                    if (iFingerprintResultListener2 != null) {
                        iFingerprintResultListener2.onAuthenticateFailed();
                    }
                }

                @Override // supwisdom.e9.b
                public void onAuthenticationHelp(int i, CharSequence charSequence) {
                    IFingerprintResultListener iFingerprintResultListener2 = iFingerprintResultListener;
                    if (iFingerprintResultListener2 != null) {
                        iFingerprintResultListener2.onAuthenticateHelp(i, charSequence);
                    }
                }

                @Override // supwisdom.e9.b
                public void onAuthenticationSucceeded(e9.c cVar) {
                    IFingerprintResultListener iFingerprintResultListener2 = iFingerprintResultListener;
                    if (iFingerprintResultListener2 != null) {
                        iFingerprintResultListener2.onAuthenticateSucceeded(cVar);
                    }
                }
            }, null);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void cancel(IWebview iWebview, JSONArray jSONArray) {
        cancelAuthenticate(iWebview);
    }

    public void cancelAuthenticate(IWebview iWebview) {
        if (iWebview != null) {
            unregisterSysEvent(iWebview, ISysEventListener.SysEventType.onKeyUp);
        }
        l9 l9Var = this.mCancellationSignal;
        if (l9Var != null) {
            l9Var.a();
            this.mCancellationSignal = null;
            this.iWebview = null;
        }
    }

    public String isEnrolledFingerprints(IWebview iWebview, JSONArray jSONArray) {
        if (this.mFingerprintManager == null) {
            this.mFingerprintManager = e9.a(iWebview.getActivity());
        }
        return isHasEnrolledFingerprints() ? JSUtil.wrapJsVar(true) : JSUtil.wrapJsVar(false);
    }

    @SuppressLint({"NewApi"})
    public boolean isHardwareDetected() {
        try {
            return this.mFingerprintManager.b();
        } catch (Exception unused) {
            return false;
        }
    }

    public String isKeyguardSecure(IWebview iWebview, JSONArray jSONArray) {
        return isKeyguardSecure(iWebview.getActivity()) ? JSUtil.wrapJsVar(true) : JSUtil.wrapJsVar(false);
    }

    public String isSupport(IWebview iWebview, JSONArray jSONArray) {
        if (this.mFingerprintManager == null) {
            this.mFingerprintManager = e9.a(iWebview.getActivity());
        }
        return isHardwareDetected() ? JSUtil.wrapJsVar(true) : JSUtil.wrapJsVar(false);
    }

    @Override // io.dcloud.common.DHInterface.BaseFeature
    public boolean onEventExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
        l9 l9Var;
        if (sysEventType != ISysEventListener.SysEventType.onKeyUp || ((Integer) ((Object[]) obj)[0]).intValue() != 4 || (l9Var = this.mCancellationSignal) == null || l9Var.c()) {
            return false;
        }
        cancelAuthenticate(this.iWebview);
        return true;
    }

    @SuppressLint({"NewApi"})
    private boolean isKeyguardSecure(Activity activity) {
        try {
            return ((KeyguardManager) activity.getSystemService("keyguard")).isKeyguardSecure();
        } catch (Exception unused) {
            return false;
        }
    }
}
