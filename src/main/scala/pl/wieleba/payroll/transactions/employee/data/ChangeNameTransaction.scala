package pl.wieleba.payroll.transactions.employee.data

import pl.wieleba.payroll.Employee

/**
 * Created by IntelliJ IDEA.
 * User: edsfq
 * Date: 13.02.11
 * Time: 20:22
 * To change this template use File | Settings | File Templates.
 */

class ChangeNameTransaction(empId: Int, val name: String)
  extends ChangeEmployeeTransaction(empId) {

  override def change(e: Employee) {
    e.name = name
  }

}