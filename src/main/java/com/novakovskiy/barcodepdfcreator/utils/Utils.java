package com.novakovskiy.barcodepdfcreator.utils;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BarcodePDF417;
import com.novakovskiy.barcodepdfcreator.Main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

    private static Properties properties = null;

    private Utils() {
    }

    private static void setProperties(){
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties = new Properties();
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Properties getProperties() {
        if (properties == null)
            setProperties();
        return properties;
    }

    public static Image barcodeToImage(BarcodePDF417 barcodePDF417) {
        Image code417Image = null;
        try {
            code417Image = barcodePDF417.getImage();
            code417Image.scalePercent(100);
        } catch (BadElementException e) {
            e.printStackTrace();
        }
        return code417Image;
    }
}
