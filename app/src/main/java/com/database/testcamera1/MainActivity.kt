package com.database.testcamera1

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


private const val REQUEST_CODE = 1
private lateinit var myPhoto: File
private var myPhotoName = "Photo"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnTakePicture.setOnClickListener {
            val intentPics = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            myPhoto = getPhotoFromCamera(myPhotoName)
            //security part
            val newProvider = FileProvider.getUriForFile(this,"com.database.testcamera1.fileprovider",
                myPhoto)
            intentPics.putExtra(MediaStore.EXTRA_OUTPUT, newProvider)
            if (intentPics.resolveActivity(this.packageManager) != null) {
                startActivityForResult(intentPics, REQUEST_CODE)
            } else {
                // there is an error
                Toast.makeText(this, "No Camera", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getPhotoFromCamera(filename: String): File {
    val localDirectory:File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(filename,".jpg",localDirectory)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
//            val LQimg = data?.extras?.get("data") as Bitmap   *change from low quality to high quality image*
            val HQimg = BitmapFactory.decodeFile(myPhoto.absolutePath)

            imageView.setImageBitmap(HQimg)
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

}