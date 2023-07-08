package com.project.edusync

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.edusync.databinding.ItemHwBinding

class HomeworkAdapter (
    private var list: MutableList<Homework>,
    private val onItemClick: (Homework) -> Unit,
) : RecyclerView.Adapter<HomeworkItem>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeworkItem = HomeworkItem(
        ItemHwBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        onItemClick = onItemClick
    )


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: HomeworkItem, position: Int) {
        holder.onBind(list[position])
    }

}