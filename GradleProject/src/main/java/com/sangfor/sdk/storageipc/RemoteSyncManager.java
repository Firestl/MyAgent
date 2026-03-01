package com.sangfor.sdk.storageipc;

import android.os.Bundle;
import com.sangfor.sdk.storageipc.provider.KeyValue;
import com.sangfor.sdk.utils.SFLogN;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;
import supwisdom.sb1;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class RemoteSyncManager {
    public static HashMap<String, String> a(Bundle bundle) {
        if (bundle == null) {
            SFLogN.d("RemoteSyncManager", "bundleToMap arg invalid");
            return null;
        }
        bundle.setClassLoader(KeyValue.class.getClassLoader());
        if (!bundle.isEmpty()) {
            return a((ArrayList<KeyValue>) bundle.getParcelableArrayList("sangfor.remote.shared.key"));
        }
        SFLogN.d("RemoteSyncManager", "bundle is empty");
        return null;
    }

    public static boolean b(Map<String, String> map) {
        if (map == null) {
            SFLogN.a("RemoteSyncManager", "syncData failed.", "map is null");
            return false;
        }
        sb1.a().b(null, map);
        return true;
    }

    public static native ArrayList<String> getNonConfidentialKeys();

    public static native ArrayList<String> getWhiteAppList();

    public static native ArrayList<String> getWhiteSignatureList();

    public static native HashMap<String, String> syncPull(HashMap<String, String> map);

    public static native boolean syncPush(HashMap<String, String> map);

    /* JADX INFO: compiled from: Proguard */
    public static class a {
        public static a b;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Map<String, Map<String, String>> f3957a = new ConcurrentHashMap();

        public static a a() {
            a aVar = b;
            if (aVar != null) {
                return aVar;
            }
            synchronized (a.class) {
                if (b == null) {
                    b = new a();
                }
            }
            return b;
        }

        public Map<String, String> a(String str) {
            return this.f3957a.remove(str);
        }
    }

    public static HashMap<String, String> a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            HashMap<String, String> map = new HashMap<>();
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                map.put(next, jSONObject.getString(next));
            }
            return map;
        } catch (Exception e2) {
            SFLogN.b("RemoteSyncManager", "jsonToMap failed.", "parse str faield, str:" + str + ".msg:" + e2.getMessage());
            return null;
        }
    }

    public static HashMap<String, String> a(ArrayList<KeyValue> arrayList) {
        if (arrayList == null) {
            SFLogN.b("RemoteSyncManager", "mapToKeyValueList failed.", "list is null");
            return null;
        }
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < arrayList.size(); i++) {
            KeyValue keyValue = arrayList.get(i);
            map.put(keyValue.a(), keyValue.b());
        }
        SFLogN.a("RemoteSyncManager", "keyValueListToMap call,map size:[%d]", Integer.valueOf(map.size()));
        return map;
    }

    public static Bundle a(HashMap<String, String> map) {
        if (map == null) {
            SFLogN.d("RemoteSyncManager", "mapToBundle arg invalid");
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("sangfor.remote.shared.key", a((Map<String, String>) map));
        return bundle;
    }

    public static ArrayList<KeyValue> a(Map<String, String> map) {
        if (map == null) {
            SFLogN.b("RemoteSyncManager", "mapToKeyValueList failed.", "map is null");
            return null;
        }
        ArrayList<KeyValue> arrayList = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            SFLogN.c("RemoteSyncManager", "key: " + key + "    valueLen: " + value.length());
            arrayList.add(new KeyValue(key, value));
        }
        SFLogN.a("RemoteSyncManager", "mapToKeyValueList call,map size:[%d]", Integer.valueOf(map.size()));
        return arrayList;
    }
}
