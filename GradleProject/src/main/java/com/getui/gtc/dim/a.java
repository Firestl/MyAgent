package com.getui.gtc.dim;

import android.text.TextUtils;
import com.alibaba.fastjson.parser.JSONLexer;
import com.efs.sdk.base.newsharedpreferences.SharedPreferencesNewImpl;
import com.getui.gtc.dim.b.b;
import com.getui.gtc.dim.b.c;
import com.getui.gtc.dim.b.d;
import com.getui.gtc.dim.b.g;
import com.getui.gtc.dim.e.b;
import com.taobao.weex.el.parse.Operators;
import com.umeng.analytics.pro.db;
import com.xiaomi.mipush.sdk.Constants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.bouncycastle.jcajce.provider.config.ProviderConfigurationPermission;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f2150a;
    public final d b;
    public final g c;
    public final Map<String, Object> d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Map<String, List<String>> f2151e;
    public final Map<String, Boolean> f;

    /* JADX INFO: renamed from: com.getui.gtc.dim.a$a, reason: collision with other inner class name */
    public static class C0036a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f2152a = new a(0);
    }

    public a() {
        this.d = new ConcurrentHashMap();
        this.f2151e = new HashMap();
        this.f = new HashMap();
        this.f2150a = c.a();
        this.b = d.a.f2161a;
        this.c = g.d();
        this.f2151e.put("dim-2-1-21-4", Arrays.asList("dim-2-1-21-5", "dim-2-1-21-3", "dim-2-1-21-1", "dim-2-1-21-2"));
    }

    public /* synthetic */ a(byte b) {
        this();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0239  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<java.lang.String> a(java.lang.String r16) {
        /*
            Method dump skipped, instruction units count: 1080
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.dim.a.a(java.lang.String):java.util.List");
    }

    private void a(String str, int i) {
        this.f.put(str, Boolean.valueOf(i != 0));
        b.a("dim use expired enable set: " + str + " : " + i);
    }

    private void c(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        this.f2151e.put(str, arrayList);
        for (String str3 : str2.split(",")) {
            arrayList.add(str3.trim().toLowerCase());
        }
        b.a("dim complex policy set: " + str + " : " + str2);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0345  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0347 A[Catch: all -> 0x05b1, TryCatch #1 {, blocks: (B:32:0x0098, B:45:0x00d0, B:47:0x00d8, B:48:0x00f3, B:50:0x00f5, B:52:0x00fb, B:54:0x0103, B:55:0x0127, B:57:0x0129, B:95:0x0207, B:97:0x0213, B:100:0x021f, B:102:0x0229, B:104:0x0231, B:109:0x023c, B:138:0x0334, B:165:0x0390, B:167:0x03c6, B:169:0x03d0, B:177:0x03e4, B:178:0x03ef, B:179:0x03f3, B:181:0x0416, B:183:0x0420, B:184:0x042a, B:185:0x042f, B:232:0x04d5, B:236:0x04dd, B:238:0x04e3, B:241:0x0510, B:243:0x0518, B:244:0x053f, B:247:0x0543, B:249:0x054d, B:250:0x0574, B:187:0x0434, B:190:0x0440, B:193:0x044c, B:196:0x0458, B:199:0x0463, B:202:0x046e, B:205:0x0479, B:208:0x0484, B:211:0x048f, B:214:0x049a, B:217:0x04a4, B:220:0x04ae, B:223:0x04b8, B:226:0x04c2, B:229:0x04cc, B:252:0x0576, B:254:0x0580, B:255:0x05ad, B:256:0x05af, B:143:0x0347, B:150:0x0354, B:152:0x0358, B:154:0x035e, B:155:0x0367, B:157:0x036d, B:159:0x0373, B:161:0x0379, B:162:0x0383, B:164:0x0389, B:111:0x025e, B:113:0x0264, B:114:0x0284, B:116:0x0292, B:118:0x029e, B:121:0x02ad, B:123:0x02b8, B:130:0x0309, B:137:0x0329, B:133:0x0317, B:135:0x0323, B:126:0x02f7, B:128:0x0303, B:119:0x02a9, B:94:0x0204, B:35:0x00a2, B:37:0x00ac, B:39:0x00b2, B:41:0x00bc, B:43:0x00c7, B:42:0x00c1), top: B:267:0x0098 }] */
    /* JADX WARN: Removed duplicated region for block: B:145:0x034c  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x034e  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0369 A[PHI: r0
  0x0369: PHI (r0v12 com.getui.gtc.dim.b.e) = (r0v11 com.getui.gtc.dim.b.e), (r0v29 com.getui.gtc.dim.b.e), (r0v11 com.getui.gtc.dim.b.e) binds: [B:96:0x0211, B:155:0x0367, B:99:0x021d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0383 A[Catch: all -> 0x05b1, TryCatch #1 {, blocks: (B:32:0x0098, B:45:0x00d0, B:47:0x00d8, B:48:0x00f3, B:50:0x00f5, B:52:0x00fb, B:54:0x0103, B:55:0x0127, B:57:0x0129, B:95:0x0207, B:97:0x0213, B:100:0x021f, B:102:0x0229, B:104:0x0231, B:109:0x023c, B:138:0x0334, B:165:0x0390, B:167:0x03c6, B:169:0x03d0, B:177:0x03e4, B:178:0x03ef, B:179:0x03f3, B:181:0x0416, B:183:0x0420, B:184:0x042a, B:185:0x042f, B:232:0x04d5, B:236:0x04dd, B:238:0x04e3, B:241:0x0510, B:243:0x0518, B:244:0x053f, B:247:0x0543, B:249:0x054d, B:250:0x0574, B:187:0x0434, B:190:0x0440, B:193:0x044c, B:196:0x0458, B:199:0x0463, B:202:0x046e, B:205:0x0479, B:208:0x0484, B:211:0x048f, B:214:0x049a, B:217:0x04a4, B:220:0x04ae, B:223:0x04b8, B:226:0x04c2, B:229:0x04cc, B:252:0x0576, B:254:0x0580, B:255:0x05ad, B:256:0x05af, B:143:0x0347, B:150:0x0354, B:152:0x0358, B:154:0x035e, B:155:0x0367, B:157:0x036d, B:159:0x0373, B:161:0x0379, B:162:0x0383, B:164:0x0389, B:111:0x025e, B:113:0x0264, B:114:0x0284, B:116:0x0292, B:118:0x029e, B:121:0x02ad, B:123:0x02b8, B:130:0x0309, B:137:0x0329, B:133:0x0317, B:135:0x0323, B:126:0x02f7, B:128:0x0303, B:119:0x02a9, B:94:0x0204, B:35:0x00a2, B:37:0x00ac, B:39:0x00b2, B:41:0x00bc, B:43:0x00c7, B:42:0x00c1), top: B:267:0x0098 }] */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0389 A[Catch: all -> 0x05b1, TryCatch #1 {, blocks: (B:32:0x0098, B:45:0x00d0, B:47:0x00d8, B:48:0x00f3, B:50:0x00f5, B:52:0x00fb, B:54:0x0103, B:55:0x0127, B:57:0x0129, B:95:0x0207, B:97:0x0213, B:100:0x021f, B:102:0x0229, B:104:0x0231, B:109:0x023c, B:138:0x0334, B:165:0x0390, B:167:0x03c6, B:169:0x03d0, B:177:0x03e4, B:178:0x03ef, B:179:0x03f3, B:181:0x0416, B:183:0x0420, B:184:0x042a, B:185:0x042f, B:232:0x04d5, B:236:0x04dd, B:238:0x04e3, B:241:0x0510, B:243:0x0518, B:244:0x053f, B:247:0x0543, B:249:0x054d, B:250:0x0574, B:187:0x0434, B:190:0x0440, B:193:0x044c, B:196:0x0458, B:199:0x0463, B:202:0x046e, B:205:0x0479, B:208:0x0484, B:211:0x048f, B:214:0x049a, B:217:0x04a4, B:220:0x04ae, B:223:0x04b8, B:226:0x04c2, B:229:0x04cc, B:252:0x0576, B:254:0x0580, B:255:0x05ad, B:256:0x05af, B:143:0x0347, B:150:0x0354, B:152:0x0358, B:154:0x035e, B:155:0x0367, B:157:0x036d, B:159:0x0373, B:161:0x0379, B:162:0x0383, B:164:0x0389, B:111:0x025e, B:113:0x0264, B:114:0x0284, B:116:0x0292, B:118:0x029e, B:121:0x02ad, B:123:0x02b8, B:130:0x0309, B:137:0x0329, B:133:0x0317, B:135:0x0323, B:126:0x02f7, B:128:0x0303, B:119:0x02a9, B:94:0x0204, B:35:0x00a2, B:37:0x00ac, B:39:0x00b2, B:41:0x00bc, B:43:0x00c7, B:42:0x00c1), top: B:267:0x0098 }] */
    /* JADX WARN: Removed duplicated region for block: B:167:0x03c6 A[Catch: all -> 0x05b1, TryCatch #1 {, blocks: (B:32:0x0098, B:45:0x00d0, B:47:0x00d8, B:48:0x00f3, B:50:0x00f5, B:52:0x00fb, B:54:0x0103, B:55:0x0127, B:57:0x0129, B:95:0x0207, B:97:0x0213, B:100:0x021f, B:102:0x0229, B:104:0x0231, B:109:0x023c, B:138:0x0334, B:165:0x0390, B:167:0x03c6, B:169:0x03d0, B:177:0x03e4, B:178:0x03ef, B:179:0x03f3, B:181:0x0416, B:183:0x0420, B:184:0x042a, B:185:0x042f, B:232:0x04d5, B:236:0x04dd, B:238:0x04e3, B:241:0x0510, B:243:0x0518, B:244:0x053f, B:247:0x0543, B:249:0x054d, B:250:0x0574, B:187:0x0434, B:190:0x0440, B:193:0x044c, B:196:0x0458, B:199:0x0463, B:202:0x046e, B:205:0x0479, B:208:0x0484, B:211:0x048f, B:214:0x049a, B:217:0x04a4, B:220:0x04ae, B:223:0x04b8, B:226:0x04c2, B:229:0x04cc, B:252:0x0576, B:254:0x0580, B:255:0x05ad, B:256:0x05af, B:143:0x0347, B:150:0x0354, B:152:0x0358, B:154:0x035e, B:155:0x0367, B:157:0x036d, B:159:0x0373, B:161:0x0379, B:162:0x0383, B:164:0x0389, B:111:0x025e, B:113:0x0264, B:114:0x0284, B:116:0x0292, B:118:0x029e, B:121:0x02ad, B:123:0x02b8, B:130:0x0309, B:137:0x0329, B:133:0x0317, B:135:0x0323, B:126:0x02f7, B:128:0x0303, B:119:0x02a9, B:94:0x0204, B:35:0x00a2, B:37:0x00ac, B:39:0x00b2, B:41:0x00bc, B:43:0x00c7, B:42:0x00c1), top: B:267:0x0098 }] */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0416 A[Catch: all -> 0x05b1, TryCatch #1 {, blocks: (B:32:0x0098, B:45:0x00d0, B:47:0x00d8, B:48:0x00f3, B:50:0x00f5, B:52:0x00fb, B:54:0x0103, B:55:0x0127, B:57:0x0129, B:95:0x0207, B:97:0x0213, B:100:0x021f, B:102:0x0229, B:104:0x0231, B:109:0x023c, B:138:0x0334, B:165:0x0390, B:167:0x03c6, B:169:0x03d0, B:177:0x03e4, B:178:0x03ef, B:179:0x03f3, B:181:0x0416, B:183:0x0420, B:184:0x042a, B:185:0x042f, B:232:0x04d5, B:236:0x04dd, B:238:0x04e3, B:241:0x0510, B:243:0x0518, B:244:0x053f, B:247:0x0543, B:249:0x054d, B:250:0x0574, B:187:0x0434, B:190:0x0440, B:193:0x044c, B:196:0x0458, B:199:0x0463, B:202:0x046e, B:205:0x0479, B:208:0x0484, B:211:0x048f, B:214:0x049a, B:217:0x04a4, B:220:0x04ae, B:223:0x04b8, B:226:0x04c2, B:229:0x04cc, B:252:0x0576, B:254:0x0580, B:255:0x05ad, B:256:0x05af, B:143:0x0347, B:150:0x0354, B:152:0x0358, B:154:0x035e, B:155:0x0367, B:157:0x036d, B:159:0x0373, B:161:0x0379, B:162:0x0383, B:164:0x0389, B:111:0x025e, B:113:0x0264, B:114:0x0284, B:116:0x0292, B:118:0x029e, B:121:0x02ad, B:123:0x02b8, B:130:0x0309, B:137:0x0329, B:133:0x0317, B:135:0x0323, B:126:0x02f7, B:128:0x0303, B:119:0x02a9, B:94:0x0204, B:35:0x00a2, B:37:0x00ac, B:39:0x00b2, B:41:0x00bc, B:43:0x00c7, B:42:0x00c1), top: B:267:0x0098 }] */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0580 A[Catch: all -> 0x05b1, TryCatch #1 {, blocks: (B:32:0x0098, B:45:0x00d0, B:47:0x00d8, B:48:0x00f3, B:50:0x00f5, B:52:0x00fb, B:54:0x0103, B:55:0x0127, B:57:0x0129, B:95:0x0207, B:97:0x0213, B:100:0x021f, B:102:0x0229, B:104:0x0231, B:109:0x023c, B:138:0x0334, B:165:0x0390, B:167:0x03c6, B:169:0x03d0, B:177:0x03e4, B:178:0x03ef, B:179:0x03f3, B:181:0x0416, B:183:0x0420, B:184:0x042a, B:185:0x042f, B:232:0x04d5, B:236:0x04dd, B:238:0x04e3, B:241:0x0510, B:243:0x0518, B:244:0x053f, B:247:0x0543, B:249:0x054d, B:250:0x0574, B:187:0x0434, B:190:0x0440, B:193:0x044c, B:196:0x0458, B:199:0x0463, B:202:0x046e, B:205:0x0479, B:208:0x0484, B:211:0x048f, B:214:0x049a, B:217:0x04a4, B:220:0x04ae, B:223:0x04b8, B:226:0x04c2, B:229:0x04cc, B:252:0x0576, B:254:0x0580, B:255:0x05ad, B:256:0x05af, B:143:0x0347, B:150:0x0354, B:152:0x0358, B:154:0x035e, B:155:0x0367, B:157:0x036d, B:159:0x0373, B:161:0x0379, B:162:0x0383, B:164:0x0389, B:111:0x025e, B:113:0x0264, B:114:0x0284, B:116:0x0292, B:118:0x029e, B:121:0x02ad, B:123:0x02b8, B:130:0x0309, B:137:0x0329, B:133:0x0317, B:135:0x0323, B:126:0x02f7, B:128:0x0303, B:119:0x02a9, B:94:0x0204, B:35:0x00a2, B:37:0x00ac, B:39:0x00b2, B:41:0x00bc, B:43:0x00c7, B:42:0x00c1), top: B:267:0x0098 }] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0213 A[Catch: all -> 0x05b1, TryCatch #1 {, blocks: (B:32:0x0098, B:45:0x00d0, B:47:0x00d8, B:48:0x00f3, B:50:0x00f5, B:52:0x00fb, B:54:0x0103, B:55:0x0127, B:57:0x0129, B:95:0x0207, B:97:0x0213, B:100:0x021f, B:102:0x0229, B:104:0x0231, B:109:0x023c, B:138:0x0334, B:165:0x0390, B:167:0x03c6, B:169:0x03d0, B:177:0x03e4, B:178:0x03ef, B:179:0x03f3, B:181:0x0416, B:183:0x0420, B:184:0x042a, B:185:0x042f, B:232:0x04d5, B:236:0x04dd, B:238:0x04e3, B:241:0x0510, B:243:0x0518, B:244:0x053f, B:247:0x0543, B:249:0x054d, B:250:0x0574, B:187:0x0434, B:190:0x0440, B:193:0x044c, B:196:0x0458, B:199:0x0463, B:202:0x046e, B:205:0x0479, B:208:0x0484, B:211:0x048f, B:214:0x049a, B:217:0x04a4, B:220:0x04ae, B:223:0x04b8, B:226:0x04c2, B:229:0x04cc, B:252:0x0576, B:254:0x0580, B:255:0x05ad, B:256:0x05af, B:143:0x0347, B:150:0x0354, B:152:0x0358, B:154:0x035e, B:155:0x0367, B:157:0x036d, B:159:0x0373, B:161:0x0379, B:162:0x0383, B:164:0x0389, B:111:0x025e, B:113:0x0264, B:114:0x0284, B:116:0x0292, B:118:0x029e, B:121:0x02ad, B:123:0x02b8, B:130:0x0309, B:137:0x0329, B:133:0x0317, B:135:0x0323, B:126:0x02f7, B:128:0x0303, B:119:0x02a9, B:94:0x0204, B:35:0x00a2, B:37:0x00ac, B:39:0x00b2, B:41:0x00bc, B:43:0x00c7, B:42:0x00c1), top: B:267:0x0098 }] */
    /* JADX WARN: Type inference failed for: r20v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r20v1 */
    /* JADX WARN: Type inference failed for: r20v10 */
    /* JADX WARN: Type inference failed for: r20v2 */
    /* JADX WARN: Type inference failed for: r20v3 */
    /* JADX WARN: Type inference failed for: r20v4 */
    /* JADX WARN: Type inference failed for: r20v5 */
    /* JADX WARN: Type inference failed for: r20v9 */
    /* JADX WARN: Type inference failed for: r2v16, types: [com.getui.gtc.dim.b.h] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object a(com.getui.gtc.dim.DimRequest r19, boolean r20) {
        /*
            Method dump skipped, instruction units count: 1560
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.dim.a.a(com.getui.gtc.dim.DimRequest, boolean):java.lang.Object");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void a(String str, String str2) {
        byte b = -1;
        try {
            char c = 0;
            char c2 = 1;
            switch (str.hashCode()) {
                case 1673842650:
                    if (str.equals("dim-2-2-1-1")) {
                        b = 0;
                    }
                    break;
                case 1673843611:
                    if (str.equals("dim-2-2-2-1")) {
                        b = 1;
                    }
                    break;
                case 1673844572:
                    if (str.equals("dim-2-2-3-1")) {
                        b = 2;
                    }
                    break;
                case 1673845533:
                    if (str.equals("dim-2-2-4-1")) {
                        b = 3;
                    }
                    break;
            }
            if (b != 0) {
                if (b == 1) {
                    this.c.f(str2);
                    return;
                } else if (b == 2) {
                    this.c.a(Caller.valueOf(str2));
                    return;
                } else {
                    if (b != 3) {
                        return;
                    }
                    com.getui.gtc.dim.b.b.a().a(Long.parseLong(str2));
                    return;
                }
            }
            try {
                JSONObject jSONObject = new JSONObject(str2);
                this.c.c(str2);
                try {
                    String strOptString = jSONObject.optString("sdk.gtc.dim.pm.use_uid.policy");
                    if (!TextUtils.isEmpty(strOptString) && !"none".equals(strOptString)) {
                        for (String str3 : strOptString.split(",")) {
                            if (str3.contains(Constants.COLON_SEPARATOR)) {
                                String[] strArrSplit = str3.split(Constants.COLON_SEPARATOR);
                                int i = Integer.parseInt(strArrSplit[1]);
                                if (Operators.MUL.equals(strArrSplit[0])) {
                                    this.c.a("dim-2-2-0-1", i);
                                } else {
                                    this.c.a(strArrSplit[0], i);
                                }
                            }
                        }
                    }
                } catch (Throwable th) {
                    b.a(th);
                }
                try {
                    String strOptString2 = jSONObject.optString("sdk.gtc.dim.storage.valid.time");
                    if (!TextUtils.isEmpty(strOptString2)) {
                        String[] strArrSplit2 = strOptString2.split(",");
                        int length = strArrSplit2.length;
                        int i2 = 0;
                        while (i2 < length) {
                            String str4 = strArrSplit2[i2];
                            if (str4.contains(Constants.COLON_SEPARATOR)) {
                                String[] strArrSplit3 = str4.split(Constants.COLON_SEPARATOR);
                                List<String> listA = a(strArrSplit3[c]);
                                long j = Long.parseLong(strArrSplit3[c2]) * 1000;
                                Iterator<String> it = listA.iterator();
                                while (it.hasNext()) {
                                    this.b.a(it.next(), j);
                                }
                            } else {
                                this.b.a("dim-2-2-0-1", Long.parseLong(str4) * 1000);
                            }
                            i2++;
                            c = 0;
                            c2 = 1;
                        }
                    }
                } catch (Throwable th2) {
                    b.a(th2);
                }
                try {
                    String strOptString3 = jSONObject.optString("sdk.gtc.dim.rom.map.extension");
                    if (!TextUtils.isEmpty(strOptString3) && !strOptString3.equals("none")) {
                        for (String str5 : strOptString3.toLowerCase().split(",")) {
                            String[] strArrSplit4 = str5.trim().split(Constants.COLON_SEPARATOR);
                            if (strArrSplit4.length >= 2) {
                                com.getui.gtc.dim.c.a.f2170a.put(strArrSplit4[0].trim(), strArrSplit4[1].trim());
                                b.a("dim sys rom map set: " + strArrSplit4[0].trim() + Constants.COLON_SEPARATOR + strArrSplit4[1].trim());
                            }
                        }
                    }
                } catch (Throwable th3) {
                    b.a(th3);
                }
                try {
                    String strOptString4 = jSONObject.optString("sdk.gtc.dim.sys.call.black.version");
                    if (!TextUtils.isEmpty(strOptString4) && !"none".equals(strOptString4)) {
                        String[] strArrSplit5 = strOptString4.split(",");
                        int length2 = strArrSplit5.length;
                        int i3 = 0;
                        while (i3 < length2) {
                            String str6 = strArrSplit5[i3];
                            if (str6.contains(Constants.COLON_SEPARATOR)) {
                                String[] strArrSplit6 = str6.split(Constants.COLON_SEPARATOR);
                                if (Operators.MUL.equals(strArrSplit6[0])) {
                                    this.c.e("dim-2-2-0-1", strArrSplit6[1]);
                                } else {
                                    Iterator<String> it2 = a(strArrSplit6[0]).iterator();
                                    while (it2.hasNext()) {
                                        String[] strArr = strArrSplit5;
                                        this.c.e(it2.next(), strArrSplit6[1]);
                                        strArrSplit5 = strArr;
                                    }
                                }
                            }
                            i3++;
                            strArrSplit5 = strArrSplit5;
                        }
                    }
                } catch (Throwable th4) {
                    b.a(th4);
                }
                try {
                    String strOptString5 = jSONObject.optString("sdk.gtc.dim.sys.call.black.model");
                    if (!TextUtils.isEmpty(strOptString5) && !"none".equals(strOptString5)) {
                        String[] strArrSplit7 = strOptString5.split(",");
                        int length3 = strArrSplit7.length;
                        int i4 = 0;
                        while (i4 < length3) {
                            String str7 = strArrSplit7[i4];
                            if (str7.contains(Constants.COLON_SEPARATOR)) {
                                String[] strArrSplit8 = str7.split(Constants.COLON_SEPARATOR);
                                Iterator<String> it3 = a(strArrSplit8[0]).iterator();
                                while (it3.hasNext()) {
                                    String[] strArr2 = strArrSplit7;
                                    this.c.g(it3.next(), strArrSplit8[1]);
                                    strArrSplit7 = strArr2;
                                }
                            }
                            i4++;
                            strArrSplit7 = strArrSplit7;
                        }
                    }
                } catch (Throwable th5) {
                    b.a(th5);
                }
                try {
                    String strOptString6 = jSONObject.optString("sdk.gtc.dim.sys.call.black.rom");
                    if (!TextUtils.isEmpty(strOptString6) && !"none".equals(strOptString6)) {
                        String[] strArrSplit9 = strOptString6.split(",");
                        int length4 = strArrSplit9.length;
                        int i5 = 0;
                        while (i5 < length4) {
                            String str8 = strArrSplit9[i5];
                            if (str8.contains(Constants.COLON_SEPARATOR)) {
                                String[] strArrSplit10 = str8.split(Constants.COLON_SEPARATOR);
                                List<String> listA2 = a(strArrSplit10[0]);
                                for (int i6 = 1; i6 < strArrSplit10.length; i6++) {
                                    Iterator<String> it4 = listA2.iterator();
                                    while (it4.hasNext()) {
                                        String[] strArr3 = strArrSplit9;
                                        int i7 = length4;
                                        this.c.f(it4.next(), strArrSplit10[i6]);
                                        strArrSplit9 = strArr3;
                                        length4 = i7;
                                    }
                                }
                            }
                            i5++;
                            strArrSplit9 = strArrSplit9;
                            length4 = length4;
                        }
                    }
                } catch (Throwable th6) {
                    b.a(th6);
                }
                try {
                    String strOptString7 = jSONObject.optString("sdk.gtc.dim.sys.call.enable");
                    if (!TextUtils.isEmpty(strOptString7)) {
                        for (String str9 : strOptString7.split(",")) {
                            if (str9.contains(Constants.COLON_SEPARATOR)) {
                                String[] strArrSplit11 = str9.split(Constants.COLON_SEPARATOR);
                                List<String> listA3 = a(strArrSplit11[0]);
                                int i8 = Integer.parseInt(strArrSplit11[1]);
                                Iterator<String> it5 = listA3.iterator();
                                while (it5.hasNext()) {
                                    this.c.b(it5.next(), i8);
                                }
                            } else {
                                this.c.b("dim-2-2-0-1", Integer.parseInt(str9));
                            }
                        }
                    }
                } catch (Throwable th7) {
                    b.a(th7);
                }
                try {
                    String strOptString8 = jSONObject.optString("sdk.gtc.dim.sys.hw.oaid.enable");
                    if (!TextUtils.isEmpty(strOptString8) && !"none".equals(strOptString8)) {
                        this.c.c(Integer.parseInt(strOptString8));
                    }
                } catch (Throwable th8) {
                    b.a(th8);
                }
                try {
                    String strOptString9 = jSONObject.optString("sdk.gtc.dim.halfclosed.policy");
                    if (!TextUtils.isEmpty(strOptString9) && !"none".equals(strOptString9)) {
                        com.getui.gtc.dim.b.b.a().a(strOptString9);
                    }
                } catch (Throwable th9) {
                    b.a(th9);
                }
                try {
                    String strOptString10 = jSONObject.optString("sdk.gtc.dim.halfclosed.enable");
                    if (!TextUtils.isEmpty(strOptString10) && !"none".equals(strOptString10)) {
                        for (b.a aVar : com.getui.gtc.dim.b.b.d(strOptString10)) {
                            if (ProviderConfigurationPermission.ALL_STR.equals(aVar.f2156a)) {
                                com.getui.gtc.dim.b.b.a().a("dim-2-2-0-1", aVar);
                            } else {
                                Iterator<String> it6 = a(aVar.f2156a).iterator();
                                while (it6.hasNext()) {
                                    com.getui.gtc.dim.b.b.a().a(it6.next(), aVar);
                                }
                            }
                        }
                    }
                } catch (Throwable th10) {
                    com.getui.gtc.dim.e.b.a(th10);
                }
                try {
                    String strOptString11 = jSONObject.optString("sdk.gtc.dim.sys.call.policy");
                    if (!TextUtils.isEmpty(strOptString11)) {
                        for (String str10 : strOptString11.split(",")) {
                            if (str10.contains(Constants.COLON_SEPARATOR)) {
                                String[] strArrSplit12 = str10.split(Constants.COLON_SEPARATOR);
                                List<String> listA4 = a(strArrSplit12[0]);
                                int i9 = Integer.parseInt(strArrSplit12[1]);
                                Iterator<String> it7 = listA4.iterator();
                                while (it7.hasNext()) {
                                    this.c.c(it7.next(), i9);
                                }
                            } else {
                                this.c.c("dim-2-2-0-1", Integer.parseInt(str10));
                            }
                        }
                    }
                } catch (Throwable th11) {
                    com.getui.gtc.dim.e.b.a(th11);
                }
                try {
                    String strOptString12 = jSONObject.optString("sdk.gtc.dim.use.expired.enable");
                    if (!TextUtils.isEmpty(strOptString12)) {
                        for (String str11 : strOptString12.split(",")) {
                            if (str11.contains(Constants.COLON_SEPARATOR)) {
                                String[] strArrSplit13 = str11.split(Constants.COLON_SEPARATOR);
                                List<String> listA5 = a(strArrSplit13[0]);
                                int i10 = Integer.parseInt(strArrSplit13[1]);
                                Iterator<String> it8 = listA5.iterator();
                                while (it8.hasNext()) {
                                    a(it8.next(), i10);
                                }
                            } else {
                                a("dim-2-2-0-1", Integer.parseInt(str11));
                            }
                        }
                    }
                } catch (Throwable th12) {
                    com.getui.gtc.dim.e.b.a(th12);
                }
                try {
                    String strOptString13 = jSONObject.optString("sdk.gtc.dim.sys.call.disallow");
                    if (!TextUtils.isEmpty(strOptString13) && !Operators.MUL.equals(strOptString13)) {
                        for (String str12 : strOptString13.split(",")) {
                            if (str12.contains(Constants.COLON_SEPARATOR)) {
                                String[] strArrSplit14 = str12.split(Constants.COLON_SEPARATOR);
                                Iterator<String> it9 = a(strArrSplit14[0]).iterator();
                                while (it9.hasNext()) {
                                    this.c.c(it9.next(), strArrSplit14[1]);
                                }
                            }
                        }
                    }
                } catch (Throwable th13) {
                    com.getui.gtc.dim.e.b.a(th13);
                }
                try {
                    String strOptString14 = jSONObject.optString("sdk.gtc.dim.sys.trace.enable");
                    if (!TextUtils.isEmpty(strOptString14) && !"none".equals(strOptString14)) {
                        this.c.b(Integer.parseInt(strOptString14));
                    }
                } catch (Throwable th14) {
                    com.getui.gtc.dim.e.b.a(th14);
                }
                try {
                    String strOptString15 = jSONObject.optString("sdk.gtc.dim.sys.trace.order");
                    if (!TextUtils.isEmpty(strOptString15)) {
                        for (String str13 : strOptString15.split(",")) {
                            if (str13.contains(Constants.COLON_SEPARATOR)) {
                                String[] strArrSplit15 = str13.split(Constants.COLON_SEPARATOR);
                                Iterator<String> it10 = a(strArrSplit15[0]).iterator();
                                while (it10.hasNext()) {
                                    this.c.d(it10.next(), strArrSplit15[1]);
                                }
                            } else {
                                this.c.d("dim-2-2-0-1", str13);
                            }
                        }
                    }
                } catch (Throwable th15) {
                    com.getui.gtc.dim.e.b.a(th15);
                }
                try {
                    String strOptString16 = jSONObject.optString("sdk.gtc.dim.busi.enable");
                    if (!TextUtils.isEmpty(strOptString16) && !"none".equals(strOptString16)) {
                        this.c.a(Integer.parseInt(strOptString16));
                    }
                } catch (Throwable th16) {
                    com.getui.gtc.dim.e.b.a(th16);
                }
                try {
                    String strOptString17 = jSONObject.optString("sdk.gtc.dim.al.tech_policy");
                    if (!TextUtils.isEmpty(strOptString17)) {
                        for (String str14 : strOptString17.split(",")) {
                            String[] strArrSplit16 = str14.trim().split("#");
                            if (strArrSplit16.length >= 2) {
                                com.getui.gtc.dim.c.a.b.put(strArrSplit16[0].trim().toLowerCase(), strArrSplit16[1].trim());
                                com.getui.gtc.dim.e.b.a("dim sys permission map set: " + strArrSplit16[0].trim() + "#" + strArrSplit16[1].trim());
                            }
                        }
                    }
                } catch (Throwable th17) {
                    com.getui.gtc.dim.e.b.a(th17);
                }
                try {
                    String strOptString18 = jSONObject.optString("sdk.gtc.dim.complex.policy");
                    if (TextUtils.isEmpty(strOptString18) || strOptString18.equals("none")) {
                        return;
                    }
                    for (String str15 : strOptString18.split(",")) {
                        String[] strArrSplit17 = str15.split("#");
                        List<String> listA6 = a(strArrSplit17[0]);
                        String[] strArrSplit18 = strArrSplit17[1].split(Constants.COLON_SEPARATOR);
                        StringBuilder sb = new StringBuilder();
                        for (String str16 : strArrSplit18) {
                            Iterator<String> it11 = a(str16).iterator();
                            while (it11.hasNext()) {
                                sb.append(it11.next());
                                sb.append(",");
                            }
                        }
                        Iterator<String> it12 = listA6.iterator();
                        while (it12.hasNext()) {
                            c(it12.next(), sb.toString());
                        }
                    }
                } catch (Throwable th18) {
                    com.getui.gtc.dim.e.b.a(th18);
                }
            } catch (Throwable th19) {
                com.getui.gtc.dim.e.b.a(th19);
            }
        } catch (Throwable th20) {
            com.getui.gtc.dim.e.b.b(th20);
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final boolean a(String str, String str2, String str3) {
        byte b = -1;
        try {
            switch (str.hashCode()) {
                case 378146557:
                    if (str.equals("dim-2-3-10-1")) {
                        b = 9;
                    }
                    break;
                case 378146558:
                    if (str.equals("dim-2-3-10-2")) {
                        b = 24;
                    }
                    break;
                case 378147518:
                    if (str.equals("dim-2-3-11-1")) {
                        b = 10;
                    }
                    break;
                case 378147519:
                    if (str.equals("dim-2-3-11-2")) {
                        b = 25;
                    }
                    break;
                case 378148479:
                    if (str.equals("dim-2-3-12-1")) {
                        b = 30;
                    }
                    break;
                case 378149440:
                    if (str.equals("dim-2-3-13-1")) {
                        b = 11;
                    }
                    break;
                case 378149441:
                    if (str.equals("dim-2-3-13-2")) {
                        b = JSONLexer.EOI;
                    }
                    break;
                case 378150401:
                    if (str.equals("dim-2-3-14-1")) {
                        b = 12;
                    }
                    break;
                case 378150402:
                    if (str.equals("dim-2-3-14-2")) {
                        b = 27;
                    }
                    break;
                case 378151362:
                    if (str.equals("dim-2-3-15-1")) {
                        b = 13;
                    }
                    break;
                case 378151363:
                    if (str.equals("dim-2-3-15-2")) {
                        b = 28;
                    }
                    break;
                case 378152323:
                    if (str.equals("dim-2-3-16-1")) {
                        b = db.l;
                    }
                    break;
                case 378152324:
                    if (str.equals("dim-2-3-16-2")) {
                        b = 29;
                    }
                    break;
                case 1674766171:
                    if (str.equals("dim-2-3-1-1")) {
                        b = 0;
                    }
                    break;
                case 1674766172:
                    if (str.equals("dim-2-3-1-2")) {
                        b = 15;
                    }
                    break;
                case 1674767132:
                    if (str.equals("dim-2-3-2-1")) {
                        b = 1;
                    }
                    break;
                case 1674767133:
                    if (str.equals("dim-2-3-2-2")) {
                        b = 16;
                    }
                    break;
                case 1674768093:
                    if (str.equals("dim-2-3-3-1")) {
                        b = 2;
                    }
                    break;
                case 1674768094:
                    if (str.equals("dim-2-3-3-2")) {
                        b = 17;
                    }
                    break;
                case 1674769054:
                    if (str.equals("dim-2-3-4-1")) {
                        b = 3;
                    }
                    break;
                case 1674769055:
                    if (str.equals("dim-2-3-4-2")) {
                        b = SharedPreferencesNewImpl.FINISH_MARK;
                    }
                    break;
                case 1674770015:
                    if (str.equals("dim-2-3-5-1")) {
                        b = 4;
                    }
                    break;
                case 1674770016:
                    if (str.equals("dim-2-3-5-2")) {
                        b = 19;
                    }
                    break;
                case 1674770976:
                    if (str.equals("dim-2-3-6-1")) {
                        b = 5;
                    }
                    break;
                case 1674770977:
                    if (str.equals("dim-2-3-6-2")) {
                        b = 20;
                    }
                    break;
                case 1674771937:
                    if (str.equals("dim-2-3-7-1")) {
                        b = 6;
                    }
                    break;
                case 1674771938:
                    if (str.equals("dim-2-3-7-2")) {
                        b = 21;
                    }
                    break;
                case 1674772898:
                    if (str.equals("dim-2-3-8-1")) {
                        b = 7;
                    }
                    break;
                case 1674772899:
                    if (str.equals("dim-2-3-8-2")) {
                        b = 22;
                    }
                    break;
                case 1674773859:
                    if (str.equals("dim-2-3-9-1")) {
                        b = 8;
                    }
                    break;
                case 1674773860:
                    if (str.equals("dim-2-3-9-2")) {
                        b = 23;
                    }
                    break;
            }
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.b(th);
        }
        switch (b) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                return this.c.a(str, Caller.valueOf(str2), Boolean.parseBoolean(str3));
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
                d dVar = this.b;
                Caller callerValueOf = Caller.valueOf(str2);
                long j = Long.parseLong(str3);
                com.getui.gtc.dim.e.b.a("dim storage caller interval set: " + str + " : " + callerValueOf + " : " + j);
                com.getui.gtc.dim.b.a aVarA = com.getui.gtc.dim.b.a.a(str);
                if (aVarA == null || j < aVarA.c || j > aVarA.d) {
                    return false;
                }
                dVar.f2160a.put(str + Constants.COLON_SEPARATOR + callerValueOf.name(), Long.valueOf(j));
                d.a(str + Constants.COLON_SEPARATOR + callerValueOf.name(), Long.valueOf(j));
                return true;
            case 30:
                return this.c.b(str, Caller.valueOf(str2), Boolean.parseBoolean(str3));
            default:
                return false;
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final Serializable b(String str, String str2) {
        byte b = -1;
        try {
            switch (str.hashCode()) {
                case 378146557:
                    if (str.equals("dim-2-3-10-1")) {
                        b = 9;
                    }
                    break;
                case 378146558:
                    if (str.equals("dim-2-3-10-2")) {
                        b = 24;
                    }
                    break;
                case 378147518:
                    if (str.equals("dim-2-3-11-1")) {
                        b = 10;
                    }
                    break;
                case 378147519:
                    if (str.equals("dim-2-3-11-2")) {
                        b = 25;
                    }
                    break;
                case 378148479:
                    if (str.equals("dim-2-3-12-1")) {
                        b = 30;
                    }
                    break;
                case 378149440:
                    if (str.equals("dim-2-3-13-1")) {
                        b = 11;
                    }
                    break;
                case 378149441:
                    if (str.equals("dim-2-3-13-2")) {
                        b = JSONLexer.EOI;
                    }
                    break;
                case 378150401:
                    if (str.equals("dim-2-3-14-1")) {
                        b = 12;
                    }
                    break;
                case 378150402:
                    if (str.equals("dim-2-3-14-2")) {
                        b = 27;
                    }
                    break;
                case 378151362:
                    if (str.equals("dim-2-3-15-1")) {
                        b = 13;
                    }
                    break;
                case 378151363:
                    if (str.equals("dim-2-3-15-2")) {
                        b = 28;
                    }
                    break;
                case 378152323:
                    if (str.equals("dim-2-3-16-1")) {
                        b = db.l;
                    }
                    break;
                case 378152324:
                    if (str.equals("dim-2-3-16-2")) {
                        b = 29;
                    }
                    break;
                case 1674766171:
                    if (str.equals("dim-2-3-1-1")) {
                        b = 0;
                    }
                    break;
                case 1674766172:
                    if (str.equals("dim-2-3-1-2")) {
                        b = 15;
                    }
                    break;
                case 1674767132:
                    if (str.equals("dim-2-3-2-1")) {
                        b = 1;
                    }
                    break;
                case 1674767133:
                    if (str.equals("dim-2-3-2-2")) {
                        b = 16;
                    }
                    break;
                case 1674768093:
                    if (str.equals("dim-2-3-3-1")) {
                        b = 2;
                    }
                    break;
                case 1674768094:
                    if (str.equals("dim-2-3-3-2")) {
                        b = 17;
                    }
                    break;
                case 1674769054:
                    if (str.equals("dim-2-3-4-1")) {
                        b = 3;
                    }
                    break;
                case 1674769055:
                    if (str.equals("dim-2-3-4-2")) {
                        b = SharedPreferencesNewImpl.FINISH_MARK;
                    }
                    break;
                case 1674770015:
                    if (str.equals("dim-2-3-5-1")) {
                        b = 4;
                    }
                    break;
                case 1674770016:
                    if (str.equals("dim-2-3-5-2")) {
                        b = 19;
                    }
                    break;
                case 1674770976:
                    if (str.equals("dim-2-3-6-1")) {
                        b = 5;
                    }
                    break;
                case 1674770977:
                    if (str.equals("dim-2-3-6-2")) {
                        b = 20;
                    }
                    break;
                case 1674771937:
                    if (str.equals("dim-2-3-7-1")) {
                        b = 6;
                    }
                    break;
                case 1674771938:
                    if (str.equals("dim-2-3-7-2")) {
                        b = 21;
                    }
                    break;
                case 1674772898:
                    if (str.equals("dim-2-3-8-1")) {
                        b = 7;
                    }
                    break;
                case 1674772899:
                    if (str.equals("dim-2-3-8-2")) {
                        b = 22;
                    }
                    break;
                case 1674773859:
                    if (str.equals("dim-2-3-9-1")) {
                        b = 8;
                    }
                    break;
                case 1674773860:
                    if (str.equals("dim-2-3-9-2")) {
                        b = 23;
                    }
                    break;
            }
            switch (b) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                    return this.c.b(str, str2);
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                    return this.b.a(str, str2);
                case 30:
                    return Boolean.valueOf(this.c.a(str, str2));
                default:
                    return null;
            }
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.b(th);
            return null;
        }
    }
}
