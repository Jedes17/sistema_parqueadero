package crud;

import java.util.List;
import modelo.EstadoVehicular;

public interface CrudEstadoVehicular {

    // ➕ Registrar entrada de vehículo
    boolean registrarEntrada(EstadoVehicular estadoVehicular);

    // ➕ Registrar salida de vehículo
    boolean registrarSalida(EstadoVehicular estadoVehicular);

    // Consultar con filtros (Tipo, Placa, Cliente, Operador)
    List<EstadoVehicular> consultarConFiltros(String tipoVehiculo, String placa, String cedulaCliente, String cedulaOperador);

    //Consultar solo activas (Tipo, Placa, Cliente)
    List<EstadoVehicular> consultarActivas(String tipoVehiculo, String placa, String cedulaCliente);

    // Obtener observaciones de entrada por placa
    String obtenerObservacionEntrada(String placa);

    // Obtener detalle completo por placa
    EstadoVehicular obtenerDetallePorPlaca(String placa);
}
