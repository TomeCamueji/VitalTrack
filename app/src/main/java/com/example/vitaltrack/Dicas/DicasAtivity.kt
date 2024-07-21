package com.example.vitaltrack.Dicas

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vitaltrack.FARMAS.Listfarm
import com.example.vitaltrack.R

class DicasAtivity : AppCompatActivity() {

    private lateinit var rvMain:RecyclerView
    @SuppressLint("MissingInflatedId", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_dicas_ativity)

        val listdica = mutableListOf<ListDica>()
        listdica.add(
            ListDica(
                id = 1,
                string = R.string.text_titleDica,
                drawable = R.drawable.obesidade,
                string1 = R.string.text2
            )
        )
        listdica.add(
            ListDica(
                id = 2,
                string = R.string.text_titleDica,
                drawable = R.drawable.notici1,
                string1 = R.string.text2
            )
        )
        listdica.add(
            ListDica(
                id = 3,
                string = R.string.titlo3,
                drawable = R.drawable.obesidade_tratamento,
                string1 = R.string.text3
            )
        )
        listdica.add(
            ListDica(
                id = 4,
                string = R.string.titlo4,
                drawable = R.drawable.not2,
                string1 = R.string.text4
            )
        )
        val adapter = MainAdapter(listdica)
        rvMain = findViewById(R.id.rv_Main)
        rvMain.adapter = adapter
        rvMain.layoutManager = LinearLayoutManager(this)


    }

    private inner class MainAdapter(val listdica: List<ListDica>):RecyclerView.Adapter<MainViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val item = layoutInflater.inflate(R.layout.noticias,parent,false)
            return MainViewHolder(item)
        }

        override fun getItemCount(): Int {
            return listdica.size
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            val dica = listdica[position]
            holder.bild(dica)
        }

    }
    private inner class MainViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        fun bild(item:ListDica){
            val Texttitle:TextView = itemView.findViewById(R.id.text_title_dica)
            val imgDicas:ImageView = itemView.findViewById(R.id.img_dica)
            val text_title_dica2:TextView = itemView.findViewById(R.id.text_title_dica2)

            Texttitle.setText(item.string)
            imgDicas.setImageResource(item.drawable)
            text_title_dica2.setText(item.string1)
        }
    }

}