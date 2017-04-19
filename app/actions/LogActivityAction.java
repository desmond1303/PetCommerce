package actions;

import play.Logger;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import java.net.URLDecoder;
import java.util.concurrent.CompletionStage;

/**
 * The type Log activity action.
 *
 * @author dinopraso
 */
public class LogActivityAction extends Action<LogActivity> {
	private static final String LOGG_PATTERN = "host={}, http_method={}, url=\"{}\", duration={} ms";

	@Override
	public CompletionStage<Result> call(Http.Context ctx) {
		if (configuration.message().equals("")) {
			Http.Request request = ctx.request();

			String url = URLDecoder.decode(request.uri());

			long start = System.currentTimeMillis();
			try {
				return delegate.call(ctx);
			} finally {
				long duration = System.currentTimeMillis() - start;
				Logger.info(LOGG_PATTERN, request.remoteAddress(), request.method(), url, duration);
			}
		} else {
			Logger.info(configuration.message());
			return delegate.call(ctx);
		}
	}

}
