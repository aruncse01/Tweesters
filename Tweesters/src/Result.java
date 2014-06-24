

import com.google.gson.annotations.SerializedName;

public class Result {
	
	 @SerializedName("created_at")
	    public String createdAt;
    
	 @SerializedName("from_user")
	    public String fromUser;

	 @SerializedName("from_user_id")
	    public String from_user_id;
	 
    @SerializedName("from_user_id_str")
    public String fromUserIdStr;
    
    @SerializedName("from_user_name")
    public String fromUserName;
    
  //  @SerializedName("geo")
  //  public String geo;
    
   // @SerializedName("location")
   // public String location;
    
    @SerializedName("id_str")
    public String idStr;
    
    public String id;
    
    @SerializedName("iso_language_code")
    public String isoLanguageCode;
    
    public Metadata metadata;
    
    @SerializedName("profile_image_url")
    public String profileImageUrl;
    
    @SerializedName("to_user_id")
    public String toUserId;
    

    @SerializedName("to_user_id_str")
    public String toUserIdStr;

    public String source;
    
    public String text;
    
}