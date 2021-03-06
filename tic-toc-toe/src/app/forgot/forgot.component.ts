import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-forgot',
  templateUrl: './forgot.component.html',
  styleUrls: ['./forgot.component.css']
})
export class ForgotComponent implements OnInit {

  constructor(private router: Router,
    private fb: FormBuilder,
    private http: HttpClient) { }



  ngOnInit(): void {
  }

  get email() {
    return this.forgot.get('email');
  }


  get password() {
    return this.forgot.get('password');
  }


  forgot = this.fb.group({
    email: ['', [Validators.required, Validators.email, Validators.maxLength(12), Validators.minLength(3)]],
    password: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(7)]],
    Cpassword: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(7)]],
  });

  flag: boolean = false;
  message:string ='';
  ///////////////Ajax///////////////////////////
  async ForgotHere() {

    const data = this.forgot.value;
    console.log(data);
    if (data.Cpassword === data.password) {
      // const url_send = 'http://localhost:5600/sendmessage';
      // const result_send: any = await this.http.post(url_send, data).toPromise();


      const url = 'http://localhost:5600/forgot';

      const result: any = await this.http.post(url, data).toPromise();

      if (result.opr ==='true') {
        //sesssion manangemt
        this.router.navigate(['login']);
      } else {
        this.message=result.message;
        this.flag=true;
      }
    }else{
      this.message="Password did not match";
      this.flag=true;
    }

  }
  RegisterPage() {
    this.router.navigate(['login']);
  }
}
