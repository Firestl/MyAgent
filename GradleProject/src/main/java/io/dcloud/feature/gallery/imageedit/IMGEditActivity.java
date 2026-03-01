package io.dcloud.feature.gallery.imageedit;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import com.dmcbig.mediapicker.utils.FileUtils;
import com.taobao.weex.common.Constants;
import com.umeng.analytics.pro.bq;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.feature.gallery.imageedit.c.d;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;
import supwisdom.k7;

/* JADX INFO: loaded from: classes2.dex */
public class IMGEditActivity extends a {
    public boolean j = false;
    public int k = 0;
    public int l = 0;
    public int m = 80;
    public boolean n = true;
    public String o;
    public int p;

    @Override // io.dcloud.feature.gallery.imageedit.b.a
    public void a(d dVar) {
        this.f6523a.a(dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0041  */
    @Override // io.dcloud.feature.gallery.imageedit.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.graphics.Bitmap c() {
        /*
            Method dump skipped, instruction units count: 203
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.gallery.imageedit.IMGEditActivity.c():android.graphics.Bitmap");
    }

    @Override // io.dcloud.feature.gallery.imageedit.a
    public void e() {
        finish();
    }

    @Override // io.dcloud.feature.gallery.imageedit.a
    public void f() {
        if (!this.j) {
            this.f6523a.a();
            b(this.f6523a.getMode() == io.dcloud.feature.gallery.imageedit.c.b.CLIP ? 1 : 0);
        } else if (PdrUtil.isEmpty(this.o) || !this.o.equals("camera")) {
            finish();
        } else {
            startActivityForResult(new Intent("android.media.action.IMAGE_CAPTURE"), 0, k7.a(this, 0, 0).a());
        }
    }

    @Override // io.dcloud.feature.gallery.imageedit.a
    public void g() {
        JSONObject jSONObject;
        int i;
        String stringExtra = getIntent().getStringExtra("IMAGE_CROP");
        this.p = getIntent().getIntExtra("IMAGE_MEDIA_ID", 0);
        if (TextUtils.isEmpty(stringExtra)) {
            jSONObject = null;
        } else {
            try {
                jSONObject = new JSONObject(stringExtra);
            } catch (JSONException unused) {
                jSONObject = null;
            }
        }
        if (jSONObject != null && jSONObject.length() > 1) {
            Pattern patternCompile = Pattern.compile("[^0-9]");
            try {
                this.k = Integer.parseInt(patternCompile.matcher(jSONObject.optString("width")).replaceAll(""));
                this.l = Integer.parseInt(patternCompile.matcher(jSONObject.optString("height")).replaceAll(""));
            } catch (Exception unused2) {
            }
            this.m = jSONObject.optInt(Constants.Name.QUALITY, 80);
            this.n = jSONObject.optBoolean("resize", true);
            int i2 = this.k;
            if (i2 > 0 && (i = this.l) > 0) {
                this.f6523a.b(i2, i);
            }
            a(io.dcloud.feature.gallery.imageedit.c.b.CLIP);
            this.j = true;
        }
        this.o = getIntent().getStringExtra("IMAGE_SOURCE");
    }

    @Override // io.dcloud.feature.gallery.imageedit.a
    public void h() {
        Bitmap bitmapI;
        Uri uriInsert;
        int i;
        String stringExtra = getIntent().getStringExtra("IMAGE_SAVE_PATH");
        String str = System.currentTimeMillis() + FileUtils.JPEG_FILE_SUFFIX;
        if (TextUtils.isEmpty(stringExtra)) {
            if (Build.VERSION.SDK_INT >= 29) {
                stringExtra = getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/DImage/" + str;
            } else {
                stringExtra = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/DImage/" + str;
            }
        }
        String str2 = stringExtra;
        if (TextUtils.isEmpty(str2) || (bitmapI = this.f6523a.i()) == null) {
            setResult(0);
            finish();
            return;
        }
        int i2 = 80;
        if (this.p == -1001) {
            File file = new File(str2);
            if (!file.exists()) {
                finish();
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                Bitmap bitmapA = a(bitmapI);
                Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.JPEG;
                int i3 = this.m;
                if (i3 > 100) {
                    i2 = 100;
                } else if (i3 >= 0) {
                    i2 = i3;
                }
                bitmapA.compress(compressFormat, i2, fileOutputStream);
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            }
            setResult(-1, new Intent());
            finish();
            return;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("datetaken", Long.valueOf(System.currentTimeMillis()));
            contentValues.put("mime_type", "image/jpeg");
            contentValues.put("is_pending", (Integer) 0);
            contentValues.put("_display_name", str);
            contentValues.put("relative_path", Environment.DIRECTORY_PICTURES + "/DImage");
            uriInsert = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            try {
                OutputStream outputStreamOpenOutputStream = getContentResolver().openOutputStream(uriInsert);
                if (this.j) {
                    Bitmap bitmapA2 = a(bitmapI);
                    Bitmap.CompressFormat compressFormat2 = Bitmap.CompressFormat.JPEG;
                    int i4 = this.m;
                    if (i4 > 100) {
                        i2 = 100;
                    } else if (i4 >= 0) {
                        i2 = i4;
                    }
                    bitmapA2.compress(compressFormat2, i2, outputStreamOpenOutputStream);
                } else {
                    bitmapI.compress(Bitmap.CompressFormat.JPEG, 100, outputStreamOpenOutputStream);
                }
            } catch (FileNotFoundException unused) {
            }
        } else {
            File file2 = new File(str2);
            if (!file2.exists()) {
                try {
                    File parentFile = file2.getParentFile();
                    if (parentFile != null && !parentFile.exists()) {
                        parentFile.mkdirs();
                    }
                    file2.createNewFile();
                } catch (IOException unused2) {
                }
            }
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
                if (this.j) {
                    Bitmap bitmapA3 = a(bitmapI);
                    Bitmap.CompressFormat compressFormat3 = Bitmap.CompressFormat.JPEG;
                    int i5 = this.m;
                    if (i5 > 100) {
                        i2 = 100;
                    } else if (i5 >= 0) {
                        i2 = i5;
                    }
                    bitmapA3.compress(compressFormat3, i2, fileOutputStream2);
                } else {
                    bitmapI.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream2);
                }
            } catch (FileNotFoundException e3) {
                e3.printStackTrace();
            }
            ContentValues contentValues2 = new ContentValues();
            contentValues2.put("_data", str2);
            contentValues2.put("datetaken", Long.valueOf(System.currentTimeMillis()));
            contentValues2.put("mime_type", "image/jpeg");
            uriInsert = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues2);
        }
        Uri uri = uriInsert;
        Intent intent = new Intent();
        Cursor cursorQuery = getContentResolver().query(uri, new String[]{"_display_name", "date_added", "mime_type", "_size", "_data", bq.d}, null, null, null);
        if (cursorQuery != null) {
            cursorQuery.moveToNext();
            String string = cursorQuery.getString(cursorQuery.getColumnIndex("_display_name"));
            long j = cursorQuery.getLong(cursorQuery.getColumnIndex("date_added"));
            int i6 = cursorQuery.getInt(cursorQuery.getColumnIndex("mime_type"));
            long j2 = cursorQuery.getLong(cursorQuery.getColumnIndex("_size"));
            int i7 = cursorQuery.getInt(cursorQuery.getColumnIndex(bq.d));
            String string2 = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data"));
            String strA = a(string2);
            cursorQuery.close();
            intent.putExtra("_display_name", string);
            intent.putExtra("date_added", j);
            intent.putExtra("mime_type", i6);
            intent.putExtra("_size", j2);
            intent.putExtra(bq.d, i7);
            intent.putExtra("PARENTPATH", strA);
            i = -1;
            intent.putExtra("IMAGE_INDEX", getIntent().getIntExtra("IMAGE_INDEX", -1));
            intent.putExtra("PATH", string2);
        } else {
            i = -1;
        }
        setResult(i, intent);
        finish();
    }

    @Override // io.dcloud.feature.gallery.imageedit.a
    public void i() {
        this.f6523a.b();
        if (this.j) {
            h();
        } else {
            b(this.f6523a.getMode() == io.dcloud.feature.gallery.imageedit.c.b.CLIP ? 1 : 0);
        }
    }

    @Override // io.dcloud.feature.gallery.imageedit.a
    public void j() {
        this.f6523a.h();
    }

    @Override // io.dcloud.feature.gallery.imageedit.a
    public void k() {
        this.f6523a.c();
    }

    @Override // io.dcloud.feature.gallery.imageedit.a
    public void m() {
        io.dcloud.feature.gallery.imageedit.c.b mode = this.f6523a.getMode();
        if (mode == io.dcloud.feature.gallery.imageedit.c.b.DOODLE) {
            this.f6523a.k();
        } else if (mode == io.dcloud.feature.gallery.imageedit.c.b.MOSAIC) {
            this.f6523a.l();
        }
    }

    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
    }

    @Override // io.dcloud.feature.gallery.imageedit.a, android.view.View.OnClickListener
    public /* bridge */ /* synthetic */ void onClick(View view) {
        super.onClick(view);
    }

    @Override // io.dcloud.feature.gallery.imageedit.a
    public void a(io.dcloud.feature.gallery.imageedit.c.b bVar) {
        if (this.f6523a.getMode() == bVar) {
            bVar = io.dcloud.feature.gallery.imageedit.c.b.NONE;
        }
        this.f6523a.setMode(bVar);
        n();
        if (bVar == io.dcloud.feature.gallery.imageedit.c.b.CLIP) {
            b(1);
        }
    }

    private Bitmap a(Bitmap bitmap) {
        if (!this.n || this.l <= 0 || this.k <= 0) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(this.k / bitmap.getWidth(), this.l / bitmap.getHeight());
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
    }

    @Override // io.dcloud.feature.gallery.imageedit.a
    public void a(int i) {
        this.f6523a.setPenColor(i);
    }

    public String a(String str) {
        return str.split("/")[r2.length - 2];
    }
}
