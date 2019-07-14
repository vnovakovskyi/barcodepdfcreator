package com.novakovskiy.barcodepdfcreator;

import com.itextpdf.text.pdf.BarcodePDF417;
import com.novakovskiy.barcodepdfcreator.impl.BarcodePDF417Impl;

import java.util.List;

class BarcodeCreator {
    List<BarcodePDF417> createBarcodePDF417List(List<String> messages){
        BarcodePDF417Impl barcodePDF417 = new BarcodePDF417Impl();
        barcodePDF417.createBarcodeList(messages);
        return barcodePDF417.getBarcodeList();
    }
}
