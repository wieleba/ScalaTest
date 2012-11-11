package pl.wieleba.payroll.classifications

import collection.mutable.HashMap
import java.util.Date
import pl.wieleba.payroll.{Paycheck, SalesReceipt}

/**
 * Created by IntelliJ IDEA.
 * User: edsfq
 * Date: 26.01.11
 * Time: 23:21
 * To change this template use File | Settings | File Templates.
 */

class CommissionedClassification(val salary: Double, val commissionRate: Double) extends PaymentClassification {

  private var receipts = new HashMap[Date, SalesReceipt]

  def addSalesReceipt(salesReceipt: SalesReceipt) = receipts += salesReceipt.payDay -> salesReceipt

  def getSalesReceipt(d: Date) = receipts.get(d).get

  override def calculatePay(pc: Paycheck) = salary

}