/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import br.com.cis.biox.sdk.CisBiox;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.bean.Funcionario;
import modelo.bean.Registro;
import modelo.conexao.FabricaConexao;

/**
 *
 * @author DTI
 */
public class FuncionarioDAO {

    private PreparedStatement pstm;
    private ResultSet rs;
    private String sql;
    Connection conexao;

    //metodo para salvar os dados do bairro
    public void salvar(Funcionario funcionario) throws SQLException {
        conexao = FabricaConexao.conexaoBanco();
        sql = "INSERT INTO funcionario VALUES(?,?,?,?,?,?,?)";
        pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, funcionario.getMatricula());
        pstm.setString(2, funcionario.getNome());
        pstm.setString(3, funcionario.getSetor());
        pstm.setString(4, funcionario.getCargo());
        pstm.setString(5, funcionario.getLider());
        pstm.setBytes(6, funcionario.getImagem());
        pstm.setBytes(7, funcionario.getBiometria());
        pstm.execute();
        FabricaConexao.fecharConexao();
    }//fim do metodo salvar

    public Funcionario consultarCodigo(int codigo) throws SQLException {
        Funcionario funcionario = null;
        conexao = FabricaConexao.conexaoBanco();
        sql = "select * from funcionario where funmatricula = ?";
        pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, codigo);
        rs = pstm.executeQuery();
        if (rs.next()) {
            funcionario = new Funcionario();
            funcionario.setMatricula(rs.getInt("funmatricula"));
            funcionario.setNome(rs.getString("funnome"));
            funcionario.setSetor(rs.getString("funsetor"));
            funcionario.setCargo(rs.getString("funcargo"));
            funcionario.setCargo(rs.getString("funlider"));
            funcionario.setImagem(rs.getBytes("funimagem"));
        }
        FabricaConexao.fecharConexao();
        return funcionario;
    }

    public Funcionario consultarBiometria(byte[] biometria) throws SQLException {
        Funcionario funcionario = null;
        conexao = FabricaConexao.conexaoBanco();
        sql = "select * from funcionario";
        CisBiox biox = new CisBiox();

        biox.iniciar();

        pstm = conexao.prepareStatement(sql);

        rs = pstm.executeQuery();
        while (rs.next()) {
            int retorno = 0;
            if (rs.getBytes("funbiometria") != null) {

                funcionario = new Funcionario();
                int iRetorno = biox.compararDigital(biometria, rs.getBytes("funbiometria"));

                switch (iRetorno) {
                    case 1:
                        funcionario.setMatricula(rs.getInt("funmatricula"));
                        funcionario.setNome(rs.getString("funnome"));
                        funcionario.setSetor(rs.getString("funsetor"));
                        funcionario.setCargo(rs.getString("funcargo"));
                        funcionario.setLider(rs.getString("funlider"));
                        funcionario.setImagem(rs.getBytes("funimagem"));

                        retorno = 1;
                        break;
                    case -2:
//                    JOptionPane.showMessageDialog(null, "Digitais são diferentes!");
                        break;
                    default:
                        // JOptionPane.showMessageDialog(null, "Erro: " + CisBiox.mensagens(iRetorno));
                        break;
                }

            }
            if(retorno == 1) {
                break;
            }

        }
        biox.finalizar();
        FabricaConexao.fecharConexao();
        return funcionario;
    }
    
    public List<Funcionario> listarTodos() throws SQLException {
        conexao = FabricaConexao.conexaoBanco();
        sql = "SELECT * FROM funcionario";
        pstm = conexao.prepareStatement(sql);
        rs = pstm.executeQuery();

        List<Funcionario> minhaLista = new ArrayList<>();

        while (rs.next()) {
            
            Funcionario funcionario = new Funcionario();
            funcionario.setMatricula(rs.getInt("funmatricula"));
            funcionario.setNome(rs.getString("funnome"));
            funcionario.setSetor(rs.getString("funsetor"));
            funcionario.setCargo(rs.getString("funcargo"));
            funcionario.setCargo(rs.getString("funlider"));
            
            minhaLista.add(funcionario);

        }
        FabricaConexao.fecharConexao();
        return minhaLista;
    }
}
