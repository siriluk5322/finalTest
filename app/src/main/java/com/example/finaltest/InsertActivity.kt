package com.example.finaltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_insert.*
import kotlinx.android.synthetic.main.std_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InsertActivity : AppCompatActivity() {
    var month : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        ////Set Spinner
        val majorSpinner: Spinner = spinner;
        val majorArray = resources.getStringArray(R.array.array)

        val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, majorArray)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        majorSpinner.adapter = arrayAdapter

        majorSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                month = majorArray[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    fun add(view: View) {
       var totalprice : Int = 0
        val serv: StudentAPI = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StudentAPI::class.java)
        var radioGroup: RadioGroup = findViewById(R.id.radioGroup)
        var selectedId : Int = radioGroup.checkedRadioButtonId;
        var radioButton : RadioButton = findViewById(selectedId);
        if(radioButton.text.toString() == "Pro 500"){
           totalprice = month.toInt() * 500
        }
        if(radioButton.text.toString() == "Normal  200"){
            totalprice = month.toInt() * 200
        }

        serv.insertStd(
            edt_name.text.toString(),
            radioButton.text.toString(),
            month.toInt(),
            price = totalprice).enqueue(object : Callback<Student> {
            override fun onResponse(call: Call<Student>, response: Response<Student>) {
                if (response.isSuccessful()){
                    Toast.makeText(applicationContext,"SuccessFully Inserted", Toast.LENGTH_LONG).show()
                    finish()
                }else{
                    Toast.makeText(applicationContext,"Error", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Student>, t: Throwable) {
                Toast.makeText(applicationContext,"Error onFailure "+ t.message, Toast.LENGTH_LONG).show()
            }
        })

    }

    fun reset(view: View) {
        edt_name.text.clear()
        radioGroup.clearCheck()
        spinner.setSelection(0)
    }
}