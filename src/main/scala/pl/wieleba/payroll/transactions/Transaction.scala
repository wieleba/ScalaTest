package pl.wieleba.payroll.transactions

trait Transaction {
  def execute(): Unit
}