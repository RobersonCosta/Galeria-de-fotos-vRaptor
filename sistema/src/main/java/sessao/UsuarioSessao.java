
package sessao;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import modelos.Usuario;

@SessionScoped
public class UsuarioSessao implements Serializable {

    private Usuario user;

    public UsuarioSessao() {
        
    
    }
    
    public Usuario getUsuario(){
        return user;
    }
    
    
    public void setUser(Usuario user){
        this.user =  user;
    }

    public boolean isLogged() {
        return user != null;
    }

    public void logout() {
        user = null;
    }
}