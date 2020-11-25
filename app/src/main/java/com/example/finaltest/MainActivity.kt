package com.example.finaltest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_insert.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    var userList = arrayListOf<Student>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.adapter = StudentsAdapter(this.userList, applicationContext)
        recycler_view.layoutManager = LinearLayoutManager(applicationContext)


    }

    override fun onResume() {
        super.onResume()
        callStudentdata()
    }

    fun callStudentdata() {
        userList.clear()
        val serv: StudentAPI = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StudentAPI::class.java)

        serv.retrieveStudent()
            .enqueue(object : Callback<List<Student>> {
                override fun onResponse(
                    call: Call<List<Student>>,
                    response: Response<List<Student>>
                ) {
                    response.body()?.forEach {
                        userList.add(Student(it.username, it.internet, it.month, it.price))
                    }

                    recycler_view.adapter = StudentsAdapter(userList, applicationContext)
                    text1.text = "List : " + userList.size.toString()
                }

                override fun onFailure(call: Call<List<Student>>, t: Throwable) {
                    return t.printStackTrace()
                }
            })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.item1 -> {
                val intent = Intent(this@MainActivity, InsertActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun addinfo(view: View) {
        val intent = Intent(this@MainActivity, InsertActivity::class.java)
        startActivity(intent)
    }
}