package com.example.vitaltrack.FARMAS

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vitaltrack.R
import com.example.vitaltrack.model.date

class farmacia : AppCompatActivity() {
    private lateinit var rvMain: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_farmacia)

        val List = mutableListOf<Listfarm>()

        List.add(
            Listfarm(
                Id = 7,
                name = R.string.farma8,
                avaliacao = R.string.avaliacao8,
                endreco = R.string.endereco8,
                status = R.string.status8
            )
        )
        List.add(
            Listfarm(
                Id = 8,
                name = R.string.farma9,
                avaliacao = R.string.avaliacao9,
                endreco = R.string.endereco9,
                status = R.string.status9
            )
        )
        List.add(
            Listfarm(
                Id = 9,
                name = R.string.farma10,
                avaliacao = R.string.avaliacao10,
                endreco = R.string.endereco10,
                status = R.string.status10
            )
        )
        List.add(
            Listfarm(
                Id = 10,
                name = R.string.farma11,
                avaliacao = R.string.avaliacao11,
                endreco = R.string.endereco11,
                status = R.string.status11
            )
        )
        List.add(
            Listfarm(
                Id = 11,
                name = R.string.farma12,
                avaliacao = R.string.avaliacao12,
                endreco = R.string.endereco12,
                status = R.string.status12
            )
        )
        val adapter = Adapter(List )
        rvMain = findViewById(R.id.recyclerView)
        rvMain.adapter = adapter
        rvMain.layoutManager = LinearLayoutManager(this)

    }

    private inner class Adapter(val List: List<Listfarm>): RecyclerView.Adapter<Adapter.ViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view =  layoutInflater.inflate(R.layout.item_farmacia,parent,false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return List.size
        }

        @SuppressLint("SuspiciousIndentation")
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val corrent = List[position]
            holder.bilder(corrent)

        }


        inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
            fun bilder(item: Listfarm){
               val Nome_Farmacia:TextView = itemView.findViewById(R.id.Nome_Farmacia)
                val Avaliacao: TextView = itemView.findViewById(R.id.avaliacao)
                val Endereco: TextView = itemView.findViewById(R.id.Endereco)
                val Status: TextView = itemView.findViewById(R.id.Status)


                Nome_Farmacia.setText(item.name)
                Avaliacao.setText(item.avaliacao)
                Endereco.setText(item.endreco)
                Status.setText(item.status)


            }

        }

    }
}