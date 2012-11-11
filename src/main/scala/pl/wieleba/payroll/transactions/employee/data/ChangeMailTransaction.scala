package pl.wieleba.payroll.transactions.employee.data

import pl.wieleba.payroll.methods.MailMethod

/**
 * Created by IntelliJ IDEA.
 * User: edsfq
 * Date: 14.02.11
 * Time: 01:48
 * To change this template use File | Settings | File Templates.
 */

class ChangeMailTransaction(empId: Int, val address: String) extends ChangeMethodTransaction(empId) {
  override def getMethod() = new MailMethod(address)
}