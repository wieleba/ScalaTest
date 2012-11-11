package pl.wieleba.payroll.schedule

import java.util.{Calendar, Date}

/**
 * Created by IntelliJ IDEA.
 * User: edsfq
 * Date: 26.01.11
 * Time: 23:20
 * To change this template use File | Settings | File Templates.
 */

class BiweeklySchedule extends PaymentSchedule {
  override def isPayDay(day: Date): Boolean = {
    val c = Calendar.getInstance
    c.setTime(day)
    (c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY
      && c.get(Calendar.DAY_OF_WEEK_IN_MONTH) % 2 == 1)
  }

  override def getPayPeriodStartDate(day: Date): Date = {
    val c = Calendar.getInstance;
    c.setTime(day);
    c.add(Calendar.DAY_OF_YEAR, -14) //cofam o 2 tyg.
    c.set(Calendar.DAY_OF_WEEK, 1);
    c.getTime
  }
}