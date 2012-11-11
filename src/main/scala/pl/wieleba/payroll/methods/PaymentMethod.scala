package pl.wieleba.payroll.methods

import pl.wieleba.payroll.Paycheck
;

/**
 * Created by IntelliJ IDEA.
 * User: edsfq
 * Date: 26.01.11
 * Time: 22:44
 * To change this template use File | Settings | File Templates.
 */

abstract class PaymentMethod {
  def pay(pc: Paycheck)
}