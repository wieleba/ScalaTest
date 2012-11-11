package pl.wieleba.payroll.methods

;

import pl.wieleba.payroll.Paycheck;


class HoldMethod extends PaymentMethod {
  override def pay(pc: Paycheck) = pc.addField("Disposition" -> "Hold")
}