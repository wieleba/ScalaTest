package pl.wieleba.payroll

import affiliations.{NoAffiliation, UnionAffiliation}
import classifications.{CommissionedClassification, HourlyClassification, SalariedClassification}
import methods.{MailMethod, DirectMethod, HoldMethod}
import org.junit.{Assert, Test}
import Assert._
import schedule.{BiweeklySchedule, WeeklySchedule, MonthlySchedule}
import transactions._
import employee.data._
import java.util.Date
import java.text.SimpleDateFormat

class PayrollTest {

  @Test
  def testAddSalariedEmployee() {
    val empId = 1
    val t = new AddSalariedEmployee(empId, "Bogdan", "Home", 1000.0)
    t.execute()

    val e: Employee = PayrollDatabase.getEmployee(empId);
    assertEquals("Bogdan", e.name);
    assertEquals("Home", e.address)

    val pc = e.classification
    assertTrue(pc.isInstanceOf[SalariedClassification])
    val sc = pc.asInstanceOf[SalariedClassification]
    assertEquals(1000.0, sc.salary, 0.001)

    val ps = e.schedule
    assertTrue(ps.isInstanceOf[MonthlySchedule])

    val pm = e.method
    assertTrue(pm.isInstanceOf[HoldMethod])
  }

  @Test(expected = classOf[NoSuchElementException])
  def testDeleteEmployee() {
    val empId = 44
    val t = new AddCommissionedEmployee(empId, "Bogdan", "Home", 1000.0, 2.0)
    t.execute()

    val e: Employee = PayrollDatabase.getEmployee(empId);
    assertEquals("Bogdan", e.name);
    assertEquals("Home", e.address)

    val d = new DeleteEmployeeTransaction(empId)
    d.execute()

    PayrollDatabase.getEmployee(empId)

  }

  @Test
  def testAddHourlyEmployee() {
    val empId = 2
    val t = new AddHourlyEmployee(empId, "Bogdan", "Home", 15.0)
    t.execute()

    val e: Employee = PayrollDatabase.getEmployee(empId);
    assertEquals("Bogdan", e.name);
    assertEquals("Home", e.address)

    val pc = e.classification
    assertTrue(pc.isInstanceOf[HourlyClassification])
    val hc = pc.asInstanceOf[HourlyClassification]
    assertEquals(15.0, hc.hourlyRate, 0.001)

    val ps = e.schedule
    assertTrue(ps.isInstanceOf[WeeklySchedule])

    val pm = e.method
    assertTrue(pm.isInstanceOf[HoldMethod])
  }


  @Test
  def testAddCommissionedEmployee() {
    val empId = 2
    val t = new AddCommissionedEmployee(empId, "Bogdan", "Home", 1000.0, 2.0)
    t.execute()

    val e: Employee = PayrollDatabase.getEmployee(empId);
    assertEquals("Bogdan", e.name);
    assertEquals("Home", e.address)

    val pc = e.classification
    assertTrue(pc.isInstanceOf[CommissionedClassification])
    val hc = pc.asInstanceOf[CommissionedClassification]
    assertEquals(1000.0, hc.salary, 0.001)
    assertEquals(2.0, hc.commissionRate, 0.001)

    val ps = e.schedule
    assertTrue(ps.isInstanceOf[BiweeklySchedule])

    val pm = e.method
    assertTrue(pm.isInstanceOf[HoldMethod])
  }

  @Test
  def testTimeCardTransaction() {
    val empId = 5
    val t = new AddHourlyEmployee(empId, "Bogdan", "Home", 20.0)
    t.execute()

    val tct = new TimeCardTransaction(new Date(2005, 7, 31), 8.0, empId)
    tct.execute()

    val e: Employee = PayrollDatabase.getEmployee(empId);
    assertNotNull(e)
    assertEquals("Bogdan", e.name)
    assertEquals("Home", e.address)

    val pc = e.classification
    assertTrue(pc.isInstanceOf[HourlyClassification])
    val hc = pc.asInstanceOf[HourlyClassification]

    val tc: TimeCard = hc.getTimeCard(new Date(2005, 7, 31))
    assertNotNull(tc)
    assertEquals(8.0, tc.hours, 0.01)
  }

  @Test
  def testSalesReceiptTransaction() {
    val empId = 5
    val t = new AddCommissionedEmployee(empId, "Bogdan", "Home", 1000.0, 2.5)
    t.execute()

    val tct = new SalesReceiptTransaction(new Date(2005, 7, 31), 8.0, empId)
    tct.execute()

    val e: Employee = PayrollDatabase.getEmployee(empId);
    assertNotNull(e)
    assertEquals("Bogdan", e.name)
    assertEquals("Home", e.address)

    val pc = e.classification
    assertTrue(pc.isInstanceOf[CommissionedClassification])

    val sr = pc.asInstanceOf[CommissionedClassification] getSalesReceipt (new Date(2005, 7, 31))
    assertNotNull(sr)
    assertEquals(8.0, sr.amount, 0.01)
  }


