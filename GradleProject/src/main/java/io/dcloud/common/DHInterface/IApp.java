package io.dcloud.common.DHInterface;

import android.app.Activity;
import android.content.Intent;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.util.AppStatusBarManager;
import java.io.InputStream;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public interface IApp extends IAppInfo {
    public static final byte ABS_PRIVATE_DOC_DIR = 1;
    public static final byte ABS_PRIVATE_WWW_DIR = 0;
    public static final byte ABS_PRIVATE_WWW_DIR_APP_MODE = -1;
    public static final byte ABS_PUBLIC_DOCUMENTS_DIR = 2;
    public static final byte ABS_PUBLIC_DOWNLOADS_DIR = 3;
    public static final byte APP_QUIT_DEF = 1;
    public static final byte APP_QUIT_IMMEDIATELY = 2;
    public static final byte APP_RUNNING_MODE = 1;
    public static final String AUTHORITY_AUTHORIZED = "authorized";
    public static final String AUTHORITY_DENIED = "denied";
    public static final String AUTHORITY_UNDETERMINED = "undetermined";
    public static final byte FS_RUNNING_MODE = 0;
    public static final byte STATUS_ACTIVE = 3;
    public static final byte STATUS_UN_ACTIVIE = 2;
    public static final byte STATUS_UN_OFFLINE_FAIL = 4;
    public static final byte STATUS_UN_RUNNING = 1;

    public interface ConfigProperty {
        public static final String CONFIG_ADAPTATION = "adaptation";
        public static final String CONFIG_ADDITIONAL_HTTPHEADERS = "additionalHttpHeaders";
        public static final String CONFIG_ALGORITHM = "algorithm";
        public static final String CONFIG_AUTHORITY = "authority";
        public static final String CONFIG_AUTOCLOSE = "autoclose";
        public static final String CONFIG_AUTOCLOSE_W2A = "autoclose_w2a";
        public static final String CONFIG_BACK_BUTTON_AUTO_CONTROL = "backButtonAutoControl";
        public static final String CONFIG_BASEURL = "baseUrl";
        public static final String CONFIG_CACHE = "cache";
        public static final String CONFIG_CERS = "cers";
        public static final String CONFIG_COMPETENT = "competent";
        public static final String CONFIG_CONCATENATE = "concatenate";
        public static final String CONFIG_CONFUSION = "confusion";
        public static final String CONFIG_COVER = "cover";
        public static final String CONFIG_CRASH = "crash";
        public static final String CONFIG_DELAY = "delay";
        public static final String CONFIG_DELAY_W2A = "delay_w2a";
        public static final String CONFIG_DESCRIPTION = "description";
        public static final String CONFIG_DEVELOPER = "developer";
        public static final String CONFIG_DEVELOPER_EMAIL = "email";
        public static final String CONFIG_DEVELOPER_NAME = "name";
        public static final String CONFIG_DEVELOPER_URL = "url";
        public static final String CONFIG_ERROR_PAGE = "error";
        public static final String CONFIG_ERROR_PAGE_URL = "url";
        public static final String CONFIG_EVENT = "event";
        public static final String CONFIG_FEATURE = "feature";
        public static final String CONFIG_FEATURES = "features";
        public static final String CONFIG_FULLSCREEN = "fullscreen";
        public static final String CONFIG_GEOLOCATION = "geolocation";
        public static final String CONFIG_H5PLUS = "h5plus";
        public static final String CONFIG_ID = "id";
        public static final String CONFIG_INJECTION = "injection";
        public static final String CONFIG_JSERROR = "jserror";
        public static final String CONFIG_KEY = "key";
        public static final String CONFIG_LANGUAGE = "language";
        public static final String CONFIG_LAUNCHWEBVIEW = "launchwebview";
        public static final String CONFIG_LAUNCH_PATH = "launch_path";
        public static final String CONFIG_LAUNCH_PATH_W2A = "launch_path_w2a";
        public static final String CONFIG_LGEOLOCATION = "L_geolocation";
        public static final String CONFIG_LICENSE = "license";
        public static final String CONFIG_LICENSE_DESCRIPTION = "description";
        public static final String CONFIG_LICENSE_URL = "url";
        public static final String CONFIG_LOADED_TIME = "loadedTime";
        public static final String CONFIG_LPLUSERQUIRE = "L_plusrequire";
        public static final String CONFIG_MODULE = "module";
        public static final String CONFIG_NAME = "name";
        public static final String CONFIG_NAVIGATIONBAR = "navigationbar";
        public static final String CONFIG_OVERRIDEURL = "overrideurl";
        public static final String CONFIG_OVERRIDE_RESOURCE = "overrideresource";
        public static final String CONFIG_PERMISSIONS = "permissions";
        public static final String CONFIG_PLUS = "plus";
        public static final String CONFIG_PLUSREQUIRE = "plusrequire";
        public static final String CONFIG_RAM_CACHE_MODE = "ramcachemode";
        public static final String CONFIG_REPLACE_WEB_API = "replacewebapi";
        public static final String CONFIG_RESOURCES = "resources";
        public static final String CONFIG_RUNMODE = "runmode";
        public static final String CONFIG_RUNMODE_LIBERATE = "liberate";
        public static final String CONFIG_RUNMODE_NORMAL = "normal";
        public static final String CONFIG_SECONDWEBVIEW = "secondwebview";
        public static final String CONFIG_SECONDWEBVIEW_MODE = "mode";
        public static final String CONFIG_SERVICE = "service";
        public static final String CONFIG_SERVICES = "services";
        public static final String CONFIG_SGEOLOCATION = "S_geolocation";
        public static final String CONFIG_SHORTCUT = "shortcut";
        public static final String CONFIG_SHORTCUTQUIT = "shortcutQuit";
        public static final String CONFIG_SPLASHSCREEN = "splashscreen";
        public static final String CONFIG_SPLUSERQUIRE = "S_pluserquire";
        public static final String CONFIG_SRC = "src";
        public static final String CONFIG_SSL = "ssl";
        public static final String CONFIG_STREAM = "stream";
        public static final String CONFIG_TARGET = "target";
        public static final String CONFIG_TIMEOUT = "timeout";
        public static final String CONFIG_TITLE_N_VIEW = "titleNView";
        public static final String CONFIG_UNIAPP_CONTROL = "control";
        public static final String CONFIG_UNTRUSTEDCA = "untrustedca";
        public static final String CONFIG_USER_AGENT = "useragent";
        public static final String CONFIG_USER_AGENT_ANDROID = "useragent_android";
        public static final String CONFIG_USE_ENCRYPTION = "use_encryption";
        public static final String CONFIG_USE_V3_ENCRYPTION = "use_v3_encryption";
        public static final String CONFIG_VALUE = "value";
        public static final String CONFIG_VERSION = "version";
        public static final String CONFIG_VERSION_CODE = "code";
        public static final String CONFIG_VERSION_NAME = "name";
        public static final String CONFIG_WAITING = "waiting";
        public static final String CONFIG_funSetUA = "funSetUA";
        public static final String UNI_NVUE_DATA = "uni_nvue_data";
        public static final String UNI_RESTART_TO_DIRECT = "uni_restart_to_direct";

        public enum ThridInfo {
            OverrideUrlJsonData,
            OverrideResourceJsonData,
            SecondWebviewJsonData,
            LaunchWebviewJsonData,
            TitleNViewJsonData,
            DirectPageJsonData,
            URDJsonData,
            SitemapJsonData,
            Tabbar
        }
    }

    public interface IAppStatusListener {
        void onPause(IApp iApp, IApp iApp2);

        void onStart();

        boolean onStop();

        String onStoped(boolean z, String str);
    }

    public interface Name {
        public static final int APP = 1;
        public static final int STORAGE = 2;
        public static final int STREAM = 3;
        public static final int UPDATE = 4;
    }

    void addAllFeaturePermission();

    void addFeaturePermission(String str);

    void applyMani();

    void applySmartUpdate();

    boolean callSysEventListener(ISysEventListener.SysEventType sysEventType, Object obj);

    boolean checkIsCustomPath();

    void checkOrLoadlaunchWebview();

    boolean checkPrivateDir(String str);

    String checkPrivateDirAndCopy2Temp(String str);

    boolean checkSchemeWhite(String str);

    int checkSelfPermission(String str, String str2);

    boolean checkWhiteUrl(String str);

    void clearRuntimeArgs();

    String convert2AbsFullPath(String str);

    String convert2AbsFullPath(String str, String str2);

    String convert2LocalFullPath(String str, String str2);

    String convert2RelPath(String str);

    String convert2WebviewFullPath(String str, String str2);

    void deleteAppTemp();

    void diyStatusBarState();

    String forceShortCut();

    IConfusionMgr getConfusionMgr();

    String getDirectPage();

    IAppStatusListener getIAppStatusListener();

    String getOriginalDirectPage();

    String getPathByType(byte b);

    String getPopGesture();

    int getQuitModel();

    boolean isOnAppRunningMode();

    boolean manifestBeParsed();

    boolean needRefreshApp();

    boolean needReload();

    String obtainAdaptationJs();

    String obtainAppDataPath();

    String obtainAppDocPath();

    String obtainAppId();

    String obtainAppInfo();

    String obtainAppLog();

    String obtainAppName();

    byte obtainAppStatus();

    String obtainAppTempPath();

    String obtainAppVersionName();

    String obtainAppWebCachePath();

    String obtainAuthority(String str);

    String obtainConfigProperty(String str);

    IWebviewStateListener obtainLaunchPageStateListener();

    Object obtainMgrData(IMgr.MgrType mgrType, int i, Object[] objArr);

    String obtainOriginalAppId();

    InputStream obtainResInStream(String str);

    InputStream obtainResInStream(String str, String str2);

    byte obtainRunningAppMode();

    String obtainRuntimeArgs(boolean z);

    AppStatusBarManager obtainStatusBarMgr();

    JSONObject obtainThridInfo(ConfigProperty.ThridInfo thridInfo);

    String obtainVersionSitemap();

    Intent obtainWebAppIntent();

    String obtainWebviewBaseUrl();

    void onSplashClosed();

    void registerSysEventListener(ISysEventListener iSysEventListener, ISysEventListener.SysEventType sysEventType);

    void requestPermissions(String[] strArr, int i);

    void setAppDataPath(String str);

    void setAppDocPath(String str);

    void setConfigProperty(String str, String str2);

    void setDirectPage(String str);

    void setHideNavBarState(boolean z);

    void setIAppStatusListener(IAppStatusListener iAppStatusListener);

    void setLaunchPageStateListener(IWebviewStateListener iWebviewStateListener);

    void setNeedRefreshApp(boolean z);

    void setQuitModel(int i);

    void setRuntimeArgs(String str);

    void setStatus(byte b);

    void setWebAppActivity(Activity activity);

    void setWebAppIntent(Intent intent);

    String shortcutQuit();

    void showSplash();

    boolean startFromShortCut();

    void unregisterSysEventListener(ISysEventListener iSysEventListener, ISysEventListener.SysEventType sysEventType);

    void updateDirectPage(String str);
}
