package pl.wieleba.payroll.transactions.employee.data

import pl.wieleba.payroll.Employee
import pl.wieleba.payroll.methods.PaymentMethod

/**
 * Created by IntelliJ IDEA.
 * User: edsfq
 * Date: 14.02.11
 * Time: 01:32
 * To change this template use File | Settings | File Templates.
 */

abstract class ChangeMethodTransaction(empId: Int) extends ChangeEmployeeTransaction(empId) {

  override def change(e: Employee) {
    e.method = getMethod()
  }

  def getMethod(): PaymentMethod

}