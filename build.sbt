import Dependencies._

lazy val root = (project in file(".")).settings(
  inThisBuild(
    List(
      organization := "io.github.yannick-cw",
      scalaVersion := "2.12.3",
      version := "0.0.1"
    )),
  name := "sane-gen",
  libraryDependencies ++= Seq(
    scalacheck,
    scalaTest % Test
  )
)
