package com.example.todolist

import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    lateinit var toDoText : EditText
    lateinit var addBtn : Button
    lateinit var listView : ListView

    lateinit var taskList : ArrayList<String>
    var fileHelp = fileHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toDoText = findViewById(R.id.inputText)
        addBtn = findViewById(R.id.addBtn)
        listView = findViewById(R.id.taskList)

        taskList = fileHelp.readData(this)
        var adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,taskList)
        listView.adapter = adapter

        addBtn.setOnClickListener{
            var itemName : String = toDoText.text.toString()
            taskList.add(itemName)
            toDoText.setText("")
            fileHelp.writeData(taskList,this@MainActivity)
            adapter.notifyDataSetChanged()
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            var alertBox = AlertDialog.Builder(this)
            alertBox.setTitle("Delete")
            alertBox.setMessage("Have you completed this task?")
            alertBox.setCancelable(false)
            alertBox.setNegativeButton("No",DialogInterface.OnClickListener { dialog, _ ->
                dialog.cancel()
            })
            alertBox.setPositiveButton("Yes",DialogInterface.OnClickListener { _, _ ->
                taskList.removeAt(position)
                adapter.notifyDataSetChanged()
                fileHelp.writeData(taskList,this@MainActivity)
            })
            alertBox.create().show()
        }

    }
}