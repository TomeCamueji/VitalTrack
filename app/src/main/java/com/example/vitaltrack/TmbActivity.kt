package com.example.vitaltrack

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.vitaltrack.model.App
import com.example.vitaltrack.model.Calc

class TmbActivity : AppCompatActivity() {
    private lateinit var lifestyle:AutoCompleteTextView
    private lateinit var EditWeight: EditText
    private lateinit var Editheight: EditText
    private lateinit var EditAge:EditText
    private lateinit var btnCalc: Button
    private lateinit var moreItem: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_tmb)

        lifestyle = findViewById(R.id.auto_lifestyle)
        val items = resources.getStringArray(R.array.btm_list)
        lifestyle.setText(items.first())
        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,items)
        lifestyle.setAdapter(adapter)


        EditWeight = findViewById(R.id.edit_Weight)
        Editheight = findViewById(R.id.edit_height)
        moreItem = findViewById(R.id.more_item)
        btnCalc = findViewById(R.id.btn_calcular)
        EditAge = findViewById(R.id.edit_age)

        btnCalc.setOnClickListener{
            if (!validate()) {
                Toast.makeText(this, "Todos os campos devem ser preenchidos", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }

            val weight = EditWeight.text.toString().toDouble()
            val height = Editheight.text.toString().toDouble()
            val age = EditAge.text.toString().toDouble()
            val result = Calculatetmb(weight,height,age)
            val response = tmbResquest(result)


            val dialog = AlertDialog.Builder(this)
                .setMessage(getString(R.string.tmb_response, response))
                .setPositiveButton(android.R.string.ok) { _, which ->

                }
                .setNegativeButton(R.string.salved) { _, _ ->

                    Thread {
                        val app = application as App
                        val dao = app.db.calcDao()
                        dao.insert(Calc(type = "TMB", res = result))

                        runOnUiThread {
                            val intent = Intent(this@TmbActivity, RegistList::class.java)
                            intent.putExtra("type", "TMB")
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
//                    dao.insert(Calc(type = "TMB", res = result))

//                    runOnUiThread {
                        val intent = Intent(this@TmbActivity, RegistList::class.java)
                        intent.putExtra("type", "TMB")
                        startActivity(intent)

//                    }
//                }.start()
            }

        }

    }

    private fun tmbResquest(tmb:Double): Double {

        val items = resources.getStringArray(R.array.btm_list)
        return if (lifestyle.text.toString() == items[1]){
            tmb * 1.2
        } else if (lifestyle.text.toString() == items[2]){
            tmb * 1.375
        }else if (lifestyle.text.toString() == items[3]){
            tmb * 1.55
        }else if (lifestyle.text.toString() == items[4]) {
            tmb * 1.725
        }else if (lifestyle.text.toString() == items[5]){
            tmb * 1.9
        }else{
            0.0
        }
    }

    private fun Calculatetmb(weight: Double, height:Double, age:Double):Double{
        return 66 + (13.8 * weight) + (5 * height) - (6.8 * age)
    }

    private fun validate(): Boolean {
        return (Editheight.text.toString().isNotEmpty()
                && EditAge.text.toString().isNotEmpty()
                && EditWeight.text.toString().isNotEmpty()
                && !Editheight.text.toString().startsWith("0")
                && !EditWeight.text.toString().startsWith("0")
                && !EditAge.text.toString().startsWith("0"))
    }
}