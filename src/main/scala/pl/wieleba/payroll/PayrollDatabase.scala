package pl.wieleba.payroll

;

import scala.collection.mutable.HashMap;

object PayrollDatabase {

  private val employees = new HashMap[Int, Employee];
  private val unionMembers = new HashMap[Int, Employee];

  def getEmployees() = employees.values;

  def getEmployee(empId: Int): Employee = employees(empId);

  def addEmployee(e: Employee) = employees += e.id -> e;

  def deleteEmployee(empId: Int) = employees -= empId;

  def addUnionMember(memberId: Int, e: Employee) = unionMembers += memberId -> e;

  def getUnionMember(memberId: Int) = unionMembers(memberId);

  def deleteUnionMember(memberId: Int) = unionMembers -= memberId;
}
