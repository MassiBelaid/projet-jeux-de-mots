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

  constructor(private jdmService: JdmServiceService) { }

  ngOnInit() {
    this.mot = '';
  }

  cherche() {
    this.jdmService.get(this.mot).subscribe(
      termes => this.termes = termes
    );
  }

  chercherJoueur() {
    console.log('écriture: ' + this.mot);
  }

}
