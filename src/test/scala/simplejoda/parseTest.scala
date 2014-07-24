package simplejoda

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.joda.time.{DateTimeZone, DateTime}

@RunWith(classOf[JUnitRunner])
class parseTest extends FunSuite {

  // Mon Jan 2 15:04:05 MST 2006
  val example = new DateTime(2006, 1, 2, 15, 4, 5, 0, DateTimeZone.forID("MST"))

  private def testFormat(pattern: String) = {
    val form = simplejoda.format(pattern)
    assert(example.toString(form) == pattern)
  }

  test("handle empty format throws exception") {
    intercept[Exception] {
      testFormat("")
    }
  }

  test("general punctuation handling") {
    testFormat(".")
    testFormat("-")
    testFormat(":")
    testFormat(" ")
    testFormat("/")
  }

  test("Handle year, both short and long") {
    testFormat("06")
    testFormat("2006")
    intercept[Exception] {
      testFormat("2005")
    }
    intercept[Exception] {
      testFormat("1999")
    }
  }

  test("handle short, long and numerical months") {
    testFormat("January")
    testFormat("Jan")
    testFormat("01")
    intercept[Exception] {
      testFormat("Feb")
    }
  }

  test("handle day of week") {
    testFormat("Mon")
    testFormat("Monday")
    intercept[Exception] {
      testFormat("Tues")
    }
  }

  test("handle day of the month") {
    testFormat("2")
    testFormat("02")
  }

  test("handle hour of day") {
    testFormat("15")
    testFormat("03")
    testFormat("03PM")
    testFormat("03 PM")
    testFormat("3")
    testFormat("3 PM")
  }

  test("handle minute of hour") {
    testFormat("04")
  }

  test("handle second of minute") {
    testFormat("05")
  }

  test("handle second decimal") {
    testFormat("0")
    testFormat("00")
    testFormat("000")
    testFormat("0000")
    testFormat("00000")
    testFormat("000000")
    testFormat("0000000")
    testFormat("00000000")
    testFormat("000000000")
  }

  test("handle literals") {
    testFormat("Z")
    testFormat("T")
  }

  test("handle time zone string") {
    testFormat("MST")
  }

  test("handle time zone offset") {
    testFormat("-07:00")
    testFormat("-0700")
  }

  test("handle era") {
    testFormat("AD")
    intercept[Exception]{
      testFormat("BC")
    }
  }

 test("small combinations") {
    testFormat("15:04")
  }

  test("ISO 8601 GMT") {
    // Mon Jan 2 15:04:05 MST 2006
    testFormat("2006-01-02T15:04:05.000Z")
  }
}
