package pl.wieleba.payroll.transactions.employee.data

import pl.wieleba.payroll.methods.HoldMethod

/**
 * Created by IntelliJ IDEA.
 * User: edsfq
 * Date: 14.02.11
 * Time: 01:50
 * To change this template use File | Settings | File Templates.
 */

class ChangeHoldTransaction(empId: Int) extends ChangeMethodTransaction(empId) {
  override def getMethod() = new HoldMethod
}