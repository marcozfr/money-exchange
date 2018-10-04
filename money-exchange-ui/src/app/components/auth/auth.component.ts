import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service'
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss']
})
export class AuthComponent implements OnInit {

  loginForm: FormGroup;
  submitted = false;
  returnUrl: string;
  errorMessage: string;

  constructor(
      private authService : AuthService,
      private route: ActivatedRoute,
      private router: Router,
      private formBuilder: FormBuilder){}

  ngOnInit() {

    this.loginForm = this.formBuilder.group({
        username: ['', Validators.required],
        password: ['', Validators.required]
    });

    this.authService.logout();
    this.returnUrl = '/';
  }

  onSubmit() {
          this.submitted = true;

          if (this.loginForm.invalid) {
              return;
          }

          this.authService.login(this.loginForm.controls.username.value, this.loginForm.controls.password.value)
              .pipe(first())
              .subscribe(
                  data => {
                      this.router.navigate(['/']);
                  },
                  error => {
                      if(error.status == '401'){
                          this.errorMessage = 'Invalid Credentials';
                      }else {
                          this.errorMessage = 'Internal Server error';
                      }
                  });
      }


}
