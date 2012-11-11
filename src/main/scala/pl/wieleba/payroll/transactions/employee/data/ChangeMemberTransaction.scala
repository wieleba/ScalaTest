package pl.wieleba.payroll.transactions.employee.data

import pl.wieleba.payroll.affiliations.UnionAffiliation
import pl.wieleba.payroll.{PayrollDatabase, Employee}

/**
 * Created by IntelliJ IDEA.
 * User: edsfq
 * Date: 14.02.11
 * Time: 02:08
 * To change this template use File | Settings | File Templates.
 */

class ChangeMemberTransaction(empId: Int, val memberId: Int, val dues: Double)
  extends ChangeAffiliationTransaction(empId) {

  override def getAffiliation() = new UnionAffiliation(memberId, dues)

  override def recordMembership(e: Employee) = PayrollDatabase.addUnionMember(memberId, e)

}