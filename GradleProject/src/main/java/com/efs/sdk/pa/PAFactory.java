package com.efs.sdk.pa;

import android.content.Context;
import android.util.Log;
import com.efs.sdk.base.EfsReporter;
import com.efs.sdk.pa.config.ConfigManager;
import com.efs.sdk.pa.config.IEfsReporter;
import com.efs.sdk.pa.config.PackageLevel;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class PAFactory {
    public static final long DEFAULT_TIME_OUT_TIME = 2000;
    public static final long INVALID_TIME_OUT_TIME = 0;
    public static final long MAX_TIME_OUT_TIME = 4000;
    public static final String TAG = "pafactory";
    public static final ThreadLocal<PA> sThreadLocal = new ThreadLocal<>();
    public ConfigManager mConfigManager;
    public Context mContext;
    public HashMap<String, String> mExtend;
    public IPaClient mPaClient;
    public EfsReporter mReporter;
    public IEfsReporter mReporterFactory;
    public String mSerial;
    public String mSver;
    public long mTimeOutTime;
    public PATraceListener mTraceListener;

    public static class Builder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public PackageLevel f1913a;
        public IEfsReporter b;
        public boolean c;
        public Context d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f1914e;
        public HashMap<String, String> f;
        public String g;
        public long h = 2000;
        public PATraceListener i;
        public IPaClient j;

        public Builder(Context context, IEfsReporter iEfsReporter) {
            if (context == null) {
                throw new RuntimeException("context Should Not null");
            }
            if (iEfsReporter == null) {
                throw new RuntimeException("reporter Should Not Empty");
            }
            this.b = iEfsReporter;
            this.d = context;
        }

        public PAFactory build() {
            if (this.f1913a != null) {
                return new PAFactory(this.d, this.f1913a, this.b, this.c, this.f1914e, this.f, this.g, this.h, this.i, this.j);
            }
            throw new RuntimeException(String.format("%s Should Not Null", ""));
        }

        public Builder extendLogInfo(HashMap<String, String> map) {
            this.f = map;
            return this;
        }

        public Builder isNewInstall(boolean z) {
            this.c = z;
            return this;
        }

        public Builder packageLevel(PackageLevel packageLevel) {
            this.f1913a = packageLevel;
            return this;
        }

        public Builder serial(String str) {
            this.f1914e = str;
            return this;
        }

        public Builder setPaClient(IPaClient iPaClient) {
            this.j = iPaClient;
            return this;
        }

        public Builder sver(String str) {
            this.g = str;
            return this;
        }

        public Builder timeoutTime(long j) {
            if (j <= 0) {
                Log.w(PAFactory.TAG, "Timeout time is invalid, and the default value 2s will be used");
                this.h = 2000L;
            } else {
                if (j > 4000) {
                    Log.w(PAFactory.TAG, "Timeout time over 4s is not recommended, and the default value 2s will be used");
                    this.h = 2000L;
                    return this;
                }
                this.h = j;
            }
            return this;
        }

        public Builder traceListener(PATraceListener pATraceListener) {
            this.i = pATraceListener;
            return this;
        }
    }

    public ConfigManager getConfigManager() {
        return this.mConfigManager;
    }

    public Context getContext() {
        return this.mContext;
    }

    public HashMap<String, String> getExtend() {
        return this.mExtend;
    }

    public IPaClient getPaClient() {
        return this.mPaClient;
    }

    public synchronized PA getPaInstance() {
        PA cVar;
        cVar = sThreadLocal.get();
        if (cVar == null) {
            cVar = new com.efs.sdk.pa.a.c(this.mConfigManager.enableTracer());
            cVar.registerPAANRListener(this.mContext, new a(this), this.mTimeOutTime);
            sThreadLocal.set(cVar);
        }
        return cVar;
    }

    public EfsReporter getReporter() {
        if (this.mReporter == null) {
            IEfsReporter iEfsReporter = this.mReporterFactory;
            this.mReporter = iEfsReporter != null ? iEfsReporter.getReporter() : null;
        }
        return this.mReporter;
    }

    public String getSerial() {
        return this.mSerial;
    }

    public String getSver() {
        return this.mSver;
    }

    public PATraceListener getTraceListener() {
        return this.mTraceListener;
    }

    public PAFactory(Context context, PackageLevel packageLevel, IEfsReporter iEfsReporter, boolean z, String str, HashMap<String, String> map, String str2, long j, PATraceListener pATraceListener, IPaClient iPaClient) {
        this.mReporterFactory = iEfsReporter;
        this.mSerial = str;
        this.mExtend = map;
        this.mSver = str2;
        this.mContext = context;
        this.mTraceListener = pATraceListener;
        this.mPaClient = iPaClient;
        this.mTimeOutTime = j;
        this.mConfigManager = new ConfigManager(context, packageLevel, iEfsReporter, z);
    }
}
