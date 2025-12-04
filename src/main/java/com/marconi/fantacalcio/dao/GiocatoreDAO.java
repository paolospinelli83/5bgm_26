package com.marconi.fantacalcio.dao;

/**
 *
 * @author 5BGM
 */
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.marconi.fantacalcio.model.Giocatore;

public class GiocatoreDAO {

    private Connection connection;

    public GiocatoreDAO() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/fantacalcio", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean connectToDB() throws SQLException{
        if (connection == null || !connection.isValid(3))
        {
            try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/fantacalcio", "root", "");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
    
    public boolean addGiocatore(Giocatore giocatore) throws SQLException {
        // Define the SQL statement
        String sql = "INSERT INTO Giocatori (nome, cognome, eta) VALUES (?, ?, ?)";

        connectToDB();
        // Set the parameters for the statement
        try ( // Create a statement
                PreparedStatement statement = connection.prepareStatement(sql)) {
            // Set the parameters for the statement
            statement.setString(1, giocatore.getNome());
            statement.setString(2, giocatore.getCognome());
            statement.setInt(3, Integer.valueOf(giocatore.getEta()));
            
            // Execute the statement
            int value = statement.executeUpdate();
            statement.close();
            
            return value==1;                     
        }
    }

    public boolean updategiocatore(Giocatore giocatore) throws SQLException {
        // Define the SQL statement
        String sql = "UPDATE docente SET cognome = ?, nome = ?, eta = ? WHERE cod_giocatore = ?";

        connectToDB();
        // Set the parameters for the statement
        //statement.setInt(1, giocatore.getId());
        try ( // Create a statement
            PreparedStatement statement = connection.prepareStatement(sql)) {
            // Set the parameters for the statement
            //statement.setInt(1, giocatore.getId());
            statement.setString(1, giocatore.getNome());
            statement.setString(2, giocatore.getCognome());
            statement.setInt(3, Integer.valueOf(giocatore.getEta()));
            statement.setInt(4, giocatore.getCod_giocatore());
            
            // Execute the statement
            int value = statement.executeUpdate();
            
            return value==1;   
        }
    }

    public boolean deleteGiocatore(int giocatoreId) throws SQLException {
        // Define the SQL statement
        String sql = "DELETE FROM Giocatori WHERE cod_giocatore = ?";
        PreparedStatement statement = null;

        connectToDB();
        // Create a statement
        statement = connection.prepareStatement(sql);

        // Set the parameters for the statement
        statement.setInt(1, giocatoreId);

        // Execute the statement
        statement.executeUpdate();
        int value = statement.executeUpdate();    
        statement.close();
        
        return value==1;   
    }
    
    public boolean deleteGiocatore(Giocatore giocatore) throws SQLException {
        // Define the SQL statement
        String sql = "DELETE FROM giocatori WHERE cod_giocatore = ?";
        PreparedStatement statement = null;

        connectToDB();
        // Create a statement
        statement = connection.prepareStatement(sql);

        // Set the parameters for the statement
        statement.setInt(1, giocatore.getCod_giocatore());

        // Execute the statement
        statement.executeUpdate();
        int value = statement.executeUpdate();    
        statement.close();
        
        return value==1;   
    }
    
    public List<Giocatore> searchGiocatoresByCognomeNome(Giocatore giocatore){
        List<Giocatore> giocatoreTable = new ArrayList<>();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Create the prepared statement
            String sql = "SELECT * FROM giocatori WHERE cognome = ? OR nome = ?";
            
            connectToDB();
            stmt = connection.prepareStatement(sql);

            // Set the values of the prepared statement
            stmt.setString(1, giocatore.getNome());
            stmt.setString(2, giocatore.getCognome());

            // Execute the query and retrieve the ResultSet
            rs = stmt.executeQuery();

            // Iterate through the ResultSet and retrieve the values
            while (rs.next()) {
                Giocatore giocatoreFromDB;
                giocatoreFromDB = new Giocatore();
                giocatoreFromDB.setCod_giocatore(rs.getInt("cod_giocatore"));
                giocatoreFromDB.setNome(rs.getString("nome"));
                giocatoreFromDB.setCognome(rs.getString("cognome"));
                giocatoreFromDB.setEta(rs.getInt("eta"));
                giocatoreTable.add(giocatoreFromDB);
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions
        } finally {
            // Close the ResultSet, PreparedStatement, and Connection objects
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    // Handle any SQL exceptions
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    // Handle any SQL exceptions
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // Handle any SQL exceptions
                }
            }
        }
        return giocatoreTable;
    }
    
    public Giocatore searchgiocatoresById(Giocatore giocatore){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Giocatore giocatoreFromDB = null;
        
        try {
            // Create the prepared statement
            String sql = "SELECT * FROM giocatori WHERE cod_giocatore = ?";
            
            connectToDB();
            stmt = connection.prepareStatement(sql);

            // Set the values of the prepared statement
            stmt.setInt(1, giocatore.getCod_giocatore());

            // Execute the query and retrieve the ResultSet
            rs = stmt.executeQuery();

            // Iterate through the ResultSet and retrieve the values
            while (rs.next()) {
                giocatoreFromDB = new Giocatore();
                giocatoreFromDB.setCod_giocatore(rs.getInt("cod_giocatore"));
                giocatoreFromDB.setNome(rs.getString("nome"));
                giocatoreFromDB.setCognome(rs.getString("cognome"));
                giocatoreFromDB.setEta(rs.getInt("eta"));
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions
        } finally {
            // Close the ResultSet, PreparedStatement, and Connection objects
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    // Handle any SQL exceptions
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    // Handle any SQL exceptions
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // Handle any SQL exceptions
                }
            }
        }
        return giocatoreFromDB;
    }

    
    public Giocatore searchgiocatoresById(int id){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Giocatore giocatoreFromDB = null;
        
        try {
            // Create the prepared statement
            String sql = "SELECT * FROM giocatori WHERE cod_giocatore = ?";
            
            connectToDB();
            stmt = connection.prepareStatement(sql);

            // Set the values of the prepared statement
            stmt.setInt(1, id);

            // Execute the query and retrieve the ResultSet
            rs = stmt.executeQuery();

            // Iterate through the ResultSet and retrieve the values
            while (rs.next()) {
                giocatoreFromDB = new Giocatore();
                giocatoreFromDB.setCod_giocatore(rs.getInt("cod_giocatore"));
                giocatoreFromDB.setNome(rs.getString("nome"));
                giocatoreFromDB.setCognome(rs.getString("cognome"));
                giocatoreFromDB.setEta(rs.getInt("eta"));
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions
        } finally {
            // Close the ResultSet, PreparedStatement, and Connection objects
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    // Handle any SQL exceptions
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    // Handle any SQL exceptions
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // Handle any SQL exceptions
                }
            }
        }
        return giocatoreFromDB;
    }
    
    
    public List<Giocatore> searchAllgiocatores() {
        List<Giocatore> giocatoreTable = new ArrayList<>();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Create the prepared statement
            String sql = "SELECT * FROM giocatori";
            
            connectToDB();
            stmt = connection.prepareStatement(sql);

            // Execute the query and retrieve the ResultSet
            rs = stmt.executeQuery();

            // Iterate through the ResultSet and retrieve the values
            while (rs.next()) {
                Giocatore giocatoreFromDB;
                giocatoreFromDB = new Giocatore();
                giocatoreFromDB.setCod_giocatore(rs.getInt("cod_giocatore"));
                giocatoreFromDB.setNome(rs.getString("nome"));
                giocatoreFromDB.setCognome(rs.getString("cognome"));
                giocatoreFromDB.setEta(rs.getInt("eta"));
                giocatoreTable.add(giocatoreFromDB);
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions
        } finally {
            // Close the ResultSet, PreparedStatement, and Connection objects
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    // Handle any SQL exceptions
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    // Handle any SQL exceptions
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // Handle any SQL exceptions
                }
            }
        }
        return giocatoreTable;
    }
    
    @Override
    protected void finalize(){
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(GiocatoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