  @Test
  def testAddServiceCharge() {
    val empId = 6
    val t = new AddHourlyEmployee(empId, "Bartosz", "Home", 17.30)
    t.execute()

    val e: Employee = PayrollDatabase.getEmployee(empId)
    assertEquals("Bartosz", e.name)
    assertEquals("Home", e.address)

    val af = new UnionAffiliation(123, 12.21)
    e.affiliation = af

    val memberId = 86
    PayrollDatabase.addUnionMember(memberId, e)

    val s = new ServiceChargeTransaction(memberId, new Date(2005, 8, 8), 12.1)
    s.execute

    val sc = af.getServiceCharge(new Date(2005, 8, 8))
    assertNotNull(sc)
    assertTrue(sc.isInstanceOf[ServiceCharge])
    assertEquals(12.1, sc.amount, 0.001)

  }

  @Test
  def testChangeNameTransaction() {
    val empId = 7
    new AddHourlyEmployee(empId, "Bartosz", "Home", 15.25).execute()
    new ChangeNameTransaction(empId, "Bogdan").execute()
    val e = PayrollDatabase.getEmployee(empId)
    assertNotNull(e)
    assertEquals("Bogdan", e.name)
  }

  @Test
  def testChangeAddressTransaction() {
    val empId = 8
    new AddHourlyEmployee(empId, "Bartosz", "Away", 15.25).execute()
    new ChangeAddressTransaction(empId, "Away").execute()
    val e = PayrollDatabase.getEmployee(empId)
    assertNotNull(e)
    assertEquals("Away", e.address)
  }

  @Test
  def testChangeHourlyTransaction() {
    val empId = 9
    new AddCommissionedEmployee(empId, "Bartosz", "Away", 1000.0, 15.25).execute()
    new ChangeHourlyTransaction(empId, 15.25).execute()
    val e = PayrollDatabase.getEmployee(empId)
    assertNotNull(e)
    val pc = e.classification
    assertNotNull(pc)
    assertTrue(pc.isInstanceOf[HourlyClassification])
    assertEquals(15.25, pc.asInstanceOf[HourlyClassification].hourlyRate, 0.001)
    val s = e.schedule
    assertTrue(s.isInstanceOf[WeeklySchedule])
  }

  @Test
  def testChangeSalariedTransaction() {
    val empId = 10
    new AddCommissionedEmployee(empId, "Bartosz", "Away", 1000.0, 15.25).execute()
    new ChangeSalariedTransaction(empId, 2000.25).execute()
    val e = PayrollDatabase.getEmployee(empId)
    assertNotNull(e)
    val pc = e.classification
    assertNotNull(pc)
    assertTrue(pc.isInstanceOf[SalariedClassification])
    assertEquals(2000.25, pc.asInstanceOf[SalariedClassification].salary, 0.001)
    val s = e.schedule
    assertTrue(s.isInstanceOf[MonthlySchedule])
  }

  @Test
  def testChangeCommissionedTransaction() {
    val empId = 10
    new AddHourlyEmployee(empId, "Bartosz", "Home", 15.25).execute()
    new ChangeCommissionedTransaction(empId, 1000.0, 15.25).execute()
    val e = PayrollDatabase.getEmployee(empId)
    assertNotNull(e)
    val pc = e.classification
    assertNotNull(pc)
    assertTrue(pc.isInstanceOf[CommissionedClassification])
    assertEquals(1000.0, pc.asInstanceOf[CommissionedClassification].salary, 0.001)
    assertEquals(15.25, pc.asInstanceOf[CommissionedClassification].commissionRate, 0.001)
    val s = e.schedule
    assertTrue(s.isInstanceOf[BiweeklySchedule])
  }

  @Test
  def testChangeDirectTransaction() {
    val empId = 11
    new AddHourlyEmployee(empId, "Bartosz", "Home", 15.25).execute()
    new ChangeDirectTransaction(empId, "Bank", "Account").execute()
    val e = PayrollDatabase.getEmployee(empId)
    assertNotNull(e)
    val m = e.method
    assertNotNull(m)
    assertTrue(m.isInstanceOf[DirectMethod])
    assertEquals("Bank", m.asInstanceOf[DirectMethod].bank)
    assertEquals("Account", m.asInstanceOf[DirectMethod].account)
  }

