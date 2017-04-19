package actions;

import play.mvc.With;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The interface Log activity.
 *
 * @author dinopraso
 */
@With(LogActivityAction.class)
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogActivity {
	/**
	 * Message string.
	 *
	 * @return the string
	 */
	String message() default "";
}
