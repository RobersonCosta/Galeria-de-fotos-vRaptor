package database;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import modelos.Imagem;


// para poder colocar o @Inject no Controller
@RequestScoped
public class ImagemDAO {

// Consultas
    private PreparedStatement selectTodos;
    private PreparedStatement selectPorId;
    private PreparedStatement selectImagemPorIdGaleria;
    private PreparedStatement delete;
    private PreparedStatement update;
    private PreparedStatement insert;
    private String DIR_DOWNLOAD = "/home/aluno/Área de Trabalho/sistema/src/main/webapp/WEB-INF/arquivos/";
    
    // Conexao
    private ConexaoPostgreSQL con;

    public ImagemDAO() throws SQLException {
        try {
            this.con = new ConexaoPostgreSQL(ConexaoPostgreSQL.HOST, ConexaoPostgreSQL.USER, ConexaoPostgreSQL.PASSWORD, ConexaoPostgreSQL.DATABASE);
        } catch (Exception ex) {
            System.out.println("Erro conexao!");
        }
        this.initStatement();
    }

    private void initStatement() throws SQLException {
        this.selectImagemPorIdGaleria = con.getConnection().prepareStatement("select * from imagem inner join  galeria on (galeria.id =imagem.id_galeria) where galeria.id = ?;");
        this.selectPorId = con.getConnection().prepareStatement("select * from imagem where id = ?;");
        this.selectTodos = con.getConnection().prepareStatement("select * from imagem order by descricao;");
        this.delete = con.getConnection().prepareStatement("delete from imagem where id = ?");
        this.update = con.getConnection().prepareStatement("update imagem set id_galeria = ?, descricao = ?, arquivo = ? where id = ?;");
        this.insert = con.getConnection().prepareStatement("insert into imagem (id_galeria, descricao, arquivo) values (?, ?,?);");
    }

    public Imagem selectPorId(int id) throws SQLException {
        this.selectPorId.setInt(1, id);
        ResultSet rs = this.selectPorId.executeQuery();
        Imagem imagem = new Imagem();
        if (rs.next()) {
            imagem.setId(rs.getInt("id"));
            imagem.setGaleria(new GaleriaDAO().selectPorId(Integer.parseInt(rs.getString("id_galeria"))));
            imagem.setDescricao(rs.getString("descricao"));
            imagem.setArquivo(new File(DIR_DOWNLOAD+rs.getString("arquivo")));
        }
       // this.selectPorId.close();
        return imagem;
    }

    public ArrayList<Imagem> selectImagemPorIdGaleria(int id) throws SQLException {
        ArrayList<Imagem> todos = new ArrayList();
        ResultSet rs;
        try {
            this.selectImagemPorIdGaleria.setInt(1,id);
            rs = this.selectImagemPorIdGaleria.executeQuery();
            while (rs.next()) {
                todos.add(new Imagem(rs.getInt("id"),  rs.getString("descricao"), new File(DIR_DOWNLOAD+rs.getString("arquivo")), new GaleriaDAO().selectPorId(Integer.parseInt(rs.getString("id_galeria")))));
            }
            this.selectImagemPorIdGaleria.close();
        } catch (SQLException e) {
            return todos;
        }
        return todos;
    }

    public ArrayList<Imagem> selectTodos() throws SQLException {
        ArrayList<Imagem> todos = new ArrayList();
        ResultSet rs;
        try {
            rs = this.selectTodos.executeQuery();
            while (rs.next()) {
                todos.add(new Imagem(rs.getInt("id"),  rs.getString("descricao"), new File(DIR_DOWNLOAD+rs.getString("arquivo")), new GaleriaDAO().selectPorId(Integer.parseInt(rs.getString("id_galeria")))));
            }
            this.selectTodos.close();
        } catch (SQLException e) {
            return todos;
        }
        return todos;
    }

    public boolean insert(Imagem imagem) {
        try {
            this.insert.setInt(1, imagem.getGaleria().getId());
            this.insert.setString(2, imagem.getDescricao());        
            this.insert.setString(3, imagem.getArquivo().getName());
            this.insert.execute();
            this.insert.close();
            return true;
        } catch (Exception erro) {
            return false;
        }
    }

    public boolean delete(int id) throws SQLException {
        this.delete.setInt(1, id);
        int resultado = this.delete.executeUpdate();
        this.delete.close();
        return (resultado == 1);
    }

    public void deleteMassa(int id[]) {
        for (int i = 0; i < id.length; i++) {
            try {
                this.delete.setInt(1, id[i]);
                this.delete.execute();
            } catch (SQLException ex) {
                Logger.getLogger(ImagemDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            this.delete.close();
        } catch (SQLException ex) {
            Logger.getLogger(ImagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean update(Imagem imagem) {
        try {
            this.update.setInt(1, imagem.getGaleria().getId());
            this.update.setString(2, imagem.getDescricao());
            this.update.setString(3, imagem.getArquivo().getName());
            this.update.setInt(4, imagem.getId());
            this.update.execute();
            this.update.close();
            return true;
        } catch (Exception erro) {
            return false;
        }
    }
}
