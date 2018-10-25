package ru.lexeder.Printers;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class DefaultPrint {

    public void printImage(String filePath) {
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        pras.add(new Copies(1));
        pras.add(MediaSizeName.ISO_A6);
        pras.add(OrientationRequested.LANDSCAPE);

        PrintService pss = PrintServiceLookup.lookupDefaultPrintService();

        System.out.println("Printing to " + pss);
        DocPrintJob job = pss.createPrintJob();
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