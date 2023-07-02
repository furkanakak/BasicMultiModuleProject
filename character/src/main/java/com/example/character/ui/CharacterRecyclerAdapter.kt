package com.example.character.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.character.R
import com.example.character.databinding.ItemCharacterListBinding
import com.example.core.data.entity.character.Result

class CharacterRecyclerAdapter : RecyclerView.Adapter<CharacterRecyclerAdapter.CharacterViewHolder>() {
    private lateinit var characterList :ArrayList<Result>
    private lateinit var onClick : (item : Result) -> Unit

    fun setCharacters(characterList :ArrayList<Result>){
        this.characterList = characterList
    }
    fun setCharacterOnclick(onClick : (item : Result) -> Unit){
        this.onClick = onClick
    }



    inner class CharacterViewHolder(private val binding: ItemCharacterListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(character : Result, context : Context){
            binding.apply {
                characterImage.load(character.image)
                characterStatusText.text = character.status
                characterGender.text = character.gender
                characterName.text  = character.name
                characterOrigin.text = character.origin.name

                when(character.status){
                    "Alive" -> {
                        characterStatusCardViewImage.setCardBackgroundColor(ContextCompat.getColor(context, R.color.aliveColor))
                    }
                    "Dead" ->{
                        characterStatusCardViewImage.setCardBackgroundColor(ContextCompat.getColor(context, R.color.deadColor))
                    }
                    else ->{
                        characterStatusCardViewImage.setCardBackgroundColor(ContextCompat.getColor(context, R.color.unKnownColor))
                    }
                }

                container.setOnClickListener {
                    onClick(character)
                }



            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CharacterViewHolder(binding)
    }

    override fun getItemCount() = characterList.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {

        holder.bind(characterList[position],holder.itemView.context)
    }



}