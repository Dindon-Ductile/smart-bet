import {Component, Inject, Input, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.scss']
})
export class DialogComponent implements OnInit {
  public message: string;
  public title: string;
  public type: string;

  constructor(@Inject(MAT_DIALOG_DATA) data) {
    this.title = data.title;
    this.message = data.message;
    this.type = data.type;
  }

  ngOnInit(): void {
  }
}
