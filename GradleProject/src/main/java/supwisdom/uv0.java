package supwisdom;

import android.content.ContentValues;
import android.database.Cursor;
import com.lzy.okgo.cache.CacheEntity;
import io.dcloud.common.DHInterface.IApp;
import java.util.List;

/* JADX INFO: compiled from: CacheManager.java */
/* JADX INFO: loaded from: classes2.dex */
public class uv0 extends tv0<CacheEntity<?>> {

    /* JADX INFO: compiled from: CacheManager.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final uv0 f9447a = new uv0();
    }

    public static uv0 c() {
        return b.f9447a;
    }

    @Override // supwisdom.tv0
    public String a() {
        return IApp.ConfigProperty.CONFIG_CACHE;
    }

    public boolean b(String str) {
        if (str == null) {
            return false;
        }
        return a("key=?", new String[]{str});
    }

    public uv0() {
        super(new wv0());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // supwisdom.tv0
    public CacheEntity<?> a(Cursor cursor) {
        return CacheEntity.parseCursorToBean(cursor);
    }

    @Override // supwisdom.tv0
    public ContentValues a(CacheEntity<?> cacheEntity) {
        return CacheEntity.getContentValues(cacheEntity);
    }

    public CacheEntity<?> a(String str) {
        if (str == null) {
            return null;
        }
        List<CacheEntity<?>> listB = b("key=?", new String[]{str});
        if (listB.size() > 0) {
            return listB.get(0);
        }
        return null;
    }

    public <T> CacheEntity<T> a(String str, CacheEntity<T> cacheEntity) {
        cacheEntity.setKey(str);
        b(cacheEntity);
        return cacheEntity;
    }
}
