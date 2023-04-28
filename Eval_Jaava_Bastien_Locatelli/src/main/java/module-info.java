module com.example.eval_jaava_bastien_locatelli {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.eval_jaava_bastien_locatelli to javafx.fxml;
    exports com.example.eval_jaava_bastien_locatelli;
}