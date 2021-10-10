package dev.mufa.movieapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class MoviesModel implements Parcelable {
	private String overview;
	private String original_language;
	private String original_title;
	private boolean video;
	private String title;
	private List<Integer> genre_ids;
	private String poster_path;
	private String backdrop_path;
	private String release_date;
	private double popularity;
	private double vote_average;
	private int id;
	private boolean adult;
	private int vote_count;

	public MoviesModel(String overview, String original_language, String original_title, boolean video, String title, List<Integer> genre_ids, String poster_path, String backdrop_path, String release_date, double popularity, double vote_average, int id, boolean adult, int vote_count) {
		this.overview = overview;
		this.original_language = original_language;
		this.original_title = original_title;
		this.video = video;
		this.title = title;
		this.genre_ids = genre_ids;
		this.poster_path = poster_path;
		this.backdrop_path = backdrop_path;
		this.release_date = release_date;
		this.popularity = popularity;
		this.vote_average = vote_average;
		this.id = id;
		this.adult = adult;
		this.vote_count = vote_count;
	}

	protected MoviesModel(Parcel in) {
		overview = in.readString();
		original_language = in.readString();
		original_title = in.readString();
		video = in.readByte() != 0;
		title = in.readString();
		poster_path = in.readString();
		backdrop_path = in.readString();
		release_date = in.readString();
		popularity = in.readDouble();
		vote_average = in.readDouble();
		id = in.readInt();
		adult = in.readByte() != 0;
		vote_count = in.readInt();
	}

	public static final Creator<MoviesModel> CREATOR = new Creator<MoviesModel>() {
		@Override
		public MoviesModel createFromParcel(Parcel in) {
			return new MoviesModel(in);
		}

		@Override
		public MoviesModel[] newArray(int size) {
			return new MoviesModel[size];
		}
	};

	public void setOverview(String overview){
		this.overview = overview;
	}

	public String getOverview(){
		return overview;
	}

	public void setoriginal_language(String original_language){
		this.original_language = original_language;
	}

	public String getoriginal_language(){
		return original_language;
	}

	public void setoriginal_title(String original_title){
		this.original_title = original_title;
	}

	public String getoriginal_title(){
		return original_title;
	}

	public void setVideo(boolean video){
		this.video = video;
	}

	public boolean isVideo(){
		return video;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setgenre_ids(List<Integer> genre_ids){
		this.genre_ids = genre_ids;
	}

	public List<Integer> getgenre_ids(){
		return genre_ids;
	}

	public void setposter_path(String poster_path){
		this.poster_path = poster_path;
	}

	public String getposter_path(){
		return poster_path;
	}

	public void setbackdrop_path(String backdrop_path){
		this.backdrop_path = backdrop_path;
	}

	public String getbackdrop_path(){
		return backdrop_path;
	}

	public void setrelease_date(String release_date){
		this.release_date = release_date;
	}

	public String getrelease_date(){
		return release_date;
	}

	public void setPopularity(double popularity){
		this.popularity = popularity;
	}

	public double getPopularity(){
		return popularity;
	}

	public void setvote_average(double vote_average){
		this.vote_average = vote_average;
	}

	public double getvote_average(){
		return vote_average;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setAdult(boolean adult){
		this.adult = adult;
	}

	public boolean isAdult(){
		return adult;
	}

	public void setvote_count(int vote_count){
		this.vote_count = vote_count;
	}

	public int getvote_count(){
		return vote_count;
	}

	@Override
 	public String toString(){
		return 
			"MoviesModel{" + 
			"overview = '" + overview + '\'' + 
			",original_language = '" + original_language + '\'' + 
			",original_title = '" + original_title + '\'' + 
			",video = '" + video + '\'' + 
			",title = '" + title + '\'' + 
			",genre_ids = '" + genre_ids + '\'' + 
			",poster_path = '" + poster_path + '\'' + 
			",backdrop_path = '" + backdrop_path + '\'' + 
			",release_date = '" + release_date + '\'' + 
			",popularity = '" + popularity + '\'' + 
			",vote_average = '" + vote_average + '\'' + 
			",id = '" + id + '\'' + 
			",adult = '" + adult + '\'' + 
			",vote_count = '" + vote_count + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(overview);
		parcel.writeString(original_language);
		parcel.writeString(original_title);
		parcel.writeByte((byte) (video ? 1 : 0));
		parcel.writeString(title);
		parcel.writeString(poster_path);
		parcel.writeString(backdrop_path);
		parcel.writeString(release_date);
		parcel.writeDouble(popularity);
		parcel.writeDouble(vote_average);
		parcel.writeInt(id);
		parcel.writeByte((byte) (adult ? 1 : 0));
		parcel.writeInt(vote_count);
	}
}