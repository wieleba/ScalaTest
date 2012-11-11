package pl.wieleba.payroll.transactions.employee.data

import pl.wieleba.payroll.Employee
import pl.wieleba.payroll.schedule.PaymentSchedule
import pl.wieleba.payroll.classifications.PaymentClassification

/**
 * Created by IntelliJ IDEA.
 * User: edsfq
 * Date: 14.02.11
 * Time: 02:27
 * To change this template use File | Settings | File Templates.
 */

abstract class ChangeClassificationTransaction(empId: Int) extends ChangeEmployeeTransaction(empId) {
  override def change(e: Employee) {
    e.classification = getClassification()
    e.schedule = getSchedule()
  }

  def getSchedule(): PaymentSchedule

  def getClassification(): PaymentClassification

}