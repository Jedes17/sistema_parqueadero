package modelo;

import conexion.Conexion;
import crud.CrudVehiculo;
import java.sql.*;

public class VehiculoDao implements CrudVehiculo<Vehiculo> {

    @Override
    public int AgregarVehiculoCliente(Vehiculo v) {
        int idVehiculoGenerado = -1;

        String sqlVehiculo = "INSERT INTO vehiculo (numero_placa, marca_vehiculo, modelo_vehiculo, tipo_vehiculo, color) VALUES (?, ?, ?, ?, ?)";
        String sqlRelacion = "INSERT INTO usuario_vehiculo (id_usuario, id_vehiculo, registrado_por) VALUES (?, ?, ?)";

        try (Connection con = Conexion.getInstance().getConnection()) {
            con.setAutoCommit(false);

            try (PreparedStatement psVehiculo = con.prepareStatement(sqlVehiculo, Statement.RETURN_GENERATED_KEYS)) {
                psVehiculo.setString(1, v.getNumeroPlaca());
                psVehiculo.setString(2, v.getMarcaVehiculo());
                psVehiculo.setString(3, v.getModeloVehiculo());
                psVehiculo.setString(4, v.getTipoVehiculo());
                psVehiculo.setString(5, v.getColor());
                psVehiculo.executeUpdate();

                ResultSet rs = psVehiculo.getGeneratedKeys();
                if (rs.next()) {
                    idVehiculoGenerado = rs.getInt(1);
                }
                rs.close();
            }

            try (PreparedStatement psRelacion = con.prepareStatement(sqlRelacion)) {
                psRelacion.setInt(1, v.getIdUsuario());
                psRelacion.setInt(2, idVehiculoGenerado);
                psRelacion.setInt(3, v.getRegistradoPor());
                psRelacion.executeUpdate();
            }

            con.commit();
            System.out.println("✅ Vehículo registrado exitosamente con ID: " + idVehiculoGenerado);

        } catch (SQLException e) {
            System.err.println("❌ Error al agregar vehículo: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("⚠️ Error general: " + e.getMessage());
        }

        return idVehiculoGenerado;
    }
}