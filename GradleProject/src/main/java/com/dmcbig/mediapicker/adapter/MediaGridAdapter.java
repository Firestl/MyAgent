package com.dmcbig.mediapicker.adapter;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.dmcbig.mediapicker.MediaPickerR;
import com.dmcbig.mediapicker.PickerConfig;
import com.dmcbig.mediapicker.entity.Media;
import com.dmcbig.mediapicker.utils.FileUtils;
import com.dmcbig.mediapicker.utils.ScreenUtils;
import java.util.ArrayList;
import supwisdom.qv;
import supwisdom.y7;

/* JADX INFO: loaded from: classes.dex */
public class MediaGridAdapter extends RecyclerView.g<MyViewHolder> {
    public Context context;
    public boolean isSingle;
    public long maxSelect;
    public long maxSize;
    public ArrayList<Media> medias;
    public ArrayList<Media> selectMedias;
    public FileUtils fileUtils = new FileUtils();
    public OnRecyclerViewItemClickListener mOnItemClickListener = null;
    public OnPickerSelectMaxListener mOnMaxListener = null;

    public class MyViewHolder extends RecyclerView.b0 {
        public ImageView check_image;
        public RelativeLayout gif_info;
        public View mask_view;
        public ImageView media_image;
        public TextView textView_size;
        public RelativeLayout video_info;

        public MyViewHolder(View view) {
            super(view);
            this.media_image = (ImageView) view.findViewById(MediaPickerR.MP_ID_MEDIA_IMAGE);
            this.check_image = (ImageView) view.findViewById(MediaPickerR.MP_ID_CHECK_IMAGE);
            this.mask_view = view.findViewById(MediaPickerR.MP_ID_MASK_VIEW);
            this.video_info = (RelativeLayout) view.findViewById(MediaPickerR.MP_ID_VIDEO_INFO);
            this.gif_info = (RelativeLayout) view.findViewById(MediaPickerR.MP_ID_GIF_INFO);
            this.textView_size = (TextView) view.findViewById(MediaPickerR.MP_ID_TEXTVIEW_SIZE);
            this.itemView.setLayoutParams(new AbsListView.LayoutParams(-1, MediaGridAdapter.this.getItemWidth()));
        }
    }

