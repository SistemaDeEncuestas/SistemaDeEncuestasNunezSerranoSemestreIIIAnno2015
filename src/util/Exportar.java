/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import domain.Encuesta;
import domain.Pregunta;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *
 * @author Daniel
 */

public class Exportar {

    public boolean exportarAPDF(Encuesta encuesta) {

        try {
            File file = new File(encuesta.getNombreArchivo() + ".pdf");
            FileOutputStream fileout = new FileOutputStream(file);

            Document document = new Document();
            PdfWriter.getInstance(document, fileout);
            document.addAuthor(encuesta.getNickname());
            document.addTitle(encuesta.getTitulo());

            document.open();

            Paragraph titulo = new Paragraph();
            Font font = new Font();
            font.setStyle(Font.BOLD);
            titulo.setFont(font);
            titulo.add(encuesta.getTitulo());
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.add(" ");
            document.add(titulo);

            Paragraph descripcion = new Paragraph();
            descripcion.add(encuesta.getDescripcion());
            descripcion.add(" ");
            document.add(descripcion);

            for (int i = 0; i < encuesta.getPreguntas().size(); i++) {
                Pregunta preguntaActual = encuesta.getPreguntas().get(i);
                Paragraph pregunta = new Paragraph();
                pregunta.add(preguntaActual.getEnunciado());
                List listaRespuesta = new List();
                if (preguntaActual.getTipo().equals(Strings.TIPO_ABIERTA)) {
                    listaRespuesta.add("________________________________________________________________________");
                    listaRespuesta.add("________________________________________________________________________");
                    listaRespuesta.add("________________________________________________________________________");
                    listaRespuesta.add("________________________________________________________________________");
                    listaRespuesta.add("________________________________________________________________________");
                } else {
                    for (int j = 0; j < preguntaActual.getListaRespuestas().size(); j++) {
                        String respuesta = preguntaActual.getListaRespuestas().get(j);
                        listaRespuesta.add("\t" + respuesta + " ( )");

                    }
                }
                pregunta.add(" ");
                pregunta.add(listaRespuesta);
                pregunta.add(" ");
                document.add(pregunta);

            }

            document.close();
            return true;

        } catch (FileNotFoundException | DocumentException e) {
        }

        return false;
    }

}
