package crud;

import java.util.List;

public interface CrudUsuario<T>{
    public List<T> IniciarSession();
    public List<T> ConsultarUsuarioConFiltro(String NombreCliente,String PlacaVehiculo,String CedulaCliente);
    public List<T> ConsultarUsuarioDetalladoConVehiculo();
}
