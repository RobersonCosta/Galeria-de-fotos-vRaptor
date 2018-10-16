package database;

import java.sql.*;

public class ConexaoPostgreSQL extends Conexao {
    public static final String HOST = "localhost";
    public static final String USER = "postgres";
    public static final String PASSWORD = "postgres";
    public static final String DATABASE = "roberson";
  
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver postgreSQL nao foi carregado.");
        }
    }

    public ConexaoPostgreSQL(String host, String user, String password, String database) throws Exception {
        super(host, user, password, database);
        if ((host != null) && (host.length() > 0)) {
            connection = DriverManager.getConnection("jdbc:postgresql://" + host + "/" + database, this.username, this.password);
        } else {
            connection = DriverManager.getConnection("jdbc:postgresql:" + database, this.username, this.password);
        }
    }

    public ConexaoPostgreSQL(String host, String user, String password, String database, boolean atual) throws Exception {
        super(host, user, password, database);
        if ((host != null) && (host.length() > 0)) {
            connection = DriverManager.getConnection("jdbc:postgresql://" + host + "/" + database, this.username, this.password);
        } else {
            connection = DriverManager.getConnection("jdbc:postgresql:" + database, this.username, this.password);
        }
        if (atual) {
            conexao = this;
        }
    }

    @Override
    public void fechar() {
        if (connection != null) {
            try {
                connection.close();
                conexao = null;
            } catch (Exception ex) {
                System.err.println("Houve um erro ao tentar fechar a conexao com o banco.");
            }
        }
    }
}
