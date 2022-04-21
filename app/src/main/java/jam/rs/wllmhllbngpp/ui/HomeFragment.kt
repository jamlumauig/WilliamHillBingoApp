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
import jam.rs.wllmhllbngpp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    var fb = FirebaseDB()
    var gson = Gson()
    lateinit var item: DataSnapshot

    private var notesModel = ArrayList<AppModel>()

    private lateinit var rview: RecyclerView
    lateinit var arrayAdapter: Adapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        rview = binding.data
        rview.layoutManager = GridLayoutManager(
            context, 2, GridLayoutManager.VERTICAL,
            false
        )
        binding.title.setText(R.string.title_home)
        getData()
        return binding.root

    }

    fun getData() {
        notesModel.clear()
        fb.getQuestions().addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                item = snapshot
                for (item in snapshot.children) {
                    val data: AppModel? = item.getValue(AppModel::class.java)
                    if (data != null) {
                        notesModel.add(data)
                        Log.d("GetData: ", gson.toJson(data).toString())
                        arrayAdapter =
                            Adapter(notesModel , requireContext())
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