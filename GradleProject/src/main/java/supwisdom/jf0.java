package supwisdom;

import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.Status;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public class jf0 {
    public static ApiException a(@RecentlyNonNull Status status) {
        return status.g() ? new ResolvableApiException(status) : new ApiException(status);
    }
}
