ADD JAR s3://elasticmapreduce/samples/hive-ads/libs/jsonserde.jar;

CREATE EXTERNAL TABLE IF NOT EXISTS tweetj ( 
created_at string,
from_user string,
from_userid int,
from_username string,
tid int,
langa string,
profileimageurl string,
tweet string,
loc string,
catso string,
sentival int,
sentiIcon string
)
ROW FORMAT SERDE 'com.amazon.elasticmapreduce.JsonSerde'
WITH SERDEPROPERTIES ( 'paths'= 'created_at, from_user,from_userid, from_username, tid, langa, profileimageurl, tweet, loc, catso, sentival, sentiIcon' )
LOCATION 's3n://twitterlog-uri/tweetj/';


CREATE EXTERNAL TABLE IF NOT EXISTS tweet_sentiment_state ( 
state string,
sentiment_sum int
)
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n'
LOCATION 's3n://twitterlog-uri/tweetj_sentiment_state/';


insert OVERWRITE table tweet_sentiment_state
select loc, sum(sentival) from tweetj
group by loc;


CREATE EXTERNAL TABLE IF NOT EXISTS tweet_sentiment_state_bycat ( 
state string,
catgy string,
sentiment_sum int
)
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n'
LOCATION 's3n://twitterlog-uri/tweetj_sentiment_state/bycategory/';


insert OVERWRITE table tweet_sentiment_state_bycat
select loc,catso, sum(sentival) from tweetj
group by loc,catso;



CREATE EXTERNAL TABLE IF NOT EXISTS tweet_sentiment_cat ( 
catgy string,
sentiment_sum int
)
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n'
LOCATION 's3n://twitterlog-uri/tweetj_sentiment_cat/';


insert OVERWRITE table tweet_sentiment_cat
select catso, sum(sentival) from tweetj
group by catso;