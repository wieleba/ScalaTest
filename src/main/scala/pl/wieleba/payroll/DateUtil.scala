package pl.wieleba.payroll

import java.util.{Calendar, Date}

/**
 * User: edsfq
 * Date: 21.08.11
 * Time: 22:37 
 */

object DateUtil {

  def isInPeriod(theDate: Date, payPeriodStart: Date, payPeriodEnd: Date) =
    theDate.compareTo(payPeriodEnd) <= 0 && theDate.compareTo(payPeriodStart) >= 0

  def numberOfFridaysInPeriod(start: Date, end: Date): Int = {
    val startCalendar = Calendar.getInstance
    val endCalendar = Calendar.getInstance
    startCalendar.setTime(start)
    endCalendar.setTime(end)
    var fridays = 0
    while (startCalendar.before(endCalendar)) {
      if (startCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
        fridays = fridays + 1
      }
      startCalendar.add(Calendar.DAY_OF_YEAR, 1)
    }
    fridays
  }
}