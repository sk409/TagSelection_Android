package com.websarva.wings.android.test

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_selected_tag_list_item.view.*

class SelectedTagRecyclerViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val chip = view.selectedTagChip
}