package pl.wieleba.payroll.transactions.employee.data

import pl.wieleba.payroll.{PayrollDatabase, Employee}
import pl.wieleba.payroll.affiliations.{UnionAffiliation, NoAffiliation}

/**
 * Created by IntelliJ IDEA.
 * User: edsfq
 * Date: 14.02.11
 * Time: 02:18
 * To change this template use File | Settings | File Templates.
 */

class ChangeUnaffiliatedTransaction(empId: Int) extends ChangeAffiliationTransaction(empId) {

  override def getAffiliation() = new NoAffiliation

  override def recordMembership(e: Employee) = {
    val a = e.affiliation
    if (a.isInstanceOf[UnionAffiliation]) {
      val membershipId = a.asInstanceOf[UnionAffiliation].memberId

      PayrollDatabase.deleteUnionMember(membershipId)
    }
  }


}