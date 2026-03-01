package supwisdom;

import com.synjones.mobilegroup.libofflinecodesdk.utils.eventbus.EBThreadMode;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes2.dex */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface un1 {
    EBThreadMode threadMode() default EBThreadMode.Main;
}
