package com.igexin.base.scheduler.task;

import com.igexin.base.api.GTSchedulerManager;
import com.igexin.base.scheduler.BaseTask;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public abstract class SimpleTask extends BaseTask {
    public SimpleTask() {
        super(0L, TimeUnit.MILLISECONDS);
        setTaskLevel(GTSchedulerManager.TASKLEVEL.LEVEL_DEFAULT);
    }
}
