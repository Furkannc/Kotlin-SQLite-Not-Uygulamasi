package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var id= arrayListOf<Int>()
        var title= arrayListOf<String>()
        var content= arrayListOf<String>()

        try {

            val veritabani=this.openOrCreateDatabase("NOTE", MODE_PRIVATE, null)

            veritabani.execSQL("CREATE TABLE IF NOT EXISTS note (id INTEGER PRIMARY KEY,titles VARCHAR, notes VARCHAR)")

            val cursor=veritabani.rawQuery("select * from note",null)


            val idColumnIndex=cursor.getColumnIndex("id")
            val titleColumnIndex=cursor.getColumnIndex("titles")
            val contentColumnIndex=cursor.getColumnIndex("notes")


            while (cursor.moveToNext()){
                id.add(cursor.getInt(idColumnIndex))
                title.add(cursor.getString(titleColumnIndex))
                content.add(cursor.getString(contentColumnIndex))
            }
            cursor.close()
        }
        catch (e:Exception){
            Toast.makeText(this,e.toString(), Toast.LENGTH_LONG).show()
        }

        val layoutManager= LinearLayoutManager(this)

        recyclerView.layoutManager=layoutManager

        var adapter=RecyclerAdapter(id,title,content)

        recyclerView.adapter=adapter
    }

    fun sqlEkleGit(view: View){
        val intent= Intent(this,notEkle::class.java)
        startActivity(intent)

    }
}