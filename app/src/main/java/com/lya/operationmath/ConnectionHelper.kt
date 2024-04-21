package com.lya.operationmath

import android.os.StrictMode
import android.util.Log
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException

class ConnectionHelper {
    lateinit var conn: Connection
    private val ip = " 192.168.1.8"
    private val db = "MathDB"
    private val username = "lya"
    private val password = "Password123"

    fun dbConn(): Connection? {
        println("Inside 1")
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var conn: Connection? = null
        var connString: String? = null
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver")
            connString =
                "jdbc:jtds:sqlserver://$ip;databaseName=$db;user=$username;password=$password;"
            conn = DriverManager.getConnection(connString)
        } catch (ex: SQLException) {
            Log.e("error: ", ex.message.toString())
        } catch (ex1: ClassNotFoundException) {
            Log.e("error: ", ex1.message.toString())
        } catch (ex2: Throwable) {
            Log.e("error: ", ex2.message.toString())
        }
        return conn
    }

    fun insertData(insertQuery: String, user: String, score: Int) {
        try {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            var conn: Connection? = null
            var connString: String? = null
            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver")
                connString =
                    "jdbc:jtds:sqlserver://$ip;databaseName=$db;user=$username;password=$password;"
                conn = DriverManager.getConnection(connString)
            } catch (e: SQLException) {
                e.printStackTrace()
            }

            // Create a prepared statement
            val preparedStatement = conn?.prepareStatement(insertQuery)

            // Set values for the prepared statement
            preparedStatement?.setString(1, user)
            preparedStatement?.setInt(2, score)
            // Set more values as needed

            // Execute the insert statement
            preparedStatement?.executeUpdate()

            println("Data inserted successfully")

        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            // Close connection
        }
    }

    fun getData(getQuery: String, user: String) {
        try {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            var conn: Connection? = null
            var connString: String? = null
            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver")
                connString =
                    "jdbc:jtds:sqlserver://$ip;databaseName=$db;user=$username;password=$password;"
                conn = DriverManager.getConnection(connString)
            } catch (e: SQLException) {
                e.printStackTrace()
            }

            // Create a statement
            val createStatement = conn?.createStatement()

            val rs: ResultSet? = createStatement?.executeQuery(getQuery)
            if (rs != null) {
                while (rs.next()) {

                }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            // Close connection
        }
    }
}

