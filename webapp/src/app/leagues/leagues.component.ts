import {Component, OnInit} from '@angular/core';
import {CdkDragDrop, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';
import {Country, League} from '../model/league';
import {LeaguesRepository} from '../services/leagues-repository.service';
import {MatSelectChange} from '@angular/material/select';

@Component({
    selector: 'app-leagues',
    templateUrl: './leagues.component.html',
    styleUrls: ['./leagues.component.scss']
})
export class LeaguesComponent implements OnInit {

    userLeagues: League[] = [];
    availableLeagues: League[] = [];
    filteredLeagues: League[] = [];
    countries: Country[] = [];
    selectedCountryId = 'FR';


    drop(event: CdkDragDrop<League[]>): any {
        if (event.previousContainer === event.container) {
            moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
        } else {
            transferArrayItem(event.previousContainer.data,
                event.container.data,
                event.previousIndex,
                event.currentIndex);
        }
    }

    constructor(private leaguesRepository: LeaguesRepository) {
    }

    ngOnInit(): void {
        this.leaguesRepository.getLeagues().subscribe(leagues => {
            this.userLeagues = leagues.userLeagues;
            this.availableLeagues = leagues.availableLeagues;
            this.filterLeaguesByCountry('FR');
            this.countries = [...new Set(this.availableLeagues.map(item => item.country))]
                .filter(country => !!country.id);
        });
    }

    doFilter($event: MatSelectChange): any {
        const value: string = $event.value;
        this.filterLeaguesByCountry(value);
    }

    filterLeaguesByCountry(countryId: string): any {
        this.filteredLeagues = this.availableLeagues.filter(league => league.country.id === countryId);
    }
}
