import { Component, OnInit } from '@angular/core';
import {JdmServiceService} from '../services/jdm-service.service';

@Component({
  selector: 'app-jdm',
  templateUrl: './jdm.component.html',
  styleUrls: ['./jdm.component.css']
})
export class JdmComponent implements OnInit {
  mot: string;
  termes: any[];
  termesDejaUtilises: string[];
  termesCompletion: any[];

  constructor(private jdmService: JdmServiceService) { }

  ngOnInit() {
    this.mot = '';
    this.termesDejaUtilises = [];
  }

  cherche() {
    this.jdmService.get(this.mot).subscribe(
      termes => this.termes = termes
    );
    this.termesDejaUtilises.push(this.mot);
    this.termesCompletion = [];
    this.mot = ' ';
  }

  chercheAvecTerme(terme: string) {
    this.jdmService.get(terme).subscribe(
      data => this.termes = data
    );
    this.termesDejaUtilises.push(terme);
    this.termesCompletion = [];
    this.mot = ' ';
  }

  chercherTermesCompletion() {
    console.log('écriture: ' + this.mot);
    if (this.mot.length > 0) {
      this.jdmService.getTermesCompletion(this.mot).subscribe(
        data => this.termesCompletion = data
      );
    } else {
        this.termesCompletion = [];
    }
  }

}
