package jam.rs.wllmhllbngpp.ui

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import jam.rs.wllmhllbngpp.Model.AppModel
import jam.rs.wllmhllbngpp.R
import jam.rs.wllmhllbngpp.databinding.SquareBinding

class Adapter(private var items: List<AppModel>, var context : Context) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    private lateinit var dialogg: Dialog

    class ViewHolder(binding: SquareBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var bindings: SquareBinding = binding
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
                R.layout.square, parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = items[position]

        holder.bindIdea(data)

        holder.itemView.setOnClickListener {
            dialogg = Dialog(context)
            dialogg.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialogg.window!!.setGravity(Gravity.CENTER)
            dialogg.setContentView(R.layout.dialog)

            val sub = dialogg.findViewById<TextView>(R.id.titled)
            val des = dialogg.findViewById<TextView>(R.id.subtitled)

            sub.text = data.title
            des.text = data.desc

            dialogg.show()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
