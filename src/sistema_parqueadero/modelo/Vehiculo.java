package modelo;


public class Vehiculo {
    private int id;
    private String numeroPlaca;
    private String marcaVehiculo;
    private String tipoVehiculo;
    private String modeloVehiculo;
    private String color;
    private int idUsuario;
    private int registradoPor;
    
    public Vehiculo() {}

    public Vehiculo(int id, String numeroPlaca, String marcaVehiculo, String tipoVehiculo, String modeloVehiculo, String color, int idUsuario, int registradoPor) {
        this.id = id;
        this.numeroPlaca = numeroPlaca;
        this.marcaVehiculo = marcaVehiculo;
        this.tipoVehiculo = tipoVehiculo;
        this.modeloVehiculo = modeloVehiculo;
        this.color = color;
        this.idUsuario = idUsuario;
        this.registradoPor = registradoPor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public void setNumeroPlaca(String numeroPlaca) {
        this.numeroPlaca = numeroPlaca;
    }

    public String getMarcaVehiculo() {
        return marcaVehiculo;
    }

    public void setMarcaVehiculo(String marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getModeloVehiculo() {
        return modeloVehiculo;
    }

    public void setModeloVehiculo(String modeloVehiculo) {
        this.modeloVehiculo = modeloVehiculo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(int registradoPor) {
        this.registradoPor = registradoPor;
    }
}
