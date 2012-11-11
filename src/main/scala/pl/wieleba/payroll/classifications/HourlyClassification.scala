package pl.wieleba.payroll.classifications

import java.util.Date
import scala.collection.mutable.HashMap
import pl.wieleba.payroll.{Paycheck, TimeCard}
import org.slf4j.LoggerFactory


/**
 * Created by IntelliJ IDEA.
 * User: edsfq
 * Date: 26.01.11
 * Time: 22:58
 */

class HourlyClassification(var hourlyRate: Double) extends PaymentClassification {

  private val log = LoggerFactory.getLogger(classOf[HourlyClassification])
  private var cards = new HashMap[Date, TimeCard]

  def addTimeCard(tc: TimeCard) = cards += tc.date -> tc

  def getTimeCard(d: Date) = cards.get(d).get

  override def calculatePay(pc: Paycheck): Double = {

    var totalPay = 0.0d
    val filteredCards = cards.filterKeys(key => {
      isInPayPeriod(key, pc)
    })
    filteredCards.foreach(d => {
      totalPay += convertToOvertime(cards(d._1).hours) * hourlyRate
    })
    totalPay
  }

  private def convertToOvertime(d: Double): Double = {
    var ret = d
    if (d > 8.0) {
      ret += (d - 8.0) * 0.5
    }
    ret
  }

}