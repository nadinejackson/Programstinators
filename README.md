# Team Programstinators
### Tabassum Fabiha, Nadine Jackson, Cheryl Qian

##Setup - How to Compile and Run
Open up a new terminal window, and navigate to the directory in which you'd like the game to be installed. Then, type
    $ git clone git@github.com:nadinejackson/Programstinators.git
and hit enter to clone the files of the game repository into your local directory.
Once installed, type
    $ javac Woo.java
and hit enter to compile the driver file.
Once compiled and ready to play, type 
    $ java Woo
to begin the game.
Prompts and basic rules will be displayed as necessary. Have fun!

## Description:

A text-based game of Mafia where the player is randomly assigned a role of Citizen,
Doctor, Detective, or Mafia, competes against other pre-programmed players, and attempts to survive.
The user begins the game by selecting or being randomly assigned a role. When 
night falls, the Mafia kills off one player. The Doctor can select a player to heal, and if 
healed, the killed player survives the night. Roles of all players are revealed upon death.  
When the night ends and day comes, the killed player’s role is revealed. Evidence 
and hints are left at the scene, but the user gains SP (suspicious points) if they choose to 
snoop. The user can interrogate the players, as well as provide alibis. These 
interrogations and dialogues are shown to the townspeople and the user to determine 
who is guilty. At the end of the day, the townspeople cast their votes. The Detectives’ 
vote are worth more than the Citizens’, and the highest voted player is killed/burned at 
the stake. The players have a SP (suspicious points) stat, based on the cumulative 
number of votes they receive, as well as the value of their chosen alibis. The user can 
factor this into their decision when voting.

## Rules:

- User is initially prompted with a Start Menu, where rules and commands are 
displayed. They set their name, the number of players, and their role ( or opt to 
have it randomized ) 
- After selection, a list of all players, their names, SP, number of votes, day roles, and a short 
quote from them is displayed. 

###Game Commands
- To pull up the list of rules at any time, enter
    $ rules
- To pull up a list of all players and their updated stats, enter
    $ players
- To quit the game at any time, enter
    $ quit
- To end the day before actions are used up, enter
    $ sleep
- To open the notebook and read or edit its contents, enter
    $ nb

### Night 
- If the user has extra abilities, they are prompted to choose which player perform 
those abilities on. 
   *Kill:* Displays success or failure based on Doctor’s actions 
   *Heal:* Displays success if Mafia killed the same player, else failure 
   *Investigate:* Displays the real role of the player

### Day  
- Starts with a short description of the killed player and death scene. 
- User is prompted by a list of actions (*Investigate, Eavesdrop, Converse, 
Defend*) 
   Converse: The user can choose who to converse with, and 3 questions to select from.   
   Investigate: Check the death scene for evidence, at the risk of gaining SP. 
   Eavesdrop: Listen to dialogue between two other players, at the risk of gaining SP. 
   Defend: The player wakes up to questions/accusations every day. Defend shows the 
   question(s) and 3 possible alibis to choose from for each.  
   Open notebook : The player has a notebook where they can store clues and other information      that they have found. They have the option to read it, write in it, or erase entries.
 
###Voting
Once the daily actions are used up, the user is prompted to vote on the person they most suspect. After voting, if there is no tie, then the person who has the greatest number of votes is hanged. An updated stats list is displayed, and the players go home for the night. The cycle restarts

###Win
If you're not a mafia member, you win when all the mafia members are killed.
If you're a member of the mafia, you win when the total number of mafia members exceeds the total number of townspeople.

###Lose
If you're not a mafia member, you lose when you're hanged, killed, or when the number of mafia members is equal to or greater than the number of townspeople.
Else, you lose when you're uncovered by the townspeople and hanged.
