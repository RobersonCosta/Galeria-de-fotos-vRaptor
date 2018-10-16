package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import modelos.Usuario;

// para poder colocar o @Inject no Controller
@RequestScoped
public class UsuarioDAO {

// Consultas
    private PreparedStatement select;
    private PreparedStatement selectPorId;
    private PreparedStatement delete;
    private PreparedStatement update;
    private PreparedStatement insert;
    private PreparedStatement selectAutentica;

    // Conexao
    private ConexaoPostgreSQL con;

    public UsuarioDAO() throws SQLException {
        try {
            this.con = new ConexaoPostgreSQL(ConexaoPostgreSQL.HOST, ConexaoPostgreSQL.USER, ConexaoPostgreSQL.PASSWORD, ConexaoPostgreSQL.DATABASE);
        } catch (Exception ex) {
            System.out.println("Erro conexao!!");
        }
        this.initStatement();
    }

    private void initStatement() throws SQLException {
        this.selectPorId = con.getConnection().prepareStatement("select * from usuario where id = ?;");
        this.selectAutentica = con.getConnection().prepareStatement("select * from usuario where login = ? and senha = ?;");
        this.select = con.getConnection().prepareStatement("select * from usuario order by login;");
        this.delete = con.getConnection().prepareStatement("delete from usuario where id = ?");
        this.update = con.getConnection().prepareStatement("update usuario set login = ?, senha = ? where id = ?;");
        this.insert = con.getConnection().prepareStatement("insert into usuario (login, senha) values (?,?);");
    }

    public Usuario selectPorId(int id) throws SQLException {
        this.selectPorId.setInt(1, id);
        ResultSet rs = this.selectPorId.executeQuery();
        Usuario usuario = new Usuario();
        if (rs.next()) {
            usuario.setId(rs.getInt("id"));
            usuario.setLogin(rs.getString("login"));
            usuario.setSenha(rs.getString("senha"));
        }
        this.selectPorId.close();
        return usuario;
    }

    public boolean autentica(Usuario usuario) throws SQLException {
        this.selectAutentica.setString(1, usuario.getLogin());
        this.selectAutentica.setString(2, usuario.getSenha());
        ResultSet rs = this.selectAutentica.executeQuery();
        boolean resultado = false;
        if (rs.next()) {
            usuario.setId(rs.getInt("id"));
            resultado = true;
        }
        this.selectAutentica.close();
        return resultado;
    }

    public ArrayList<Usuario> select() throws SQLException {
        ArrayList<Usuario> todos = new ArrayList();
        ResultSet rs;
        try {
            rs = this.select.executeQuery();
            while (rs.next()) {
                todos.add(new Usuario(rs.getInt("id"), rs.getString("login"), rs.getString("senha")));
            }
            this.select.close();
        } catch (SQLException e) {
            return todos;
        }
        return todos;
    }

    public boolean insert(Usuario usuario) {
        try {
            this.insert.setString(1, usuario.getLogin());
            this.insert.setString(2, usuario.getSenha());
            this.insert.execute();
            this.insert.close();
            return true;
        } catch (Exception erro) {
            return false;
        }
    }

    public void delete(int id) throws SQLException {
        this.delete.setInt(1, id);
        this.delete.execute();
        this.delete.close();
    }

    public void deleteMassa(int id[]) {
        for (int i = 0; i < id.length; i++) {
            try {
                this.delete.setInt(1, id[i]);
                this.delete.execute();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            this.delete.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean update(Usuario usuario) {
        try {
            this.update.setString(1, usuario.getLogin());
            this.update.setString(2, usuario.getSenha());
            this.update.setInt(3, usuario.getId());
            this.update.execute();
            this.update.close();
            return true;
        } catch (Exception erro) {
            return false;
        }
    }
}
