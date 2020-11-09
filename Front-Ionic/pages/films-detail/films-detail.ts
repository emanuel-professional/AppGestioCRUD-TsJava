import { Component } from '@angular/core';
import {  NavController, NavParams } from 'ionic-angular';
import { FilmsPage } from '../films/films';
import { Film } from '../../shared/film';
import { AlertController } from 'ionic-angular';
import { FilmService } from '../../providers/film-service';
/**
 * Generated class for the FilmsDetailPage page.
 *
 * See http://ionicframework.com/docs/components/#navigation for more info
 * on Ionic pages and navigation.
 */
@Component({
  selector: 'page-films-detail',
  templateUrl: 'films-detail.html',
})
export class FilmsDetailPage {

  private film: Film;
  private titleOld : string;
  private error: string;
  private titleFilm : string;
  private directorFilm : string;
  private descripFilm :string;
  private year : string;
  private nFilm : Film;

  constructor(public navCtrl: NavController, public navParams: NavParams,private filmService: FilmService,public alertCtrl: AlertController) {
    this.film = navParams.get('paramfilm');
    this.titleOld = navParams.get('titleOld');
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad FilmsDetailPage');
  }

    goBack(): void {
      this.navCtrl.push(FilmsPage);
    }

    save(): void {
      if(this.titleFilm == null || this.titleFilm == null
      || this.directorFilm == null || this.descripFilm == null
      || this.year == null) this.showError();
      else{
        this.nFilm = new Film(this.titleFilm,this.directorFilm,this.descripFilm,this.year);
        this.filmService.insert(this.nFilm)
          .then(() => {this.goBack()}).catch(error => this.error = error ).catch(error => this.error = error);
          this.showAlert(this.nFilm);
          this.goBack();
      }

      this.titleFilm = null;
      this.directorFilm = null;
      this.descripFilm = null;
      this.year = null;
    }

    update(film : Film): void {
      this.filmService.update(this.film,this.titleOld)
        .then(() => {this.goBack()}).catch(error => this.error = error);
    }

    showError(){
      let alert = this.alertCtrl.create({
        title:"Error, enter data",
        subTitle: "You produce an error because you have to enter a value data",
        buttons: ['OK']
      });
      alert.present();
    }

    showAlert(film: Film) {
      let alert = this.alertCtrl.create({
        title:"Successfully added",
        subTitle: "You added a film named "+film.title,
        buttons: ['OK']
      });
      alert.present();
    }

    _keyPress(event: any) {
      const pattern = /[0-9\+\-\ ]/;
      let inputChar = String.fromCharCode(event.charCode);

      if (!pattern.test(inputChar)) {
        event.preventDefault();
      }
    }

}
