package pepe.com.is3261.tutoria6

import android.content.Context
import android.os.AsyncTask
import android.widget.TextView
import android.widget.Toast
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class JSONParser(private var c: Context, private var jsonData: String,
                 private var myTextView: TextView): AsyncTask<Void, Void, Boolean>() {

    private var users = ArrayList<User>()

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg voids: Void): Boolean? {
        return parse()
    }

    override fun onPostExecute(isParsed: Boolean?) {
        super.onPostExecute(isParsed)

        if (isParsed!!) {

            myTextView.text = "parse successful -- " + users.size + " " + users[users.size-1].getName() + " " + users[users.size-1].getEmail() + " " +
                    users[users.size-1].getUserName()

        } else {
            Toast.makeText(c, "Unable to Parse that data.", Toast.LENGTH_LONG).show()
            Toast.makeText(c, "This is the data that we are trying to parse : " + jsonData,
                    Toast.LENGTH_LONG).show()
        }
    }

    private fun parse(): Boolean {
        try {
            val ja = JSONArray(jsonData)
            var jo : JSONObject

            users.clear()
            var user: User

            for (i in 0 until ja.length()) {
                jo = ja.getJSONObject(i)

                val name = jo.getString("name")
                val username = jo.getString("username")
                val email = jo.getString("email")

                user = User(username, name, email)
                users.add(user)
            }
            return true
        } catch (e: JSONException) {
            e.printStackTrace()
            return false
        }
    }

    class User(private var m_username: String, private var m_name: String,
               private var m_email: String) {

        fun getUserName(): String {
            return m_username
        }

        fun getName(): String {
            return m_name
        }

        fun getEmail(): String {
            return m_email
        }
    }

}