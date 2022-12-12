import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Affectation } from '../affectation';
import { AffectationService } from '../affectation.service';

@Component({
  selector: 'app-affectaion-list',
  templateUrl: './affectaion-list.component.html',
  styleUrls: ['./affectaion-list.component.css']
})
export class AffectaionListComponent implements OnInit {
  affectations!:Affectation[];
  constructor(private affecService:AffectationService, private rtr:Router) {}

  ngOnInit(): void {
    this.getAffectations();
  }
  private getAffectations(){
    this.affecService.getAffectationsList().subscribe(data=>{
      this.affectations=data;
    })
  }

}
