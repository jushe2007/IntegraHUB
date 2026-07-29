package org.example.dao;

import org.example.Config.Conexion;
import org.example.modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO {

    // Extrae todos los datos de la tabla Cliente
    public ArrayList<Cliente> extraerCliente() {
        ArrayList<Cliente> clientesBD = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement stm = conexion.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();

              cliente.setId_Cliente(rs.getInt("Id_cliente"));
              cliente.setNombre(rs.getString("Nombre"));
              cliente.setDireccion(rs.getString("Direccion"));
              cliente.setTel1(rs.getString("Tel1"));
              cliente.setTel2(rs.getString("Tel2"));
              cliente.setId_Almacen3(rs.getInt("Id_almacen3"));

              clientesBD.add(cliente);

            }

        } catch (SQLException err) {
            System.err.println("Error al extraer los datos: " + err.getMessage());
        }

        return clientesBD;
    }

    // Borra un cliente por su ID
    public boolean borrarCliente(Cliente cliente) {
        boolean eliminado = false;
        String sql = "DELETE FROM `integraHUB`.`cliente` WHERE Id_cliente = ?";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement stm = conexion.prepareStatement(sql)) {

            stm.setInt(1, cliente.getId_Cliente());
            int filasAfectadas = stm.executeUpdate();

            if (filasAfectadas > 0) {
                eliminado = true;
            }

        } catch (SQLException err) {
            System.err.println("Error al borrar al cliente: " + err.getMessage());
        }

        return eliminado;
    }

    // Buscar clientes
    public ArrayList<Cliente> buscarCliente(Cliente clienteex) {
        ArrayList<Cliente> clientesBD = new ArrayList<Cliente>();
        String sql = "SELECT * FROM cliente WHERE Id_cliente = ?";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement stm = conexion.prepareStatement(sql)) {

            stm.setInt(1, clienteex.getId_Cliente());

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Cliente cliente = new Cliente();

                    cliente.setId_Cliente(rs.getInt("Id_cliente"));
                    cliente.setNombre(rs.getString("Nombre"));
                    cliente.setDireccion(rs.getString("Direccion"));
                    cliente.setTel1(rs.getString("Tel1"));
                    cliente.setTel2(rs.getString("Tel2"));
                    cliente.setId_Almacen3(rs.getInt("id_almacen3"));

                    clientesBD.add(cliente);
                }
            }

        } catch (SQLException err) {
            System.err.println("Error al buscar al cliente: " + err.getMessage());
        }

        return clientesBD;
    }
}