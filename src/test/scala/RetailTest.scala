import java.io.{ByteArrayOutputStream, EOFException, StringReader}

import com.adthena.Retail
import com.adthena.Calculations
import org.scalatest.FunSuite
import org.scalatest.Matchers

import scala.collection.mutable
import scala.io.StdIn

class RetailTest extends FunSuite with Matchers {

  // ************ TEST CASE - 1 ***************** //
  test("readItemValid") {
    val inputnbr = "1"
    val in = new StringReader(inputnbr)
    val out = new ByteArrayOutputStream()
    Console.withOut(out) {
      Console.withIn(in) {
        assert(Calculations.readInputItem(3, mutable.HashMap.empty[Int, Int]).get == 1)
      }
    }
    println("readItemValid test passed")
  }

  // ************ TEST CASE - 2 ***************** //
  test("readItemExists") {
    try {
      val inputnbr = "2"
      val in = new StringReader(inputnbr)
      val out = new ByteArrayOutputStream()
      Console.withOut(out) {
        Console.withIn(in) {
          assert(Calculations.readInputItem(3, mutable.HashMap[Int, Int]((2, 2))).isEmpty)
        }
      }
    }
    catch {
      case e: EOFException =>
    }
    println("readItemValid test passed")
  }

  // ************ TEST CASE - 3 ***************** //
  test("readItemInvalidLowerBound") {
    try {
      val inputnbr = "0"
      val in = new StringReader(inputnbr)
      val out = new ByteArrayOutputStream()
      Console.withOut(out) {
        Console.withIn(in) {
          assert(Calculations.readInputItem(3, mutable.HashMap.empty[Int, Int]).isEmpty)
        }
      }
    }
    catch {
      case e: EOFException =>
    }
    println("readItemInvalidLowerBound test passed")
  }

  // ************ TEST CASE - 4 ***************** //
  test("readItemInvalidUpperBound") {
    try {
      val inputnbr = "5"
      val in = new StringReader(inputnbr)
      val out = new ByteArrayOutputStream()
      Console.withOut(out) {
        Console.withIn(in) {
          assert(Calculations.readInputItem(3, mutable.HashMap.empty[Int, Int]).isEmpty)
        }
      }
    }
    catch {
      case e: EOFException =>
    }
    println("readItemInvalidUpperBound test passed")
  }

  // ************ TEST CASE - 5 ***************** //
  test("readItemChar") {
    try {
      val inputnbr = "a"
      val in = new StringReader(inputnbr)
      val out = new ByteArrayOutputStream()
      Console.withOut(out) {
        Console.withIn(in) {
          assert(Calculations.readInputItem(3, mutable.HashMap.empty[Int, Int]).isEmpty)
        }
      }
    }
    catch {
      case e: EOFException =>
    }
    println("readItemChar test passed")
  }

  // ************ TEST CASE - 6 ***************** //
  test("readItemSymbol") {
    try {
      val inputnbr = "-"
      val in = new StringReader(inputnbr)
      val out = new ByteArrayOutputStream()
      Console.withOut(out) {
        Console.withIn(in) {
          assert(Calculations.readInputItem(3, mutable.HashMap.empty[Int, Int]).isEmpty)
        }
      }
    }
    catch {
      case e: EOFException =>
    }
    println("readItemSymbol test passed")
  }

  // ************ TEST CASE - 7 ***************** //
  test("readQtyValid") {
    val inputnbr = "50"
    val in = new StringReader(inputnbr)
    val out = new ByteArrayOutputStream()
    Console.withOut(out) {
      Console.withIn(in) {
        assert(Calculations.readInputQty(3).get == 50)
      }
    }
    println("readQtyValid test passed")
  }

  // ************ TEST CASE - 8 ***************** //
  test("readQtyInvalidLowerBound") {
    try {
      val inputnbr = "0"
      val in = new StringReader(inputnbr)
      val out = new ByteArrayOutputStream()
      Console.withOut(out) {
        Console.withIn(in) {
          assert(Calculations.readInputQty(3).isEmpty)
        }
      }
    }
    catch {
      case e: EOFException =>
    }
    println("readQtyInvalidLowerBound test passed")
  }

  // ************ TEST CASE - 9 ***************** //
  test("readQtyInvalidUpperBound") {
    try {
      val inputnbr = "101"
      val in = new StringReader(inputnbr)
      val out = new ByteArrayOutputStream()
      Console.withOut(out) {
        Console.withIn(in) {
          assert(Calculations.readInputQty(3).isEmpty)
        }
      }
    }
    catch {
      case e: EOFException =>
    }
    println("readQtyInvalidUpperBound test passed")
  }

  // ************ TEST CASE - 10 ***************** //
  test("readQtyChar") {
    try {
      val inputnbr = "a"
      val in = new StringReader(inputnbr)
      val out = new ByteArrayOutputStream()
      Console.withOut(out) {
        Console.withIn(in) {
          assert(Calculations.readInputQty(3).isEmpty)
        }
      }
    }
    catch {
      case e: EOFException =>
    }
    println("readQtyChar test passed")
  }

