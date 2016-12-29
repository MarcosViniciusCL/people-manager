/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people.manager.tools;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import people.manager.controller.Controller;
import people.manager.controller.ControllerVenda;
import people.manager.model.Venda;

/**
 *
 * @author marcos
 */
public class PDF {

    public static void criarPDFVendas() {
        // criação do documento
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("./PDF_Vendas.pdf"));
            document.open();
            // adicionando um parágrafo no documento
            document.add(new Paragraph("Relatório de Vendas"));
            document.add(new Paragraph(" "));
            document.add(new LineSeparator());
            PdfPTable table = new PdfPTable(5);
            Paragraph p1 = new Paragraph("Vendas Realizadas");
            PdfPCell header = new PdfPCell(p1);
            header.setColspan(5);
            table.addCell(header);
            ArrayList<Venda> vendas = ControllerVenda.todasVendas();
            Double valorTotal = 0.0;
            table.addCell("DATA");
            table.addCell("ID CLIENTE");
            table.addCell("ID VENDEDO");
            table.addCell("PRODUTOS");
            table.addCell("VALOR");
            for (Venda venda : vendas) {
                table.addCell(Controller.calendarParaString(venda.getData()));
                table.addCell(venda.getIdCliente()+"");
                table.addCell(venda.getIdVendedor()+"");
                table.addCell(venda.getNomesProdutos());
                table.addCell(String.format("R$ %.2f", venda.getValorVenda()));
                valorTotal += venda.getValorVenda();
            }

            table.addCell("TOTAL");
            table.addCell("");
            table.addCell("");
            table.addCell("");
            table.addCell(String.format("R$ %.2f", valorTotal));
            
            document.add(table);
            
            document.close();
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
