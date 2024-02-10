package presentador;

import java.util.List;
import modelo.Prestamo;

public interface VistaPrestamos extends VistaPrestamo{
    void setPrestamos(List<Prestamo> listaPrestamos);
}
