package crud;

import java.util.List;

public interface CrudFactura<T> {
    // Orden de parámetros corregido
    public List<T> FacturaConFiltro(String CedulaCliente, String TipoVehiculo, String PlacaVehiculo);
    public List<T> FacturaDetallado();
}
