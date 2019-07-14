package com.novakovskiy.barcodepdfcreator;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.novakovskiy.barcodepdfcreator.utils.Utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

class PDFCreator {
    Document createPDF(String fileName) {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(Utils.getProperties().getProperty("pdf.file.location") + fileName));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        document.addAuthor(Utils.getProperties().getProperty("pdf.author"));
        document.addCreationDate();
        document.addProducer();
        document.addTitle(Utils.getProperties().getProperty("pdf.title"));
        document.setPageSize(PageSize.A4);
        return document;
    }

    List<PdfPTable> addRowsToTable(List<Image> code417ImageList) {

        int columnsCount = Integer.parseInt(Utils.getProperties().getProperty("columns.count"));
        int rowsCount = Integer.parseInt(Utils.getProperties().getProperty("rows.count")) * columnsCount;
        List<PdfPTable> tables = new ArrayList<>();

        PdfPTable table = createPDFTable(columnsCount);
        tables.add(table);

        int elementNumber = 0;
        int rowNumber = 0;

        for (Image image : code417ImageList) {
            PdfPCell imageCell = new PdfPCell(image);
            imageCell.setMinimumHeight(150);
            imageCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            imageCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

            if (elementNumber % columnsCount == 0)
                table.completeRow();

            table.addCell(imageCell);

            elementNumber++;
            rowNumber++;

            if (rowNumber == rowsCount) {
                table.completeRow();
                table = createPDFTable(columnsCount);
                tables.add(table);
                elementNumber = 0;
                rowNumber = 0;
            }
        }

        table.completeRow();
        return tables;
    }

    private PdfPTable createPDFTable(int columnsCount) {
        PdfPTable table = new PdfPTable(columnsCount);
        table.setSplitRows(true);
        return table;
    }
}
