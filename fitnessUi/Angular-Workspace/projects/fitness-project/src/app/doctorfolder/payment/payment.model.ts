export class Payment {
constructor(
 public paymentId: number,
   public  paymentAmount: number,
    public paymentMode: string,
    public membershipType:string,
   public  paymentDate: Date,
    public status: boolean,
   public username :string){}
  }