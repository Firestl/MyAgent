package com.dmcbig.mediapicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import androidtranscoder.MediaTranscoder;
import androidtranscoder.format.MediaFormatStrategyPresets;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.dmcbig.mediapicker.adapter.FolderAdapter;
import com.dmcbig.mediapicker.adapter.MediaGridAdapter;
import com.dmcbig.mediapicker.adapter.SpacingDecoration;
import com.dmcbig.mediapicker.data.DataCallback;
import com.dmcbig.mediapicker.data.ImageLoader;
import com.dmcbig.mediapicker.data.MediaLoader;
import com.dmcbig.mediapicker.data.VideoLoader;
import com.dmcbig.mediapicker.entity.Folder;
import com.dmcbig.mediapicker.entity.Media;
import com.dmcbig.mediapicker.utils.FileUtils;
import com.dmcbig.mediapicker.utils.ScreenUtils;
import com.taobao.weex.common.Constants;
import com.umeng.analytics.pro.bq;
import io.dcloud.base.R;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.CompressUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.RuningAcitvityUtil;
import io.dcloud.common.util.ThreadPool;
import io.dcloud.common.util.language.LanguageUtil;
import io.dcloud.feature.gallery.imageedit.IMGEditActivity;
import io.dcloud.js.gallery.GalleryFeatureImpl;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import supwisdom.qv;

/* JADX INFO: loaded from: classes.dex */
public class PickerActivity extends FragmentActivity implements DataCallback, View.OnClickListener {
    public Intent argsIntent;
    public Button category_btn;
    public String cropOptions;
    public String docPath;
    public Button done;
    public ImageView fullImage;
    public MediaGridAdapter gridAdapter;
    public FolderAdapter mFolderAdapter;
    public ListPopupWindow mFolderPopupWindow;
    public Button preview;
    public RecyclerView recyclerView;
    public Iterator<Media> selectIterator;
    public boolean isSingle = false;
    public String doneBtnText = "";
    public boolean isCompress = false;
    public boolean isFinish = false;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: compress, reason: merged with bridge method [inline-methods] */
    public void a(final ArrayList<Media> arrayList) {
        final String str;
        if (isFinishing() || isDestroyed()) {
            return;
        }
        if (!this.selectIterator.hasNext()) {
            runOnUiThread(new Runnable() { // from class: com.dmcbig.mediapicker.PickerActivity.5
                @Override // java.lang.Runnable
                public void run() {
                    Intent intent = new Intent();
                    intent.putParcelableArrayListExtra(PickerConfig.EXTRA_RESULT, arrayList);
                    PickerActivity.this.setResult(PickerConfig.RESULT_CODE, intent);
                    PickerActivity.this.finish();
                }
            });
            return;
        }
        final Media next = this.selectIterator.next();
        if (next.extension.equalsIgnoreCase(".png") || next.extension.equalsIgnoreCase(FileUtils.JPEG_FILE_SUFFIX) || next.extension.equalsIgnoreCase(".jpeg")) {
            String strCompressImage = CompressUtil.compressImage(next.path, this.docPath + "uniapp_temp/compressed/" + System.currentTimeMillis() + "_" + next.path.split("/")[r1.length - 1], next.extension.equalsIgnoreCase(".png"));
            if (!TextUtils.isEmpty(strCompressImage)) {
                next.path = strCompressImage;
            }
            a(arrayList);
            return;
        }
        if (next.mediaType != 3) {
            a(arrayList);
            return;
        }
        if (this.docPath.endsWith("/")) {
            str = this.docPath + "compress_video_" + SystemClock.elapsedRealtime() + ".mp4";
        } else {
            str = this.docPath + "/compress_video_" + SystemClock.elapsedRealtime() + ".mp4";
        }
        try {
            new File(str).getParentFile().mkdirs();
            MediaTranscoder.getInstance().transcodeVideo(next.path, str, MediaFormatStrategyPresets.createAndroid720pStrategy(2, 1.0d), new MediaTranscoder.Listener() { // from class: com.dmcbig.mediapicker.PickerActivity.4
                @Override // androidtranscoder.MediaTranscoder.Listener
                public void onTranscodeCanceled() {
                    PickerActivity.this.a(arrayList);
                }

                @Override // androidtranscoder.MediaTranscoder.Listener
                public void onTranscodeCompleted() {
                    next.path = str;
                    PickerActivity.this.a(arrayList);
                }

                @Override // androidtranscoder.MediaTranscoder.Listener
                public void onTranscodeFailed(Exception exc) {
                    PickerActivity.this.a(arrayList);
                }

                @Override // androidtranscoder.MediaTranscoder.Listener
                public void onTranscodeProgress(double d) {
                }
            });
        } catch (Exception unused) {
            a(arrayList);
        }
    }

