package crud;

import java.util.List;

public interface CrudReserva<T> {
    // Orden de parámetros corregido
    int SolicitarReserva(T tr);
    public List<T> FiltroSolicitudDeReserva(String TipoVehiculo,String PlacaVehiculo,String CedulaCliente);
    public List<T> ReservaDetallada();
}
