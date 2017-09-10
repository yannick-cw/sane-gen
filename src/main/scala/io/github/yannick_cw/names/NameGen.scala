package io.github.yannick_cw.names

import io.github.yannick_cw.file_reader.FileReader
import org.scalacheck.Gen

object NameGen {

  private lazy val firstNames = FileReader.firstNames
  private lazy val lastNames  = FileReader.lastNames

  case class FirstName(name: String) extends AnyVal
  case class LastName(name: String)  extends AnyVal
  case class FullName(name: String)  extends AnyVal

  val firstName: Gen[FirstName] = Gen.oneOf(firstNames).map(FirstName)

  val lastName: Gen[LastName] = Gen.oneOf(lastNames).map(LastName)

  val fullName: Gen[FullName] = for {
    firstN <- firstName
    lastN  <- lastName
  } yield FullName(firstN.name + " " + lastN.name)
}
