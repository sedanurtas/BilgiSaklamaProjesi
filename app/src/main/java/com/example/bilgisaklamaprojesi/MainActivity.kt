package com.example.bilgisaklamaprojesi

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bilgisaklamaprojesi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //SharedPreferences
    lateinit var sharedPreferences: SharedPreferences

    var alinanKullaniciAdi : String? = null




    override fun onCreate(savedInstanceState: Bundle?) { /*oncreate fonks içine yazıyoruz ki ilk çalıştığı zaman başlatılsın */
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        val view =binding.root
        setContentView(view)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
       sharedPreferences = getSharedPreferences("com.example.bilgisaklamaprojesi",
            Context.MODE_PRIVATE) //isim sharedpreferencesın ismi genelde paket ismi yazılır.
        //MODE_PRİVATE bizim oluşturduğumuz dosyaya başka kimse erişemiyor demek

        alinanKullaniciAdi = sharedPreferences.getString("isim","")//defvalue eğer aradığımız değerde bi anahtar yoksa ne aktarıyım demek
        if (alinanKullaniciAdi==""){
            binding.textView.text="Kaydedilen İsim : "

        }
        else{
            binding.textView.text = "Kaydedilen İsim : ${alinanKullaniciAdi} "
        }
    }

    fun kaydet(view: View){

        val kullaniciIsmi = binding.editText.text.toString()
        if(kullaniciIsmi=="") {
            Toast.makeText(this, "İsminizi boş bırakmayınız", Toast.LENGTH_LONG).show()
        }
        else{ //kullanıcı ismi boş değilse sharedpreferences a kaydedilecek.
            sharedPreferences.edit().putString("isim",kullaniciIsmi).apply() //edit açma apply kapama gibi düşünebilirsiniz
            binding.textView.text = "Kaydedilen isim : ${kullaniciIsmi}"
        }




    }

    fun sil(view: View){

        alinanKullaniciAdi = sharedPreferences.getString("isim","")
        if (alinanKullaniciAdi!=""){
            sharedPreferences.edit().remove("isim").apply()

        }
        binding.textView.text = "Kaydedilen İsim : "

    }

}