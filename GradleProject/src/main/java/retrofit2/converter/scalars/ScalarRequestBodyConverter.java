package retrofit2.converter.scalars;

import java.io.IOException;
import retrofit2.Converter;
import supwisdom.ct1;
import supwisdom.xs1;

/* JADX INFO: loaded from: classes3.dex */
public final class ScalarRequestBodyConverter<T> implements Converter<T, ct1> {
    public static final ScalarRequestBodyConverter<Object> INSTANCE = new ScalarRequestBodyConverter<>();
    public static final xs1 MEDIA_TYPE = xs1.a("text/plain; charset=UTF-8");

    @Override // retrofit2.Converter
    public ct1 convert(T t) throws IOException {
        return ct1.create(MEDIA_TYPE, String.valueOf(t));
    }
}
