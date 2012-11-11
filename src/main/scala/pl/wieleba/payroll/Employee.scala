package pl.wieleba.payroll

;

import affiliations.Affiliation;
import org.slf4j.LoggerFactory;
import java.util.Date;
import classifications.PaymentClassification;
import methods.PaymentMethod;
import schedule.PaymentSchedule;

class Employee(val id: Int, var name: String, var address: String) {
  var classification: PaymentClassification = null;
  var schedule: PaymentSchedule = null;
  var method: PaymentMethod = null;
  var affiliation: Affiliation = null;
  var log = LoggerFactory.getLogger(classOf[Employee]);

  def payday(pc: Paycheck) = {
    log.info("payday for {}", this);
    val grossPay = classification.calculatePay(pc);
    pc.payPeriodEnd = schedule.getPayPeriodEndDate(pc);
    val deductions = affiliation.calculateDeductions(pc);
    val netPay = grossPay - deductions;
    pc.grossPay = grossPay;
    pc.deductions = deductions;
    pc.netPay = netPay;
    method.pay(pc);

  };

  def isPayDay(d: Date) = schedule.isPayDay(d);

  def getPayPeriodStartDate(d: Date) = schedule.getPayPeriodStartDate(d);

  //  def post(date:Date) = classification.post(date);
  override def toString() = "Employee{" + id + "," + name + "}"

}