import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {BackendService} from "../../service/backend.service";
import {HttpClient} from "@angular/common/http";

export interface Person {
  id: number;
  nfccard: NfcCard;
  firstName: string;
  lastName: string;
  entryYear: number;
}

export interface NfcCard {
  nfcId: string;
  registerDateTime: Date;
}

export interface Consumation {
  id: number;
  person: Person;
  date: Date;
  hasConsumed: boolean;
}

@Component({
  selector: 'app-meals-overview',
  templateUrl: './meals-overview.component.html',
  styleUrls: ['./meals-overview.component.css']
})

export class MealsOverviewComponent implements OnInit {

  @Output() consumationSelected: EventEmitter<Object> = new EventEmitter<Object>();
  persons: Person[] | null;
  displayedColumns: string[] = ['id', 'eintrittsjahr', 'vorname', 'nachname', 'nfc_Card'];

  constructor(private readonly backend: BackendService,
              private http: HttpClient) {
    this.persons = null;
  }

  ngOnInit(): void {
    this.backend.get('person').then(value => {
      this.persons = value as Person[];
      this.consumationSelected.emit();
    });
  }
}
