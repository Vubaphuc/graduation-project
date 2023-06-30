package com.example.graduationprojectbe.service.auth;

import com.example.graduationprojectbe.entity.Bill;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class PDFService {

    public byte[] convertBillToPdf(Bill bill) throws IOException, DocumentException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);

        document.open();

        // Set up custom fonts and colors
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLUE);
        Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        Font contentFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);

        // Title
        Paragraph title = new Paragraph();
        title.setAlignment(Element.ALIGN_CENTER);

        LocalDate localDate = bill.getInvoiceCreationDate().toLocalDate();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Chunk invoiceLabel = new Chunk("Invoice ", titleFont);
        Chunk invoiceId = new Chunk(String.valueOf(bill.getId()), titleFont);
        Chunk invoiceDateChunk = new Chunk(dateTimeFormatter.format(localDate), titleFont);

        title.add(invoiceLabel);
        title.add(Chunk.NEWLINE);
        title.add(invoiceId);
        title.add(Chunk.NEWLINE);
        title.add(invoiceDateChunk);

        document.add(title);
        document.add(Chunk.NEWLINE);

        // Invoice creator information
        Paragraph creatorCode = new Paragraph("Creator ID: " + bill.getInvoiceCreator().getEmployeeCode(), headerFont);
        Paragraph creatorName = new Paragraph("Creator Name: " + bill.getInvoiceCreator().getEmployeeName(), headerFont);
        document.add(creatorCode);
        document.add(creatorName);

        document.add(Chunk.NEWLINE);

        // Customer information
        Paragraph customerName = new Paragraph("Customer Name: " + bill.getProduct().getCustomer().getFullName(), headerFont);
        Paragraph customerPhone = new Paragraph("Phone: " + bill.getProduct().getCustomer().getPhoneNumber(), headerFont);
        Paragraph customerEmail = new Paragraph("Email: " + bill.getProduct().getCustomer().getEmail(), headerFont);
        document.add(customerName);
        document.add(customerPhone);
        document.add(customerEmail);

        document.add(Chunk.NEWLINE);

        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

        // Product information
        Paragraph productName = new Paragraph("Model Name: " + bill.getProduct().getNameModel(), headerFont);
        Paragraph productIme = new Paragraph("IME: " + bill.getProduct().getIme(), headerFont);
        Paragraph productQuantity = new Paragraph("Quantity: 1", headerFont);
        Paragraph productPrice = new Paragraph("Price: " + format.format(bill.getProduct().getPrice()) + " VND", headerFont);
        document.add(productName);
        document.add(productIme);
        document.add(productQuantity);
        document.add(productPrice);

        // Calculate total
        double totalPrice = bill.getProduct().getPrice() * 1; // Assuming quantity is 1
        Paragraph total = new Paragraph("Total: " + format.format(totalPrice) + " VND", headerFont);
        document.add(total);

        document.close();

        return outputStream.toByteArray();
    }


    public byte[] convertBillGuaranteeToPdf(Bill bill) throws IOException, DocumentException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);

        document.open();

        // Set up custom fonts and colors
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLUE);
        Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        Font contentFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);

        // Title
        Paragraph title = new Paragraph();
        title.setAlignment(Element.ALIGN_CENTER);

        LocalDate localDate = bill.getInvoiceCreationDate().toLocalDate();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Chunk invoiceLabel = new Chunk("Invoice ", titleFont);
        Chunk invoiceId = new Chunk(String.valueOf(bill.getId()), titleFont);
        Chunk invoiceDateChunk = new Chunk(dateTimeFormatter.format(localDate), titleFont);

        title.add(invoiceLabel);
        title.add(Chunk.NEWLINE);
        title.add(invoiceId);
        title.add(Chunk.NEWLINE);
        title.add(invoiceDateChunk);

        document.add(title);
        document.add(Chunk.NEWLINE);

        // Invoice creator information
        Paragraph creatorCode = new Paragraph("Creator ID: " + bill.getInvoiceCreator().getEmployeeCode(), headerFont);
        Paragraph creatorName = new Paragraph("Creator Name: " + bill.getInvoiceCreator().getEmployeeName(), headerFont);
        document.add(creatorCode);
        document.add(creatorName);

        document.add(Chunk.NEWLINE);

        // Customer information
        Paragraph customerName = new Paragraph("Customer Name: " + bill.getProduct().getCustomer().getFullName(), headerFont);
        Paragraph customerPhone = new Paragraph("Phone: " + bill.getProduct().getCustomer().getPhoneNumber(), headerFont);
        Paragraph customerEmail = new Paragraph("Email: " + bill.getProduct().getCustomer().getEmail(), headerFont);
        document.add(customerName);
        document.add(customerPhone);
        document.add(customerEmail);

        document.add(Chunk.NEWLINE);

        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

        // Product information
        Paragraph productName = new Paragraph("Model Name: " + bill.getProduct().getNameModel(), headerFont);
        Paragraph productIme = new Paragraph("IME: " + bill.getProduct().getIme(), headerFont);
        Paragraph productQuantity = new Paragraph("Quantity: 1", headerFont);
        Paragraph productPrice = new Paragraph("Price: " + format.format(bill.getProduct().getPrice()) + " VND", headerFont);
        document.add(productName);
        document.add(productIme);
        document.add(productQuantity);
        document.add(productPrice);

        // Calculate total
        double totalPrice = bill.getProduct().getPrice() * 1; // Assuming quantity is 1
        Paragraph total = new Paragraph("Total: " + format.format(totalPrice) + " VND", headerFont);
        document.add(total);

        document.close();

        return outputStream.toByteArray();
    }

}
