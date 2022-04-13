package com.example.noteapp

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.openOrCreateDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

val DatabaseName="NOTE"
val TableName="note"
val col_title="titles"
val col_content="notes"
val col_id="id"
class database(var context:Context):SQLiteOpenHelper(context,DatabaseName,null,1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        val createTable="CREATE TABLE IF NOT EXISTS "+TableName+ "("+col_id+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                col_title+" VARCHAR," +
                col_content+" VARCHAR)"
        p0?.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun Insert(title:String, content:String){
        val db=this.writableDatabase
        val cv=ContentValues()
        cv.put(col_title,title)
        cv.put(col_content,content)

        var result=db.insert(TableName,null,cv)
        if(result==(-1).toLong()){
            Toast.makeText(context,"Kayıt başarısız",Toast.LENGTH_LONG).show()
        }
        else
            Toast.makeText(context,"Kayıt başarılı",Toast.LENGTH_LONG).show()
    }

}