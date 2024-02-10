package presentador;

import java.util.List;
import modelo.Libro;

public interface VistaLibros extends VistaLibro{
    void setLibros(List<Libro> listaLibros);
}
