# Round Robin Generator

## Scope

This is a CLI generator of fixtures of a Round-Robin Tournament.

You can pass a list of competitors (players, teams or whatever you want) via simple text file (1 competitor per line).
For an example, see playerList.txt in "resources" directory.

See https://en.wikipedia.org/wiki/Round-robin_tournament for more information.

#### Remark:

When competitors are odd, a fake one called "Rest" is created automatically in order to create all the combinations.
In this way, the program generates a "bye" (tennis term for "no play") for the competitor that is scheduled against the fake one.
In the output file you will see something like "Rest: Competitor Name" as the last line of the Matchday.

## Usage

java -jar roundRobinGenerator.jar C:\mydir\list.txt C:\mydir\output.txt 
