import sbt.Resolver

name := "load-testing"

version := "0.0.1"

scalaVersion := "2.12.7"

resolvers += Resolver.bintrayIvyRepo("gatling", "sbt-plugins")
resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.3.1",
  "io.gatling" % "gatling-test-framework" % "2.3.1",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0"
)

enablePlugins(GatlingPlugin)

javaOptions in Gatling ++= Seq("-Xmx2G")