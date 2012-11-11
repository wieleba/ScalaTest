package pl.wieleba.payroll.transactions.employee.data

import pl.wieleba.payroll.Employee
import pl.wieleba.payroll.affiliations.Affiliation

/**
 * Created by IntelliJ IDEA.
 * User: edsfq
 * Date: 14.02.11
 * Time: 01:54
 * To change this template use File | Settings | File Templates.
 */

abstract class ChangeAffiliationTransaction(empId: Int) extends ChangeEmployeeTransaction(empId) {

  override def change(e: Employee) {
    recordMembership(e)
    e.affiliation = getAffiliation()
  }

  def getAffiliation(): Affiliation

  def recordMembership(e: Employee)

}