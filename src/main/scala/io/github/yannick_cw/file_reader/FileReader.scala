package io.github.yannick_cw.file_reader

import scala.io.Source

object FileReader {

  val uppercaseFirst: String => String  = name => name.substring(0, 1).toUpperCase + name.substring(1).toLowerCase
  lazy val firstNames: Vector[String]   = readNames("names/first_names").map(uppercaseFirst)
  lazy val lastNames: Vector[String]    = readNames("names/last_names").map(uppercaseFirst)
  lazy val emailDomains: Vector[String] = readNames("email/domains")
  lazy val urls: Vector[String]         = readNames("urls/top_urls")

  private def readNames(file: String): Vector[String] =
    Source.fromResource(file).getLines().toVector
}
