package io.dcloud.common.util.net;

import io.dcloud.common.util.ThreadPool;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes2.dex */
public class NetWorkLoop {
    public Thread mSyncThread;
    public final int MAX_EXE_REQUESTDATA = 5;
    public LoopComparator mComparator = new LoopComparator();
    public LinkedList<NetWork> mQuestTask = new LinkedList<>();
    public LinkedList<NetWork> mExeTask = new LinkedList<>();

    public class LoopComparator implements Comparator<NetWork> {
        public LoopComparator() {
        }

        @Override // java.util.Comparator
        public int compare(NetWork netWork, NetWork netWork2) {
            return netWork.mPriority - netWork2.mPriority;
        }
    }

    public synchronized void addNetWork(NetWork netWork) {
        this.mQuestTask.add(netWork);
        Collections.sort(this.mQuestTask, this.mComparator);
    }

    public void dispose() {
        this.mSyncThread = null;
        try {
            LinkedList<NetWork> linkedList = this.mExeTask;
            if (linkedList != null && linkedList.size() > 0) {
                Iterator<NetWork> it = this.mExeTask.iterator();
                while (it.hasNext()) {
                    it.next().dispose();
                }
                this.mExeTask.clear();
                this.mExeTask = null;
            }
            LinkedList<NetWork> linkedList2 = this.mQuestTask;
            if (linkedList2 == null || linkedList2.size() <= 0) {
                return;
            }
            Iterator<NetWork> it2 = this.mQuestTask.iterator();
            while (it2.hasNext()) {
                it2.next().dispose();
            }
            this.mQuestTask.clear();
            this.mQuestTask = null;
        } catch (ConcurrentModificationException e2) {
            e2.printStackTrace();
        }
    }

    public void execSyncTask(NetWork netWork) {
        netWork.mNetWorkLoop = this;
        netWork.startWork();
    }

    public synchronized void removeNetWork(final NetWork netWork) {
        try {
            if (this.mQuestTask.contains(netWork)) {
                this.mQuestTask.remove(netWork);
            }
            if (this.mExeTask.contains(netWork)) {
                this.mExeTask.remove(netWork);
            }
            ThreadPool.self().addThreadTask(new Runnable() { // from class: io.dcloud.common.util.net.NetWorkLoop.2
                @Override // java.lang.Runnable
                public void run() {
                    netWork.cancelWork();
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void startThreadPool() {
        Thread thread = new Thread() { // from class: io.dcloud.common.util.net.NetWorkLoop.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                while (true) {
                    try {
                        NetWorkLoop netWorkLoop = NetWorkLoop.this;
                        if (netWorkLoop.mSyncThread == null) {
                            return;
                        }
                        if (netWorkLoop.mQuestTask.isEmpty()) {
                            Thread.sleep(100L);
                        } else {
                            synchronized (NetWorkLoop.this.mExeTask) {
                                if (NetWorkLoop.this.mExeTask.size() < 5) {
                                    synchronized (NetWorkLoop.this.mQuestTask) {
                                        NetWork netWork = NetWorkLoop.this.mQuestTask.get(0);
                                        NetWorkLoop.this.mExeTask.add(netWork);
                                        NetWorkLoop.this.mQuestTask.remove(netWork);
                                        NetWorkLoop.this.execSyncTask(netWork);
                                    }
                                }
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        };
        this.mSyncThread = thread;
        thread.start();
    }
}
