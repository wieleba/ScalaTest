package pl.wieleba.payroll.transactions

import java.util.Date
import pl.wieleba.payroll.{ServiceCharge, PayrollDatabase}

/**
 * Created by IntelliJ IDEA.
 * User: edsfq
 * Date: 01.02.11
 * Time: 22:28
 * To change this template use File | Settings | File Templates.
 */

class ServiceChargeTransaction(val memberId: Int, val data: Date, val charge: Double)
  extends Transaction {

  override def execute() {
    val e = PayrollDatabase.getUnionMember(memberId)
    if (null != e) {
      val ua = e.affiliation
      ua.addServiceCharge(new ServiceCharge(data, charge))
    } else {
      throw new IllegalArgumentException("Nie znaleziono członka związku " + memberId)
    }
  }

}