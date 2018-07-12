# ParallelDots-Java-API
Java wrapper for ParallelDots APIs

# Installation
#### Use the JAR
* paralleldots-1.0.0.jar

#### Path to JAR
> path: target/paralleldots-1.0.0.jar

#### Dependencies
* okhttp-3.10.0.jar
* okio-1.14.0.jar
* json-simple-1.1.jar

#### Languages Supported
	- Portuguese ( pt )
	- Simplified Chinese ( zh )
	- Spanish ( es )
	- German ( de )
	- French ( fr )
	- Dutch ( nl )
	- Italian (it)
	- Japanese ( ja )
	- Russian ( ru )
	- Thai ( th )
	- Danish ( da )
	- Finish ( fi )
	- Arabic (ar)
	- Greek (el)

#### Example
```sh
import com.paralleldots.paralleldots.App;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

JSONParser parser = new JSONParser();
JSONObject category = (JSONObject)parser.parse("{\"world politics\": [\"diplomacy\", \"UN\", \"war\"], \"india\": [\"congress\", \"india\", \"bjp\"], \"finance\": [\"markets\", \"economy\", \"shares\"]}".trim());
String path_to_image="<path_to_image>";
String url_to_image="<url_to_image>";
String lang_text = "C'est un environnement très hostile, si vous choisissez de débattre ici, vous serez vicieusement attaqué par l'opposition.";
JSONArray text_list = (JSONArray)parser.parse("[ \"drugs are fun\", \"don\'t do drugs, stay in school\", \"lol you a fag son\", \"I have a throat infection\" ]");
JSONArray text_list_multilang = (JSONArray)parser.parse("[\"les drogues sont amusantes\", \"ne pas faire de la drogue reste à l'école\", \"lol vous un fils de fag\", \"J\'ai une infection de la gorge\"]");

App pd = new App(<api-key>);
System.out.println("Abuse");
String abuse = pd.abuse("you f**king a$$hole");
System.out.println(abuse);
Abuse
{"code": 200, "sentence_type": "Abusive", "confidence_score": 0.953125}

System.out.println("Abuse Batch");
String abuse_batch = pd.abuse_batch(text_list);
System.out.println(abuse_batch);
Abuse Batch
{"code": 200, "abuse": [{"sentence_type": "Non Abusive", "confidence_score": 0.904297}, {"sentence_type": "Non Abusive", "confidence_score": 0.953125}, {"sentence_type": "Abusive", "confidence_score": 0.884766}, {"sentence_type": "Non Abusive", "confidence_score": 0.859375}]}

System.out.println("Custom_Classifier");
String custom_classifier = pd.custom_classifier("Narendra Modi is the Prime Minister of India", category);
System.out.println(custom_classifier);
Custom_Classifier
{"code": 200, "taxonomy": [{"confidence_score": 0.929256021976471, "tag": "india"}, {"confidence_score": 0.9066339731216431, "tag": "world politics"}, {"confidence_score": 0.5557219982147217, "tag": "finance"}]}

System.out.println("Emotion");
String emotion = pd.emotion("Did you hear the latest Porcupine Tree song ? It's rocking !");
System.out.println(emotion);
Emotion
{"code": 200, "emotion": {"probabilities": {"Excited": 0.35946185611995524, "Angry": 0.047388801553766284, "Sarcasm": 0.11246225260033746, "Sad": 0.008463515319536615, "Happy": 0.38386026259527695, "Fear": 0.0832442102790067, "Bored": 0.005119101532120664}, "emotion": "Happy"}}

System.out.println("Emotion Multilang");
String emotion_multilang = pd.emotion("Avez-vous entendu la dernière chanson de Porcupine Tree? Ça berce!", "fr");
System.out.println(emotion_multilang);
Emotion Multilang
{"code": 200, "emotion": {"probabilities": {"Excited": 0.22379238991339617, "Angry": 0.06747602813321307, "Sarcasm": 0.129273083404285, "Sad": 0.1666631471181756, "Happy": 0.2766871542748692, "Fear": 0.13610819715606104, "Bored": 0.0}, "emotion": "Happy"}}

System.out.println("Emotion Batch");
String emotion_batch = pd.emotion_batch(text_list);
System.out.println(emotion_batch);
Emotion Batch
{"code": 200, "emotion": [{"Excited": 0.2082173830066366, "Angry": 0.08368749025924326, "Sarcasm": 0.14361357966835644, "Sad": 0.025132654797074747, "Happy": 0.1269828871815771, "Fear": 0.344180628127824, "Bored": 0.06818537695928778}, {"Excited": 0.10118401124107868, "Angry": 0.28458333402617014, "Sarcasm": 0.09578231410218406, "Sad": 0.05735552847026735, "Happy": 0.11277100320338784, "Fear": 0.13348989058422842, "Bored": 0.21483391837268373}, {"Excited": 0.16457090063285224, "Angry": 0.1216389498218648, "Sarcasm": 0.11124312097614852, "Sad": 0.05410169293913279, "Happy": 0.07598588202024392, "Fear": 0.18020579627989994, "Bored": 0.2922536573298578}, {"Excited": 0.004337021311595699, "Angry": 0.46982189055546925, "Sarcasm": 0.05327575096045899, "Sad": 0.3672790882763135, "Happy": 0.005119673993076841, "Fear": 0.09443579921654321, "Bored": 0.005730775686542725}]}

System.out.println("Emotion Multilang Batch");
String emotion_multilang_batch = pd.emotion_batch(text_list_multilang, "fr");
System.out.println(emotion_multilang_batch);
Emotion Multilang Batch
{"code": 200, "emotion": [{"Excited": 0.2082173830066366, "Angry": 0.08368749025924326, "Sarcasm": 0.14361357966835644, "Sad": 0.025132654797074747, "Happy": 0.1269828871815771, "Fear": 0.344180628127824, "Bored": 0.06818537695928778}, {"Excited": 0.04239430417791154, "Angry": 0.5973252487701952, "Sarcasm": 0.07606870827400262, "Sad": 0.05560273629943171, "Happy": 0.048649541948481326, "Fear": 0.09433366519047207, "Bored": 0.08562579533950584}, {"Excited": 0.20433981731805748, "Angry": 0.1220104704524655, "Sarcasm": 0.13830145223523543, "Sad": 0.04597934708090136, "Happy": 0.0987838892687481, "Fear": 0.16393978751891622, "Bored": 0.22664523612567564}, {"Excited": 0.004337021311595699, "Angry": 0.46982189055546925, "Sarcasm": 0.05327575096045899, "Sad": 0.3672790882763135, "Happy": 0.005119673993076841, "Fear": 0.09443579921654321, "Bored": 0.005730775686542725}]}

System.out.println("Facial Emotion");
String facial_emotion = pd.facial_emotion(path_to_image);
System.out.println(facial_emotion);
Facial Emotion
{"code": 200, "facial_emotion": [{"tag": "Happy", "score": 0.9499974846839905}, {"tag": "Neutral", "score": 0.00833375658839941}, {"tag": "Surprise", "score": 0.00833375658839941}, {"tag": "Sad", "score": 0.00833375658839941}, {"tag": "Fear", "score": 0.00833375658839941}, {"tag": "Disgust", "score": 0.00833375658839941}, {"tag": "Angry", "score": 0.00833375658839941}]}

System.out.println("Facial Emotion URL");
String facial_emotion_url = pd.facial_emotion_url(url_to_image);
System.out.println(facial_emotion_url);
Facial Emotion URL
{"code": 200, "facial_emotion": [{"tag": "Happy", "score": 0.9499974846839905}, {"tag": "Neutral", "score": 0.00833375658839941}, {"tag": "Surprise", "score": 0.00833375658839941}, {"tag": "Sad", "score": 0.00833375658839941}, {"tag": "Fear", "score": 0.00833375658839941}, {"tag": "Disgust", "score": 0.00833375658839941}, {"tag": "Angry", "score": 0.00833375658839941}]}

System.out.println("Intent");
String intent = pd.intent("Finance ministry calls banks to discuss new facility to drain cash");
System.out.println(intent);
Intent
{"code": 200, "probabilities": {"feedback/opinion": 0.097, "query": 0.032, "spam/junk": 0.039, "marketing": 0.059, "news": 0.773}, "intent": "news"}

System.out.println("Intent Batch");
String intent_batch = pd.intent_batch(text_list);
System.out.println(intent_batch);
Intent Batch
{"code": 200, "intent": [{"feedback/opinion": 0.141, "query": 0.002, "spam/junk": 0.66, "marketing": 0.116, "news": 0.08}, {"feedback/opinion": 0.393, "query": 0.027, "spam/junk": 0.423, "marketing": 0.106, "news": 0.051}, {"feedback/opinion": 0.333, "query": 0.001, "spam/junk": 0.664, "marketing": 0.001, "news": 0.001}, {"feedback/opinion": 0.469, "query": 0.404, "spam/junk": 0.124, "marketing": 0.0, "news": 0.004}]}

System.out.println("Keywords");
String keywords = pd.keywords("Prime Minister Narendra Modi tweeted a link to the speech Human Resource Development Minister Smriti Irani made in the Lok Sabha during the debate on the ongoing JNU row and the suicide of Dalit scholar Rohith Vemula at the Hyderabad Central University.");
System.out.println(keywords);
Keywords
{"code": 200, "keywords": [{"keyword": "Prime Minister Narendra Modi", "confidence_score": 0.874473}, {"keyword": "link", "confidence_score": 0.940891}, {"keyword": "speech Human Resource Development", "confidence_score": 0.906034}, {"keyword": "Smriti Irani", "confidence_score": 0.966651}]}

System.out.println("Keywords Batch");
String keywords_batch = pd.keywords_batch(text_list);
System.out.println(keywords_batch);
Keywords Batch
{"code": 200, "keywords": [[{"keyword": "fun", "confidence_score": 0.560126}], [{"keyword": "drugs", "confidence_score": 0.894901}, {"keyword": "school", "confidence_score": 0.509218}], [{"keyword": "fag", "confidence_score": 0.791485}], [{"keyword": "throat infection", "confidence_score": 0.849233}]]}

System.out.println("Language Detection");
String language_detection = pd.language_detection(lang_text);
System.out.println(language_detection);
Language Detection
{"output": "French", "code": 200, "prob": 0.9999592304229736}

System.out.println("Language Detection Batch");
String language_detection_batch = pd.language_detection_batch(text_list_multilang);
System.out.println(language_detection_batch);
Language Detection Batch
{"code": 200, "lang_detection": [{"output": "French", "prob": 0.9990742802619934}, {"output": "French", "prob": 0.9999998807907104}, {"output": "French", "prob": 0.996209442615509}, {"output": "French", "prob": 0.9985631108283997}]}

System.out.println("Multilang_Keywords");
String multilang_keywords = pd.multilang_keywords("La ville de Paris est très belle", "fr");
System.out.println(multilang_keywords);
Multilang_Keywords
{"code": 200, "keywords": [{"confidence_score": 1.0, "keyword": "ville"}, {"confidence_score": 1.0, "keyword": "paris"}, {"confidence_score": 1.0, "keyword": "belle"}]}

System.out.println("NER");
String ner = pd.ner("Narendra Modi is the prime minister of India");
System.out.println(ner);
NER
{"entities": [{"name": "Narendra Modi", "category": "name", "confidence_score": 0.951439}, {"name": "India", "category": "place", "confidence_score": 0.9263}], "code": 200}

System.out.println("NER Batch");
String ner_batch = pd.ner_batch(text_list);
System.out.println(ner_batch);
NER Batch
{"entities": [[], [{"name": "don", "category": "name", "confidence_score": 0.671695}], [], []], "code": 200}

System.out.println("NSFW");
String nsfw = pd.nsfw(path_to_image);
System.out.println(nsfw);
NSFW
{"code": 200, "nsfw": "safe to open at work", "probability": 0.99995}

System.out.println("NSFW_Url");
String nsfw_url = pd.nsfw_url(url_to_image);
System.out.println(nsfw_url);
NSFW_Url
{"code": 200, "nsfw": "safe to open at work", "probability": 0.99995}

System.out.println("Object Recognizer");
String object_recognizer = pd.object_recognizer(path_to_image);
System.out.println(object_recognizer);
Object Recognizer
{"code": 200, "output": [{"score": 0.44762882590293884, "tag": "Brand"}, {"score": 0.4309701919555664, "tag": "Person"}, {"score": 0.307409405708313, "tag": "Clothing"}, {"score": 0.1471368819475174, "tag": "T-shirt"}, {"score": 0.1348528116941452, "tag": "Hairstyle"}, {"score": 0.1298481822013855, "tag": "Font"}, {"score": 0.11203251779079437, "tag": "Man"}, {"score": 0.10691165924072266, "tag": "Photo shoot"}, {"score": 0.10481810569763184, "tag": "Album cover"}, {"score": 0.10019328445196152, "tag": "Hand"}]}

System.out.println("Object Recognizer URL");
String object_recognizer_url = pd.object_recognizer_url(url_to_image);
System.out.println(object_recognizer_url);
Object Recognizer URL
{"code": 200, "output": [{"score": 0.44762882590293884, "tag": "Brand"}, {"score": 0.4309701919555664, "tag": "Person"}, {"score": 0.307409405708313, "tag": "Clothing"}, {"score": 0.1471368819475174, "tag": "T-shirt"}, {"score": 0.1348528116941452, "tag": "Hairstyle"}, {"score": 0.1298481822013855, "tag": "Font"}, {"score": 0.11203251779079437, "tag": "Man"}, {"score": 0.10691165924072266, "tag": "Photo shoot"}, {"score": 0.10481810569763184, "tag": "Album cover"}, {"score": 0.10019328445196152, "tag": "Hand"}]}

System.out.println("Phrase Extractor");
String phrase_extractor = pd.phrase_extractor("The girls giggling and playing in the park never seemed to tire");
System.out.println(phrase_extractor);
Phrase Extractor
{"keywords": [{"relevance_score": 1, "keyword": "tire"}], "code": 200}

System.out.println("Phrase Extractor Batch");
String phrase_extractor_batch = pd.phrase_extractor_batch(text_list);
System.out.println(phrase_extractor_batch);
Phrase Extractor Batch
{"code": 200, "phrases": [[], [{"relevance_score": 1, "keyword": "school"}], [{"relevance_score": 2, "keyword": "fag son"}], [{"relevance_score": 2, "keyword": "throat infection"}]]}

System.out.println("Popularity");
String popularity = pd.popularity(path_to_image);
System.out.println(popularity);
Popularity
{"Not Popular": "15.0209337473", "code": 200, "Popular": "84.9790692329"}

System.out.println("Popularity_Url");
String popularity_url = pd.popularity_url(url_to_image);
System.out.println(popularity_url);
Popularity_Url
{"Not Popular": "15.0209337473", "code": 200, "Popular": "84.9790692329"}

System.out.println("Sentiment");
String sentiment = pd.sentiment("Come on, lets play together");
System.out.println(sentiment);
Sentiment
{"code": 200, "sentiment": "neutral", "probabilities": {"positive": 0.096, "negative": 0.013, "neutral": 0.891}}

System.out.println("Sentiment Multilang");
String sentiment_multilang = pd.sentiment("Allez, jouons ensemble", "fr");
System.out.println(sentiment_multilang);
Sentiment Multilang
{"code": 200, "sentiment": "neutral", "probabilities": {"positive": 0.223, "negative": 0.013, "neutral": 0.764}}

System.out.println("Sentiment Batch");
String sentiment_batch = pd.sentiment_batch(text_list);
System.out.println(sentiment_batch);
Sentiment Batch
{"sentiment": [{"positive": 0.69, "negative": 0.046, "neutral": 0.265}, {"positive": 0.061, "negative": 0.361, "neutral": 0.578}, {"positive": 0.527, "negative": 0.275, "neutral": 0.198}, {"positive": 0.077, "negative": 0.908, "neutral": 0.015}], "code": 200}

System.out.println("Sentiment Multilang Batch");
String sentiment_multilang_batch = pd.sentiment_batch(text_list_multilang, "fr");
System.out.println(sentiment_multilang_batch);
Sentiment Multilang Batch
{"sentiment": [{"positive": 0.69, "negative": 0.046, "neutral": 0.265}, {"positive": 0.021, "negative": 0.492, "neutral": 0.487}, {"positive": 0.564, "negative": 0.194, "neutral": 0.242}, {"positive": 0.077, "negative": 0.908, "neutral": 0.015}], "code": 200}

System.out.println("Similarity");
String similarity = pd.similarity("Sachin is the greatest batsman", "Tendulkar is the finest cricketer");
System.out.println(similarity);
Similarity
{"actual_score": 0.842932, "normalized_score": 4.931469, "code": 200}

System.out.println("Taxonomy");
String taxonomy = pd.taxonomy("Narendra Modi is the prime minister of India");
System.out.println(taxonomy);
Taxonomy
{"taxonomy": [{"confidence_score": 0.9171473383903503, "tag": "hobbies and interests/inventors and patents"}, {"confidence_score": 0.792364, "tag": "law, govt and politics/government"}, {"confidence_score": 0.916781, "tag": "business and industrial/business news"}], "code": 200}

System.out.println("Taxonomy Batch");
String taxonomy_batch = pd.taxonomy_batch(text_list);
System.out.println(taxonomy_batch);
Taxonomy Batch
{"taxonomy": [[{"confidence_score": 0.996437, "tag": "health and fitness/drugs"}, {"confidence_score": 0.967404, "tag": "family and parenting/babies and toddlers"}, {"confidence_score": 0.6848993897438049, "tag": "automotive and vehicles/motor shows"}], [{"confidence_score": 0.977439, "tag": "health and fitness/dental care"}, {"confidence_score": 0.961832, "tag": "family and parenting/babies and toddlers"}, {"confidence_score": 0.970684, "tag": "education/school"}], [{"confidence_score": 0.9779467582702637, "tag": "family and parenting/parenting teens"}, {"confidence_score": 0.972425, "tag": "health and fitness/therapy"}, {"confidence_score": 0.9049649834632874, "tag": "pets/cats"}], [{"confidence_score": 0.985712, "tag": "health and fitness/disease"}, {"confidence_score": 0.974752, "tag": "family and parenting/adoption"}, {"confidence_score": 0.97041, "tag": "pets/cats"}]], "code": 200}

System.out.println("Text_Parser");
String text_parser = pd.text_parser("Thrilling contest between Manchester City and Manchester United ends in a draw.");
System.out.println(text_parser);
Text_Parser
{"code": 200, "output": [{"text": "Thrilling", "Dependency": "compound", "Tags": "noun"}, {"text": "contest", "Dependency": "nominal subject", "Tags": "noun"}, {"text": "between", "Dependency": "prepositional modifier", "Tags": "preposition or conjunction"}, {"text": "Manchester", "Dependency": "compound", "Tags": "noun"}, {"text": "City", "Dependency": "object of a preposition", "Tags": "noun"}, {"text": "and", "Dependency": "coordinating conjunction", "Tags": "conjuction"}, {"text": "Manchester", "Dependency": "compound", "Tags": "noun"}, {"text": "United", "Dependency": "conjunct", "Tags": "noun"}, {"text": "ends", "Dependency": "root", "Tags": "verb"}, {"text": "in", "Dependency": "prepositional modifier", "Tags": "preposition or conjunction"}, {"text": "a", "Dependency": "determiner", "Tags": "determiner"}, {"text": "draw", "Dependency": "object of a preposition", "Tags": "noun"}]}

System.out.println("Usage");
String usage = pd.usage();
System.out.println(usage);
Usage
{"monthly_billable_hits_breakdown": {"facial_emotion": 8, "emotion": 45, "sentiment": 45, "similarity": 4, "language_detection": 20, "taxonomy": 20, "popularity": 32, "text_parser": 5, "ner": 28, "custom_classifier": 4, "abuse": 26, "nsfw": 8, "keywords": 46, "intent": 20, "object_recognizer": 8, "multilang_keywords": 4, "phrase_extractor": 20}, "daily_billable_hits_breakdown": {"facial_emotion": 8, "emotion": 40, "sentiment": 40, "similarity": 4, "language_detection": 20, "taxonomy": 20, "popularity": 8, "text_parser": 5, "ner": 28, "custom_classifier": 4, "abuse": 25, "nsfw": 8, "keywords": 20, "intent": 20, "object_recognizer": 8, "multilang_keywords": 4, "phrase_extractor": 20}, "daily_billable_hits": 282, "monthly_billable_hits": 343}

```
