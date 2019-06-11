package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectBDD {
    // Attributs
    final String url = "jdbc:mysql://localhost:3306/DonjonDragon";
    final String user = "Matthias";
    final String passwd = "1948Ac@demY1948";
    protected Connection conn;

    // Constructors
    public ConnectBDD() {
        this.conn = connectionToBDD();
    }

    // Getters
    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPasswd() {
        return passwd;
    }

    // Methods
    public Connection connectionToBDD(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver O.K.");

            conn = DriverManager.getConnection(this.url, this.user, this.passwd);
            System.out.println("Connexion effective !");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
