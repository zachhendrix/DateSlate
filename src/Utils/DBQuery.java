/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author zach.hendrix
 */
public class DBQuery 
{
    private static Statement statement;
    
    public static void setStatement(Connection conn) throws SQLException
    {
        statement = conn.createStatement();
    }
    
    public static Statement getStatement()
    {
        return statement;
    }


    
}
