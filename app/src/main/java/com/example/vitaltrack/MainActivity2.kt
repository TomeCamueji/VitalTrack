package com.example.vitaltrack

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
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
import androidx.recyclerview.widget.RecyclerView
import com.example.vitaltrack.Dicas.DicasAtivity
import com.example.vitaltrack.FARMAS.farmacia
import com.example.vitaltrack.Sus.SusAtivity
import com.example.vitaltrack.model.date
import com.example.vitaltrack.water.WaterActivity

class MainActivity2 : AppCompatActivity() {

    private lateinit var rvMain:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        val list = mutableListOf<date>()
        list.add(
            date(
                id = 1,
                drawableId = R.drawable.baseline_calculate_25,
                string = R.string.IMC,
//                color = R.drawable.backgrounicon
            )
        )
        list.add(
            date(
                id = 2,
                drawableId = R.drawable.baseline_health_and_safety_24,
                string = R.string.TMB,
//                color = R.color.azul_clar
            )
        )
        list.add(
            date(
                id = 3,
                drawableId = R.drawable.baseline_water_drop_24,
                string = R.string.ÁGUA,
//                color = R.drawable.backgrounicon
            )
        )
        list.add(
            date(
                id = 4,
                drawableId = R.drawable.baseline_location_on_24,
                string = R.string.FARM,
//                color = R.drawable.backgrounicon
            )
        )
        list.add(
            date(
                id = 5,
                drawableId = R.drawable.baseline_directions_run_25,
                string = R.string.DICAS,
//                color = R.drawable.backgrounicon
            )
        )
        list.add(
            date(
                id = 6,
                drawableId = R.drawable.baseline_gamepad_24,
                string = R.string.sus,
//                color = R.drawable.backgrounicon
            )
        )
        val adapter = MainAdapter(list){id ->
            when(id){
                1 ->{
                    val intent = Intent(this@MainActivity2, ImcAtivity::class.java)
                    startActivity(intent)
                }
                2 ->{
                    val intent = Intent(this@MainActivity2, TmbActivity::class.java)
                    startActivity(intent)
                }
                3 ->{
                    val intent = Intent(this@MainActivity2, WaterActivity::class.java)
                    startActivity(intent)
                }

                4 ->{
                    val intent = Intent(this@MainActivity2, farmacia::class.java)
                    startActivity(intent)
                }
                5 ->{
                    val intent = Intent(this@MainActivity2, DicasAtivity::class.java)
                    startActivity(intent)
                }
                6 ->{
                    val intent = Intent(this@MainActivity2, SusAtivity::class.java)
                    startActivity(intent)
                }


            }

        }
        rvMain = findViewById(R.id.rv_main)
        rvMain.adapter = adapter
        rvMain.layoutManager = GridLayoutManager(this,3)

    }

    private inner class MainAdapter(
        val list: List<date>,
        val onItemClicklistener: (Int) -> Unit,
    ): RecyclerView.Adapter<MainAdapter.MainViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val view =  layoutInflater.inflate(R.layout.list,parent,false)
            return MainViewHolder(view)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        @SuppressLint("SuspiciousIndentation")
        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val corrent = list[position]
            holder.bilder(corrent)

        }


        inner class MainViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
            fun bilder(item: date){
                val img:ImageView = itemView.findViewById(R.id.img_item)
                val container:LinearLayout = itemView.findViewById(R.id.Linear_item)
                val txtName:TextView = itemView.findViewById(R.id.txt_name)

                img.setImageResource(item.drawableId)
                txtName.setText(item.string)
//                container.setBackgroundColor(item.color)

                container.setOnClickListener {
                    onItemClicklistener.invoke(item.id)
                }

            }

        }

    }




}
