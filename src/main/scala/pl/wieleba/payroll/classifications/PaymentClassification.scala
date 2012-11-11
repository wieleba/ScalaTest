package pl.wieleba.payroll.classifications

import java.util.Date
import pl.wieleba.payroll.{DateUtil, Paycheck}
;

/**
 * Created by IntelliJ IDEA.
 * User: edsfq
 * Date: 26.01.11
 * Time: 22:43
 * To change this template use File | Settings | File Templates.
 */

abstract class PaymentClassification {
  def calculatePay(pc: Paycheck): Double

  def isInPayPeriod(date: Date, pc: Paycheck): Boolean = {
    val payPeriodEnd = pc.payDay
    val payPeriodStart = pc.payPeriodStart

    DateUtil.isInPeriod(date, payPeriodStart, payPeriodEnd)
  }
}