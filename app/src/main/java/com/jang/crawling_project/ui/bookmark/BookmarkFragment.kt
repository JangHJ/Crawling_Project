package com.jang.crawling_project.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jang.crawling_project.R
import com.jang.crawling_project.databinding.FragmentBookmarkBinding

class BookmarkFragment : Fragment() {

    private var _binding: FragmentBookmarkBinding? = null
    private lateinit var recyclerView: RecyclerView // RecyclerView 추가

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bookmarkViewModel =
            ViewModelProvider(this).get(BookmarkViewModel::class.java)

        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recyclerView = root.findViewById(R.id.recycler_view_book) // RecyclerView 초기화

        // RecyclerView 레이아웃 매니저 설정 (LinearLayoutManager 사용)
        recyclerView.layoutManager = LinearLayoutManager(context)

        bookmarkViewModel.text.observe(viewLifecycleOwner) {
            // 여기에서 데이터를 RecyclerView에 표시하는 코드를 작성
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}