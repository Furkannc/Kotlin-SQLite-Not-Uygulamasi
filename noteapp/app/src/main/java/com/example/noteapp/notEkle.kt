package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_not_ekle.*

class notEkle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_not_ekle)
    }
    fun sqlEkle(view: View){
        val content=txtcontent.text
        val title=txtTitle.text
        if(title!=null){
            try {

                val veritabani=this.openOrCreateDatabase("NOTE", MODE_PRIVATE, null)

                veritabani.execSQL("CREATE TABLE IF NOT EXISTS note (id INTEGER PRIMARY KEY,titles VARCHAR, notes VARCHAR)")

                veritabani.execSQL("INSERT INTO note (titles,notes) values ('${title}','${content}')")

                val intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            catch (e:Exception){
                Toast.makeText(this,e.toString(), Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(this,"Başlık alanı boş olmamalıdır", Toast.LENGTH_LONG).show()
        }
    }
}