  @Test
  def testChangeMailTransaction() {
    val empId = 11
    new AddHourlyEmployee(empId, "Bartosz", "Home", 15.25).execute()
    new ChangeMailTransaction(empId, "Nowy adres...").execute()
    val e = PayrollDatabase.getEmployee(empId)
    assertNotNull(e)
    val m = e.method
    assertNotNull(m)
    assertTrue(m.isInstanceOf[MailMethod])
    assertEquals("Nowy adres...", m.asInstanceOf[MailMethod].address)
  }

  @Test
  def testChangeHoldTransaction() {
    val empId = 11
    new AddHourlyEmployee(empId, "Bartosz", "Home", 15.25).execute()
    new ChangeHoldTransaction(empId).execute()
    val e = PayrollDatabase.getEmployee(empId)
    assertNotNull(e)
    val m = e.method
    assertNotNull(m)
    assertTrue(m.isInstanceOf[HoldMethod])
  }

  @Test
  def changeMemberTransaction() {
    val empId = 12
    new AddHourlyEmployee(empId, "Bartosz", "Home", 15.25).execute()
    val memberId = 1234
    new ChangeMemberTransaction(empId, memberId, 99.42).execute()

    val e = PayrollDatabase.getEmployee(empId)
    assertNotNull(e)
    val a = e.affiliation
    assertNotNull(a)
    assertTrue(a.isInstanceOf[UnionAffiliation])
    assertEquals(99.42, a.asInstanceOf[UnionAffiliation].dues, 0.001)
  }

  @Test
  def changeUnaffiliatedTransaction() {
    val empId = 12
    new AddHourlyEmployee(empId, "Bartosz", "Home", 15.25).execute()
    val memberId = 1234
    new ChangeUnaffiliatedTransaction(empId).execute()

    val e = PayrollDatabase.getEmployee(empId)
    assertNotNull(e)
    val a = e.affiliation
    assertNotNull(a)
    assertTrue(a.isInstanceOf[NoAffiliation])

  }

  @Test
  def paySingleSalariedEmployee() {
    val empId = 13
    new AddSalariedEmployee(empId, "Bogdan", "Home", 1000.0).execute()
    new ChangeUnaffiliatedTransaction(empId).execute()
    val d = new SimpleDateFormat("dd-MM-yyyy").parse("01-11-2001")
    val pt = new PaydayTransaction(d)
    pt.execute()

    val pay = 1000.0
    validatePaycheck(pt, empId, d, pay, 0.0)
  }

  @Test(expected = classOf[NoSuchElementException])
  def paySingleSalariedEmployeeOnWrongDate() {
    val empId = 14
    new AddSalariedEmployee(empId, "Bogdan", "Home", 1000.0).execute()
    val d = new SimpleDateFormat("dd-MM-yyyy").parse("11-11-2001")
    val pt = new PaydayTransaction(d)
    pt.execute()
    pt.getPaycheck(empId)
  }

  @Test
  def payingSingleHourlyEmployeeNoTimeCards() {
    val empId = 15
    new AddHourlyEmployee(empId, "Bartosz", "Home", 15.25).execute()
    val d = new SimpleDateFormat("dd-MM-yyyy").parse("13-05-2011")
    val pt = new PaydayTransaction(d)
    pt.execute()

    validatePaycheck(pt, empId, d, 0.0, 0.0)
  }


  @Test
  def paySingleHourlyEmployeeOneTimeCard() {
    val empId = 16
    new AddHourlyEmployee(empId, "Bartosz", "Home", 15.25).execute()
    val payDate = new SimpleDateFormat("dd-MM-yyyy").parse("13-05-2011")

    new TimeCardTransaction(payDate, 2.0, empId).execute()
    val pc = new PaydayTransaction(payDate)
    pc.execute()

    val pay = 30.5
    validatePaycheck(pc, empId, payDate, pay, 0.0)
  }

  @Test(expected = classOf[NoSuchElementException])
  def paySingleHourlyEmployeeOneWrongDate() {
    val empId = 20
    new AddHourlyEmployee(empId, "Bartosz", "Home", 15.25).execute()
    val payDate = new SimpleDateFormat("dd-MM-yyyy").parse("03-05-2011")

    new TimeCardTransaction(payDate, 2.0, empId).execute()
    val pc = new PaydayTransaction(payDate)
    pc.execute()

    validatePaycheck(pc, empId, payDate, 0.0, 0.0)
  }

  @Test
  def paySingleHourlyEmployeeOvertimeOneTimeCard() {
    val empId = 17
    new AddHourlyEmployee(empId, "Bartosz", "Home", 15.25).execute()
    val payDate = new SimpleDateFormat("dd-MM-yyyy").parse("13-05-2011")

    new TimeCardTransaction(payDate, 9.0, empId).execute()
    val pc = new PaydayTransaction(payDate)
    pc.execute()
    val pay = (8 + 1.5) * 15.25
    validatePaycheck(pc, empId, payDate, pay, 0.0)
  }

