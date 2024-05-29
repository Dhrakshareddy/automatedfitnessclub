package com.training.bean;

import java.util.List;

import com.training.entity.Exercise;
import com.training.entity.Workout;

public class ProgressCalculationRequest {
    private List<Exercise> exercises;
    private List<Workout> workouts;

   
    public ProgressCalculationRequest() {
    }

    
    public ProgressCalculationRequest(List<Exercise> exercises, List<Workout> workouts) {
        this.exercises = exercises;
        this.workouts = workouts;
    }

    
    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }


}
