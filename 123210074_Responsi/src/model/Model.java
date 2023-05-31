/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author PC PRAKTIKUM
 */
public class Model extends Konektor{
    public Model(){
        
    }
    
    public int totalDataFilm(){
        try{
            int totalData = 0;
            String query = " SELECT * FROM  `movie`";
            statement = koneksi.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next()){
                totalData++;
            }
            statement.close();
            return totalData;   
            
        }catch (Exception e){
            System.out.println("Eror : " + e.getMessage());
            return 0; 
        }
    }
    
   public String[][] readDataUser() {

        String data[][] = new String[totalDataFilm()][5];
        try {
            int indexData = 0;
            String query = "SELECT * FROM `movie`";
            statement = koneksi.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                data[indexData][0] = resultSet.getString("judul");
                data[indexData][1] = resultSet.getString("alur");
                data[indexData][2] = resultSet.getString("penokohan");
                data[indexData][3] = resultSet.getString("akting");
                data[indexData][4] = resultSet.getString("nilai");
                indexData++;
            }
            statement.close();
            return data;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return data;
        }
   }
   
  public void insertFilm(String judul, double alur, double penokohan, double akting, double nilai ) {
        
        try {
            String query = "INSERT INTO `movie` (`judul`, `alur`, `penokohan`, `akting`, `nilai`) "
                    + "VALUES "
                    + "('" + judul + "','" + alur + "','" + penokohan + "','" + akting + "','" + nilai + "')";

            statement = koneksi.createStatement();
            statement.executeUpdate(query);

            statement.close();
            JOptionPane.showMessageDialog(null, "Input Sukses");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed : " + e.getMessage());
        }
    }
   
   
}
    