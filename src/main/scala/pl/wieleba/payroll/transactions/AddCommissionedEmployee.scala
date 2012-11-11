package pl.wieleba.payroll.transactions

import pl.wieleba.payroll.classifications.CommissionedClassification
import pl.wieleba.payroll.schedule.BiweeklySchedule

/**
 * Created by IntelliJ IDEA.
 * User: edsfq
 * Date: 26.01.11
 * Time: 23:21
 * To change this template use File | Settings | File Templates.
 */

class AddCommissionedEmployee(empId: Int, name: String, address: String, val salary: Double,
                              val commissionRate: Double) extends AddEmployeeTransaction(empId, name, address) {

  override def makeSchedule() = new BiweeklySchedule;


  override def makeClassification() = new CommissionedClassification(salary, commissionRate)

}