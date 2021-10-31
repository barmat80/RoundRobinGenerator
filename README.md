# Round Robin Generator

## Scope

This is a generator of the schedule (i.e. matches) of a Round-Robin Tournament.

You can pass a list of competitors (players, teams or whatever you want) via simple text file (1 competitor per line).
For an example, see playerList.txt in "res" directory.

See https://en.wikipedia.org/wiki/Round-robin_tournament for more information.

### Remark:

If the competitors are odd, a fake one called "Rest" is created automatically in order to realize all the possible combination.
In this way, the program generates a "bye" (tennis term for "no play") for the competitor that is scheduled against the fake one.
In the output file you will see something like "Rest: Competitor" as the last line of the schedule

## Usage

java -jar roundRobinGenerator.jar com.mb.Main "C:\mydir\list.txt" "C:\mydir\output.txt" 