  // ************ TEST CASE - 11 ***************** //
  test("readQtySymbol") {
    try {
      val inputnbr = "-"
      val in = new StringReader(inputnbr)
      val out = new ByteArrayOutputStream()
      Console.withOut(out) {
        Console.withIn(in) {
          assert(Calculations.readInputQty(3).isEmpty)
        }
      }
    }
    catch {
      case e: EOFException =>
    }
    println("readQtySymbol test passed")
  }

  // ************ TEST CASE - 12 ***************** //
  test("readCheckoutValidY") {
      val inputstr = "y"
      val in = new StringReader(inputstr)
      val out = new ByteArrayOutputStream()
      Console.withOut(out) {
        Console.withIn(in) {
          assert(Calculations.readInputCheckout(3).get == "y")
        }
      }
    println("readCheckoutValidY test passed")
  }

  // ************ TEST CASE - 13 ***************** //
  test("readCheckoutValidN") {
      val inputstr = "n"
      val in = new StringReader(inputstr)
      val out = new ByteArrayOutputStream()
      Console.withOut(out) {
        Console.withIn(in) {
          assert(Calculations.readInputCheckout(3).get == "n")
        }
      }
    println("readCheckoutValidN test passed")
  }

  // ************ TEST CASE - 14 ***************** //
  test("readCheckoutInvalid") {
    try {
      val inputstr = "a"
      val in = new StringReader(inputstr)
      val out = new ByteArrayOutputStream()
      Console.withOut(out) {
        Console.withIn(in) {
          assert(Calculations.readInputCheckout(3).isEmpty)
        }
      }
    }
    catch {
      case e: EOFException =>
      case n: NullPointerException =>
    }
    println("readCheckoutInvalid test passed")
  }

  // ************ TEST CASE - 15 ***************** //
  test("offersValid") {
          assert(Calculations.offer(mutable.HashMap[Int, Int]((1,2))) == mutable.HashMap[Int, Double]((4,10),(2,50)))
    println("offersValid test passed")
  }

  // ************ TEST CASE - 16 ***************** //
  test("offersInvalid") {
    assert(Calculations.offer(mutable.HashMap[Int, Int]((1,1))) == mutable.HashMap[Int, Double]((4,10)))
    println("offersInvalid test passed")
  }

  // ************ TEST CASE - 17 ***************** //
  test("discountValidBread") {
    assert(Calculations.discount(Map[Int, Double]((1, 0.65), (2, 0.80), (3, 1.30), (4, 1.00)),mutable.HashMap[Int, Int]((1,2),(2,2)),mutable.HashMap[Int, Double]((4,10),(2,50))) == mutable.HashMap[Int, Double]((1,0),(2,0.4)))
    println("discountValidBread test passed")
  }

  // ************ TEST CASE - 18 ***************** //
  test("discountInvalidBread") {
    assert(Calculations.discount(Map[Int, Double]((1, 0.65), (2, 0.80), (3, 1.30), (4, 1.00)),mutable.HashMap[Int, Int]((1,1),(2,2)),mutable.HashMap[Int, Double]((4,10))) == mutable.HashMap[Int, Double]((1,0),(2,0)))
    println("discountInvalidBread test passed")
  }

  // ************ TEST CASE - 19 ***************** //
  test("discountValidApples") {
    assert(Calculations.discount(Map[Int, Double]((1, 0.65), (2, 0.80), (3, 1.30), (4, 1.00)),mutable.HashMap[Int, Int]((4,4)),mutable.HashMap[Int, Double]((4,10),(2,50))) == mutable.HashMap[Int, Double]((4,0.4)))
    println("discountValidApples test passed")
  }

  // ************ TEST CASE - 20 ***************** //
  test("subTotalValid") {
    assert(Calculations.subTotal(Map[Int, Double]((1, 0.65), (2, 0.80), (3, 1.30), (4, 1.00)),mutable.HashMap[Int, Int]((4,4)),mutable.HashMap[Int, Double]((4,0.4))) == mutable.HashMap[Int, Double]((4,3.6)))
    println("subTotalValid test passed")
  }

  // ************ TEST CASE - 20 ***************** //
  test("finalPriceValid") {
    assert(Calculations.printReceipt(Map[Int, String]((1, "Soup"), (2, "Bread"), (3, "Milk"), (4, "Apples")),Map[Int, Double]((1, 0.65), (2, 0.80), (3, 1.30), (4, 1.00)),mutable.HashMap[Int, Int]((4,4)),mutable.HashMap[Int, Double]((4,0.4)),mutable.HashMap[Int, Double]((4,3.6))) == 3.6)
    println("finalPriceValid test passed")
  }
}