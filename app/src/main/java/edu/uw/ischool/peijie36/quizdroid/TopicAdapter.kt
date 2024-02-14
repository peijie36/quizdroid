package edu.uw.ischool.peijie36.quizdroid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class TopicAdapter(context: Context, resource: Int, objects: List<Pair<String, String>>) :
    ArrayAdapter<Pair<String, String>>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }

        val item = getItem(position)

        val titleTextView = view!!.findViewById<TextView>(R.id.txt_title)
        val descriptionTextView = view.findViewById<TextView>(R.id.txt_short_description)

        titleTextView.text = item?.first
        descriptionTextView.text = item?.second

        return view
    }
}