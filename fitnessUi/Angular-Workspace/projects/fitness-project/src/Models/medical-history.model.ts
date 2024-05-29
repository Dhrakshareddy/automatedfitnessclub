export class MedicalHistory {
	createdBy: any;

	constructor(
		public medicalHistoryId: number,
		public dateOfAssessment: string,
		public bloodPressure: string,
		public heartRate: number,
		public allergies: string,
          public previousInjuryOrSurgery: string,
		public fitnessLevel: number,
		public bodyMassIndex: number,
		public username: string
	) { }
}

