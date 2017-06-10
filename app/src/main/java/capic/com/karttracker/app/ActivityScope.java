package capic.com.karttracker.app;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Vincent on 09/06/2017.
 */

@Scope
@Documented
@Retention(value= RetentionPolicy.RUNTIME)
public  @interface ActivityScope {
}
