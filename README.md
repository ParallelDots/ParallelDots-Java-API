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

#### Path to Dependencies
> path: dependencies/okhttp-3.9.0.jar

> path: dependencies/okio-1.13.0.jar

#### Example
```sh
import paralleldots.ParallelDots;

ParallelDots pd = new ParallelDots(<api-key>);
String sentiment = pd.sentiment("Come on, lets play together");
System.out.println(sentiment);

{ "sentiment": 0.851301}

String similarity = pd.similarity("Sachin is the greatest batsman", "Tendulkar is the finest cricketer");
System.out.println(similarity);

{"actual_score": 0.8429316084125629, "normalized_score": 4.931468682744329, "similarity": 4.931468682744329}

String ner = pd.ner("Narendra Modi is the prime minister of India");
System.out.println(ner);

{"entities": [{"category": ["place"], "name": "India", "confidence_score": 1.0}, {"category": ["person"], "name": "Narendra Modi", "confidence_score": 1.0}]}

String keywords = pd.keywords("Prime Minister Narendra Modi tweeted a link to the speech Human Resource Development Minister Smriti Irani made in the Lok Sabha during the debate on the ongoing JNU row and the suicide of Dalit scholar Rohith Vemula at the Hyderabad Central University.");
System.out.println(keywords);

{"keywords": [{"relevance_score": 6, "keyword": "Human Resource Development Minister Smriti Irani"}, {"relevance_score": 4, "keyword": "Prime Minister Narendra Modi"}, {"relevance_score": 3, "keyword": "Hyderabad Central University"}, {"relevance_score": 3, "keyword": "ongoing JNU row"}, {"relevance_score": 2, "keyword": "Dalit scholar"}, {"relevance_score": 2, "keyword": "Lok Sabha"}, {"relevance_score": 2, "keyword": "Rohith Vemula"}]}

String taxonomy = pd.taxonomy("Narendra Modi is the prime minister of India");
System.out.println(taxonomy);

{"taxonomy": [{"tag": "finance", "confidence_score": 4.088}, {"tag": "government", "confidence_score": 3.4284}, {"tag": "business", "confidence_score": 1.2719}]}

String emotion = pd.emotion("Did you hear the latest Porcupine Tree song ? It's rocking !");
System.out.println(emotion);

{"emotion": "happy"}

String intent = pd.intent("Finance ministry calls banks to discuss new facility to drain cash");
System.out.println(intent);

{"intent": "news"}

String multilang = pd.multilang("La ville de Paris est très belle", "fr");
System.out.println(multilang);

{"sentiment": "positive", "confidence_score": 0.845703}

String abuse = pd.abuse("you f**king a$$hole");
System.out.println(abuse);

{"sentence_type": "Abusive", "confidence_score": 0.953125}

String sentiment_social = pd.sentiment_social("I left my camera at home");
System.out.println(sentiment_social);

{"probabilities": {"positive": 0.040374, "neutral": 0.491032, "negative": 0.468594}}

String usage = pd.usage();
System.out.println(usage);

{"emotion": 100, "sentiment": 100, "similarity": 100,	"taxonomy": 100, "abuse": 100, "intent": 100,	"keywords": 100, "ner": 100, "multilang": 100, "sentiment_social": 100}

```
