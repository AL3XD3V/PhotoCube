package ru.lexeder.Printers;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class DefaultPrint {

    public void printImage(String filePath) {
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        pras.add(new Copies(1));
        PrintService pss[] = PrintServiceLookup.lookupPrintServices(DocFlavor.INPUT_STREAM.PNG, pras);
        if (pss.length == 0)
            throw new RuntimeException("No printer services available.");
        System.out.print(Arrays.toString(pss));
        PrintService ps = pss[2];
        System.out.println("Printing to " + ps);
        DocPrintJob job = ps.createPrintJob();
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Doc doc = new SimpleDoc(fin, DocFlavor.INPUT_STREAM.PNG, null);
        try {
            job.print(doc, pras);
            fin.close();
        } catch (PrintException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}