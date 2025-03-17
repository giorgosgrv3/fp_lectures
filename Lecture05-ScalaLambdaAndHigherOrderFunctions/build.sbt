ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.1"

lazy val root = (project in file("."))
  .settings(
    name := "ScalaLambdaAndHigherOrderFunctions"
  )
libraryDependencies ++= Seq(
  "io.circe" % "circe-core_3" % "0.14.1",
  "io.circe" % "circe-parser_3" % "0.14.1"
)