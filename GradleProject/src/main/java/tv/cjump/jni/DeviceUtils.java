package tv.cjump.jni;

import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import com.taobao.weex.utils.WXSoInstallMgrSdk;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes3.dex */
public class DeviceUtils {
    public static final String ABI_MIPS = "mips";
    public static final String ABI_X86 = "x86";
    public static final int EM_386 = 3;
    public static final int EM_AARCH64 = 183;
    public static final int EM_ARM = 40;
    public static final int EM_MIPS = 8;
    public static ARCH sArch = ARCH.Unknown;

    public enum ARCH {
        Unknown,
        ARM,
        X86,
        MIPS,
        ARM64
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v20 */
    /* JADX WARN: Type inference failed for: r3v21 */
    /* JADX WARN: Type inference failed for: r3v22 */
    /* JADX WARN: Type inference failed for: r3v23 */
    /* JADX WARN: Type inference failed for: r3v24 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.io.RandomAccessFile] */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8 */
    public static synchronized ARCH getMyCpuArch() {
        RandomAccessFile randomAccessFile;
        ?? r3;
        byte[] bArr = new byte[20];
        File file = new File(Environment.getRootDirectory(), "lib/libc.so");
        if (file.canRead()) {
            ?? r32 = 0;
            RandomAccessFile randomAccessFile2 = null;
            RandomAccessFile randomAccessFile3 = null;
            try {
                try {
                    randomAccessFile = new RandomAccessFile(file, "r");
                } catch (FileNotFoundException e2) {
                    e = e2;
                } catch (IOException e3) {
                    e = e3;
                }
                try {
                    randomAccessFile.readFully(bArr);
                    r3 = 8;
                    r3 = 8;
                    r3 = 8;
                    r3 = 8;
                    int i = bArr[18] | (bArr[19] << 8);
                    if (i == 3) {
                        sArch = ARCH.X86;
                    } else if (i == 8) {
                        sArch = ARCH.MIPS;
                    } else if (i == 40) {
                        sArch = ARCH.ARM;
                    } else if (i != 183) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("libc.so is unknown arch: ");
                        sb.append(Integer.toHexString(i));
                        Log.e("NativeBitmapFactory", sb.toString());
                        r3 = sb;
                    } else {
                        sArch = ARCH.ARM64;
                    }
                } catch (FileNotFoundException e4) {
                    e = e4;
                    randomAccessFile2 = randomAccessFile;
                    e.printStackTrace();
                    r32 = randomAccessFile2;
                    if (randomAccessFile2 != null) {
                        try {
                            randomAccessFile2.close();
                            r32 = randomAccessFile2;
                        } catch (IOException e5) {
                            e = e5;
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e6) {
                    e = e6;
                    randomAccessFile3 = randomAccessFile;
                    e.printStackTrace();
                    r32 = randomAccessFile3;
                    if (randomAccessFile3 != null) {
                        try {
                            randomAccessFile3.close();
                            r32 = randomAccessFile3;
                        } catch (IOException e7) {
                            e = e7;
                            e.printStackTrace();
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    r32 = randomAccessFile;
                    if (r32 != 0) {
                        try {
                            r32.close();
                        } catch (IOException e8) {
                            e8.printStackTrace();
                        }
                    }
                    throw th;
                }
                try {
                    randomAccessFile.close();
                    r32 = r3;
                } catch (IOException e9) {
                    e = e9;
                    e.printStackTrace();
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return sArch;
    }

    public static String get_CPU_ABI() {
        return Build.CPU_ABI;
    }

    public static String get_CPU_ABI2() {
        try {
            Field declaredField = Build.class.getDeclaredField("CPU_ABI2");
            if (declaredField == null) {
                return null;
            }
            Object obj = declaredField.get(null);
            if (obj instanceof String) {
                return (String) obj;
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean isARMSimulatedByX86() {
        return !supportX86() && ARCH.X86.equals(getMyCpuArch());
    }

    public static boolean isMagicBoxDevice() {
        return Build.MANUFACTURER.equalsIgnoreCase("MagicBox") && Build.PRODUCT.equalsIgnoreCase("MagicBox");
    }

    public static boolean isMiBox2Device() {
        return Build.MANUFACTURER.equalsIgnoreCase("Xiaomi") && Build.PRODUCT.equalsIgnoreCase("dredd");
    }

    public static boolean isProblemBoxDevice() {
        return isMiBox2Device() || isMagicBoxDevice();
    }

    public static boolean isRealARMArch() {
        return (supportABI("armeabi-v7a") || supportABI(WXSoInstallMgrSdk.ARMEABI)) && ARCH.ARM.equals(getMyCpuArch());
    }

    public static boolean isRealX86Arch() {
        return supportABI("x86") || ARCH.X86.equals(getMyCpuArch());
    }

    public static boolean supportABI(String str) {
        String _cpu_abi = get_CPU_ABI();
        if (TextUtils.isEmpty(_cpu_abi) || !_cpu_abi.equalsIgnoreCase(str)) {
            return !TextUtils.isEmpty(get_CPU_ABI2()) && _cpu_abi.equalsIgnoreCase(str);
        }
        return true;
    }

    public static boolean supportMips() {
        return supportABI("mips");
    }

    public static boolean supportX86() {
        return supportABI("x86");
    }
}
