import org.scalatest.FunSuite

  class CalculatorController extends FunSuite {
      test("Basic Addition") {
          ButtonPress.btnOnePressed()
          ButtonPress.btnAdditionPressed()
          ButtonPress.btnTwoPressed()
          ButtonPress.btnEqualsPressed()
          assert(CalculatorEvaluations.evaluateTotal("") === "3.0")
      }
  }

