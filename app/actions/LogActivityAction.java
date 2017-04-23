package actions;

import annotations.LogActivity;
import play.Logger;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import java.util.Date;
import java.util.concurrent.CompletionStage;

/**
 * The Log activity Action.
 *
 * @author dinopraso
 */
public class LogActivityAction extends Action<LogActivity> {
	private static final String LOG_PATTERN = "date={}, host={}, http_method={}, url=\"{}\", duration={} ms";

	@Override
	public CompletionStage<Result> call(Http.Context ctx) {
		if (configuration.message().equals("")) {
			Http.Request request = ctx.request();

			String url = request.uri();

			long start = System.currentTimeMillis();
			try {
				return delegate.call(ctx);
			} finally {
				long duration = System.currentTimeMillis() - start;
				Logger.info(LOG_PATTERN, new Date(System.currentTimeMillis()).toString(), request.remoteAddress(), request.method(), url, duration);
			}
		} else {
			Logger.info(configuration.message());
			return delegate.call(ctx);
		}
	}

}
