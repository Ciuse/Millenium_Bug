package it.polimi.ingsw.ps31.model.choiceType;

/**
 * Created by Giuseppe on 15/06/2017.
 *
 * Classe astratta che rappresenta una possibile scelta.
 * Il model dovrà mettersi in attesa e aspettare che il player risponda alla domanda
 * Ogni scelta implementa il pattern visitator perciò sa come visitarsi e quale metodo invocare una volta
 * che raggiunge la view destinataria.
 *
 * @see ChoiceVisitable
 * @see MVChoiceInfoVisitor
 * @see it.polimi.ingsw.ps31.messages.messageMV.MVAskChoice
 */
public abstract class ChoiceType implements ChoiceVisitable {

}
