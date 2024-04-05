package spamdetector;

import weka.core.*;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.Evaluation;
import weka.core.converters.CSVLoader;

import java.io.File;

public class SpamDetector {
    public static void main(String[] args) {
        try {
            // Load CSV file
            CSVLoader loader = new CSVLoader();
            loader.setSource(new File("C:\\Users\\INDIAN SCHOOL RUSTAQ\\Desktop\\spam.csv"));
            loader.setFieldSeparator(",");  
            Instances data = loader.getDataSet();


            // Print dataset information
            System.out.println("Dataset Name: " + data.relationName());
            System.out.println("Number of Attributes: " + data.numAttributes());
            System.out.println("Number of Instances: " + data.numInstances());

            // Set class attribute (last attribute) as the class to predict
            data.setClassIndex(data.numAttributes() - 1);

            // Build and evaluate Naive Bayes classifier
            NaiveBayes classifier = new NaiveBayes();
            classifier.buildClassifier(data);

            // Evaluate classifier using cross-validation
            Evaluation eval = new Evaluation(data);
            eval.crossValidateModel(classifier, data, 10, new java.util.Random(1));

            // Display evaluation results
            System.out.println("=== Evaluation Results ===");
            System.out.println("Accuracy: " + eval.pctCorrect() + "%");
            System.out.println("Precision: " + eval.weightedPrecision());
            System.out.println("Recall: " + eval.weightedRecall());
            System.out.println("F1-Score: " + eval.weightedFMeasure());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
