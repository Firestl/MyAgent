package io.dcloud.f.a;

import android.text.TextUtils;
import android.util.Log;
import com.lzy.okgo.model.Progress;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.common.adapter.util.MessageHandler;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.ThreadPool;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AbsMgr f6476a;
    public ConcurrentHashMap<String, c> b = new ConcurrentHashMap<>();
    public ArrayList<c> c = new ArrayList<>();

    public class a implements MessageHandler.IMessages {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ c f6477a;

        /* JADX INFO: renamed from: io.dcloud.f.a.d$a$a, reason: collision with other inner class name */
        public class C0161a implements ICallBack {
            public C0161a() {
            }

            @Override // io.dcloud.common.DHInterface.ICallBack
            public Object onCallBack(int i, Object obj) {
                if (i == -1) {
                    Log.i("console", "nativeApp pull fail");
                } else if (i == 1) {
                    Log.i("console", "nativeApp pull success");
                }
                d.this.b.clear();
                d.this.a();
                return null;
            }
        }

        public class b implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ String f6479a;

            public b(String str) {
                this.f6479a = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (DHFile.delete(this.f6479a)) {
                    Log.i("console", "rm file success");
                } else {
                    Log.i("console", "rm file fail");
                }
                d.this.b.clear();
                d.this.a();
            }
        }

        public a(c cVar) {
            this.f6477a = cVar;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:21:0x003f  */
        @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void execute(java.lang.Object r6) {
            /*
                Method dump skipped, instruction units count: 352
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: io.dcloud.f.a.d.a.execute(java.lang.Object):void");
        }
    }

    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f6480a;
        public final /* synthetic */ String b;
        public final /* synthetic */ File c;
        public final /* synthetic */ ICallBack d;

        public b(d dVar, String str, String str2, File file, ICallBack iCallBack) {
            this.f6480a = str;
            this.b = str2;
            this.c = file;
            this.d = iCallBack;
        }

        @Override // java.lang.Runnable
        public void run() {
            int iCopyFile = DHFile.copyFile(this.f6480a, this.b);
            DHFile.delete(this.c.getParent());
            if (iCopyFile == 1) {
                ICallBack iCallBack = this.d;
                if (iCallBack != null) {
                    iCallBack.onCallBack(1, null);
                    return;
                }
                return;
            }
            ICallBack iCallBack2 = this.d;
            if (iCallBack2 != null) {
                iCallBack2.onCallBack(-1, null);
            }
        }
    }

    public class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f6481a;
        public String b;

        public c(d dVar) {
        }
    }

    public d(AbsMgr absMgr) {
        this.f6476a = null;
        this.f6476a = absMgr;
    }

    private ArrayList<c> b(String str) {
        ArrayList<c> arrayList = new ArrayList<>(1);
        if (str.startsWith(AbsoluteConst.SOCKET_NATIVE_COMMAND)) {
            str = str.substring(4);
        }
        String strTrim = str.trim();
        int length = strTrim.length();
        ArrayList arrayList2 = new ArrayList();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            char cCharAt = strTrim.charAt(i2);
            i2++;
            if (i2 == length || ((b(cCharAt) && arrayList2.size() % 2 == 0) || a(cCharAt))) {
                String strTrim2 = strTrim.substring(i3, i2).trim();
                if (!"".equals(strTrim2)) {
                    arrayList2.add(strTrim2);
                    i3 = i2;
                }
            }
        }
        int size = arrayList2.size();
        while (i < size) {
            c cVar = new c(this);
            cVar.f6481a = (String) arrayList2.get(i);
            cVar.b = (String) arrayList2.get(i + 1);
            i += 2;
            arrayList.add(cVar);
        }
        return arrayList;
    }

    public synchronized void a(String str) {
        ArrayList<c> arrayListB = b(str);
        if (arrayListB != null && !arrayListB.isEmpty()) {
            this.c.addAll(arrayListB);
        }
        a();
    }

    public boolean a(char c2) {
        return c2 == '\r' || c2 == '\n';
    }

    public boolean b(char c2) {
        return c2 == '\t' || c2 == 11 || c2 == '\f' || c2 == ' ' || c2 == 160 || c2 == 12288;
    }

    public synchronized void a() {
        ArrayList<c> arrayList;
        c cVarRemove;
        if (this.b.isEmpty() && (arrayList = this.c) != null && !arrayList.isEmpty() && (cVarRemove = this.c.remove(0)) != null) {
            this.b.put("runing", cVarRemove);
            a(cVarRemove);
        }
    }

    public void a(c cVar) {
        MessageHandler.sendMessage(new a(cVar), null);
    }

    public synchronized void a(String str, ICallBack iCallBack) {
        JSONObject jSONObject;
        if (!PdrUtil.isEmpty(str)) {
            try {
                jSONObject = new JSONObject(str);
            } catch (JSONException e2) {
                e2.printStackTrace();
                jSONObject = null;
            }
            if (jSONObject == null) {
                Log.i("console", "nativeApp pull fail");
                if (iCallBack != null) {
                    iCallBack.onCallBack(-1, null);
                }
                return;
            }
            String strOptString = jSONObject.optString("appid");
            String strOptString2 = jSONObject.optString(Progress.FILE_PATH);
            if (!TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString2)) {
                String str2 = BaseInfo.sBaseFsAppsPath + strOptString + "/www";
                File file = new File(strOptString2);
                if (file.exists()) {
                    ThreadPool.self().addSingleThreadTask(new b(this, strOptString2, str2, file, iCallBack));
                } else {
                    Log.i("console", "nativeApp pull fail");
                    if (iCallBack != null) {
                        iCallBack.onCallBack(-1, null);
                    }
                }
            }
            Log.i("console", "nativeApp pull fail");
            if (iCallBack != null) {
                iCallBack.onCallBack(-1, null);
            }
        }
    }
}
