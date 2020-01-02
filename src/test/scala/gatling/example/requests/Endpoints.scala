package gatling.example.requests

import io.gatling.core.Predef.exec

object Endpoints {
  import Requests._

  val websiteEndpoint = exec(
    getWebsite()
  )

}
