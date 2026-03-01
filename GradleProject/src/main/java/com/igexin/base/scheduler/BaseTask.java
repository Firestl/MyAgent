package com.igexin.base.scheduler;

import com.igexin.base.api.GTSchedulerManager;
import com.igexin.base.scheduler.b;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseTask implements Runnable {
    public static final String TAG = "BaseTask";
    public String groupName;
    public long initDelay;
    public volatile int isRunVal;
    public b.a mParent;
    public long period;
    public AtomicBoolean isCanceled = new AtomicBoolean();
    public GTSchedulerManager.TASKLEVEL taskLevel = GTSchedulerManager.TASKLEVEL.LEVEL_DEFAULT;

    public BaseTask(long j, long j2, TimeUnit timeUnit, boolean z) {
        setDelayImpl(j, j2, timeUnit, z);
    }

    public BaseTask(long j, TimeUnit timeUnit) {
        setDelayImpl(j, 0L, timeUnit, true);
    }

    private void setDelayImpl(long j, long j2, TimeUnit timeUnit, boolean z) {
        this.initDelay = TimeUnit.MILLISECONDS.convert(j, timeUnit);
        this.period = ((long) (z ? 1 : -1)) * TimeUnit.MILLISECONDS.convert(j2, timeUnit);
    }

    public void bind(b.a aVar) {
        if (aVar == null) {
            return;
        }
        this.mParent = aVar;
    }

    public boolean cancel() {
        b.a aVar = this.mParent;
        if (aVar != null) {
            return aVar.cancel(false);
        }
        this.isCanceled.set(true);
        return true;
    }

    public void done() {
    }

    public String getGroupName() {
        return this.groupName;
    }

    public long getInitDelay() {
        return this.initDelay;
    }

    public long getPeriod() {
        return this.period;
    }

    public int getTaskLevel() {
        return this.taskLevel.val;
    }

    public boolean interrupt() {
        b.a aVar = this.mParent;
        if (aVar != null) {
            return aVar.cancel(true);
        }
        this.isCanceled.set(true);
        return true;
    }

    public boolean isPeriodic() {
        return this.period != 0;
    }

    public boolean isRunning() {
        return this.isRunVal != 0;
    }

    public void onCancel() {
    }

    public void onException(Throwable th) {
    }

    public abstract void onRunTask();

    @Override // java.lang.Runnable
    public final void run() {
        if (this.isCanceled.get()) {
            return;
        }
        setIsRunning(true);
        onRunTask();
    }

    public void setDelay(long j, long j2, TimeUnit timeUnit, boolean z) {
        setDelayImpl(j, j2, timeUnit, z);
    }

    public void setDelay(long j, TimeUnit timeUnit) {
        setDelayImpl(j, 0L, timeUnit, true);
    }

    public void setGroupName(String str) {
        this.groupName = str;
    }

    public void setIsRunning(boolean z) {
        this.isRunVal = z ? 1 : 0;
    }

    public void setTaskLevel(GTSchedulerManager.TASKLEVEL tasklevel) {
        this.taskLevel = tasklevel;
    }
}
