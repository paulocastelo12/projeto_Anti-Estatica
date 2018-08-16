/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.bean.Funcionario;
import modelo.bean.Registro;
import modelo.conexao.FabricaConexao;

/**
 *
 * @author DTI
 */
public class RegistroDAO {

    private PreparedStatement pstm;
    private ResultSet rs;
    private String sql;
    Connection conexao;

    //metodo para salvar os dados do bairro
    public void salvar(Registro registro) throws SQLException {
        conexao = FabricaConexao.conexaoBanco();
        sql = "INSERT INTO registro VALUES(null,?,?,?,?)";
        pstm = conexao.prepareStatement(sql);
        pstm.setString(1, registro.getData());
        pstm.setString(2, registro.getHora());
        pstm.setInt(3, registro.getFuncionario().getMatricula());
        pstm.setString(4, registro.getStatus());
        pstm.execute();
        FabricaConexao.fecharConexao();
    }//fim do metodo salvar

    public List<Registro> listarTodos() throws SQLException {
        conexao = FabricaConexao.conexaoBanco();
        sql = "SELECT * FROM registro inner join funcionario on funmatricula = regfunmatricula order by regcod";
        pstm = conexao.prepareStatement(sql);
        rs = pstm.executeQuery();

        List<Registro> minhaLista = new ArrayList<>();

        while (rs.next()) {
            Registro registro = new Registro();
            registro.setCodigo(rs.getInt("regcod"));
            registro.setData(rs.getString("regdata"));
            registro.setHora(rs.getString("reghora"));
            registro.setStatus(rs.getString("regstatus"));

            Funcionario funcionario = new Funcionario();
            funcionario.setMatricula(rs.getInt("funmatricula"));
            funcionario.setNome(rs.getString("funnome"));
            funcionario.setSetor(rs.getString("funsetor"));
            funcionario.setCargo(rs.getString("funcargo"));

            registro.setFuncionario(funcionario);

            minhaLista.add(registro);

        }
        FabricaConexao.fecharConexao();
        return minhaLista;
    }
    
    public List<Registro> listaPorData(String data, String setor) throws SQLException {
        conexao = FabricaConexao.conexaoBanco();
        sql = "SELECT * FROM registro inner join funcionario on funmatricula = regfunmatricula where regdata like '"+data+"%' and funsetor like '"+setor+"%' order by funnome ";
        pstm = conexao.prepareStatement(sql);
        rs = pstm.executeQuery();

        List<Registro> minhaLista = new ArrayList<>();

        while (rs.next()) {
            Registro registro = new Registro();
            registro.setCodigo(rs.getInt("regcod"));
            registro.setData(rs.getString("regdata"));
            registro.setHora(rs.getString("reghora"));
            registro.setStatus(rs.getString("regstatus"));

            Funcionario funcionario = new Funcionario();
            funcionario.setMatricula(rs.getInt("funmatricula"));
            funcionario.setNome(rs.getString("funnome"));
            funcionario.setSetor(rs.getString("funsetor"));
            funcionario.setCargo(rs.getString("funcargo"));

            registro.setFuncionario(funcionario);

            minhaLista.add(registro);

        }
        FabricaConexao.fecharConexao();
        return minhaLista;
    }
}
