package pl.wieleba.payroll.affiliations

import java.util.Date
import scala.collection.mutable.HashMap
import pl.wieleba.payroll.{DateUtil, Paycheck, ServiceCharge}

/**
 * Created by IntelliJ IDEA.
 * User: edsfq
 * Date: 01.02.11
 * Time: 22:38
 * To change this template use File | Settings | File Templates.
 */

class UnionAffiliation(val memberId: Int, val dues: Double) extends Affiliation {

  private var charges = new HashMap[Date, ServiceCharge]

  override def getServiceCharge(d: Date): ServiceCharge = charges(d)

  override def addServiceCharge(sc: ServiceCharge) = {
    print("inserting at " + sc.date + " sc " + sc)
    charges += sc.date -> sc
  }

  def calculateDeductions(pc: Paycheck) = {
    var deductions =
      dues * DateUtil.numberOfFridaysInPeriod(pc.payPeriodStart, pc.payPeriodEnd)
    if (charges.contains(pc.payDay)) {
      deductions = deductions + charges(pc.payDay).amount
    }
    deductions
  }

}