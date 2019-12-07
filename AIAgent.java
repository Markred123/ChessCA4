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
      Stack backupMove = (Stack) whitePossibilitiesStack.clone();
      Stack bStack = (Stack) blackPossibilitiesStack.clone();
      Move bestMove = null;
      Move whiteMove;
      Move presentMove;
      Square blackPosition;
      int strength = 0;
      int chosenPieceStrength = 0;

      while (!whitePossibilitiesStack.empty()) {
          whiteMove = (Move) whitePossibilitiesStack.pop();
          presentMove = whiteMove;



          //compare white landing positions to black positions, return capture if available or random if not.
          while (!bStack.isEmpty()) {
              strength = 0;
              blackPosition = (Square) bStack.pop();
              if ((presentMove.getLanding().getXC() == blackPosition.getXC()) && (presentMove.getLanding().getYC() == blackPosition.getYC())) {

                  //Assign strength to pieces
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
              //updating the best move
              if (strength > chosenPieceStrength) {
                  chosenPieceStrength = strength;
                  bestMove = presentMove;
              }
          }
          //reloading the black squares
          bStack = (Stack) blackPossibilitiesStack.clone();
      }

      // Make the best move if not available make a random move.
      if (chosenPieceStrength > 0) {
          System.out.println("Selected AI Agent - Next best move: " +chosenPieceStrength);
          return bestMove;
      }

      return randomMove(backupMove);

  }
  public Move twoLevelsDeep(Stack possibilities){
    Move selectedMove = new Move();
    return selectedMove;
  }
}
