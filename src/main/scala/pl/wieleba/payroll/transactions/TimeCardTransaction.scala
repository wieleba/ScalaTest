package pl.wieleba.payroll.transactions

import java.util.Date
import pl.wieleba.payroll.classifications.HourlyClassification
import pl.wieleba.payroll.{TimeCard, PayrollDatabase}

/**
 * Created by IntelliJ IDEA.
 * User: edsfq
 * Date: 01.02.11
 * Time: 22:44
 * To change this template use File | Settings | File Templates.
 */

class TimeCardTransaction(val payDay: Date, val hours: Double, val empId: Int)
  extends Transaction {

  override def execute() {
    val e = PayrollDatabase.getEmployee(empId)
    val c = e.classification
    c.asInstanceOf[HourlyClassification] addTimeCard (new TimeCard(payDay, hours))
  }

}