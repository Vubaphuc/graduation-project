package com.example.graduationprojectbe.service.auth;

import com.example.graduationprojectbe.entity.Bill;
import com.example.graduationprojectbe.entity.BillGuarantee;
import com.example.graduationprojectbe.entity.Receipt;
import com.example.graduationprojectbe.entity.ReceiptGuarantee;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
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
        Font titleFont = FontFactory.getFont("Arial Unicode MS", BaseFont.IDENTITY_H, 18, Font.BOLD, BaseColor.BLUE);
        Font headerFont = FontFactory.getFont("Arial Unicode MS", BaseFont.IDENTITY_H, 12, Font.BOLD);

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


    public byte[] convertBillGuaranteeToPdf(BillGuarantee bill) throws IOException, DocumentException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);

        document.open();

        // Set up custom fonts and colors
        Font titleFont = FontFactory.getFont("Times New Roman", BaseFont.IDENTITY_H, 18, Font.BOLD, BaseColor.BLUE);
        Font headerFont = FontFactory.getFont("Times New Roman", BaseFont.IDENTITY_H, 12, Font.BOLD);

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
        Paragraph customerName = new Paragraph("Customer Name: " + bill.getProductGuarantee().getCustomer().getFullName(), headerFont);
        Paragraph customerPhone = new Paragraph("Phone: " + bill.getProductGuarantee().getCustomer().getPhoneNumber(), headerFont);
        Paragraph customerEmail = new Paragraph("Email: " + bill.getProductGuarantee().getCustomer().getEmail(), headerFont);
        document.add(customerName);
        document.add(customerPhone);
        document.add(customerEmail);

        document.add(Chunk.NEWLINE);

        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

        // Product information
        Paragraph productName = new Paragraph("Model Name: " + bill.getProductGuarantee().getNameModel(), headerFont);
        Paragraph productIme = new Paragraph("IME: " + bill.getProductGuarantee().getIme(), headerFont);
        Paragraph productQuantity = new Paragraph("Quantity: 1", headerFont);
        Paragraph productPrice = new Paragraph("Price: " + format.format(bill.getProductGuarantee().getPrice()) + " VND", headerFont);
        document.add(productName);
        document.add(productIme);
        document.add(productQuantity);
        document.add(productPrice);

        // Calculate total
        double totalPrice = bill.getProductGuarantee().getPrice() * 1; // Assuming quantity is 1
        Paragraph total = new Paragraph("Total: " + format.format(totalPrice) + " VND", headerFont);
        document.add(total);

        document.close();

        return outputStream.toByteArray();
    }



    public byte[] convertReceiptToPdf(Receipt receipt) throws IOException, DocumentException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);

        document.open();

        // Set up custom fonts and colors
        Font titleFont = FontFactory.getFont("Arial Unicode MS", BaseFont.IDENTITY_H, 18, Font.BOLD, BaseColor.BLUE);
        Font headerFont = FontFactory.getFont("Arial Unicode MS", BaseFont.IDENTITY_H, 12, Font.BOLD);

        // Title
        Paragraph title = new Paragraph();
        title.setAlignment(Element.ALIGN_CENTER);

        LocalDate localDate = receipt.getCreateDate().toLocalDate();
        LocalDate payDate = receipt.getPayDate().toLocalDate();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Chunk receiptLabel = new Chunk("RECEIPT ", titleFont);
        Chunk receiptId = new Chunk(String.valueOf(receipt.getId()), titleFont);
        Chunk receiptDateChunk = new Chunk(dateTimeFormatter.format(localDate), titleFont);

        title.add(receiptLabel);
        title.add(Chunk.NEWLINE);
        title.add(receiptId);
        title.add(Chunk.NEWLINE);
        title.add(receiptDateChunk);

        document.add(title);
        document.add(Chunk.NEWLINE);

        // Invoice creator information
        Paragraph creatorCode = new Paragraph("Creator ID: " + receipt.getEmployeeCreate().getEmployeeCode(), headerFont);
        Paragraph creatorName = new Paragraph("Creator Name: " + receipt.getEmployeeCreate().getEmployeeName(), headerFont);
        document.add(creatorCode);
        document.add(creatorName);

        document.add(Chunk.NEWLINE);

        // Customer information
        Paragraph customerName = new Paragraph("Customer Name: " + receipt.getProduct().getCustomer().getFullName(), headerFont);
        Paragraph customerPhone = new Paragraph("Phone: " + receipt.getProduct().getCustomer().getPhoneNumber(), headerFont);
        Paragraph customerEmail = new Paragraph("Email: " + receipt.getProduct().getCustomer().getEmail(), headerFont);
        document.add(customerName);
        document.add(customerPhone);
        document.add(customerEmail);

        document.add(Chunk.NEWLINE);


        // Invoice creator information
        Paragraph createDateP = new Paragraph("Received Date: " + dateTimeFormatter.format(localDate), headerFont);
        Paragraph payDateP = new Paragraph("Payment Appointment Date: " + dateTimeFormatter.format(payDate), headerFont);
        document.add(createDateP);
        document.add(payDateP);

        document.add(Chunk.NEWLINE);


        // Product information
        Paragraph productName = new Paragraph("Model Name: " + receipt.getProduct().getNameModel(), headerFont);
        Paragraph productIme = new Paragraph("IME: " + receipt.getProduct().getIme(), headerFont);
        Paragraph productQuantity = new Paragraph("Quantity: " + receipt.getQuantity(), headerFont);
        document.add(productName);
        document.add(productIme);
        document.add(productQuantity);

        // Calculate total
        int totalQuantity = receipt.getQuantity(); // Assuming quantity is 1
        Paragraph total = new Paragraph("Total: " + totalQuantity + " EA", headerFont);
        document.add(total);

        document.close();

        return outputStream.toByteArray();
    }

    public byte[] convertReceiptGuaranteeToPdf(ReceiptGuarantee receiptGuarantee) throws IOException, DocumentException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);

        document.open();

        // Set up custom fonts and colors
        Font titleFont = FontFactory.getFont("Arial Unicode MS", BaseFont.IDENTITY_H, 18, Font.BOLD, BaseColor.BLUE);
        Font headerFont = FontFactory.getFont("Arial Unicode MS", BaseFont.IDENTITY_H, 12, Font.BOLD);

        // Title
        Paragraph title = new Paragraph();
        title.setAlignment(Element.ALIGN_CENTER);

        LocalDate localDate = receiptGuarantee.getCreateDate().toLocalDate();
        LocalDate payDate = receiptGuarantee.getPayDate().toLocalDate();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Chunk receiptLabel = new Chunk("RECEIPT ", titleFont);
        Chunk receiptId = new Chunk(String.valueOf(receiptGuarantee.getId()), titleFont);
        Chunk receiptDateChunk = new Chunk(dateTimeFormatter.format(localDate), titleFont);

        title.add(receiptLabel);
        title.add(Chunk.NEWLINE);
        title.add(receiptId);
        title.add(Chunk.NEWLINE);
        title.add(receiptDateChunk);

        document.add(title);
        document.add(Chunk.NEWLINE);

        // Invoice creator information
        Paragraph creatorCode = new Paragraph("Creator ID: " + receiptGuarantee.getEmployeeCreate().getEmployeeCode(), headerFont);
        Paragraph creatorName = new Paragraph("Creator Name: " + receiptGuarantee.getEmployeeCreate().getEmployeeName(), headerFont);
        document.add(creatorCode);
        document.add(creatorName);

        document.add(Chunk.NEWLINE);

        // Customer information
        Paragraph customerName = new Paragraph("Customer Name: " + receiptGuarantee.getProductGuarantee().getCustomer().getFullName(), headerFont);
        Paragraph customerPhone = new Paragraph("Phone: " + receiptGuarantee.getProductGuarantee().getCustomer().getPhoneNumber(), headerFont);
        Paragraph customerEmail = new Paragraph("Email: " + receiptGuarantee.getProductGuarantee().getCustomer().getEmail(), headerFont);
        document.add(customerName);
        document.add(customerPhone);
        document.add(customerEmail);

        document.add(Chunk.NEWLINE);


        // Invoice creator information
        Paragraph createDateP = new Paragraph("Received Date: " + dateTimeFormatter.format(localDate), headerFont);
        Paragraph payDateP = new Paragraph("Payment Appointment Date: " + dateTimeFormatter.format(payDate), headerFont);
        document.add(createDateP);
        document.add(payDateP);

        document.add(Chunk.NEWLINE);


        // Product information
        Paragraph productName = new Paragraph("Model Name: " + receiptGuarantee.getProductGuarantee().getNameModel(), headerFont);
        Paragraph productIme = new Paragraph("IME: " + receiptGuarantee.getProductGuarantee().getIme(), headerFont);
        Paragraph productQuantity = new Paragraph("Quantity: " + receiptGuarantee.getQuantity(), headerFont);
        document.add(productName);
        document.add(productIme);
        document.add(productQuantity);

        // Calculate total
        int totalQuantity = receiptGuarantee.getQuantity(); // Assuming quantity is 1
        Paragraph total = new Paragraph("Total: " + totalQuantity + " EA", headerFont);
        document.add(total);

        document.close();

        return outputStream.toByteArray();
    }

}
