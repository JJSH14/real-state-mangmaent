package com.example.loborems;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class PropertyDetailsPageController {

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
}
