package pl.wieleba.payroll.transactions.employee.data

import pl.wieleba.payroll.classifications.{HourlyClassification, PaymentClassification}
import pl.wieleba.payroll.schedule.{WeeklySchedule, PaymentSchedule}

/**
 * Created by IntelliJ IDEA.
 * User: edsfq
 * Date: 14.02.11
 * Time: 02:31
 * To change this template use File | Settings | File Templates.
 */

class ChangeHourlyTransaction(empId: Int, val hourlyRate: Double) extends ChangeClassificationTransaction(empId) {

  override def getClassification(): PaymentClassification = new HourlyClassification(hourlyRate)

  override def getSchedule(): PaymentSchedule = new WeeklySchedule()

}