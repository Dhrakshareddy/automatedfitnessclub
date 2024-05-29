export class Exercise{
	createdBy:any;
    constructor(
       
	public exerciseId:number,
	public  exerciseName:string,
	public  description:string,
    public  numberOfSets:number,
	public  equipmentNeeded:string,
	public  username:string
    ){}
    
}