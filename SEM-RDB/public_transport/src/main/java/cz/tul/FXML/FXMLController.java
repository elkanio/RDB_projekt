package cz.tul.FXML;

import com.jfoenix.controls.JFXButton;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.JdbcStatement;
import com.mysql.cj.jdbc.MysqlDataSource;
import com.mysql.cj.jdbc.StatementImpl;
import cz.tul.WaterMarking;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.hibernate.Session;
import org.hsqldb.jdbc.JDBCConnection;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class FXMLController implements Initializable {
    WaterMarking waterMarking = new WaterMarking();


    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;

    MysqlDataSource dataSource;


    String[] allFiles = {"znacka.csv", "autobus.csv", "typkontaktu.csv", "ridic.csv", "kontakt.csv", "lokalita.csv", "trasy.csv", "jizda.csv", "klient.csv", "jizdenka.csv",};
    String[] nonTimeFiles = {"znacka.csv", "autobus.csv", "typkontaktu.csv", "ridic.csv", "kontakt.csv", "lokalita.csv", "trasy.csv", "klient.csv"};
    String jizdaFile = "jizda.csv";
    String jizdenkaFile = "jizdenka.csv";

    @FXML
    private Label label;

    @FXML
    private JFXButton importButton, exportButton;

    public FXMLController(){
    }

    @FXML
    private void importButtonAction(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Vyberte adresář");
        Stage directoryChooserStage = new Stage();
        File file = directoryChooser.showDialog(directoryChooserStage);
        if (file != null) {
            importData(file);
        }
    }

    @FXML
    private void exportButtonAction(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File dir = directoryChooser.showDialog(new Stage());
        if (dir != null) {
            saveFile(dir);

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dataSource = createDatasource();
    }

    private MysqlDataSource createDatasource(){
        try {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setAllowLoadLocalInfile(true);
            dataSource.setServerName("localhost");
            dataSource.setPortNumber(3306);
            dataSource.setDatabaseName("publictransport");
            dataSource.setUser(username);
            dataSource.setPassword(password);
            dataSource.setServerTimezone("UTC");
            dataSource.setConnectionCollation("utf8mb4_bin");


            return dataSource;
        }
        catch(SQLException ex){
            System.out.println(ex);
        }
        return null;
    }

    private void deleteTable(String table){

        try {
            Connection con = dataSource.getConnection();
            Statement statement = con.createStatement();
            String query = String.format("DELETE FROM %s", table);

            long set = statement.executeLargeUpdate(query);

            int i = 1;
        }
        catch(SQLException ex){
            System.out.println(ex);
        }
    }

    private boolean importData(File dir) {
        System.out.println("Import started.");
        for (String file: allFiles) {
            File filename = new File(dir.getPath()+ "\\" + file);
            if(!filename.exists()){
                System.out.println("Cannot import database! File: " + filename.getAbsolutePath() + " is missing.");
                return false;
            }
        }
        for (int i = allFiles.length - 1; i >= 0; i--) {
            String file = allFiles[i];
            File filename = new File(dir.getPath()+ "\\" + file);
            deleteTable( file.substring(0, file.indexOf('.')));
        }

        for (String file: nonTimeFiles) {
            File filename = new File(dir.getPath()+ "\\" + file);
            importFile(filename.getAbsolutePath(), file.substring(0, file.indexOf('.')));
        }

        File filename = new File(dir.getPath()+ "\\" + jizdaFile);
        importJizda(filename.getAbsolutePath());

        filename = new File(dir.getPath()+ "\\" + jizdenkaFile);
        importJizdenka(filename.getAbsolutePath());
        System.out.println("Import finished");

        for (String file: allFiles) {
            System.out.println("Watermark check for table " + file.substring(0, file.indexOf('.')) +
                    " is: " + checkWatermark(file.substring(0, file.indexOf('.'))) + "%");
        }


        return true;
    }


    private void importFile(String filename, String tableName) {
        Connection con;
        try{
            con = dataSource.getConnection();
            Statement statement = con.createStatement();
            String query = String.format("LOAD DATA LOCAL INFILE '%s' " +
                    "INTO TABLE %s " +
                    "CHARACTER SET UTF8 " +
                    "FIELDS TERMINATED BY ',' " +
                    "ESCAPED BY '' " +
                    "LINES TERMINATED BY '\\r\\n' " +
                    ";", filename.toString().replace('\\', '/'), tableName);

            ResultSet set = statement.executeQuery(query);
            int i = 1;
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }


    private void importJizda(String filename) {
        Connection con;
        try{
            con = dataSource.getConnection();
            Statement statement = con.createStatement();
            String query = String.format("LOAD DATA LOCAL INFILE '%s' " +
                    "INTO TABLE Jizda " +
                    "FIELDS TERMINATED BY ',' " +
                    "ESCAPED BY '' " +
                    "LINES TERMINATED BY '\\r\\n' " +
                    "(linka, spz, cislo_rp, @cas ) " +
                    "SET cas = FROM_UNIXTIME(@cas) " +
                    "COLLATE utf8mb4_bin;", filename.toString().replace('\\', '/'));

            ResultSet set = statement.executeQuery(query);
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }

    private void importJizdenka(String filename) {
        Connection con;
        try{
            con = dataSource.getConnection();
            Statement statement = con.createStatement();
            String query = String.format("LOAD DATA LOCAL INFILE '%s' " +
                    "INTO TABLE Jizdenka " +
                    "FIELDS TERMINATED BY ',' " +
                    "LINES TERMINATED BY '\\r\\n' " +
                    "(linka, email, @cas, cislo) " +
                    "SET cas = FROM_UNIXTIME(@cas) " +
                    "COLLATE utf8mb4_bin;", filename.toString().replace('\\', '/'));

            ResultSet set = statement.executeQuery(query);
            int i = 1;
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }

    private void exportTable(String dir, String table){
        Connection con;
        try{
            con = dataSource.getConnection();
            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String query = String.format("SELECT * FROM %s", table);

            ResultSet set = statement.executeQuery(query);
            ResultSetMetaData meta = set.getMetaData();


            for (int i = 1; i <= meta.getColumnCount(); i++){
                String temp = meta.getColumnClassName(i);
                if (meta.getColumnClassName(i).equals("java.lang.String")){
                    while(set.next()){
                        set.updateString(i,waterMarking.addWatermark(set.getString(i)));
                        try {
                            set.updateRow();
                        }
                        catch(SQLIntegrityConstraintViolationException ex)
                        {
                            break;
                        }

                    }
                    set.beforeFirst();
                }
            }

            CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(new FileOutputStream(dir + "/" + table + ".csv"), "UTF-8"),
                    ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.NO_ESCAPE_CHARACTER, "\r\n");
            csvWriter.writeAll(set, false);
            csvWriter.close();
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }

    private void exportJizda(String dir ){
        Connection con;
        try{
            con = dataSource.getConnection();
            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String query = String.format("SELECT linka, spz, cislo_rp, cas FROM jizda");

            ResultSet set = statement.executeQuery(query);
            ResultSetMetaData meta = set.getMetaData();


            for (int i = 1; i <= meta.getColumnCount(); i++){
                String temp = meta.getColumnClassName(i);
                if (meta.getColumnClassName(i).equals("java.lang.String")){
                    while(set.next()){
                        set.updateString(i,waterMarking.addWatermark(set.getString(i)));
                        try {
                            set.updateRow();
                        }
                        catch(SQLIntegrityConstraintViolationException ex)
                        {
                            break;
                        }

                    }
                    set.beforeFirst();
                }
            }
            query = String.format("SELECT linka, spz, cislo_rp, UNIX_TIMESTAMP(cas) FROM jizda");

            set = statement.executeQuery(query);

            CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(new FileOutputStream(dir + "/" + "jizda" + ".csv"), "UTF-8"),
                    ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.NO_ESCAPE_CHARACTER, "\r\n");
            csvWriter.writeAll(set, false);
            csvWriter.close();
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }


    private void exportJizdenka(String dir){
        Connection con;
        try{
            con = dataSource.getConnection();
            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String query = String.format("SELECT linka, email, cas, cislo FROM jizdenka");

            ResultSet set = statement.executeQuery(query);
            ResultSetMetaData meta = set.getMetaData();


            for (int i = 1; i <= meta.getColumnCount(); i++){
                String temp = meta.getColumnClassName(i);
                if (meta.getColumnClassName(i).equals("java.lang.String")){
                    while(set.next()){
                        set.updateString(i,waterMarking.addWatermark(set.getString(i)));
                        try {
                            set.updateRow();
                        }
                        catch(SQLIntegrityConstraintViolationException ex)
                        {
                            break;
                        }

                    }
                    set.beforeFirst();
                }
            }
            query = String.format("SELECT linka, IFNULL(email, '\\\\N'), UNIX_TIMESTAMP(cas), cislo FROM jizdenka");

            set = statement.executeQuery(query);

            CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(new FileOutputStream(dir + "/" + "jizdenka" + ".csv"), "UTF-8"),
                    ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.NO_ESCAPE_CHARACTER, "\r\n");
            csvWriter.writeAll(set, false);
            csvWriter.close();
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }

    private double checkWatermark(String table){
        Connection con;
        try{
            con = dataSource.getConnection();
            Statement statement = con.createStatement();
            String query = String.format("SELECT * FROM %s", table);

            ResultSet set = statement.executeQuery(query);
            ResultSetMetaData meta = set.getMetaData();
            int watermarkHit = 0;
            int nEntries = 0;

            for (int i = 1; i <= meta.getColumnCount(); i++){
                String temp = meta.getColumnClassName(i);
                if (meta.getColumnClassName(i).equals("java.lang.String")){
                    while(set.next()){
                        String currCell = set.getString(i);
                        if(currCell == null)
                        {
                            continue;
                        }
                        nEntries++;
                        if (waterMarking.detectWatermark(currCell)){
                            watermarkHit++;
                        }
                    }
                    set.beforeFirst();
                }
            }
            if (nEntries == 0){
                return -1;
            }
            return watermarkHit/(double)nEntries * 100;

        }
        catch (Exception ex){
            System.out.println(ex);
        }
        return 0;
    }

    private void saveFile(File dir) {
        System.out.println("Begin export");
        System.out.println("Exporting: jizdenka");
        exportJizdenka(dir.getPath());
        System.out.println("Exporting: jizda");
        exportJizda(dir.getPath());

        for (int i = nonTimeFiles.length - 1; i >= 0; i--) {
            String file = nonTimeFiles[i];
            System.out.println("Exporting: " +file.substring(0, file.indexOf('.')));
            exportTable(dir.getPath(), file.substring(0, file.indexOf('.')));
        }

        System.out.println("Export finished.");

    }
}
