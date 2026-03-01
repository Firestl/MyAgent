package com.baidu.speech;

import android.media.AudioRecord;
import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import com.baidu.speech.audio.MicrophoneServer;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class MicrophoneInputStream extends InputStream {
    public static final String TAG = "MicrophoneInputStream";
    public String SOCKET_ADDRESS;
    public final LocalSocket socket;
    public final InputStream source;

    public MicrophoneInputStream() throws IOException {
        this(1, 16000);
    }

    public MicrophoneInputStream(int i) throws IOException {
        this(1, i);
    }

    public MicrophoneInputStream(int i, int i2) throws IOException {
        this(i, i2, null);
    }

    public MicrophoneInputStream(int i, int i2, InputStream inputStream) throws IOException {
        this(i, i2, inputStream, null);
    }

    public MicrophoneInputStream(int i, int i2, InputStream inputStream, AudioRecord audioRecord) throws IOException {
        this.SOCKET_ADDRESS = "com.baidu.speech";
        final LocalSocket[] localSocketArr = new LocalSocket[1];
        MicrophoneServer.create("", i);
        try {
            this.socket = (LocalSocket) Executors.newSingleThreadExecutor().submit(new Callable<LocalSocket>() { // from class: com.baidu.speech.MicrophoneInputStream.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public LocalSocket call() throws Exception {
                    localSocketArr[0] = new LocalSocket();
                    localSocketArr[0].connect(new LocalSocketAddress(MicrophoneInputStream.this.SOCKET_ADDRESS));
                    localSocketArr[0].getOutputStream().write(0);
                    if (localSocketArr[0].getInputStream().read(new byte[640]) != 6) {
                        return localSocketArr[0];
                    }
                    localSocketArr[0].close();
                    throw new IOException("Recorder open failed");
                }
            }).get(23000L, TimeUnit.MILLISECONDS);
            this.source = localSocketArr[0].getInputStream();
            new Thread() { // from class: com.baidu.speech.MicrophoneInputStream.2
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    while (true) {
                        try {
                            MicrophoneInputStream.this.socket.getInputStream().read(new byte[640]);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            return;
                        }
                    }
                }
            }.start();
        } catch (Exception e2) {
            if (localSocketArr[0] != null) {
                localSocketArr[0].close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            throw new IOException(e2);
        }
    }

    public MicrophoneInputStream(int i, InputStream inputStream) throws IOException {
        this(1, i, inputStream);
    }

    public MicrophoneInputStream(AudioRecord audioRecord) throws IOException {
        this(1, 16000, null, audioRecord);
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        super.close();
        InputStream inputStream = this.source;
        if (inputStream != null) {
            inputStream.close();
        }
        LocalSocket localSocket = this.socket;
        if (localSocket != null) {
            localSocket.close();
        }
    }

    public long mills() {
        return 0L;
    }

    public void mills(long j) {
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        return 0;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.source.read(bArr, i, i2);
    }
}
