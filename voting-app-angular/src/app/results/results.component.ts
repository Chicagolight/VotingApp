import {Component, OnInit} from '@angular/core';
import {Game} from '../game';
import {GameService} from '../game.service';

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.css']
})
export class ResultsComponent implements OnInit {

  games: Game[] = [];

  constructor(private gameService: GameService) {}

  ngOnInit() {
    this.getGames();
  }

  getGames(): void {
    this.gameService.getGames()
    .subscribe(games => this.games = games);
  }

}
