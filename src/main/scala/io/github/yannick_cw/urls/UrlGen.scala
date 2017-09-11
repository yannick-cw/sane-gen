package io.github.yannick_cw.urls

import io.github.yannick_cw.file_reader.FileReader
import org.scalacheck.Gen
import org.scalacheck.Gen.freqTuple

object UrlGen {

  private lazy val urls = FileReader.urls
  private val protocolFrequency =
    List((10, "http://"), (10, "https://"), (1, "ftp://"), (1, "file://")).map(freqTuple)

  case class Url(url: String) extends AnyVal

  val url: Gen[Url] =
    for {
      url      <- Gen.oneOf(urls)
      protocol <- Gen.frequency(protocolFrequency: _*)
      www      <- Gen.oneOf("www.", "")
    } yield Url(protocol + www + url)
}
