import {SimpleLeague} from './league';

export interface Fixture {
  fixtureId: number;
  leagueId: number;
  startsAt: number;
  startsAtDay: string;
  startsAtTime: string;
  venue: string;
  homeTeam: SimpleTeam;
  awayTeam: SimpleTeam;
}

export interface FixturesAtDate {
  date: string;
  fixtures: Fixture[];
}

export interface UserLeaguesFixtures {
  fixturesByDate: FixturesAtDate[];
  leagues: SimpleLeague[];
}

export interface SimpleTeam {
  id: number;
  name: string;
  logoUrl: string;
}
