export class Workout{
    createdBy:any;
    constructor(
        public workoutId:number,
        public workoutDate:Date,
        public duration:string,
        public setsCompleted: number,
       
         public username:string

    ){}
}