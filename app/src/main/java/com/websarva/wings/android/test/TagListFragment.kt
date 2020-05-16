package com.websarva.wings.android.test

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_tag_list.*
import kotlinx.android.synthetic.main.fragment_tag_list.view.*

class TagListFragment(private val tags: MutableList<Tag>, private val listItemClickListener: (Tag) -> Unit): Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_tag_list, container, false)
        val adapter = activity?.applicationContext?.let { it -> ArrayAdapter(it, android.R.layout.simple_list_item_1, tags) }
        root.tagListView.adapter = adapter
        root.tagListView.setOnItemClickListener { parent, view, position, id ->
            listItemClickListener(tags[position])
        }
        return root
    }

}