    private void getOtherArgs() {
        String stringExtra = this.argsIntent.getStringExtra(PickerConfig.DONE_BUTTON_TEXT);
        this.doneBtnText = stringExtra;
        if (!PdrUtil.isEmpty(stringExtra)) {
            this.done.setText(this.doneBtnText);
        }
        if (PdrUtil.isEmpty(this.cropOptions)) {
            return;
        }
        this.done.setVisibility(4);
        findViewById(MediaPickerR.MP_ID_PREVIEW).setVisibility(4);
        this.isCompress = false;
        findViewById(R.id.check_origin_image_layout).setVisibility(8);
    }

    private void setTopAndBottomBarColor() {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(Color.parseColor("#21282C"));
            window.setNavigationBarColor(Color.parseColor("#21282C"));
        }
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        if (Build.VERSION.SDK_INT < 26) {
            super.attachBaseContext(context);
        } else {
            super.attachBaseContext(LanguageUtil.updateContextLanguageAfterO(context, false));
        }
    }

    public void createAdapter() {
        this.recyclerView.setLayoutManager(new GridLayoutManager(this, PickerConfig.GridSpanCount));
        this.recyclerView.addItemDecoration(new SpacingDecoration(PickerConfig.GridSpanCount, PickerConfig.GridSpace));
        this.recyclerView.setHasFixedSize(true);
        ArrayList arrayList = new ArrayList();
        ArrayList parcelableArrayListExtra = this.argsIntent.getParcelableArrayListExtra(PickerConfig.DEFAULT_SELECTED_LIST);
        final String stringExtra = this.argsIntent.getStringExtra(PickerConfig.SELECTED_MAX_CALLBACK_ID);
        int intExtra = this.argsIntent.getIntExtra(PickerConfig.MAX_SELECT_COUNT, Integer.MAX_VALUE);
        long longExtra = this.argsIntent.getLongExtra(PickerConfig.MAX_SELECT_SIZE, Long.MAX_VALUE);
        this.isSingle = this.argsIntent.getBooleanExtra(PickerConfig.SINGLE_SELECT, false);
        if (!TextUtils.isEmpty(this.cropOptions)) {
            this.isSingle = true;
        }
        if (this.isSingle) {
            Button button = this.done;
            if (button != null) {
                button.setVisibility(8);
            }
            Button button2 = this.preview;
            if (button2 != null) {
                button2.setVisibility(4);
            }
        }
        MediaGridAdapter mediaGridAdapter = new MediaGridAdapter(arrayList, this, parcelableArrayListExtra, intExtra, longExtra, this.isSingle);
        this.gridAdapter = mediaGridAdapter;
        mediaGridAdapter.setOnMaxListener(new MediaGridAdapter.OnPickerSelectMaxListener() { // from class: com.dmcbig.mediapicker.PickerActivity.1
            @Override // com.dmcbig.mediapicker.adapter.MediaGridAdapter.OnPickerSelectMaxListener
            public void onMaxed() {
                if (TextUtils.isEmpty(stringExtra)) {
                    return;
                }
                GalleryFeatureImpl.onMaxed(PickerActivity.this, stringExtra);
            }
        });
        this.recyclerView.setAdapter(this.gridAdapter);
    }

    public void createFolderAdapter() {
        this.mFolderAdapter = new FolderAdapter(new ArrayList(), this);
        ListPopupWindow listPopupWindow = new ListPopupWindow(this);
        this.mFolderPopupWindow = listPopupWindow;
        listPopupWindow.setBackgroundDrawable(new ColorDrawable(-1));
        this.mFolderPopupWindow.setAdapter(this.mFolderAdapter);
        this.mFolderPopupWindow.setHeight((int) (((double) ScreenUtils.getScreenHeight(this)) * 0.6d));
        this.mFolderPopupWindow.setAnchorView(findViewById(MediaPickerR.MP_ID_FOOTER));
        this.mFolderPopupWindow.setModal(true);
        this.mFolderPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.dmcbig.mediapicker.PickerActivity.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                PickerActivity.this.mFolderAdapter.setSelectIndex(i);
                PickerActivity pickerActivity = PickerActivity.this;
                pickerActivity.category_btn.setText(pickerActivity.mFolderAdapter.getItem(i).name);
                PickerActivity pickerActivity2 = PickerActivity.this;
                pickerActivity2.gridAdapter.updateAdapter(pickerActivity2.mFolderAdapter.getSelectMedias());
                PickerActivity.this.mFolderPopupWindow.dismiss();
            }
        });
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.isFinish) {
            return true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void done(final ArrayList<Media> arrayList) {
        this.isFinish = true;
        if (!this.isCompress || arrayList == null || arrayList.size() <= 0) {
            Intent intent = new Intent();
            intent.putParcelableArrayListExtra(PickerConfig.EXTRA_RESULT, arrayList);
            setResult(PickerConfig.RESULT_CODE, intent);
            finish();
            return;
        }
        this.done.setClickable(false);
        this.done.setFocusable(true);
        findViewById(R.id.loading).setVisibility(0);
        this.selectIterator = arrayList.iterator();
        ThreadPool.self().addThreadTask(new Runnable() { // from class: supwisdom.f00
            @Override // java.lang.Runnable
            public final void run() {
                this.f7555a.a(arrayList);
            }
        });
    }

    public void getMediaData() {
        int intExtra = this.argsIntent.getIntExtra(PickerConfig.SELECT_MODE, 101);
        if (intExtra == 101) {
            getLoaderManager().initLoader(intExtra, null, new MediaLoader(this, this));
        } else if (intExtra == 100) {
            getLoaderManager().initLoader(intExtra, null, new ImageLoader(this, this));
        } else if (intExtra == 102) {
            getLoaderManager().initLoader(intExtra, null, new VideoLoader(this, this));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent != null && intent.hasExtra(PickerConfig.FULL_IMAGE)) {
            this.fullImage.setSelected(intent.getBooleanExtra(PickerConfig.FULL_IMAGE, false));
            this.isCompress = !this.fullImage.isSelected();
        }
        if (i != 200) {
            if (i == 201 && i2 == -1 && intent != null) {
                int intExtra = intent.getIntExtra("IMAGE_INDEX", -1);
                int intExtra2 = intent.getIntExtra(bq.d, -1);
                if (intExtra2 == -1 || intExtra == -1) {
                    return;
                }
                Media media = new Media(intent.getStringExtra("PATH"), intent.getStringExtra("_display_name"), intent.getLongExtra("date_added", System.currentTimeMillis()), intent.getIntExtra("mime_type", 0), intent.getLongExtra("_size", 0L), intExtra2, intent.getStringExtra("PARENTPATH"));
                ArrayList<Media> arrayList = new ArrayList<>();
                arrayList.add(media);
                done(arrayList);
                return;
            }
            return;
        }
        ArrayList<Media> parcelableArrayListExtra = intent.getParcelableArrayListExtra(PickerConfig.EXTRA_RESULT);
        if (i2 != 1990) {
            if (i2 == 19901026) {
                done(parcelableArrayListExtra);
                return;
            }
            return;
        }
        ArrayList<Integer> integerArrayListExtra = intent.getIntegerArrayListExtra(PickerConfig.ORIGINAL_PREVIEW_INDEX);
        ArrayList parcelableArrayListExtra2 = intent.getParcelableArrayListExtra(PickerConfig.EDITED_PREVIEW_DATA);
        if (integerArrayListExtra != null && integerArrayListExtra.size() > 0 && parcelableArrayListExtra2 != null && parcelableArrayListExtra2.size() > 0) {
            ArrayList<Media> medias = this.gridAdapter.getMedias();
            for (int i3 = 0; i3 < integerArrayListExtra.size(); i3++) {
                int iIntValue = integerArrayListExtra.get(i3).intValue();
                if (iIntValue >= 0) {
                    medias.set(iIntValue, parcelableArrayListExtra2.get(i3));
                }
            }
            this.gridAdapter.updateAdapter(medias);
        }
        this.gridAdapter.updateSelectAdapter(parcelableArrayListExtra);
        setButtonText();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        done(new ArrayList<>());
        super.onBackPressed();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == MediaPickerR.MP_ID_BTN_BACK) {
            done(new ArrayList<>());
            return;
        }
        if (id == MediaPickerR.MP_ID_CATEGORY_BTN) {
            if (this.mFolderPopupWindow.isShowing()) {
                this.mFolderPopupWindow.dismiss();
                return;
            } else {
                this.mFolderPopupWindow.show();
                return;
            }
        }
        if (id == MediaPickerR.MP_ID_DONE) {
            done(this.gridAdapter.getSelectMedias());
            return;
        }
        if (id != MediaPickerR.MP_ID_PREVIEW) {
            if (id == R.id.check_origin_image_layout) {
                this.fullImage.setSelected(!r6.isSelected());
                this.isCompress = !this.fullImage.isSelected();
                return;
            }
            return;
        }
        if (this.gridAdapter.getSelectMedias().size() <= 0) {
            qv.makeText((Context) this, (CharSequence) getString(MediaPickerR.MP_STRING_SELECT_NULL), 0).show();
            return;
        }
        if (PdrUtil.isEmpty(this.cropOptions)) {
            Intent intent = new Intent(this, (Class<?>) PreviewActivity.class);
            intent.putExtra(PickerConfig.MAX_SELECT_COUNT, this.argsIntent.getIntExtra(PickerConfig.MAX_SELECT_COUNT, Integer.MAX_VALUE));
            ArrayList<Media> selectMedias = this.gridAdapter.getSelectMedias();
            intent.putExtra(PickerConfig.PRE_RAW_LIST, selectMedias);
            ArrayList<Integer> arrayList = new ArrayList<>();
            Iterator<Media> it = selectMedias.iterator();
            while (it.hasNext()) {
                arrayList.add(Integer.valueOf(this.gridAdapter.getItemIndex(it.next())));
            }
            intent.putExtra(PickerConfig.FULL_IMAGE, this.fullImage.isSelected());
            intent.putExtra(PickerConfig.SIZE_TYPE, this.argsIntent.getStringExtra(PickerConfig.SIZE_TYPE));
            intent.putIntegerArrayListExtra(PickerConfig.ORIGINAL_PREVIEW_INDEX, arrayList);
            intent.putExtra(PickerConfig.IMAGE_EDITABLE, this.argsIntent.getBooleanExtra(PickerConfig.IMAGE_EDITABLE, true));
            intent.putExtra(PickerConfig.DONE_BUTTON_TEXT, this.argsIntent.getStringExtra(PickerConfig.DONE_BUTTON_TEXT));
            intent.putExtra(PickerConfig.SELECT_MODE, this.argsIntent.getIntExtra(PickerConfig.SELECT_MODE, 101));
            startActivityForResult(intent, 200);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        String stringExtra;
        super.onCreate(bundle);
        requestWindowFeature(1);
        this.argsIntent = getIntent();
        setContentView(MediaPickerR.MP_LAYOUT_PICKER_MAIN);
        this.recyclerView = (RecyclerView) findViewById(MediaPickerR.MP_ID_RECYCLER_VIEW);
        findViewById(MediaPickerR.MP_ID_BTN_BACK).setOnClickListener(this);
        setTitleBar();
        this.done = (Button) findViewById(MediaPickerR.MP_ID_DONE);
        this.category_btn = (Button) findViewById(MediaPickerR.MP_ID_CATEGORY_BTN);
        this.preview = (Button) findViewById(MediaPickerR.MP_ID_PREVIEW);
        this.fullImage = (ImageView) findViewById(R.id.check_origin_image);
        if (this.argsIntent.getIntExtra(PickerConfig.SELECT_MODE, 101) == 102) {
            this.isCompress = this.argsIntent.getBooleanExtra(PickerConfig.COMPRESSED, false);
            findViewById(R.id.check_origin_image_layout).setVisibility(8);
        } else {
            Intent intent = this.argsIntent;
            if (intent == null || !intent.hasExtra(PickerConfig.SIZE_TYPE) || (stringExtra = this.argsIntent.getStringExtra(PickerConfig.SIZE_TYPE)) == null) {
                this.isCompress = true;
                findViewById(R.id.check_origin_image_layout).setOnClickListener(this);
            } else if (stringExtra.contains(Constants.Value.ORIGINAL) && stringExtra.contains("compressed")) {
                this.isCompress = true;
                findViewById(R.id.check_origin_image_layout).setOnClickListener(this);
            } else if (stringExtra.contains(Constants.Value.ORIGINAL)) {
                this.isCompress = false;
                this.fullImage.setSelected(true);
            } else {
                this.isCompress = true;
                findViewById(R.id.check_origin_image_layout).setVisibility(8);
            }
        }
        this.done.setOnClickListener(this);
        this.category_btn.setOnClickListener(this);
        this.preview.setOnClickListener(this);
        if (BaseInfo.sGlobalFullScreen) {
            setFullScreen(this, true);
        }
        this.cropOptions = this.argsIntent.getStringExtra(PickerConfig.IMAGE_CROP);
        this.docPath = this.argsIntent.getStringExtra(PickerConfig.DOC_PATH);
        createAdapter();
        createFolderAdapter();
        getMediaData();
        getOtherArgs();
        setTopAndBottomBarColor();
    }

    @Override // com.dmcbig.mediapicker.data.DataCallback
    public void onData(ArrayList<Folder> arrayList) {
        setView(arrayList);
        this.category_btn.setText(arrayList.get(0).name);
        this.mFolderAdapter.updateAdapter(arrayList);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        Glide.get(this).clearMemory();
        super.onDestroy();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        RuningAcitvityUtil.removeRuningActivity(getComponentName().getClassName());
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, supwisdom.j7.c
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        RuningAcitvityUtil.putRuningActivity(this);
    }

    public void setButtonText() {
        int intExtra = this.argsIntent.getIntExtra(PickerConfig.MAX_SELECT_COUNT, Integer.MAX_VALUE);
        if (PdrUtil.isEmpty(this.doneBtnText)) {
            this.doneBtnText = getString(MediaPickerR.MP_STRING_DONE);
        }
        if (intExtra == Integer.MAX_VALUE) {
            this.done.setText(this.doneBtnText + "(" + this.gridAdapter.getSelectMedias().size() + ")");
        } else {
            this.done.setText(this.doneBtnText + "(" + this.gridAdapter.getSelectMedias().size() + "/" + intExtra + ")");
        }
        if (!PdrUtil.isEmpty(this.cropOptions)) {
            this.done.setVisibility(4);
            findViewById(MediaPickerR.MP_ID_PREVIEW).setVisibility(4);
            return;
        }
        this.preview.setText(getString(MediaPickerR.MP_STRING_PREVIEW) + "(" + this.gridAdapter.getSelectMedias().size() + ")");
    }

    public void setFullScreen(Activity activity, boolean z) {
        Window window = activity.getWindow();
        if (z) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.flags |= 1024;
            window.setAttributes(attributes);
        } else {
            WindowManager.LayoutParams attributes2 = window.getAttributes();
            attributes2.flags &= -1025;
            window.setAttributes(attributes2);
        }
    }

    public void setTitleBar() {
        int intExtra = this.argsIntent.getIntExtra(PickerConfig.SELECT_MODE, 101);
        if (intExtra == 101) {
            ((TextView) findViewById(MediaPickerR.MP_ID_BAR_TITLE)).setText(getString(MediaPickerR.MP_STRING_SELECT_TITLE));
        } else if (intExtra == 100) {
            ((TextView) findViewById(MediaPickerR.MP_ID_BAR_TITLE)).setText(getString(MediaPickerR.MP_STRING_SELECT_IMAGE_TITLE));
        } else if (intExtra == 102) {
            ((TextView) findViewById(MediaPickerR.MP_ID_BAR_TITLE)).setText(getString(MediaPickerR.MP_STRING_SELECT_VIDEO_TITLE));
        }
    }

    public void setView(ArrayList<Folder> arrayList) {
        this.gridAdapter.updateAdapter(arrayList.get(0).getMedias());
        setButtonText();
        this.gridAdapter.setOnItemClickListener(new MediaGridAdapter.OnRecyclerViewItemClickListener() { // from class: com.dmcbig.mediapicker.PickerActivity.3
            @Override // com.dmcbig.mediapicker.adapter.MediaGridAdapter.OnRecyclerViewItemClickListener
            public void onItemClick(View view, Media media, ArrayList<Media> arrayList2) {
                PickerActivity pickerActivity = PickerActivity.this;
                if (!pickerActivity.isSingle) {
                    pickerActivity.setButtonText();
                    return;
                }
                if (PdrUtil.isEmpty(pickerActivity.cropOptions)) {
                    Intent intent = new Intent(PickerActivity.this, (Class<?>) PreviewActivity.class);
                    intent.putExtra(PickerConfig.MAX_SELECT_COUNT, PickerActivity.this.argsIntent.getIntExtra(PickerConfig.MAX_SELECT_COUNT, Integer.MAX_VALUE));
                    intent.putExtra(PickerConfig.SINGLE_SELECT, true);
                    ArrayList arrayList3 = new ArrayList(1);
                    arrayList3.add(media);
                    ArrayList<Integer> arrayList4 = new ArrayList<>();
                    arrayList4.add(Integer.valueOf(PickerActivity.this.gridAdapter.getItemIndex(media)));
                    intent.putExtra(PickerConfig.PRE_RAW_LIST, arrayList3);
                    intent.putExtra(PickerConfig.FULL_IMAGE, PickerActivity.this.fullImage.isSelected());
                    intent.putExtra(PickerConfig.SIZE_TYPE, PickerActivity.this.argsIntent.getStringExtra(PickerConfig.SIZE_TYPE));
                    intent.putExtra(PickerConfig.SELECT_MODE, PickerActivity.this.argsIntent.getIntExtra(PickerConfig.SELECT_MODE, 101));
                    intent.putIntegerArrayListExtra(PickerConfig.ORIGINAL_PREVIEW_INDEX, arrayList4);
                    PickerActivity.this.startActivityForResult(intent, 200);
                    return;
                }
                if (".gif".equalsIgnoreCase(media.extension) || media.mediaType == 3) {
                    ArrayList<Media> arrayList5 = new ArrayList<>();
                    arrayList5.add(media);
                    PickerActivity.this.done(arrayList5);
                    return;
                }
                Intent intent2 = new Intent(PickerActivity.this, (Class<?>) IMGEditActivity.class);
                int itemIndex = PickerActivity.this.gridAdapter.getItemIndex(media);
                intent2.putExtra("IMAGE_URI", Uri.parse("file://" + media.path));
                intent2.putExtra("IMAGE_MEDIA_ID", media.id);
                intent2.putExtra("IMAGE_INDEX", itemIndex);
                intent2.putExtra("IMAGE_CROP", PickerActivity.this.cropOptions);
                PickerActivity.this.startActivityForResult(intent2, 201);
            }
        });
    }
}
