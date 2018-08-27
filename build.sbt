import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.6",
      version      := "0.0.1",
      scalacOptions := Seq("-deprecation")
    )),
    name := "kipkafka2",
    libraryDependencies += scalaTest % Test,
    libraryDependencies ++= Seq(
      "org.apache.kafka" % "kafka-clients" % "0.11.0.0",
      "com.lightbend" %%  "kafka-streams-scala" % "0.2.1",
      "org.apache.kafka" % "kafka-streams" % "1.1.0",
      "io.circe" %% "circe-parser" % "0.9.3",
      "io.circe" %% "circe-generic" % "0.9.3",
      "io.circe" %% "circe-parser" % "0.9.3"
    )
  )