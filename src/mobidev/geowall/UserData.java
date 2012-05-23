package mobidev.geowall;


import android.graphics.Bitmap;

public class UserData  {

	private String email;
	private String nick;
	private int hash;
	private Bitmap img;
	private String country;
	private String city;
	
	
	public UserData( String email, String nick, int hash){
		super();		
		this.email=email;
		this.nick=nick;
		this.hash=hash;
		
	}
	
	public UserData(String email, String nick, int hash, Bitmap img, String country, String city){
		super();
		this.email=email;
		this.nick=nick;
		this.img=img;
		this.country=country;
		this.city=city;
		this.hash=hash;
	}
	
	
	
	public String getemail(){
		return email;
	}
	public String getnick(){
		return nick;
	}
	public Bitmap getbitmap(){
		return img;
	}
	public String getcountry(){
		return country;
	}
	public String getcity(){
		return city;
	}
	public int getpassword(){
		return hash;
	}
}
