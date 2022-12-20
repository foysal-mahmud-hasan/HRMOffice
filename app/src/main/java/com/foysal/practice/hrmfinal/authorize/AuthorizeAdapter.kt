package com.foysal.practice.hrmfinal.authorize

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.foysal.practice.hrmfinal.R
import com.foysal.practice.hrmfinal.database.ExceptionDao
import com.foysal.practice.hrmfinal.database.ExceptionData

class AuthorizeAdapter : RecyclerView.Adapter<AuthorizeAdapter.ViewHolder>() {

    var data = listOf<ExceptionData>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return data.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
    class ViewHolder private constructor(itemView : View) :
            RecyclerView.ViewHolder(itemView){

            val tvName : TextView = itemView.findViewById(R.id.tv_card_name)
            val tvId : TextView = itemView.findViewById(R.id.tv_card_id)
            val tvStatus : TextView = itemView.findViewById(R.id.tv_card_status)

            fun bind(item : ExceptionData){
                tvName.text = item.userName
                tvId.text = item.userExceptionId
                tvStatus.text = item.userStatus
            }

        companion object{

            fun from(parent: ViewGroup) : ViewHolder{

                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.card_design,
                parent, false)

                return ViewHolder(view)

            }

        }
    }
}