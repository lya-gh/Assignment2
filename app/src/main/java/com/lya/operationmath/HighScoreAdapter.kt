package com.lya.operationmath

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HighScoreAdapter(private val highScores: List<Pair<String, Int>>) :
    RecyclerView.Adapter<HighScoreAdapter.HighScoreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighScoreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_high_scores, parent, false)
        return HighScoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: HighScoreViewHolder, position: Int) {
        val (name, score) = highScores[position]
        holder.nameTextView.text = name
        holder.scoreTextView.text = score.toString()
    }

    override fun getItemCount() = highScores.size

    class HighScoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.textViewName)
        val scoreTextView: TextView = itemView.findViewById(R.id.textViewScore)
    }
}
