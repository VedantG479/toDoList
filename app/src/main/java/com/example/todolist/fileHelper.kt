package com.example.todolist

import android.content.Context
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class fileHelper {

    val fileName = "TASKLIST_FILE.dat"

    fun writeData(item: ArrayList<String>,context: Context) {
        var FOS : FileOutputStream = context.openFileOutput(fileName,Context.MODE_PRIVATE)
        var oos  = ObjectOutputStream(FOS)
        oos.writeObject(item)
        oos.close()
    }

    fun readData(context: Context) : ArrayList<String>{
        var itemList : ArrayList<String>
        try {
            var FIS : FileInputStream = context.openFileInput(fileName)
            var ois = ObjectInputStream(FIS)
            itemList = ois.readObject() as ArrayList<String>
        }
        catch (e: FileNotFoundException){
            itemList = ArrayList()
        }

        return itemList
    }
}