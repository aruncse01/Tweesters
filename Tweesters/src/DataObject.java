import java.util.HashMap;
import java.util.Map;

public class DataObject {

	public String created_at;
	public String from_user;
	public String from_userid;
	public String from_username;
	public String geo;
	public String tid;
	public String langa;
	public String retweetcount;
	public String profileimageurl;
	public String tweet;
	public String loc;
	public String resulttype;
	public String catso;
	public String sentival;
	public String sentiIcon;
	public String sentitype;
	
	
	/*public void setCreatedAt(String created_at) {
		created_at.replace(",", "-");
		this.Created_at = created_at;

	}
	
	public String getCreatedAt() {
		return this.Created_at;
	}

	public void setFrom_user(String from_User) {
		this.from_user = from_User;
	}

	public String getFrom_User() {
		return this.from_user;
	}*/

	/*public String getData() {
		// StringBuilder sb = new StringBuilder();

		// sb.append("{created_at:").append(Created_at).append(",").append("from_user:").append(from_user).append(",").append("from_userid:").append(from_userid).append("}").append(",");
		return "Results [created_at=" + Created_at + ",from_user=" + from_user
				+ ",from_userid:" + from_userid + ",from_username:"
				+ from_username + ",geo:" + geo + ",tweetid:" + tid
				+ ",language:" + language + ",retweetcount:" + retweetcount
				+ ",profileImageUrl:" + profileImageUrl + ",text:" + text + "]";

		// return sb.toString();
	}*/
	
	@Override
	public String toString() {
	   return "Results [createdat=" + created_at  + ", from_user=" +from_user +",from_userid="+from_userid+",from_username="+from_username+"tweetid="+tid+"geol="+geo+"loc="+loc+"langn="+langa+"retweetcount="+retweetcount+"prfimageurl="+profileimageurl+"resulttype="+resulttype+"tweet="+tweet+"catso="+catso+"sentival="+sentival+"senticon="+sentiIcon+"sentitype="+sentitype+"]";
	}

	/*public void setData(String create_At, String from_user, String from_userid,
			String from_username, String geo, String tid, String language,
			String retweetCount, String profileImageurl, String text) {
		this.Created_at = create_At;
		this.from_user = from_user;
		this.from_userid = from_userid;
		this.from_username = from_username;
		this.geo = geo;
		this.tid = tid;
		this.language = language;
		this.retweetcount = retweetCount;
		this.profileImageUrl = profileImageurl;
		this.text = text;
	}*/
}
