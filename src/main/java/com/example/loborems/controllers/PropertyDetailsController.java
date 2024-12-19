package com.example.loborems.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PropertyDetailsController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ImageView mainImageView;

    @FXML
    private ImageView thumbImage1;

    @FXML
    private ImageView thumbImage2;

    @FXML
    private ImageView thumbImage3;

    @FXML
    private ImageView thumbImage4;

    @FXML
    private Button backButton;

    @FXML
    private Button editButton;


    // مسارات الصور
    private final String[] imagePaths = {
            getClass().getResource("/images/th.jpeg").toExternalForm(),
            getClass().getResource("/images/imgB2.gif").toExternalForm(),
            getClass().getResource("/images/th3.jpeg").toExternalForm(),
            getClass().getResource("/images/FB4.jpg").toExternalForm()
    };

    @FXML
    public void initialize() {
        // إعداد لون الخلفية

        // تعيين الصور الصغيرة عند بداية التطبيق
        thumbImage1.setImage(new Image(imagePaths[0]));
        thumbImage2.setImage(new Image(imagePaths[1]));
        thumbImage3.setImage(new Image(imagePaths[2]));
        thumbImage4.setImage(new Image(imagePaths[3]));

        // الصورة الرئيسية تكون الأولى افتراضياً
        mainImageView.setImage(new Image(imagePaths[0]));

        // إضافة تأثير تغيير الصورة عند الضغط على الصور المصغرة
        thumbImage1.setOnMouseClicked(event -> changeMainImage(imagePaths[0]));
        thumbImage2.setOnMouseClicked(event -> changeMainImage(imagePaths[1]));
        thumbImage3.setOnMouseClicked(event -> changeMainImage(imagePaths[2]));
        thumbImage4.setOnMouseClicked(event -> changeMainImage(imagePaths[3]));

        // إضافة تأثيرات عند المرور على الصور المصغرة (مثل تكبير الحجم)
        addHoverEffect(thumbImage1);
        addHoverEffect(thumbImage2);
        addHoverEffect(thumbImage3);
        addHoverEffect(thumbImage4);
    }

    // تغيير الصورة الرئيسية بدون تأثير التلاشي
    private void changeMainImage(String imagePath) {
        mainImageView.setImage(new Image(imagePath));
    }

    // إضافة تأثير عند التمرير على الصور المصغرة
    private void addHoverEffect(ImageView imageView) {
        imageView.setOnMouseEntered(event -> {
            imageView.setScaleX(1.1);
            imageView.setScaleY(1.1);
        });
        imageView.setOnMouseExited(event -> {
            imageView.setScaleX(1.0);
            imageView.setScaleY(1.0);
        });
    }

    @FXML
    private void onBackButtonClicked() {
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            Scene newScene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/loborems/PropertyListing/property-listing.fxml")));
            stage.setScene(newScene);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load Client List Page.");
        }
    }

    @FXML
    private void onEditButtonClicked() {
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            Scene newScene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/loborems/AddProperty/add-property.fxml")));

            stage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load Client List Page.");
        }
    }

}
