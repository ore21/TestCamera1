package com.database.testcamera1

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


private const val REQUEST_CODE = 1
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnTakePicture.setOnClickListener {
            val intentPics = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (intentPics.resolveActivity(this.packageManager) != null) {
                startActivityForResult(intentPics, REQUEST_CODE)
            } else {
                // there is an error
                Toast.makeText(this, "No Camera", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            val LQimg = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(LQimg)
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

}