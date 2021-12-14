/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

    private Connection conn;
    private Statement stm;
    private ResultSet rst;

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Statement getStm() {
        return stm;
    }

    public void setStm(Statement stm) {
        this.stm = stm;
    }

    public ResultSet getRst() {
        return rst;
    }

    public void setRst(ResultSet rst) {
        this.rst = rst;
    }

    public void conectar() throws ClassNotFoundException, SQLException {

        try {

            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mysql://192.168.0.2:3306/segta?zeroDateTimeBehavior=convertToNull", "segta", "nvr963");
           conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/segta?zeroDateTimeBehavior=convertToNull", "root", "1234");
        } finally {

        }
    }

    public void desconectar() throws SQLException {

        conn.close();

    }

}
