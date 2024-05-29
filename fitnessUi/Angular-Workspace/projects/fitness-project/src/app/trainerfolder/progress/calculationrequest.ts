import { Exercise } from "../../../Models/exercise.model";
import { Workout } from "../../../Models/workout.model";



export class CalculationRequest {
    constructor(
      public exercises: Exercise[],
      public workouts: Workout[]
    ) {}
  }
  