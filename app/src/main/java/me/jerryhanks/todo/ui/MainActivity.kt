/**
 * @author jerry on 26/07/2020
 * for Todo
 **/
package me.jerryhanks.todo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import me.jerryhanks.todo.R
import me.jerryhanks.todo.core.ui.BaseActivity
import me.jerryhanks.todo.nav.Navigable
import me.jerryhanks.todo.nav.Navigator
import net.openid.appauth.AuthState
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationResponse
import net.openid.appauth.AuthorizationService
import net.openid.appauth.TokenResponse
import timber.log.Timber
import javax.inject.Inject

interface Analytics {
    fun analyse(from: String, context: Context): String
}

class AnalyticsAdapter @Inject constructor() :
    Analytics {
    override fun analyse(from: String, context: Context): String {
        return "Hi there, I am analysing $from as you can see I am here : ${context.applicationContext.packageName}"
    }
}


@AndroidEntryPoint
class MainActivity : BaseActivity(), Navigable {
    @Inject
    lateinit var analyticsService: Analytics

//    private val mainVM by viewModels<MainActivityViewModel>()

    private lateinit var navigator: Navigator

    private val navController by lazy { findNavController(R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigator = Navigator(this, this)

//        Timber.d("$navigator")

        Timber.d("${analyticsService.analyse("MainActivity", this)}")

        // invoke network call
//        mainVM.getTodos()

        navigator.navigate()

        bottomNavigationView.setupWithNavController(navController)


//        val tasksScope = Scope("https://www.googleapis.com/auth/tasks")
//        val emailScope = Scope(Scopes.EMAIL)
//
//        val serviceConfiguration =
//            AuthorizationServiceConfiguration(
//                Uri.parse("https://accounts.google.com/o/oauth2/v2/auth") /* auth endpoint */,
//                Uri.parse("https://www.googleapis.com/oauth2/v4/token") /* token endpoint */
//            )
//
//
//        val clientId = "157892065474-koqe2hjg6q0usj9ljeeq1kv7nk29o0iv.apps.googleusercontent.com"
//        val redirectUri =
//            Uri.parse("me.jerryhanks.todos:/oauth2callback")
//
//        val builder = AuthorizationRequest.Builder(
//            serviceConfiguration,
//            clientId,
//            AuthorizationRequest.RESPONSE_TYPE_CODE,
//            redirectUri
//        )
//        builder.setScopes("profile", tasksScope.scopeUri, emailScope.scopeUri)
//
//        val request = builder.build()
//        val authorizationService = AuthorizationService(this)
//
//        val action = "me.jerryhanks.todo.HANDLE_AUTHORIZATION_RESPONSE"
//        val postAuthorizationIntent = Intent(action)
//        val pendingIntent = PendingIntent.getActivity(
//            this,
//            request.hashCode(),
//            postAuthorizationIntent,
//            0
//        )
//        authorizationService.performAuthorizationRequest(request, pendingIntent)

    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        checkIntent(intent)
    }

    private fun checkIntent(intent: Intent?) {
        if (intent != null) {
            when (intent.action) {
                "me.jerryhanks.todo.HANDLE_AUTHORIZATION_RESPONSE" -> if (!intent.hasExtra(
                        USED_INTENT
                    )
                ) {
                    handleAuthorizationResponse(intent)
                    intent.putExtra(USED_INTENT, true)
                }
                else -> {
                }
            }
        }
    }

    /**
     * Exchanges the code, for the [TokenResponse].
     *
     * @param intent represents the [Intent] from the Custom Tabs or the System Browser.
     */
    private fun handleAuthorizationResponse(intent: Intent) {
        val response = AuthorizationResponse.fromIntent(intent)
        val error = AuthorizationException.fromIntent(intent)
        val authState = AuthState(response, error)
        if (response != null) {
            Log.i(
                LOG_TAG, String.format(
                    "Handled Authorization Response %s ",
                    authState.toJsonString()
                )
            )
            val service = AuthorizationService(this)
            service.performTokenRequest(
                response.createTokenExchangeRequest()
            ) { tokenResponse, exception ->
                if (exception != null) {
                    Log.w(LOG_TAG, "Token Exchange failed", exception)
                } else {
                    if (tokenResponse != null) {
                        authState.update(tokenResponse, exception)
                        //                                persistAuthState(authState)

                        Log.i(
                            LOG_TAG, String.format(
                                "Token Response [ Access Token: %s, ID Token: %s ]",
                                tokenResponse.accessToken,
                                tokenResponse.idToken
                            )
                        )
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        checkIntent(intent)
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

    companion object {
        //        var topspace = 0
//        private const val RC_TASKS = 10112
        private const val USED_INTENT = "USED_INTENT"
        private const val LOG_TAG = "MainActivity"
    }
}

//        if (!GoogleSignIn.hasPermissions(
//                GoogleSignIn.getLastSignedInAccount(this),
//                tasksScope,
//                emailScope
//            )
//        ) {
//
//            GoogleSignIn.requestPermissions(
//                this,
//                RC_TASKS,
//                GoogleSignIn.getLastSignedInAccount(this),
//                tasksScope,
//                emailScope
//            )
//
//        } else {
//            val account = GoogleSignIn.getLastSignedInAccount(this)
//            account?.let {
//                Timber.d("service Auth Code: ${it.serverAuthCode}")
//
//                val cred = GoogleAccountCredential.usingOAuth2(
//                    this,
//                    Collections.singletonList(tasksScope.scopeUri)
//                )
//                cred.selectedAccount = it.account
//
//                val transport = AndroidHttp.newCompatibleTransport()
//                val jacksonFactory = JacksonFactory.getDefaultInstance()
//
//                val service = Tasks.Builder(transport, jacksonFactory, cred)
//                    .setApplicationName(getString(R.string.app_name))
//                    .build()
//
//                val tasksLists = service.Tasklists()
//
//                val tl = TaskList()
//                tl.title = "main"
//                tasksLists.insert(tl)
//
//                tasksLists.list().forEach {
//                    Timber.d("Tasks List: ${it.key} >> ${it.value} ")
//                }
//
//            }
//        }