package com.umeng.analytics.vshelper;

/* JADX INFO: loaded from: classes2.dex */
public class PageNameMonitor implements com.umeng.analytics.vshelper.a {
    public static String currentActivity;
    public static String currentCustomPage;
    public static Object lock = new Object();

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final PageNameMonitor f5339a = new PageNameMonitor();
    }

    public static PageNameMonitor getInstance() {
        return a.f5339a;
    }

    @Override // com.umeng.analytics.vshelper.a
    public void activityPause(String str) {
        synchronized (lock) {
            currentActivity = null;
        }
    }

    @Override // com.umeng.analytics.vshelper.a
    public void activityResume(String str) {
        synchronized (lock) {
            currentActivity = str;
        }
    }

    @Override // com.umeng.analytics.vshelper.a
    public void customPageBegin(String str) {
        synchronized (lock) {
            currentCustomPage = str;
        }
    }

    @Override // com.umeng.analytics.vshelper.a
    public void customPageEnd(String str) {
        synchronized (lock) {
            currentCustomPage = null;
        }
    }

    public String getCurrenPageName() {
        synchronized (lock) {
            if (currentCustomPage != null) {
                return currentCustomPage;
            }
            if (currentActivity == null) {
                return null;
            }
            return currentActivity;
        }
    }

    public String getCurrentActivityName() {
        String str;
        synchronized (lock) {
            str = currentActivity;
        }
        return str;
    }

    public PageNameMonitor() {
    }
}
