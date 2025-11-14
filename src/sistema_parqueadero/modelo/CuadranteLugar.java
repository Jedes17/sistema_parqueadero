package modelo;

public class CuadranteLugar {
    private int id;             
    private String nombre;      
    private String tipoVehiculo;
    private int id_lugar;
    private String numero;
    private int idCuadrante;
    private boolean disponible;

    public CuadranteLugar() {}

    public CuadranteLugar(int id, String nombre, String tipoVehiculo, int id_lugar, String numero, int idCuadrante, boolean disponible) {
        this.id = id;
        this.nombre = nombre;
        this.tipoVehiculo = tipoVehiculo;
        this.id_lugar = id_lugar;
        this.numero = numero;
        this.idCuadrante = idCuadrante;
        this.disponible = disponible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public int getId_lugar() {
        return id_lugar;
    }

    public void setId_lugar(int id_lugar) {
        this.id_lugar = id_lugar;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getIdCuadrante() {
        return idCuadrante;
    }

    public void setIdCuadrante(int idCuadrante) {
        this.idCuadrante = idCuadrante;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    @Override
    public String toString() {
        return nombre + " - " + numero + " (" + tipoVehiculo + ") [" + (disponible ? "Disponible" : "Ocupado") + "]";
    }
}
