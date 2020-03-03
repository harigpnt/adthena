package com.adthena

import org.apache.logging.log4j.{Logger,LogManager}

object Retail {
  //Global logger variable

  val LOGGER: Logger = LogManager.getLogger(getClass.getName)

  def main(args: Array[String]): Unit = {
    try {
      LOGGER.info("******* Starting the application Retail*******")
      //A map for storing the item id and item names
      val itemChart: Map[Int, String] = Map((1, "Soup"), (2, "Bread"), (3, "Milk"), (4, "Apples"))

      //A map for storing the item id and corresponding price
      val priceChart: Map[Int, Double] = Map((1, 0.65), (2, 0.80), (3, 1.30), (4, 1.00))

      LOGGER.info("******* Calling the cart Method from Calculations object for getting the items into basket *******")
      //A hashmap for getting the items from customer along with their respective quantity required
      val basket = Calculations.cart

      LOGGER.info("******* Calling the offer Method from Calculations object for deriving the offers *******")
      //A hashmap for loading the current offer based on the items in the basket
      val offers = Calculations.offer(basket)

      LOGGER.info("******* Calling the discount Method from Calculations object for calculating the discount amount *******")
      //A hashmap for storing the discount amount
      val discAmt = Calculations.discount(priceChart, basket, offers)

      LOGGER.info("******* Calling the subTotal Method from Calculations object for deriving the subTotal for each item *******")
      //A hasmap for storing the subtotal for each item purchased
      val subTot = Calculations.subTotal(priceChart, basket, discAmt)

      LOGGER.info("******* Calling the printReceipt Method from Calculations object for printing the final receipt *******")
      //Print the receipt
      val finalPrice = Calculations.printReceipt(itemChart, priceChart, basket, discAmt, subTot)

      println(f"Final Price to Pay: £$finalPrice%1.2f")

    } catch {
      case n: NoSuchElementException =>
        println("No items in your basket to checkout. Kindly re initiate the checkout process")
      case e:  Exception =>
        LOGGER.error("MAIN", e)
    }
  }
}
