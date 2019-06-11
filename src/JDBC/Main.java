package JDBC;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class Main {

    public static void main(String[] args) {
        Hero hero = new Hero();
        try {
            hero.getHeroes();
            hero.getHere(2);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
