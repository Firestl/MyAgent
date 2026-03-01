package supwisdom;

import com.sangfor.sdk.base.SFAuthType;
import com.sangfor.sdk.base.ServiceInfo;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class l81 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f8262a;
    public String b;
    public String c;
    public SFAuthType d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f8263e;
    public List<String> f;
    public List<ServiceInfo> g;

    public l81(long j, String str, String str2) {
        this(j, SFAuthType.AUTH_TYPE_NONE.intValue(), str, str2);
    }

    public String toString() {
        return "SFBaseMessage{mErrCode=" + this.f8262a + ", mErrStr='" + this.b + Operators.SINGLE_QUOTE + ", currentAuthType='" + this.d + Operators.SINGLE_QUOTE + ", mServerInfo='" + this.c + Operators.SINGLE_QUOTE + ", mDisplayName='" + this.f8263e + Operators.SINGLE_QUOTE + ", mEnhanceAuthTips=" + this.f.toString() + Operators.SINGLE_QUOTE + ", mNextServiceList=" + this.g.toString() + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }

    public l81(long j, int i, String str, String str2) {
        this.f = new ArrayList();
        this.g = new ArrayList();
        this.f8262a = j;
        this.b = str;
        this.c = str2;
        this.d = SFAuthType.valueOf(i);
    }
}
