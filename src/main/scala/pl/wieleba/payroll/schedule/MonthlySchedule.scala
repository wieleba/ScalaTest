package pl.wieleba.payroll.schedule

;

import java.util.{Date, Calendar};

class MonthlySchedule extends PaymentSchedule {
  override def isPayDay(day: Date): Boolean = {
    val c = Calendar.getInstance;
    c.setTime(day);
    c.get(Calendar.DAY_OF_MONTH) == 1;
  };

  override def getPayPeriodStartDate(day: Date): Date = {
    val c = Calendar.getInstance;
    c.setTime(day);
    c.set(Calendar.DAY_OF_MONTH, 1);
    c.add(Calendar.MONTH, -1);
    c.getTime;
  };
}