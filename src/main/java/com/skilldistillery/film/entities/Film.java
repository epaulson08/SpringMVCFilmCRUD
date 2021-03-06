package com.skilldistillery.film.entities;

import java.util.List;

public class Film {
	private int id;
	private String title;
	private String description;
	private Integer languageId;
	private Integer rentalDuration;
	private double rentalRate;
	private Integer length;
	private double replacementCost;
	private String rating;
	private String specialFeatures;
	private Integer releaseYear;
	private List<Actor> actorsList;
	private String languagePlainText;
	private List<Category> categories;

	public Film() {
		super();
	}

	// for unit testing
	public Film(int id, String title, Integer languageId, Integer rentalDuration, Integer releaseYear) {
		super();
		this.id = id;
		this.title = title;
		this.languageId = languageId;
		this.rentalDuration = rentalDuration;
		this.releaseYear = releaseYear;
	}

	// for user-defined film
	public Film(String title, String description) {
		this.title = title;
		this.description = description;
	}
	
	// full constructor
	public Film(int id, String title, String description, Integer languageId, Integer rentalDuration, double rentalRate,
			Integer length, double replacementCost, String rating, String specialFeatures, Integer releaseYear) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.languageId = languageId;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
		this.releaseYear = releaseYear;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public String getLanguagePlainText() {
		return languagePlainText;
	}

	public void setLanguagePlainText(String languagePlainText) {
		this.languagePlainText = languagePlainText;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	public Integer getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(Integer rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public double getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public double getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	public List<Actor> getActorsList() {
		return actorsList;
	}

	public void setActorsList(List<Actor> actorsList) {
		this.actorsList = actorsList;
	}

	public String userFacingToString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\nTitle: ");
		builder.append(title);
		builder.append("\nRelease year: ");
		builder.append(releaseYear);
		builder.append("\nRating: ");
		builder.append(rating);
		builder.append("\nDescription: ");
		builder.append(description);
		builder.append("\nCast: ");
		// No commas after last actor:
		int counter = 0;
		for (Actor a : actorsList) {
			builder.append(a.getFirstName() + " " + a.getLastName());
			if (counter < actorsList.size() - 1)
				builder.append(", ");
			counter++;
		}
		// No commas after last category:
		builder.append("\nCategories: ");
		if (categories != null) {
			for (Category c : categories) {
				builder.append(c.getName());
				if (counter < categories.size() - 1)
					builder.append(", ");
				counter++;
			}
		} else
			builder.append("none");

		builder.append("\nLanguage: ");
		builder.append(languagePlainText);
		builder.append("\n");
		return builder.toString();
	}

	public String exhaustiveUserFacingToString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\nFilm ID: ");
		builder.append(id);
		builder.append(userFacingToString());
		builder.append("Language ID: ");
		builder.append(languageId);
		builder.append("\nRental duration: ");
		builder.append(rentalDuration);
		builder.append("\nRental rate: ");
		builder.append(rentalRate);
		builder.append("\nLength: ");
		builder.append(length);
		builder.append("\nReplacement cost: ");
		builder.append(replacementCost);
		builder.append("\nSpecial Features: ");
		builder.append(specialFeatures + "\n");

		return builder.toString();

	}
	
	public String limitedToString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Film ID: ");
		builder.append(id);
		builder.append("\nTitle: ");
		builder.append(title);
		builder.append("\nDescription: ");
		builder.append(description);
		return builder.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Film [id=");
		builder.append(id);
		builder.append(", title=");
		builder.append(title);
		builder.append(", description=");
		builder.append(description);
		builder.append(", languageId=");
		builder.append(languageId);
		builder.append(", rentalDuration=");
		builder.append(rentalDuration);
		builder.append(", rentalRate=");
		builder.append(rentalRate);
		builder.append(", length=");
		builder.append(length);
		builder.append(", replacementCost=");
		builder.append(replacementCost);
		builder.append(", rating=");
		builder.append(rating);
		builder.append(", specialFeatures=");
		builder.append(specialFeatures);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((languageId == null) ? 0 : languageId.hashCode());
		result = prime * result + ((rentalDuration == null) ? 0 : rentalDuration.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	// Note that this is an abbreviated .equals() for the sake of unit testing
	// Not all fields are included
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		if (id != other.id)
			return false;
		if (languageId == null) {
			if (other.languageId != null)
				return false;
		} else if (!languageId.equals(other.languageId))
			return false;
		if (rentalDuration == null) {
			if (other.rentalDuration != null)
				return false;
		} else if (!rentalDuration.equals(other.rentalDuration))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}