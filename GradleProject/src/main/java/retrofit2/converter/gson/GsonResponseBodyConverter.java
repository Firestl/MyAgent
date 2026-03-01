package retrofit2.converter.gson;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import retrofit2.Converter;
import supwisdom.et1;

/* JADX INFO: loaded from: classes3.dex */
public final class GsonResponseBodyConverter<T> implements Converter<et1, T> {
    public final TypeAdapter<T> adapter;
    public final Gson gson;

    public GsonResponseBodyConverter(Gson gson, TypeAdapter<T> typeAdapter) {
        this.gson = gson;
        this.adapter = typeAdapter;
    }

    @Override // retrofit2.Converter
    public T convert(et1 et1Var) throws IOException {
        JsonReader jsonReaderNewJsonReader = this.gson.newJsonReader(et1Var.charStream());
        try {
            T t = this.adapter.read2(jsonReaderNewJsonReader);
            if (jsonReaderNewJsonReader.peek() == JsonToken.END_DOCUMENT) {
                return t;
            }
            throw new JsonIOException("JSON document was not fully consumed.");
        } finally {
            et1Var.close();
        }
    }
}
