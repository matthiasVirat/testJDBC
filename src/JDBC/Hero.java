package JDBC;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hero {
    // Attributs
    protected int id;
    protected String type;
    protected String name;
    protected String image;
    protected int life;
    protected int attackLevel;
    protected String attackMode;
    protected String shield;

    // Conxtructors
    public Hero(){}

    public Hero(int id){
        this.id = id;
    }

    public Hero(int id, String type, String name, String image, int life, int attackLevel, String attackMode, String shield) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public ResultSet getHero(int id) throws SQLException {
        Connection conn = new ConnectBDD().connectionToBDD();
        String query = "SELECT * from hero WHERE id = ?";
        Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        PreparedStatement prepare = conn.prepareStatement(query);
        prepare.setInt(1, id);

        ResultSet result = prepare.executeQuery();

        return result;
    }

    public void displayHero(ResultSet result) throws SQLException {
        while (result.next()){
            this.setId(result.getInt("id"));
            this.setName(result.getString("nom"));
            if (result.getString("type").equals("G")){
                this.setType(result.getString("type"));
            } else if (result.getString("type").equals("M")) {
                this.setType(result.getString("type"));
            }
            this.setImage(result.getString("image"));
            this.setLife(result.getInt("niveauVie"));
            this.setAttackLevel(result.getInt("niveauForce"));
            this.setAttackMode(result.getString("moyenAttaque"));
            this.setShield(result.getString("bouclier"));
        }
        
        System.out.println("Voici le détail du Hero n° : " +this.id);
        System.out.println("Nom du hero : " + this.name);
        if (this.type.equals("G")){
            System.out.println("Type du hero : Guerrier");
        } else if (this.type.equals("M")){
            System.out.println("Type du hero : Magicien");
        }
        System.out.println("image du hero : " +this.image);
        System.out.println("la vie du hero : " +this.life);
        System.out.println("la force du hero : " +this.attackLevel);
        System.out.println("l'arme du hero : " +this.attackMode);
        System.out.println("le bouclier du hero : " +this.shield);
    }

    public void createHero() throws SQLException {
        Connection conn = new ConnectBDD().connectionToBDD();
        String query = "INSERT INTO hero(type, nom, image, niveauVie, niveauForce, moyenAttaque, bouclier) ";
        query += "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement prepare = conn.prepareStatement(query);

        Scanner sc = new Scanner(System.in);
        System.out.println("Creation d'un hero :");
        System.out.println("Quel type de hero (G : guerrier, M : magicien) :");
        String type = sc.nextLine();
        prepare.setString(1, type);
        System.out.println("Entrer le nom du Hero :");
        String name = sc.nextLine();
        prepare.setString(2, name);
        System.out.println("Entrer l'url de l'image du Hero :");
        String image = sc.nextLine();
        prepare.setString(3, image);
        System.out.println("Entrer le niveau de vie du Hero :");
        int life = sc.nextInt();
        sc.nextLine();
        prepare.setInt(4, life);
        System.out.println("Entrer le niveau d'attaque du Hero :");
        int attackLevel = sc.nextInt();
        sc.nextLine();
        prepare.setInt(5, attackLevel);
        System.out.println("Entrer l'arme du Hero :");
        String attackMode = sc.nextLine();
        prepare.setString(6, attackMode);
        System.out.println("Entrer le bouclier du Hero :");
        String shield = sc.nextLine();
        prepare.setString(7, shield);

        prepare.executeUpdate();

    }

    public void updtaeHero(int id) throws SQLException {
        Connection conn = new ConnectBDD().connectionToBDD();
        Scanner sc = new Scanner(System.in);
        boolean updated = false;

        String newType = this.type;
        System.out.println("Voulez-vous modifier le type (O : oui, N : non) ?");
        String updateType = sc.nextLine();
        switch (updateType) {
            case "O":
                System.out.println("Entrer le nouveau type (G : guerrier, M : magicien) :");
                newType = sc.nextLine();
                updated = true;
                break;
            case "N":
                break;
        }

        String newName = this.name;
        System.out.println("Voulez-vous modifier le nom (O : oui, N : non) ?");
        String updateName = sc.nextLine();
        switch (updateName) {
            case "O":
                System.out.println("Entrer le nouveau nom :");
                newName = sc.nextLine();
                updated = true;
                break;
            case "N":
                break;
        }

        String newImage = this.image;
        System.out.println("Voulez-vous modifier l'image (O : oui, N : non) ?");
        String updateImage = sc.nextLine();
        switch (updateImage) {
            case "O":
                System.out.println("Entrer la nouvelle url de l'image :");
                newImage = sc.nextLine();
                updated = true;
                break;
            case "N":
                break;
        }

        int newLife = this.life;
        System.out.println("Voulez-vous modifier le niveau de vie (O : oui, N : non) ?");
        String updateLife = sc.nextLine();
        switch (updateLife) {
            case "O":
                System.out.println("Entrer le nouveau niveau de vie :");
                newLife = sc.nextInt();
                sc.nextLine();
                updated = true;
                break;
            case "N":
                break;
        }

        int newAttackLevel = this.attackLevel;
        System.out.println("Voulez-vous modifier le niveau d'attaque (O : oui, N : non) ?");
        String updateAttackLevel = sc.nextLine();
        switch (updateAttackLevel) {
            case "O":
                System.out.println("Entrer le nouveau niveau d'attaque :");
                newAttackLevel = sc.nextInt();
                sc.nextLine();
                updated = true;
                break;
            case "N":
                break;
        }

        String newAttackMode = this.attackMode;
        System.out.println("Voulez-vous modifier l'arme (O : oui, N : non) ?");
        String updateAttackMode = sc.nextLine();
        switch (updateAttackMode) {
            case "O":
                System.out.println("Entrer la nouvelle arme :");
                newAttackMode = sc.nextLine();
                updated = true;
                break;
            case "N":
                break;
        }

        String newShield = this.shield;
        System.out.println("Voulez-vous modifier le bouclier (O : oui, N : non) ?");
        String updateShield = sc.nextLine();
        switch (updateShield) {
            case "O":
                System.out.println("Entrer le nouveau bouclier :");
                newShield = sc.nextLine();
                updated = true;
                break;
            case "N":
                break;
        }
        String query = "UPDATE hero SET type = ?, nom = ?, image = ?, niveauVie = ?, niveauForce = ?, moyenAttaque = ?, ";
        query += "bouclier = ? WHERE id = ?";
        PreparedStatement prepare = conn.prepareStatement(query);
        prepare.setString(1, newType);
        prepare.setString(2, newName);
        prepare.setString(3, newImage);
        prepare.setInt(4, newLife);
        prepare.setInt(5, newAttackLevel);
        prepare.setString(6, newAttackMode);
        prepare.setString(7, newShield);
        prepare.setInt(8, id);
        prepare.executeUpdate();
    }

    public void deleteHero(int id) throws SQLException {
        Connection conn = new ConnectBDD().connectionToBDD();
        String query = "DELETE FROM hero WHERE id = ?";
        PreparedStatement prepare = conn.prepareStatement(query);
        prepare.setInt(1, id);
        prepare.executeUpdate();
    }
}
