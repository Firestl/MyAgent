package supwisdom;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.webkit.JavascriptInterface;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: compiled from: DownloadBlobFileJSInterface.java */
/* JADX INFO: loaded from: classes2.dex */
public class tn1 {
    public static String c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f9305a;
    public b b;

    /* JADX INFO: compiled from: DownloadBlobFileJSInterface.java */
    public class a extends SimpleTarget<Drawable> {
        public a() {
        }

        @Override // com.bumptech.glide.request.target.Target
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
            tn1 tn1Var = tn1.this;
            tn1Var.a(tn1Var.f9305a, ((BitmapDrawable) drawable).getBitmap());
        }
    }

    /* JADX INFO: compiled from: DownloadBlobFileJSInterface.java */
    public interface b {
        void a();

        void a(String str);
    }

    public tn1(Context context) {
        this.f9305a = context;
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith("data:image/png;base64,") || str.startsWith("data:image/*;base64,") || str.startsWith("data:image/jpg;base64,");
    }

    @JavascriptInterface
    public void errorForm(String str) {
        b bVar = this.b;
        if (bVar != null) {
            bVar.a();
        }
    }

    @JavascriptInterface
    public void getBase64FromBlobData(String str) {
        a(str);
    }

    public void a(b bVar) {
        this.b = bVar;
    }

    public static String a(String str, String str2, String str3, String str4) {
        c = str2;
        if (!str.startsWith("blob")) {
            return "javascript: console.log('It is not a Blob URL');";
        }
        return "javascript: var xhr = new XMLHttpRequest();xhr.open('GET', '" + str + "', true);xhr.setRequestHeader('Content-type','" + str4 + "');xhr.responseType = 'blob';xhr.onload = function(e) {    if (this.status == 200) {        var blobFile = this.response;        var reader = new FileReader();        reader.readAsDataURL(blobFile);        reader.onloadend = function() {            base64data = reader.result;            Android.getBase64FromBlobData(base64data);        }    }else{       Android.errorForm(base64data);}};Android.errorForm(base64data);xhr.send();";
    }

    public final void a(String str) {
        byte[] bArrDecode;
        String str2;
        if (b(str)) {
            String str3 = str.split(",")[1];
            bArrDecode = Base64.decode(str3, 0);
            str2 = str3;
        } else {
            bArrDecode = null;
            str2 = str;
        }
        RequestManager requestManagerWith = Glide.with(this.f9305a);
        Object obj = str2;
        if (bArrDecode != null) {
            obj = bArrDecode;
        }
        requestManagerWith.load(obj).into(new a());
        b bVar = this.b;
        if (bVar != null) {
            bVar.a("");
        }
    }

    public void a(Context context, Bitmap bitmap) {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "MyApp" + File.separator);
        if (!file.exists()) {
            file.mkdir();
        }
        File file2 = new File(file, c);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), file2.getAbsolutePath(), c, (String) null);
        } catch (FileNotFoundException e3) {
            e3.printStackTrace();
        }
        context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.parse(file2.getAbsolutePath())));
    }
}
