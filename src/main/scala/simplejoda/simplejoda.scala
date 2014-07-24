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
   * @param format: the standard date formatted in the desired datetime format.
   * @return A joda.format.DateTimeFormatter for the desired datetime format.
   */
  /*

 G       era                          text          AD
 C       century of era (>=0)         number        20
 Y       year of era (>=0)            year          1996

 x       weekyear                     year          1996
 w       week of weekyear             number        27
 e       day of week                  number        2
 E       day of week                  text          Tuesday; Tue

 y       year                         year          1996
 D       day of year                  number        189
 M       month of year                month         July; Jul; 07
 d       day of month                 number        10

 a       halfday of day               text          PM
 K       hour of halfday (0~11)       number        0
 h       clockhour of halfday (1~12)  number        12

 H       hour of day (0~23)           number        0
 k       clockhour of day (1~24)      number        24
 m       minute of hour               number        30
 s       second of minute             number        55
 S       fraction of second           number        978

 z       time zone                    text          Pacific Standard Time; PST
 Z       time zone offset/id          zone          -0800; -08:00; America/Los_Angeles

 '       escape for text              delimiter
 ''      single quote                 literal       '
 */
  // Mon Jan 2 15:04:05 MST 2006
  private val punctuation = """( |:|-)(.*)""".r
  private val year =  """(06|2006)(.*)""".r
  private val month =  """(01|Jan|January)(.*)""".r

  @throws(classOf[Exception])
  def format(format: String): DateTimeFormatter = {
    def gen(str: String): String = str match {
      // handle punctuation & EOL
      case "" => ""
      case punctuation(c, t) => c + gen(t)
      case year(y, t) => "Y" * y.size + gen(t)
      case month(m, t) => "M" * m.size.max(3) + gen(t)
    }
    DateTimeFormat.forPattern(gen(format))
  }
}


