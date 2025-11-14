package modelo;


public class Usuario {
    private int id;
    private String cedula;
    private String nombre;
    private String apellido;
    private String contrasena;
    private String telefono;
    private String correo;
    private String rol;
    private String estado;
    private int numeroVehiculos;
    private String tipoVehiculo;
    private String placaVehiculo;
    private String modeloVehiculo;
    private String colorVehiculo;
    private String marcaVehiculo;
    
    public Usuario(){}

    public Usuario(int id, String cedula, String nombre, String apellido, String contrasena, String telefono, String correo, String rol, String estado, int numeroVehiculos, String tipoVehiculo, String placaVehiculo, String modeloVehiculo, String colorVehiculo, String marcaVehiculo) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.correo = correo;
        this.rol = rol;
        this.estado = estado;
        this.numeroVehiculos = numeroVehiculos;
        this.tipoVehiculo = tipoVehiculo;
        this.placaVehiculo = placaVehiculo;
        this.modeloVehiculo = modeloVehiculo;
        this.colorVehiculo = colorVehiculo;
        this.marcaVehiculo = marcaVehiculo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getNumeroVehiculos() {
        return numeroVehiculos;
    }

    public void setNumeroVehiculos(int numeroVehiculos) {
        this.numeroVehiculos = numeroVehiculos;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getModeloVehiculo() {
        return modeloVehiculo;
    }

    public void setModeloVehiculo(String modeloVehiculo) {
        this.modeloVehiculo = modeloVehiculo;
    }

    public String getColorVehiculo() {
        return colorVehiculo;
    }

    public void setColorVehiculo(String colorVehiculo) {
        this.colorVehiculo = colorVehiculo;
    }

    public String getMarcaVehiculo() {
        return marcaVehiculo;
    }

    public void setMarcaVehiculo(String marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }
}
