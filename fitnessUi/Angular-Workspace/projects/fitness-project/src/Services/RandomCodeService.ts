import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RandomCodeService {

  private verificationCode: string="";

  generateVerificationCode(): string {
    let code = '';
    const characters = '0123456789'; // You can include other characters if needed
    for (let i = 0; i < 6; i++) {
      code += characters.charAt(Math.floor(Math.random() * characters.length));
    }
    this.verificationCode = code;
    return this.verificationCode;
  }

  getVerificationCode(): string {
    return this.verificationCode;
  }
}
