Hello! This is my summer project for AP Computer Science A! I chose to clone Worlde with some cool features.
While my teacher said not to spend your whole summer on it, I enjoyed building this project as it allowed me to learn and practice Java
instead of spending hours "learning" in tutorial hell. My C++ skills really helped with this project and using Netbeans is pretty cool. - 8/20/2024 9:24 P.M.

Thoughts and record log:

Hello! I began this project on 6/29/2024 and made significant progress, however, I made this README on 7/28/2024.
I began working on this project again on 7/28/2024 as I was busy with other work haha.

7/28/2024 - Finally made the word checker work, really wasn't that hard afterall. All it took was clean code. 
Realized I had to remove inappropriate words from the word file I extracted.
Problem: Outputting before I input??

7/29/2024 - I FIXED THE WORD CHECKER, I realized I didn't need to complicate it that much!
Found a bug where I was inputting the input letters instead of the word letters into the vector letter so it was causing all letters entered to exist as yellow.
IS IT IMPOSSIBLE TO ERROR TRAP????
The game is coming together wow, just need to figure out how to error trap... 11:14 P.M.
Made significant progress

8/1/2024 - I attempted to create a keyboard, plenty of bugs I need to fix to make it work.

8/5/2024 - FINALLY REALIZED WHY THE KEYBOARD WAS NOT WORKING AS IT WAS ONLY COLORING THE FIRST LETTER OF THE WORD!!! I DID NOT CORRELATE THE CORRECT 
CASING THE INPUT AND WORD THAT WAS SENT TO THE KEYBOARD LOOOL!
Keyboard works amazingly, if a character is marked yellow initially and found to be green, it will change. If it's green initialy, it will never change.

8/6/2024 - It is currently 12:32 AM, attempted to create a verification system and realized my words selection was not accounting for the github repository words, 
turns out I had two different words.txt files haha.
Having an issue with Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index -1 out of bounds for length 6
Fixed it... Turns out my position in array[position][i] = color; was being read as array[position - 1][i] = color; ??????

4:33 PM I fixed up the attempts counter, it was reading incorrectly.

8/7/2024 - 6:51 PM I created a history txt file to track the word history from a player and their attempts, little work today.

8/16/2024 - Started working again and implemented the history file. 

8/17/2024 - Fixed a lot of sections and implemented streak function to add spice. Touched up the project and essentially finished it, just need to comment

8/19/2024 - Commented

8/20/2024 - Finished the entire project, added more comments and wins feature to implement a double.