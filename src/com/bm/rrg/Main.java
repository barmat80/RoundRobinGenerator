package com.bm.rrg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.bm.rrg.logic.RoundRobinGenerator;
import com.bm.rrg.model.Competitor;

public class Main {

	public static void main(String[] args) throws IOException {
		Utilities.debug(Arrays.toString(args));

		boolean canGenerate = true;

		if (args.length == 2) {
			if(args[0].isEmpty()) {
				Utilities.log("File with player/team list not provided.\nPlease provide a fullpath");
				canGenerate = false;
			}

			if (args[1].isEmpty()) {
				Utilities.log("Output File not provided.\nPlease provide a fullpath)");
				canGenerate = false;
			}
		} else {
			canGenerate = false;
			Utilities.log(
					"Incorrect Parameters\nUsage: java -jar roundRobinGenerator.jar com.mb.Main c:\\mydir\\inputList.txt c:\\mydir\\output.txt");
		}

		if (canGenerate) {
			ArrayList<Competitor> competitors = Utilities.readTextFile(args[0]);
			RoundRobinGenerator rrg = new RoundRobinGenerator(competitors);
			rrg.create();
			rrg.writeToFile(args[1]);

			Utilities.log("Round Robin Generated.\nSee file in " + args[1]);
		}
	}

}
