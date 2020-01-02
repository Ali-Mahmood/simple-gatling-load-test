package gatling.example

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Defaults {

  def httpProtocol = http
    .header("X-Secure", "True")
    .acceptHeader("image/webp,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-GB")
    .contentTypeHeader("application/x-www-form-urlencoded")
    .doNotTrackHeader("1")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36")
    .disableCaching
    .shareConnections
    .maxConnectionsPerHostLikeChrome
}