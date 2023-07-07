package com.project.edusync


import androidx.recyclerview.widget.RecyclerView
import com.project.edusync.databinding.ItemSubjectBinding

class SubjectItem (
    private val binding: ItemSubjectBinding,
    private val onItemClick: (Subject) -> Unit,
    private val name:(Subject) -> Unit,
    private val aud:(Subject) -> Unit,
    private val start:(Subject) -> Unit,
    private val end:(Subject) -> Unit
    ): RecyclerView.ViewHolder(binding.root){

        fun onBind(subject: Subject){
            name(subject)
            aud(subject)
            start(subject)
            end(subject)
            binding.run {
                root.setOnClickListener{
                    onItemClick(subject)
                }
            }
        }
}