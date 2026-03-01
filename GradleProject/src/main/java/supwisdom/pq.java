package supwisdom;

import com.bumptech.glide.load.engine.executor.RuntimeCompat;
import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class pq implements FileFilter {
    public pq(oq oqVar) {
    }

    @Override // java.io.FileFilter
    public final boolean accept(File file) {
        return Pattern.matches(RuntimeCompat.CPU_NAME_REGEX, file.getName());
    }
}
