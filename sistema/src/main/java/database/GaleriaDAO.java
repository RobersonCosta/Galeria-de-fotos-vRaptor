package database;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import modelos.Galeria;
import modelos.Imagem;

// para poder colocar o @Inject no Controller
@RequestScoped 
public class GaleriaDAO {
    
// Consultas
    private PreparedStatement selectTodos;    
    private PreparedStatement selectPorId;
    private PreparedStatement selectPorIdUsuario;
    private PreparedStatement delete;
    private PreparedStatement update;
    private PreparedStatement insert;
    
        

   
    // Conexao
    private ConexaoPostgreSQL con;

    public GaleriaDAO() throws SQLException {
        try {
            this.con = new ConexaoPostgreSQL(ConexaoPostgreSQL.HOST, ConexaoPostgreSQL.USER, ConexaoPostgreSQL.PASSWORD, ConexaoPostgreSQL.DATABASE);
        } catch (Exception ex) {
            System.out.println("Erro conexao!");
        }
        this.initStatement();
    }

    private void initStatement() throws SQLException {
        this.selectPorId = con.getConnection().prepareStatement("select * from galeria where id = ?;");
        this.selectTodos = con.getConnection().prepareStatement("select * from galeria order by titulo;");
        this.selectPorIdUsuario = con.getConnection().prepareStatement("select * from galeria where galeria.id_usuario = ? order by titulo;");
        this.delete = con.getConnection().prepareStatement("delete from galeria where id = ?");
        this.update = con.getConnection().prepareStatement("update galeria set id_usuario = ?,titulo = ?, descricao = ? where id = ?;");
        this.insert = con.getConnection().prepareStatement("insert into galeria (id_usuario, titulo, descricao) values (?,?,?) RETURNING id;");
    }
    
    public ArrayList<Galeria> selectPorIdUsuario (int id){
        ArrayList<Galeria> todos = new ArrayList();
        ResultSet rs;
        try {
            this.selectPorIdUsuario.setInt(1, id);
            rs = this.selectPorIdUsuario.executeQuery();
            while (rs.next()) {
                todos.add(new Galeria(rs.getInt("id"), rs.getString("titulo"), rs.getString("descricao"), new UsuarioDAO().selectPorId(Integer.parseInt(rs.getString("id_usuario")))));
            }
            this.selectPorIdUsuario.close();
        } catch (SQLException e) {
            return todos;
        }
        return todos;
    }
    
    public Galeria selectPorId(int id) throws SQLException{
         this.selectPorId.setInt(1, id);
         ResultSet rs = this.selectPorId.executeQuery();
         Galeria galeria = new Galeria();
         if (rs.next()){
             galeria.setId(rs.getInt("id"));
             galeria.setUsuario(new UsuarioDAO().selectPorId(Integer.parseInt(rs.getString("id_usuario"))));
             galeria.setTitulo(rs.getString("titulo"));
             galeria.setDescricao(rs.getString("descricao"));
         }
        this.selectPorId.close();
        return galeria;
    }

    public ArrayList<Galeria> selectTodos()  {
        ArrayList<Galeria> todos = new ArrayList();
        ResultSet rs;
        try {
            rs = this.selectTodos.executeQuery();
            while (rs.next()) {
                todos.add(new Galeria(rs.getInt("id"), rs.getString("titulo"), rs.getString("descricao"), new UsuarioDAO().selectPorId(Integer.parseInt(rs.getString("id_usuario")))));
            }
            //this.selectTodos.close();
        } catch (SQLException e) {
            return todos;  
        }
        return todos;
    }

    public int insert(Galeria galeria) throws SQLException {
            this.insert.setInt(1, galeria.getUsuario().getId());
            this.insert.setString(2, galeria.getTitulo());
            this.insert.setString(3, galeria.getDescricao());
            ResultSet rs = this.insert.executeQuery();
            int id  = -1;
            if (rs.next()){
                id = rs.getInt("id");
            }
            this.insert.close();            
            //rs.close();
            return id;
    }

    public void delete(int id) throws SQLException {
        this.delete.setInt(1, id);
        this.delete.execute();
        this.delete.close();
    }
    
    public void deleteTodosSelecionados(int id[]){
       for (int i = 0; i < id.length; i++){ 
           try {
               this.delete.setInt(1, id[i]);
               this.delete.execute();             
           } catch (SQLException ex) {
               Logger.getLogger(GaleriaDAO.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       try {
           this.delete.close();
       } catch (SQLException ex) {
           Logger.getLogger(GaleriaDAO.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    public boolean update(Galeria galeria) {
          try {
            this.update.setInt(1, galeria.getUsuario().getId());
            this.update.setString(2, galeria.getTitulo());
            this.update.setString(3, galeria.getDescricao());
            this.update.setInt(4, galeria.getId());
            this.update.execute();
            this.update.close();
            return true;
        } catch (Exception erro) {
            return false;
        }
    }
}
