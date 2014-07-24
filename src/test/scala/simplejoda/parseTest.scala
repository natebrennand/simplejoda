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
    intercept[Exception] {
      testFormat("2005")
    }
  }

  test("beak") {
  }
}

