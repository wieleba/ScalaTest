package pl.wieleba.payroll.transactions

import pl.wieleba.payroll.methods.HoldMethod
import pl.wieleba.payroll.classifications.PaymentClassification
import pl.wieleba.payroll.schedule.PaymentSchedule
import pl.wieleba.payroll.{PayrollDatabase, Employee}
import pl.wieleba.payroll.affiliations.NoAffiliation


/**
 * Created by IntelliJ IDEA.
 * User: edsfq
 * Date: 26.01.11
 * Time: 23:30
 * To change this template use File | Settings | File Templates.
 */

abstract class AddEmployeeTransaction(val empId: Int, val name: String, val address: String)
  extends Transaction {

  override def execute() {
    val e = new Employee(empId, name, address)
    e.schedule = makeSchedule()
    e.method = new HoldMethod
    e.classification = makeClassification()
    e.affiliation = makeAffiliation()

    PayrollDatabase.addEmployee(e)
  }

  protected def makeSchedule(): PaymentSchedule

  protected def makeClassification(): PaymentClassification

  protected def makeAffiliation() = new NoAffiliation
}