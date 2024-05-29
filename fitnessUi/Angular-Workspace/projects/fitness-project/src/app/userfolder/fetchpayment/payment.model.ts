export class Payments {
    createdBy: any;
 
  
    constructor(
      public paymentId: number,
      public paymentAmount: string,
      public paymentMode: string,
      public paymentDate: Date,
      public username:string,
      public userId:number
    ) {}
  }
  