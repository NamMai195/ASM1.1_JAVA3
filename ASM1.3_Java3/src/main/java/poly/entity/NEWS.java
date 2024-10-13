package poly.entity;

import java.util.Date;

public class NEWS {
	private String Id;
	private String Title;
	private String Content;
	private String Image;
	private Date PostedDate;
	private String Author;
	private int ViewCount;
	private String CategoryId;
	private Boolean Home;
	
	public NEWS() {
	}

	public NEWS(String id, String title, String content, String image, Date postedDate, String author, int viewCount,String categoryId, Boolean home) {
		Id = id;
		Title = title;
		Content = content;
		Image = image;
		PostedDate = postedDate;
		Author = author;
		ViewCount = viewCount;
		CategoryId = categoryId;
		Home = home;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public Date getPostedDate() {
		return PostedDate;
	}

	public void setPostedDate(Date postedDate) {
		PostedDate = postedDate;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public int getViewCount() {
		return ViewCount;
	}

	public void setViewCount(int viewCount) {
		ViewCount = viewCount;
	}

	public String getCategoryId() {
		return CategoryId;
	}

	public void setCategoryId(String categoryId) {
		CategoryId = categoryId;
	}

	public Boolean getHome() {
		return Home;
	}

	public void setHome(Boolean home) {
		Home = home;
	}
	
}
