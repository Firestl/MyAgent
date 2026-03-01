package supwisdom;

import android.text.TextUtils;
import com.sangfor.sdk.base.SFOnlineState;
import com.sangfor.sdk.base.SFOnlineType;
import com.sangfor.sdk.base.SFSyncDataResult;
import com.sangfor.sdk.utils.SFLogN;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class d91 extends c91 {
    public SFOnlineType a(String str) {
        SFLogN.c("LaunchEntryInternal", "updateCompleteData in");
        if (TextUtils.isEmpty(str)) {
            SFLogN.b("LaunchEntryInternal", "updateCompleteData failed.", "sharedData is empty.");
            return SFOnlineType.UNKNOWN;
        }
        SFSyncDataResult sFSyncDataResultB = b71.b().a().d().b(str);
        SFLogN.c("LaunchEntryInternal", "updateCompleteData ret:" + sFSyncDataResultB);
        SFOnlineType sFOnlineType = SFOnlineType.UNKNOWN;
        if (sFSyncDataResultB == SFSyncDataResult.SyncDataComplete) {
            sFOnlineType = SFOnlineType.INNER;
        } else if (sFSyncDataResultB == SFSyncDataResult.SyncDataSession) {
            sFOnlineType = SFOnlineType.SESSION;
        } else {
            SFLogN.b("LaunchEntryInternal", "processUpdateResult failed", "SFSyncDataResult:" + sFSyncDataResultB);
        }
        if (sFOnlineType == SFOnlineType.INNER) {
            b71.b().a().g().a(SFOnlineState.ONLINE_STATE_ONLINE);
        }
        return sFOnlineType;
    }
}
