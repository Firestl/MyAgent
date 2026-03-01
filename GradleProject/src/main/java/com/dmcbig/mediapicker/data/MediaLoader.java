package com.dmcbig.mediapicker.data;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import com.dmcbig.mediapicker.MediaPickerR;
import com.dmcbig.mediapicker.entity.Folder;
import com.dmcbig.mediapicker.entity.Media;
import com.umeng.analytics.pro.bq;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class MediaLoader extends LoaderM implements LoaderManager.LoaderCallbacks {
    public String[] MEDIA_PROJECTION = {"_data", "_display_name", "date_added", "media_type", "_size", bq.d, "parent"};
    public Context mContext;
    public DataCallback mLoader;

    public MediaLoader(Context context, DataCallback dataCallback) {
        this.mContext = context;
        this.mLoader = dataCallback;
    }

    @Override // android.app.LoaderManager.LoaderCallbacks
    public Loader onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this.mContext, MediaStore.Files.getContentUri("external"), this.MEDIA_PROJECTION, "media_type=1 OR media_type=3", null, "date_added DESC");
    }

    @Override // android.app.LoaderManager.LoaderCallbacks
    public void onLoadFinished(Loader loader, Object obj) {
        ArrayList<Folder> arrayList = new ArrayList<>();
        Folder folder = new Folder(this.mContext.getResources().getString(MediaPickerR.MP_STRING_ALL_DIR_NAME));
        arrayList.add(folder);
        Folder folder2 = new Folder(this.mContext.getResources().getString(MediaPickerR.MP_STRING_VIDEO_DIR_NAME));
        arrayList.add(folder2);
        Cursor cursor = (Cursor) obj;
        while (cursor.moveToNext()) {
            String string = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
            String string2 = cursor.getString(cursor.getColumnIndexOrThrow("_display_name"));
            long j = cursor.getLong(cursor.getColumnIndexOrThrow("date_added"));
            int i = cursor.getInt(cursor.getColumnIndexOrThrow("media_type"));
            long j2 = cursor.getLong(cursor.getColumnIndexOrThrow("_size"));
            int i2 = cursor.getInt(cursor.getColumnIndexOrThrow(bq.d));
            if (j2 >= 1) {
                String parent = getParent(string);
                Cursor cursor2 = cursor;
                Media media = new Media(string, string2, j, i, j2, i2, parent);
                folder.addMedias(media);
                if (i == 3) {
                    folder2.addMedias(media);
                }
                int iHasDir = hasDir(arrayList, parent);
                if (iHasDir != -1) {
                    arrayList.get(iHasDir).addMedias(media);
                } else {
                    Folder folder3 = new Folder(parent);
                    folder3.addMedias(media);
                    arrayList.add(folder3);
                }
                cursor = cursor2;
            }
        }
        this.mLoader.onData(arrayList);
        cursor.close();
    }

    @Override // android.app.LoaderManager.LoaderCallbacks
    public void onLoaderReset(Loader loader) {
    }
}
