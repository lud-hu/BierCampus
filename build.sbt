name := """play-java"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs
)

// https://mvnrepository.com/artifact/mysql/mysql-connector-java
libraryDependencies += "mysql" % "mysql-connector-java" % "6.0.5"

//http://stackoverflow.com/questions/15657062/play-framework-2-best-way-to-store-password-hash-of-user
libraryDependencies += "org.mindrot" % "jbcrypt" % "0.3m"

lazy val myProject = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

fork in run := false