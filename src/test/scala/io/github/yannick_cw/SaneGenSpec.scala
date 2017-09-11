package io.github.yannick_cw

import io.github.yannick_cw.file_reader.FileReader
import org.scalatest.prop.PropertyChecks
import org.scalatest.{Assertion, FlatSpec, Matchers}

class SaneGenSpec extends FlatSpec with Matchers with PropertyChecks {

  val firstNames = FileReader.firstNames
  val lastNames  = FileReader.lastNames
  val mailRegex =
    """(?i)(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])""".r
  val urlRegex =
    """(?:(?:https?|ftp|file):\/\/|www\.)[-a-zA-Z0-9+&@#/%?=~_|!:,.;]+\.[-a-zA-Z0-9+&@#/%?=~_|!:,.;]{1,6}\/?(?:[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*)?""".r

  val isFirstName: String => Assertion  = firstNames should contain(_)
  val isLastName: String => Assertion   = lastNames should contain(_)
  val isValidEmail: String => Assertion = mailRegex.findAllMatchIn(_) should have size 1
  val isValidUrl: String => Assertion   = urlRegex.findAllMatchIn(_) should have size 1

  behavior of "SaneGen for names"

  it should "generate full names" in {
    forAll(SaneGen.fullName) { fullName =>
      val splitName = fullName.name.split(" ")
      splitName should have length 2
      isFirstName(splitName.head)
      isLastName(splitName(1))
    }
  }

  it should "generate first names" in forAll(SaneGen.firstName)(isFirstName.compose(_.name))

  it should "generate last names" in forAll(SaneGen.lastName)(isLastName.compose(_.name))

  behavior of "SaneGen for emails"

  it should "generate valid emails" in forAll(SaneGen.email)(isValidEmail.compose(_.mail))

  behavior of "SaneGen for urls"

  it should "generate valid urls" in forAll(SaneGen.url)(isValidUrl.compose(_.url))
}
