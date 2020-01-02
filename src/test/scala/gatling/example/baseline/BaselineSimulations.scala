import io.gatling.core.structure.ChainBuilder

import scala.concurrent.duration._

package gatling.example.baseline {

  import io.gatling.core.Predef._
  import io.gatling.http.Predef.http
  import io.gatling.http.protocol.HttpProtocolBuilder
  import gatling.example.Defaults
  import gatling.example.requests.Endpoints
  import gatling.example.Website._

  trait BaselineScenarioRunner extends Simulation {

    val expectedMeanResponseTime = 3000
    val expectedErrorRate        = 1.0
    val maxRPS: Double           = 1000
    val quart: Double            = maxRPS / 4.0
    val mid: Double              = maxRPS / 2.0

    def runScenario(description: String, endpoint: ChainBuilder): SetUp = {

      val traffic = scenario(description)
        .exitBlockOnFail(endpoint)
        .inject(
          rampUsersPerSec(1.0) to quart during 5.minutes,
          rampUsersPerSec(quart) to mid during 5.minutes,
          rampUsersPerSec(mid) to maxRPS during 2.minutes,
          constantUsersPerSec(maxRPS) during 5.minutes
        )

      setUp(traffic)
        .protocols(Defaults.httpProtocol)
        .assertions(
          forAll.responseTime.mean.lte(expectedMeanResponseTime),
          forAll.failedRequests.percent.lte(1)
        )
    }
  }

  trait ShortSoakScenarioRunner extends Simulation {

    val HttpConf: HttpProtocolBuilder = http.baseURL(website)

    val maxRPS: Double           = 300 // 300 requests per second
    val expectedMeanResponseTime = 3000 // 3000 milliseconds
    val expectedErrorRate        = 1.0 // 1% error rate expected

    def runScenario(description: String, endpoint: ChainBuilder): SetUp = {
      val traffic = scenario(description)
        .exitBlockOnFail {
          endpoint
        }
        .inject(rampUsersPerSec(1.0) to maxRPS during 1.minutes, constantUsersPerSec(maxRPS) during 1.minute) // test will run for 2 minutes

      setUp(traffic)
        .protocols(HttpConf)
        .assertions(
          forAll.responseTime.mean.lte(expectedMeanResponseTime),
          forAll.failedRequests.percent.lte(expectedErrorRate)
        )
    }
  }

  class GetWebsite extends ShortSoakScenarioRunner {
    runScenario("Get Website", Endpoints.websiteEndpoint)
  }
}

