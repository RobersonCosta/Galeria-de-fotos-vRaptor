/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author iapereira
 */
public class Usuario implements Serializable {
    
    private int id;
    
    @NotNull(message = "{usuario.login.vazio}")
    private String login;
    
    @NotNull(message = "{usuario.senha.vazio}")
    private String senha;

    public Usuario() {
    }

    public Usuario(int id, String login, String senha) {
        this.id = id;
        this.login = login;
        this.senha = senha;
    }

 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
    
    
}
