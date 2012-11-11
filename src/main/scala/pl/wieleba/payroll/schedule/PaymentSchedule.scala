package pl.wieleba.payroll.schedule

import java.util.Date
import pl.wieleba.payroll.Paycheck

/**
 * Created by IntelliJ IDEA.
 * User: edsfq
 * Date: 26.01.11
 * Time: 22:44
 * To change this template use File | Settings | File Templates.
 */

abstract class PaymentSchedule {
  def isPayDay(day: Date): Boolean

  def getPayPeriodStartDate(d: Date): Date

  def getPayPeriodEndDate(pc: Paycheck): Date = pc.payPeriodEnd
}