package simplejoda

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.joda.time.DateTime

@RunWith(classOf[JUnitRunner])
class parseTest extends FunSuite {

  // Mon Jan 2 15:04:05 MST 2006
  val example = new DateTime(2006, 1, 2, 15, 4, 5, 0)

  private def testFormat(pattern: String) = {
    val form = simplejoda.format(pattern)
    assert(example.toString(form) == pattern)
  }

  test("Handle year, both short and long") {
    testFormat("06")
    testFormat("2006")
    // fail on invalid years
    intercept[Exception] {
      testFormat("2005")
    }
    intercept[Exception] {
      testFormat("1999")
    }
  }

  test("general punctuation handling") {
    testFormat("-")
    testFormat(":")
    testFormat(" ")
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
    intercept[Exception] {
      testFormat("03")
    }
  }

  test("handle hour of day") {
    testFormat("15")
  }

  test("handle minute of hour") {
    testFormat("04")
  }

  test("handle second of minute") {
    testFormat("05")
  }

  test("handle millisecond") {
    testFormat("0")
    testFormat("00")
    testFormat("000")
  }

  test("handle literals") {
    testFormat("Z")
    testFormat("T")
  }
}

