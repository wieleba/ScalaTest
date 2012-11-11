package pl.wieleba.payroll.affiliations

import java.util.Date
import pl.wieleba.payroll.{Paycheck, ServiceCharge}

/**
 * Created by IntelliJ IDEA.
 * User: edsfq
 * Date: 01.02.11
 * Time: 22:56
 * To change this template use File | Settings | File Templates.
 */

class NoAffiliation extends Affiliation {
  override def getServiceCharge(d: Date) = new ServiceCharge(null, 0.0)

  override def addServiceCharge(sc: ServiceCharge) = throw new IllegalArgumentException(
    "Próba obciążenia pracownika, który nie należy do związku")

  override def calculateDeductions(pc: Paycheck) = 0.0
}