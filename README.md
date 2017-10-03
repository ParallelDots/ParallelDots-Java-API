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

{"probabilities": {"positive": 0.568817,"neutral": 0.400776,"negative": 0.030407}, "sentiment":"positive"}

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

{"tag": "terrorism", "confidence_score": 0.531435}, {"tag": "world politics", "confidence_score": 0.391963}, {"tag": "politics", "confidence_score": 0.358955}, {"tag": "religion", "confidence_score": 0.308195}, {"tag": "defense", "confidence_score": 0.26187}, {"tag": "business", "confidence_score": 0.20885}, {"tag": "entrepreneurship", "confidence_score": 0.18349}, {"tag": "health", "confidence_score": 0.171121}, {"tag": "technology", "confidence_score": 0.168591}, {"tag": "law", "confidence_score": 0.156953}, {"tag": "education", "confidence_score": 0.146511}, {"tag": "science", "confidence_score": 0.101002}, {"tag": "crime", "confidence_score": 0.085016}, {"tag": "entertainment", "confidence_score": 0.080634}, {"tag": "environment", "confidence_score": 0.078024}, {"tag": "disaster", "confidence_score": 0.075295}, {"tag": "weather", "confidence_score": 0.06784}, {"tag": "accident", "confidence_score": 0.066831}, {"tag": "sports", "confidence_score": 0.058329}, {"tag": "advertising", "confidence_score": 0.054868}, {"tag": "history", "confidence_score": 0.043581}, {"tag": "mining", "confidence_score": 0.03833}, {"tag": "travel", "confidence_score": 0.025517}, {"tag": "geography", "confidence_score": 0.022372}, {"tag": "nature", "confidence_score": 0.013477}, {"tag": "lifestyle", "confidence_score": 0.006467}, {"tag": "automobile", "confidence_score": 0.001161}, {"tag": "personal care", "confidence_score": 0.000275}]}

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
