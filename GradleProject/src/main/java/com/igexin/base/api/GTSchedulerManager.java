package com.igexin.base.api;

import com.igexin.base.scheduler.BaseTask;
import com.igexin.base.scheduler.a;
import com.igexin.base.scheduler.c;

/* JADX INFO: loaded from: classes2.dex */
public class GTSchedulerManager implements c {
    public static GTSchedulerManager mInstance;
    public c mBase = new a();

    public enum TASKLEVEL {
        LEVEL_MIN(1),
        LEVEL_LOW(2),
        LEVEL_DEFAULT(3),
        LEVEL_HIGH(4),
        LEVEL_MAX(5);

        public int val;

        TASKLEVEL(int i) {
            this.val = i;
        }
    }

    private void checkTask(BaseTask baseTask) {
        if (baseTask == null) {
            throw null;
        }
    }

    public static GTSchedulerManager getInstance() {
        if (mInstance == null) {
            synchronized (GTSchedulerManager.class) {
                if (mInstance == null) {
                    mInstance = new GTSchedulerManager();
                }
            }
        }
        return mInstance;
    }

    @Override // com.igexin.base.scheduler.c
    public void execute(BaseTask baseTask) {
        checkTask(baseTask);
        this.mBase.execute(baseTask);
    }

    @Override // com.igexin.base.scheduler.c
    public void submit(BaseTask baseTask) {
        checkTask(baseTask);
        this.mBase.submit(baseTask);
    }
}
