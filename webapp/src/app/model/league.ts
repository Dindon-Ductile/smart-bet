export interface League {
  id: number;
  name: string;
  logoUrl: string;
  country: Country;
  isCurrent?: boolean;
  seasonStart: number;
  seasonEnd: number;
}

export interface SimpleLeague {
  id: number;
  name: string;
  country: string;
  logoUrl: string;
  flagUrl: string;
}

export interface Country {
  id?: string;
  name: string;
  flagUrl?: string;
}

export interface Leagues {
  userLeagues: League[];
  availableLeagues: League[];
}

export interface LeagueId {
  leagueId: number;
}
