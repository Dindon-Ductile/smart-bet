import {Fixture} from './fixture';

export interface CombinedBet {
  entries: SimpleBet[];
  betMoney: number;
}

export interface SimpleBet {
  fixture: Fixture;
  outcomes: SelectedBetOutcome[];
  active: boolean;
}

export interface UserCombinedBet {
  id: string;
  userId: string;
  entries: SimpleBet[];
  betMoney: number;
  createdAt: number;
  lastUpdatedAt?: number;
}

export interface SelectedBetOutcome {
  outcome: string;
  odds: number;
  selected: boolean;
}
