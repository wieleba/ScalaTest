package pl.wieleba.payroll.transactions.employee.data

import pl.wieleba.payroll.schedule.BiweeklySchedule
import pl.wieleba.payroll.classifications.CommissionedClassification

/**
 * Created by IntelliJ IDEA.
 * User: edsfq
 * Date: 14.02.11
 * Time: 02:04
 * To change this template use File | Settings | File Templates.
 */

class ChangeCommissionedTransaction(empId: Int, val salary: Double,
                                    val commissionRate: Double) extends ChangeClassificationTransaction(empId) {

  override def getSchedule() = new BiweeklySchedule

  override def getClassification() = new CommissionedClassification(salary, commissionRate)
}