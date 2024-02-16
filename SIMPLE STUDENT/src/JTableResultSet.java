/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ONEAL
 */
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.TableColumn;

public class JTableResultSet extends javax.swing.JFrame{
    public static void main(String[] args) {
        Vector columnNames = new Vector();
        Vector data = new Vector();
        JPanel panel = new JPanel();   //
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/simple_student","root","");
            String sql = "Select * from student";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columns = metaData.getColumnCount();
            for (int i = 1; i <= columns; i++) {
                columnNames.addElement(metaData.getColumnName(i));
            }
            while (resultSet.next()) {
                Vector row = new Vector(columns);
                for (int i = 1; i <= columns; i++) {
                    row.addElement(resultSet.getObject(i));
                }
                data.addElement(row);
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        JTable table = new JTable(data, columnNames);
        TableColumn column;
        for (int i = 0; i < table.getColumnCount(); i++) {
            column = table.getColumnModel().getColumn(i);
            column.setMaxWidth(250);
        }
        JScrollPane scrollPane = new JScrollPane(table);        panel.add(scrollPane);               
        JFrame frame = new JFrame();
        frame.add(panel);         //adding panel to the frame
        frame.setSize(600, 400); //setting frame size
        frame.setVisible(true);  //setting visibility true
    }
}
