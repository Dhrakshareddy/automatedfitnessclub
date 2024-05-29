

  export class AttendanceUser{
	createdBy:any;
    constructor(
       
	public attendanceId:number,
	public  name:string,
	public  date:Date,
   
	public  status:string,
	public  feedback:string
    ){}
    
}