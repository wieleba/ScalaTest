package pl.wieleba.payroll.transactions

import java.util.Date
import pl.wieleba.payroll.classifications.{CommissionedClassification, HourlyClassification}
import pl.wieleba.payroll.{SalesReceipt, PayrollDatabase}

/**
 * Created by IntelliJ IDEA.
 * User: edsfq
 * Date: 01.02.11
 * Time: 22:28
 * To change this template use File | Settings | File Templates.
 */

class SalesReceiptTransaction(val payDay: Date, val amount: Double, val empId: Int) extends Transaction {

  override def execute() {
    val e = PayrollDatabase.getEmployee(empId)
    val c = e.classification
    c.asInstanceOf[CommissionedClassification] addSalesReceipt (new SalesReceipt(payDay, amount))
  }

}