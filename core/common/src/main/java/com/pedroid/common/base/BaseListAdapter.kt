import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout.LayoutParams
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseListAdapter<T : Any, VB : ViewBinding> :
    ListAdapter<T, BaseListAdapter.ViewBindingVH<VB>>(BaseDiffUtil<T>()) {

    class ViewBindingVH<VB : ViewBinding> constructor(val binding: VB) :
        RecyclerView.ViewHolder(binding.root)

    private class BaseDiffUtil<T : Any> : DiffUtil.ItemCallback<T>() {

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewBindingVH<VB> {
        val view = inflateView(LayoutInflater.from(parent.context), viewType)
        return ViewBindingVH(view)
    }

    override fun onBindViewHolder(holder: ViewBindingVH<VB>, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            bind(this, position, item)
            root.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        }
    }

    abstract fun inflateView(inflater: LayoutInflater, viewType: Int): VB

    abstract fun bind(binding: VB, position: Int, item: T)

    abstract fun getViewType(item: T): Int
}