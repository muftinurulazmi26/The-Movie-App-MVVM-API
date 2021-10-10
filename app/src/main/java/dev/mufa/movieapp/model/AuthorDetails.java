package dev.mufa.movieapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AuthorDetails implements Parcelable {
	private String avatar_path;
	private String name;
	private double rating;
	private String username;

	protected AuthorDetails(Parcel in) {
		avatar_path = in.readString();
		name = in.readString();
		rating = in.readDouble();
		username = in.readString();
	}

	public static final Creator<AuthorDetails> CREATOR = new Creator<AuthorDetails>() {
		@Override
		public AuthorDetails createFromParcel(Parcel in) {
			return new AuthorDetails(in);
		}

		@Override
		public AuthorDetails[] newArray(int size) {
			return new AuthorDetails[size];
		}
	};

	public void setAvatarPath(String avatar_path){
		this.avatar_path = avatar_path;
	}

	public String getAvatarPath(){
		return avatar_path;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setRating(double rating){
		this.rating = rating;
	}

	public double getRating(){
		return rating;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"AuthorDetails{" + 
			"avatar_path = '" + avatar_path + '\'' + 
			",name = '" + name + '\'' + 
			",rating = '" + rating + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(avatar_path);
		parcel.writeString(name);
		parcel.writeDouble(rating);
		parcel.writeString(username);
	}
}
