package cn.com.chinatelecom.account.api.e;

import com.taobao.weex.wson.Wson;
import org.bouncycastle.jcajce.provider.asymmetric.edec.KeyFactorySpi;
import org.bouncycastle.util.encoders.UTF8;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f1512a = {59, 50, 51};
    public static final byte[] b = {Wson.MAP_TYPE, 98, 114};
    public static final byte[] c = {Wson.MAP_TYPE, 98};
    public static final byte[] d = {UTF8.S_P4B, Wson.STRING_TYPE};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final byte[] f1513e = {126, Wson.STRING_TYPE, 114};
    public static final byte[] f = {99, KeyFactorySpi.x25519_type, 60, 36, Wson.NUMBER_BIG_INTEGER_TYPE, KeyFactorySpi.x448_type};
    public static final byte[] g = {Wson.NUMBER_BIG_DECIMAL_TYPE, 122, KeyFactorySpi.x448_type, Wson.NUMBER_DOUBLE_TYPE, 36, KeyFactorySpi.x448_type, 36, 59, 50, 51, 36, Wson.NUMBER_INT_TYPE, Wson.NUMBER_DOUBLE_TYPE};
    public static int h = 0;
}
