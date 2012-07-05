package mobidev.geowall;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class GeoWallActivity extends Activity implements OnClickListener {
	private TextView nick, pw, forgot;
	private Button login, signup;
	private ImageButton fbSocial, twSocial;

	private String USER_PREFERENCES = "UserPreference";
	private String ERROR = "- Insert nick or email\n\n- Password is required and it is max 10 character";
	public final int ERROR_DIALOG_ID = 0;

	String clog;
	String cEmail;
	String cPw;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		nick = (TextView) findViewById(R.id.nickText);
		pw = (TextView) findViewById(R.id.pwText);
		forgot = (TextView) findViewById(R.id.forgotLabel);
		login = (Button) findViewById(R.id.loginButton);
		signup = (Button) findViewById(R.id.registerButton);
		fbSocial = (ImageButton) findViewById(R.id.fbButton);
		twSocial = (ImageButton) findViewById(R.id.twButton);

		login.setOnClickListener(this);
		signup.setOnClickListener(this);
		forgot.setOnClickListener(this);
		fbSocial.setOnClickListener(this);
		twSocial.setOnClickListener(this);

		clog = (String) nick.getText().toString();
		cPw = (String) pw.getText().toString();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		SharedPreferences settings;
		switch (v.getId()) {
		case R.id.registerButton:
			// Il codice che segue lo utlizzo solo per far partire
			// l'activity di registrazione per fare delle prove
			settings = getSharedPreferences(USER_PREFERENCES, 0);
			SharedPreferences.Editor editor = settings.edit();
			editor.clear();
			editor.commit();

			Intent i = new Intent(this, RegistrationActivity.class);
			File temp = MediaController.getImage();
			temp.delete();
			this.startActivity(i);

			// delete image temp if it exists

			break;

		case R.id.loginButton:
			LoginData l;
			String pass = pw.getText().toString();
			String log = nick.getText().toString();
			boolean controll = true;
			if (nick.equals(clog)) {
				controll = false;
			}
			if (pass.equals(cPw)) {
				controll = false;
			}
			if (controll == false) {
				showDialog(ERROR_DIALOG_ID);
				return;
			} else {

				if (log.contains("@"))
					l = new LoginData(null, log, pass);
				else
					l = new LoginData(log, null, pass);
				setSharedPreference(l);
				new SignUpController().execute(this, this, null);
				settings = getSharedPreferences(USER_PREFERENCES, 0);
				if (settings.contains("SESSION")) {
					i = new Intent(this, GeoMapActivity.class);
					this.startActivity(i);
				}
			}
			break;
		}
	}

	private void setSharedPreference(LoginData userPreferences) {
		if (userPreferences == null)
			return;

		SharedPreferences settings = getSharedPreferences(USER_PREFERENCES, 0);
		SharedPreferences.Editor editor = settings.edit();
		if (userPreferences.getnick() != null)
			editor.putString("NICK", userPreferences.getnick());
		if (userPreferences.getemail() != null)
			editor.putString("EMAIL", userPreferences.getemail());
		editor.putString("PASS", userPreferences.getpassword());
		editor.commit();

	}

	protected Dialog onCreateDialog(int id) {
		AlertDialog alert = null;
		switch (id) {
		case ERROR_DIALOG_ID:
			alert = createAlert(ERROR);
			break;
		}
		return alert;
	}

	private AlertDialog createAlert(String messaggeError) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(messaggeError.toString())
				.setTitle("Error")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});
		AlertDialog alert = builder.create();
		return alert;
	}
}