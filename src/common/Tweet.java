package common;

import java.util.Date;

public class Tweet {
	
    private String id = "";
    private String text = "";
    private Date createdAt;
    private boolean truncated = false;
    private String source = "";
    private String inReplyToStatusId = "";
    private String inReplyToUserId = "";
    private String inReplyToScreenName = "";
    private String userId = "";
    private Float longitude;
    private Float latitude;
    private boolean isQuote = false;
    private int retweetCount = 0;
    private int favoriteCount = 0;
    private String lang = "";
    
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @return the truncated
	 */
	public boolean isTruncated() {
		return truncated;
	}
	/**
	 * @param truncated the truncated to set
	 */
	public void setTruncated(boolean truncated) {
		this.truncated = truncated;
	}
	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * @return the inReplyToStatusId
	 */
	public String getInReplyToStatusId() {
		return inReplyToStatusId;
	}
	/**
	 * @param inReplyToStatusId the inReplyToStatusId to set
	 */
	public void setInReplyToStatusId(String inReplyToStatusId) {
		this.inReplyToStatusId = inReplyToStatusId;
	}
	/**
	 * @return the inReplyToUserId
	 */
	public String getInReplyToUserId() {
		return inReplyToUserId;
	}
	/**
	 * @param inReplyToUserId the inReplyToUserId to set
	 */
	public void setInReplyToUserId(String inReplyToUserId) {
		this.inReplyToUserId = inReplyToUserId;
	}
	/**
	 * @return the inReplyToScreenName
	 */
	public String getInReplyToScreenName() {
		return inReplyToScreenName;
	}
	/**
	 * @param inReplyToScreenName the inReplyToScreenName to set
	 */
	public void setInReplyToScreenName(String inReplyToScreenName) {
		this.inReplyToScreenName = inReplyToScreenName;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the longitude
	 */
	public Float getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the latitude
	 */
	public Float getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the isQuote
	 */
	public boolean isQuote() {
		return isQuote;
	}
	/**
	 * @param isQuote the isQuote to set
	 */
	public void setQuote(boolean isQuote) {
		this.isQuote = isQuote;
	}
	/**
	 * @return the retweetCount
	 */
	public int getRetweetCount() {
		return retweetCount;
	}
	/**
	 * @param retweetCount the retweetCount to set
	 */
	public void setRetweetCount(int retweetCount) {
		this.retweetCount = retweetCount;
	}
	/**
	 * @return the favoriteCount
	 */
	public int getFavoriteCount() {
		return favoriteCount;
	}
	/**
	 * @param favoriteCount the favoriteCount to set
	 */
	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}
	/**
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}
	/**
	 * @param lang the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}
}
