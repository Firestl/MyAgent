package supwisdom;

import android.app.Activity;
import android.util.SparseArray;
import com.sangfor.sdk.utils.SFLogN;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class g71 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SparseArray<List<Activity>> f7700a;

    /* JADX INFO: compiled from: Proguard */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final g71 f7701a = new g71();
    }

    public static g71 a() {
        return b.f7701a;
    }

    public void b(Activity activity) {
        List<Activity> list;
        if (activity == null || (list = this.f7700a.get(activity.getTaskId())) == null) {
            return;
        }
        SFLogN.c("ActivityMonitor", "removeAuthActivity " + activity.getClass().getName() + " from task: " + activity.getTaskId());
        list.remove(activity);
    }

    public g71() {
        this.f7700a = new SparseArray<>();
        new ArrayList();
    }

    public void a(Activity activity) {
        if (activity == null) {
            return;
        }
        List<Activity> arrayList = this.f7700a.get(activity.getTaskId());
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.f7700a.put(activity.getTaskId(), arrayList);
        }
        SFLogN.c("ActivityMonitor", "addAuthActivity " + activity.getClass().getName() + " to task: " + activity.getTaskId());
        arrayList.add(activity);
    }
}
