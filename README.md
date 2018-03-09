# ParallelDots-Java-API
Java wrapper for ParallelDots APIs

# Installation
#### Use the JAR
* ParallelDots.jar

#### Path to JAR
> path: dist/ParallelDots.jar

#### Dependencies
* okhttp-3.9.0.jar
* okio-1.13.0.jar
* json-simple-1.1.jar

#### Path to Dependencies
> path: dependencies/okhttp-3.9.0.jar

> path: dependencies/okio-1.13.0.jar

> path: dependencies/json-simple-1.1.jar

#### Languages Supported
	- Portuguese ( pt )
	- Chinese ( zh )
	- Spanish ( es )
	- German ( de )
	- French ( fr )
	- Dutch ( nl )
	- Italian (it)
	- Japanese ( ja )
	- Indonesian ( id )
	- Thai ( th )
	- Danish ( da )
	- Finish ( fi )

#### Example
```sh
import paralleldots.ParallelDots;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

JSONParser parser = new JSONParser();
JSONObject category = (JSONObject)parser.parse("{\"world politics\": [\"diplomacy\", \"UN\", \"war\"], \"india\": [\"congress\", \"india\", \"bjp\"], \"finance\": [\"markets\", \"economy\", \"shares\"]}".trim());
String path_to_image="<path_to_image>";

ParallelDots pd = new ParallelDots(<api-key>);
System.out.println("Abuse");
String abuse = pd.abuse("you f**king a$$hole");
System.out.println(abuse);
Abuse
{"usage": "By accessing ParallelDots API or using information generated by ParallelDots API, you are agreeing to be bound by the ParallelDots API Terms of Use: http://www.paralleldots.com/terms-and-conditions", "sentence_type": "Abusive", "confidence_score": 0.953125}

System.out.println("Custom_Classifier");
String custom_classifier = pd.custom_classifier("Narendra Modi is the Prime Minister of India", category);
System.out.println(custom_classifier);
Custom_Classifier
{"usage": "By accessing ParallelDots API or using information generated by ParallelDots API, you are agreeing to be bound by the ParallelDots API Terms of Use: http://www.paralleldots.com/terms-and-conditions", "taxonomy": [{"tag": "india", "confidence_score": 0.758248}, {"tag": "world politics", "confidence_score": 0.610343}, {"tag": "finance", "confidence_score": 0.371018}]}

System.out.println("Emotion");
String emotion = pd.emotion("Did you hear the latest Porcupine Tree song ? It's rocking !", "en");
System.out.println(emotion);
Emotion
{"emotion": "indifferent", "probabilities": {"angry": 0.004, "indifferent": 0.379, "sad": 0.008, "excited": 0.31, "happy": 0.298}, "usage": "By accessing ParallelDots API or using information generated by ParallelDots API, you are agreeing to be bound by the ParallelDots API Terms of Use: http://www.paralleldots.com/terms-and-conditions"}

System.out.println("Emotion Multilang");
String emotion_multilang = pd.emotion("Avez-vous entendu la dernière chanson de Porcupine Tree? Ça berce!", "fr");
System.out.println(emotion_multilang);
Emotion Multilang
{"emotion": "indifferent", "probabilities": {"angry": 0.021, "indifferent": 0.636, "sad": 0.107, "excited": 0.11, "happy": 0.126}, "usage": "By accessing ParallelDots API or using information generated by ParallelDots API, you are agreeing to be bound by the ParallelDots API Terms of Use: http://www.paralleldots.com/terms-and-conditions"}

System.out.println("Intent");
String intent = pd.intent("Finance ministry calls banks to discuss new facility to drain cash");
System.out.println(intent);
Intent
{"probabilities": {"marketing": 0.059, "spam/junk": 0.039, "news": 0.773, "feedback/opinion": 0.097, "query": 0.032}, "usage": "By accessing ParallelDots API or using information generated by ParallelDots API, you are agreeing to be bound by the ParallelDots API Terms of Use: http://www.paralleldots.com/terms-and-conditions", "intent": "news"}

System.out.println("Keywords");
String keywords = pd.keywords("Prime Minister Narendra Modi tweeted a link to the speech Human Resource Development Minister Smriti Irani made in the Lok Sabha during the debate on the ongoing JNU row and the suicide of Dalit scholar Rohith Vemula at the Hyderabad Central University.");
System.out.println(keywords);
Keywords
{"keywords": [{"keyword": "Prime Minister Narendra Modi", "confidence_score": 0.857594}, {"keyword": "link", "confidence_score": 0.913924}, {"keyword": "speech Human Resource", "confidence_score": 0.70655}, {"keyword": "Smriti", "confidence_score": 0.860351}, {"keyword": "Lok", "confidence_score": 0.945534}], "usage": "By accessing ParallelDots API or using information generated by ParallelDots API, you are agreeing to be bound by the ParallelDots API Terms of Use: http://www.paralleldots.com/terms-and-conditions"}

System.out.println("Multilang_Keywords");
String multilang_keywords = pd.multilang_keywords("La ville de Paris est très belle", "fr");
System.out.println(multilang_keywords);
Multilang_Keywords
{"keywords": ["paris", "ville", "belle", "tr\u00e8s"], "usage": "By accessing ParallelDots API or using information generated by ParallelDots API, you are agreeing to be bound by the ParallelDots API Terms of Use: http://www.paralleldots.com/terms-and-conditions"}

System.out.println("NER");
String ner = pd.ner("Narendra Modi is the prime minister of India");
System.out.println(ner);
NER
{"usage": "By accessing ParallelDots API or using information generated by ParallelDots API, you are agreeing to be bound by the ParallelDots API Terms of Use: http://www.paralleldots.com/terms-and-conditions", "entities": [{"category": "name", "name": "Narendra Modi", "confidence_score": 0.951439}, {"category": "place", "name": "India", "confidence_score": 0.9263}]}

System.out.println("NSFW");
String nsfw = pd.nsfw(path_to_image);
System.out.println(nsfw);
NSFW
{"usage": "By accessing ParallelDots API or using information generated by ParallelDots API, you are agreeing to be bound by the ParallelDots API Terms of Use: http://www.paralleldots.com/terms-and-conditions", "output": "not safe to open at work", "prob": 0.9999975562095642}

System.out.println("Phrase Extractor");
String phrase_extractor = pd.phrase_extractor("The girls giggling and playing in the park never seemed to tire");
System.out.println(phrase_extractor);
Phrase Extractor
{"keywords": [{"relevance_score": 1, "keyword": "tire"}], "usage": "By accessing ParallelDots API or using information generated by ParallelDots API, you are agreeing to be bound by the ParallelDots API Terms of Use: http://www.paralleldots.com/terms-and-conditions"}

System.out.println("Popularity");
String popularity = pd.popularity(path_to_image);
System.out.println(popularity);
Popularity
{"Popular": "52.2365987301", "usage": "By accessing ParallelDots API or using information generated by ParallelDots API, you are agreeing to be bound by the ParallelDots API Terms of Use: http://www.paralleldots.com/terms-and-conditions", "Not Popular": "47.7633982897"}

System.out.println("Sentiment");
String sentiment = pd.sentiment("Come on, lets play together");
System.out.println(sentiment);
Sentiment
{"probabilities": {"positive": 0.096, "neutral": 0.891, "negative": 0.013}, "usage": "By accessing ParallelDots API or using information generated by ParallelDots API, you are agreeing to be bound by the ParallelDots API Terms of Use: http://www.paralleldots.com/terms-and-conditions", "sentiment": "neutral"}

System.out.println("Sentiment Multilang");
String sentiment_multilang = pd.sentiment("Allez, jouons ensemble", "fr");
System.out.println(sentiment_multilang);
Sentiment Multilang
{"probabilities": {"positive": 0.223, "neutral": 0.764, "negative": 0.013}, "usage": "By accessing ParallelDots API or using information generated by ParallelDots API, you are agreeing to be bound by the ParallelDots API Terms of Use: http://www.paralleldots.com/terms-and-conditions", "sentiment": "neutral"}

System.out.println("Similarity");
String similarity = pd.similarity("Sachin is the greatest batsman", "Tendulkar is the finest cricketer");
System.out.println(similarity);
Similarity
{"usage": "By accessing ParallelDots API or using information generated by ParallelDots API, you are agreeing to be bound by the ParallelDots API Terms of Use: http://www.paralleldots.com/terms-and-conditions", "actual_score": 0.842932, "normalized_score": 4.931469}

System.out.println("Taxonomy");
String taxonomy = pd.taxonomy("Narendra Modi is the prime minister of India");
System.out.println(taxonomy);
Taxonomy
{"usage": "By accessing ParallelDots API or using information generated by ParallelDots API, you are agreeing to be bound by the ParallelDots API Terms of Use: http://www.paralleldots.com/terms-and-conditions", "taxonomy": [{"tag": "News and Politics/International News", "confidence_score": 0.729859}, {"tag": "Business and Finance/Economy", "confidence_score": 0.738674}, {"tag": "Sports/Cricket", "confidence_score": 0.875686}]}

System.out.println("Text_Parser");
String text_parser = pd.text_parser("Thrilling contest between Manchester City and Manchester United ends in a draw.");
System.out.println(text_parser);
Text_Parser
{"usage": "By accessing ParallelDots API or using information generated by ParallelDots API, you are agreeing to be bound by the ParallelDots API Terms of Use: http://www.paralleldots.com/terms-and-conditions", "output": [{"text": "Thrilling", "Dependency": "compound", "Tags": "noun"}, {"text": "contest", "Dependency": "nominal subject", "Tags": "noun"}, {"text": "between", "Dependency": "prepositional modifier", "Tags": "preposition or conjunction"}, {"text": "Manchester", "Dependency": "compound", "Tags": "noun"}, {"text": "City", "Dependency": "object of a preposition", "Tags": "noun"}, {"text": "and", "Dependency": "coordinating conjunction", "Tags": "conjuction"}, {"text": "Manchester", "Dependency": "compound", "Tags": "noun"}, {"text": "United", "Dependency": "conjunct", "Tags": "noun"}, {"text": "ends", "Dependency": "root", "Tags": "verb"}, {"text": "in", "Dependency": "prepositional modifier", "Tags": "preposition or conjunction"}, {"text": "a", "Dependency": "determiner", "Tags": "determiner"}, {"text": "draw", "Dependency": "object of a preposition", "Tags": "noun"}]}

System.out.println("Usage");
String usage = pd.usage();
System.out.println(usage);
Usage
{"paying": true, "visual_monthly_quota": 918, "monthly_quota": 0.0, "daily_quota": 1000, "visual_daily_quota": 100}

```
