package com.hearthsim.card.minion.concrete;

import com.hearthsim.card.Card;
import com.hearthsim.card.minion.Minion;
import com.hearthsim.card.minion.MinionTargetableBattlecry;
import com.hearthsim.event.EffectMinionAction;
import com.hearthsim.event.MinionFilterTargetedBattlecry;
import com.hearthsim.exception.HSException;
import com.hearthsim.model.BoardModel;
import com.hearthsim.model.PlayerSide;
import com.hearthsim.util.tree.HearthTreeNode;

public class CruelTaskmaster extends Minion implements MinionTargetableBattlecry {

    /**
     * Battlecry: Deal 1 damage to a minion and give it +2 Attack
     */
    private final static MinionFilterTargetedBattlecry filter = new MinionFilterTargetedBattlecry() {
        protected boolean includeEnemyMinions() { return true; }
        protected boolean includeOwnMinions() { return true; }
    };

    private final static EffectMinionAction<Minion> battlecryAction = new EffectMinionAction<Minion>() {
        @Override
        public HearthTreeNode applyEffect(PlayerSide originSide, Minion origin, PlayerSide targetSide, int targetCharacterIndex, HearthTreeNode boardState) throws HSException {
            Minion targetMinion = boardState.data_.modelForSide(targetSide).getCharacter(targetCharacterIndex);
            HearthTreeNode toRet = boardState;
            targetMinion.setAttack((byte)(targetMinion.getAttack() + 2));
            toRet = targetMinion.takeDamageAndNotify((byte) 1, PlayerSide.CURRENT_PLAYER, targetSide, toRet, false, true);
            return toRet;
        }
    };

    public CruelTaskmaster() {
        super();
    }

    @Override
    public boolean canTargetWithBattlecry(PlayerSide originSide, Card origin, PlayerSide targetSide, int targetCharacterIndex, BoardModel board) {
        return CruelTaskmaster.filter.targetMatches(originSide, origin, targetSide, targetCharacterIndex, board);
    }

    @Override
    public HearthTreeNode useTargetableBattlecry_core(PlayerSide originSide, Minion origin, PlayerSide targetSide, int targetCharacterIndex, HearthTreeNode boardState) throws HSException {
        return CruelTaskmaster.battlecryAction.applyEffect(originSide, origin, targetSide, targetCharacterIndex, boardState);
    }
}
