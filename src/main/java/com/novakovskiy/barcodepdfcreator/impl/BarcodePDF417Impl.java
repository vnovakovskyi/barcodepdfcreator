package com.novakovskiy.barcodepdfcreator.impl;

import com.itextpdf.text.pdf.BarcodePDF417;
import com.novakovskiy.barcodepdfcreator.interfaces.Barcode;
import com.novakovskiy.barcodepdfcreator.utils.Utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BarcodePDF417Impl implements Barcode {
    private List<BarcodePDF417> barcodeList;
    private BarcodePDF417 barcode;

    @Override
    public void createBarcodeList(List<String> messages) {
        barcodeList = new ArrayList<>();
        messages.forEach(message -> {
            if (!message.equals("0")) {
                create(message);
                barcodeList.add(barcode);
            }
        });
    }

    @Override
    public void create(String message) {
        barcode = new BarcodePDF417();
        barcode.setText(message.toUpperCase().replace(" ", ""));
        barcode.createAwtImage(Color.black, Color.white);
        barcode.setAspectRatio(3);
        barcode.setErrorLevel(Integer.parseInt(Utils.getProperties().getProperty("barcode.error.level")));
    }

    public List<BarcodePDF417> getBarcodeList() {
        return barcodeList;
    }
}
