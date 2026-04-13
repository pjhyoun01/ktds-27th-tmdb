package com.ktdsuniversity.edu.genre.vo;

public class GenreVO {
	private String genreId;
	private String movieId;
	private String categoryId;
	private String categoryName;
	

	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getGenreId() {
		return this.genreId;
	}
	public void setGenreId(String genreId) {
		this.genreId = genreId;
	}
	public String getMovieId() {
		return this.movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getCategoryId() {
		return this.categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
}
