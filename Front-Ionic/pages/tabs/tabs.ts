import { Component } from '@angular/core';
import { FilmsPage } from '../films/films';
import { ActorsPage } from '../actors/actors';
import { HomePage } from '../home/home';

@Component({
  templateUrl: 'tabs.html'
})
export class TabsPage {

  tab3Root = HomePage;
  tab1Root = FilmsPage;
  tab2Root = ActorsPage;
  constructor() {

  }
}
