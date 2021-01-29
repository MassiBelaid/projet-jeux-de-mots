import { Component, OnInit } from '@angular/core';
import {JdmServiceService} from '../services/jdm-service.service';

@Component({
  selector: 'app-jdm',
  templateUrl: './jdm.component.html',
  styleUrls: ['./jdm.component.css']
})
export class JdmComponent implements OnInit {
  mot: string;
  termes: any[] = new Array();
  termesDejaUtilises: string[];
  termesCompletion: any[];
  content: any[] = new Array();
  counter: number;

  constructor(private jdmService: JdmServiceService) { }

  ngOnInit() {
    this.mot = '';
    this.termesDejaUtilises = [];
    this.counter = 0;
  }

  reInit() {
    this.termesCompletion = [];
    this.mot = ' ';
    this.counter = 0;
    this.getData();
  }

  cherche() {
    this.content = [];
    this.jdmService.get(this.mot).subscribe(
      termes => this.termes = termes,
    error => console.log('erreur recup serveur'),
      () => this.reInit()
    );
    this.termesDejaUtilises.push(this.mot);
  }

  chercheAvecTerme(terme: string) {
    this.content = [];
    this.jdmService.get(terme).subscribe(
      data => this.termes = data,
      error => console.log('erreur recup serveur'),
      () => this.reInit()
    );
    this.termesDejaUtilises.push(terme);
  }

  chercherTermesCompletion() {
    console.log('écriture: ' + this.mot);
    if (this.mot.length > 0) {
      this.jdmService.getTermesCompletion(this.mot).subscribe(
        data => this.termesCompletion = data,
        error => console.log(error)
      );
    } else {
        this.termesCompletion = [];
    }
  }

  getData() {
    console.log('ICIICIICISI');
    console.log(this.termes.length);
    /*for (let i = this.counter + 1; i < this.termes.length; i++) {
      this.content.push(this.termes[i]);
      if (i % 10 === 0) { break; }
    }*/
    let j = this.counter + 1;
    while (j % 10 !== 0) {
      this.content.push(this.termes[j]);
      j++;
    }
    this.counter += 10;
  }

}
