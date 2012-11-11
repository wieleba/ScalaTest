package pl.wieleba.payroll.transactions

import pl.wieleba.payroll.PayrollDatabase

/**
 * Created by IntelliJ IDEA.
 * User: edsfq
 * Date: 26.01.11
 * Time: 22:32
 * To change this template use File | Settings | File Templates.
 */

class DeleteEmployeeTransaction(val empId: Int) extends Transaction {

  override def execute() {
    PayrollDatabase.deleteEmployee(empId)
  }

}