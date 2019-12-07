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

  public Move nextBestMove(Stack whitePossibilitiesStack, Stack blackPositions) {

    //This stack has all the white possibilities
      Stack randomMoveBackup = (Stack) whitePossibilitiesStack.clone();

      //this stack has the black positions
      Stack bStack = (Stack) blackPositions.clone();

      //stores the best move selection
      Move bestMove = null;

      //the strength of the chosen piece
      int currentPieceStrength = 0;

      //stores the current move
      Move currentMove;

      //stores the black position squares
      Square blackPos;

      // strength of the pieces for the second loop
      int strength = 0;

      //stores the selected white move
      Move whiteMove;

// for every single white possibility the loop will cycle and try for every single possibility and check the result
      while (!whitePossibilitiesStack.empty()) {
        //every move that's tried is removed from the stack
          whiteMove = (Move) whitePossibilitiesStack.pop();
          //the move that's tried is selected as the present move
          currentMove = whiteMove;



          /*if((currentMove.getStart().getYC() < currentMove.getLanding().getYC()) && (currentMove.getLanding().getYC() < 3 )){
            strength = 0;
            if(strength > currentPieceStrength){
              currentPieceStrength = strength;
              bestMove = currentMove;
            }
          }*/

          // this loop cycles through the black positions
          while (!bStack.isEmpty()) {
            // The strenght is always set back to 0
              strength = 0;
              // the black position is selected from the stack of black positions
              blackPos = (Square) bStack.pop();
              // if the move selected is going to land on a black piece then the if statement occurs
              if ((currentMove.getLanding().getXC() == blackPos.getXC()) && (currentMove.getLanding().getYC() == blackPos.getYC())) {

                  // Different values are assigned to the pieces as per keith's tutorial, if none of them are the piece in the position, then it sets the value to 10 which must be the king
                  if (blackPos.getName().equals("BlackQueen")) {
                      strength = 9;
                  }

                  else if (blackPos.getName().equals("BlackRook")) {
                      strength = 5;
                  }

                  else if (blackPos.getName().equals("BlackBishop") || blackPos.getName().equals("BlackKnight")) {
                      strength = 3;
                  }

                  // the strength of the black pawn has been adjusted to reflect the strength for moving towards the centre of the board
                  else if (blackPos.getName().equals("BlackPawn")) {
                      strength = 1;
                  }
                  else {
                      strength = 10;
                  }
              }
              // a move is initially chosen and the strenght is set to the chosenPieceStrenght, this chosenPieceStrenght is not set back to 0 at the start of the loop
              // if the next move selected has a higher strenght then that move is set to the new chosenPieceStrenght and that move is set to the selectedMove
              // this loops until every possibilty is chosen, in the end the piece with the highest strenght will be taken
              if (currentPieceStrength < strength ) {
                  currentPieceStrength = strength;
                  bestMove = currentMove;
              }
          }


          // the black postion stack needs to be reloaded as we've removed items from it in the second loop
          bStack = (Stack) blackPositions.clone();
      }

      // if chosen pieces remains 0 then a random move is selected
      if (currentPieceStrength > 0) {
          System.out.println("The agent choose the next best move" +currentPieceStrength);
          return bestMove;
      }
      System.out.println("The strenght is" +strength);
      return randomMove(randomMoveBackup);

  }
  public Move twoLevelsDeep(Stack blackPossibilities, Stack whitePositions){
    //loop through the black posibilities to figure out what would be considered the worst move for white to makes
    //could use similar algorithm to the above table but use negative values for the 'bad' moves
    //This stack has all the white possibilities
    Stack blackPossibilitiesStack = (Stack) blackPossibilities.clone();




    Move selectedMove = new Move();
    return selectedMove;
  }
}
