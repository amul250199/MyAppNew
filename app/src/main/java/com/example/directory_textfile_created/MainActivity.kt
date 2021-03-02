package com.example.directory_textfile_created

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    lateinit var btnOne: Button
    lateinit var btnTwo: Button

    lateinit var edtTextFileName: EditText

    lateinit var btnSave: Button
    lateinit var btnLoad: Button
    lateinit var btnDelete: Button

    lateinit var btnDeleteAll: Button

    val FILE_NAME="example.txt"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnOne=findViewById(R.id.btnOne)
        btnTwo=findViewById(R.id.btnTwo)


        btnSave=findViewById(R.id.btnSave)
        btnLoad=findViewById(R.id.btnLoad)
        btnDelete=findViewById(R.id.btnDelete)

        btnDeleteAll=findViewById(R.id.btnDeleteAll)

        edtTextFileName=findViewById(R.id.edtTxtFileName)


        btnOne.setOnClickListener {

            val folder = filesDir
            val f = File(folder, "TestFolder")
            if(f.exists())
            {
               //    f.delete()
               // f.mkdir()
                   Toast.makeText(this,"Folder="+folder,Toast.LENGTH_SHORT).show()
                Toast.makeText(this,"Already Exists", Toast.LENGTH_SHORT).show()
            }
            else
            {
                f.mkdir()
                Toast.makeText(this,"Folder="+folder,Toast.LENGTH_SHORT)
                Toast.makeText(this,"Created SuccessFully", Toast.LENGTH_SHORT).show()
            }

        }

        btnSave.setOnClickListener {
            val text: String=edtTextFileName.text.toString()
            val fos: FileOutputStream =openFileOutput(FILE_NAME, MODE_PRIVATE)
            fos.write(text.toByteArray(Charsets.UTF_8))
            edtTextFileName.text.clear()
            Toast.makeText(this, "Saved to " + filesDir + "/" + FILE_NAME, Toast.LENGTH_SHORT).show()

            if(fos!=null)
            {
                fos.close()
            }

        }

        btnDelete.setOnClickListener {
            val dir: File = filesDir
            val file = File(dir, FILE_NAME)
            val deleted: Boolean = file.delete()

            Toast.makeText(this,"Deleted ="+deleted,Toast.LENGTH_SHORT).show()
            if(deleted)
            {
                Toast.makeText(this,"Delete file successFully",Toast.LENGTH_SHORT)
            }
            else
            {
                Toast.makeText(this,"No Delete ",Toast.LENGTH_SHORT).show()
            }

        }

        btnLoad.setOnClickListener {

        }

        btnDeleteAll.setOnClickListener {

            try
            {
                val dir:File=filesDir
                val f = File(dir, "TestFolder")
                val myfile=f.list()
                for(i in myfile)
                {
                    val myfile=File(f,i)
                    myfile.delete()
                }

                f.delete()

                if(f.exists())
                {
                    Toast.makeText(this,"Folder Not Delete",Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Toast.makeText(this,"Delete Successfully",Toast.LENGTH_SHORT).show()
                }
            }
            catch(ex: Exception)
            {
                Toast.makeText(this,"exception ex="+ex.toString(),Toast.LENGTH_SHORT).show()
            }


        }


    }
}