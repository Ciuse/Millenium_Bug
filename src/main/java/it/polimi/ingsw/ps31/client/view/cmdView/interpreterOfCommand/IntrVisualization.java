package it.polimi.ingsw.ps31.client.view.cmdView.interpreterOfCommand;

import it.polimi.ingsw.ps31.client.view.cmdView.CmdLineView;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewDevelopmentCard;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewLeaderCard;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceType;

import static java.lang.String.valueOf;

/**
 * Created by Giuseppe on 08/06/2017.
 */
public class IntrVisualization implements CmdInterpreterView {
    @Override
    public void notGameMessageInterpreter(CmdLineView cmdLineView, Character in) {
        //la view si gestisce i suoi comandi base in locale ( quelli di mera visualizzazione)
        if(in.compareTo('1')==0) {
            cmdLineView.printLastEvent("inserisci il numero della carta (usa 2 cifre)");
            Character character = cmdLineView.inputVis1();
            if(character!=null && character.compareTo('0')>=0 &&character.compareTo('9')<=0) {
                Character character2 = cmdLineView.inputVis1();
                if(character2!=null && character2.compareTo('0')>=0 &&character2.compareTo('9')<=0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(character);
                    stringBuilder.append(character2);


                    int cardNumber = Integer.parseInt(stringBuilder.toString());
                    System.out.println(cardNumber);

                    if (cardNumber > 0 && cardNumber <= 96) {
                        for (StateViewDevelopmentCard cardToPrint : cmdLineView.getStateViewGame().getStateViewDevelopmentCardList()
                                ) {
                            if (cardToPrint.getCardId() == cardNumber) {
                                cmdLineView.printDevelopmentCard(cardNumber);
                                cmdLineView.printLastEvent(">Carta: "+valueOf(cardNumber)+" stampata");
                            }
                        }
                    }
                    else {
                        cmdLineView.printLastEvent("hai inserito un cardId non valido");
                    }
                }
                else {
                    cmdLineView.printLastEvent("Non hai inserito un numero");
                }
            }
            else cmdLineView.printLastEvent("Non hai inserito un numero");
        }

        if(in.compareTo('2')==0) {
            String leaderName = "Possiedi";

            if (!cmdLineView.getMyStateViewPlayer().getStateViewLeaderCardList().isEmpty()) {
                for (StateViewLeaderCard leaderCard : cmdLineView.getMyStateViewPlayer().getStateViewLeaderCardList()
                        ) {
                    leaderName = leaderName + leaderCard.getLeaderName() + "/ ";
                }
                cmdLineView.printLastEvent(leaderName);
            }
            else cmdLineView.printLastEvent("Non possiedi Leader");
        }

        if(in.compareTo('3')==0) {
        }

        if(in.compareTo('4')==0) {
        }

        if(in.compareTo('5')==0) {
        }
        cmdLineView.setCmdInterpreterView(cmdLineView.getLastInterpreterView());
    }

    @Override
    public boolean messageInterpreter(CmdLineView terminalView, ChoiceType choiceType, Character in) {
return false;
    }

    @Override
    public boolean messageInterpreter2(CmdLineView terminalView, ChoiceType choiceType, Character in1, Character in2) {
        return false;
    }

    @Override
    public String toString() {
        return "IntrVisualization";
    }
}
