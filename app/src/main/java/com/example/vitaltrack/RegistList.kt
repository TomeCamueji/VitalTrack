package com.example.vitaltrack

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vitaltrack.model.App
import com.example.vitaltrack.model.Calc
import java.text.SimpleDateFormat
import java.util.Locale

class RegistList : AppCompatActivity() {

    private lateinit var rvMain: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_regist_list2)

        val listResult = mutableListOf<Calc>()
        val adapter = MainAdapter(listResult)
        rvMain = findViewById(R.id.rv_main)
        rvMain.adapter = adapter
        rvMain.layoutManager = LinearLayoutManager(this)


        val type = intent?.extras?.getString("type") ?: throw IllegalAccessException("type not found")
        Thread {
            val app = application as App
            val dao = app.db.calcDao()
            val response = dao.getRegisterByType(type)

            runOnUiThread {
                listResult.addAll(response)
                adapter.notifyDataSetChanged()
            }
        }.start()
    }

    private inner class MainAdapter(
        private var mylist: List<Calc>
    ) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
//            inflar
            val itemview = layoutInflater.inflate(android.R.layout.simple_list_item_1,parent,false)
            return MainViewHolder(itemview)
        }

        override fun getItemCount(): Int {
            return mylist.size
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            val current = mylist[position]
            holder.bild(current)
        }

        inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bild(item:Calc){
                val tv = itemView as TextView
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("pt","BR"))
                val data =sdf.format(item.createdDate)
                val res = item.res

                tv.text = getString(R.string.List_response,res,data)
            }

        }

    }
}