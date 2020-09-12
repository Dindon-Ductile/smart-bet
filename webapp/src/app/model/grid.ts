import {Fixture} from './fixture';
import {CombinedBet} from './bet';

export interface UserBetGrids {
  fixtures: Fixture[];
  combinedBetResult: CombinedBetResult;
}

export interface CombinedBetResult {
  bet: CombinedBet;
  playableGrids: AdvancedBetGrid[];
}

export interface AdvancedBetGrid {
  id: string;
  entries: BetGridEntry[];
  combinedOdds: number;
  equitableMoneyBet: number;
  adjustedMoneyBet: number;
  equitableGain: number;
  adjustedGain: number;
}

export interface BetGridEntry {
  fixtureId: number;
  outcome: string;
  odds: number;
}
