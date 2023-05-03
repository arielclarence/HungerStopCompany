package id.sc.proyek
import android.os.Parcelable
import android.widget.TextView
import kotlinx.parcelize.Parcelize

@Parcelize
class Rank (var username : String,
            var totaldonasi : Int
) : Parcelable {

}