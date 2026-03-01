package com.dmcbig.mediapicker.data;

import com.dmcbig.mediapicker.entity.Folder;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class LoaderM {
    public String getParent(String str) {
        return str.split("/")[r2.length - 2];
    }

    public int hasDir(ArrayList<Folder> arrayList, String str) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).name.equals(str)) {
                return i;
            }
        }
        return -1;
    }
}
