# MendelToText
Takes as input two files, containing :
- a list of common words
- the list of all element's symbols of the Mendeleïev

For each word, the program will look into the element's symbols list and check if it can be written with them.

If it can, the word will be written in a file with all it's ways to be written with element's symbols.

This program will remove all accents but cannot remove some characters : "'[]+-{}!;:.123456789 (etc)
Any word containing one of these caracters will not be parse.

## How to use it ?
First, get an IDE (Eclipse)

1. Download Eclipse (The last version)
2. Install Eclipse
3. Launch Eclipse

Next, clone / download this project

Go to Eclipse and follow the procedure :

File -> Open project from File system -> Directory -> [go to the project] -> Select the project with the checkbox -> Finish

Then, you should have access to the project.

To launch it, you can :
1. click the run button
2. ctrl + f11
3. use the menu : Run -> Run

To change it : open the project from the Eclipse window -> open the src folder -> open the launcher package (folder) -> double click on Launcher.java

## RESSOURCES
This project uses only one resource file, the excel that store the encounter tables.

This file is stored in the resource folder of the EncounterRPG project.

You can update them as much as you want (adding/removing rows of data).