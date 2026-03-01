package com.synjones.mobilegroup.libofflinecodesdk.beans;

/* JADX INFO: loaded from: classes2.dex */
public enum ExternalCodeResultEnum {
    ERROR_NO("0", "请求成功"),
    ERROR_NOPERMISSIONOF_API("-1", "API未授权"),
    ERROR_SDKINIT_FAIL("-2", "SDK初始化失败"),
    ERROR_SDKINIT_FAIL_NONET("-3", "SDK初始化失败,无网络"),
    ERROR_SDKINIT_FAIL_DATA_NULL("-4", "SDK初始化失败,获取配置信息接口数据为空"),
    ERROR_SDKINIT_FAIL_DATA_ERROR("-5", "SDK初始化失败,获取配置信息接口数据异常"),
    ERROR_SDKINIT_FAIL_GERSDK_DATA_NULL("-6", "SDK初始化失败,获取授信包名列表接口数据为空"),
    ERROR_SDKINIT_FAIL_GERSDK_DATA_ERROR("-7", "SDK初始化失败,获取授信包名列表接口数据异常"),
    ERROR_SDKINIT_FAIL_SOFILE_ERROR("-8", "SDK初始化失败,SO本地化路径初始化失败"),
    ERROR_SDKINIT_FAIL_SETPACKAGENAME_JNI_ERROR("-9", "SDK初始化失败,授信包名列表传给SO库异常"),
    ERROR_PAYLIST_FAIL_NONET("-10", "获取支付列表数据失败,无网络"),
    ERROR_PAYLIST_FAIL_GET_ERROR("-11", "获取支付列表数据失败,接口数据异常"),
    ERROR_PAYLIST_FAIL_GET_NULL("-12", "获取支付列表数据失败,接口数据为空"),
    ERROR_PAYLIST_FAIL_GET_PARAM_ERROR("-13", "获取支付列表数据失败,脱机参数接口数据异常"),
    ERROR_GETONLINE_NONET("-14", "获取联机码失败,无网络"),
    ERROR_GETONLINE_NOPARM_FAIL("-15", "获取联机码失败,无支付列表数据或无支付列表中每一项对应的脱机参数数据"),
    ERROR_GETONLINE_TYPEID("-16", "获取联机码失败，TypeId有误"),
    ERROR_GETONLINE_GET_DATA_ERROR("-17", "获取联机码失败,接口数据异常"),
    ERROR_GETONLINE_GET_DATA_NULL("-18", "获取联机码失败,接口数据为空"),
    ERROR_GETOFFLINE_GET_DATA_NOPARAM("-19", "获取脱机码失败,无支付列表数据或无支付列表中每一项对应的脱机参数数据"),
    ERROR_GETOFFLINE_TYPEID("-20", "获取脱机码失败，TypeId有误"),
    ERROR_GETOFFLINE_GET_DATA_SO_SUCCESS("-21", "获取脱机码失败,脱机参数上传so库成功"),
    ERROR_GETOFFLINE_GET_DATA_SO_VOUCHER("-22", "获取脱机码失败,脱机参数上传so库无凭证"),
    ERROR_GETOFFLINE_GET_DATA_SO_FAIL("-23", "获取脱机码失败,脱机参数上传so库失败"),
    ERROR_GETOFFLINE_GET_DATA_JNI_FAIL("-24", "获取脱机码失败,JNI获取脱机码失败"),
    ERROR_GETCOMPOUND_LINE_GET_DATA_NOPARAM("-25", "获取复合码失败,无支付列表数据或无支付列表中每一项对应的脱机参数数据"),
    ERROR_GETCOMPOUND_LINE_TYPEID("-26", "获取复合码失败,TypeId有误"),
    ERROR_GETCOMPOUND_LINE_DATA_ERROR("-27", "获取复合码失败,接口数据异常"),
    ERROR_GETCOMPOUND_LINE_DATA_NULL("-28", "获取复合码失败,接口数据为空"),
    ERROR_SOCKET_CONNET_CLOSED("-29", "socket连接关闭"),
    ERROR_SOCKET_CONNET_FAILED("-30", "socket连接异常"),
    ERROR_SOCKET_RECEIVE_DATA("-31", "socket接收数据异常"),
    ERROR_GETPAYLIST_FAIL("-37", "未知异常错误");

    public String code;
    public String msg;

    ExternalCodeResultEnum(String str, String str2) {
        this.code = str;
        this.msg = str2;
    }
}
