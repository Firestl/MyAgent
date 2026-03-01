package io.dcloud.common.adapter.util;

import android.os.AsyncTask;
import io.dcloud.common.util.ThreadPool;

/* JADX INFO: loaded from: classes2.dex */
public class AsyncTaskHandler {

    public interface IAsyncTaskListener {
        void onCancel();

        void onExecuteBegin();

        void onExecuteEnd(Object obj);

        Object onExecuting();
    }

    public static class MyAsyncTask extends AsyncTask<String[], Integer, Object> {
        public IAsyncTaskListener mListener;

        public MyAsyncTask(IAsyncTaskListener iAsyncTaskListener) {
            this.mListener = null;
            this.mListener = iAsyncTaskListener;
        }

        @Override // android.os.AsyncTask
        public void onCancelled() {
            super.onCancelled();
        }

        @Override // android.os.AsyncTask
        public void onPostExecute(Object obj) {
            super.onPostExecute(obj);
            this.mListener.onExecuteEnd(obj);
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            super.onPreExecute();
            this.mListener.onExecuteBegin();
        }

        @Override // android.os.AsyncTask
        public Object doInBackground(String[]... strArr) {
            return this.mListener.onExecuting();
        }
    }

    public static void executeAsyncTask(IAsyncTaskListener iAsyncTaskListener, String[] strArr) {
        new MyAsyncTask(iAsyncTaskListener).execute(strArr);
    }

    public static void executeThreadTask(final IAsyncTaskListener iAsyncTaskListener) {
        ThreadPool.self().addThreadTask(new Runnable() { // from class: io.dcloud.common.adapter.util.AsyncTaskHandler.1
            @Override // java.lang.Runnable
            public void run() {
                IAsyncTaskListener iAsyncTaskListener2 = iAsyncTaskListener;
                if (iAsyncTaskListener2 != null) {
                    iAsyncTaskListener2.onExecuteBegin();
                    final Object objOnExecuting = iAsyncTaskListener.onExecuting();
                    MessageHandler.post(new Runnable() { // from class: io.dcloud.common.adapter.util.AsyncTaskHandler.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            iAsyncTaskListener.onExecuteEnd(objOnExecuting);
                        }
                    });
                }
            }
        }, true);
    }
}
