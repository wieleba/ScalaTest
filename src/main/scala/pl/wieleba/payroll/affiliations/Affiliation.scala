package pl.wieleba.payroll.affiliations

import java.util.Date
import pl.wieleba.payroll.{Paycheck, ServiceCharge}

/**
 * Created by IntelliJ IDEA.
 * User: edsfq
 * Date: 01.02.11
 * Time: 22:40
 * To change this template use File | Settings | File Templates.
 */

abstract class Affiliation {
  def getServiceCharge(d: Date): ServiceCharge

  def addServiceCharge(sc: ServiceCharge): Unit

  def calculateDeductions(pc: Paycheck): Double
}