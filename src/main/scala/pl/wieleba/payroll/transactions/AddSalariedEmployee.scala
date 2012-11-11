package pl.wieleba.payroll.transactions

;

import pl.wieleba.payroll.schedule.MonthlySchedule;
import pl.wieleba.payroll.classifications.SalariedClassification;


class AddSalariedEmployee(empId: Int, name: String, address: String, val amount: Double)
  extends AddEmployeeTransaction(empId, name, address) {

  override def makeSchedule() = new MonthlySchedule;


  override def makeClassification() = new SalariedClassification(amount);

}