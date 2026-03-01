package com.sangfor.sdk.base;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class SFException extends RuntimeException {
    public static final String ERROR_FLAGS_SUPPORT_MANAGE_POLICY = "FLAGS_SUPPORT_MANAGE_POLICY没有设置不能使用此接口";
    public static final String ERROR_INVOKE_SCENE_NO_PRIMARY_AUTH = "调用时机错误，需完成主认证后才能调用！";
    public static final String ERROR_MODE = "模式错误! 当前模式不能调用此API";
    public static final String ERROR_NATVIE_FAIL = "Native方法执行失败! 可能原因\n1.内存不够 2.参数不合法";
    public static final String ERROR_NATVIE_NULL = "native引用为空! 可能原因\n1.您未初始化(请初始化)\n2.初始化失败(检查初始化时传入参数是否正确)";
    public static final String ERROR_PARAM_AUTH_PATH = "认证路径不能为空！";
    public static final String ERROR_PARAM_CERT_PATH = "证书路径不能为空！";
    public static final String ERROR_PARAM_CODE_IS_EMPTY = "code不能为空！";
    public static final String ERROR_PARAM_CONTEXT_IS_EMPTY = "context参数不能为空！";
    public static final String ERROR_PARAM_INIT_FLAGS = "参数错误! flags参数不在指定的范围类";
    public static final String ERROR_PARAM_IS_EMPRTY = "参数不能为空！";
    public static final String ERROR_PARAM_IS_NOT_JSON = "参数不是json字符串";
    public static final String ERROR_PARAM_KEY = "key不能为空！";
    public static final String ERROR_PARAM_LISTENER_IS_EMPTY = "listener参数不能为空！";
    public static final String ERROR_PARAM_PASSWORD = "passWord不能为空！";
    public static final String ERROR_PARAM_SECONDARY_AUTH = "参数错误! 可能原因\u30001.认证类型非法";
    public static final String ERROR_PARAM_URL = "url不合法！";
    public static final String ERROR_PARAM_USERNAME = "用户名不能为空！";
    public static final String ERROR_PARAM_VALUE = "value不能为空！";
    public static final String ERROR_PARAM_VALUE_IS_NOT_JSON = "value不是json字符串";
    public int mErrorCode;

    public SFException(String str) {
        super(str);
        this.mErrorCode = -1;
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public SFException(int i, String str) {
        super(str);
        this.mErrorCode = -1;
        this.mErrorCode = i;
    }
}
