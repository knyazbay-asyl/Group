ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "Univer"
  )

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.3.3",
  "org.postgresql" % "postgresql" % "42.3.4",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.3",
  "com.github.tminglei" %% "slick-pg" % "0.20.3",
  "com.github.tminglei" %% "slick-pg_play-json" % "0.20.3",
  "org.json4s" %% "json4s-native" % "3.6.11",
  "org.json4s" %% "json4s-jackson" % "3.6.11",
  "com.typesafe.akka" %% "akka-actor" % "2.6.16",

  "com.typesafe.akka" %% "akka-http" % "10.2.10",
  "com.typesafe.akka" %% "akka-stream" % "2.6.16",
  "de.heikoseeberger" %% "akka-http-json4s" % "1.37.0",

)


libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.33"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3" // Используйте актуальную версию


libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json" % "10.5.0"
libraryDependencies += "com.typesafe.akka" %% "akka-http" % "10.5.0"
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.8.0"

libraryDependencies += "com.github.tminglei" %% "slick-pg" % "0.19.4"