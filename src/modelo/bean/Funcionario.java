/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.bean;

/**
 *
 * @author DTI
 */
public class Funcionario {

    private Integer matricula;
    private String nome;
    private String setor;
    private String cargo;
    private String lider;
    private byte[] imagem;
    private byte[] biometria;

    public String getLider() {
        return lider;
    }

    public void setLider(String lider) {
        this.lider = lider;
        
        
    }

    
    public byte[] getBiometria() {
        return biometria;
    }

    public void setBiometria(byte[] biometria) {
        this.biometria = biometria;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    /**
     * @return the matricula
     */
    public Integer getMatricula() {
        return matricula;
    }

    /**
     * @param matricula the matricula to set
     */
    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the setor
     */
    public String getSetor() {
        return setor;
    }

    /**
     * @param setor the setor to set
     */
    public void setSetor(String setor) {
        this.setor = setor;
    }

    /**
     * @return the cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
