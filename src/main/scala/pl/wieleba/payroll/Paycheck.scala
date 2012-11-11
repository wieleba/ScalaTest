package pl.wieleba.payroll

import java.util.Date
import scala.collection.mutable.HashMap

/**
 * User: edsfq
 * Date: 14.02.11
 * Time: 02:10 
 */

class Paycheck(val payPeriodStart: Date, var payDay: Date) {

  var payPeriodEnd = payDay
  var grossPay: Double = 0.0
  var deductions: Double = 0.0
  var netPay: Double = 0.0
  private val fields = new HashMap[String, String]

  def addField(key: String, value: String) = fields += key -> value

  def addField(p: Pair[String, String]) = fields += p

  def getField(s: String) = fields(s)

}