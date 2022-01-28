import {Component, OnInit} from '@angular/core';
import {BackendService} from "../../service/backend.service";


export interface Person {
  id: number;
  nfcCard: NfcCard;
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

  consumations: Consumation[];
  displayedColumns: string[] = ['id', 'personId', 'date', 'hasConsumed'];


  constructor(private readonly backend: BackendService) {

    this.consumations = [];

  }

  ngOnInit(): void {
    this.backend.get('consumation').then(value => {
      this.consumations = value as Consumation[];
      console.log(this.consumations)


    });
  }
}
