package com.supwisdom.superapp.ui.activity;

import android.content.BroadcastReceiver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.media.AudioManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.aip.face.stat.Ast;
import com.baidu.idl.face.platform.FaceConfig;
import com.baidu.idl.face.platform.FaceSDKManager;
import com.baidu.idl.face.platform.FaceStatusEnum;
import com.baidu.idl.face.platform.ILivenessStrategy;
import com.baidu.idl.face.platform.ILivenessStrategyCallback;
import com.baidu.idl.face.platform.ui.FaceSDKResSettings;
import com.baidu.idl.face.platform.ui.utils.CameraUtils;
import com.baidu.idl.face.platform.ui.utils.VolumeUtils;
import com.baidu.idl.face.platform.ui.widget.FaceDetectRoundView;
import com.baidu.idl.face.platform.utils.Base64Utils;
import com.baidu.idl.face.platform.utils.CameraPreviewUtils;
import com.supwisdom.superapp.WXBaseActivity;
import com.supwisdom.zueb.R;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class FaceLivenessActivity extends WXBaseActivity implements SurfaceHolder.Callback, Camera.PreviewCallback, Camera.ErrorCallback, VolumeUtils.VolumeCallback, ILivenessStrategyCallback {
    public BroadcastReceiver A;
    public TextView B;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public View f4080a;
    public FrameLayout b;
    public SurfaceView c;
    public SurfaceHolder d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TextView f4081e;
    public ImageView f;
    public ImageView g;
    public TextView h;
    public TextView i;
    public FaceDetectRoundView j;
    public LinearLayout k;
    public FaceConfig l;
    public ILivenessStrategy m;
    public Drawable q;
    public Camera u;
    public Camera.Parameters v;
    public int w;
    public int x;
    public int y;
    public int z;
    public Rect n = new Rect();
    public int o = 0;
    public int p = 0;
    public volatile boolean r = true;
    public HashMap<String, String> s = new HashMap<>();
    public boolean t = false;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            FaceLivenessActivity.this.onBackPressed();
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            FaceLivenessActivity.this.r = !r2.r;
            FaceLivenessActivity faceLivenessActivity = FaceLivenessActivity.this;
            faceLivenessActivity.f.setImageResource(faceLivenessActivity.r ? R.mipmap.ic_enable_sound_ext : R.mipmap.ic_disable_sound_ext);
            FaceLivenessActivity faceLivenessActivity2 = FaceLivenessActivity.this;
            ILivenessStrategy iLivenessStrategy = faceLivenessActivity2.m;
            if (iLivenessStrategy != null) {
                iLivenessStrategy.setLivenessStrategySoundEnable(faceLivenessActivity2.r);
            }
        }
    }

    public static /* synthetic */ class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f4084a;

        static {
            int[] iArr = new int[FaceStatusEnum.values().length];
            f4084a = iArr;
            try {
                iArr[FaceStatusEnum.OK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4084a[FaceStatusEnum.Liveness_OK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f4084a[FaceStatusEnum.Liveness_Completion.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f4084a[FaceStatusEnum.Detect_DataNotReady.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f4084a[FaceStatusEnum.Liveness_Eye.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f4084a[FaceStatusEnum.Liveness_Mouth.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f4084a[FaceStatusEnum.Liveness_HeadUp.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f4084a[FaceStatusEnum.Liveness_HeadDown.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f4084a[FaceStatusEnum.Liveness_HeadLeft.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f4084a[FaceStatusEnum.Liveness_HeadRight.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f4084a[FaceStatusEnum.Liveness_HeadLeftRight.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f4084a[FaceStatusEnum.Detect_PitchOutOfUpMaxRange.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f4084a[FaceStatusEnum.Detect_PitchOutOfDownMaxRange.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f4084a[FaceStatusEnum.Detect_PitchOutOfLeftMaxRange.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f4084a[FaceStatusEnum.Detect_PitchOutOfRightMaxRange.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
        }
    }

    public static Bitmap e(String str) {
        byte[] bArrDecode = Base64Utils.decode(str, 2);
        return BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length);
    }

    public final void a(FaceStatusEnum faceStatusEnum, String str) {
        switch (c.f4084a[faceStatusEnum.ordinal()]) {
            case 1:
            case 2:
            case 3:
                a(false, str);
                this.i.setText("");
                this.j.processDrawState(false);
                b(true);
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                a(false, str);
                this.i.setText("");
                this.j.processDrawState(false);
                b(false);
                break;
            case 12:
            case 13:
            case 14:
            case 15:
                a(true, str);
                this.i.setText(str);
                this.j.processDrawState(true);
                b(false);
                break;
            default:
                a(false, str);
                this.i.setText("");
                this.j.processDrawState(true);
                b(false);
                break;
        }
    }

    public final void b(boolean z) {
        if (this.g.getTag() == null) {
            Rect faceRoundRect = this.j.getFaceRoundRect();
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.g.getLayoutParams();
            layoutParams.setMargins(faceRoundRect.centerX() - (this.g.getWidth() / 2), faceRoundRect.top - (this.g.getHeight() / 2), 0, 0);
            this.g.setLayoutParams(layoutParams);
            this.g.setTag("setlayout");
        }
        this.g.setVisibility(z ? 0 : 4);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int c(android.content.Context r4) {
        /*
            r3 = this;
            java.lang.String r0 = "window"
            java.lang.Object r4 = r4.getSystemService(r0)
            android.view.WindowManager r4 = (android.view.WindowManager) r4
            android.view.Display r4 = r4.getDefaultDisplay()
            int r4 = r4.getRotation()
            r0 = 1
            r1 = 0
            if (r4 == 0) goto L27
            if (r4 == r0) goto L24
            r2 = 2
            if (r4 == r2) goto L21
            r2 = 3
            if (r4 == r2) goto L1e
            goto L27
        L1e:
            r4 = 270(0x10e, float:3.78E-43)
            goto L28
        L21:
            r4 = 180(0xb4, float:2.52E-43)
            goto L28
        L24:
            r4 = 90
            goto L28
        L27:
            r4 = 0
        L28:
            int r1 = r1 - r4
            int r1 = r1 + 360
            int r1 = r1 % 360
            boolean r2 = com.baidu.idl.face.platform.utils.APIUtils.hasGingerbread()
            if (r2 == 0) goto L52
            android.hardware.Camera$CameraInfo r1 = new android.hardware.Camera$CameraInfo
            r1.<init>()
            int r2 = r3.w
            android.hardware.Camera.getCameraInfo(r2, r1)
            int r2 = r1.facing
            if (r2 != r0) goto L4b
            int r0 = r1.orientation
            int r0 = r0 + r4
            int r0 = r0 % 360
            int r4 = 360 - r0
            int r1 = r4 % 360
            goto L52
        L4b:
            int r0 = r1.orientation
            int r0 = r0 - r4
            int r0 = r0 + 360
            int r1 = r0 % 360
        L52:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.supwisdom.superapp.ui.activity.FaceLivenessActivity.c(android.content.Context):int");
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
    }

    public final Camera l() {
        int numberOfCameras = Camera.getNumberOfCameras();
        if (numberOfCameras == 0) {
            return null;
        }
        int i = 0;
        while (i < numberOfCameras) {
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == 1) {
                break;
            }
            i++;
        }
        if (i < numberOfCameras) {
            Camera cameraOpen = Camera.open(i);
            this.w = i;
            return cameraOpen;
        }
        Camera cameraOpen2 = Camera.open(0);
        this.w = 0;
        return cameraOpen2;
    }

    public void m() {
        this.t = false;
        ILivenessStrategy iLivenessStrategy = this.m;
        if (iLivenessStrategy != null) {
            iLivenessStrategy.reset();
        }
        VolumeUtils.unRegisterVolumeReceiver(this, this.A);
        this.A = null;
        o();
        setVolumeControlStream(3);
        this.A = VolumeUtils.registerVolumeReceiver(this, this);
        TextView textView = this.h;
        if (textView != null) {
            textView.setText(R.string.detect_face_in);
        }
        n();
    }

    public void n() {
        SurfaceView surfaceView = this.c;
        if (surfaceView != null && surfaceView.getHolder() != null) {
            SurfaceHolder holder = this.c.getHolder();
            this.d = holder;
            holder.addCallback(this);
        }
        if (this.u == null) {
            try {
                this.u = l();
            } catch (RuntimeException e2) {
                e2.printStackTrace();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        Camera camera = this.u;
        if (camera == null) {
            return;
        }
        if (this.v == null) {
            this.v = camera.getParameters();
        }
        this.v.setPictureFormat(256);
        int iC = c(this);
        this.u.setDisplayOrientation(iC);
        this.v.set("rotation", iC);
        this.z = iC;
        Point bestPreview = CameraPreviewUtils.getBestPreview(this.v, new Point(this.o, this.p));
        this.x = bestPreview.x;
        this.y = bestPreview.y;
        ILivenessStrategy iLivenessStrategy = this.m;
        if (iLivenessStrategy != null) {
            iLivenessStrategy.setPreviewDegree(iC);
        }
        this.n.set(0, 0, this.y, this.x);
        this.v.setPreviewSize(this.x, this.y);
        this.u.setParameters(this.v);
        try {
            this.u.setPreviewDisplay(this.d);
            this.u.stopPreview();
            this.u.setErrorCallback(this);
            this.u.setPreviewCallback(this);
            this.u.startPreview();
        } catch (RuntimeException e4) {
            e4.printStackTrace();
            CameraUtils.releaseCamera(this.u);
            this.u = null;
        } catch (Exception e5) {
            e5.printStackTrace();
            CameraUtils.releaseCamera(this.u);
            this.u = null;
        }
    }

    public void o() {
        Camera camera = this.u;
        try {
            if (camera != null) {
                try {
                    try {
                        camera.setErrorCallback(null);
                        this.u.setPreviewCallback(null);
                        this.u.stopPreview();
                    } catch (RuntimeException e2) {
                        e2.printStackTrace();
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
            SurfaceHolder surfaceHolder = this.d;
            if (surfaceHolder != null) {
                surfaceHolder.removeCallback(this);
            }
            if (this.m != null) {
                this.m = null;
            }
        } finally {
            CameraUtils.releaseCamera(this.u);
            this.u = null;
        }
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(128);
        setContentView(R.layout.activity_face_liveness_v3100);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.o = displayMetrics.widthPixels;
        this.p = displayMetrics.heightPixels;
        FaceSDKResSettings.initializeResId();
        this.l = FaceSDKManager.getInstance().getFaceConfig();
        this.r = ((AudioManager) getSystemService("audio")).getStreamVolume(3) > 0 ? this.l.isSound : false;
        View viewFindViewById = findViewById(R.id.liveness_root_layout);
        this.f4080a = viewFindViewById;
        this.b = (FrameLayout) viewFindViewById.findViewById(R.id.liveness_surface_layout);
        this.B = (TextView) this.f4080a.findViewById(R.id.liveness_title);
        SurfaceView surfaceView = new SurfaceView(this);
        this.c = surfaceView;
        SurfaceHolder holder = surfaceView.getHolder();
        this.d = holder;
        holder.setSizeFromLayout();
        this.d.addCallback(this);
        this.d.setType(3);
        this.c.setLayoutParams(new FrameLayout.LayoutParams((int) (this.o * 0.75f), (int) (this.p * 0.75f), 17));
        this.b.addView(this.c);
        this.f4080a.findViewById(R.id.liveness_close).setOnClickListener(new a());
        this.j = (FaceDetectRoundView) this.f4080a.findViewById(R.id.liveness_face_round);
        this.f4081e = (TextView) this.f4080a.findViewById(R.id.liveness_close);
        ImageView imageView = (ImageView) this.f4080a.findViewById(R.id.liveness_sound);
        this.f = imageView;
        imageView.setImageResource(this.r ? R.mipmap.ic_enable_sound_ext : R.mipmap.ic_disable_sound_ext);
        this.f.setOnClickListener(new b());
        this.h = (TextView) this.f4080a.findViewById(R.id.liveness_top_tips);
        this.i = (TextView) this.f4080a.findViewById(R.id.liveness_bottom_tips);
        this.g = (ImageView) this.f4080a.findViewById(R.id.liveness_success_image);
        this.k = (LinearLayout) this.f4080a.findViewById(R.id.liveness_result_image_layout);
        HashMap<String, String> map = this.s;
        if (map != null) {
            map.clear();
        }
    }

    @Override // android.hardware.Camera.ErrorCallback
    public void onError(int i, Camera camera) {
    }

    @Override // com.baidu.idl.face.platform.ILivenessStrategyCallback
    public void onLivenessCompletion(FaceStatusEnum faceStatusEnum, String str, HashMap<String, String> map) throws Throwable {
        if (this.t) {
            return;
        }
        a(faceStatusEnum, str);
        if (faceStatusEnum == FaceStatusEnum.OK) {
            this.t = true;
            a(map);
        }
        Ast.getInstance().faceHit("liveness");
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        o();
    }

    @Override // android.hardware.Camera.PreviewCallback
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        if (this.t) {
            return;
        }
        if (this.m == null) {
            ILivenessStrategy livenessStrategyModule = FaceSDKManager.getInstance().getLivenessStrategyModule();
            this.m = livenessStrategyModule;
            livenessStrategyModule.setPreviewDegree(this.z);
            this.m.setLivenessStrategySoundEnable(this.r);
            this.m.setLivenessStrategyConfig(this.l.getLivenessTypeList(), this.n, FaceDetectRoundView.getPreviewDetectRect(this.o, this.y, this.x), this);
        }
        this.m.livenessStrategy(bArr);
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        setVolumeControlStream(3);
        this.A = VolumeUtils.registerVolumeReceiver(this, this);
        TextView textView = this.h;
        if (textView != null) {
            textView.setText(R.string.detect_face_in);
        }
        n();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        ILivenessStrategy iLivenessStrategy = this.m;
        if (iLivenessStrategy != null) {
            iLivenessStrategy.reset();
        }
        VolumeUtils.unRegisterVolumeReceiver(this, this.A);
        this.A = null;
        super.onStop();
        o();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        if (surfaceHolder.getSurface() == null) {
            return;
        }
        n();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }

    @Override // com.baidu.idl.face.platform.ui.utils.VolumeUtils.VolumeCallback
    public void volumeChanged() {
        try {
            AudioManager audioManager = (AudioManager) getSystemService("audio");
            if (audioManager != null) {
                this.r = audioManager.getStreamVolume(3) > 0;
                this.f.setImageResource(this.r ? R.mipmap.ic_enable_sound_ext : R.mipmap.ic_disable_sound_ext);
                if (this.m != null) {
                    this.m.setLivenessStrategySoundEnable(this.r);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public final void a(boolean z, String str) {
        if (z) {
            if (this.q == null) {
                Drawable drawable = getResources().getDrawable(R.mipmap.ic_warning);
                this.q = drawable;
                drawable.setBounds(0, 0, (int) (drawable.getMinimumWidth() * 0.7f), (int) (this.q.getMinimumHeight() * 0.7f));
                this.h.setCompoundDrawablePadding(15);
            }
            this.h.setBackgroundResource(R.drawable.bg_tips);
            this.h.setText(R.string.detect_standard);
            this.h.setCompoundDrawables(this.q, null, null, null);
            return;
        }
        this.h.setBackgroundResource(R.drawable.shape_face_toast);
        this.h.setCompoundDrawables(null, null, null, null);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.h.setText(str);
    }

    public final void a(HashMap<String, String> map) {
        Set<Map.Entry<String, String>> setEntrySet = map.entrySet();
        this.k.removeAllViews();
        Iterator<Map.Entry<String, String>> it = setEntrySet.iterator();
        while (it.hasNext()) {
            Bitmap bitmapE = e(it.next().getValue());
            ImageView imageView = new ImageView(this);
            imageView.setImageBitmap(bitmapE);
            this.k.addView(imageView, new LinearLayout.LayoutParams(300, 300));
        }
    }
}
