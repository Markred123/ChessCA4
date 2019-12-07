import java.util.*;

public class AIAgent{
  Random rand;

  public AIAgent(){
    rand = new Random();
  }

/*
  The method randomMove takes as input a stack of potential moves that the AI agent
  can make. The agent uses a rondom number generator to randomly select a move from
  the inputted Stack and returns this to the calling agent.
*/

  public Move randomMove(Stack possibilities){

    int moveID = rand.nextInt(possibilities.size());
    System.out.println("Agent randomly selected move : "+moveID);
    for(int i=1;i < (possibilities.size()-(moveID));i++){
      possibilities.pop();
    }
    Move selectedMove = (Move)possibilities.pop();
    return selectedMove;
  }

  public Move nextBestMove(Stack whitePossibilitiesStack, Stack blackPossibilitiesStack) {
    //This stack has all the white possibilities
      Stack backupMove = (Stack) whitePossibilitiesStack.clone();
      //this stack has the black positions
      Stack bStack = (Stack) blackPossibilitiesStack.clone();
      //stores the best move selection
      Move bestMove = null;
      //stores the selected white move
      Move whiteMove;
      //stores the current move
      Move presentMove;
      //stores the black position squares
      Square blackPosition;
      // strength of the pieces for the second loop
      int strength = 0;
      //the strength of the chosen piece
      int chosenPieceStrength = 0;
// for every single white possibility the loop will cycle and try for every single possibility and check the result
      while (!whitePossibilitiesStack.empty()) {
        //every move that's tried is removed from the stack
          whiteMove = (Move) whitePossibilitiesStack.pop();
          //the move that's tried is selected as the present move
          presentMove = whiteMove;



          // this loop cycles through the black positions
          while (!bStack.isEmpty()) {
            // The strenght is always set back to 0
              strength = 0;
              // the black position is selected from the stack of black positions
              blackPosition = (Square) bStack.pop();
              // if the move selected is going to land on a black piece then the if statement occurs
              if ((presentMove.getLanding().getXC() == blackPosition.getXC()) && (presentMove.getLanding().getYC() == blackPosition.getYC())) {

                  // Different values are assigned to the pieces as per keith's tutorial, if none of them are the piece in the position, then it sets the value to 10 which must be the king
                  if (blackPosition.getName().equals("BlackQueen")) {
                      strength = 9;
                  } else if (blackPosition.getName().equals("BlackRook")) {
                      strength = 5;
                  } else if (blackPosition.getName().equals("BlackBishop") || blackPosition.getName().equals("BlackKnight")) {
                      strength = 3;
                  } else if (blackPosition.getName().equals("BlackPawn")) {
                      strength = 1;
                  } else {
                      strength = 10;
                  }
              }
              // a move is initially chosen and the strenght is set to the chosenPieceStrenght, this chosenPieceStrenght is not set back to 0 at the start of the loop
              // if the next move selected has a higher strenght then that move is set to the new chosenPieceStrenght and that move is set to the bestmove
              // this loops until every possibilty is chosen, in the end the piece with the highest strenght will be taken
              if (strength > chosenPieceStrength) {
                  chosenPieceStrength = strength;
                  bestMove = presentMove;
              }
          }
          // the black postion stack needs to be reloaded as we've removed items from it in the second loop
          bStack = (Stack) blackPossibilitiesStack.clone();
      }

      // if chosen pieces remains 0 then a random move is selected
      if (chosenPieceStrength > 0) {
          System.out.println("Selected AI Agent - Next best move: " +chosenPieceStrength);
          return bestMove;
      }

      return randomMove(backupMove);

  }
  public Move twoLevelsDeep(Stack possibilities){
    //loop through the black posibilities to figure out what would be considered the worst move for white to makes
    //could use similar algorithm to the above table but use negative values for the 'bad' moves
    Move selectedMove = new Move();
    return selectedMove;
  }
}
