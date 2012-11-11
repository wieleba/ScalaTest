package pl.wieleba.payroll.schedule

import java.util.{Calendar, Date}
import pl.wieleba.payroll.Paycheck
;

/**
 * Created by IntelliJ IDEA.
 * User: edsfq
 * Date: 26.01.11
 * Time: 23:00
 * To change this template use File | Settings | File Templates.
 */

class WeeklySchedule extends PaymentSchedule {
  override def isPayDay(day: Date): Boolean = {
    val c = Calendar.getInstance
    c.setTime(day)
    c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY
  }

  override def getPayPeriodStartDate(day: Date): Date = {
    val c = Calendar.getInstance;
    c.setTime(day);
    c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    c.getTime
  }

  override def getPayPeriodEndDate(pc: Paycheck): Date = {
    val c = Calendar.getInstance
    c.setTime(pc.payDay)
    c.add(Calendar.DAY_OF_YEAR, 1)
    c.getTime
  }
}
