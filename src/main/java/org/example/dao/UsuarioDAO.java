package org.example.dao;

import org.example.Config.Conexion;
import org.example.modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO {

    // Extrae todos los datos de la tabla Usuarios
    public ArrayList<Usuario> extraerUsuarios() {
        ArrayList<Usuario> usuariosBD = new ArrayList<Usuario>();
        String sql = "SELECT * FROM usuarios";
        try (Connection conexion = Conexion.conectar();
             PreparedStatement stm = conexion.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();

                usuario.setId_User(rs.getInt("Id_User"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setNivel_Pri(rs.getString("nivel_pri"));
                usuario.setId_Almacen2(rs.getInt("Id_almacen2"));
                usuario.setId_Empleado1(rs.getInt("Id_empleado1"));
                usuariosBD.add(usuario);
            }
        } catch (SQLException err) {
            System.err.println("Error al extraer los datos " + err.getMessage());
        }
        return usuariosBD;
    }

    // Borrar al usuario pidiendo el id
    public boolean borrarUsuario(Usuario usuario) {
        boolean eliminado = false;
        String sql = "DELETE FROM `integraHUB`.`usuarios` WHERE Id_User = ?";
        try (Connection conexion = Conexion.conectar();
             PreparedStatement stm = conexion.prepareStatement(sql)) {

            stm.setInt(1, usuario.getId_User());
            int filasAfectadas = stm.executeUpdate();

            if (filasAfectadas > 0) {
                eliminado = true;
            }

        } catch (SQLException err) {
            System.out.println("Error al borrar al usuario " + err.getMessage());
        }

        return eliminado;
    }

    // Buscar usuario por nombre o id
    public ArrayList<Usuario> buscarUsuario(Usuario usuarioex) {
        ArrayList<Usuario> usuariosBD = new ArrayList<Usuario>();
        String sql = "SELECT * FROM usuarios WHERE Id_User = ? OR usuario LIKE ?";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement stm = conexion.prepareStatement(sql)) {

            stm.setInt(1, usuarioex.getId_User());
            stm.setString(2, "%" + usuarioex.getUsuario() + "%");

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Usuario usuario = new Usuario();

                    usuario.setId_User(rs.getInt("Id_User"));
                    usuario.setUsuario(rs.getString("usuario"));
                    usuario.setContrasena(rs.getString("contrasena"));
                    usuario.setNivel_Pri(rs.getString("nivel_pri"));
                    usuario.setId_Almacen2(rs.getInt("Id_almacen2"));
                    usuario.setId_Empleado1(rs.getInt("Id_empleado1"));

                    usuariosBD.add(usuario);
                }
            }

        } catch (SQLException err) {
            System.err.println("Error al buscar el usuario " + err.getMessage());
        }

        return usuariosBD;
    }

    // Validar Usuario (Inicio de Sesión)
    public Usuario validarUsuario(String usuarioInput, String contrasenaInput) {
        Usuario usuarioValido = null;
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND contrasena = ?";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement stm = conexion.prepareStatement(sql)) {

            stm.setString(1, usuarioInput);
            stm.setString(2, contrasenaInput);

            try (ResultSet rs = stm.executeQuery()) {
                // Si rs.next() es true, significa que el usuario y la contraseña coinciden
                if (rs.next()) {
                    usuarioValido = new Usuario();
                    usuarioValido.setId_User(rs.getInt("Id_User"));
                    usuarioValido.setUsuario(rs.getString("usuario"));
                    usuarioValido.setContrasena(rs.getString("contrasena"));
                    usuarioValido.setNivel_Pri(rs.getString("nivel_pri"));
                    usuarioValido.setId_Almacen2(rs.getInt("Id_almacen2"));
                    usuarioValido.setId_Empleado1(rs.getInt("Id_empleado1"));
                }
            }

        } catch (SQLException err) {
            System.err.println("Error al validar el usuario: " + err.getMessage());
        }

        return usuarioValido; // Retorna el objeto Usuario si es correcto, o null si falló
    }
}