module com.example.humansversegoblinsgui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.humansversegoblinsgui to javafx.fxml;
    exports com.example.humansversegoblinsgui;
}