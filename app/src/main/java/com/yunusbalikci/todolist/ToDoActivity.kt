package com.yunusbalikci.todolist

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yunusbalikci.todolist.databinding.ActivityMainBinding
import com.yunusbalikci.todolist.databinding.ActivityToDoBinding

class ToDoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityToDoBinding
    private lateinit var notesList:ArrayList<Notes>
    private lateinit var adapter: MyAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToDoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recycleView.setHasFixedSize(true)
        binding.recycleView.layoutManager = LinearLayoutManager(this)
        val u1 =Notes(1,R.drawable.logo,"Deneme 123")
        notesList=ArrayList<Notes>()
        notesList.add(u1)

        adapter = MyAdapter(this,notesList)
        binding.recycleView.adapter = adapter

    }

}