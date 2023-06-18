package com.yunusbalikci.todolist

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.yunusbalikci.todolist.databinding.ActivityToDoBinding

class ToDoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityToDoBinding
    private lateinit var notesList:ArrayList<Notes>
    private lateinit var adapter: MyAdapter
    private lateinit var sharedPreferences:SharedPreferences
    private lateinit var editor: SharedPreferences.Editor // Add this line


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToDoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.recycleView.setHasFixedSize(true)
        binding.recycleView.layoutManager = LinearLayoutManager(this)

        notesList = ArrayList() // Move the initialization here
        adapter = MyAdapter(this,notesList)
        binding.recycleView.adapter = adapter



        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()


        binding.floatingActionButton2.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val inflater =layoutInflater
            val dialogLayout = inflater.inflate(R.layout.edit_text_layout,null)
            val editText = dialogLayout.findViewById<EditText>(R.id.et_editText)

            with(builder){
                setTitle("Enter some task!")
                setPositiveButton("OK"){dialog,which ->
                    val text = editText.text.toString()

                    editor.putString("task", text)
                    editor.commit()
                    Log.d("Denme","Başarılı")

                    val storedTask =sharedPreferences.getString("task","")
                    if (storedTask != null && storedTask.isNotEmpty()) {
                        // SharedPreferences'den alınan değer boş değilse ve içerik varsa işlemleri yapabilirsiniz
                        // Örneğin, RecyclerView veri listesine ekleyebilirsiniz:
                        val note = Notes(1, R.drawable.logo, storedTask)
                        notesList.add(note)
                        adapter.notifyDataSetChanged()
                    }

                }
                setNegativeButton("Cancel"){dialog,which ->
                    Log.d("Main","Negative Button")
                }
                setView(dialogLayout)
                show()
            }

        }
    }







}