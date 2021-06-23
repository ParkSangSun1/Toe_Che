package com.children.toyexchange.ui.view.signIn

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.children.toyexchange.R
import com.children.toyexchange.databinding.ActivitySignInBinding
import com.children.toyexchange.ui.utils.MainObject
import com.children.toyexchange.ui.view.base.BaseActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.GoogleAuthProvider

class SignInActivity : BaseActivity() {
    val binding by binding<ActivitySignInBinding>(R.layout.activity_sign_in)
    val GOOGLE_REQUEST_CODE = 1004
    val TAG = "googleLogin"
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activity = this




//GoogleSignInClient 객체 초기화
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN) //기본 로그인 방식 사용
            .requestIdToken(getString(R.string.default_web_client_id))
            //requestIdToken :필수사항이다. 사용자의 식별값(token)을 사용하겠다.
            //(App이 구글에게 요청)
            .requestEmail()
            // 사용자의 이메일을 사용하겠다.(App이 구글에게 요청)
            .build()

// 내 앱에서 구글의 계정을 가져다 쓸거니 알고 있어라!
        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    fun googleLoginBtn(view: View) {

        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, GOOGLE_REQUEST_CODE)
    }

    //startAcivityResult()로 인해 다른 앱/ 액티비티가 실행된 후.
//그 앱/액티비티의 작업이 끝나서 다시 이 액티비티로 돌아왔을 떄 할일
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //Activity.Result_OK : 정상완료
        //Activity.Result_CANCEL : 중간에 취소 되었음(실패)
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1004) {
            if (resultCode == Activity.RESULT_OK) {
                //결과 Intent(data 매개변수) 에서 구글로그인 결과 꺼내오기
                val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)!!

                //정상적으로 로그인되었다면
                if (result.isSuccess) {
                    //우리의 Firebase 서버에 사용자 이메일정보보내기
                    val account = result.signInAccount
                    firebaseAuthWithGoogle(account)
                }
            }
        }
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount?) {
        //구글로부터 로그인된 사용자의 정보(Credentail)을 얻어온다.
        val credential = GoogleAuthProvider.getCredential(account?.idToken!!, null)
        //그 정보를 사용하여 Firebase의 auth를 실행한다.
        MainObject.auth?.signInWithCredential(credential)
            ?.addOnCompleteListener {  //통신 완료가 된 후 무슨일을 할지
                    task ->
                if (task.isSuccessful) {
                    loginSuccess()
                } else {
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun loginSuccess() {
        val intent = Intent(this, PhoneAuthActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.right_in, R.anim.left_out)
        finish()
    }
}