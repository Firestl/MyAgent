package supwisdom;

import android.app.Activity;
import android.os.Bundle;
import com.sangfor.sdk.base.SFLaunchReason;
import com.sangfor.sdk.base.SFOnlineType;
import com.sangfor.sdk.base.SFSDKMode;
import com.sangfor.sdk.utils.SFLogN;
import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class e91 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public SFOnlineType f7456a;
    public String b;
    public String c;
    public Bundle d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public SFLaunchReason f7457e;
    public ub1 f;
    public Activity g;

    public e91() {
        SFSDKMode.MODE_VPN.intValue();
        SFLogN.a();
        this.f7456a = SFOnlineType.UNKNOWN;
        this.f7457e = SFLaunchReason.UNKNOWN;
    }

    public void a(int i) {
    }

    public void a(Bundle bundle) {
        this.d = bundle;
    }

    public void b(int i) {
    }

    public void b(String str) {
        this.c = str;
    }

    public String toString() {
        return "SFLaunchInfo{mOnlineType=" + this.f7456a + ", mPackageName='" + this.b + Operators.SINGLE_QUOTE + ", mSrcSdkVersion='" + this.c + Operators.SINGLE_QUOTE + ", mBundle=" + this.d + ", mLaunchReason=" + this.f7457e + ", mSubAppInfo=" + this.f + ", mForegroundActivity=" + this.g + Operators.BLOCK_END;
    }

    public void a(SFLaunchReason sFLaunchReason) {
        this.f7457e = sFLaunchReason;
    }

    public void a(SFOnlineType sFOnlineType) {
        this.f7456a = sFOnlineType;
    }

    public void a(String str) {
        this.b = str;
    }

    public void a(ub1 ub1Var) {
        this.f = ub1Var;
    }
}
