import {Fixture} from './fixture';
import {CombinedBet} from './bet';

export interface UserBetGrids {
  fixtures: Fixture[];
  combinedBetResult: CombinedBetResult;
}

export interface CombinedBetResult {
  bet: CombinedBet;
  playableGrids: AdvancedBetGrid[];
  actualMoneyAllocatedAmongstGrids: number;
}

export interface AdvancedBetGrid {
  id: string;
  entries: BetGridEntry[];
  combinedOdds: number;
  equitableMoneyOverview: BetGridMoneyOverview;
  adjustedMoneyOverview: BetGridMoneyOverview;
}

export interface BetGridMoneyOverview {
  moneyBet: number;
  comboBoosterPercentage: number;
  baseGain: number;
  comboBoosterGain: number;
  totalGain: number;
  combinedOdds: number;
}

export interface BetGridEntry {
  fixtureId: number;
  outcome: string;
  odds: number;
}
