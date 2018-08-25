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
      "com.lightbend" %%  "kafka-streams-scala" % "0.2.1",
      "org.apache.kafka" % "kafka-streams" % "1.1.0"
    )
  )
