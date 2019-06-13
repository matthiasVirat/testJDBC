package JDBC;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class Main {

    public static void main(String[] args) {
        Hero myHero = new Hero();
        try {
            myHero.getHeroes();

            myHero.getHero(2);
            myHero.displayHero();
//            myHero.updateHero(myHero.id);

//            myHero.deleteHero(5);

//            myHero.createHero();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
