import { Component, OnInit } from '@angular/core';
import { Game } from '../game';
import { GameService } from '../game.service';

@Component({
  selector: 'app-games',
  templateUrl: './games.component.html',
  styleUrls: ['./games.component.css']
})
export class GamesComponent implements OnInit {

  selectedGame: Game;
  games: Game[];


  constructor(private gameService: GameService) {}

  ngOnInit() {
    this.getGames();
  }

  onSelect(game: Game): void {
    this.selectedGame = game;
  }

  onSubmit(selectedGame): void {
    this.gameService.updateGame(selectedGame).subscribe();
  }

  getGames(): void {
    this.gameService.getGames()
    .subscribe(games => this.games = games);
  }

}
