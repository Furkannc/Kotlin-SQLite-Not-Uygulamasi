package com.example.noteapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_note_page.*

class notePage : AppCompatActivity() {
    var idNo:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_page)
        val intent=intent

        idNo=intent.getIntExtra("id",0)

        val title=intent.getStringExtra("title")

        val content=intent.getStringExtra("content")

        txtTitle.setText(title)

        txtcontent.setText(content)

    }

    fun sqlGuncelle(view: View){
        val content=txtcontent.text
        val title=txtTitle.text

        try {
            //veri tabanı oluşturmak
            val veritabani=this.openOrCreateDatabase("NOTE", MODE_PRIVATE, null)
            //tablo oluşturmak
            veritabani.execSQL("CREATE TABLE IF NOT EXISTS note (id INTEGER PRIMARY KEY,titles VARCHAR, notes VARCHAR)")
            //veri eklemek
            veritabani.execSQL("UPDATE note set titles='${title}',notes='${content}' where id=${idNo}")

            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        catch (e:Exception){
            Toast.makeText(this,e.toString(), Toast.LENGTH_LONG).show()
        }
    }

    fun sqlSil(view: View){
        val alert=AlertDialog.Builder(this)
        alert.setTitle("Cofirm?")
        alert.setMessage("Are you sure you want to delete?")
        alert.setNegativeButton("No",null)
        alert.setPositiveButton("Yes",DialogInterface.OnClickListener { dialogInterface, i ->
            try {

                val veritabani=this.openOrCreateDatabase("NOTE", MODE_PRIVATE, null)

                veritabani.execSQL("CREATE TABLE IF NOT EXISTS note (id INTEGER PRIMARY KEY,titles VARCHAR, notes VARCHAR)")

                veritabani.execSQL("Delete from note where id=${idNo}")

                Toast.makeText(this,"Successfully deleted",Toast.LENGTH_LONG).show()
                val intent=Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            catch (e:Exception){
                Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show()
            }
        })
        alert.show()
    }
}