    public interface OnPickerSelectMaxListener {
        void onMaxed();
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, Media media, ArrayList<Media> arrayList);
    }

    public MediaGridAdapter(ArrayList<Media> arrayList, Context context, ArrayList<Media> arrayList2, int i, long j, boolean z) {
        this.selectMedias = new ArrayList<>();
        this.isSingle = false;
        if (arrayList2 != null) {
            this.selectMedias = arrayList2;
        }
        this.maxSelect = i;
        this.maxSize = j;
        this.medias = arrayList;
        this.context = context;
        this.isSingle = z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int getItemCount() {
        return this.medias.size();
    }

    public int getItemIndex(Object obj) {
        return this.medias.indexOf(obj);
    }

    public int getItemWidth() {
        int screenWidth = ScreenUtils.getScreenWidth(this.context);
        int i = PickerConfig.GridSpanCount;
        return (screenWidth / i) - i;
    }

    public ArrayList<Media> getMedias() {
        return this.medias;
    }

    public ArrayList<Media> getSelectMedias() {
        return this.selectMedias;
    }

    public int isSelect(Media media) {
        if (this.selectMedias.size() <= 0) {
            return -1;
        }
        for (int i = 0; i < this.selectMedias.size(); i++) {
            Media media2 = this.selectMedias.get(i);
            if (media2.path.equals(media.path)) {
                media2.id = media.id;
                return i;
            }
        }
        return -1;
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.mOnItemClickListener = onRecyclerViewItemClickListener;
    }

    public void setOnMaxListener(OnPickerSelectMaxListener onPickerSelectMaxListener) {
        this.mOnMaxListener = onPickerSelectMaxListener;
    }

    public void setSelectMedias(Media media) {
        int iIsSelect = isSelect(media);
        if (iIsSelect == -1) {
            this.selectMedias.add(media);
        } else {
            this.selectMedias.remove(iIsSelect);
        }
    }

    public void updateAdapter(ArrayList<Media> arrayList) {
        this.medias = arrayList;
        notifyDataSetChanged();
    }

    public void updateSelectAdapter(ArrayList<Media> arrayList) {
        if (arrayList != null) {
            this.selectMedias = arrayList;
        }
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {
        Context context;
        int i2;
        final Media media = this.medias.get(i);
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        int i3 = media.mediaType;
        if (i3 != 1 && i3 == 3) {
            uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        }
        Glide.with(this.context).load(ContentUris.withAppendedId(uri, media.id)).into(myViewHolder.media_image);
        if (media.mediaType == 3) {
            myViewHolder.gif_info.setVisibility(4);
            myViewHolder.video_info.setVisibility(0);
            myViewHolder.textView_size.setText(this.fileUtils.getSizeByUnit(media.size));
        } else {
            myViewHolder.video_info.setVisibility(4);
            myViewHolder.gif_info.setVisibility(".gif".equalsIgnoreCase(media.extension) ? 0 : 4);
        }
        int iIsSelect = isSelect(media);
        if (this.isSingle) {
            myViewHolder.check_image.setVisibility(8);
            myViewHolder.mask_view.setVisibility(8);
        } else {
            myViewHolder.mask_view.setVisibility(iIsSelect < 0 ? 4 : 0);
            ImageView imageView = myViewHolder.check_image;
            if (iIsSelect >= 0) {
                context = this.context;
                i2 = MediaPickerR.MP_DRAWABLE_BNT_SELECTED;
            } else {
                context = this.context;
                i2 = MediaPickerR.MP_DRAWABLE_BNT_UNSELECTED;
            }
            imageView.setImageDrawable(y7.c(context, i2));
        }
        myViewHolder.media_image.setOnClickListener(new View.OnClickListener() { // from class: com.dmcbig.mediapicker.adapter.MediaGridAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Context context2;
                int i4;
                MediaGridAdapter mediaGridAdapter = MediaGridAdapter.this;
                if (mediaGridAdapter.isSingle) {
                    mediaGridAdapter.mOnItemClickListener.onItemClick(view, media, null);
                    return;
                }
                int iIsSelect2 = mediaGridAdapter.isSelect(media);
                long size = MediaGridAdapter.this.selectMedias.size();
                MediaGridAdapter mediaGridAdapter2 = MediaGridAdapter.this;
                if (size >= mediaGridAdapter2.maxSelect && iIsSelect2 < 0) {
                    if (mediaGridAdapter2.mOnMaxListener != null) {
                        MediaGridAdapter.this.mOnMaxListener.onMaxed();
                        return;
                    }
                    return;
                }
                if (media.size > mediaGridAdapter2.maxSize) {
                    qv.makeText(mediaGridAdapter2.context, (CharSequence) (MediaGridAdapter.this.context.getString(MediaPickerR.MP_STRING_MSG_SIZE_LIMIT) + FileUtils.fileSize(MediaGridAdapter.this.maxSize)), 1).show();
                    return;
                }
                myViewHolder.mask_view.setVisibility(iIsSelect2 >= 0 ? 4 : 0);
                ImageView imageView2 = myViewHolder.check_image;
                if (iIsSelect2 >= 0) {
                    context2 = MediaGridAdapter.this.context;
                    i4 = MediaPickerR.MP_DRAWABLE_BNT_UNSELECTED;
                } else {
                    context2 = MediaGridAdapter.this.context;
                    i4 = MediaPickerR.MP_DRAWABLE_BNT_SELECTED;
                }
                imageView2.setImageDrawable(y7.c(context2, i4));
                MediaGridAdapter.this.setSelectMedias(media);
                MediaGridAdapter.this.mOnItemClickListener.onItemClick(view, media, MediaGridAdapter.this.selectMedias);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(MediaPickerR.MP_LAYOUT_MEDIA_VIEW_ITEM, viewGroup, false));
    }
}
