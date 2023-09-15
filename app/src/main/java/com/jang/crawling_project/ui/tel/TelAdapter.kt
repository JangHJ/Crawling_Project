import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jang.crawling_project.R

data class Contact(
    val department: String,
    val position: String,
    val phoneNumber: String
)
class TelAdapter(private val contactList: List<Contact>) :
    RecyclerView.Adapter<TelAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val departmentTextView: TextView = itemView.findViewById(R.id.text_department)
        val positionTextView: TextView = itemView.findViewById(R.id.text_position)
        val phoneNumberTextView: TextView = itemView.findViewById(R.id.text_phone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.tel_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contactList[position]
        holder.departmentTextView.text = contact.department
        holder.positionTextView.text = contact.position
        holder.phoneNumberTextView.text = contact.phoneNumber
    }

    override fun getItemCount(): Int {
        return contactList.size
    }
}
