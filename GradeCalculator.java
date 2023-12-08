
// AUTHOR NAME : RAVITEJA ANNAM
import java.util.Scanner;

public class GradeCalculator {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String name, bid, courseName;
        System.out.println(
                "Dear professor Aboudja, this is RAVI TEJA ANNAM, here is the output for my final project of Grade Calculator:");
        System.out.println("Enter the student name:");
        name = scanner.nextLine();
        System.out.println("Enter the Course Name:");
        courseName = scanner.nextLine();
        System.out.println("Enter student BID (B followed by 8 digits):");
        bid = scanner.nextLine();

        double[][] scores = new double[5][];
        double[][] outOf = new double[5][];
        double[] weightages = { 0.15, 0.05, 0.25, 0.30, 0.25 };
        int[] itemCounts = { 8, 5, 1, 1, 1 };
        String[] categories = { "Homework", "Quiz", "Mid-Term Exam", "Final Exam", "Final Project" };

        for (int i = 0; i < categories.length; i++) {
            scores[i] = new double[itemCounts[i]];
            outOf[i] = new double[itemCounts[i]];
            initializeScores(scores[i], outOf[i]);
            handleCategory(categories[i], scores[i], outOf[i], itemCounts[i]);
        }

        double totalScore = calculateFinalScore(scores, outOf, weightages);
        displayResults(name, courseName, categories, scores, outOf, weightages, totalScore);
        displayGradeMessage(name, totalScore);
    }

    private static void initializeScores(double[] scores, double[] outOf) {
        for (int i = 0; i < scores.length; i++) {
            scores[i] = -1;
            outOf[i] = -1;
        }
    }

    private static void handleCategory(String category, double[] scores, double[] outOf, int count) {
        boolean isConfirmed = false;
        while (!isConfirmed) {
            if (!areScoresInitialized(scores)) {
                enterScores(category, scores, outOf, count);
            }
            displayScores(category, scores, outOf, count);
            isConfirmed = confirmAndModifyScores(category, scores, outOf, count);
        }
    }

    private static void displayScores(String category, double[] scores, double[] outOf, int count) {
        System.out.println("Here are the marks you entered for " + category + ":");
        for (int i = 0; i < count; i++) {
            System.out.println(category + " " + (i + 1) + ": " + scores[i] + " out of " + outOf[i]);
        }
    }

    private static void enterScores(String category, double[] scores, double[] outOf, int count) {
        System.out.println("Enter scores for " + category + ":");
        for (int i = 0; i < count; i++) {
            scores[i] = getValidatedInput("Enter " + category + " " + (i + 1) + " score:");
            outOf[i] = getValidatedInput("Enter total marks for " + category + " " + (i + 1) + ":");
            while (scores[i] > outOf[i] || outOf[i] == 0) {
                System.out.println("Invalid input. Please re-enter:");
                scores[i] = getValidatedInput("Score:");
                outOf[i] = getValidatedInput("Total marks:");
            }
        }
    }

    private static boolean confirmAndModifyScores(String category, double[] scores, double[] outOf, int count) {
        System.out.println("Would you like to continue with these marks for " + category + " or modify them?");
        System.out.println("1. Continue\n2. Modify");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        if (choice == 2) {
            modifyScores(category, scores, outOf, count);
            return false;
        }
        return true;
    }

    private static void modifyScores(String category, double[] scores, double[] outOf, int count) {
        System.out.println("Which " + category + " would you like to modify? Enter number (1-" + count + "):");
        int itemToModify = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline left-over
        if (itemToModify >= 0 && itemToModify < count) {
            enterScore(category, itemToModify, scores, outOf);
        }
    }

    private static void enterScore(String category, int index, double[] scores, double[] outOf) {
        scores[index] = getValidatedInput("Enter " + category + " " + (index + 1) + " score:");
        outOf[index] = getValidatedInput("Enter total marks for " + category + " " + (index + 1) + ":");
        while (scores[index] > outOf[index] || outOf[index] == 0) {
            System.out.println("Invalid input. Please re-enter:");
            scores[index] = getValidatedInput("Score:");
            outOf[index] = getValidatedInput("Total marks:");
        }
    }

    private static double getValidatedInput(String prompt) {
        while (true) {
            System.out.println(prompt);
            if (scanner.hasNextDouble()) {
                double input = scanner.nextDouble();
                scanner.nextLine(); // Consume newline left-over
                return input;
            } else {
                System.out.println("Invalid input. Please enter a numeric value.");
                scanner.next(); // Consume the invalid input
            }
        }
    }

    private static double calculateFinalScore(double[][] scores, double[][] outOf, double[] weightages) {
        double finalScore = 0;
        for (int i = 0; i < scores.length; i++) {
            finalScore += calculateCategoryScore(scores[i], outOf[i], weightages[i]);
        }
        return finalScore;
    }

    private static double calculateCategoryScore(double[] scores, double[] outOf, double weightage) {
        double totalPercentage = 0;
        for (int i = 0; i < scores.length; i++) {
            totalPercentage += (scores[i] / outOf[i]) * 100;
        }
        return (totalPercentage / scores.length) * weightage;
    }

    private static void displayResults(String name, String courseName, String[] categories, double[][] scores,
            double[][] outOf, double[] weightages, double totalScore) {
        System.out.println("Student Name: " + name);
        System.out.println("Course Name: " + courseName);
        System.out.println("Here are the category-wise marks %:");
        for (int i = 0; i < categories.length; i++) {
            double categoryScore = calculateCategoryScore(scores[i], outOf[i], weightages[i]);
            System.out.println(categories[i] + ": " + String.format("%.2f", categoryScore) + "% of total grade");
        }
        System.out.println("Total grade for " + courseName + ": " + String.format("%.2f", totalScore) + "%.");
    }

    private static void displayGradeMessage(String name, double score) {
        String grade;
        String message;

        if (score >= 92) {
            grade = "A";
            message = "Congratulations " + name + ", hard work paid off!";
        } else if (score >= 90) {
            grade = "A-";
            message = "Congratulations " + name
                    + ", you got an A- Grade, you were almost there, a little bit more hard work will help you next time.";
        } else if (score >= 85) {
            grade = "B";
            message = "Good job " + name + ", you got a B Grade. Keep pushing for higher.";
        } else if (score >= 80) {
            grade = "B-";
            message = "Well done " + name + ", you got a B- Grade. Aim higher next time.";
        } else if (score >= 75) {
            grade = "C";
            message = "Nice effort " + name + ", you got a C Grade. Let's work to improve.";
        } else if (score >= 70) {
            grade = "C-";
            message = name + ", you got a C- Grade. Focus a bit more to get better results.";
        } else if (score >= 65) {
            grade = "D";
            message = name + ", you got a D Grade. There's room for improvement.";
        } else if (score >= 60) {
            grade = "D-";
            message = name + ", you got a D- Grade. Let's put in more effort next time.";
        } else if (score >= 55) {
            grade = "E";
            message = name + ", you got an E Grade. Hard work is key to progress.";
        } else if (score >= 50) {
            grade = "E-";
            message = name + ", you got an E- Grade. Push harder and you can do it.";
        } else {
            grade = "F";
            message = "Hey " + name + ", you need attention in class and do some hard work, better luck next time.";
        }

        System.out.println("Your letter grade: " + grade);
        System.out.println(message);
    }

    private static boolean areScoresInitialized(double[] scores) {
        for (double score : scores) {
            if (score == -1) {
                return false;
            }
        }
        return true;
    }
}
