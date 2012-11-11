package pl.wieleba.payroll.transactions

import pl.wieleba.payroll.{Paycheck, Employee, PayrollDatabase}
import java.util.{Date}
import collection.mutable.HashMap
import org.slf4j.LoggerFactory

/**
 * User: edsfq
 * Date: 14.02.11
 * Time: 01:34
 */

class PaydayTransaction(val payDay: Date) extends Transaction {

  private val log = LoggerFactory.getLogger(classOf[PaydayTransaction])
  private val paychecks = new HashMap[Int, Paycheck]

  override def execute() = {
    PayrollDatabase.getEmployees.foreach((e: Employee) => {
      if (e.isPayDay(payDay)) {
        log.info("adding new pay day for {} at day {}", e, payDay)
        val startDate = e.getPayPeriodStartDate(payDay)
        val pc = new Paycheck(startDate, payDay)
        paychecks += e.id -> pc
        e.payday(pc)
      }
    })
  }

  def getPaycheck(empId: Int): Paycheck = {
    log.info("retrieving {}", empId)
    if (!paychecks.contains(empId)) {
      log.warn("Paycheck for employee {} not found", empId)
      None
    }
    paychecks(empId)
  }
}