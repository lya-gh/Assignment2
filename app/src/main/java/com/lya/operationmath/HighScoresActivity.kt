package com.lya.operationmath

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException

class HighScoresActivity : AppCompatActivity() {
    private lateinit var deleteScoresButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high_scores)

        val highScores = getScores()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewHighScores)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = HighScoreAdapter(highScores)

        deleteScoresButton = findViewById<Button>(R.id.deleteButton)
        deleteScoresButton.setOnClickListener() {
            deleteScores()
            val highScores = getScores()

            val recyclerView: RecyclerView = findViewById(R.id.recyclerViewHighScores)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = HighScoreAdapter(highScores)

        }
    }
}

//Get the high scores from the DB
private fun getScores():MutableList<Pair<String, Int>>{
    val connectionString = "jdbc:sqlserver:20231470-lya;databaseName=MathDB;integratedSecurity=true;"
    val scores: MutableList<Pair<String, Int>> = mutableListOf()
    try {
        // Load SQL Server JDBC driver and establish connection
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var conn: Connection? = null
        val ip = "192.168.1.8"
        val db = "MathDB"
        val username = "lya"
        val password = "Password123"
        var connString: String? = null
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver")
            connString =
                "jdbc:jtds:sqlserver://$ip;databaseName=$db;user=$username;password=$password;"
            conn = DriverManager.getConnection(connString)
        } catch (e: SQLException) {
            e.printStackTrace()
        }

        val statement = conn?.createStatement()
        val resultSet: ResultSet? = statement?.executeQuery("select username, Max(score) as score from scores\n" +
                "  group by username\n" +
                "  order by 2 desc")

        if (resultSet != null) {
            while (resultSet.next()) {
                scores.add(resultSet.getString("Username") to resultSet.getString("Score").toInt())
            }
        }

        // Close connection
        resultSet?.close()
        statement?.close()
        conn?.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return scores
}

//Get the high scores from the DB
private fun deleteScores(){
    val connectionString = "jdbc:sqlserver:20231470-lya;databaseName=MathDB;integratedSecurity=true;"
    val scores: MutableList<Pair<String, Int>> = mutableListOf()
    try {
        // Load SQL Server JDBC driver and establish connection
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var conn: Connection? = null
        val ip = "192.168.1.8"
        val db = "MathDB"
        val username = "lya"
        val password = "Password123"
        var connString: String? = null
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver")
            connString =
                "jdbc:jtds:sqlserver://$ip;databaseName=$db;user=$username;password=$password;"
            conn = DriverManager.getConnection(connString)
        } catch (e: SQLException) {
            e.printStackTrace()
        }

        val statement = conn?.createStatement()
        val resultSet: ResultSet? = statement?.executeQuery("delete from scores")

        // Close connection
        resultSet?.close()
        statement?.close()
        conn?.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

