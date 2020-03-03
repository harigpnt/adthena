package com.adthena

import scala.annotation.tailrec
import scala.collection.mutable
import scala.io.StdIn.{readInt, readLine}
import util.control.Breaks.{break, breakable}
import Retail.LOGGER
object Calculations {

//Method for getting the item id from the user and validating it
  def readInputItem(count: Int, basket: mutable.HashMap[Int, Int]): Option[Int] = {
    if (count == 0) {
      println("Exceeded the maximum number of attempts to choose a valid item id")
      None
    } else {
      println("Please enter the item id of the item, you would like to purchase \n " +
        "1. Soup - 65p per tin\n" +
        "2. Bread - 80p per loaf\n" +
        "3. Milk - £1.30 per bottle\n" +
        "4. Apples £1.00 per bag\n" +
        "Please consider the below special offers:\n" +
        "Apples have a 10% discount off their normal price this week \n" +
        "Buy 2 tins of soup and get a loaf of bread for half price"
      )
      println(s"$count remaining attempt(s) to choose an item")
      var id: Int = 0
      try {
        id = readInt()
        if (id >= 5 | id < 1) {
          println("Not a valid Item. Please enter a valid Item Id")
          readInputItem(count - 1, basket)
        } else if (basket.keysIterator.exists(_.equals(id))) {
          println(s"This item $id already exists in your basket. Please enter any other item id")
          readInputItem(count - 1, basket)
        } else Some(id)
      } catch {
        case e: NumberFormatException =>
          println("Not a valid Item. Please enter a valid Item Id")
          readInputItem(count - 1, basket)
      }
    }
  }

//Method for getting the qunaity  from the user and validating it
  def readInputQty(count: Int): Option[Int] = {
    if (count == 0) {
      println("Exceeded the maximum number of attempts to choose the quantity")
      None
    } else {
      println("Please enter the quantity required")
      println(s"$count remaining attempt(s) to choose the quantity")
      var qty: Int = 0
      try {
        qty = readInt()
        if (qty <= 0 | qty > 100) {
          println("The quantity you have entered is not valid. Please enter a valid quantity(1 to 100 maximum), example: 2")
          readInputQty(count - 1)
        } else Some(qty)
      } catch {
        case e: NumberFormatException =>
          println("Not a valid quantity. Please enter a valid quantity")
          readInputQty(count - 1)
      }
    }
  }

//Method for reading the checkout flag from the user and validating it
  @tailrec
  def readInputCheckout(count: Int): Option[String] = {

      if (count == 0) {
        println("Exceeded the maximum number of attempts to choose to continue shopping. Checking out")
        None
      } else {
        println("Please enter Y to add another item to your basket or N to checkout")
        println(s"$count remaining attempt(s) to continue shopping")
        var check: String = ""
        check = readLine()
        if (check.trim.toLowerCase() != "y" & check.trim.toLowerCase() != "n") {
          println("Not a Valid input. Please enter either Y or N")
          readInputCheckout(count - 1)
        } else Some(check)
      }
    }

//Method for creating a hashmap for the basket, storing the items purchased by the customer along with quantity
  def cart: mutable.HashMap[Int, Int] = {
    //Default flag to purchase another item
    var checkOut: String = "Y"

    var basket = scala.collection.mutable.HashMap.empty[Int, Int]
    breakable {
      while (checkOut.trim.toLowerCase() == "y") {
        val inputId = readInputItem(3, basket)
        var id: Int = 0
        if (inputId.isEmpty) {
          checkOut = "N"
          break()
        } else id = inputId.get
        val inputQty = readInputQty(3)
        var qty: Int = 0
        if (inputQty.isEmpty) {
          checkOut = "N"
          break()
        } else qty = inputQty.get
        if (id != 0 & qty != 0) basket += (id -> qty)
        val inputCheckOut = readInputCheckout(3)
        checkOut = if (inputCheckOut.isEmpty) "N" else inputCheckOut.get
      }
    }
    basket
  }

//Method for storing the current applicable offers for the customer
  def offer(basket: mutable.HashMap[Int, Int]): mutable.HashMap[Int, Double] = {

    var offers: mutable.HashMap[Int, Double] = mutable.HashMap[Int, Double]((4, 10))

    if(basket.keysIterator.exists(_.equals(1))) {
      if (basket(1) >= 2) {
        offers += ((2, 50))
      }
    }
    offers
  }

//Method for calculating the discount for each item based on the offers for the customer
  def discount(priceChart: Map[Int, Double], basket: mutable.HashMap[Int, Int], offers: mutable.HashMap[Int, Double]): mutable.HashMap[Int, Double] = {
    var discAmt = mutable.HashMap.empty[Int, Double]
    for ((id, quantity) <- basket) {
      val price = priceChart(id)
      var discqty: Float = 0

      if(id != 2) discqty = quantity
      else if (basket.keysIterator.exists(_.equals(2)) & basket.keysIterator.exists(_.equals(1))) {
        if (basket(2) <= basket(1) / 2) {
          discqty = quantity
        }
        else discqty = (basket(1) / 2).floor
      }
      val discount = priceChart(id) * ((if (offers.keysIterator.exists(_.equals(id))) offers(id) else 0) / 100) * discqty
      discAmt += (id -> discount)
    }
    discAmt
  }

//Method for calculating the subtotal for each item the user has bought
  def subTotal(priceChart: Map[Int, Double], basket: mutable.HashMap[Int, Int], discAmt: mutable.HashMap[Int, Double]): mutable.HashMap[Int, Double] = {
    var subTot = mutable.HashMap.empty[Int, Double]
    for ((id, quantity) <- basket) {
      val subtotal = (priceChart(id) * quantity) - discAmt(id)
      subTot += (id -> "%.2f".format(subtotal).toDouble)
    }
    subTot
  }

//Method for printing the final receipt for the customer
  def printReceipt(itemChart: Map[Int, String], priceChart: Map[Int, Double], basket: mutable.HashMap[Int, Int], discAmt: mutable.HashMap[Int, Double], subTotal: mutable.HashMap[Int, Double]): Double = {
    var total: Double = 0

    var totalDisc: Double = 0

    println("********* Receipt ************")
    for ((id, quantity) <- basket) {
      println(itemChart(id) + " - Price: £" + priceChart(id) + " Qty: " + quantity)
      totalDisc += discAmt(id)
      if (discAmt(id) == 0) {
        println("No offers/discounts available")
      } else {
        println(f"Discount: £"+ discAmt(id)%1.2f)
      }
      val subtot = subTotal(id)
      println(f"Subtotal: £$subtot%1.2f")
      total += (priceChart(id) * quantity)
      println("")
    }
    val finalPrice = total - totalDisc

    println("********************************")
    println(f"Total Price: £$total%1.2f")
    println(f"Total Discount: £$totalDisc%1.2f")
    finalPrice
  }
}