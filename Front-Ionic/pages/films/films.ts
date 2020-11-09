import { OnInit} from '@angular/core';
import { Component } from '@angular/core';
import { NavController ,NavParams} from 'ionic-angular';
import { FilmService } from '../../providers/film-service';
import {Film} from '../../shared/film';
import { AlertController } from 'ionic-angular';
import {FilmsDetailPage } from '../films-detail/films-detail';
/**
 * Generated class for the FilmsPage page.
 *
 * See http://ionicframework.com/docs/components/#navigation for more info
 * on Ionic pages and navigation.
 */
@Component({
  selector: 'page-films',
  templateUrl: 'films.html',
})
export class FilmsPage {

  private films: Film[];
  private selectedFilm: Film;
  private error: string;

  constructor(public navCtrl: NavController,public navParams : NavParams,private filmService: FilmService,public alertCtrl: AlertController) {
  }

  ngOnInit() {
    this.getFilms();
  }

  getFilms(): void {
    this.filmService.getAllFilms().then(films => this.films = films).catch(error => this.error = error  );
  }

  onSelect(film: Film): void {
    this.selectedFilm = film;
  }

  gotoDetail(film: Film): void {
      if(film != null)this.navCtrl.push(FilmsDetailPage, { paramfilm: film , titleOld : film.title});
      else this.navCtrl.push(FilmsDetailPage);

  }

  delete(film: Film): void {
    this.filmService
        .delete(film).then(() => {
          this.films = this.films.filter(e => e !== film);
          if (this.selectedFilm === film) { this.selectedFilm = null; }
        });
  }

  showAlert(film: Film) {
    let alert = this.alertCtrl.create({
      title: "Successfully deleted",
      subTitle: "You deleted this film : "+ film.title,
      buttons: ['OK']
    });
    alert.present();
  }

  presentConfirm(film : Film) {
    let alert = this.alertCtrl.create({
      title: 'Confirm delete',
      message: 'Do you want to delete this film?',
      buttons: [
        {
          text: 'Cancel',
          role: 'cancel',
          handler: () => {
          }
        },
        {
          text: 'Confirm',
          handler: () => {
              this.delete(film);
              this.showAlert(film);
          }
        }
      ]
    });
    alert.present();
  }


}
