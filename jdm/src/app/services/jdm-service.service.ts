import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

const httpOption = {
  headers: new HttpHeaders({
    'Access-Control-Allow-Methods': 'GET,POST',
    'Access-Control-Allow-Headers': 'Content-type',
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*'
  })
};

@Injectable({
  providedIn: 'root'
})
export class JdmServiceService {
  private urlBase = 'http://localhost:8080/';

  constructor(private http: HttpClient) { }

  get(nom: string): Observable<any> {
    return this.http.get(this.urlBase + '/get/' + nom);
  }

  getTermesCompletion(nom: string): Observable<any> {
    return this.http.get(this.urlBase + '/terme/' + nom, httpOption);
  }


}
