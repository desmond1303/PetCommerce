package actions;

import play.mvc.With;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Log activity Annotation.
 * Annotate all Classes or Methods to Log the activity they perform to the system log.
 *
 * @author dinopraso
 */
@With(LogActivityAction.class)
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogActivity {
	/**
	 * A Custom Message. Optional. Will override default log pattern.
	 *
	 * @return the string
	 */
	String message() default "";
}
