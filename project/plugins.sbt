import sbt.Resolver

resolvers += Resolver.bintrayIvyRepo("gatling", "sbt-plugins")

addSbtPlugin("io.gatling" % "gatling-sbt" % "3.0.0")