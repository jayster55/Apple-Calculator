import breeze.numerics.{acosh, asinh, atanh}
import scala.util.control.Breaks._

import scala.collection.mutable
import scala.math._

object CalculatorEvaluations {

  //---------------------------------------------
  // Stack Evaluation (BEDMAS) Implementations
  //---------------------------------------------
  class bracket_record(var b_type: String, var location: Int) {}

  val operations = mutable.Stack[String]()
  val brackets = mutable.Stack[bracket_record]()
  var sleeper: Boolean = false
  var sleeper_name: String = ""
  var in_brackets: Boolean = false

  // Radians & Degrees
  var radians: Boolean = false
  var to_radians: Double = 0.005555556 * Pi
  var to_degrees: Double = 1 / to_radians

  def evaluateTotal(total_eval: String): String = {
    evaluate(total_eval)
  }


  def evaluate(total_eval: String): String = {

    println("In Evaluate")
    println("This is the total eval so far:", total_eval)
    println("Here is the stack so far:", operations)

    var op: String = ""
    op = operations.top
    var result: String = ""
    var in_brackets: Boolean = false

    if (!sleeper) {
      op match {
        // Constant Evaluations
        case "e" => result = evaluateConst(E.toString, total_eval)
        case "rand" => result = evaluateConst(random().toString, total_eval)
        case "pi" => result = evaluateConst(Pi.toString, total_eval)
        // Function Evaluations
        case "%" => result = evaluateFunc("cos", total_eval)
        case "cos" => result = evaluateFunc("cos", total_eval)
        case "sin" => result = evaluateFunc("sin", total_eval)
        case "tan" => result = evaluateFunc("tan", total_eval)
        case "cosh" => result = evaluateFunc("cosh", total_eval)
        case "sinh" => result = evaluateFunc("sinh", total_eval)
        case "tanh" => result = evaluateFunc("tanh", total_eval)
        case "!" => result = evaluateFunc("!", total_eval)
        case "1/x" => result = evaluateFunc("1/x", total_eval)
        case "2√ x" => result = evaluateFunc("2√ x", total_eval)
        case "3√ x" => result = evaluateFunc("3√ x", total_eval)
        case "ln" => result = evaluateFunc("ln", total_eval)
        case "log10" => result = evaluateFunc("log10", total_eval)
        case "x^2" => result = evaluateFunc("x^2", total_eval)
        case "x^3" => result = evaluateFunc("x^3", total_eval)
        case "e^x" => result = evaluateFunc("e^x", total_eval)
        case "10^x" => result = evaluateFunc("10^x", total_eval)
        // 2nd Button Functions
        case "sinh^-1" => result = evaluateFunc("sinh^-1", total_eval)
        case "cosh^-1" => result = evaluateFunc("cosh^-1", total_eval)
        case "tanh^-1" => result = evaluateFunc("tanh^-1", total_eval)
        case "sin^-1" => result = evaluateFunc("sin^-1", total_eval)
        case "cos^-1" => result = evaluateFunc("cos^-1", total_eval)
        case "tan^-1" => result = evaluateFunc("tan^-1", total_eval)
        case "log2" => result = evaluateFunc("log2", total_eval)
        case "2^x" => result = evaluateFunc("2^x", total_eval)
        // Sleeper Functions
        case "y√ x" => result = setSleeper("y√ x", total_eval)
        case "x^y" => result = setSleeper("x^y", total_eval)
        case "y^x" => result = setSleeper("y^x", total_eval)
        case "logy" => result = setSleeper("logy", total_eval)
        // Special Case Evaluations
        case "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9" | "0" | "." => result = evaluateNumber(op, total_eval)
        case "=" => result = evaluateEquals(total_eval)
        case "+" | "-" | "/" | "*" => result = evaluateAlgebraicOperation(total_eval)
        case "(" | ")" => result = evaluateBrackets(total_eval)
        // Global Changes
        case "clear" => result = evaluateClear()
        case "+/-" => result = evaluateInversion(total_eval)
        case "toRad" => result = evaluateDegRadConvert(func_name = "to_rad")
        case "toDeg" => result = evaluateDegRadConvert(func_name = "to_deg")
        case "2nd" => result = evaluate2nd()
        case _ =>
      }
    } else {
      sleeper = false
      result = evaluateSleeper(sleeper_name, total_eval)

    }
    println("Here is the result", result)
    result
  }

  //--------------
  // Evaluations
  //--------------
  def evaluate2nd(): String = {
    operations.pop() // removing function name
    var result: String = ""
    result
  }

  def evaluateDegRadConvert(func_name: String) = {
    operations.pop() // removing function name
    val result: String = ""
    func_name match {
      case "to_rad" => radians = true
      case "to_deg" => radians = false
    }
    result
  }

