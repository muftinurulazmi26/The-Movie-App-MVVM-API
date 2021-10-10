package dev.mufa.movieapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ReviewUserModel implements Parcelable {
	private AuthorDetails author_details;
	private String updated_at;
	private String author;
	private String created_at;
	private String id;
	private String content;
	private String url;

	protected ReviewUserModel(Parcel in) {
		author_details = in.readParcelable(AuthorDetails.class.getClassLoader());
		updated_at = in.readString();
		author = in.readString();
		created_at = in.readString();
		id = in.readString();
		content = in.readString();
		url = in.readString();
	}

	public static final Creator<ReviewUserModel> CREATOR = new Creator<ReviewUserModel>() {
		@Override
		public ReviewUserModel createFromParcel(Parcel in) {
			return new ReviewUserModel(in);
		}

		@Override
		public ReviewUserModel[] newArray(int size) {
			return new ReviewUserModel[size];
		}
	};

	public void setAuthorDetails(AuthorDetails author_details){
		this.author_details = author_details;
	}

	public AuthorDetails getAuthorDetails(){
		return author_details;
	}

	public void setUpdatedAt(String updated_at){
		this.updated_at = updated_at;
	}

	public String getUpdatedAt(){
		return updated_at;
	}

	public void setAuthor(String author){
		this.author = author;
	}

	public String getAuthor(){
		return author;
	}

	public void setCreatedAt(String created_at){
		this.created_at = created_at;
	}

	public String getCreatedAt(){
		return created_at;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return content;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"ReviewUserModel{" + 
			"author_details = '" + author_details + '\'' + 
			",updated_at = '" + updated_at + '\'' + 
			",author = '" + author + '\'' + 
			",created_at = '" + created_at + '\'' + 
			",id = '" + id + '\'' + 
			",content = '" + content + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeParcelable(author_details, i);
		parcel.writeString(updated_at);
		parcel.writeString(author);
		parcel.writeString(created_at);
		parcel.writeString(id);
		parcel.writeString(content);
		parcel.writeString(url);
	}
}
