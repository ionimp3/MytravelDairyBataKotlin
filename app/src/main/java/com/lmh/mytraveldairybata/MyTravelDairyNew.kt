package com.lmh.mytraveldairybata

//import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_my_travel_dairy_new.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class MyTravelDairyNew : AppCompatActivity()  {
    var _onlydate: LocalDate = LocalDate.now()
    var str_onlydate = _onlydate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    var _planname: EditText? = null
    var _plandepartday: EditText? = null
    var _plandays: EditText? = null
    var _testbtn: Button? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_travel_dairy_new)

        _planname = findViewById(R.id.editplanname) as EditText
        _plandepartday = findViewById(R.id.editplandepartday) as EditText
        _plandays = findViewById(R.id.editplandays) as EditText
        _testbtn = findViewById<Button>(R.id.testbtn)

        //String chg_onlydate = _plandepartday . getText ().toString()
        testView2?.text = "test --> $_onlydate"
        //_plandepartday?.text = "test --> $_onlydate"

        _testbtn!!.setOnClickListener { testenablebtn() }

        //_loginLink!!.setOnClickListener {
            // Finish the registration screen and return to the Login activity
            //val intent = Intent(applicationContext, LoginActivity::class.java)
            //startActivity(intent)
           // finish()
           // overridePendingTransition(com.kotlindroider.devaj.R.anim.push_left_in, com.kotlindroider.devaj.R.anim.push_left_out)
       // }
    }
    fun testenablebtn() {

        if (!validate()) {
            onEnablefail()
            return
        }

        _testbtn!!.isEnabled = false

       // val progressDialog = ProgressDialog(this@MyTravelDairyNew,
         //   com.kotlindroider.devaj.R.style.AppTheme_Dark_Dialog)
        //progressDialog.isIndeterminate = true
        //progressDialog.setMessage("Creating Account...")
        //progressDialog.show()

        val aplanname = _planname!!.text.toString()
        val aplandepartday = _plandepartday!!.text.toString()
        val aplandays =  _plandays!!.text.toString()


        // TODO: Implement your own signup logic here.
        onEnablesucess()
        //android.os.Handler().postDelayed(
        //    {
                // On complete call either onSignupSuccess or onSignupFailed
                // depending on success
        //        onSignupSuccess()
                // onSignupFailed();
        //       progressDialog.dismiss()
        //    }, 3000)
    }
    fun onEnablesucess() {
        _testbtn!!.isEnabled = true
        //setResult(MyTravelDairyNew.RESULT_OK, null)
        //finish()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    fun onEnablefail() {
        Toast.makeText(baseContext, "생성실패:계획명,날짜,일수를 확인해주세요", Toast.LENGTH_LONG).show()

        _testbtn!!.isEnabled = true
    }

    fun validate(): Boolean {
        var valid = true

        val aplanname = _planname!!.text.toString()
        val aplandepartday = _plandepartday!!.text.toString()
        val aplandays = _plandays!!.text.toString()


        if (aplanname.isEmpty() || aplanname.length > 13) {
            _planname!!.error = "13자리까지만 허용합니다."
            valid = false
        } else {
            _planname!!.error = null
        }

        if (aplandepartday.isEmpty() || aplanname.length > 10) {
            _plandepartday!!.error = "날짜를 선택해주세요."
            valid = false
        } else {
            _plandepartday!!.error = null
        }


        if (aplandays.isEmpty() || aplandays.length > 2 || aplandays == "0") {
            _plandays!!.error = "999일 이내 만 가능합니다"
            valid = false
        } else {
            _plandays!!.error = null
        }


        return valid
    }


}
