package com.g.gysdk.a;

import android.text.TextUtils;
import com.getui.gtc.server.ServerManager;
import com.xiaomi.mipush.sdk.Constants;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ae {
    public static String a(String str) {
        try {
            return ServerManager.getServer(str);
        } catch (Throwable th) {
            ak.e(th);
            return "";
        }
    }

    public static void a(aq aqVar) {
        try {
            HashMap map = new HashMap();
            String strA = aqVar.a("GY_CS", "");
            map.put("gy.cs", Arrays.asList("https://c-gy.getui.net,https://c-gy.gepush.com".split(",")));
            if (!TextUtils.isEmpty(strA)) {
                map.put("gy.cs", Arrays.asList(strA.split(",")));
            }
            String strA2 = aqVar.a("GY_BS", "");
            map.put("gy.bs", Arrays.asList("https://b-gy.getui.net,https://b-gy.gepush.com".split(",")));
            if (!TextUtils.isEmpty(strA2)) {
                map.put("gy.bs", Arrays.asList(strA2.split(",")));
            }
            String strA3 = aqVar.a("GY_AS", "");
            map.put("gy.as", Arrays.asList("https://gy.getui.net,https://gy.gepush.com".split(",")));
            if (!TextUtils.isEmpty(strA3)) {
                map.put("gy.as", Arrays.asList(strA3.split(",")));
            }
            ServerManager.addBuildInServerMap(map);
            List<String> buildInServers = ServerManager.getBuildInServers("gy.cs");
            StringBuilder sb = new StringBuilder();
            sb.append("getBuildInServers ");
            sb.append("gy.cs");
            sb.append(Constants.COLON_SEPARATOR);
            String string = com.igexin.push.core.b.m;
            sb.append(buildInServers != null ? buildInServers.toString() : com.igexin.push.core.b.m);
            ak.a(sb.toString());
            List<String> buildInServers2 = ServerManager.getBuildInServers("gy.bs");
            StringBuilder sb2 = new StringBuilder();
            sb2.append("getBuildInServers ");
            sb2.append("gy.bs");
            sb2.append(Constants.COLON_SEPARATOR);
            sb2.append(buildInServers2 != null ? buildInServers2.toString() : com.igexin.push.core.b.m);
            ak.a(sb2.toString());
            List<String> buildInServers3 = ServerManager.getBuildInServers("gy.as");
            StringBuilder sb3 = new StringBuilder();
            sb3.append("getBuildInServers ");
            sb3.append("gy.as");
            sb3.append(Constants.COLON_SEPARATOR);
            if (buildInServers3 != null) {
                string = buildInServers3.toString();
            }
            sb3.append(string);
            ak.a(sb3.toString());
            ServerManager.updateConfigServerMap();
        } catch (Throwable th) {
            ak.e(th);
        }
    }

    public static boolean a(String str, String str2) {
        try {
            return ServerManager.switchServer(str, str2);
        } catch (Throwable th) {
            ak.e(th);
            return false;
        }
    }

    public static void b(String str, String str2) {
        try {
            ServerManager.confirmServer(str, str2);
        } catch (Throwable th) {
            ak.e(th);
        }
    }
}
