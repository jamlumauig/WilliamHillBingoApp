package jam.rs.wllmhllbngpp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import jam.rs.wllmhllbngpp.Model.AppModel
import jam.rs.wllmhllbngpp.R
import jam.rs.wllmhllbngpp.databinding.RecyclerBinding

class Adapter2(var mainlist: ArrayList<AppModel>) :
    RecyclerView.Adapter<Adapter2.ViewHolder>() {

    class ViewHolder(binding: RecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var bindings: RecyclerBinding = binding
        fun bindIdea(dataPor: AppModel) {
            itemView.apply {
                bindings.title.text = dataPor.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.recycler, parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = mainlist[position]
        //+val desc = desc[position]

        holder.bindIdea(data)
        holder.bindings.subtitled.text = data.desc

        holder.bindings.subtitled.setOnClickListener {
            if (holder.bindings.subtitled.maxLines == 6) {
                holder.bindings.subtitled.maxLines = Int.MAX_VALUE
            } else {
                holder.bindings.subtitled.maxLines = 6
            }
        }
    }

    override fun getItemCount(): Int {
        return mainlist.size
    }
}
