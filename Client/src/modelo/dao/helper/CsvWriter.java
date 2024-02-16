package modelo.dao.helper;

import helper.Table;
import modelo.CsvSerializable;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Clase auxiliar con distintas funcionalidades a la hora de trabajar con SQL
 * @author AGE
 * @version 2
 */
public class CsvWriter {

    public static void importCsv(Path path, Table tabla) throws Exception {
        System.out.println("TODO: Implement");
        /*
        switch (tabla) {
            case LIBROS -> writeCsv(path, new LibroDAOHibernate().leerAllLibros());
            case USUARIOS -> writeCsv(path, new UsuarioDAOHibernate().leerAllUsuarios());
            case PRESTAMOS -> writeCsv(path, new PrestamoDAOHibernate().leerAllPrestamos());
            case CATEGORIAS -> writeCsv(path, new CategoriaDAOHibernate().leerAllCategorias());
        }
         */
    }

    // List<? extends entidad> -> acepta una lista de cualquier tipo que extienda entidad
    // Lista de objetos que extienden entidad (Libro, Usuario, Categoria y Prestamo)
    // He añadido un método getCsv() a la clase Entidad
    private static void writeCsv(Path path, List<? extends CsvSerializable> entities) throws IOException {
        StringBuilder csv = new StringBuilder();
        if (!entities.isEmpty())
            csv.append(entities.get(0).getCsvHeader());

        for (CsvSerializable csvSerializable : entities) {
            csv.append(System.lineSeparator());
            csv.append(csvSerializable.getCsv());
        }

        Files.createDirectories(path.getParent()); // crea las carpetas si no existen :)
        Files.writeString(path, csv, StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
    }

}
