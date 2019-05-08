package com.idiotleon.tutorialadvancedandroidespressotesting

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_ideas.*

class IdeasActivity : AppCompatActivity() {
    companion object {
        val KEY_THEME = "theme"
        val KEY_NAME = "name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_ideas)

        val themeText = intent.getStringExtra(KEY_THEME)

        tv_theme.text = themeText

        val ideasId = when (themeText) {
            getString(R.string.theme_popular) -> R.array.ideas_popular
            getString(R.string.theme_famous) -> R.array.ideas_famous
            getString(R.string.theme_punny) -> R.array.ideas_punny
            else -> 0
        }

        if (ideasId == 0) {
            tv_theme.text = getString(R.string.unknown_theme, theme)
            return
        }

        val ideas = resources.getStringArray(ideasId)

        rv_ideas.setHasFixedSize(true)
        rv_ideas.layoutManager = LinearLayoutManager(this)
        rv_ideas.adapter = IdeasAdapter(ideas, object : IdeasAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val data = Intent()
                data.putExtra(KEY_NAME, ideas[position])
                setResult(Activity.RESULT_OK, data)
                finish()
            }
        })
    }
}

class IdeasAdapter(
    private val ideas: Array<String>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<TextViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): TextViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.idea, parent, false)
        return TextViewHolder(view)
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        val label = ideas[position]
        holder.textView.text = label
        holder.textView.setOnClickListener { listener.onItemClick(position) }
    }

    override fun getItemCount(): Int {
        return ideas.size
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}

class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView: TextView = itemView as TextView
}