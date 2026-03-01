package supwisdom;

import android.util.Pair;
import androidx.constraintlayout.motion.widget.MotionLayout;
import java.util.HashMap;

/* JADX INFO: compiled from: DesignTool.java */
/* JADX INFO: loaded from: classes.dex */
public class a5 {
    public static final HashMap<Pair<Integer, Integer>, String> b = new HashMap<>();
    public static final HashMap<String, String> c = new HashMap<>();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final MotionLayout f6856a;

    static {
        b.put(Pair.create(4, 4), "layout_constraintBottom_toBottomOf");
        b.put(Pair.create(4, 3), "layout_constraintBottom_toTopOf");
        b.put(Pair.create(3, 4), "layout_constraintTop_toBottomOf");
        b.put(Pair.create(3, 3), "layout_constraintTop_toTopOf");
        b.put(Pair.create(6, 6), "layout_constraintStart_toStartOf");
        b.put(Pair.create(6, 7), "layout_constraintStart_toEndOf");
        b.put(Pair.create(7, 6), "layout_constraintEnd_toStartOf");
        b.put(Pair.create(7, 7), "layout_constraintEnd_toEndOf");
        b.put(Pair.create(1, 1), "layout_constraintLeft_toLeftOf");
        b.put(Pair.create(1, 2), "layout_constraintLeft_toRightOf");
        b.put(Pair.create(2, 2), "layout_constraintRight_toRightOf");
        b.put(Pair.create(2, 1), "layout_constraintRight_toLeftOf");
        b.put(Pair.create(5, 5), "layout_constraintBaseline_toBaselineOf");
        c.put("layout_constraintBottom_toBottomOf", "layout_marginBottom");
        c.put("layout_constraintBottom_toTopOf", "layout_marginBottom");
        c.put("layout_constraintTop_toBottomOf", "layout_marginTop");
        c.put("layout_constraintTop_toTopOf", "layout_marginTop");
        c.put("layout_constraintStart_toStartOf", "layout_marginStart");
        c.put("layout_constraintStart_toEndOf", "layout_marginStart");
        c.put("layout_constraintEnd_toStartOf", "layout_marginEnd");
        c.put("layout_constraintEnd_toEndOf", "layout_marginEnd");
        c.put("layout_constraintLeft_toLeftOf", "layout_marginLeft");
        c.put("layout_constraintLeft_toRightOf", "layout_marginLeft");
        c.put("layout_constraintRight_toRightOf", "layout_marginRight");
        c.put("layout_constraintRight_toLeftOf", "layout_marginRight");
    }

    public a5(MotionLayout motionLayout) {
        this.f6856a = motionLayout;
    }
}
