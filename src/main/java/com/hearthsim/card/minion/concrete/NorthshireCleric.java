package com.hearthsim.card.minion.concrete;

import com.hearthsim.card.minion.Minion;
import com.hearthsim.card.minion.MinionHealedInterface;
import com.hearthsim.event.effect.CardEffectCharacter;
import com.hearthsim.event.effect.CardEffectCharacterDraw;
import com.hearthsim.model.PlayerSide;
import com.hearthsim.util.tree.CardDrawNode;
import com.hearthsim.util.tree.HearthTreeNode;

/**
 * Northshire Cleric
 *
 * @author oyachai
 *
 * This minion is a 1 mana, 1 attack, 3 health minion.
 * Whenever a minion is healed, this minion draws a card for its player.
 *
 */
public class NorthshireCleric extends Minion implements MinionHealedInterface {

    private static final CardEffectCharacter effect = new CardEffectCharacterDraw(1);

    public NorthshireCleric() {
        super();
    }

    /**
     *
     * Called whenever another character (including the hero) is healed
     *
     *
     * @param thisMinionPlayerSide
     * @param healedMinionPlayerSide
     * @param healedMinion The healed minion
     * @param boardState The BoardState before this card has performed its action.  It will be manipulated and returned.
     * @return The boardState is manipulated and returned
     */
    @Override
    public HearthTreeNode minionHealedEvent(PlayerSide thisMinionPlayerSide, PlayerSide healedMinionPlayerSide, Minion healedMinion, HearthTreeNode boardState) {
        return NorthshireCleric.effect.applyEffect(thisMinionPlayerSide, this, thisMinionPlayerSide, 0, boardState);
    }
}
