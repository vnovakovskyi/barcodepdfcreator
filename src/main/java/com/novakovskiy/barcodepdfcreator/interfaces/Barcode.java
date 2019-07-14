package com.novakovskiy.barcodepdfcreator.interfaces;

import java.util.List;

public interface Barcode {
    void create(String message);
    void createBarcodeList(List<String> messages);
}
