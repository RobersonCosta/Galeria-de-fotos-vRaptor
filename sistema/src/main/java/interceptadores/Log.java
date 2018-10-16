package interceptadores;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AfterCall;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.BeforeCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import controller.UsuarioController;
import java.sql.SQLException;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import sessao.UsuarioSessao;

@Intercepts 
public class Log {
    @Inject
    private HttpServletRequest request;    
    
    @Inject
    private UsuarioSessao usuarioSessao; 
    
    @Inject
    private Result result; 
    
    @Accepts
    public boolean accept(ControllerMethod m) {
        return ! m.getController().getType().equals(UsuarioController.class);
    }

    @BeforeCall
    public void before() {
        if (!usuarioSessao.isLogged()){
          this.result.redirectTo(UsuarioController.class).index();
        }
    
    }
  
}