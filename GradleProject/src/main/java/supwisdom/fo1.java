package supwisdom;

import cz.msebera.android.httpclient.entity.ContentType;
import io.dcloud.common.util.net.NetWork;
import java.io.UnsupportedEncodingException;
import java.util.List;

/* JADX INFO: compiled from: UrlEncodedFormEntity.java */
/* JADX INFO: loaded from: classes2.dex */
public class fo1 extends no1 {
    public fo1(List<? extends co1> list, String str) throws UnsupportedEncodingException {
        super(ho1.a(list, str != null ? str : xo1.f9794a.name()), ContentType.create(NetWork.CONTENT_TYPE_UPLOAD, str));
    }
}
