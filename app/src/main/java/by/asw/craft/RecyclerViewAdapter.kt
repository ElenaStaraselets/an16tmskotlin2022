package by.asw.craft

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class RecyclerViewAdapter(
    context: Context?,
    values: List<DataModel>,
    itemListener: ItemListener?
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder?>() {
    var mValues: List<DataModel>
    lateinit var mContext: Context
    protected var mListener: ItemListener?

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        var textView: TextView
        var imageView: ImageView
        var relativeLayout: RelativeLayout
        var item: DataModel? = null
        fun setData(item: DataModel) {
            this.item = item
            textView.setText(item.text)
            imageView.setImageResource(item.drawable)
            relativeLayout.setBackgroundColor(Color.parseColor(item.color))
        }

        override fun onClick(view: View) {
            if (mListener != null) {
                mListener!!.onItemClick(item)
            }
        }

        init {
            v.setOnClickListener(this)
            textView = v.findViewById<View>(R.id.textView) as TextView
            imageView = v.findViewById<View>(R.id.imageView) as ImageView
            relativeLayout = v.findViewById<View>(R.id.relativeLayout) as RelativeLayout
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(mContext).inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(Vholder: ViewHolder, position: Int) {
        Vholder.setData(mValues[position])
    }

 //   val itemCount: Int
   //     get() = mValues.size

    interface ItemListener {
        fun onItemClick(item: DataModel?)
    }

    init {
        mValues = values
        if (context != null) {
            mContext = context
        }
        mListener = itemListener
    }

    override fun getItemCount(): Int {
        return  mValues.size
    }
}