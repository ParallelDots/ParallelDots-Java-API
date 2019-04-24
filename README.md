# ParallelDots-Java-API
Java wrapper for ParallelDots APIs

# Installation
#### Use the JAR
* paralleldots-1.0.2.jar

#### Path to JAR
> path: target/paralleldots-1.0.2.jar

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


System.out.println("Abuse Batch");
String abuse_batch = pd.abuse_batch(text_list);


System.out.println("Custom_Classifier");
String custom_classifier = pd.custom_classifier("Narendra Modi is the Prime Minister of India", category);


System.out.println("Emotion");
String emotion = pd.emotion("Did you hear the latest Porcupine Tree song ? It's rocking !");


System.out.println("Emotion Multilang");
String emotion_multilang = pd.emotion("Avez-vous entendu la dernière chanson de Porcupine Tree? Ça berce!", "fr");


System.out.println("Emotion Batch");
String emotion_batch = pd.emotion_batch(text_list);


System.out.println("Emotion Multilang Batch");
String emotion_multilang_batch = pd.emotion_batch(text_list_multilang, "fr");


System.out.println("Sarcasm");
String sarcasm = pd.sarcasm("Did you hear the latest Porcupine Tree song ? It's rocking !");


System.out.println("Sarcasm Multilang");
String sarcasm_multilang = pd.sarcasm("Avez-vous entendu la dernière chanson de Porcupine Tree? Ça berce!", "fr");


System.out.println("Sarcasm Batch");
String sarcasm_batch = pd.sarcasm_batch(text_list);


System.out.println("Sarcasm Multilang Batch");
String sarcasm_multilang_batch = pd.sarcasm_batch(text_list_multilang, "fr");


System.out.println("Facial Emotion");
String facial_emotion = pd.facial_emotion(path_to_image);


System.out.println("Facial Emotion URL");
String facial_emotion_url = pd.facial_emotion_url(url_to_image);


System.out.println("Intent");
String intent = pd.intent("Finance ministry calls banks to discuss new facility to drain cash");


System.out.println("Intent Batch");
String intent_batch = pd.intent_batch(text_list);

System.out.println("Keywords");
String keywords = pd.keywords("Prime Minister Narendra Modi tweeted a link to the speech Human Resource Development Minister Smriti Irani made in the Lok Sabha during the debate on the ongoing JNU row and the suicide of Dalit scholar Rohith Vemula at the Hyderabad Central University.");


System.out.println("Keywords Batch");
String keywords_batch = pd.keywords_batch(text_list);


System.out.println("Multilang_Keywords");
String multilang_keywords = pd.multilang_keywords("La ville de Paris est très belle", "fr");


System.out.println("NER");
String ner = pd.ner("Narendra Modi is the prime minister of India");


System.out.println("NER Batch");
String ner_batch = pd.ner_batch(text_list);

System.out.println("Multilang NER");
String multilang_ner = pd.ner("Lionel Andrés Messi vuelve a ser el gran protagonista en las portadas de la prensa deportiva internacional al día siguiente de un partido de Champions.","es");


System.out.println("Multilang NER Batch");
String ner_multilang_batch = pd.ner_batch(text_list,"es");


System.out.println("Object Recognizer");
String object_recognizer = pd.object_recognizer(path_to_image);


System.out.println("Object Recognizer URL");
String object_recognizer_url = pd.object_recognizer_url(url_to_image);


System.out.println("Phrase Extractor");
String phrase_extractor = pd.phrase_extractor("The girls giggling and playing in the park never seemed to tire");


System.out.println("Phrase Extractor Batch");
String phrase_extractor_batch = pd.phrase_extractor_batch(text_list);


System.out.println("Popularity");
String popularity = pd.popularity(path_to_image);


System.out.println("Popularity_Url");
String popularity_url = pd.popularity_url(url_to_image);


System.out.println("Sentiment");
String sentiment = pd.sentiment("Come on, lets play together");


System.out.println("Sentiment Multilang");
String sentiment_multilang = pd.sentiment("Allez, jouons ensemble", "fr");


System.out.println("Sentiment Batch");
String sentiment_batch = pd.sentiment_batch(text_list);


System.out.println("Sentiment Multilang Batch");
String sentiment_multilang_batch = pd.sentiment_batch(text_list_multilang, "fr");


System.out.println("Similarity");
String similarity = pd.similarity("Sachin is the greatest batsman", "Tendulkar is the finest cricketer");


System.out.println("Taxonomy");
String taxonomy = pd.taxonomy("Narendra Modi is the prime minister of India");


System.out.println("Taxonomy Batch");
String taxonomy_batch = pd.taxonomy_batch(text_list);


System.out.println("Text_Parser");
String text_parser = pd.text_parser("Thrilling contest between Manchester City and Manchester United ends in a draw.");


System.out.println("Usage");
String usage = pd.usage();


```
