package code;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicios {
	
	public int Day1Part1() {
		int increasement = 0;
		int previousMeasurement = Integer.MAX_VALUE;
		int currentMeasurement = 0;
		ArrayList<String> collection = inputToStringCollection();
		previousMeasurement = Integer.valueOf(collection.get(0));
		for (int i = 1; i < collection.size(); i++) {
			currentMeasurement = Integer.valueOf(collection.get(i));
			if (currentMeasurement > previousMeasurement) {
				increasement++;
			}
			previousMeasurement = currentMeasurement;
		}
		return increasement;
	}
	
	public int Day1Part2() {
		int increasement = 0;
		int previousMeasurementSum = Integer.MAX_VALUE;
		int currentMeasurementSum = 0;
		int measurement1 = 0;
		int measurement2 = 0;
		int measurement3 = 0;
		ArrayList<String> collection = inputToStringCollection();
		measurement1 = Integer.valueOf(collection.get(0));
		measurement2 = Integer.valueOf(collection.get(1));
		for (int i = 2; i < collection.size(); i++) {
			measurement3 = Integer.valueOf(collection.get(i));
			currentMeasurementSum = measurement1 + measurement2 + measurement3;
			if (currentMeasurementSum > previousMeasurementSum) {
				increasement++;
			}
			previousMeasurementSum = currentMeasurementSum;
			measurement1 = measurement2;
			measurement2 = measurement3;
		}
		return increasement;
	}
	
	public int Day2Part1() {
		int depth = 0;
		int horizontalPosition = 0;
		int movement = 0;
		String direction;
		Scanner input = new Scanner(System.in);
		
		do {
			direction = input.next();
			if (direction.equals("stop")) {
				break;
			}
			
			movement = input.nextInt();
			
			switch (direction) {
				case "forward":
					horizontalPosition+= movement;
					break;
				case "up":
					depth-= movement;
					break;
				case "down":
					depth+= movement;
					break;
			}
		} while (input.hasNextLine());
		
		return depth * horizontalPosition;
	}
	
	public int Day2Part2() {
		int depth = 0;
		int horizontalPosition = 0;
		int aim = 0;
		int movement = 0;
		String direction;
		Scanner input = new Scanner(System.in);
		
		do {
			direction = input.next();
			if (direction.equals("stop")) {
				break;
			}
			movement = input.nextInt();
			
			switch (direction) {
				case "foward":
					horizontalPosition+= movement;
					depth+= aim * movement;
					break;
				case "up":
					aim-= movement;
					break;
				case "down":
					aim+= movement;
					break;
			}
		} while (input.hasNextLine());
		
		return depth * horizontalPosition;
	}
	
	public int Day3Part1() {
		int gammaRate = 0;
		int epsilonRate = 0;
		String gammaRateString = "";
		String epsilonRateString = "";
		
		ArrayList<String> collection = inputToStringCollection();
		int mostCommonValue = -1;
		
		for (int i = 0; i < collection.get(0).length(); i++) {
			mostCommonValue = mostCommonValueAtIndex(collection, i);
			gammaRateString += mostCommonValue;
			epsilonRateString+= (mostCommonValue == 1) ? 0 : 1;
		}
		
		gammaRate = binaryStringToInteger(gammaRateString);
		epsilonRate = binaryStringToInteger(epsilonRateString);
		
		return gammaRate * epsilonRate;
	}
	
	public int Day3Part2() {
		ArrayList<String> binaryNumbers = inputToStringCollection();
		ArrayList<String> oxygenGenerator = new ArrayList<String>();
		ArrayList<String> co2Scrubber = new ArrayList<String>();
		
		for (int i = 0; i < binaryNumbers.size(); i++) {
			String binaryReaded = binaryNumbers.get(i);
			oxygenGenerator.add(binaryReaded);
			co2Scrubber.add(binaryReaded);
		}
		
		int index = 0;
		int mostCommonValue;
		while (oxygenGenerator.size() > 1) {
			ArrayList<String> toBeRemovedOxygen = new ArrayList<String>();
			String binaryReaded;
			mostCommonValue = mostCommonValueAtIndex(oxygenGenerator, index);
			if (mostCommonValue == 1 || mostCommonValue == -1) {
				for (int i = 0; i < oxygenGenerator.size(); i++) {
					binaryReaded = oxygenGenerator.get(i);
					if (binaryReaded.charAt(index) == '0') {
						toBeRemovedOxygen.add(binaryReaded);
					}
				}
				
			}
			if (mostCommonValue == 0) {
				for (int i = 0; i < oxygenGenerator.size(); i++) {
					binaryReaded = oxygenGenerator.get(i);
					if (binaryReaded.charAt(index) == '1') {
						toBeRemovedOxygen.add(binaryReaded);
					}
				}
			}
			for (int i = 0; i < toBeRemovedOxygen.size(); i++) {
				oxygenGenerator.remove(toBeRemovedOxygen.get(i));
			}
			index++;
		}
		
		index = 0;
		
		while (co2Scrubber.size() > 1) {
			
			ArrayList<String> toBeRemovedCO2 = new ArrayList<String>();
			String binaryReaded;
			mostCommonValue = mostCommonValueAtIndex(co2Scrubber, index);
			if (mostCommonValue == 1 || mostCommonValue == -1) {
				for (int i = 0; i < co2Scrubber.size(); i++) {
					binaryReaded = co2Scrubber.get(i);
					if (binaryReaded.charAt(index) == '1') {
						toBeRemovedCO2.add(binaryReaded);
					}
				}
				
			}
			if (mostCommonValue == 0) {
				for (int i = 0; i < co2Scrubber.size(); i++) {
					binaryReaded = co2Scrubber.get(i);
					if (binaryReaded.charAt(index) == '0') {
						toBeRemovedCO2.add(binaryReaded);
					}
				}
				
			}
			for (int i = 0; i < toBeRemovedCO2.size(); i++) {
				co2Scrubber.remove(toBeRemovedCO2.get(i));
			}
			index++;
		}
		
		int oxygenGeneratorRating = binaryStringToInteger(oxygenGenerator.get(0));
		int co2ScrubberRating = binaryStringToInteger(co2Scrubber.get(0));
		
		return oxygenGeneratorRating * co2ScrubberRating;
	}
	
	private ArrayList<String> inputToStringCollection() {
		ArrayList<String> binaryNumbers = new ArrayList<String>();
		String binaryReaded;
		Scanner input = new Scanner(System.in);
		boolean itsDone = false;
		do {
			binaryReaded = input.next();
			if (!binaryReaded.equals("stop")) {
				binaryNumbers.add(binaryReaded);
			}
			else {
				itsDone = true;
			}
			
		} while (input.hasNextLine() && !itsDone);
		return binaryNumbers;
	}
	
	private int binaryStringToInteger(String binary) {
		int binaryInteger = 0;
		int pot = 0;
		for (int i = binary.length()-1; i >= 0; i--) {
			if (binary.charAt(i) == '1') {
				binaryInteger+= Math.pow(2, pot);
			}
			pot++;
		}
		return binaryInteger;
	}
	
	private int mostCommonValueAtIndex(ArrayList<String> collection, int index) {
		int cantOf1 = 0;
		int cantOf0 = 0;
		int mostCommonValue = -1;
		
		for (int i = 0 ; i < collection.size(); i++) {
			if (collection.get(i).charAt(index) == '1') {
				cantOf1++;
			}
			if (collection.get(i).charAt(index) == '0') {
				cantOf0++;
			}
		}
		
		if (cantOf0 != cantOf1) {
			mostCommonValue = (cantOf0 > cantOf1) ? 0 : 1;
		}
		return mostCommonValue;
	}
}
