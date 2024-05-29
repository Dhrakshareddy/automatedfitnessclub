export class User {
  createdBy: any;

  constructor(
    public userId: number,
    public name: string,
    public password: string,
    public dateOfBirth: Date,
    public email: string,
    public contactNumber: number,
    public trainerCode:string,
    public doctorCode:string,
    public role:string,
    public fitnessActivity:string 
  ){}
}