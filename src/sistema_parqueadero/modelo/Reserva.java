package modelo;

public class Reserva {
    private int id;
    private int idUsuario;
    private int idVehiculo;
    private int idLugar;
    private String fechaEntrada;
    private String horaEntrada;
    private String fechaSalida;
    private String horaSalida;
    private String estado;

    // Campos adicionales para mostrar información relacionada
    private String nombreUsuario;
    private String cedulaUsuario;
    private String placaVehiculo;
    private String tipoVehiculo;
    private String nombreCuadrante;
    private String numeroLugar;
    private boolean disponibilidad;
    public Reserva(){}

    public Reserva(int id, int idUsuario, int idVehiculo, int idLugar, String fechaEntrada, String horaEntrada, String fechaSalida, String horaSalida, String estado, String nombreUsuario, String cedulaUsuario, String placaVehiculo, String tipoVehiculo, String nombreCuadrante, String numeroLugar, boolean disponibilidad) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idVehiculo = idVehiculo;
        this.idLugar = idLugar;
        this.fechaEntrada = fechaEntrada;
        this.horaEntrada = horaEntrada;
        this.fechaSalida = fechaSalida;
        this.horaSalida = horaSalida;
        this.estado = estado;
        this.nombreUsuario = nombreUsuario;
        this.cedulaUsuario = cedulaUsuario;
        this.placaVehiculo = placaVehiculo;
        this.tipoVehiculo = tipoVehiculo;
        this.nombreCuadrante = nombreCuadrante;
        this.numeroLugar = numeroLugar;
        this.disponibilidad = disponibilidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(int idLugar) {
        this.idLugar = idLugar;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCedulaUsuario() {
        return cedulaUsuario;
    }

    public void setCedulaUsuario(String cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getNombreCuadrante() {
        return nombreCuadrante;
    }

    public void setNombreCuadrante(String nombreCuadrante) {
        this.nombreCuadrante = nombreCuadrante;
    }

    public String getNumeroLugar() {
        return numeroLugar;
    }

    public void setNumeroLugar(String numeroLugar) {
        this.numeroLugar = numeroLugar;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
}
