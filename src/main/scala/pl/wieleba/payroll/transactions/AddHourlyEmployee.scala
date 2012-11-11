package pl.wieleba.payroll.transactions

import pl.wieleba.payroll.schedule.WeeklySchedule
import pl.wieleba.payroll.classifications.HourlyClassification

/**
 * Created by IntelliJ IDEA.
 * User: edsfq
 * Date: 26.01.11
 * Time: 23:01
 * To change this template use File | Settings | File Templates.
 */

class AddHourlyEmployee(empId: Int, name: String, address: String, val hourlyRate: Double)
  extends AddEmployeeTransaction(empId, name, address) {

  override def makeSchedule() = new WeeklySchedule

  override def makeClassification() = new HourlyClassification(hourlyRate)

}