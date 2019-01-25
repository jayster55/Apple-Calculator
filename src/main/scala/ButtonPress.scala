import javafx.fxml.FXML
import javafx.scene.control.Button

object ButtonPress {
  //---------------------------------------------
  // Button Press Implementations
  //---------------------------------------------
  //--------------
  // Numbers
  //--------------
  def btnOnePressed(): Unit = {
    CalculatorEvaluations.operations.push("1")
  }

  def btnTwoPressed(): Unit = {
    CalculatorEvaluations.operations.push("2")
  }

  def btnThreePressed(): Unit = {
    CalculatorEvaluations.operations.push("3")
  }

  def btnFourPressed(): Unit = {
    CalculatorEvaluations.operations.push("4")
  }

  def btnFivePressed(): Unit = {
    CalculatorEvaluations.operations.push("5")
  }

  def btnSixPressed(): Unit = {
    CalculatorEvaluations.operations.push("6")
  }

  def btnSevenPressed(): Unit = {
    CalculatorEvaluations.operations.push("7")
  }

  def btnEightPressed(): Unit = {
    CalculatorEvaluations.operations.push("8")
  }

  def btnNinePressed(): Unit = {
    CalculatorEvaluations.operations.push("9")
  }

  def btnZeroPressed(): Unit = {
    CalculatorEvaluations.operations.push("0")
  }

  def btnDecimalPressed(): Unit = {
    CalculatorEvaluations.operations.push(".")
  }
  //--------------
  // Orange Operations (+,-,/,x,=)
  //--------------
  def btnDivisionPressed(): Unit = {
    CalculatorEvaluations.operations.push("/")
  }

  def btnMultiplicationPressed(): Unit = {
    CalculatorEvaluations.operations.push("*")
  }

  def btnSubtractionPressed(): Unit = {
    CalculatorEvaluations.operations.push("-")
  }

  def btnAdditionPressed(): Unit = {
    CalculatorEvaluations.operations.push("+")
  }

  def btnEqualsPressed(): Unit = {
    CalculatorEvaluations.operations.push("=")
  }
  //--------------
  // First Row Operations (Brackets, m-CalculatorEvaluations.operations, All Clear, +/- Inversion, Percent)
  //--------------
  def btnLeftBracePressed(): Unit = {
    CalculatorEvaluations.operations.push("(")
  }

  def btnRightBracePressed(): Unit = {
    CalculatorEvaluations.operations.push(")")
  }

  def btnMCPressed(): Unit = {
    CalculatorEvaluations.operations.push("mc")
  }

  def btnMPlusPressed(): Unit = {
    CalculatorEvaluations.operations.push("m+")
  }

  def btnMMinusPressed(): Unit = {
    CalculatorEvaluations.operations.push("m-")
  }

  def btnMRPressed(): Unit = {
    CalculatorEvaluations.operations.push("mr")
  }

  def btnClearPressed(): Unit = {
    CalculatorEvaluations.operations.push("clear")
  }

  def btnPlusMinusInvertPressed(): Unit = {
    CalculatorEvaluations.operations.push("+/-")
  }

  def btnPercentPressed(): Unit = {
    CalculatorEvaluations.operations.push("%")
  }
  //--------------
  // Second Row Operations (2nd, Squared, Cubed, x^y, e^x, 10^x)
  //--------------
  def btnSecondPressed(btn_2nd: Button, btn_sinh: Button, btn_cosh: Button, btn_tanh: Button,
                       btn_sin: Button, btn_cos: Button, btn_tan: Button, btn_ln: Button,
                       btn_logten: Button, btn_epower: Button, btn_tenpower: Button): Unit = {
    btn_2nd.getText match {
      case "2nd" => flipSecondButtons(btn_sinh, btn_cosh, btn_tanh, btn_sin, btn_cos,
        btn_tan, btn_ln, btn_logten, btn_epower, btn_tenpower, "2nd")
      case "1st" => flipSecondButtons(btn_sinh, btn_cosh, btn_tanh, btn_sin, btn_cos,
        btn_tan, btn_ln, btn_logten, btn_epower, btn_tenpower, "1st")
    }
    btn_2nd.getText match {
      case "2nd" => btn_2nd.setText("1st")
      case "1st" => btn_2nd.setText("2nd")
    }
    CalculatorEvaluations.operations.push("2nd")
  }

  def btnSquaredPressed(): Unit = {
    CalculatorEvaluations.operations.push("x^2")
  }

  def btnCubedPressed(): Unit = {
    CalculatorEvaluations.operations.push("x^3")
  }

  def btnPowerPressed(): Unit = {
    CalculatorEvaluations.operations.push("x^y")
  }

  def btnEPowerPressed(): Unit = {
    CalculatorEvaluations.operations.push("e^x")
  }

  def btnTenPowerPressed(): Unit = {
    CalculatorEvaluations.operations.push("10^x")
  }
  //--------------
  // Third Row Operations (1/x, 2√ x, 3√ x, y√ x, ln, log10)
  //--------------
  def btnOneDivXPressed(): Unit = {
    CalculatorEvaluations.operations.push("1/x")
  }

  def btnSquareRootPressed(): Unit = {
    CalculatorEvaluations.operations.push("2√ x")
  }

  def btnCubedRootPressed(): Unit = {
    CalculatorEvaluations.operations.push("3√ x")
  }

  def btnYSquareRootPressed(): Unit = {
    CalculatorEvaluations.operations.push("y√ x")
  }

  def btnLnPressed(): Unit = {
    CalculatorEvaluations.operations.push("ln")
  }

  def btnLogTenPressed(): Unit = {
    CalculatorEvaluations.operations.push("log10")
  }
  //--------------
  // Fourth Row Operations (x!, sin, cos, tan, e, EE)
  // --------------
  def btnFactorialPressed(): Unit = {
    CalculatorEvaluations.operations.push("!")
  }

