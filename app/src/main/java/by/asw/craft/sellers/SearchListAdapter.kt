package by.asw.craft.sellers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.asw.craft.databinding.SearchHistoryViewItemBinding
import by.asw.craft.db.SearchRequest
import by.asw.craft.network.CatalogItem

/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 * @param onClick a lambda that takes the
 */
class SearchListAdapter(val onClickListener: OnClickListener) :
    ListAdapter<SearchRequest, SearchListAdapter.SearchItemViewHolder>(DiffCallback) {


    class SearchItemViewHolder(private var binding: SearchHistoryViewItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SearchRequest) {
            binding.property = item
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<SearchRequest>() {
        override fun areItemsTheSame(oldItem: SearchRequest, newItem: SearchRequest): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: SearchRequest, newItem: SearchRequest): Boolean {
            return oldItem.dateAdded == newItem.dateAdded
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): SearchItemViewHolder {
        return SearchItemViewHolder(SearchHistoryViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
        holder.bind(item)
    }

    /**
     * Custom listener that handles clicks on [RecyclerView] items.  Passes the [SearchRequest]
     * associated with the current item to the [onClick] function.
     * @param clickListener lambda that will be called with the current [SearchRequest]
     */
    class OnClickListener(val clickListener: (item:SearchRequest) -> Unit) {
        fun onClick(item:SearchRequest) = clickListener(item)
    }
}