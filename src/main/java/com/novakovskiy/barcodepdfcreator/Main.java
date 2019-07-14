package com.novakovskiy.barcodepdfcreator;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BarcodePDF417;
import com.itextpdf.text.pdf.PdfPTable;
import com.novakovskiy.barcodepdfcreator.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<String> messages = null;

    public static void main(String[] args) {
        readMessagesFromUser();

        if (messages == null)
            messages = List.of(
                    "AMZN,PO:FBA15H9RGK6C,FNSKU:X001UECQAX,QTY:1,EXP:140428",
                    "AMZN,PO:FBA15H9RGK6C,FNSKU:X001UECQAX,QTY:1,EXP:140428",
                    "AMZN,PO:FBA15H9RGK6C,FNSKU:X001UECQAX,QTY:1,EXP:140428",
                    "AMZN,PO:FBA15H9RGK6C,FNSKU:X001UECQAX,QTY:1,EXP:140428",
                    "AMZN,PO:FBA15H9RGK6C,FNSKU:X001UECQAX,QTY:1,EXP:140428",
                    "AMZN,PO:FBA15H9RGK6C,FNSKU:X001UECQAX,QTY:1,EXP:140428",
                    "AMZN,PO:FBA15H9RGK6C,FNSKU:X001UECQAX,QTY:1,EXP:140428",
                    "AMZN,PO:FBA15H9RGK6C,FNSKU:X001UECQAX,QTY:1,EXP:140428",
                    "AMZN,PO:FBA15H9RGK6C,FNSKU:X001UECQAX,QTY:1,EXP:140428",
                    "AMZN,PO:FBA15H9RGK6C,FNSKU:X001UECQAX,QTY:1,EXP:140428",
                    "AMZN,PO:FBA15H9RGK6C,FNSKU:X001UECQAX,QTY:1,EXP:140428",
                    "AMZN,PO:FBA15H9RGK6C,FNSKU:X001UECQAX,QTY:1,EXP:140428",
                    "AMZN,PO:FBA15H9RGK6C,FNSKU:X001UECQAX,QTY:1,EXP:140428",
                    "AMZN,PO:FBA15H9RGK6C,FNSKU:X001UECQAX,QTY:1,EXP:140428",
                    "AMZN,PO:FBA15H9RGK6C,FNSKU:X001UECQAX,QTY:1,EXP:140428");

        BarcodeCreator barcodeCreator = new BarcodeCreator();
        List<BarcodePDF417> barcodePDF417List = barcodeCreator.createBarcodePDF417List(messages);

        List<Image> barcodePDF417ImagesList = new ArrayList<>();
        barcodePDF417List.forEach(barcodePDF417 ->
                barcodePDF417ImagesList.add(Utils.barcodeToImage(barcodePDF417)));

        PDFCreator pdfCreator = new PDFCreator();

        List<PdfPTable> tables = pdfCreator.addRowsToTable(barcodePDF417ImagesList);

        Document document = pdfCreator.createPDF(Utils.getProperties().getProperty("file.name"));
        document.open();

        for (PdfPTable table : tables) {
            try {
                document.add(table);
            } catch (DocumentException e) {
                e.printStackTrace();
                document.close();
            }
        }
        document.close();
    }

    private static void readMessagesFromUser() {
        List<String> messages = new ArrayList<>();

        System.out.println("Hello!\nNow You can generate barcode from string and save it to the PDF file");
        System.out.println("At now I ask You to enter string, that You want to convert to barcode");
        System.out.println("Please press \"Enter\" button when You finish");
        System.out.println("\n\n" +
                "If You want to test this application, enter word \"test\"");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line = null;
        do {
            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line.equals("test") && Main.messages == null) {
                return;
            }
            messages.add(line);
        } while (!line.equals(""));

        Main.messages = messages;
    }
}