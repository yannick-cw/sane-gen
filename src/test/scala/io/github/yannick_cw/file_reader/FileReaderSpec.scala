package io.github.yannick_cw.file_reader

import io.github.yannick_cw.SaneGen
import org.scalatest.{FlatSpec, Inspectors, Matchers, OptionValues}

class FileReaderSpec extends FlatSpec with Matchers with Inspectors with OptionValues {

  behavior of "The file reader"

  it should "read all first names" in (FileReader.firstNames should have length 1780)

  it should "read all last names" in (FileReader.lastNames should have length 4760)

  it should "have the first character uppercased and rest lowercased for names" in {
    forAll(FileReader.lastNames ++ FileReader.firstNames) { name =>
      name.headOption.value.isUpper shouldBe true
      forAll(name.toList.tail)(trailing => trailing.isUpper shouldBe false)
    }
  }

  it should "read all email domains" in (FileReader.emailDomains should have length 132)
}
