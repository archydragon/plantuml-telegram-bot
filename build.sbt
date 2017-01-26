name := "PlantBot"
version := "0.1"

scalaVersion := "2.11.8"

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "info.mukel" %% "telegrambot4s" % "2.1.0-SNAPSHOT",
  "ch.qos.logback" % "logback-classic" % "1.1.7",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0"
)
