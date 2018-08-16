/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.bean.Usuario;
import modelo.conexao.FabricaConexao;

/**
 *
 * @author DTI
 */
public class UsuarioDAO {
    
    private PreparedStatement pstm;
    private ResultSet rs;
    private String sql;
    Connection conexao;

    public Usuario validarLogin(String login, String senha) throws SQLException {
        Usuario usuario = null;
        conexao = FabricaConexao.conexaoBanco();
        sql = "select * from usuario where usulogin=? and ususenha=?";
        pstm = conexao.prepareStatement(sql);
        pstm.setString(1, login);
        pstm.setString(2, senha);
        rs = pstm.executeQuery();
        if (rs.next()) {
            usuario = new Usuario();
            usuario.setLogin(rs.getString("usulogin"));
            usuario.setNome(rs.getString("usunome"));
            usuario.setNivel(rs.getString("usunivel"));
        }
        return usuario;
    }//fim validarLogin
}
