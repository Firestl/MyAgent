package com.baidu.speech.audio;

import android.media.AudioRecord;
import android.net.LocalServerSocket;
import android.net.LocalSocket;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.speech.utils.LogUtil;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class MicrophoneServer implements Runnable {
    public static final int PORT = 3277;
    public static final int S_DATA_LENGTH = 1920000;
    public static final int S_LENGTH = 640;
    public static final String TAG = MicrophoneServer.class.getSimpleName();
    public static HashMap<String, MicrophoneServer> sPorts = new HashMap<>();
    public Future<Integer> future;
    public int mAudioSource;
    public String mInfile;
    public final int mServerPort;
    public LocalServerSocket mServerSocket;
    public final byte[] sData = new byte[S_DATA_LENGTH];
    public final int sLen = 640;
    public long sLimit = 0;
    public ArrayList<SocketWrap> mRemoteOutputStreams = new ArrayList<>();
    public String SOCKET_ADDRESS = "com.baidu.speech";
    public DataInputStream mIn = null;
    public boolean firstStart = true;
    public ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();

    public static class MicInputStream extends InputStream {
        public static final int DEFAULT_BUFFER_SIZE = 160000;
        public String TAG = MicInputStream.class.getSimpleName();
        public AudioRecord mAudioRecord;

        public MicInputStream(int i, int i2) {
            int i3;
            try {
                try {
                    this.mAudioRecord = new AudioRecord(i, i2, 16, 2, DEFAULT_BUFFER_SIZE);
                    LogUtil.i("audioSource : ", i + "");
                    LogUtil.i(this.TAG, "startRecordingAndCheckStatus recorder status is " + this.mAudioRecord.getState());
                    this.mAudioRecord.startRecording();
                    byte[] bArr = new byte[32];
                    int i4 = 0;
                    while (true) {
                        if (i4 >= 10) {
                            i3 = 0;
                            break;
                        }
                        int i5 = this.mAudioRecord.read(bArr, 0, 32);
                        if (i5 > 0) {
                            i3 = i5 + 0;
                            break;
                        }
                        i4++;
                    }
                    if (i3 <= 0) {
                        this.mAudioRecord.release();
                        new Exception("bad recorder, read(byte[])");
                    }
                    AudioRecord audioRecord = this.mAudioRecord;
                    if ((audioRecord == null || audioRecord.getRecordingState() == 3) && this.mAudioRecord.getState() != 0) {
                        return;
                    }
                    try {
                        this.mAudioRecord.release();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    LogUtil.d(this.TAG, "recorder start failed, RecordingState=" + this.mAudioRecord.getRecordingState());
                } catch (Exception e3) {
                    e3.printStackTrace();
                    AudioRecord audioRecord2 = this.mAudioRecord;
                    if ((audioRecord2 == null || audioRecord2.getRecordingState() == 3) && this.mAudioRecord.getState() != 0) {
                        return;
                    }
                    try {
                        this.mAudioRecord.release();
                    } catch (Exception e4) {
                        e4.printStackTrace();
                    }
                    LogUtil.d(this.TAG, "recorder start failed, RecordingState=" + this.mAudioRecord.getRecordingState());
                }
            } catch (Throwable th) {
                AudioRecord audioRecord3 = this.mAudioRecord;
                if ((audioRecord3 != null && audioRecord3.getRecordingState() != 3) || this.mAudioRecord.getState() == 0) {
                    try {
                        this.mAudioRecord.release();
                    } catch (Exception e5) {
                        e5.printStackTrace();
                    }
                    LogUtil.d(this.TAG, "recorder start failed, RecordingState=" + this.mAudioRecord.getRecordingState());
                }
                throw th;
            }
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            AudioRecord audioRecord = this.mAudioRecord;
            if (audioRecord != null) {
                audioRecord.release();
            }
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            throw new IOException("read not support");
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            AudioRecord audioRecord = this.mAudioRecord;
            if (audioRecord == null) {
                throw new IOException("audio recorder is null");
            }
            int i3 = audioRecord.read(bArr, i, i2);
            LogUtil.v(this.TAG, " AudioRecord read: len:" + i3 + " byteOffset:" + i + " byteCount:" + i2);
            if (i3 >= 0 && i3 <= i2) {
                return i3;
            }
            throw new IOException("audio recdoder read error, len = " + i3);
        }
    }

    public static class SocketWrap extends LocalSocket {
        public final LocalSocket mSocket;
        public long mPosition = -1;
        public byte[] data = new byte[8];

        public SocketWrap(LocalSocket localSocket) {
            this.mSocket = localSocket;
            try {
                localSocket.setSoTimeout(2000);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        private long byteArrayToInt(byte[] bArr) {
            byte[] bArr2 = new byte[8];
            int i = 7;
            int i2 = 0;
            while (i >= 0) {
                if (i2 < bArr.length) {
                    bArr2[i] = bArr[i2];
                } else {
                    bArr2[i] = 0;
                }
                i--;
                i2++;
            }
            long j = ((long) (bArr2[0] & 255)) << 56;
            long j2 = ((long) (bArr2[1] & 255)) << 48;
            long j3 = ((long) (bArr2[2] & 255)) << 40;
            long j4 = ((long) (bArr2[3] & 255)) << 32;
            long j5 = ((long) (bArr2[4] & 255)) << 24;
            return j + j2 + j3 + j4 + j5 + (((long) (bArr2[5] & 255)) << 16) + (((long) (bArr2[6] & 255)) << 8) + ((long) (bArr2[7] & 255));
        }

        @Override // android.net.LocalSocket, java.io.Closeable, java.lang.AutoCloseable
        public synchronized void close() throws IOException {
            this.mSocket.close();
        }

        @Override // android.net.LocalSocket
        public OutputStream getOutputStream() throws IOException {
            return this.mSocket.getOutputStream();
        }

        public long getPosition(long j) {
            long j2 = this.mPosition;
            if (j2 >= 0) {
                return j2;
            }
            try {
                this.mSocket.getInputStream().read(this.data, 0, this.data.length);
                long jByteArrayToInt = byteArrayToInt(this.data);
                Log.i(MicrophoneServer.TAG, "audio mills is " + jByteArrayToInt);
                if (jByteArrayToInt > 0) {
                    this.mPosition = (Math.min(Math.max(0L, System.currentTimeMillis() - jByteArrayToInt), j / 32) / 20) * 20 * 32;
                } else {
                    this.mPosition = 640L;
                }
                this.mPosition = ((j - this.mPosition) + 1920000) % 1920000;
            } catch (Exception e2) {
                this.mPosition = ((j - 640) + 1920000) % 1920000;
                e2.printStackTrace();
            }
            return this.mPosition;
        }

        public void setPosition(long j) {
            this.mPosition = j;
        }

        @Override // android.net.LocalSocket
        public void shutdownOutput() throws IOException {
            this.mSocket.shutdownOutput();
        }
    }

    public MicrophoneServer(String str, int i) throws IOException {
        int iNextInt;
        LocalServerSocket localServerSocket;
        this.mInfile = str;
        this.mAudioSource = i;
        Log.i(TAG, "infile:" + str + "  audioSource:" + i);
        if (TextUtils.isEmpty(str)) {
            iNextInt = new Random().nextInt(1000);
            localServerSocket = new LocalServerSocket(this.SOCKET_ADDRESS + "_" + iNextInt);
        } else {
            iNextInt = new Random().nextInt(1000);
            localServerSocket = new LocalServerSocket(this.SOCKET_ADDRESS + "_" + iNextInt);
        }
        this.mServerSocket = localServerSocket;
        this.mServerPort = iNextInt;
        new Thread() { // from class: com.baidu.speech.audio.MicrophoneServer.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                while (true) {
                    try {
                        LocalSocket localSocketAccept = MicrophoneServer.this.mServerSocket.accept();
                        synchronized (MicrophoneServer.this.mRemoteOutputStreams) {
                            MicrophoneServer.this.mRemoteOutputStreams.add(new SocketWrap(localSocketAccept));
                            LogUtil.i(MicrophoneServer.TAG, "add wrap socket, mRemoteOutputStreams size = " + MicrophoneServer.this.mRemoteOutputStreams.size() + " firstStart = " + MicrophoneServer.this.firstStart);
                            if (MicrophoneServer.this.mRemoteOutputStreams.size() == 1 && MicrophoneServer.this.firstStart) {
                                MicrophoneServer.this.firstStart = false;
                                if (MicrophoneServer.this.mIn != null) {
                                    try {
                                        MicrophoneServer.this.mIn.close();
                                        MicrophoneServer.this.mIn = null;
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                    }
                                }
                                MicrophoneServer.this.mIn = new DataInputStream(MicrophoneServer.this.createInputStream(MicrophoneServer.this.mInfile, MicrophoneServer.this.mAudioSource));
                                new Thread(MicrophoneServer.this).start();
                            }
                        }
                    } catch (Exception e3) {
                        LogUtil.d(MicrophoneServer.TAG, " mRemoteOutputStreams.size：" + MicrophoneServer.this.mRemoteOutputStreams.size());
                        MicrophoneServer.this.firstStart = true;
                        synchronized (MicrophoneServer.this.mRemoteOutputStreams) {
                            for (LocalSocket localSocket : MicrophoneServer.this.mRemoteOutputStreams) {
                                try {
                                    localSocket.getOutputStream().close();
                                    localSocket.close();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                }
                            }
                            MicrophoneServer.this.mRemoteOutputStreams.clear();
                            if (MicrophoneServer.this.mIn != null) {
                                try {
                                    MicrophoneServer.this.mIn.close();
                                    MicrophoneServer.this.mIn = null;
                                } catch (Exception e5) {
                                    e5.printStackTrace();
                                }
                            }
                            try {
                                MicrophoneServer.this.mServerSocket.close();
                            } catch (Exception e6) {
                                e6.printStackTrace();
                            }
                            synchronized (MicrophoneServer.sPorts) {
                                MicrophoneServer.sPorts.remove(MicrophoneServer.this.mInfile);
                                e3.printStackTrace();
                                return;
                            }
                        }
                    }
                }
            }
        }.start();
    }

    public static int create(String str, int i) throws IOException {
        int i2;
        synchronized (sPorts) {
            if (sPorts.get(str) == null) {
                try {
                    sPorts.put(str, new MicrophoneServer(str, i));
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return PORT;
                }
            }
            i2 = sPorts.get(str).mServerPort;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public InputStream createInputStream(String str, int i) throws Exception {
        if (str == null || str.equals("")) {
            return new MicInputStream(i, 16000);
        }
        if (str.startsWith("#")) {
            Matcher matcher = Pattern.compile("^#(.*)[#.](.*?)\\(").matcher(str);
            if (!matcher.find()) {
                throw new IOException("can not create inputStream");
            }
            return (InputStream) Class.forName(matcher.group(1)).getMethod(matcher.group(2), new Class[0]).invoke(null, new Object[0]);
        }
        if (str.startsWith("res://")) {
            return MicrophoneServer.class.getResourceAsStream("/" + str.replaceFirst("res://", "").replaceFirst("/", ""));
        }
        if (!str.startsWith("asset://") && !str.startsWith("assets://")) {
            return str.startsWith("tcp://") ? new Socket("localhost", Integer.parseInt(str.replaceFirst("tcp://", "").replaceFirst("/", ""))).getInputStream() : new FileInputStream(str);
        }
        String strReplaceFirst = str.replaceFirst("assets://", "").replaceFirst("/", "");
        if (str.startsWith("asset://")) {
            strReplaceFirst = str.replaceFirst("asset://", "").replaceFirst("/", "");
        }
        return MicrophoneServer.class.getResourceAsStream("/assets/" + strReplaceFirst);
    }

    /* JADX WARN: Removed duplicated region for block: B:130:0x024a A[Catch: all -> 0x024c, TryCatch #10 {, blocks: (B:113:0x01e4, B:115:0x0212, B:116:0x0218, B:118:0x021e, B:119:0x0224, B:122:0x0230, B:123:0x0234, B:124:0x0239, B:126:0x023d, B:129:0x0247, B:130:0x024a), top: B:178:0x01e4, inners: #5, #16 }] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x02ba A[Catch: all -> 0x02bc, TRY_LEAVE, TryCatch #18 {, blocks: (B:139:0x0254, B:141:0x0282, B:142:0x0288, B:144:0x028e, B:145:0x0294, B:148:0x02a0, B:149:0x02a4, B:150:0x02a9, B:152:0x02ad, B:155:0x02b7, B:156:0x02ba), top: B:189:0x0254, inners: #7, #17 }] */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0093 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00f9 A[Catch: all -> 0x00fc, DONT_GENERATE, TryCatch #19 {, blocks: (B:34:0x0093, B:36:0x00c1, B:37:0x00c7, B:39:0x00cd, B:40:0x00d3, B:43:0x00df, B:44:0x00e3, B:45:0x00e8, B:47:0x00ec, B:50:0x00f6, B:51:0x00f9), top: B:191:0x0093, inners: #8, #20 }] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01bd A[Catch: all -> 0x01c0, TryCatch #0 {, blocks: (B:76:0x0157, B:78:0x0185, B:79:0x018b, B:81:0x0191, B:82:0x0197, B:85:0x01a3, B:86:0x01a7, B:87:0x01ac, B:89:0x01b0, B:92:0x01ba, B:93:0x01bd), top: B:161:0x0157, inners: #14, #15 }] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            Method dump skipped, instruction units count: 703
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.speech.audio.MicrophoneServer.run():void");
    }
}
