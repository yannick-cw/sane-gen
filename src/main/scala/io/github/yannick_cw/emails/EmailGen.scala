package io.github.yannick_cw.emails

import io.github.yannick_cw.file_reader.FileReader
import io.github.yannick_cw.names.NameGen
import org.scalacheck.Gen

object EmailGen {

  private lazy val domains = FileReader.emailDomains
  private val separators =
    Vector("!", "#", "$", "%", "&", "'", "*", "+", "-", "/", "=", "?", "^", "_", "`", "{", "|", "}", "~", ";")

  case class Email(mail: String) extends AnyVal

  val email: Gen[Email] = for {
    domain         <- Gen.oneOf(domains)
    firstName      <- NameGen.firstName
    lastName       <- NameGen.lastName
    firstOrInitial <- Gen.option(Gen.oneOf(firstName.name, firstName.name.head.toString))
    lastOrInitial  <- Gen.option(Gen.oneOf(lastName.name, lastName.name.head.toString))
    maybeNumber    <- Gen.option(Gen.posNum[Int])
    separator      <- Gen.oneOf(separators)
    Seq(n1, n2)    <- Gen.pick(2, List(firstOrInitial, lastOrInitial))
    if firstOrInitial.isDefined || lastOrInitial.isDefined || maybeNumber.isDefined
    firstN = n1.map(_ + separator).getOrElse("")
    lastN  = n2.getOrElse("")
    number = maybeNumber.map(_.toString).getOrElse("")
  } yield {
    Email(firstN + lastN + number + "@" + domain)
  }
}
