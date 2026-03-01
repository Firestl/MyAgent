package supwisdom;

import com.sangfor.dex.MethodHandle$MethodHandleType;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public /* synthetic */ class qy0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f8972a;

    static {
        int[] iArr = new int[MethodHandle$MethodHandleType.values().length];
        f8972a = iArr;
        try {
            iArr[MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_STATIC_PUT.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f8972a[MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_STATIC_GET.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            f8972a[MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_INSTANCE_PUT.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            f8972a[MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_INSTANCE_GET.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
    }
}
