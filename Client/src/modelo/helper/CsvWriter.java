package modelo.helper;

import helper.Table;
import modelo.CsvSerializable;
import modelo.http.CategoriaRequests;
import modelo.http.LibroRequests;
import modelo.http.PrestamoRequests;
import modelo.http.UsuarioRequests;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class CsvWriter {

    public static void importCsv(Path path, Table tabla) throws Exception {
        switch (tabla) {
            case LIBROS -> writeCsv(path, new LibroRequests().getAll());
            case USUARIOS -> writeCsv(path, new UsuarioRequests().getAll());
            case PRESTAMOS -> writeCsv(path, new PrestamoRequests().getAll());
            case CATEGORIAS -> writeCsv(path, new CategoriaRequests().getAll());
        }
    }

    // List<? extends CsvSerializable> -> acepta una lista de cualquier tipo que implemente CsvSerializable
    // Lista de objetos que implementan CsvSerializable (Libro, Usuario, Categoria y Prestamo)
    // He añadido un método getCsv() a la interfaz CsvSerializable
    private static void writeCsv(Path path, List<? extends CsvSerializable> entities) throws IOException {
        StringBuilder csv = new StringBuilder();
        if (!entities.isEmpty())
            csv.append(entities.get(0).getCsvHeader());

        for (CsvSerializable csvSerializable : entities) {
            csv.append(System.lineSeparator());
            csv.append(csvSerializable.toCsv());
        }

        Files.createDirectories(path.getParent()); // crea las carpetas si no existen
        Files.writeString(path, csv, StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
    }

}
