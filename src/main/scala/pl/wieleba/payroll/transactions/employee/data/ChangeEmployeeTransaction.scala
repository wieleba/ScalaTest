package pl.wieleba.payroll.transactions.employee.data

import pl.wieleba.payroll.transactions.Transaction
import pl.wieleba.payroll.{Employee, PayrollDatabase}

/**
 * Created by IntelliJ IDEA.
 * User: edsfq
 * Date: 13.02.11
 * Time: 20:17
 * To change this template use File | Settings | File Templates.
 */

abstract class ChangeEmployeeTransaction(val empId: Int) extends Transaction {
  override def execute() {
    val e = PayrollDatabase.getEmployee(empId)
    if (null != e) {
      change(e)
    } else {
      throw new IllegalArgumentException("Employee " + empId + "not found")
    }
  }

  def change(e: Employee)
}