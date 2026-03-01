package net.ossrs.yasea;

import android.app.Activity;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.AttributeSet;
import com.seu.magicfilter.utils.MagicFilterType;
import com.uc.crashsdk.export.LogType;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import supwisdom.ud1;
import supwisdom.yd1;
import supwisdom.zd1;

/* JADX INFO: loaded from: classes3.dex */
public class SrsCameraView extends GLSurfaceView implements GLSurfaceView.Renderer {
    public CameraCallbacksHandler cameraCallbacksHandler;
    public int mCamId;
    public Camera mCamera;
    public ConcurrentLinkedQueue<IntBuffer> mGLIntBufferCache;
    public ByteBuffer mGLPreviewBuffer;
    public float mInputAspectRatio;
    public volatile boolean mIsEncoding;
    public boolean mIsTorchOn;
    public int mOESTextureId;
    public float mOutputAspectRatio;
    public PreviewCallback mPrevCb;
    public int mPreviewHeight;
    public int mPreviewOrientation;
    public int mPreviewRotation;
    public int mPreviewWidth;
    public float[] mProjectionMatrix;
    public int mSurfaceHeight;
    public float[] mSurfaceMatrix;
    public int mSurfaceWidth;
    public float[] mTransformMatrix;
    public ud1 magicFilter;
    public SurfaceTexture surfaceTexture;
    public Thread worker;
    public final Object writeLock;

    public interface CameraCallbacks {
        void onCameraParameters(Camera.Parameters parameters);
    }

    public static class CameraCallbacksHandler implements CameraCallbacks {
        @Override // net.ossrs.yasea.SrsCameraView.CameraCallbacks
        public void onCameraParameters(Camera.Parameters parameters) {
        }
    }

    public interface PreviewCallback {
        void onGetRgbaFrame(byte[] bArr, int i, int i2);
    }

    public SrsCameraView(Context context) {
        this(context, null);
    }

    private int[] adaptFpsRange(int i, List<int[]> list) {
        int iAbs;
        int i2 = i * 1000;
        int[] iArr = list.get(0);
        int iAbs2 = Math.abs(iArr[0] - i2) + Math.abs(iArr[1] - i2);
        for (int[] iArr2 : list) {
            if (iArr2[0] <= i2 && iArr2[1] >= i2 && (iAbs = Math.abs(iArr2[0] - i2) + Math.abs(iArr2[1] - i2)) < iAbs2) {
                iArr = iArr2;
                iAbs2 = iAbs;
            }
        }
        return iArr;
    }

    private Camera.Size adaptPreviewResolution(Camera.Size size) {
        float f = size.width / size.height;
        float f2 = 100.0f;
        Camera.Size size2 = null;
        for (Camera.Size size3 : this.mCamera.getParameters().getSupportedPreviewSizes()) {
            if (size3.equals(size)) {
                return size3;
            }
            float fAbs = Math.abs((size3.width / size3.height) - f);
            if (fAbs < f2) {
                size2 = size3;
                f2 = fAbs;
            }
        }
        return size2;
    }

    private void deleteTextures() {
        if (this.mOESTextureId != -1) {
            queueEvent(new Runnable() { // from class: net.ossrs.yasea.SrsCameraView.3
                @Override // java.lang.Runnable
                public void run() {
                    GLES20.glDeleteTextures(1, new int[]{SrsCameraView.this.mOESTextureId}, 0);
                    SrsCameraView.this.mOESTextureId = -1;
                }
            });
        }
    }

    public void disableEncoding() {
        this.mIsEncoding = false;
        this.mGLIntBufferCache.clear();
        this.mGLPreviewBuffer.clear();
        Thread thread = this.worker;
        if (thread != null) {
            thread.interrupt();
            try {
                this.worker.join();
            } catch (InterruptedException e2) {
                e2.printStackTrace();
                this.worker.interrupt();
            }
            this.worker = null;
        }
    }

