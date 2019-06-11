package JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Hero {
    // Attributs
    protected int id;
    protected char type;
    protected String name;
    protected String image;
    protected int life;
    protected int attackLevel;
    protected String attackMode;
    protected String shield;

    // Conxtructors
    public Hero(){}

    public Hero(int id, char type, String name, String image, int life, int attackLevel, String attackMode, String shield) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.image = image;
        this.life = life;
        this.attackLevel = attackLevel;
        this.attackMode = attackMode;
        this.shield = shield;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getAttackLevel() {
        return attackLevel;
    }

    public void setAttackLevel(int attackLevel) {
        this.attackLevel = attackLevel;
    }

    public String getAttackMode() {
        return attackMode;
    }

    public void setAttackMode(String attackMode) {
        this.attackMode = attackMode;
    }

    public String getShield() {
        return shield;
    }

    public void setShield(String shield) {
        this.shield = shield;
    }

    // methods
    public void getHeroes() throws SQLException {
        Connection conn = new ConnectBDD().connectionToBDD();
        Statement state = conn.createStatement();
        ResultSet result = state.executeQuery("SELECT * from hero");

        ResultSetMetaData resultMeta = result.getMetaData();
        System.out.println("\n********************************************************************");
        for(int i = 1; i <= resultMeta.getColumnCount(); i++)
            System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");

        System.out.println("\n********************************************************************");
        while (result.next()){
            for(int i = 1; i <= resultMeta.getColumnCount(); i++)
                System.out.print("\t" + result.getObject(i).toString() + "\t |");
            System.out.println("\n********************************************************************");
        }
    }

    public void getHere(int id) throws SQLException {
        Connection conn = new ConnectBDD().connectionToBDD();
        Statement state = conn.createStatement();
        ResultSet result = state.executeQuery("SELECT * from hero WHERE id =" +id);
        System.out.println("Voici le détail du Hero n° : " +id);
        while (result.next()){
            System.out.println("Nom du hero : " +result.getString("nom"));
            if (result.getString("type").equals("G")){
                System.out.println("Type du hero : Guerrier");
            } else if (result.getString("type").equals("M")) {
                System.out.println("Type du hero : Magicien");
            }
            System.out.println("image du hero : " +result.getString("image"));
            System.out.println("la vie du hero : " +result.getInt("niveauVie"));
            System.out.println("la force du hero : " +result.getInt("niveauForce"));
            System.out.println("l'arme du hero : " +result.getString("moyenAttaque"));
            System.out.println("l'arme du hero : " +result.getString("bouclier"));
        }
    }
}
