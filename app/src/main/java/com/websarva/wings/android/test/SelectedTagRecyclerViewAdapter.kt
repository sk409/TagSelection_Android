package com.websarva.wings.android.test

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SelectedTagRecyclerViewAdapter: RecyclerView.Adapter<SelectedTagRecyclerViewHolder>() {

    private val selectedTags: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectedTagRecyclerViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.fragment_selected_tag_list_item, parent, false)
        return SelectedTagRecyclerViewHolder(root)
    }

    override fun getItemCount(): Int {
        return selectedTags.size
    }

    override fun onBindViewHolder(holder: SelectedTagRecyclerViewHolder, position: Int) {
        holder.chip.text = selectedTags[position]
    }

    fun appendTag(tag: String) {
        selectedTags.add(tag)
        notifyItemInserted(selectedTags.lastIndex)
//        notifyDataSetChanged()
    }

}