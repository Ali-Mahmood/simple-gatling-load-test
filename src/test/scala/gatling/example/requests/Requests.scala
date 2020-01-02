package gatling.example.requests

import io.gatling.core.Predef._
import io.gatling.http.Predef.{http, status}
import gatling.example.Website._

object Requests {

  import Urls._

  def getWebsite() =
    exec {
      val req = http(get_URL)
        .get(page)
        .header("Accept", "application/json")
        .check(status.in(200))
      req
    }
}

object Urls {
  val get_URL = "Get website"
}