  def btnSinPressed(): Unit = {
    CalculatorEvaluations.operations.push("sin")
  }

  def btnCosPressed(): Unit = {
    CalculatorEvaluations.operations.push("cos")
  }

  def btnTanPressed(): Unit = {
    CalculatorEvaluations.operations.push("tan")
  }

  def btnEPressed(): Unit = {
    CalculatorEvaluations.operations.push("e")
  }

  def btnEEPressed(): Unit = {
    CalculatorEvaluations.operations.push("EE")
  }
  //--------------
  // Fifth Row Operations (Rad, sinh, cosh, tanh, Pi, Rand) // --------------


  def btnRadPressed(btn: Button): Unit = {
    btn.getText match {
      case "Deg" => btn.setText("Rad")
      case "Rad" => btn.setText("Deg")
    }
    btn.getText match {
      case "Deg" => CalculatorEvaluations.operations.push("toDeg")
      case "Rad" => CalculatorEvaluations.operations.push("toRad")
    }
  }

  def btnSinhPressed(): Unit = {
    CalculatorEvaluations.operations.push("sinh")
  }

  def btnCoshPressed(): Unit = {
    CalculatorEvaluations.operations.push("cosh")
  }

  def btnTanhPressed(): Unit = {
    CalculatorEvaluations.operations.push("tanh")
  }

  def btnPiPressed(): Unit = {
    CalculatorEvaluations.operations.push("pi")
  }

  def btnRandPressed(): Unit = {
    CalculatorEvaluations.operations.push("rand")
  }

  def btnSinhInversePressed() = {
    CalculatorEvaluations.operations.push("sinh^-1")
  }

  def btnCoshInversePressed() = {
    CalculatorEvaluations.operations.push("cosh^-1")
  }

  def btnTanhInversePressed() = {
    CalculatorEvaluations.operations.push("tanh^-1")
  }

  def btnSinInversePressed() = {
    CalculatorEvaluations.operations.push("sin^-1")
  }

  def btnCosInversePressed() = {
    CalculatorEvaluations.operations.push("cos^-1")
  }

  def btnTanInversePressed() = {
    CalculatorEvaluations.operations.push("tan^-1")
  }

  def btnLogYPressed() = {
    CalculatorEvaluations.operations.push("logy")
  }

  def btnLogTwoPressed() = {
    CalculatorEvaluations.operations.push("log2")
  }

  def btnYPowerXPressed() = {
    CalculatorEvaluations.operations.push("y^x")
  }

  def btnTwoPowerXPressed() = {
    CalculatorEvaluations.operations.push("2^x")
  }

  def flipSecondButtons(btn_sinh: Button, btn_cosh: Button, btn_tanh: Button,
                        btn_sin: Button, btn_cos: Button, btn_tan: Button, btn_ln: Button,
                        btn_logten: Button, btn_epower: Button, btn_tenpower: Button, flip: String): Unit = {
    var style: String = ""
   if (flip == "2nd") {
     style = "-fx-background-color: #47474B#47474B; -fx-font-family: Lucida Grande; -fx-font-size: 12;"
     // Changing UI
     btn_sinh.setText("sinh^-1")
     btn_sinh.setStyle(style)
     btn_cosh.setText("cosh^-1")
     btn_cosh.setStyle(style)
     btn_tanh.setText("tanh^-1")
     btn_tanh.setStyle(style)
     btn_sin.setText("sin^-1")
     btn_sin.setStyle(style)
     btn_cos.setText("cos^-1")
     btn_cos.setStyle(style)
     btn_tan.setText("tan^-1")
     btn_tan.setStyle(style)
     // style changes unneeded for below, font still fits
     btn_ln.setText("logy")
     btn_logten.setText("log2")
     btn_epower.setText("y^x")
     btn_tenpower.setText("2^x")

     // Changing FX:IDs
     btn_sinh.setId("btn_sinh^-1")
     btn_cosh.setId("btn_cosh^-1")
     btn_tanh.setId("btn_tanh^-1")
     btn_sin.setId("btn_sin^-1")
     btn_cos.setId("btn_cos^-1")
     btn_tan.setId("btn_tan^-1")
     btn_ln.setId("btn_logy")
     btn_logten.setId("btn_log2")
     btn_epower.setId("btn_y^x")
     btn_tenpower.setId("btn_2^x")
   } else {
     style = "-fx-background-color: #47474B#47474B; -fx-font-family: Lucida Grande; -fx-font-size: 15;"
     // Changing UI
     btn_sinh.setText("sinh")
     btn_sinh.setStyle(style)
     btn_cosh.setText("cosh")
     btn_cosh.setStyle(style)
     btn_tanh.setText("tanh")
     btn_tanh.setStyle(style)
     btn_sin.setText("sin")
     btn_sin.setStyle(style)
     btn_cos.setText("cos")
     btn_cos.setStyle(style)
     btn_tan.setText("tan")
     btn_tan.setStyle(style)
     btn_ln.setText("ln")
     btn_logten.setText("log10")
     btn_epower.setText("e^x")
     btn_tenpower.setText("10^x")
     // Changing FX:IDs
     btn_sinh.setId("btn_sinh")
     btn_cosh.setId("btn_cosh")
     btn_tanh.setId("btn_tanh")
     btn_sin.setId("btn_sin")
     btn_cos.setId("btn_cos")
     btn_tan.setId("btn_tan")
     btn_ln.setId("btn_ln")
     btn_logten.setId("btn_logten")
     btn_epower.setId("btn_epower")
     btn_tenpower.setId("btn_10power")
   }

  }
}
