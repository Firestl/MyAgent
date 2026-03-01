package supwisdom;

import java.util.List;
import java.util.Map;
import supwisdom.si;

/* JADX INFO: compiled from: IEventHandler.java */
/* JADX INFO: loaded from: classes.dex */
public interface vi extends wi {
    void a(String str);

    void a(String str, Map<String, Object> map, jj jjVar, List<Map<String, Object>> list, si.d dVar);

    void a(Object[] objArr);

    boolean a(String str, String str2);

    void b(String str);

    void b(Map<String, Object> map);

    boolean b(String str, String str2);

    void c(String str, String str2);

    void onActivityPause();

    void onActivityResume();

    void onDestroy();
}
