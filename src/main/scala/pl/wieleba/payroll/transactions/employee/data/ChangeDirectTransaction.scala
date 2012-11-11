package pl.wieleba.payroll.transactions.employee.data

import pl.wieleba.payroll.methods.DirectMethod

/**
 * Created by IntelliJ IDEA.
 * User: edsfq
 * Date: 14.02.11
 * Time: 01:45
 * To change this template use File | Settings | File Templates.
 */

class ChangeDirectTransaction(empId: Int, val bank: String, val account: String) extends ChangeMethodTransaction(empId) {

  override def getMethod() = new DirectMethod(bank, account)

}