package retrofit2.converter.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import okio.Buffer;
import retrofit2.Converter;
import supwisdom.ct1;
import supwisdom.xs1;

/* JADX INFO: loaded from: classes3.dex */
public final class GsonRequestBodyConverter<T> implements Converter<T, ct1> {
    public static final xs1 MEDIA_TYPE = xs1.a("application/json; charset=UTF-8");
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    public final TypeAdapter<T> adapter;
    public final Gson gson;

    public GsonRequestBodyConverter(Gson gson, TypeAdapter<T> typeAdapter) {
        this.gson = gson;
        this.adapter = typeAdapter;
    }

    @Override // retrofit2.Converter
    public ct1 convert(T t) throws IOException {
        Buffer buffer = new Buffer();
        JsonWriter jsonWriterNewJsonWriter = this.gson.newJsonWriter(new OutputStreamWriter(buffer.outputStream(), UTF_8));
        this.adapter.write(jsonWriterNewJsonWriter, t);
        jsonWriterNewJsonWriter.close();
        return ct1.create(MEDIA_TYPE, buffer.readByteString());
    }
}