  def evaluateBrackets(total_eval: String) = {
    var result: String = ""
    in_brackets = true
    var this_bracket = operations.top
    val recent_bracket = new bracket_record(this_bracket, operations.size + brackets.size + 1)
    println("Before evaluate", operations, brackets, result)
    brackets.push(recent_bracket)
    if (this_bracket == ")") result = bracketFinished(total_eval)
    println("this is the stack, total_eval, brackets on an end bracket", operations, total_eval, brackets)
    brackets.foreach {
      b => println("Bracket Type, Bracket Position", b.b_type, b.location)
    }
    result
  }

  def bracketFinished(total_eval: String) = {
    var result: String = ""
    println("in bracket finished", operations, brackets)
    if (in_brackets) {
      operations.pop() //popping recent closed bracket
      println("after the evaluate equals", operations, brackets, result)
      result = evaluateEquals(total_eval)
      brackets.pop() // popping closing brace
      brackets.pop() // popping open brace
      if (brackets.isEmpty) in_brackets = false

    }
    else {


    }

    result

  }

  def evaluateClear(): String = {
    operations.clear()
    "0" // returns zero to reset on clear
  }

  def evaluateNumber(operation: String, total_eval: String): String = {
    var recent_operation: String = ""
    var recent_num: String = ""
    var result: String = ""
    var evaluation = total_eval

    if (operations.size > 1) {
      recent_num = operations.pop()
      recent_operation = operations.pop()

      recent_operation match {
        case "+" => result = recent_num
        case "-" => result = recent_num
        case "/" => result = recent_num
        case "*" => result = recent_num
        case "." => result = decimalEvaluation(evaluation)
        case "\\d" => result = numericalEvaluation(evaluation, recent_operation, recent_num)
        case _ => result = multiDigitEvaluation(evaluation, recent_operation, recent_num) // multi-digit #
      }
      evaluateSpecialCases(result, recent_operation, recent_num)
    }
    else {
      if (operations.top == ".") {
        operations.pop()
        operations.push("0.")
        result = "0."
      } else {
        result = operation
      }
    }
    result
  }

  def evaluateAlgebraicOperation(total_eval: String): String = {
    println("In evaluating algebraic expression", total_eval, operations)
    var result: String = ""
    if (!in_brackets) {
      var second_operation: String = ""
      if (operations.size == 4) {
        second_operation = operations.pop()
        println("after top op pop", total_eval, operations)
        result = evaluateEquals(total_eval)
        println("after eval equals", total_eval, operations, result)
        operations.push(second_operation)
      }
    }
    result
  }

  def combiningNumbers(evaluation: String, operation: String): String = {
    operations.pop() // removes partial digit so it does not remain in the stack
  }

  def evaluateInversion(total_eval: String): String = {
    var result: Double = 0 - total_eval.toDouble
    result.toString
  }

  def evaluateEquals(total_eval: String): String = {
    if (operations.top == "=") operations.pop() //removing equals from stack if present (see AlgebraicOperation function)
    if (operations.size == 1) {
      total_eval
    }
    else {

      var result: String = ""

      if (operations.size == 2 && operations.contains("(")) {
        result = operations.pop()
        operations.pop() // removing bracket
        result
      }

      var total: Double = 0
      var num2: Double = 0
      var operation: String = ""
      var num1: Double = 0
      var num1_temp: String = ""

      breakable { while (operations.size != 1) {
        println("in eval, here is stack", operations)

        num2 = operations.pop().toDouble //second number is top of stack
        operation = operations.pop()
        num1_temp = operations.pop()
        // removing bracket from num1_temp if it is a num that contains an open bracket (working backwards)
        if (num1_temp != "(" && num1_temp.contains("(")) num1_temp = num1_temp.replace("(", "")

        println("here is num1_temp", num1_temp)
        num1 = num1_temp.toDouble

        println("stuff", num2, operation, num1)

        operation match {
          case "+" => total = num1 + num2
          case "-" => total = num1 - num2
          case "/" => total = num1 / num2
          case "*" => total = num1 * num2
        }
        operations.push(total.toString)
        result = operations.top
        if (operations.contains("\\d\\)") || operations.contains("\\(\\d")) println("HOLY FUCK")
      }}

      result
    }
  }

  //--------------
  // Evaluation Helper Functions
  //--------------
  def isOperationFollowedByDecimal(evaluation: String) = {
    evaluation match {
      case "+" | "-" | "/" | "*" => true // operation followed by decimal
      case _ => false
    }
  }

  def decimalEvaluation(evaluation: String): String = {
    println("Here is my evaluation", evaluation)
    if (evaluation.contains(".")) evaluation
    else if (isOperationFollowedByDecimal(evaluation)) "0." //append 0 to front new decimal value
    else evaluation.concat(".")
  }

  def numericalEvaluation(evaluation: String, recent_operation: String, recent_num: String): String = {
    if (evaluation == "0") recent_num
    else recent_operation.concat(recent_num)
  }

