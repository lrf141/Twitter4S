name := "Twitter4S"

version := "1.0.0"

scalaVersion := "2.12.3"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "commons-codec" % "commons-codec" % "1.9",
  "io.circe" %% "circe-core" % "0.8.0",
  "io.circe" %% "circe-generic" % "0.8.0",
  "io.circe" %% "circe-parser" % "0.8.0"
)

publishTo := Some(Resolver.file("Twitter4S",file("./twitter4s"))(Patterns(true, Resolver.mavenStyleBasePattern)))