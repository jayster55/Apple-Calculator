import javafx.fxml.FXML
import javafx.event.ActionEvent
import javafx.scene.control.{Button, TextField}

class CalculatorController() {


  @FXML var btn_rad_deg_switcher: Button = _
  @FXML var btn_2nd: Button = _
  @FXML var btn_sinh: Button = _
  @FXML var btn_cosh: Button = _
  @FXML var btn_tanh: Button = _
  @FXML var btn_sin: Button = _
  @FXML var btn_cos: Button = _
  @FXML var btn_tan: Button = _
  @FXML var btn_ln: Button = _
  @FXML var btn_logten: Button = _
  @FXML var btn_epower: Button = _
  @FXML var btn_tenpower: Button = _

  //--------------
  // Global Variables
  //--------------
  private var field: Option[TextField] = None
  var total_eval: String = ""

  //---------------------------------------------
  // Text Display Implementations
  //---------------------------------------------
  def setText(field: TextField): Unit = {
    this.field = Some(field)
  }

  def updateDisplay(text: String): Unit = {
    // removing leading and trailing zeros
    field match {
      case Some(innerField) => innerField.setText(text)
      case None =>
    }
  }

  //
  //  def getDisplay: String = {
  //    field match {
  //      case Some(innerField) => innerField.getText
  //      case None => ""
  //    }
  //  }

  def updateCalculator(): Unit = {
    var new_total: String = CalculatorEvaluations.evaluateTotal(total_eval)
    if (new_total != "") {
      total_eval = new_total.replace("^0.$", "").replaceAll("\\.0+$", "")
      updateDisplay(total_eval)
    }
  }

  //---------------------------------------------
  // FXML Linkages for Buttons
  //---------------------------------------------
  @FXML def buttonPress(event: ActionEvent): Unit = {

    // Following line extracts the id from the getSource object (as could not cast to Button)
    val id = event.getSource.toString.split(",")(0).split("=")(1)
    println("here is the actionevent entered")
    println(id)

    id match {
      case "btn_zero" => ButtonPress.btnZeroPressed()
      case "btn_one" => ButtonPress.btnOnePressed()
      case "btn_two" => ButtonPress.btnTwoPressed()
      case "btn_three" => ButtonPress.btnThreePressed()
      case "btn_four" => ButtonPress.btnFourPressed()
      case "btn_five" => ButtonPress.btnFivePressed()
      case "btn_six" => ButtonPress.btnSixPressed()
      case "btn_seven" => ButtonPress.btnSevenPressed()
      case "btn_eight" => ButtonPress.btnEightPressed()
      case "btn_nine" => ButtonPress.btnNinePressed()
      case "btn_division" => ButtonPress.btnDivisionPressed()
      case "btn_multiplication" => ButtonPress.btnMultiplicationPressed()
      case "btn_subtraction" => ButtonPress.btnSubtractionPressed()
      case "btn_addition" => ButtonPress.btnAdditionPressed()
      case "btn_equals" => ButtonPress.btnEqualsPressed()
      case "btn_leftbrace" => ButtonPress.btnLeftBracePressed()
      case "btn_rightbrace" => ButtonPress.btnRightBracePressed()
      case "btn_mc" => ButtonPress.btnMCPressed()
      case "btn_mplus" => ButtonPress.btnMPlusPressed()
      case "btn_mminus" => ButtonPress.btnMMinusPressed()
      case "btn_mr" => ButtonPress.btnMRPressed()
      case "btn_clear" => ButtonPress.btnClearPressed()
      case "btn_plusminusinvert" => ButtonPress.btnPlusMinusInvertPressed()
      case "btn_percent" => ButtonPress.btnPercentPressed()
      case "btn_squared" => ButtonPress.btnSquaredPressed()
      case "btn_cubed" => ButtonPress.btnCubedPressed()
      case "btn_power" => ButtonPress.btnPowerPressed()
      case "btn_epower" => ButtonPress.btnEPowerPressed()
      case "btn_tenpower" => ButtonPress.btnTenPowerPressed()
      case "btn_onedivx" => ButtonPress.btnOneDivXPressed()
      case "btn_squareroot" => ButtonPress.btnSquareRootPressed()
      case "btn_cubedroot" => ButtonPress.btnCubedRootPressed()
      case "btn_ysquareroot" => ButtonPress.btnYSquareRootPressed()
      case "btn_ln" => ButtonPress.btnLnPressed()
      case "btn_logten" => ButtonPress.btnLogTenPressed()
      case "btn_factorial" => ButtonPress.btnFactorialPressed()
      case "btn_sin" => ButtonPress.btnSinPressed()
      case "btn_cos" => ButtonPress.btnCosPressed()
      case "btn_tan" => ButtonPress.btnTanPressed()
      case "btn_e" => ButtonPress.btnEPressed()
      case "btn_ee" => ButtonPress.btnEEPressed()
      case "btn_sinh" => ButtonPress.btnSinhPressed()
      case "btn_cosh" => ButtonPress.btnCoshPressed()
      case "btn_tanh" => ButtonPress.btnTanhPressed()
      case "btn_pi" => ButtonPress.btnPiPressed()
      case "btn_rand" => ButtonPress.btnRandPressed()
      case "btn_decimal" => ButtonPress.btnDecimalPressed()
      case "btn_rad_deg_switcher" => ButtonPress.btnRadPressed(btn_rad_deg_switcher)
      case "btn_2nd" => ButtonPress.btnSecondPressed(btn_2nd, btn_sinh, btn_cosh, btn_tanh, btn_sin, btn_cos, btn_tan,
        btn_ln, btn_logten, btn_epower, btn_tenpower)
      case "btn_sinh^-1" => ButtonPress.btnSinhInversePressed()
      case "btn_cosh^-1" => ButtonPress.btnCoshInversePressed()
      case "btn_tanh^-1" => ButtonPress.btnTanhInversePressed()
      case "btn_sin^-1" => ButtonPress.btnSinInversePressed()
      case "btn_cos^-1" => ButtonPress.btnCosInversePressed()
      case "btn_tan^-1" => ButtonPress.btnTanInversePressed()
      case "btn_logy" => ButtonPress.btnLogYPressed()
      case "btn_log2" => ButtonPress.btnLogTwoPressed()
      case "btn_y^x" => ButtonPress.btnYPowerXPressed()
      case "btn_2^x" => ButtonPress.btnTwoPowerXPressed()
      case _ =>
    }

    println("Updating Calculator")
    updateCalculator()
  }
}
