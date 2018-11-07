package news.airweb.fr.testandroidairweb.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class News {

    @SerializedName("nid")
    @Expose
    private Integer nid;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("dateformated")
    @Expose
    private String dateformated;

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public News withNid(Integer nid) {
        this.nid = nid;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public News withType(String type) {
        this.type = type;
        return this;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public News withDate(String date) {
        this.date = date;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public News withTitle(String title) {
        this.title = title;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public News withPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public News withContent(String content) {
        this.content = content;
        return this;
    }

    public String getDateformated() {
        return dateformated;
    }

    public void setDateformated(String dateformated) {
        this.dateformated = dateformated;
    }

    public News withDateformated(String dateformated) {
        this.dateformated = dateformated;
        return this;
    }

}
