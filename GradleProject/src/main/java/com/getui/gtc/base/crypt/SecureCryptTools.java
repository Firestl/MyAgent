package com.getui.gtc.base.crypt;

import android.content.Context;
import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.util.io.IOUtils;
import com.huawei.secure.android.common.encrypt.keystore.aes.AesCbcKS;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
public class SecureCryptTools {
    public static final String CIPHER_FLAG_FIRST = "First";
    public static final String CIPHER_FLAG_SECOND = "Second";
    public static final String CIPHER_FLAG_SEPARATOR = "-";
    public static final String CIPHER_FLAG_STARTER = ":::";
    public volatile boolean initInvoked;
    public ReentrantLock lock;
    public d secureKeyStore;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static SecureCryptTools f2124a = new SecureCryptTools();
    }

    public SecureCryptTools() {
        this.lock = new ReentrantLock();
        try {
            init(GtcProvider.context());
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private synchronized byte[] doDecrypt(byte[] bArr) throws CryptException {
        InputStream inputStream;
        Closeable closeable;
        ByteArrayOutputStream byteArrayOutputStream;
        InputStream inputStream2;
        Closeable closeable2;
        ByteArrayOutputStream byteArrayOutputStream2;
        String cipherFlag = getCipherFlag(bArr);
        if (cipherFlag == null) {
            throw new CryptException("Cipher flag not found in cipher text!");
        }
        String[] strArrSplit = cipherFlag.split("-");
        if (strArrSplit.length < 2) {
            throw new CryptException("Cipher flag is wrong in cipher text!");
        }
        String str = strArrSplit[0];
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 0, (bArr.length - cipherFlag.length()) - 3);
        InputStream inputStreamDecrypt = null;
        if (cipherFlag.endsWith(CIPHER_FLAG_FIRST)) {
            try {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArrCopyOfRange);
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                } catch (Throwable th) {
                    th = th;
                    inputStream = null;
                    inputStreamDecrypt = byteArrayInputStream;
                    closeable = inputStream;
                    try {
                        throw new CryptException("decrypt failed!", th);
                    } finally {
                    }
                }
                try {
                    inputStreamDecrypt = CryptTools.decrypt(AesCbcKS.c, this.secureKeyStore.a(str), this.secureKeyStore.c(str), byteArrayInputStream);
                    byte[] bArr2 = new byte[256];
                    while (true) {
                        int i = inputStreamDecrypt.read(bArr2);
                        if (i == -1) {
                            byteArrayOutputStream.flush();
                            byte[] byteArray = byteArrayOutputStream.toByteArray();
                            IOUtils.safeClose(inputStreamDecrypt);
                            IOUtils.safeClose(byteArrayInputStream);
                            IOUtils.safeClose(byteArrayOutputStream);
                            return byteArray;
                        }
                        byteArrayOutputStream.write(bArr2, 0, i);
                    }
                } catch (Throwable th2) {
                    th = th2;
                    closeable = byteArrayOutputStream;
                    inputStream = inputStreamDecrypt;
                    inputStreamDecrypt = byteArrayInputStream;
                    throw new CryptException("decrypt failed!", th);
                }
            } catch (Throwable th3) {
                th = th3;
                inputStream = null;
            }
        } else {
            if (!cipherFlag.endsWith(CIPHER_FLAG_SECOND)) {
                throw new CryptException("Cipher flag not found in cipher text!");
            }
            try {
                ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(bArrCopyOfRange);
                try {
                    byteArrayOutputStream2 = new ByteArrayOutputStream();
                } catch (Throwable th4) {
                    th = th4;
                    inputStream2 = null;
                    inputStreamDecrypt = byteArrayInputStream2;
                    closeable2 = inputStream2;
                    try {
                        throw new CryptException("decrypt failed!", th);
                    } finally {
                    }
                }
                try {
                    inputStreamDecrypt = CryptTools.decrypt(AesCbcKS.c, this.secureKeyStore.b(str), this.secureKeyStore.c(str), byteArrayInputStream2);
                    byte[] bArr3 = new byte[256];
                    while (true) {
                        int i2 = inputStreamDecrypt.read(bArr3);
                        if (i2 == -1) {
                            byteArrayOutputStream2.flush();
                            byte[] byteArray2 = byteArrayOutputStream2.toByteArray();
                            IOUtils.safeClose(inputStreamDecrypt);
                            IOUtils.safeClose(byteArrayInputStream2);
                            IOUtils.safeClose(byteArrayOutputStream2);
                            return byteArray2;
                        }
                        byteArrayOutputStream2.write(bArr3, 0, i2);
                    }
                } catch (Throwable th5) {
                    th = th5;
                    closeable2 = byteArrayOutputStream2;
                    inputStream2 = inputStreamDecrypt;
                    inputStreamDecrypt = byteArrayInputStream2;
                    throw new CryptException("decrypt failed!", th);
                }
            } catch (Throwable th6) {
                th = th6;
                inputStream2 = null;
            }
        }
    }

    private byte[] doEncrypt(byte[] bArr) throws CryptException {
        InputStream inputStream;
        InputStream inputStreamEncrypt;
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStreamEncrypt = null;
        if (this.secureKeyStore.c != null) {
            try {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                } catch (Throwable unused) {
                    byteArrayOutputStream = null;
                    byteArrayOutputStreamEncrypt = byteArrayInputStream;
                    inputStreamEncrypt = null;
                }
                try {
                    d dVar = this.secureKeyStore;
                    byteArrayOutputStreamEncrypt = CryptTools.encrypt(AesCbcKS.c, dVar.a(dVar.g), this.secureKeyStore.b(), byteArrayInputStream);
                    byte[] bArr2 = new byte[256];
                    while (true) {
                        int i = byteArrayOutputStreamEncrypt.read(bArr2);
                        if (i == -1) {
                            byteArrayOutputStream.flush();
                            byte[] byteArray = byteArrayOutputStream.toByteArray();
                            byte[] bytes = (CIPHER_FLAG_STARTER + this.secureKeyStore.g + "-First").getBytes();
                            int length = bytes.length;
                            byte[] bArr3 = new byte[byteArray.length + length];
                            System.arraycopy(byteArray, 0, bArr3, 0, byteArray.length);
                            System.arraycopy(bytes, 0, bArr3, byteArray.length, length);
                            IOUtils.safeClose(byteArrayOutputStreamEncrypt);
                            IOUtils.safeClose(byteArrayInputStream);
                            IOUtils.safeClose(byteArrayOutputStream);
                            return bArr3;
                        }
                        byteArrayOutputStream.write(bArr2, 0, i);
                    }
                } catch (Throwable unused2) {
                    InputStream inputStream2 = byteArrayOutputStreamEncrypt;
                    byteArrayOutputStreamEncrypt = byteArrayInputStream;
                    inputStreamEncrypt = inputStream2;
                    try {
                        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(bArr);
                        try {
                            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                            try {
                                inputStreamEncrypt = CryptTools.encrypt(AesCbcKS.c, this.secureKeyStore.a(), this.secureKeyStore.b(), byteArrayInputStream2);
                                byte[] bArr4 = new byte[256];
                                while (true) {
                                    int i2 = inputStreamEncrypt.read(bArr4);
                                    if (i2 == -1) {
                                        byteArrayOutputStream2.flush();
                                        byte[] byteArray2 = byteArrayOutputStream2.toByteArray();
                                        byte[] bytes2 = (CIPHER_FLAG_STARTER + this.secureKeyStore.g + "-Second").getBytes();
                                        int length2 = bytes2.length;
                                        byte[] bArr5 = new byte[byteArray2.length + length2];
                                        System.arraycopy(byteArray2, 0, bArr5, 0, byteArray2.length);
                                        System.arraycopy(bytes2, 0, bArr5, byteArray2.length, length2);
                                        IOUtils.safeClose(inputStreamEncrypt);
                                        IOUtils.safeClose(byteArrayInputStream2);
                                        IOUtils.safeClose(byteArrayOutputStream2);
                                        return bArr5;
                                    }
                                    byteArrayOutputStream2.write(bArr4, 0, i2);
                                }
                            } catch (Throwable th) {
                                th = th;
                                byteArrayOutputStream = byteArrayOutputStream2;
                                byteArrayOutputStreamEncrypt = byteArrayInputStream2;
                                try {
                                    throw new CryptException("encrypt failed", th);
                                } catch (Throwable th2) {
                                    IOUtils.safeClose(inputStreamEncrypt);
                                    throw th2;
                                }
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                    }
                }
            } catch (Throwable unused3) {
                inputStreamEncrypt = null;
                byteArrayOutputStream = null;
            }
        } else {
            try {
                ByteArrayInputStream byteArrayInputStream3 = new ByteArrayInputStream(bArr);
                try {
                    ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
                    byteArrayOutputStreamEncrypt = CryptTools.encrypt(AesCbcKS.c, this.secureKeyStore.a(), this.secureKeyStore.b(), byteArrayInputStream3);
                    byte[] bArr6 = new byte[256];
                    while (true) {
                        int i3 = byteArrayOutputStreamEncrypt.read(bArr6);
                        if (i3 == -1) {
                            byteArrayOutputStream3.flush();
                            byte[] byteArray3 = byteArrayOutputStream3.toByteArray();
                            byte[] bytes3 = (CIPHER_FLAG_STARTER + this.secureKeyStore.g + "-Second").getBytes();
                            int length3 = bytes3.length;
                            byte[] bArr7 = new byte[byteArray3.length + length3];
                            System.arraycopy(byteArray3, 0, bArr7, 0, byteArray3.length);
                            System.arraycopy(bytes3, 0, bArr7, byteArray3.length, length3);
                            IOUtils.safeClose(byteArrayOutputStreamEncrypt);
                            IOUtils.safeClose(byteArrayInputStream3);
                            return bArr7;
                        }
                        byteArrayOutputStream3.write(bArr6, 0, i3);
                    }
                } catch (Throwable th5) {
                    th = th5;
                    InputStream inputStream3 = byteArrayOutputStreamEncrypt;
                    byteArrayOutputStreamEncrypt = byteArrayInputStream3;
                    inputStream = inputStream3;
                    try {
                        throw new CryptException("encrypt failed", th);
                    } finally {
                        IOUtils.safeClose(inputStream);
                        IOUtils.safeClose(byteArrayOutputStreamEncrypt);
                    }
                }
            } catch (Throwable th6) {
                th = th6;
                inputStream = null;
            }
        }
    }

    private String getCipherFlag(byte[] bArr) {
        String str = new String(bArr);
        int iLastIndexOf = str.lastIndexOf(CIPHER_FLAG_STARTER);
        if (iLastIndexOf < 0) {
            return null;
        }
        return str.substring(iLastIndexOf + 3);
    }

    public static SecureCryptTools getInstance() {
        return a.f2124a;
    }

    private List<CryptException> init(Context context) throws CryptException {
        List<CryptException> listA;
        try {
            this.lock.lock();
            if (this.initInvoked) {
                listA = Collections.emptyList();
            } else {
                this.initInvoked = true;
                d dVar = new d();
                this.secureKeyStore = dVar;
                listA = dVar.a(context);
            }
            return listA;
        } finally {
            this.lock.unlock();
        }
    }

    public byte[] decrypt(byte[] bArr) throws CryptException {
        if (!this.initInvoked) {
            throw new CryptException("SecureCryptTools: please init firstly!");
        }
        try {
            try {
                this.lock.tryLock(3000L, TimeUnit.MILLISECONDS);
                return doDecrypt(bArr);
            } catch (InterruptedException unused) {
                throw new CryptException("SecureCryptTools: wait init time out!");
            }
        } finally {
            if (this.lock.isLocked()) {
                this.lock.unlock();
            }
        }
    }

    public byte[] encrypt(byte[] bArr) throws CryptException {
        if (!this.initInvoked) {
            throw new CryptException("SecureCryptTools: please init firstly!");
        }
        try {
            try {
                this.lock.tryLock(3000L, TimeUnit.MILLISECONDS);
                return doEncrypt(bArr);
            } catch (InterruptedException unused) {
                throw new CryptException("SecureCryptTools: wait init time out!");
            }
        } finally {
            if (this.lock.isLocked()) {
                this.lock.unlock();
            }
        }
    }
}
