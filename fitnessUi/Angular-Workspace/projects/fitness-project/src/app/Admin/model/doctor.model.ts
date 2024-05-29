export class Doctor {
  createdBy: any;
  constructor(
    public doctorId: number,
    public name: string,
    public password: string,
    public age: string,
    public yearOfExperience: number,
    public contactNumber: number,
    public specialization: string,
    public doctorCode: string) {

  }
}



