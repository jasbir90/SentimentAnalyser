import java.util.Scanner;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentAnnotatedTree;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
public class EvaluateSentiment {
	static StanfordCoreNLP pipeline;

	public static void init() {
		pipeline = new StanfordCoreNLP("MyPropFile.properties");
	}

	public static int findSentiment(String input) {

		int mainSentiment = 0;
		if (input != null && input.length() > 0) {
			int longest = 0;
			Annotation annotation = pipeline.process(input);
			for (CoreMap sentence : annotation
					.get(CoreAnnotations.SentencesAnnotation.class)) {
				Tree tree = sentence
						.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
				int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
				String partText = sentence.toString();
				if (partText.length() > longest) {
					mainSentiment = sentiment;
					longest = partText.length();
				}

			}
		}
		return mainSentiment;
	}
	 public static void main(String args[]) {
         Scanner scanner = new Scanner(System.in);
         EvaluateSentiment.init();
         System.out.print("Enter the statement or type stop to quit the program: ");
         String input=scanner.nextLine();
         while(!input.equals("stop"))
         {
          int value= (EvaluateSentiment.findSentiment(input));
          if(value==1)
          System.out.println("This is a neutral sentiment");
          else if (value==2)
          System.out.println("This is a negative sentiment");
          else if (value==3)
          System.out.println("This is a positive sentiment");
          
          System.out.println("Enter the statement or type stop to quit the program: ");
          input = scanner.nextLine();
                     
         }
     }

}
