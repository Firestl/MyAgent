package com.dmcbig.mediapicker.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.dmcbig.mediapicker.MediaPickerR;
import com.dmcbig.mediapicker.entity.Folder;
import com.dmcbig.mediapicker.entity.Media;
import java.util.ArrayList;
import supwisdom.y7;

/* JADX INFO: loaded from: classes.dex */
public class FolderAdapter extends BaseAdapter {
    public ArrayList<Folder> folders;
    public int lastSelected = 0;
    public Context mContext;
    public LayoutInflater mInflater;

    public class ViewHolder {
        public ImageView cover;
        public ImageView indicator;
        public TextView name;
        public TextView path;
        public TextView size;

        public ViewHolder(View view) {
            this.cover = (ImageView) view.findViewById(MediaPickerR.MP_ID_COVER);
            this.name = (TextView) view.findViewById(MediaPickerR.MP_ID_NAME);
            this.path = (TextView) view.findViewById(MediaPickerR.MP_ID_PATH);
            this.size = (TextView) view.findViewById(MediaPickerR.MP_ID_SIZE);
            this.indicator = (ImageView) view.findViewById(MediaPickerR.MP_ID_INDICATOR);
            view.setTag(this);
        }
    }

    public FolderAdapter(ArrayList<Folder> arrayList, Context context) {
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        this.folders = arrayList;
        this.mContext = context;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.folders.size();
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    public ArrayList<Media> getSelectMedias() {
        return this.folders.get(this.lastSelected).getMedias();
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = this.mInflater.inflate(MediaPickerR.MP_LAYOUT_FOLDERS_VIEW_ITME, viewGroup, false);
            viewHolder = new ViewHolder(view);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Folder item = getItem(i);
        if (item.getMedias().size() > 0) {
            Media media = item.getMedias().get(0);
            Glide.with(this.mContext).load(Uri.parse("file://" + media.path)).into(viewHolder.cover);
        } else {
            viewHolder.cover.setImageDrawable(y7.c(this.mContext, MediaPickerR.MP_DRAWABLE_DEFAULT_IMAGE));
        }
        viewHolder.name.setText(item.name);
        viewHolder.size.setText(item.getMedias().size() + "" + this.mContext.getString(MediaPickerR.MP_STRING_COUNT_STRING));
        viewHolder.indicator.setVisibility(this.lastSelected != i ? 4 : 0);
        return view;
    }

    public void setSelectIndex(int i) {
        if (this.lastSelected == i) {
            return;
        }
        this.lastSelected = i;
        notifyDataSetChanged();
    }

    public void updateAdapter(ArrayList<Folder> arrayList) {
        this.folders = arrayList;
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public Folder getItem(int i) {
        return this.folders.get(i);
    }
}