    public void enableEncoding() {
        Thread thread = new Thread(new Runnable() { // from class: net.ossrs.yasea.SrsCameraView.4
            @Override // java.lang.Runnable
            public void run() {
                while (!Thread.interrupted()) {
                    while (!SrsCameraView.this.mGLIntBufferCache.isEmpty()) {
                        SrsCameraView.this.mGLPreviewBuffer.asIntBuffer().put(((IntBuffer) SrsCameraView.this.mGLIntBufferCache.poll()).array());
                        SrsCameraView.this.mPrevCb.onGetRgbaFrame(SrsCameraView.this.mGLPreviewBuffer.array(), SrsCameraView.this.mPreviewWidth, SrsCameraView.this.mPreviewHeight);
                    }
                    synchronized (SrsCameraView.this.writeLock) {
                        try {
                            SrsCameraView.this.writeLock.wait(500L);
                        } catch (InterruptedException unused) {
                            SrsCameraView.this.worker.interrupt();
                        }
                    }
                }
            }
        });
        this.worker = thread;
        thread.start();
        this.mIsEncoding = true;
    }

    public Camera getCamera() {
        return this.mCamera;
    }

    public int getCameraId() {
        return this.mCamId;
    }

    public int getRotateDeg() {
        try {
            int rotation = ((Activity) getContext()).getWindowManager().getDefaultDisplay().getRotation();
            if (rotation == 0) {
                return 0;
            }
            if (rotation == 1) {
                return 90;
            }
            if (rotation != 2) {
                return rotation != 3 ? -1 : 270;
            }
            return 180;
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glClear(LogType.UNEXP_RESTART);
        this.surfaceTexture.updateTexImage();
        this.surfaceTexture.getTransformMatrix(this.mSurfaceMatrix);
        Matrix.multiplyMM(this.mTransformMatrix, 0, this.mSurfaceMatrix, 0, this.mProjectionMatrix, 0);
        this.magicFilter.a(this.mTransformMatrix);
        this.magicFilter.a(this.mOESTextureId);
        if (this.mIsEncoding) {
            this.mGLIntBufferCache.add(this.magicFilter.e());
            synchronized (this.writeLock) {
                this.writeLock.notifyAll();
            }
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        float f;
        float f2;
        GLES20.glViewport(0, 0, i, i2);
        this.mSurfaceWidth = i;
        this.mSurfaceHeight = i2;
        this.magicFilter.b(i, i2);
        this.magicFilter.c(this.mPreviewWidth, this.mPreviewHeight);
        if (i > i2) {
            f = i;
            f2 = i2;
        } else {
            f = i2;
            f2 = i;
        }
        float f3 = f / f2;
        this.mOutputAspectRatio = f3;
        float f4 = f3 / this.mInputAspectRatio;
        if (i > i2) {
            Matrix.orthoM(this.mProjectionMatrix, 0, -1.0f, 1.0f, -f4, f4, -1.0f, 1.0f);
        } else {
            Matrix.orthoM(this.mProjectionMatrix, 0, -f4, f4, -1.0f, 1.0f, -1.0f, 1.0f);
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        GLES20.glDisable(3024);
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        ud1 ud1Var = new ud1(MagicFilterType.NONE);
        this.magicFilter = ud1Var;
        ud1Var.a(getContext().getApplicationContext());
        this.magicFilter.c(this.mPreviewWidth, this.mPreviewHeight);
        this.mOESTextureId = yd1.a();
        SurfaceTexture surfaceTexture = new SurfaceTexture(this.mOESTextureId);
        this.surfaceTexture = surfaceTexture;
        surfaceTexture.setOnFrameAvailableListener(new SurfaceTexture.OnFrameAvailableListener() { // from class: net.ossrs.yasea.SrsCameraView.1
            @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
            public void onFrameAvailable(SurfaceTexture surfaceTexture2) {
                SrsCameraView.this.requestRender();
            }
        });
        Camera camera = this.mCamera;
        if (camera != null) {
            try {
                camera.setPreviewTexture(this.surfaceTexture);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public Camera openCamera() {
        if (this.mCamId < 0) {
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            int numberOfCameras = Camera.getNumberOfCameras();
            int i = 0;
            int i2 = -1;
            while (true) {
                if (i >= numberOfCameras) {
                    i = -1;
                    break;
                }
                Camera.getCameraInfo(i, cameraInfo);
                int i3 = cameraInfo.facing;
                if (i3 == 0) {
                    i2 = i;
                } else if (i3 == 1) {
                    break;
                }
                i++;
            }
            if (i != -1) {
                this.mCamId = i;
            } else if (i2 != -1) {
                this.mCamId = i2;
            } else {
                this.mCamId = 0;
            }
        }
        Camera cameraOpen = null;
        try {
            cameraOpen = Camera.open(this.mCamId);
            cameraOpen.setErrorCallback(new Camera.ErrorCallback() { // from class: net.ossrs.yasea.SrsCameraView.5
                @Override // android.hardware.Camera.ErrorCallback
                public void onError(int i4, Camera camera) {
                    SrsCameraView.this.stopCamera();
                }
            });
            return cameraOpen;
        } catch (Exception e2) {
            e2.printStackTrace();
            return cameraOpen;
        }
    }

    public void setCameraCallbacksHandler(CameraCallbacksHandler cameraCallbacksHandler) {
        this.cameraCallbacksHandler = cameraCallbacksHandler;
    }

    public void setCameraId(int i) {
        stopTorch();
        this.mCamId = i;
        setPreviewOrientation(this.mPreviewOrientation);
    }

    public boolean setFilter(final MagicFilterType magicFilterType, final int i) {
        if (this.mCamera == null) {
            return false;
        }
        queueEvent(new Runnable() { // from class: net.ossrs.yasea.SrsCameraView.2
            @Override // java.lang.Runnable
            public void run() {
                if (SrsCameraView.this.magicFilter != null) {
                    SrsCameraView.this.magicFilter.b();
                }
                SrsCameraView.this.magicFilter = zd1.a(magicFilterType, i);
                if (SrsCameraView.this.magicFilter != null) {
                    SrsCameraView.this.magicFilter.a(SrsCameraView.this.getContext().getApplicationContext());
                    SrsCameraView.this.magicFilter.c(SrsCameraView.this.mPreviewWidth, SrsCameraView.this.mPreviewHeight);
                    SrsCameraView.this.magicFilter.b(SrsCameraView.this.mSurfaceWidth, SrsCameraView.this.mSurfaceHeight);
                }
            }
        });
        requestRender();
        return true;
    }

    public void setPreviewCallback(PreviewCallback previewCallback) {
        this.mPrevCb = previewCallback;
    }

    public void setPreviewOrientation(int i) {
        this.mPreviewOrientation = i;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(this.mCamId, cameraInfo);
        int rotateDeg = getRotateDeg();
        if (i == 1) {
            if (cameraInfo.facing == 1) {
                int i2 = cameraInfo.orientation % 360;
                this.mPreviewRotation = i2;
                this.mPreviewRotation = (360 - i2) % 360;
            } else {
                this.mPreviewRotation = (cameraInfo.orientation + 360) % 360;
            }
        } else if (i == 2) {
            if (cameraInfo.facing == 1) {
                int i3 = (cameraInfo.orientation - 90) % 360;
                this.mPreviewRotation = i3;
                this.mPreviewRotation = (360 - i3) % 360;
            } else {
                this.mPreviewRotation = (cameraInfo.orientation + 90) % 360;
            }
        }
        if (rotateDeg > 0) {
            this.mPreviewRotation %= rotateDeg;
        }
    }

    public int[] setPreviewResolution(int i, int i2) {
        this.mCamera = openCamera();
        this.mPreviewWidth = i;
        this.mPreviewHeight = i2;
        Camera camera = this.mCamera;
        camera.getClass();
        Camera.Size sizeAdaptPreviewResolution = adaptPreviewResolution(new Camera.Size(camera, i, i2));
        if (sizeAdaptPreviewResolution != null) {
            this.mPreviewWidth = sizeAdaptPreviewResolution.width;
            this.mPreviewHeight = sizeAdaptPreviewResolution.height;
        }
        getHolder().setFixedSize(this.mPreviewWidth, this.mPreviewHeight);
        this.mCamera.getParameters().setPreviewSize(this.mPreviewWidth, this.mPreviewHeight);
        this.mGLPreviewBuffer = ByteBuffer.allocate(this.mPreviewWidth * this.mPreviewHeight * 4);
        int i3 = this.mPreviewWidth;
        int i4 = this.mPreviewHeight;
        this.mInputAspectRatio = i3 > i4 ? i3 / i4 : i4 / i3;
        return new int[]{this.mPreviewWidth, this.mPreviewHeight};
    }

    public boolean startCamera() {
        if (this.mCamera == null) {
            Camera cameraOpenCamera = openCamera();
            this.mCamera = cameraOpenCamera;
            if (cameraOpenCamera == null) {
                return false;
            }
        }
        Camera.Parameters parameters = this.mCamera.getParameters();
        parameters.setPreviewSize(this.mPreviewWidth, this.mPreviewHeight);
        int[] iArrAdaptFpsRange = adaptFpsRange(24, parameters.getSupportedPreviewFpsRange());
        parameters.setPreviewFpsRange(iArrAdaptFpsRange[0], iArrAdaptFpsRange[1]);
        parameters.setPreviewFormat(17);
        parameters.setFlashMode("off");
        parameters.setWhiteBalance("auto");
        parameters.setSceneMode("auto");
        parameters.setRecordingHint(true);
        List<String> supportedFocusModes = parameters.getSupportedFocusModes();
        if (supportedFocusModes != null && !supportedFocusModes.isEmpty()) {
            if (supportedFocusModes.contains("continuous-picture")) {
                parameters.setFocusMode("continuous-picture");
            } else if (supportedFocusModes.contains("auto")) {
                parameters.setFocusMode("auto");
                this.mCamera.autoFocus(null);
            } else {
                parameters.setFocusMode(supportedFocusModes.get(0));
            }
        }
        List<String> supportedFlashModes = parameters.getSupportedFlashModes();
        if (supportedFlashModes != null && !supportedFlashModes.isEmpty()) {
            if (!supportedFlashModes.contains("torch")) {
                parameters.setFlashMode(supportedFlashModes.get(0));
            } else if (this.mIsTorchOn) {
                parameters.setFlashMode("torch");
            }
        }
        this.cameraCallbacksHandler.onCameraParameters(parameters);
        this.mCamera.setParameters(parameters);
        this.mCamera.setDisplayOrientation(this.mPreviewRotation);
        try {
            this.mCamera.setPreviewTexture(this.surfaceTexture);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        this.mCamera.startPreview();
        return true;
    }

    public boolean startTorch() {
        Camera.Parameters parameters;
        List<String> supportedFlashModes;
        Camera camera = this.mCamera;
        if (camera == null || (supportedFlashModes = (parameters = camera.getParameters()).getSupportedFlashModes()) == null || supportedFlashModes.isEmpty() || !supportedFlashModes.contains("torch")) {
            return false;
        }
        parameters.setFlashMode("torch");
        this.mCamera.setParameters(parameters);
        return true;
    }

    public void stopCamera() {
        disableEncoding();
        stopTorch();
        Camera camera = this.mCamera;
        if (camera != null) {
            camera.setPreviewCallback(null);
            this.mCamera.stopPreview();
            this.mCamera.release();
            this.mCamera = null;
        }
    }

    public void stopTorch() {
        Camera camera = this.mCamera;
        if (camera != null) {
            try {
                Camera.Parameters parameters = camera.getParameters();
                parameters.setFlashMode("off");
                this.mCamera.setParameters(parameters);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public SrsCameraView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mOESTextureId = -1;
        this.mIsTorchOn = false;
        this.mProjectionMatrix = new float[16];
        this.mSurfaceMatrix = new float[16];
        this.mTransformMatrix = new float[16];
        this.mCamId = -1;
        this.mPreviewRotation = 90;
        this.mPreviewOrientation = 1;
        this.writeLock = new Object();
        this.mGLIntBufferCache = new ConcurrentLinkedQueue<>();
        this.cameraCallbacksHandler = new CameraCallbacksHandler();
        setEGLContextClientVersion(2);
        setRenderer(this);
        setRenderMode(0);
    }

    public void setPreviewCallback(Camera.PreviewCallback previewCallback) {
        this.mCamera.setPreviewCallback(previewCallback);
    }
}
