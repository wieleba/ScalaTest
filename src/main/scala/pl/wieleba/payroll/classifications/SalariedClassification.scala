package pl.wieleba.payroll.classifications

;


import pl.wieleba.payroll.Paycheck
;

class SalariedClassification(var salary: Double) extends PaymentClassification {

  override def calculatePay(pc: Paycheck): Double = salary;

}