  @Test
  def paySingleHourlyEmployeeOvertimeTwoTimeCard() {
    val empId = 18
    new AddHourlyEmployee(empId, "Bartosz", "Home", 15.25).execute()
    val payDate = new SimpleDateFormat("dd-MM-yyyy").parse("13-05-2011")
    val payDate2 = new SimpleDateFormat("dd-MM-yyyy").parse("12-05-2011")

    new TimeCardTransaction(payDate, 2.0, empId).execute()
    new TimeCardTransaction(payDate2, 5.0, empId).execute()
    val pc = new PaydayTransaction(payDate)
    pc.execute()
    val pay = (7) * 15.25;
    validatePaycheck(pc, empId, payDate, pay, 0.0)
  }

  @Test
  def paySingleHourlyEmployeeWithTimeCardsSpanningTwoPayPeriods() {
    val empId = 19
    new AddHourlyEmployee(empId, "Bartosz", "Home", 15.25).execute()
    val payDate = new SimpleDateFormat("dd-MM-yyyy").parse("13-05-2011")
    val payDateInPreviousPeriod = new SimpleDateFormat("dd-MM-yyyy").parse("01-05-2011")

    new TimeCardTransaction(payDate, 2.0, empId).execute()
    new TimeCardTransaction(payDateInPreviousPeriod, 5.0, empId).execute()
    val pc = new PaydayTransaction(payDate)
    pc.execute()

    val pay = (2) * 15.25
    validatePaycheck(pc, empId, payDate, pay, 0.0)
  }

  @Test
  def salariedUnionMemberDues() {
    val empId = 129
    new AddSalariedEmployee(empId, "Bartosz", "Home", 1000.0).execute()
    val memberId = 111
    val membershipCost = 9.42
    new ChangeMemberTransaction(empId, memberId, membershipCost).execute()
    val payDate = new SimpleDateFormat("dd-MM-yyyy").parse("01-09-2011")
    val pt = new PaydayTransaction(payDate)
    pt.execute()
    val numberOfFridaysInMonth = 4 // we sierpniu 2011 jest 4 piątki (1 września wyłata jest za sierpień)
    val deduction = numberOfFridaysInMonth * membershipCost
    validatePaycheck(pt, empId, payDate, 1000.0, deduction)
  }

  @Test
  def hourlyUnionMemberServiceCharge() {
    val empId = 72
    new AddHourlyEmployee(empId, "Piotr", "Home", 15.43) execute
    val memberId = 3
    val membershipCost = 9.42
    val serviceCost = 19.42

    new ChangeMemberTransaction(empId, memberId, membershipCost) execute
    val payDate = new SimpleDateFormat("dd-MM-yyyy").parse("09-11-2001")
    new ServiceChargeTransaction(memberId, payDate, serviceCost).execute()
    new TimeCardTransaction(payDate, 8.0, empId) execute

    val pt = new PaydayTransaction(payDate)
    pt.execute()
    validatePaycheck(pt, empId, payDate, 8.0 * 15.43, membershipCost + serviceCost)
  }

  @Test
  def serviceChargesSpanningMultiplePayPeriods() {
    val empId = 73
    new AddHourlyEmployee(empId, "Piotr", "Home", 15.43) execute
    val memberId = 37
    val membershipCost = 9.42
    val serviceCost = 19.42
    new ChangeMemberTransaction(empId, memberId, membershipCost) execute

    val payDate = new SimpleDateFormat("dd-MM-yyyy").parse("09-11-2001")
    val earlyDate = new SimpleDateFormat("dd-MM-yyyy").parse("02-11-2001")
    val lateDate = new SimpleDateFormat("dd-MM-yyyy").parse("16-11-2001")

    new ServiceChargeTransaction(memberId, payDate, serviceCost).execute()
    new ServiceChargeTransaction(memberId, earlyDate, 100.0).execute()
    new ServiceChargeTransaction(memberId, lateDate, 200.0).execute()

    new TimeCardTransaction(payDate, 8.0, empId) execute

    val pt = new PaydayTransaction(payDate)
    pt.execute()
    validatePaycheck(pt, empId, payDate, 8.0 * 15.43, membershipCost + serviceCost)
  }

  private def validatePaycheck(pt: PaydayTransaction, empId: Int,
                               d: Date, grossPay: Double, deductions: Double): Unit = {
    val pc = pt.getPaycheck(empId)
    assertNotNull(pc)
    assertEquals(d, pc.payDay)
    assertEquals(grossPay, pc.grossPay, 0.001)
    assertEquals("Hold", pc.getField("Disposition"))
    assertEquals(deductions, pc.deductions, 0.001)
    assertEquals(grossPay - deductions, pc.netPay, 0.001)
  }

}
