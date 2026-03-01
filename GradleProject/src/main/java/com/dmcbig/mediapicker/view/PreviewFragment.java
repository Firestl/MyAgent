package com.dmcbig.mediapicker.view;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.dcloud.android.widget.photoview.PhotoView;
import com.dmcbig.mediapicker.MediaPickerR;
import com.dmcbig.mediapicker.PreviewActivity;
import com.dmcbig.mediapicker.entity.Media;
import io.dcloud.common.util.LoadAppUtils;
import supwisdom.kv;
import supwisdom.qv;

/* JADX INFO: loaded from: classes.dex */
public class PreviewFragment extends Fragment {
    public PhotoView mPhotoView;
    public ImageView play_view;

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isIntentAvailable(Context context, Intent intent) {
        return context.getPackageManager().queryIntentActivities(intent, 0).size() > 0;
    }

    public static PreviewFragment newInstance(Media media, String str) {
        PreviewFragment previewFragment = new PreviewFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("media", media);
        previewFragment.setArguments(bundle);
        return previewFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        setRetainInstance(true);
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(MediaPickerR.MP_LAYOUT_PREVIEW_FRAGMENT_ITEM, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        Media media = (Media) getArguments().getParcelable("media");
        this.play_view = (ImageView) view.findViewById(MediaPickerR.MP_ID_PLAY_VIEW);
        PhotoView photoView = (PhotoView) view.findViewById(MediaPickerR.MP_ID_PHOTOVIEW);
        this.mPhotoView = photoView;
        photoView.setMaximumScale(5.0f);
        this.mPhotoView.setOnPhotoTapListener(new kv() { // from class: com.dmcbig.mediapicker.view.PreviewFragment.1
            @Override // supwisdom.kv
            public void onPhotoTap(ImageView imageView, float f, float f2) {
                ((PreviewActivity) PreviewFragment.this.getActivity()).setBarStatus();
            }
        });
        setPlayView(media);
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        int i = media.mediaType;
        if (i != 1 && i == 3) {
            uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        }
        Glide.with(getActivity()).load(ContentUris.withAppendedId(uri, media.id)).into(this.mPhotoView);
    }

    public void setPlayView(final Media media) {
        if (media.mediaType == 3) {
            this.play_view.setVisibility(0);
            this.play_view.setOnClickListener(new View.OnClickListener() { // from class: com.dmcbig.mediapicker.view.PreviewFragment.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    Intent dataAndTypeIntent = LoadAppUtils.getDataAndTypeIntent(PreviewFragment.this.getContext(), media.path, "video/*");
                    PreviewFragment previewFragment = PreviewFragment.this;
                    if (previewFragment.isIntentAvailable(previewFragment.getContext(), dataAndTypeIntent)) {
                        PreviewFragment.this.startActivity(dataAndTypeIntent);
                    } else {
                        qv.makeText(PreviewFragment.this.getContext(), (CharSequence) PreviewFragment.this.getString(MediaPickerR.MP_STRING_CANT_PLAY_VIDEO), 0).show();
                    }
                }
            });
        }
    }
}
