package io.dcloud.common.adapter.util;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.ThreadPool;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class Logger {
    public static final String ANIMATION_TAG = "Animation_Path";
    public static final String AUTO_POP_PUSH_TAG = "Auto_Pop_Push_Path";
    public static final String Android_System_TAG = "Android_System_Path";
    public static final String AppMgr_TAG = "appmgr";
    public static final String AutoGC_TAG = "AutoGC_Path";
    public static final String Capture_TAG = "Capture_Tag";
    public static String D = "D";
    public static String E = "E";
    public static final String EXCEPTION_TAG = "DCloud_Exception";
    public static final String Event_TAG = "Event_Tag";
    public static String I = "I";
    public static final String LAYOUT_TAG = "Layout_Path";
    public static final String LOGTAG = "DCloud_LOG";
    public static String LogPath = "";
    public static final String MAIN_TAG = "Main_Path";
    public static final String MAP_TAG = "Map_Path";
    public static int MAX_CRASH_FILE_COUNT = 3;
    public static final String MSC_TAG = "DCloud_";
    public static final String STREAMSDKLOGTAG = "DCLOUD_STREAMSDK_LOG";
    public static final String StreamApp_TAG = "stream_manager";
    public static long TIMES = 432000000;
    public static final String TIMESTAMP_HH_MM_SS_SSS = "HH:mm:ss.SSS";
    public static final String TIMESTAMP_YYYY_MM_DD = "yyyyMMdd";
    public static final String TIMESTAMP_YYYY_MM_DD_HH_MM_SS_SSS = "yyyyMMdd HH:mm:ss.SSS";
    public static final String VIEW_VISIBLE_TAG = "View_Visible_Path";
    public static String W = "W";
    public static boolean isHasDevFile = false;
    public static boolean isOpen = true;
    public static boolean isStoreLog = false;
    public static File mLogFile = null;
    public static HashMap<String, SimpleDateFormat> mSimpleDateFormatCache = null;
    public static HandlerThread mWriteLogToSdCardThread = null;
    public static WriteLogToSdCardThreadHandler mWriteLogToSdCardThreadHandler = null;
    public static String pkg = "";

    public static class WriteLogToSdCardThreadHandler extends Handler {
        public WriteLogToSdCardThreadHandler(Looper looper) {
            super(looper);
        }

        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x005d -> B:40:0x006c). Please report as a decompilation issue!!! */
        @Override // android.os.Handler
        public void handleMessage(Message message) throws Throwable {
            super.handleMessage(message);
            Object obj = message.obj;
            if (obj == null || !(obj instanceof String[])) {
                return;
            }
            String[] strArr = (String[]) obj;
            if (strArr.length < 3) {
                return;
            }
            String strGenerateLog = Logger.generateLog(strArr[0], strArr[1], strArr[2]);
            if (Logger.mLogFile == null || !Logger.mLogFile.exists() || strGenerateLog == null) {
                return;
            }
            FileOutputStream fileOutputStream = null;
            try {
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            try {
                try {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(Logger.mLogFile, true);
                    try {
                        fileOutputStream2.write(strGenerateLog.getBytes());
                        fileOutputStream2.flush();
                        fileOutputStream2.close();
                    } catch (Exception e3) {
                        e = e3;
                        fileOutputStream = fileOutputStream2;
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Exception e5) {
                    e = e5;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    public static void WriteExceptionToSDcard(String str, String str2, String str3, Throwable th) {
        if (th != null) {
            WriteLogToSDcard(str, str2, generateLog(str, EXCEPTION_TAG, th.getClass().getName()));
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace != null) {
                WriteLogToSDcard(str, str2, str3);
                for (StackTraceElement stackTraceElement : stackTrace) {
                    WriteLogToSDcard(str, str2, generateLog(str, EXCEPTION_TAG, "    at " + stackTraceElement.toString()));
                }
            }
        }
    }

    public static void WriteLogToSDcard(String str, String str2, String str3) {
        File file;
        if (isOpen && (file = mLogFile) != null && file.exists()) {
            initWriteLogToSdCardThread();
            Message messageObtain = Message.obtain();
            messageObtain.obj = new String[]{str, str2, str3};
            if (PdrUtil.isEmpty(mWriteLogToSdCardThreadHandler)) {
                return;
            }
            mWriteLogToSdCardThreadHandler.sendMessage(messageObtain);
        }
    }

    public static boolean canStoreLogToSDcard() {
        if (isSDcardExists() && isOpen) {
            File file = new File(LogPath);
            if (file.exists()) {
                deleteOldLog(file);
            }
            if (file.exists() && file.canWrite()) {
                isStoreLog = true;
            }
        }
        return isStoreLog;
    }

    public static String concatString(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(str.length() + str2.length());
        stringBuffer.append(str);
        stringBuffer.append(str2);
        return stringBuffer.toString();
    }

    public static void d(String str, String str2) {
        if (isOpen) {
            if (BaseInfo.ISDEBUG) {
                Log.d(str, str2);
            } else {
                Log.i(str, str2);
            }
            WriteLogToSDcard(D, str, str2);
        }
    }

    public static void deleteOldLog(File file) {
        File[] fileArrListFiles = file.listFiles();
        Date date = new Date();
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                if (!file2.isDirectory()) {
                    try {
                        if (date.getTime() - new SimpleDateFormat(TIMESTAMP_YYYY_MM_DD).parse(file2.getName().substring(0, 8)).getTime() > TIMES) {
                            file2.delete();
                        }
                    } catch (ParseException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
    }

    public static void e(String str, String str2) {
        if (isOpen) {
            Log.e(str, str2);
            WriteLogToSDcard(E, str, str2);
        }
    }

    public static void es(String str, String str2) {
        if (isOpen) {
            Log.e(str, str2);
            WriteLogToSDcard(E, str, str2);
        }
    }

    public static String generateLog(String str, String str2, String str3) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(generateTimeStamp(Boolean.TRUE));
        stringBuffer.append(Operators.SPACE_STR);
        stringBuffer.append(str);
        stringBuffer.append(Operators.SPACE_STR);
        stringBuffer.append("-");
        stringBuffer.append(Operators.SPACE_STR);
        stringBuffer.append(str2);
        stringBuffer.append(Operators.SPACE_STR);
        stringBuffer.append(str3);
        stringBuffer.append("\n");
        return stringBuffer.toString();
    }

    public static String generateTimeStamp(Boolean bool) {
        return generateTimeStamp(bool.booleanValue() ? TIMESTAMP_HH_MM_SS_SSS : TIMESTAMP_YYYY_MM_DD, new Date());
    }

    public static SimpleDateFormat getDateFormatFromCache(String str) {
        if (mSimpleDateFormatCache == null) {
            mSimpleDateFormatCache = new HashMap<>();
        }
        if (mSimpleDateFormatCache.containsKey(str)) {
            return mSimpleDateFormatCache.get(str);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, Locale.ENGLISH);
        mSimpleDateFormatCache.put(str, simpleDateFormat);
        return simpleDateFormat;
    }

    public static void i(String str, String str2) {
        if (!isOpen || str2 == null) {
            return;
        }
        Log.i(str, str2);
        WriteLogToSDcard(I, str, str2);
    }

    public static void init(final String str, final String str2) {
        try {
            ThreadPool.self().addThreadTask(new Runnable() { // from class: io.dcloud.common.adapter.util.Logger.1
                @Override // java.lang.Runnable
                public void run() {
                    File[] fileArrListFiles = new File(str).listFiles();
                    if (fileArrListFiles == null || fileArrListFiles.length <= Logger.MAX_CRASH_FILE_COUNT) {
                        return;
                    }
                    if (fileArrListFiles[0].getName().compareTo(fileArrListFiles[1].getName()) < 0) {
                        for (int i = 0; i < fileArrListFiles.length - Logger.MAX_CRASH_FILE_COUNT; i++) {
                            if (!TextUtils.equals(str2, fileArrListFiles[i].getName())) {
                                fileArrListFiles[i].delete();
                            }
                        }
                        return;
                    }
                    for (int length = fileArrListFiles.length - 1; length >= Logger.MAX_CRASH_FILE_COUNT; length--) {
                        if (!TextUtils.equals(str2, fileArrListFiles[length].getName())) {
                            fileArrListFiles[length].delete();
                        }
                    }
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void initLogger(Context context) {
        pkg = context.getPackageName();
        isHasDevFile = DHFile.hasFile();
        boolean z = BaseInfo.isBase(context) || isHasDevFile;
        isOpen = z;
        isOpen = z | BaseInfo.ISDEBUG;
        if ("mounted".equalsIgnoreCase(Environment.getExternalStorageState())) {
            LogPath = BaseInfo.getCrashLogsPath(context);
        }
        if (isOpen) {
            init(LogPath, IApp.ConfigProperty.CONFIG_CRASH);
            init(LogPath + UEH.CRASH_DIRECTORY, null);
        }
        setOpen(isOpen);
    }

    public static void initWriteLogToSdCardThread() {
        if (mWriteLogToSdCardThread == null) {
            HandlerThread handlerThread = new HandlerThread("WriteLogToSdCardThread");
            mWriteLogToSdCardThread = handlerThread;
            handlerThread.start();
            mWriteLogToSdCardThreadHandler = new WriteLogToSdCardThreadHandler(mWriteLogToSdCardThread.getLooper());
        }
    }

    public static boolean isOpen() {
        return isOpen;
    }

    public static boolean isSDcardExists() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static boolean isTurnOn() {
        return isStoreLog;
    }

    public static void p(String str, String str2) {
        privacyLog(str, str2);
    }

    public static void privacyLog(String str, String str2) {
        if (isHasDevFile) {
            Log.e(str, str2);
        }
    }

    public static void setOpen(boolean z) {
        isOpen = z;
        if (z) {
            canStoreLogToSDcard();
            storeLogToSDcard();
        }
    }

    public static void stopWriteLogToSdCardThread() {
        if (mWriteLogToSdCardThread != null) {
            WriteLogToSdCardThreadHandler writeLogToSdCardThreadHandler = mWriteLogToSdCardThreadHandler;
            if (writeLogToSdCardThreadHandler != null) {
                writeLogToSdCardThreadHandler.removeCallbacksAndMessages(null);
            }
            mWriteLogToSdCardThreadHandler = null;
            if (Build.VERSION.SDK_INT >= 18) {
                mWriteLogToSdCardThread.quitSafely();
            } else {
                mWriteLogToSdCardThread.quit();
            }
            mWriteLogToSdCardThread = null;
        }
    }

    public static void storeLogToSDcard() {
        if (isStoreLog) {
            StringBuffer stringBuffer = new StringBuffer();
            String str = generateTimeStamp(Boolean.FALSE) + ".log";
            stringBuffer.append(LogPath);
            stringBuffer.append(str);
            File file = new File(stringBuffer.toString());
            mLogFile = file;
            if (!file.exists()) {
                try {
                    mLogFile.createNewFile();
                } catch (IOException e2) {
                    mLogFile = null;
                    e2.printStackTrace();
                }
            }
            WriteLogToSDcard("日志路径:", "Logger", "path=" + stringBuffer.toString());
        }
    }

    public static void turnOff() {
        isStoreLog = false;
    }

    public static void w(String str, Throwable th) {
        if (isOpen) {
            if (th != null) {
                th.printStackTrace();
            }
            Log.w(EXCEPTION_TAG, str, th);
            WriteExceptionToSDcard(W, EXCEPTION_TAG, str, th);
        }
    }

    public static void p(String str) {
        privacyLog(LOGTAG, str);
    }

    public static String generateTimeStamp(String str, Date date) {
        SimpleDateFormat dateFormatFromCache = getDateFormatFromCache(str);
        dateFormatFromCache.applyPattern(str);
        return dateFormatFromCache.format(date);
    }

    public static void e(String str) {
        e(LOGTAG, str);
    }

    public static void es(String str) {
        if (isOpen) {
            es(STREAMSDKLOGTAG, str);
        }
    }

    public static void i(String str) {
        i(LOGTAG, str);
    }

    public static void w(Throwable th) {
        w("", th);
    }

    public static void d(String str, Object... objArr) {
        if (isOpen) {
            StringBuffer stringBuffer = new StringBuffer();
            if (objArr != null) {
                for (Object obj : objArr) {
                    stringBuffer.append(obj);
                    stringBuffer.append(";");
                }
            }
            if (!BaseInfo.ISDEBUG) {
                Log.i(str, stringBuffer.toString());
            } else {
                Log.d(str, stringBuffer.toString());
            }
            WriteLogToSDcard(D, str, stringBuffer.toString());
        }
    }

    public static void d(String str) {
        d(LOGTAG, str);
    }
}
