package com.bindeshwar.bindeshwarmart.invoice;

public class InvoiceNumber {
	private static int nextInvoiceNumber = 1000;

    public static String generateInvoiceNumber() {
        int invoiceNumber = nextInvoiceNumber++;
        String formattedInvoiceNumber = String.format("INV-%04d", new Object[] {invoiceNumber});
        return formattedInvoiceNumber;
    }
    
}
