package objects;

import javafx.scene.control.TextField;

public class TextArea extends TextField {

    TextArea() {
        onlyNumber(this);
    }
    private void onlyNumber(TextField textField) {
        textField.textProperty().addListener((observable, oldValue,newValue) ->{
            if (!newValue.matches("[-]?\\d*([.]\\d*)?")) {                     // for int use [-]?\d*
                textField.setText(oldValue);
            }
        });
    }

    double getValue(){
        return Double.valueOf(this.getText());
    }
}