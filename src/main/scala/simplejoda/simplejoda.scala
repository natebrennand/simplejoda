package simplejoda

import org.joda.time.format.{DateTimeFormat, DateTimeFormatter}

object simplejoda {
  /**
   * Generates a datetime formatter based upon a standard date.
   *
   * Standard date: Mon Jan 2 15:04:05 MST 2006
   *
   * Reference for Joda used:
   * http://joda-time.sourceforge.net/apidocs/org/joda/time/format/DateTimeFormat.html
   *
   * format: the standard date formatted in the desired datetime format.
   * @return A joda.format.DateTimeFormatter for the desired datetime format.
   */
  // STANDARD: Mon Jan 2 15:04:05 MST 2006
  private val punctuation = """( |:|-(?!07)|\.|/)(.*)""".r
  private val year = """(2006|06)(.*)""".r
  private val month = """(January|Jan|01)(.*)""".r
  private val dayOfWeek = """(Monday|Mon)(.*)""".r
  private val day = """(2|02)(.*)""".r
  private val hour = """(15)(.*)""".r       // TODO, how do we handle AM/PM?
  private val minute = """(04)(.*)""".r
  private val second = """(05)(.*)""".r
  private val millisecond = """(0{1,3})(.*)""".r
  private val literals = """(T|Z)(.*)""".r
  private val timezone = """(MST)(.*)""".r
  private val timezoneOffset = """(-0700|-07:00)(.*)""".r

  @throws(classOf[Exception])
  def format(format: String): DateTimeFormatter = {
    def gen(str: String): String = str match {
      case "" => ""
      case punctuation(c, t) => c + gen(t)
      case year(y, t) => "y" * y.size + gen(t)
      case month(m, t) => "M" * m.size + gen(t)
      case dayOfWeek(d, t) => "E" * d.size + gen(t)
      case day(d, t) => "d" * d.size + gen(t)
      case hour(_, t) => "HH" + gen(t)
      case minute(_, t) => "mm" + gen(t)
      case second(s, t) => "ss" + gen(t)
      case millisecond(s, t) => "S" * s.size + gen(t)
      case literals(l, t) => s"'$l'" + gen(t)
      case timezone(z, t) => "z" + gen(t)
      case timezoneOffset(z, t) => "Z" * (z.size - 4) + gen(t)
    }
    DateTimeFormat.forPattern(gen(format))
  }
}