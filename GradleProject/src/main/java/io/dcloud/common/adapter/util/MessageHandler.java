package io.dcloud.common.adapter.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: loaded from: classes2.dex */
public class MessageHandler {
    public static Handler myHandler = new Handler(Looper.getMainLooper()) { // from class: io.dcloud.common.adapter.util.MessageHandler.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                Object[] objArr = (Object[]) message.obj;
                if (objArr[0] instanceof IMessages) {
                    ((IMessages) objArr[0]).execute(objArr[1]);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                Logger.e("MessageHandler.handleMessage e=" + e2);
            }
        }
    };

    public interface IMessages {
        void execute(Object obj);
    }

    public interface UncheckedCallable {
        void run(WaitableRunnable waitableRunnable);
    }

    public static abstract class WaitableRunnable implements Runnable {
        public Exception mException;
        public String mTimeOutKey = "evalJSSync_time_out";
        public Object mValue;

        private void join() {
            synchronized (this) {
                try {
                    wait(500L);
                    if (this.mValue == null) {
                        this.mValue = this.mTimeOutKey;
                    }
                } catch (InterruptedException unused) {
                }
            }
        }

        public void callBack(Object obj) {
            this.mValue = obj;
            synchronized (this) {
                notifyAll();
            }
        }

        public Object invoke(Handler handler) {
            if (!handler.post(this)) {
                throw new RuntimeException("Handler.post() returned false");
            }
            join();
            if (this.mException == null) {
                return this.mValue;
            }
            throw new RuntimeException(this.mException);
        }

        public abstract void onRun(WaitableRunnable waitableRunnable);

        @Override // java.lang.Runnable
        public final void run() {
            try {
                onRun(this);
                this.mException = null;
            } catch (Exception e2) {
                this.mValue = null;
                this.mException = e2;
            }
        }
    }

    public static void post(Runnable runnable) {
        myHandler.post(runnable);
    }

    public static Object postAndWait(final UncheckedCallable uncheckedCallable) {
        return new WaitableRunnable() { // from class: io.dcloud.common.adapter.util.MessageHandler.2
            @Override // io.dcloud.common.adapter.util.MessageHandler.WaitableRunnable
            public void onRun(WaitableRunnable waitableRunnable) {
                uncheckedCallable.run(waitableRunnable);
            }
        }.invoke(myHandler);
    }

    public static void postDelayed(Runnable runnable, long j) {
        myHandler.postDelayed(runnable, j);
    }

    public static void removeCallbacks(Runnable runnable) {
        myHandler.removeCallbacks(runnable);
    }

    public static void removeCallbacksAndMessages() {
    }

    public static void sendMessage(IMessages iMessages, Object obj) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 0;
        messageObtain.obj = new Object[]{iMessages, obj};
        myHandler.sendMessage(messageObtain);
    }

    public static void sendMessage(IMessages iMessages, long j, Object obj) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 0;
        messageObtain.obj = new Object[]{iMessages, obj};
        myHandler.sendMessageDelayed(messageObtain, j);
    }
}
