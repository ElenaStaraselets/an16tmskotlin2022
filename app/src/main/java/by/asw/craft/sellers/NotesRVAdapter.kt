package by.asw.craft.sellers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.asw.craft.R
import by.asw.craft.db.SearchRequest

class NotesRVAdapter : ListAdapter<SearchRequest, NotesRVAdapter.NoteHolder>(DiffCallback()) {

    class NoteHolder(view: View) : RecyclerView.ViewHolder(view)

    private lateinit var listener: RecyclerClickListener
    fun setItemListener(listener: RecyclerClickListener) {
        this.listener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.notes_row, parent, false)
        val noteHolder = NoteHolder(v)

        val noteDelete = noteHolder.itemView.findViewById<ImageView>(R.id.note_delete)
        noteDelete.setOnClickListener {
            listener.onItemRemoveClick(noteHolder.adapterPosition)
        }

        val note = noteHolder.itemView.findViewById<CardView>(R.id.note)
        note.setOnClickListener {
            listener.onItemClick(noteHolder.adapterPosition)
        }

        return noteHolder
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val currentItem = getItem(position)
        val noteText = holder.itemView.findViewById<TextView>(R.id.note_text)
        noteText.text = currentItem.noteText
    }

    class DiffCallback : DiffUtil.ItemCallback<SearchRequest>() {
        override fun areItemsTheSame(oldItem: SearchRequest, newItem: SearchRequest) =
            oldItem.dateAdded == newItem.dateAdded

        override fun areContentsTheSame(oldItem: SearchRequest, newItem: SearchRequest) =
            oldItem == newItem
    }
}