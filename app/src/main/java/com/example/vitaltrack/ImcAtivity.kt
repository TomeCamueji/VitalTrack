package com.example.vitaltrack

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.vitaltrack.model.App
import com.example.vitaltrack.model.Calc

class ImcAtivity : AppCompatActivity() {
    private lateinit var EditWeight:EditText
    private lateinit var Editheight:EditText
    private lateinit var btnCalc:Button
    private lateinit var moreItem:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_ativity)

        EditWeight = findViewById(R.id.edit_Weight)
        Editheight = findViewById(R.id.edit_height)
        moreItem = findViewById(R.id.more_item)
        btnCalc = findViewById(R.id.btn_calcular)


        btnCalc.setOnClickListener {
            if (!validate()) {
                Toast.makeText(this, "Todos os campos devem ser preenchidos", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }
            val weight = EditWeight.text.toString().toDouble()
            val height = Editheight.text.toString().toDouble()
            val result = calc(weight, height)
            val imcResponsive = Classification(result)

            val dialog = AlertDialog.Builder(this)
                .setTitle(getString(R.string.res, result))
                .setMessage((imcResponsive))
                .setPositiveButton(android.R.string.ok) { _, which ->

                }
                .setNegativeButton(R.string.salved) { _, _ ->

                    Thread {
                        val app = application as App
                        val dao = app.db.calcDao()
                        dao.insert(Calc(type = "IMC", res = result))

                        runOnUiThread {
                            val intent = Intent(this@ImcAtivity, RegistList::class.java)
                            intent.putExtra("type", "IMC")
                            startActivity(intent)
                        }
                    }.start()


                }
                .create()
                .show()

            moreItem.setOnClickListener {
//                Thread {
//                    val app = application as App
//                    val dao = app.db.calcDao()
//                    dao.insert(Calc(type = "IMC", res = result))
//
//                    runOnUiThread {
                        val intent = Intent(this@ImcAtivity, RegistList::class.java)
                        intent.putExtra("type", "IMC")
                        startActivity(intent)
//                    }
//                }.start()
            }

        }
    }


//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.busca,menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//        if (item.itemId == R.menu.busca){
//            openListActivity()
//        }
//        return super.onOptionsItemSelected(item)
//    }
//
//    private fun openListActivity(){
//        val intent = Intent(this@ImcAtivity, RegistList::class.java)
//        intent.putExtra("type", "IMC")
//        startActivity(intent)
//    }

    @StringRes
    private fun Classification(imc: Double): Int {
        return when {
            imc < 18.5 -> R.string.Magreza
            imc > 18.5 && imc <= 24.9 -> R.string.Normal
            imc > 24.9 && imc <= 30 -> R.string.Sobrepeso
            else -> R.string.Obsid
        }
    }

    private fun validate(): Boolean {
        return (Editheight.text.toString().isNotEmpty()
                && EditWeight.text.toString().isNotEmpty()
                && !Editheight.text.toString().startsWith("0")
                && !EditWeight.text.toString().startsWith("0"))
    }

    private fun calc(weight: Double, height: Double): Double {
        return weight / ((height / 100.0) * (height / 100.0))
    }


}