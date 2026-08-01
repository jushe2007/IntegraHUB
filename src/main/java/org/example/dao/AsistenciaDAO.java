package org.example.dao;

import org.example.Config.Conexion;
import org.example.modelo.Asistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AsistenciaDAO {

    // Marcar Entrada (INSERT) usando la fecha y hora de MySQL
    public boolean marcarEntrada(int idEmpleado) {
        boolean insertado = false;
        String sql = "INSERT INTO asistencias (Fecha, horEntrada, Id_empleado2) VALUES (CURRENT_DATE(), CURRENT_TIME(), ?)";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement stm = conexion.prepareStatement(sql)) {

            stm.setInt(1, idEmpleado);

            int registrosAfectados = stm.executeUpdate();
            if (registrosAfectados > 0) {
                System.out.println("Entrada registrada correctamente");
                insertado = true;
            } else {
                System.out.println("No se pudo registrar la entrada");
            }
        } catch (SQLException err) {
            System.err.println("Error al marcar entrada: " + err.getMessage());
        }

        return insertado;
    }

    // Marcar Salida (UPDATE) calculando las horas con MySQL
    public boolean marcarSalida(int idEmpleado) {
        boolean actualizado = false;
        String sql = "UPDATE asistencias SET horSalida = CURRENT_TIME(), Totalhr = TIMEDIFF(CURRENT_TIME(), horEntrada) WHERE Id_empleado2 = ? AND Fecha = CURRENT_DATE()";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement stm = conexion.prepareStatement(sql)) {

            stm.setInt(1, idEmpleado);

            int registrosAfectados = stm.executeUpdate();
            if (registrosAfectados > 0) {
                System.out.println("Salida registrada correctamente");
                actualizado = true;
            } else {
                System.out.println("No se encontró entrada previa para el día de hoy");
            }
        } catch (SQLException err) {
            System.err.println("Error al marcar salida: " + err.getMessage());
        }

        return actualizado;
    }

    // Buscar asistencias por medio del ID de Asistencia
    public ArrayList<Asistencia> buscarAsistenciaPorId(int idAsistencia) {
        ArrayList<Asistencia> asistenciasBD = new ArrayList<>();
        String sql = "SELECT * FROM asistencias WHERE Id_asistencia = ?";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement stm = conexion.prepareStatement(sql)) {

            stm.setInt(1, idAsistencia);

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Asistencia asistencia = new Asistencia();

                    asistencia.setId_Asistencia(rs.getInt("Id_asistencia"));
                    asistencia.setFecha(rs.getString("Fecha"));
                    asistencia.setHoraEntrada(rs.getString("horEntrada"));
                    asistencia.setHoraSalida(rs.getString("horSalida"));
                    asistencia.setTotalHr(rs.getString("Totalhr"));
                    asistencia.setId_Empleado(rs.getInt("Id_empleado2"));

                    asistenciasBD.add(asistencia);
                }
            }
        } catch (SQLException err) {
            System.err.println("Error al buscar la asistencia por ID: " + err.getMessage());
        }

        return asistenciasBD;
    }

    // Buscar asistencias por medio de la Fecha
    public ArrayList<Asistencia> buscarAsistenciaPorFecha(String fecha) {
        ArrayList<Asistencia> asistenciasBD = new ArrayList<>();
        String sql = "SELECT * FROM asistencias WHERE Fecha = ?";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement stm = conexion.prepareStatement(sql)) {

            stm.setString(1, fecha);

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Asistencia asistencia = new Asistencia();

                    asistencia.setId_Asistencia(rs.getInt("Id_asistencia"));
                    asistencia.setFecha(rs.getString("Fecha"));
                    asistencia.setHoraEntrada(rs.getString("horEntrada"));
                    asistencia.setHoraSalida(rs.getString("horSalida"));
                    asistencia.setTotalHr(rs.getString("Totalhr"));
                    asistencia.setId_Empleado(rs.getInt("Id_empleado2"));

                    asistenciasBD.add(asistencia);
                }
            }
        } catch (SQLException err) {
            System.err.println("Error al buscar la asistencia por Fecha: " + err.getMessage());
        }

        return asistenciasBD;
    }

    // Eliminar un registro de asistencia
    public boolean eliminarAsistencia(Asistencia asistencia) {
        boolean eliminado = false;
        String sql = "DELETE FROM asistencias WHERE Id_asistencia = ?";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement stm = conexion.prepareStatement(sql)) {

            stm.setInt(1, asistencia.getId_Asistencia());
            int filasAfectadas = stm.executeUpdate();

            if (filasAfectadas > 0) {
                eliminado = true;
                System.out.println("Asistencia eliminada correctamente");
            }

        } catch (SQLException err) {
            System.out.println("Error al borrar la asistencia " + err.getMessage());
        }

        return eliminado;
    }

    // Modificar un registro de asistencia de forma manual
    public boolean modificarAsistencia(Asistencia asistencia) {
        boolean actualizado = false;
        String sql = "UPDATE asistencias SET Fecha = ?, horEntrada = ?, horSalida = ?, Totalhr = ?, Id_empleado2 = ? WHERE Id_asistencia = ?";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement stm = conexion.prepareStatement(sql)) {

            stm.setString(1, asistencia.getFecha());
            stm.setString(2, asistencia.getHoraEntrada());
            stm.setString(3, asistencia.getHoraSalida());
            stm.setString(4, asistencia.getTotalHr());
            stm.setInt(5, asistencia.getId_Empleado());
            stm.setInt(6, asistencia.getId_Asistencia());

            int registrosAfectados = stm.executeUpdate();
            if (registrosAfectados > 0) {
                System.out.println("Asistencia actualizada correctamente");
                actualizado = true;
            } else {
                System.out.println("No se encontró el código de la asistencia");
            }
        } catch (SQLException err) {
            System.out.println("Error al actualizar la asistencia " + err.getMessage());
        }

        return actualizado;
    }

    // Extrae todos los datos de la tabla Asistencias
    public ArrayList<Asistencia> extraerAsistencia() {
        ArrayList<Asistencia> asistenciasBD = new ArrayList<>();
        String sql = "SELECT * FROM asistencias";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement stm = conexion.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                Asistencia asistencia = new Asistencia();

                asistencia.setId_Asistencia(rs.getInt("Id_asistencia"));
                asistencia.setFecha(rs.getString("Fecha"));
                asistencia.setHoraEntrada(rs.getString("horEntrada"));
                asistencia.setHoraSalida(rs.getString("horSalida"));
                asistencia.setTotalHr(rs.getString("Totalhr"));
                asistencia.setId_Empleado(rs.getInt("Id_empleado2"));

                asistenciasBD.add(asistencia);
            }

        } catch (SQLException err) {
            System.err.println("Error al extraer los datos: " + err.getMessage());
        }

        return asistenciasBD;
    }
}