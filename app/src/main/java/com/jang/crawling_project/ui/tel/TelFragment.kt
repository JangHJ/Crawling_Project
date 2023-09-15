package com.jang.crawling_project.ui.tel

import Contact
import TelAdapter
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
import com.jang.crawling_project.databinding.FragmentTelBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class TelFragment : Fragment() {

    private var _binding: FragmentTelBinding? = null
    private lateinit var recyclerView: RecyclerView // RecyclerView 추가

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val telViewModel =
            ViewModelProvider(this).get(TelViewModel::class.java)

        _binding = FragmentTelBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recyclerView = root.findViewById(R.id.recycler_view_tel) // RecyclerView 초기화

        // RecyclerView 레이아웃 매니저 설정 (LinearLayoutManager 사용)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // 백그라운드 스레드에서 parsingTel() 함수 호출
        GlobalScope.launch(Dispatchers.IO) {
            val contactList = parsingTel()

            // 메인 스레드에서 RecyclerView 어댑터 설정
            withContext(Dispatchers.Main) {
                val adapter = TelAdapter(contactList)
                recyclerView.adapter = adapter
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parsingTel(): List<Contact> {
        // 웹 페이지를 가져오고 파싱
        val url = "https://www.swu.ac.kr/www/camd.html"
        val doc: Document = Jsoup.connect(url).get()

        // 전화번호부 데이터를 저장할 리스트 생성
        val contactList = mutableListOf<Contact>()

        // h1 태그 아래의 department 데이터 수집
        val departments = doc.select("h1")
        for (department in departments) {
            var departmentName = department.text()

            // department 아래의 tt2 확인
            val tt2 = department.nextElementSibling()
            if (tt2 != null) {
                // tt2가 있다면 department 뒤에 붙이기
                departmentName += tt2.text()
            }

            // department 아래의 table 수집
            val tables = department.select("table")
            for (table in tables) {
                // table 안의 td.label와 td 데이터 수집
                val labels = table.select("td.label")
                val values = table.select("td:not(.label)")

                for (i in labels.indices) {
                    val position = labels[i].text()
                    val phoneNumber = values[i].text()

                    // Contact 객체 생성 및 리스트에 추가
                    val contact = Contact(departmentName, position, phoneNumber)
                    contactList.add(contact)
                }
            }
        }

        return contactList
    }
}
