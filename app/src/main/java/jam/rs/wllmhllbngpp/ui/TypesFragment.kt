package jam.rs.wllmhllbngpp.ui

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import jam.rs.wllmhllbngpp.Model.AppModel
import jam.rs.wllmhllbngpp.R
import jam.rs.wllmhllbngpp.database.FirebaseDB
import jam.rs.wllmhllbngpp.databinding.FragmentTypesBinding

class TypesFragment : Fragment() {

    private var _binding: FragmentTypesBinding? = null
    private val binding get() = _binding!!

    private var notesModel = ArrayList<AppModel>()

    var fb = FirebaseDB()
    var gson = Gson()
    lateinit var item: DataSnapshot

    private lateinit var rview: RecyclerView
    lateinit var arrayAdapter: Adapter2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTypesBinding.inflate(inflater, container, false)
        initialization()
        return binding.root
    }

    private fun initialization(){
        getData()

        rview = binding.data
        rview.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL,
            false
        )
    }

    fun getData() {
        notesModel.clear()
        fb.getTypes().addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                item = snapshot
                for (item in snapshot.children) {
                    val data: AppModel? = item.getValue(AppModel::class.java)
                    if (data != null) {
                        notesModel.add(data)
                        Log.d("GetData: ", gson.toJson(data).toString())
                        arrayAdapter =
                            Adapter2(notesModel)
                        rview.adapter = arrayAdapter
                        arrayAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}