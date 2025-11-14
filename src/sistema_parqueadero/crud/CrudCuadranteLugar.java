package crud;

import java.util.List;

public interface CrudCuadranteLugar<T> {
    List<T> listarCuadrantesYLugares();
    List<T> listarLugaresPorTipoVehiculo(String tipoVehiculo);
    T buscarPorNumero(String numero);
}
