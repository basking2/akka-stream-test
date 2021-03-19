
val AkkaVersion = "2.6.13"
lazy val root = (project in file("."))
  .settings(
    name := "Hello",
    scalaVersion := "2.12.13",
    libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
    "org.scalactic" %% "scalactic" % "3.2.5",
    "com.typesafe.akka" %% "akka-stream-testkit" % AkkaVersion % Test,
    "org.scalatest" %% "scalatest" % "3.2.5" % "test"
  )
)
