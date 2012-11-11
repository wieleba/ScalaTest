package pl.wieleba.payroll.methods

import pl.wieleba.payroll.Paycheck

/**
 * Created by IntelliJ IDEA.
 * User: edsfq
 * Date: 14.02.11
 * Time: 01:37
 * To change this template use File | Settings | File Templates.
 */

class DirectMethod(val bank: String, val account: String) extends PaymentMethod {
  override def pay(pc: Paycheck) = print("paying direct " + pc.grossPay)

}