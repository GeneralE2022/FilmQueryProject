package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;

public class Film {

	private int id;
	private String title;
	private String desc;
	private Integer releaseYear;
	private int langId;
	private int rentDur;
	private double rate;
	private Integer length;
	private double repCost;
	private String rating;
	private String features;
	private String language;
	private List<Actor> actors;


	public Film(int id, String title, String desc, Integer releaseYear, int langId, int rentDur, double rate,
			Integer length, double repCost, String rating, String features, String language, List<Actor> actors) {
		super();
		this.id = id;
		this.title = title;
		this.desc = desc;
		this.releaseYear = releaseYear;
		this.langId = langId;
		this.rentDur = rentDur;
		this.rate = rate;
		this.length = length;
		this.repCost = repCost;
		this.rating = rating;
		this.features = features;
		this.language = language;
		this.actors = actors;
	}



	public Film() {
		// TODO Auto-generated constructor stub
	}





	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}




	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public String getDesc() {
		return desc;
	}




	public void setDesc(String desc) {
		this.desc = desc;
	}




	public Integer getReleaseYear() {
		return releaseYear;
	}




	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}




	public int getLangId() {
		return langId;
	}




	public void setLangId(int langId) {
		this.langId = langId;
	}




	public int getRentDur() {
		return rentDur;
	}




	public void setRentDur(int rentDur) {
		this.rentDur = rentDur;
	}




	public double getRate() {
		return rate;
	}




	public void setRate(double rate) {
		this.rate = rate;
	}




	public Integer getLength() {
		return length;
	}




	public void setLength(Integer length) {
		this.length = length;
	}




	public double getRepCost() {
		return repCost;
	}




	public void setRepCost(double repCost) {
		this.repCost = repCost;
	}




	public String getRating() {
		return rating;
	}




	public void setRating(String rating) {
		this.rating = rating;
	}




	public String getFeatures() {
		return features;
	}




	public void setFeatures(String features) {
		this.features = features;
	}




	public String getLanguage() {
		return language;
	}




	public void setLanguage(String language) {
		this.language = language;
	}




	public List<Actor> getActors() {
		return actors;
	}




	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}




	@Override
	public int hashCode() {
		return Objects.hash(actors, desc, features, id, langId, language, length, rate, rating, releaseYear, rentDur,
				repCost, title);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(actors, other.actors) && Objects.equals(desc, other.desc)
				&& Objects.equals(features, other.features) && id == other.id && langId == other.langId
				&& Objects.equals(language, other.language) && Objects.equals(length, other.length)
				&& Double.doubleToLongBits(rate) == Double.doubleToLongBits(other.rate)
				&& Objects.equals(rating, other.rating) && Objects.equals(releaseYear, other.releaseYear)
				&& rentDur == other.rentDur
				&& Double.doubleToLongBits(repCost) == Double.doubleToLongBits(other.repCost)
				&& Objects.equals(title, other.title);
	}


	@Override
	public String toString() {
		return "Film id=" + id + ", title=" + title + ", desc=" + desc + ", releaseYear=" + releaseYear + ", langId="
				+ langId + ", rentDur=" + rentDur + ", rate=" + rate + ", length=" + length + ", repCost=" + repCost
				+ ", rating=" + rating + ", features=" + features + ", language=" + language + ", actors=" + actors;
	}
}

