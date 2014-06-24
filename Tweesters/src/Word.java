

import com.google.gson.annotations.*;



public class Word {
	@SerializedName("name")
	public String text;
	@SerializedName("weight")
	public double sentimentValue = 0d;
	@Expose
	public Sentiments type = Sentiments.Neutral;

	public Word(String text, double sentimentValue) {
		this.text = text;
		this.sentimentValue = sentimentValue;

		if (sentimentValue == 0) {
			type = Sentiments.Neutral;
		} else if (sentimentValue > 3) {
			type = Sentiments.VeryPositive;
		} 
		else if (sentimentValue > 0 && sentimentValue <=3) {
			type = Sentiments.Positive;
		}
		else if (sentimentValue < 0 && sentimentValue <=-3) {
			type = Sentiments.Negative;
		}
		else {
			type = Sentiments.VeryNegative;
			}
	}

	public Word(Word word) {
		this(word.text, word.sentimentValue);
	}
}