  def multiDigitEvaluation(evaluation: String, recent_operation: String, recent_num: String): String = {
    //prevents multiple decimals in string
    if (recent_operation.contains(recent_num) && recent_num == ".") recent_operation
    else if (evaluation == "0") recent_num
    else recent_operation.concat(recent_num)
  }

  def evaluateSpecialCases(result: String, recent_operation: String, recent_num: String) = {
    if (result == ".") { // appending 0 to front of decimal if by itself
      operations.push(recent_operation, "0.")
    }
    else if (result.contains(".") && recent_num == ".") { // preventing multiple decimals in string
      operations.push(result)
    }
    else if (recent_operation == "-") { // pushing back onto stack preparing for an equals operation
      operations.push("+", (0 - result.toInt).toString)
    }
    else {
      if (result.contains(recent_operation)) { // for combining digits
        operations.push(result)
      } else operations.push(recent_operation, result) // pushing back onto stack preparing for an equals operation
    }
  }

  def evaluateSleeper(func_name: String, total_eval: String): String = {
    var result: String = ""
    var y: String = operations.pop()
    var op: String = operations.pop()
    var x: String = operations.pop()

    func_name match {
      case "y√ x" => result = nthRoot(y.toInt, x.toDouble).toString
      case "x^y" => result = pow(x.toDouble, y.toDouble).toString
      case "y^x" => result = pow(y.toDouble, x.toDouble).toString
      case "logy" => result = (log10(x.toDouble) / log10(y.toDouble)).toString
    }
    operations.push(result)
    result
  }

  def evaluateConst(const: String, total_eval: String): String = {
    var result: String = ""
    operations.pop()
    operations.push(const)
    if (operations.size == 3) result = evaluateEquals(total_eval)
    else {
      result = operations.pop()
      operations.clear() //wiping repeated rands
      operations.push(result) //pushing current rand back onto stack
    }
    result
  }

  def evaluateFunc(func_name: String, total_eval: String): String = {
    var result: String = ""
    operations.pop() //remove func from stack
    println(func_name, operations)
    if (operations.size >= 1) { // if func can be applied properly (only one element on stack)
      if (operations.size == 3) result = evaluateEquals(total_eval)
      func_name match {
        case "%" => result = (operations.pop().toDouble / 100).toString
        case "cos" => result = cos(operations.pop().toDouble * degRadianConverter).toString
        case "sin" => result = sin(operations.pop().toDouble * degRadianConverter).toString
        case "tan" => result = tan(operations.pop().toDouble * degRadianConverter).toString
        case "cosh" => result = cosh(operations.pop().toDouble * degRadianConverter).toString
        case "sinh" => result = sinh(operations.pop().toDouble * degRadianConverter).toString
        case "tanh" => result = tanh(operations.pop().toDouble * degRadianConverter).toString
        case "!" => result = factorial(operations.pop().toDouble).toInt.toString
        case "1/x" => result = (1 / operations.pop().toDouble).toString
        case "2√ x" => result = sqrt(operations.pop().toDouble).toString
        case "3√ x" => result = cbrt(operations.pop().toDouble).toString
        case "ln" => result = log(operations.pop().toDouble).toString
        case "log10" => result = log10(operations.pop().toDouble).toString
        case "x^2" => result = pow(operations.pop().toDouble, 2).toString
        case "x^3" => result = pow(operations.pop().toDouble, 3).toString
        case "e^x" => result = pow(E, operations.pop().toDouble).toString
        case "10^x" => result = pow(10, operations.pop().toDouble).toString
        case "sinh^-1" => result = asinh(operations.pop().toDouble).toString
        case "cosh^-1" => result = acosh(operations.pop().toDouble).toString
        case "tanh^-1" => result = atanh(operations.pop().toDouble).toString
        case "sin^-1" => result = asin(operations.pop().toDouble).toString
        case "cos^-1" => result = acos(operations.pop().toDouble).toString
        case "tan^-1" => result = atan(operations.pop().toDouble).toString
        case "log2" => result = (log10(operations.pop().toDouble) / log10(2)).toString
        case "2^x" => result = pow(2, operations.pop().toDouble).toString
        case _ =>

      }
      operations.push(result)
    } // else do nothing
    result
  }

  def setSleeper(name: String, total_eval: String): String = {
    sleeper_name = name
    sleeper = true
    total_eval
  }


  def nthRoot(n: Int, a: Double): Double = {
    def loop(x0: Double): Double = {
      val x1 = 1.0d / n * ((n - 1) * x0 + a / math.pow(x0, n - 1))
      if (x0 <= x1) x0
      else loop(x1)
    }

    loop(a / 2)
  }

  def degRadianConverter(): Double = {
    if (radians) 0.005555556 * Pi
    else 1
  }


  def factorial(n: Double): Double = {
    if (n == 0)
      1
    else
      n * factorial(n - 1)
  }
}
