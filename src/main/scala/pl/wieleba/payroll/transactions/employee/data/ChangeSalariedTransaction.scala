package pl.wieleba.payroll.transactions.employee.data

import pl.wieleba.payroll.schedule.MonthlySchedule
import pl.wieleba.payroll.classifications.SalariedClassification

/**
 * Created by IntelliJ IDEA.
 * User: edsfq
 * Date: 14.02.11
 * Time: 01:59
 * To change this template use File | Settings | File Templates.
 */

class ChangeSalariedTransaction(empId: Int, val salary: Double) extends ChangeClassificationTransaction(empId) {

  override def getSchedule() = new MonthlySchedule

  override def getClassification() = new SalariedClassification(salary)

}