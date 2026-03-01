package io.dcloud.feature.audio.recorder;

import android.media.AudioRecord;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import io.dcloud.feature.audio.aac.AacEncode;
import io.dcloud.feature.audio.mp3.SimpleLame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: classes2.dex */
public class DataEncodeThread extends Thread implements AudioRecord.OnRecordPositionUpdateListener {
    public static final int PROCESS_STOP = 1;
    public byte[] mBuffer;
    public FileOutputStream mFileOutputStream;
    public String mFormat;
    public StopHandler mHandler;
    public CountDownLatch mHandlerInitLatch = new CountDownLatch(1);
    public List<Task> mTasks = Collections.synchronizedList(new ArrayList());

    public static class StopHandler extends Handler {
        public WeakReference<DataEncodeThread> encodeThread;

        public StopHandler(DataEncodeThread dataEncodeThread) {
            this.encodeThread = new WeakReference<>(dataEncodeThread);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 1) {
                DataEncodeThread dataEncodeThread = this.encodeThread.get();
                while (dataEncodeThread.processData() > 0) {
                }
                removeCallbacksAndMessages(null);
                dataEncodeThread.flushAndRelease();
                getLooper().quit();
            }
            super.handleMessage(message);
        }
    }

    public DataEncodeThread(File file, int i, String str) throws FileNotFoundException {
        this.mFileOutputStream = new FileOutputStream(file);
        this.mBuffer = new byte[(int) ((((double) (i * 2)) * 1.25d) + 7200.0d)];
        this.mFormat = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void flushAndRelease() {
        int length;
        if (this.mFormat.equalsIgnoreCase("aac")) {
            try {
                byte[] bArrOfferEncoder = AacEncode.getAacEncode().offerEncoder(this.mBuffer);
                this.mBuffer = bArrOfferEncoder;
                length = bArrOfferEncoder.length;
            } catch (Exception e2) {
                e2.printStackTrace();
                length = 0;
            }
        } else {
            length = SimpleLame.flush(this.mBuffer);
        }
        if (length > 0) {
            try {
                try {
                    this.mFileOutputStream.write(this.mBuffer, 0, length);
                    FileOutputStream fileOutputStream = this.mFileOutputStream;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                            this.mFileOutputStream = null;
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                } catch (Throwable th) {
                    FileOutputStream fileOutputStream2 = this.mFileOutputStream;
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                            this.mFileOutputStream = null;
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e5) {
                e5.printStackTrace();
                FileOutputStream fileOutputStream3 = this.mFileOutputStream;
                if (fileOutputStream3 != null) {
                    try {
                        fileOutputStream3.close();
                        this.mFileOutputStream = null;
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
            }
        }
        if (this.mFormat.equalsIgnoreCase("aac")) {
            AacEncode.getAacEncode().close();
        } else {
            SimpleLame.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int processData() {
        int length;
        if (this.mTasks.size() <= 0) {
            return 0;
        }
        Task taskRemove = this.mTasks.remove(0);
        short[] data = taskRemove.getData();
        taskRemove.getRightData();
        int readSize = taskRemove.getReadSize();
        short[] data2 = (taskRemove.getRightData() == null || taskRemove.getRightData().length <= 0) ? taskRemove.getData() : taskRemove.getRightData();
        if (this.mFormat.equalsIgnoreCase("aac")) {
            try {
                byte[] bArrOfferEncoder = AacEncode.getAacEncode().offerEncoder(taskRemove.getByteData());
                this.mBuffer = bArrOfferEncoder;
                length = bArrOfferEncoder.length;
            } catch (Exception e2) {
                e2.printStackTrace();
                length = 0;
            }
        } else {
            length = SimpleLame.encode(data, data2, readSize, this.mBuffer);
        }
        if (length > 0) {
            try {
                this.mFileOutputStream.write(this.mBuffer, 0, length);
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
        return readSize;
    }

    public void addTask(short[] sArr, int i) {
        this.mTasks.add(new Task(sArr, i));
    }

    public Handler getHandler() {
        try {
            this.mHandlerInitLatch.await();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        return this.mHandler;
    }

    @Override // android.media.AudioRecord.OnRecordPositionUpdateListener
    public void onMarkerReached(AudioRecord audioRecord) {
    }

    @Override // android.media.AudioRecord.OnRecordPositionUpdateListener
    public void onPeriodicNotification(AudioRecord audioRecord) {
        processData();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Looper.prepare();
        this.mHandler = new StopHandler(this);
        this.mHandlerInitLatch.countDown();
        Looper.loop();
    }

    public void addTask(short[] sArr, short[] sArr2, int i) {
        this.mTasks.add(new Task(sArr, sArr2, i));
    }

    public class Task {
        public byte[] byteRawData;
        public short[] rawData;
        public int readSize;
        public short[] rightData;

        public Task(short[] sArr, int i) {
            this.rawData = (short[]) sArr.clone();
            this.readSize = i;
        }

        public byte[] getByteData() {
            return this.byteRawData;
        }

        public short[] getData() {
            return this.rawData;
        }

        public int getReadSize() {
            return this.readSize;
        }

        public short[] getRightData() {
            return this.rightData;
        }

        public Task(byte[] bArr, int i) {
            this.byteRawData = (byte[]) bArr.clone();
            this.readSize = i;
        }

        public Task(short[] sArr, short[] sArr2, int i) {
            this.rawData = (short[]) sArr.clone();
            this.rightData = (short[]) sArr2.clone();
            this.readSize = i;
        }
    }

    public void addTask(byte[] bArr, int i) {
        this.mTasks.add(new Task(bArr, i));
    }
}
