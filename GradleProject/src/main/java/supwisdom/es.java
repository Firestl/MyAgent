package supwisdom;

import com.auth0.android.jwt.DecodeException;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: JWTDeserializer.java */
/* JADX INFO: loaded from: classes.dex */
public class es implements JsonDeserializer<fs> {
    public final Date a(JsonObject jsonObject, String str) {
        if (jsonObject.has(str)) {
            return new Date(jsonObject.get(str).getAsLong() * 1000);
        }
        return null;
    }

    public final String b(JsonObject jsonObject, String str) {
        if (jsonObject.has(str)) {
            return jsonObject.get(str).getAsString();
        }
        return null;
    }

    public final List<String> c(JsonObject jsonObject, String str) {
        List<String> listEmptyList = Collections.emptyList();
        if (!jsonObject.has(str)) {
            return listEmptyList;
        }
        JsonElement jsonElement = jsonObject.get(str);
        if (!jsonElement.isJsonArray()) {
            return Collections.singletonList(jsonElement.getAsString());
        }
        JsonArray asJsonArray = jsonElement.getAsJsonArray();
        ArrayList arrayList = new ArrayList(asJsonArray.size());
        for (int i = 0; i < asJsonArray.size(); i++) {
            arrayList.add(asJsonArray.get(i).getAsString());
        }
        return arrayList;
    }

    @Override // com.google.gson.JsonDeserializer
    public fs deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement.isJsonNull() || !jsonElement.isJsonObject()) {
            throw new DecodeException("The token's payload had an invalid JSON format.");
        }
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        String strB = b(asJsonObject, com.umeng.commonsdk.utils.c.f);
        String strB2 = b(asJsonObject, "sub");
        Date dateA = a(asJsonObject, com.umeng.analytics.pro.aw.b);
        Date dateA2 = a(asJsonObject, "nbf");
        Date dateA3 = a(asJsonObject, "iat");
        String strB3 = b(asJsonObject, "jti");
        List<String> listC = c(asJsonObject, "aud");
        HashMap map = new HashMap();
        for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            map.put(entry.getKey(), new ds(entry.getValue()));
        }
        return new fs(strB, strB2, dateA, dateA2, dateA3, strB3, listC, map);
    }
}
