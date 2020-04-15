package com.lmh.mytraveldairybata


import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_my_travel_dairy_new.*
import org.jetbrains.anko.toast
import java.util.*


class MyTravelDairyNew : AppCompatActivity() {
    var _planname: EditText? = null
    var _plandepartday: EditText? = null
    var _plandays: EditText? = null
    var _createplanbtn: Button? = null


    //lateinit var editText: EditText


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_travel_dairy_new)

        _planname = findViewById(R.id.editplanname) as EditText
        _plandepartday = findViewById(R.id.editplandepartday) as EditText
        _plandays = findViewById(R.id.editplandays) as EditText
        _createplanbtn = findViewById<Button>(R.id.createplanbtn)


        //키보드내리기
        CloseKeyboard()


        //날짜입력 edit text에 초기값이 오늘일자로 입력하기
        //_todayDate()


        _createplanbtn!!.setOnClickListener { createenablebtn() }

        //_loginLink!!.setOnClickListener {
        // Finish the registration screen and return to the Login activity
        //val intent = Intent(applicationContext, LoginActivity::class.java)
        //startActivity(intent)
        // finish()
        // overridePendingTransition(com.kotlindroider.devaj.R.anim.push_left_in, com.kotlindroider.devaj.R.anim.push_left_out)
        // }
    }

    //
    fun CloseKeyboard() {
        var view = this.currentFocus

        if (view != null) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }


    //fun _todayDate() {


    //   var onlydate: LocalDate = LocalDate.now()
    //   var onlydateformatter = DateTimeFormatter.ofPattern("yyyyMMdd")
    //   var onlydateformatted = onlydate.format(onlydateformatter)

    //   var todaydate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    //    _plandepartday?.setText(onlydateformatted)
    //}

    fun createenablebtn() {

        if (!validate()) {
            onEnablefail()
            return
        }


        _createplanbtn!!.isEnabled

        val progressDialog = ProgressDialog(this@MyTravelDairyNew)
        //progressDialog.isIndeterminate = true
        //progressDialog.setMessage("Creating New Plan...")
        //progressDialog.setCancelable(false)
        //progressDialog.show()

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


    //


    fun onEnablesucess() {
        _createplanbtn!!.isEnabled = true
        CloseKeyboard()

        Toast.makeText(
            baseContext,
            "입력하신 여행명 ,  출발일 , 여행기간 을  기준으로 여행다이어리를 생성합니다...",
            Toast.LENGTH_LONG
        )
            .show()

        //3초후 프로세스 진행 다이얼로그 표시 및 3초동안 화면에 띄우고 사라진다.
        Handler().postDelayed({
            _myprogressBar.visibility = View.VISIBLE

        }, 3000)

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()

        }, 10000)

    }

    fun onEnablefail() {
        CloseKeyboard()
        Toast.makeText(baseContext, "입력이 올바르지 않습니다. 다시 확인해주세요", Toast.LENGTH_LONG).show()

        _createplanbtn!!.isEnabled = true
    }

    //여행명 자릿수 13자리(실제동작할일없음, 예비용) , 실제 달력에 존재 유무 확인 및 yyyymmdd 포맷인지 확인
    fun validate(): Boolean {
        var valid = true
        val aplanname = _planname!!.text.toString()
        val aplandepartday = _plandepartday!!.text.toString()
        val aplandays = _plandays!!.text.toString()
        val cal = Calendar.getInstance()


        if (aplanname.isEmpty() || aplanname.length > 13) {
            _planname!!.error = "13자리까지만 허용합니다."
            valid = false
        } else {
            _planname!!.error = null
        }

        if (aplandepartday.isEmpty() || aplandepartday.length < 8) {
            _plandepartday!!.error = "날짜를 YYYYMMDD 형태로 입력해주세요."
            valid = false
        } else {

            // 실제 달력에 존재하는 일자 인지 검출
            var _year = aplandepartday.substring(startIndex = 0, endIndex = 4)
            var _mon = aplandepartday.substring(startIndex = 4, endIndex = 6)
            var _day = aplandepartday.substring(startIndex = 6, endIndex = 8)
            var year: Int = _year.toInt()
            var mon: Int = _mon.toInt()
            var day: Int = _day.toInt()
            if ((year < 1980) || (year > 2100)) {
                _plandepartday!!.error = "1900~2100년까지만 설정이 가능합니다."
                valid = false
            } else {
                if ((mon < 1) || (mon > 12)) {
                    _plandepartday!!.error = "입력한 월이 $mon 잘못되었읍니다."
                    valid = false
                } else {
                    cal.set(Calendar.MONTH, mon - 1)
                    cal.set(Calendar.YEAR, year)
                    val maxday: Int = cal.getActualMaximum(Calendar.DATE)
                    if (day > maxday) {
                        _plandepartday!!.error = "입력일자 $mon 월은 $maxday 까지입니다."
                        valid = false
                    } else {
                        _plandepartday!!.error = null
                        //_plandepartday!!.error = null
                    }
                }
            }
        }

        if (aplandays.isEmpty() || aplandays.length > 3) {
            _plandays!!.error = "1~999일까지만 허용됩니다."
            valid = false
        } else {
            _plandays!!.error = null
        }

        return valid
    }


}
