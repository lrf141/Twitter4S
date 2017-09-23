name := "Twitter4S"

version := "1.0.0"

scalaVersion := "2.12.3"

resolvers += "Maven Repo on github" at "https://lrf141.github.io/twitter4s/"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "commons-codec" % "commons-codec" % "1.9",
  "io.circe" %% "circe-core" % "0.8.0",
  "io.circe" %% "circe-generic" % "0.8.0",
  "io.circe" %% "circe-parser" % "0.8.0",
  "twitter4s" %% "twitter4s-2.12" % "1.0.0"
)

publishTo := Some(Resolver.file("twitter4s",file("./"))(Patterns(true, Resolver.mavenStyleBasePattern)))