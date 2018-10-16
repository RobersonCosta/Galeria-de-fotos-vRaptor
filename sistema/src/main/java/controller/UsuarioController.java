package controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import database.UsuarioDAO;
import java.sql.SQLException;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import modelos.Usuario;
import sessao.UsuarioSessao;

@Controller
@Any
@Default
public class UsuarioController {

    @Inject
    private Validator validator;

    @Inject
    private Result result;

    @Inject
    private UsuarioSessao sessao;

    @Inject
    private UsuarioDAO usuarioDAO;

    @Path("/")
    public void index() {
    }


    public void inserir(@Valid @NotNull Usuario usuario) throws SQLException {
        validator.onErrorForwardTo(this).index();
        usuarioDAO.insert(usuario);
        result.redirectTo(this).index();
    }

    public void login(Usuario usuario) throws SQLException {
        boolean resultado = this.usuarioDAO.autentica(usuario);
        if (resultado) {
            this.sessao.setUser(usuario);
            this.result.redirectTo(GaleriaController.class).listar();
        } else {
            this.result.redirectTo(this).index();
        }
    }

    @Path({"/usuario/logout", "/usuario/logout/"})
    public void logout() {
        this.sessao.logout();
        this.result.redirectTo(this).index();

    }
}
