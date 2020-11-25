import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http'
@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {
  cross:String;
  constructor(private router: Router,private http:HttpClient) { }

  ngOnInit(): void {
  }
  btn_Click(btn_num){
         console.log(btn_num);
     this.cross ='X';      
  }

  start() {
    var winnings = [
      [0, 1, 2], [3, 4, 5], [6, 7, 8],
      [0, 3, 6], [1, 4, 7], [2, 5, 8],
      [0, 4, 8], [2, 4, 6]
    ];

    
    
    



    
  }

  async logout(){
console.log('Log oUt Call');
    const url = 'http://localhost:5600/logout';
    const result: any = await this.http.get(url).toPromise();
    console.log(result.opr);
    if (result.opr) {
      this.router.navigate(['login']);
    } else {
      this.router.navigate(['error']);
    }
  }
}
