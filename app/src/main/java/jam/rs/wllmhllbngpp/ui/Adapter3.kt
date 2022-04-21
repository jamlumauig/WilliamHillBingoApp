package jam.rs.wllmhllbngpp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import jam.rs.wllmhllbngpp.Model.AppModel
import jam.rs.wllmhllbngpp.R
import jam.rs.wllmhllbngpp.databinding.RecyclerBinding
import jam.rs.wllmhllbngpp.databinding.TextviewBinding

class Adapter3(var mainlist: ArrayList<AppModel>) :
    RecyclerView.Adapter<Adapter3.ViewHolder>() {

    class ViewHolder(binding: TextviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var bindings: TextviewBinding = binding
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
                R.layout.textview, parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = mainlist[position]

        holder.bindIdea(data)
        holder.bindings.subtitled.text = data.desc
    }

    override fun getItemCount(): Int {
        return mainlist.size
    }
}
