package io.github.yannick_cw

import io.github.yannick_cw.emails.EmailGen
import io.github.yannick_cw.emails.EmailGen.Email
import io.github.yannick_cw.names.NameGen
import io.github.yannick_cw.names.NameGen.{FirstName, FullName, LastName}
import org.scalacheck.Gen

trait SaneGen {

  /**
    * Generators for the most common american names
    */
  val firstName: Gen[FirstName] = NameGen.firstName
  val lastName: Gen[LastName]   = NameGen.lastName
  val fullName: Gen[FullName]   = NameGen.fullName

  /**
    * An email generator using common domains and combinations of names + special characters
    *  to generate valid email addresses
    */
  val email: Gen[Email] = EmailGen.email
}

object SaneGen extends SaneGen
