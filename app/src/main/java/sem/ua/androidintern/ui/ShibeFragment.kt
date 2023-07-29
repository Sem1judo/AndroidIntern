package sem.ua.androidintern.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sem.ua.androidintern.R
import sem.ua.androidintern.adapter.ShibeAdapter
import sem.ua.androidintern.retrofit.ShibeService


class ShibeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ShibeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_shibe, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        fetchData()
    }

    private fun setupRecyclerView() {
        adapter = ShibeAdapter()
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = adapter
    }

    private fun fetchData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://shibe.online/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ShibeService::class.java)

        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = service.getShibes(count = 10, urls = true, httpsUrls = true)
                if (response.isSuccessful) {
                    val shibes = response.body()
                    shibes?.let { shibeUrls ->
                        requireActivity().runOnUiThread {
                            adapter.submitList(shibeUrls)
                        }
                    }
                } else {
                    // Handle error here if needed
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}