package supwisdom;

/* JADX INFO: loaded from: classes2.dex */
public interface dp1 {
    @jp1("/berserker-app/ykt/tsm/codebarPayinfo")
    gs1 a();

    @jp1("/berserker-app/ykt/tsm/barcodePar")
    gs1 a(@lp1("payacc") String str, @lp1("paytype") String str2, @lp1("voucher") String str3);

    @jp1("/berserker-app/ykt/tsm/configSDK")
    gs1 a(@lp1("appId") String str, @lp1(com.heytap.mcssdk.a.a.l) String str2, @lp1("oldToken") String str3, @lp1("newToken") String str4);

    @jp1("/berserker-app/ykt/tsm/getSdkList")
    gs1 b();

    @jp1("/berserker-app/ykt/tsm/getBarCode")
    gs1 b(@lp1("payacc") String str, @lp1("paytype") String str2, @lp1("account") String str3);
}
