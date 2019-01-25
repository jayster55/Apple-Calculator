import javafx.application.Application
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.Scene
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage
import javafx.scene.control.{Button, TextField}
import javafx.scene.input.{KeyCode, KeyEvent}

class Calculator extends Application {
  @FXML
  var btn_one: Button = _

  override def start(primaryStage: Stage): Unit = {
    val loader: FXMLLoader = new FXMLLoader()
    loader.setLocation(getClass.getResource("calc.fxml"))


    val layout:AnchorPane = loader.load()
    val scene = new Scene(layout)

    primaryStage.setTitle("Calculator")
    primaryStage.setScene(scene)
    primaryStage.setResizable(false)
    primaryStage.show()

    val calculatorController = loader.getController[CalculatorController]
    calculatorController.setText(scene.lookup(  "#text_value").asInstanceOf[TextField])

    // Key Press Registration
    scene.setOnKeyPressed((event: KeyEvent) => {
      event.getCode match {
        // Digits
        case KeyCode.DIGIT0 | KeyCode.NUMPAD0 => ButtonPress.btnZeroPressed()
        case KeyCode.DIGIT1 | KeyCode.NUMPAD1 => ButtonPress.btnOnePressed()
        case KeyCode.DIGIT2 | KeyCode.NUMPAD2 => ButtonPress.btnTwoPressed()
        case KeyCode.DIGIT3 | KeyCode.NUMPAD3 => ButtonPress.btnThreePressed()
        case KeyCode.DIGIT4 | KeyCode.NUMPAD4 => ButtonPress.btnFourPressed()
        case KeyCode.DIGIT5 | KeyCode.NUMPAD5 => ButtonPress.btnFivePressed()
        case KeyCode.DIGIT6 | KeyCode.NUMPAD6 => ButtonPress.btnSixPressed()
        case KeyCode.DIGIT7 | KeyCode.NUMPAD7 => ButtonPress.btnSevenPressed()
        case KeyCode.DIGIT8 | KeyCode.NUMPAD8 => ButtonPress.btnEightPressed()
        case KeyCode.DIGIT9 | KeyCode.NUMPAD9 => ButtonPress.btnNinePressed()
        // Operations
        case KeyCode.ENTER | KeyCode.EQUALS => ButtonPress.btnEqualsPressed()
        case KeyCode.MINUS | KeyCode.SUBTRACT => ButtonPress.btnSubtractionPressed()
        case KeyCode.SLASH | KeyCode.DIVIDE => ButtonPress.btnDivisionPressed()
        case KeyCode.PERIOD | KeyCode.DECIMAL => ButtonPress.btnDecimalPressed()
        case KeyCode.MULTIPLY | KeyCode.ASTERISK | KeyCode.X => ButtonPress.btnMultiplicationPressed()
        case KeyCode.ADD | KeyCode.PLUS => ButtonPress.btnAdditionPressed()
        case KeyCode.EXCLAMATION_MARK => ButtonPress.btnFactorialPressed()
        case KeyCode.BRACELEFT | KeyCode.LEFT_PARENTHESIS | KeyCode.OPEN_BRACKET => ButtonPress.btnLeftBracePressed()
        case KeyCode.BRACERIGHT | KeyCode.RIGHT_PARENTHESIS | KeyCode.CLOSE_BRACKET => ButtonPress.btnRightBracePressed()
        case KeyCode.C => ButtonPress.btnClearPressed()
        case _ =>
      }

      calculatorController.updateCalculator()
    })
  }
}
