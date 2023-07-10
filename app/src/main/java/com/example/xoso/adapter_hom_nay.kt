package com.example.xoso

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class adapter_hom_nay(val activity: Activity, var list: List<list_hom_nay>) :
    ArrayAdapter<list_hom_nay>(activity, R.layout.list_hom_nay) {
    override fun getCount(): Int {
        return list.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val context = activity.layoutInflater
        val rowView = context.inflate(R.layout.list_hom_nay, parent, false)

        val image = rowView.findViewById<ImageView>(R.id.imageView2)
        val title = rowView.findViewById<TextView>(R.id.textView20)
        val desc = rowView.findViewById<TextView>(R.id.textView21)

        title.text = list[position].title
        desc.text = list[position].desc
        image.setImageResource(list[position].image)

        return rowView
    }

    fun updateData(newList: List<list_hom_nay>) {
        list = newList
        notifyDataSetChanged()
    }
}
