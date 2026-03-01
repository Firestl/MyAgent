package com.g.gysdk;

/* JADX INFO: loaded from: classes.dex */
public enum GyErrorCode {
    SUCCESS("gysdk success!", 0),
    SDK_NOT_INITED("gysdk没有初始化!", -10000),
    SDK_INIT_ERROR("gysdk初始化失败!", -10001),
    OPERATE_TIMEOUT("gysdk请求超时!", -10003),
    OPERATE_DOING("gysdk上一个请求正在进行中，请稍后再试!", -10006),
    UNKNOWN_ERROR("gysdk其他错误，具体见msg!", -10009),
    PARAM_ERROR("gysdk传入参数错误!", -20100),
    APPID_EMPTY("gysdk appid为空!", -20101),
    APPID_INVALID("gysdk appid或者签名无效!", -20102),
    INVALID_PRELOGIN("gysdk预登录无效!", -20104),
    NO_NET("gysdk无网络可用!", -20200),
    NO_SIM_CARD("没有电话卡!", -20201),
    NO_MOBILE_DATA("未开启蜂窝网络!", -20202),
    UNSUPPORT_OPERATOR("不支持的运营商!", -20203),
    SHOW_LOGIN_ERROR("启动登录页失败!", -20304),
    GET_OPERATOR_APPID_ERROR("获取运营商APPID失败!", -20500),
    SERVER_RETURN_ERROR("服务器返回错误，具体见msg!", -30001),
    OPERATOR_RETURN_ERROR("运营商返回错误，具体见msg!", -40001),
    LOGIN_PAGE_DISMISSED("退出登录页面!", -20301),
    SWITCH_LOGIN_PAGE("切换登录!", -20303),
    AUTH_PAGE_ERROR("授权页面加载异常!", -20304),
    PRIVACY_NOT_CHECKED("协议未同意!", -20305),
    UI_NOT_COMPLIANCED("界面不合规!", -20306),
    ON_CLICK_EXCEPTION("loginOnClickListener异常!", -20307);

    public String name;
    public int value;

    GyErrorCode(String str, int i) {
        this.name = str;
        this.value = i;
    }
}
